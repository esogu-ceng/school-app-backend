package tr.ogu.edu.school.schoolapp.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TermServiceTest {
	 @Mock
	    private TermRepository termRepository;

	    @InjectMocks
	    private TermService termService;

	    @Test
	    public void testGetInstallmentsByTermAndStudent() {
	        // Mock data
	        Term term = new Term("Spring Term", null, null, null);
	        Student student = new Student("John", "Doe", 10, null, null);
	        List<Installment> expectedInstallments = new ArrayList<>();
	        // Add expected installments if needed

	        // Mocking the behavior of termRepository
	        when(termRepository.findInstallmentsByStudentAndTerm(student, term)).thenReturn(expectedInstallments);

	        // Call the service method
	        List<Installment> actualInstallments = termService.getInstallmentsByTermAndStudent(term, student);

	        // Verify the result
	        assertEquals(expectedInstallments, actualInstallments);
	    }

}
