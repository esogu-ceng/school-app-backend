package tr.ogu.edu.school.schoolapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Payment;


import tr.ogu.edu.school.schoolapp.repository.TermRepository;

@Service
@RequiredArgsConstructor
public class TermService {

	private final TermRepository termRepository;

	public Term getTermById(Long id) {
		// FIXME sadece kendi öğrencilerine ait dönem için kullanıcı idsi oturum açan
		// kullanıcıdan alınarak sorguya eklenmeli.
		// yani findByIDAndUserId gibi bir metot repositorye eklenmeli ve çağrılmalı.
		Term term = termRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Term not found with id: " + id));
		return term;
	}

	@Transactional
	public Term createTerm(Term term) {
		term = termRepository.save(term);
		return term;
	}

	@Transactional
	public Term updateTerm(Term term) {
		Long id = term.getId();
		Term existingTerm = termRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Term not found with id: " + id));
		existingTerm.setTermName(term.getTermName());
		existingTerm.setStartDate(term.getStartDate());
		existingTerm.setEndDate(term.getEndDate());
		existingTerm = termRepository.save(existingTerm);
		return existingTerm;
	}

	@Transactional
	public boolean deleteTerm(Long id) {
		if (id == null || !termRepository.existsById(id)) {
			return false;
		}
		termRepository.deleteById(id);
		return true;
	}
	@Transactional(readOnly = true)
    public List<Installment> getInstallmentsForTermAndStudents(Long termId, Set<Long> studentIds) {
        return termRepository.findInstallmentsByTermIdAndStudentIds(termId, studentIds);
    }
	
}
