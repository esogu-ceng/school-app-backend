package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.service.InstallmentService;

@RestController
@RequestMapping("/installments")
@RequiredArgsConstructor
public class InstallmentController {

	private final InstallmentService installmentService;

	@GetMapping
	public ResponseEntity<List<Installment>> getAllInstallments() {
		return ResponseEntity.ok(installmentService.getAllInstallments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Installment>> getInstallmentsByUserId(@PathVariable Long id) {
		List<Installment> installments = installmentService.getInstallmentsByUserId(id);
		if (installments.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(installments);
	}

	@PostMapping
	public ResponseEntity<Installment> saveInstallment(@RequestBody Installment installment) {
		Installment savedInstallment = installmentService.saveInstallment(installment);
		return ResponseEntity.ok(savedInstallment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInstallment(@PathVariable Long id) {
		installmentService.deleteInstallment(id);
		return ResponseEntity.noContent().build();
	}
}
