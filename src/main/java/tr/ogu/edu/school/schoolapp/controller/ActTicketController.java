package tr.ogu.edu.school.schoolapp.controller;

import java.io.FileNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.ogu.edu.school.schoolapp.model.ActTicket;
import tr.ogu.edu.school.schoolapp.service.ActTicketService;

@RestController
@RequestMapping("/act-tickets")
public class ActTicketController {
    private final ActTicketService actTicketService;

    // Constructor
    public ActTicketController(ActTicketService actTicketService) {
        this.actTicketService = actTicketService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createActTicket(@RequestBody ActTicket actTicket) throws FileNotFoundException {
        actTicketService.createActTicket(actTicket);
        return ResponseEntity.ok("Bilet başarıyla oluşturuldu.");
    }
}