package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.TermDto;
import tr.ogu.edu.school.schoolapp.service.TermService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/terms")
public class TermController {

	private final TermService termService;

	@GetMapping
	public ResponseEntity<List<TermDto>> getAllTerms() {
		return ResponseEntity.ok(termService.getAllTerms());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TermDto> getTermById(@PathVariable Long id) {
		TermDto termDto = termService.getTermById(id);
		return ResponseEntity.ok(termDto);
	}

	@PostMapping
	public ResponseEntity<TermDto> createTerm(@RequestBody TermDto termDto) {
		TermDto createdTerm = termService.createTerm(termDto);
		return ResponseEntity.ok(createdTerm);
	}

	@PutMapping
	public ResponseEntity<TermDto> updateTerm(@RequestBody TermDto termDto) {
		TermDto updatedTerm = termService.updateTerm(termDto);
		return ResponseEntity.ok(updatedTerm);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTerm(@PathVariable Long id) {
		boolean result = termService.deleteTerm(id);
		return ResponseEntity.ok(result);
	}
}