package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.repository.InstallmentRepository;

@Service
@RequiredArgsConstructor
public class InstallmentService {

	private final InstallmentRepository installmentRepository;

	public List<Installment> getAllInstallments() {
		return installmentRepository.findAll();
	}

	public Optional<Installment> getInstallmentById(Long id) {
		return installmentRepository.findById(id);
	}

	public Installment saveInstallment(Installment installment) {
		return installmentRepository.save(installment);
	}

	public void deleteInstallment(Long id) {
		installmentRepository.deleteById(id);
	}
}
