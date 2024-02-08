package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;
import tr.ogu.edu.school.schoolapp.service.StudentService;
import tr.ogu.edu.school.schoolapp.service.UserService;

@Service
@RequiredArgsConstructor
public class TermService {

	private final TermRepository termRepository;
	private final AuthenticationService authenticationService;
	private final StudentService studentService;
	private final UserService userService;

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
	 public List<Installment> getInstallmentsByTermAndStudent(Long termId, Long studentId) {
	        // Kullanıcının öğrencilerini alır
	        List<Student> userStudents = studentService.getMyStudents();

	        // Verilen studentId parametresine göre istenen öğrenciyi alır
	        Student requestedStudent = studentService.getStudentById(studentId);

	        // Eğer istenen öğrenci kullanıcının öğrencileri arasında değilse, hata döndürür
	        if (!userStudents.contains(requestedStudent)) {
	            throw new IllegalArgumentException("Requested student is not among the user's students.");
	        }

	        Optional<Term> optionalTerm = termRepository.findById(termId);
	        Term term = optionalTerm.orElseThrow(() -> new IllegalArgumentException("Term not found with id: " + termId));
	        List<Installment> installments = termRepository.findInstallmentsByStudentAndTerm(requestedStudent, term);

	        return installments;
	}
	
}
