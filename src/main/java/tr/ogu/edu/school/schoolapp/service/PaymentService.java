package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

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

	// FIXME: Bu metod ileride, kullanıcının ödemelerini getirecek şekilde
	// güncellenecektir.
	public List<PaymentDto> getPaymentsForCurrentUser(String currentUserMail) {
		// Ödeme repository'sinde uygun sorgu metodunu tanımlayana kadar geçici bir
		// çözüm
		// Şu anda tüm ödemeleri döndürüyor, ancak bu ileride değişecek.
		List<Payment> payments = paymentRepository.findAll(); // Geçici olarak tüm ödemeleri getir
		return payments.stream().map(PaymentMapper::toPaymentDto).collect(Collectors.toList());
	}

	public PaymentDto getPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + id));
		return PaymentMapper.toPaymentDto(payment);
	}

	@Transactional
	public PaymentDto createPayment(PaymentDto paymentDto) {
		Payment payment = PaymentMapper.fromPaymentDto(paymentDto);
		payment = paymentRepository.save(payment);
		return PaymentMapper.toPaymentDto(payment);
	}

	@Transactional
	public PaymentDto updatePayment(PaymentDto paymentDto) {
		Long id = paymentDto.getId();
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + id));
		payment.setAmount(paymentDto.getAmount());
		payment.setPaymentDate(paymentDto.getPaymentDate());
		payment = paymentRepository.save(payment);
		return PaymentMapper.toPaymentDto(payment);
	}

	public boolean deletePayment(Long id) {
		if (!paymentRepository.existsById(id)) {
			return false;
		}
		paymentRepository.deleteById(id);
		return true;
	}
}
