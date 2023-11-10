package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Payment;
import tr.ogu.edu.school.schoolapp.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Optional<Payment> getPaymentById(Long id) {
		return paymentRepository.findById(id);
	}

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public void deletePayment(Long id) {
		paymentRepository.deleteById(id);
	}
	
	public boolean isPaymentCompleted(Long id) {
	    return paymentRepository.findById(id).map(Payment::isPaymentCompleted).orElse(false);
	}
}
