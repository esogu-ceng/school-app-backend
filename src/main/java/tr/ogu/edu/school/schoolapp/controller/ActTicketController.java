package tr.ogu.edu.school.schoolapp.controller;

import java.io.FileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.exception.TicketRequiredException;
import tr.ogu.edu.school.schoolapp.model.ActTicket;
import tr.ogu.edu.school.schoolapp.service.ActTicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/act-tickets")
public class ActTicketController {
    private final ActTicketService actTicketService;

    @PostMapping("/create")
    public ResponseEntity<String> createActTicket(@RequestBody ActTicket actTicket) throws FileNotFoundException {
        actTicketService.createActTicket(actTicket);
        return ResponseEntity.ok("Bilet başarıyla oluşturuldu.");
    }

    // Bileti çekmemizi sağlayan endpoint
    @GetMapping("/ticket/{verificationCode}")
    public ResponseEntity<Resource> getTicketPdf(@PathVariable String verificationCode) throws IOException {
        try {
            Resource resource = actTicketService.getTicketPdfResource(verificationCode);    // Dosya kaynağını almak için
            return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_PDF).
                header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + verificationCode + ".pdf").
                body(resource);
        } catch (TicketRequiredException e) {
            // TODO: handle exception
            throw new TicketRequiredException(e);
        }
    }
}