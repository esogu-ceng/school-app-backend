package tr.ogu.edu.school.schoolapp.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	public void createActTicket(ActTicket actTicket) {

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
			sendTicketEmailtoUser(actTicket, user.getMail());
			log.info("Koltuk: {}-{} Kullanıcı: {} için bilet oluşturuldu",
					actTicket.getActSessionHallSeat().getActSeat().getLine(),
					actTicket.getActSessionHallSeat().getActSeat().getNo(), user.getMail());
		} catch (Exception e) {
			log.error("Bilet oluşturulamadı", e);
			throw new TicketRequiredException(e);
		}
	}

	private void sendTicketEmailtoUser(ActTicket actTicket, String mail) {
		String mailContent = settingRepository.getMailContent().getValue();
		String ticketUrl = "http://app.bbomeskisehir.com:8080/tickets/public/" + actTicket.getVerificationCode() + "/"
				+ actTicket.getActSessionHallSeat().getActSeat().getLine() + "/"
				+ actTicket.getActSessionHallSeat().getActSeat().getNo();
		mailContent = mailContent.replaceAll("__TICKET_URL__", ticketUrl);
		mailService.sendMailWithAttachment(actTicket.getUser().getMail(), "Biletiniz Burada :)", mailContent,
				actTicket.getFilepath(), Arrays.asList(Pair.with("logo.png", "jasper-report-templates/icon.png"),
						Pair.with("instagram.png", "jasper-report-templates/instagram.png")));
	}

	// Parametreleri birlikte almak için
	private Map<String, Object> preparePdfParameters(ActTicket actTicket) {
		Map<String, Object> parameters = new HashMap<>();

		// PDF şablonu için gerekli parametreleri belirleyip alıyoruz
		parameters.put("verification_code", actTicket.getVerificationCode());
		parameters.put("session_date", actTicket.getSessionDate());
		parameters.put("seat_line", actTicket.getActSessionHallSeat().getActSeat().getLine()); // required
		parameters.put("seat_no", String.valueOf(actTicket.getActSessionHallSeat().getActSeat().getNo())); // required

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
	public void createTickets(int count, int activityId) {
		List<ActSessionHallSeat> availableSeats = actSessionHallSeatRepository
				.findAllByStatusOrderByIdAsc(SeatStatus.AVAILABLE, PageRequest.of(0, count));
		for (ActSessionHallSeat actSessionHallSeat : availableSeats) {
			actSessionHallSeat.setStatus(SeatStatus.BLOCKED);
			ActTicket actTicket = new ActTicket();
			actTicket.setActSessionHallSeat(actSessionHallSeat);
			createActTicket(actTicket);
		}
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}