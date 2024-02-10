package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import tr.ogu.edu.school.schoolapp.mapper.InstallmentMapper;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.service.InstallmentService;

@RestController
@RequestMapping(value = "/installments")
@RequiredArgsConstructor
public class InstallmentController {

	private final InstallmentService installmentService;

	@GetMapping("/my-installments")
	public ResponseEntity<List<InstallmentDto>> getInstallmentsForCurrentUser() {
		List<Installment> installments = installmentService.getCurrentUserInstallments();
		List<InstallmentDto> installmentDtos = installments.stream().map(InstallmentMapper::toInstallmentDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(installmentDtos);
	}

	@PostMapping
	public ResponseEntity<InstallmentDto> createInstallment(@RequestBody InstallmentDto installmentDto) {
		Installment installment = InstallmentMapper.fromInstallmentDto(installmentDto);
		Installment createdInstallment = installmentService.createInstallment(installment);
		InstallmentDto createdInstallmentDto = InstallmentMapper.toInstallmentDto(createdInstallment);
		return ResponseEntity.ok(createdInstallmentDto);
	}

	@PutMapping
	public ResponseEntity<InstallmentDto> updateInstallment(@RequestBody InstallmentDto installmentDto) {
		Installment installment = InstallmentMapper.fromInstallmentDto(installmentDto);
		Installment updatedInstallment = installmentService.updateInstallment(installment);
		InstallmentDto updatedInstallmentDto = InstallmentMapper.toInstallmentDto(updatedInstallment);
		return ResponseEntity.ok(updatedInstallmentDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInstallment(@PathVariable Long id) {
		installmentService.deleteInstallment(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/paid")
    public ResponseEntity<List<Installment>> getPaidInstallments() {
		List<Installment> paidInstallments = installmentService.getPaidInstallments();
        return ResponseEntity.ok(paidInstallments);
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<Installment>> getUnpaidInstallments() {
    	  List<Installment> unpaidInstallments = installmentService.getUnpaidInstallments();
          return ResponseEntity.ok(unpaidInstallments);
    }
}
