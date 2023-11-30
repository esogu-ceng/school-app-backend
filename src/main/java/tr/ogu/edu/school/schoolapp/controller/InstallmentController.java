package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.InstallmentDto;
import tr.ogu.edu.school.schoolapp.service.InstallmentService;

@RestController
@RequestMapping(value = "/installments")
@RequiredArgsConstructor
public class InstallmentController {

	private final InstallmentService installmentService;

	@GetMapping("/my-installments")
	public ResponseEntity<List<InstallmentDto>> getInstallmentsForCurrentUser() {
		// FIXME: Oturum açan kullanıcının bilgilerini Spring Security üzerinden alacak
		// şekilde güncellenmeli.
		// Örnek olarak geçici bir userId kullanılmıştır.
		Long userId = 1L; // Bu kısım, oturum açan kullanıcının kimliğiyle değiştirilmeli.
		List<InstallmentDto> installments = installmentService.getInstallmentsByUserId(userId);
		if (installments.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(installments);
	}

	@PostMapping
	public ResponseEntity<InstallmentDto> createInstallment(@RequestBody InstallmentDto installmentDto) {
		InstallmentDto savedInstallment = installmentService.createInstallment(installmentDto);
		return new ResponseEntity<>(savedInstallment, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<InstallmentDto> updateInstallment(@RequestBody InstallmentDto installmentDto) {
		InstallmentDto updatedInstallment = installmentService.updateInstallment(installmentDto);
		return ResponseEntity.ok(updatedInstallment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInstallment(@PathVariable Long id) {
		installmentService.deleteInstallment(id);
		return ResponseEntity.noContent().build();
	}
}
