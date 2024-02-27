package tr.ogu.edu.school.schoolapp.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.javatuples.Pair;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tr.ogu.edu.school.schoolapp.enums.SeatStatus;
import tr.ogu.edu.school.schoolapp.enums.TicketStatus;
import tr.ogu.edu.school.schoolapp.exception.InvalidTicketException;
import tr.ogu.edu.school.schoolapp.exception.TicketRequiredException;
import tr.ogu.edu.school.schoolapp.model.ActSessionHallSeat;
import tr.ogu.edu.school.schoolapp.model.ActTicket;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.ActSessionHallSeatRepository;
import tr.ogu.edu.school.schoolapp.repository.ActTicketRepository;
import tr.ogu.edu.school.schoolapp.repository.SettingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActTicketService {
	private final ActTicketRepository actTicketRepository;
	private final PdfGeneratorService pdfGeneratorService;
	private final AuthenticationService authenticationService;
	private final MailService mailService;
	private final SettingRepository settingRepository;
	private final ActSessionHallSeatRepository actSessionHallSeatRepository;

	@Transactional
	public String createActTicket(ActTicket actTicket, String email) {

		// FIXME Daha sonra veritabanından çekilmeli
		String templatePath = "jasper-report-templates/ticket.jrxml"; // Şablon dosyasının bulunduğu yer

		// PDF üretme metodunu çağırıyoruz
		try {
			User user = authenticationService.getAuthenticatedUser();
			actTicket.setVerificationCode(UUID.randomUUID().toString());
			actTicket.setUser(user);
			actTicket.setStatus(TicketStatus.VALID);
			actTicket.getActSessionHallSeat().setStatus(SeatStatus.OCCUPIED);
			actTicket.getActSessionHallSeat().setUser(user);

			// ActTicket objesinden gerekli parametreleri alıyoruz
			Map<String, Object> parameters = preparePdfParameters(actTicket);
			String pdfFilePath = pdfGeneratorService.generatePdf("Bilet-" + actTicket.getVerificationCode(), parameters,
					templatePath);
			actTicket.setFilepath(pdfFilePath);
			actSessionHallSeatRepository.save(actTicket.getActSessionHallSeat());
			actTicketRepository.save(actTicket); // Kayıt yapılıyor
			String ticketUrl = sendTicketEmailtoUser(actTicket, email);
			log.info("Koltuk: {}-{} Kullanıcı: {} için bilet oluşturuldu",
					actTicket.getActSessionHallSeat().getActSeat().getLine(),
					actTicket.getActSessionHallSeat().getActSeat().getNo(), user.getTckn());
			return ticketUrl;
		} catch (Exception e) {
			log.error("Bilet oluşturulamadı", e);
			throw new TicketRequiredException(e);
		}
	}

	private String sendTicketEmailtoUser(ActTicket actTicket, String mail) {
		String ticketUrl = "https://app.bbomeskisehir.com:8444/tickets/public/" + actTicket.getVerificationCode() + "/"
				+ actTicket.getActSessionHallSeat().getActSeat().getLine() + "/"
				+ actTicket.getActSessionHallSeat().getActSeat().getNo();
		if (mail != null && !mail.isEmpty()) {
			String mailContent = settingRepository.getMailContent().getValue();
			mailContent = mailContent.replaceAll("__TICKET_URL__", ticketUrl);
			mailService.sendMailWithAttachment(mail, "Biletiniz Burada :)", mailContent, actTicket.getFilepath(),
					Arrays.asList(Pair.with("logo.png", "jasper-report-templates/icon.png"),
							Pair.with("instagram.png", "jasper-report-templates/instagram.png")));
		}
		return ticketUrl;
	}

	// Parametreleri birlikte almak için
	private Map<String, Object> preparePdfParameters(ActTicket actTicket) {
		Map<String, Object> parameters = new HashMap<>();

		// PDF şablonu için gerekli parametreleri belirleyip alıyoruz
		parameters.put("verification_code", actTicket.getVerificationCode());
		parameters.put("session_date", actTicket.getSessionDate());
		parameters.put("seat_line", actTicket.getActSessionHallSeat().getActSeat().getLine()); // required
		parameters.put("seat_no", String.valueOf(actTicket.getActSessionHallSeat().getActSeat().getNo())); // required

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleHourFormat = new SimpleDateFormat("HH:mm");
		parameters.put("act_date",
				simpleDateFormat.format(actTicket.getActSessionHallSeat().getActSessionInfo().getActivityDate())); // required
		parameters.put("act_hour",
				simpleHourFormat.format(actTicket.getActSessionHallSeat().getActSessionInfo().getActivityDate())); // required
		parameters.put("hall_address",
				String.valueOf(actTicket.getActSessionHallSeat().getActSessionInfo().getActHall().getName())); // required
		parameters.put("hall_name",
				String.valueOf(actTicket.getActSessionHallSeat().getActSessionInfo().getActivityName())); // required

		return parameters;
	}

	// Pdf kaynağını alma metodu
	public Resource getTicketPdfResource(String verificationCode) {
		String filePath = actTicketRepository.findByVerificationCode(verificationCode).getFilepath();

		try {
			Path path = Paths.get(filePath);
			return new UrlResource(path.toUri());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<String> createTickets(Long offset, int count, int activityId, String email) {
		List<ActSessionHallSeat> availableSeats = actSessionHallSeatRepository
				.findAllByStatusAndIdGreaterThanOrderByIdAsc(SeatStatus.AVAILABLE, offset, PageRequest.ofSize(count));
		if (availableSeats.size() < count)
			return null;
		List<String> ticketUrls = new ArrayList<>();
		ActSessionHallSeat actSessionHallSeat = availableSeats.get(0);
		String line = actSessionHallSeat.getActSeat().getLine();
		for (int i = 1; i < availableSeats.size(); i++) {
			actSessionHallSeat = availableSeats.get(i);
			String curLine = actSessionHallSeat.getActSeat().getLine();
			if (!curLine.equals(line)) {
				List<String> retList = createTickets(availableSeats.get(i - 1).getId(), count, activityId, email);
				if (retList == null) {
					break;
				}
				return retList;
			}
		}

		for (ActSessionHallSeat actSessionHallSeat2 : availableSeats) {
			actSessionHallSeat2.setStatus(SeatStatus.BLOCKED);
			ActTicket actTicket = new ActTicket();
			actTicket.setActSessionHallSeat(actSessionHallSeat2);
			String ticketUrl = createActTicket(actTicket, email);
			ticketUrls.add(ticketUrl);
		}
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticketUrls;
	}

	@Transactional
	public Map<String, Object> verifyTicket(String qrCode) {
		ActTicket ticket = actTicketRepository.findByVerificationCode(qrCode);

		if (ticket == null) {
			throw new InvalidTicketException();
		}

		// Biletin mevcut durumu
		TicketStatus currentStatus = ticket.getStatus();

		// Biletin durumunu 'Kullanıldı' olarak güncelleme
		ticket.setStatus(TicketStatus.USED);
		actTicketRepository.save(ticket);

		// Koltuk no ve durumun json olarak gitmesi
		Map<String, Object> response = new HashMap<>();
		response.put("seatNumber", ticket.getActSessionHallSeat().getActSeat().getNo());
		response.put("seatLine", ticket.getActSessionHallSeat().getActSeat().getLine());
		response.put("currentStatus", currentStatus);

		return response;
	}
}