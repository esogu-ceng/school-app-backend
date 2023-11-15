package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.PaymentDto;
import tr.ogu.edu.school.schoolapp.service.PaymentService;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
		PaymentDto paymentDto = paymentService.getPaymentById(id);
		if (paymentDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(paymentDto);
	}

	@PostMapping
	public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
		PaymentDto newPayment = paymentService.createPayment(paymentDto);
		return ResponseEntity.ok(newPayment);
	}

	@PutMapping
	public ResponseEntity<PaymentDto> updatePayment(@RequestBody PaymentDto paymentDto) {
		PaymentDto updatedPayment = paymentService.updatePayment(paymentDto);
		return ResponseEntity.ok(updatedPayment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePayment(@PathVariable Long id) {
		boolean result = paymentService.deletePayment(id);
		return result ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}
}
