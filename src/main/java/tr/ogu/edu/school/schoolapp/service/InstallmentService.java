package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

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

	public List<Installment> getInstallmentsByUserId(Long userId) {
		return installmentRepository.findInstallmentsByUserId(userId);
	}

	public Installment saveInstallment(Installment installment) {
		return installmentRepository.save(installment);
	}

	public void deleteInstallment(Long id) {
		installmentRepository.deleteById(id);
	}
}
