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

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.PaymentDto;
import tr.ogu.edu.school.schoolapp.service.PaymentService;
import tr.ogu.edu.school.schoolapp.util.JwtUtil;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	private final JwtUtil jwtUtil;

	// FIXME: Bu endpoint'in oturum açan kullanıcının ödemelerini doğru bir şekilde
	// getirecek şekilde güncellenmesi gerekmektedir.
	@GetMapping("/my-payments")
	public ResponseEntity<List<PaymentDto>> getCurrentUserPayments(HttpServletRequest request) {
		String token = request.getHeader("Authorization").substring(7);
		String userMail = jwtUtil.extractUsername(token);

		// FIXME: Geçici olarak, bu metot oturum açan kullanıcının e-postasını
		// kullanarak ödemeleri getirecek şekilde güncellenecek.
		List<PaymentDto> paymentDtos = paymentService.getPaymentsForCurrentUser(userMail);
		return ResponseEntity.ok(paymentDtos);
	}

	@GetMapping("/{id}")
	// FIXME: Bu metot güvenlik açısından gözden geçirilmeli ve yalnızca oturum açan
	// kullanıcıya ait ödemeleri getirecek şekilde güncellenmelidir.
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
		PaymentDto paymentDto = paymentService.getPaymentById(id);
		if (paymentDto == null) {
			return ResponseEntity.notFound().build();
		}
		// FIXME: Ödemenin gerçekten oturum açan kullanıcıya ait olduğunu
		// doğrulanmalıdır.
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
