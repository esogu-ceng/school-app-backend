package tr.ogu.edu.school.schoolapp.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.Payment;
import tr.ogu.edu.school.schoolapp.model.Term;
import tr.ogu.edu.school.schoolapp.repository.TermRepository;

public class TermServiceTest {
	 @Mock
	    private TermRepository termRepository;

	    @InjectMocks
	    private TermService termService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testGetPaymentsByTermId() {
	        // Arrange
	        Long termId = 1L;
	        Term term = new Term();
	        term.setId(termId);

	        List<Installment> installments = new ArrayList<>();
	        Payment payment1 = new Payment(100.0, new Date());
	        Payment payment2 = new Payment(200.0, new Date());
	        Installment installment1 = new Installment(100.0, new Date(), term, null, payment1);
	        Installment installment2 = new Installment(200.0, new Date(), term, null, payment2);
	        installments.add(installment1);
	        installments.add(installment2);
	        term.setInstallments(installments);

	        when(termRepository.findById(anyLong())).thenReturn(Optional.of(term));

	        // Act
	        List<Payment> payments = termService.getPaymentsByTermId(termId);

	        // Assert
	        assertEquals(2, payments.size());
	    }
}
