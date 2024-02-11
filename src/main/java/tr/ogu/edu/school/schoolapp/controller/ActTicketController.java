package tr.ogu.edu.school.schoolapp.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.ActTicket;
import tr.ogu.edu.school.schoolapp.service.ActTicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tickets")
public class ActTicketController {
	private final ActTicketService actTicketService;

	@PostMapping("/create")
	public ResponseEntity<String> createActTicket(@RequestBody ActTicket actTicket) {
		actTicketService.createActTicket(actTicket);
		return ResponseEntity.ok("Bilet başarıyla oluşturuldu.");
	}

	@PostMapping("/getSequentialTicket")
	public ResponseEntity<String> createTickets(@RequestParam int count, @RequestParam int activityId) {
		actTicketService.createTickets(count, activityId);
		return ResponseEntity.ok("Biletler başarıyla oluşturuldu ve gönderildi.");
	}

	// Bileti çekmemizi sağlayan endpoint
	@GetMapping("/public/{verificationCode}/{line}/{no}")
	public ResponseEntity<Resource> getTicketAsPdf(@PathVariable String verificationCode, @PathVariable String line,
			@PathVariable int no) {
		Resource resource = actTicketService.getTicketPdfResource(verificationCode); // Dosya kaynağını almak için
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + verificationCode + ".pdf").body(resource);
	}

}