package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.PaymentDto;
import tr.ogu.edu.school.schoolapp.model.Payment;

public class PaymentMapper {
	private PaymentMapper() {
	}

	public static PaymentDto toPaymentDto(Payment payment) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setId(payment.getId());
		paymentDto.setAmount(payment.getAmount());
		paymentDto.setPaymentDate(payment.getPaymentDate());
		return paymentDto;
	}

	public static Payment fromPaymentDto(PaymentDto paymentDto) {
		Payment payment = new Payment();
		payment.setId(paymentDto.getId());
		payment.setAmount(paymentDto.getAmount());
		payment.setPaymentDate(payment.getPaymentDate());
		return payment;
	}
}
