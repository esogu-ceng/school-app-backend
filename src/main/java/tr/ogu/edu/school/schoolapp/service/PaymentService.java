package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.PaymentDto;
import tr.ogu.edu.school.schoolapp.mapper.PaymentMapper;
import tr.ogu.edu.school.schoolapp.model.Payment;
import tr.ogu.edu.school.schoolapp.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public List<Payment> getPaymentsForCurrentUser(String currentUserMail) {
		// FIXME: Bu metod ileride, kullanıcının ödemelerini getirecek şekilde
		// güncellenecektir.
		return paymentRepository.findAll(); // Geçici olarak tüm ödemeleri getiriliyor.
	}

	public PaymentDto getPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + id));
		return PaymentMapper.toPaymentDto(payment);
	}

	@Transactional
	public Payment createPayment(Payment payment) {
		if (payment.getAmount() == null || payment.getPaymentDate() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect payment information. Please fill in all required fields.");
		}
		return paymentRepository.save(payment);
	}

	@Transactional
	public Payment updatePayment(Payment payment) {
		Long id = payment.getId();
		Payment existingPayment = paymentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + payment.getId()));

		if (payment.getAmount() == null || payment.getPaymentDate() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect payment information. Please fill in all required fields.");
		}

		existingPayment.setAmount(payment.getAmount());
		existingPayment.setPaymentDate(payment.getPaymentDate());
		return paymentRepository.save(existingPayment);
	}

	@Transactional
	public boolean deletePayment(Long id) {
		if (!paymentRepository.existsById(id)) {
			return false;
		}
		paymentRepository.deleteById(id);
		return true;
	}
}
