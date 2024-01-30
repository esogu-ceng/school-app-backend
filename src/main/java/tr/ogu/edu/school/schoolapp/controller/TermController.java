package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import tr.ogu.edu.school.schoolapp.mapper.TermMapper;
import tr.ogu.edu.school.schoolapp.model.Payment;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.service.TermService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/terms")
public class TermController {

	private final TermService termService;

	@GetMapping("/{id}")
	public ResponseEntity<TermDto> getTermById(@PathVariable Long id) {
		Term term = termService.getTermById(id);
		return ResponseEntity.ok(TermMapper.toTermDto(term));
	}

	@PostMapping
	public ResponseEntity<TermDto> createTerm(@RequestBody TermDto termDto) {
		Term term = TermMapper.fromTermDto(termDto);
		Term createdTerm = termService.createTerm(term);
		return ResponseEntity.ok(TermMapper.toTermDto(createdTerm));
	}

	@PutMapping
	public ResponseEntity<TermDto> updateTerm(@RequestBody TermDto termDto) {
		Term term = TermMapper.fromTermDto(termDto);
		Term updatedTerm = termService.updateTerm(term);
		return ResponseEntity.ok(TermMapper.toTermDto(updatedTerm));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTerm(@PathVariable Long id) {
		boolean result = termService.deleteTerm(id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{termId}/payments")
    public ResponseEntity<List<Payment>> getPaymentsByTermId(@PathVariable Long termId) {
        List<Payment> payments = termService.getPaymentsByTermId(termId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}