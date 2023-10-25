package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;

@Service
@RequiredArgsConstructor
public class TermService {

	private final TermRepository termRepository;

	public List<Term> getAllTerms() {
		return termRepository.findAll();
	}

	public Optional<Term> getTermById(Long id) {
		return termRepository.findById(id);
	}

	public Term saveTerm(Term term) {
		return termRepository.save(term);
	}

	public void deleteTerm(Long id) {
		termRepository.deleteById(id);
	}
}
