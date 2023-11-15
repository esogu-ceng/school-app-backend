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

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<InstallmentDto>> getInstallmentsByUserId(@PathVariable Long userId) {
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

	@PutMapping("/{id}")
	public ResponseEntity<InstallmentDto> updateInstallment(@PathVariable Long id,
			@RequestBody InstallmentDto installmentDto) {
		InstallmentDto updatedInstallment = installmentService.updateInstallment(id, installmentDto);
		return ResponseEntity.ok(updatedInstallment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInstallment(@PathVariable Long id) {
		installmentService.deleteInstallment(id);
		return ResponseEntity.noContent().build();
	}
}
