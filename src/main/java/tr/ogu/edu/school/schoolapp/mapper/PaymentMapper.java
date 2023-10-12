package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.PaymentDto;
import tr.ogu.edu.school.schoolapp.model.Payment;

public class PaymentMapper {
	private PaymentMapper() {
	}

	public static PaymentDto toPaymentDto(Payment payment) {
		PaymentDto paymentDto = new PaymentDto();
		return paymentDto;
	}

	public static Payment fromPaymentDto(PaymentDto paymentDto) {
		Payment payment = new Payment();
		return payment;
	}
}
