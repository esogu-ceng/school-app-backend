package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.service.ActTicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tickets")
public class ActTicketController {
	private final ActTicketService actTicketService;

	@PostMapping("/getSequentialTicket")
	public ResponseEntity<List<String>> createTickets(@RequestParam int count, @RequestParam int activityId,
			@RequestParam(required = false) String email) {
		List<String> ticketUrls = actTicketService.createTickets(0L, count, activityId, email);
		return ResponseEntity.ok(ticketUrls);
	}

	// Bileti çekmemizi sağlayan endpoint
	@GetMapping("/public/{verificationCode}/{line}/{no}")
	public ResponseEntity<Resource> getTicketAsPdf(@PathVariable String verificationCode, @PathVariable String line,
			@PathVariable int no) {
		Resource resource = actTicketService.getTicketPdfResource(verificationCode); // Dosya kaynağını almak için
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + verificationCode + ".pdf").body(resource);
	}

	// Biletin kullanım durumu
	@PostMapping("/public/{verificationCode}/{line}/{no}")
	public ResponseEntity<Map<String, Object>> verifyTicket(@PathVariable String verificationCode,
        	@PathVariable String line, @PathVariable String no) {

    	// Biletin durumunu doğrular ve günceller
    	Map<String, Object> response = actTicketService.verifyTicket(verificationCode);
    	return ResponseEntity.ok(response);
	}
}
