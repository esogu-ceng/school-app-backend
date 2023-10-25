package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.service.TermService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/terms")
public class TermController {

	private final TermService termService;

	@GetMapping
	public List<Term> getAllTerms() {
		return termService.getAllTerms();
	}

	@GetMapping("/{id}")
	public Term getTermById(@PathVariable Long id) {
		return termService.getTermById(id).orElse(null);
	}

	@PostMapping
	public Term createTerm(@RequestBody Term term) {
		return termService.saveTerm(term);
	}

	@PutMapping("/{id}")
	public Term updateTerm(@PathVariable Long id, @RequestBody Term term) {
		if (termService.getTermById(id).isPresent()) {
			term.setId(id);
			return termService.saveTerm(term);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTerm(@PathVariable Long id) {
		termService.deleteTerm(id);
	}
}
