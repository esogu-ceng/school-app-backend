package tr.ogu.edu.school.schoolapp.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.exception.FileNotCreatedException;
import tr.ogu.edu.school.schoolapp.exception.TicketRequiredException;
import tr.ogu.edu.school.schoolapp.model.ActTicket;
import tr.ogu.edu.school.schoolapp.repository.ActTicketRepository;

@Service
@RequiredArgsConstructor
public class ActTicketService {
    private final ActTicketRepository actTicketRepository;
    private final PdfGeneratorService pdfGeneratorService;

    @Transactional
    public void createActTicket(ActTicket actTicket) throws FileNotFoundException {

        // ActTicket objesinden gerekli parametreleri alıyoruz
        Map<String, Object> parameters = preparePdfParameters(actTicket); 

        // FIXME Daha sonra veritabanından çekilmeli
        String templatePath = "jasper-report-templates/ticket.jrxml"; // Şablon dosyasının bulunduğu yer

        // PDF üretme metodunu çağırıyoruz
        try {
            String pdfFilePath = pdfGeneratorService.generatePdf(actTicket.getVerificationCode(), parameters, templatePath);
            actTicket.setFilepath(pdfFilePath); 
            actTicketRepository.save(actTicket);    // Kayıt yapılıyor
        } catch (FileNotCreatedException e) {
            // Rapor şablonu dosyası bulunamadı hatasını TicketRequiredException olarak yeniden fırlat
            throw new TicketRequiredException(e);
        }
    }

    // Parametreleri birlikte almak için
    private Map<String, Object> preparePdfParameters(ActTicket actTicket) {
        Map<String, Object> parameters = new HashMap<>();
        
        // PDF şablonu için gerekli parametreleri belirleyip alıyoruz
        parameters.put("verification_code", actTicket.getVerificationCode());
        parameters.put("session_date", actTicket.getSessionDate());
        parameters.put("session_hall_id", actTicket.getActSessionHall().getId());
        
        return parameters;
    }
}