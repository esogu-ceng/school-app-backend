package tr.ogu.edu.school.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.exception.UserNotLoggedInException;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.InstallmentRepository;

@Service
@RequiredArgsConstructor
public class InstallmentService {

	private final InstallmentRepository installmentRepository;
	private final AuthenticationService authenticationService;

	public List<Installment> getCurrentUserInstallments() {
		User authenticatedUser = authenticationService.getAuthenticatedUser();
		if (authenticatedUser == null) {
			throw new UserNotLoggedInException();
		}
		return installmentRepository.findInstallmentsByUserId(authenticatedUser.getId());
	}

	@Transactional
	public Installment createInstallment(Installment installment) {
		if (installment.getAmount() == null || installment.getDueDate() == null || installment.getTerm() == null
				|| installment.getStudent() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect installment information. Please fill in all required fields.");
		}
		return installmentRepository.save(installment);
	}

	@Transactional
	public Installment updateInstallment(Installment installment) {
		if (!installmentRepository.existsById(installment.getId())) {
			throw new RuntimeException("Installment not found with id: " + installment.getId());
		}

		if (installment.getAmount() == null || installment.getDueDate() == null || installment.getTerm() == null
				|| installment.getStudent() == null) {
			throw new IllegalArgumentException(
					"Missing or incorrect installment information. Please fill in all required fields.");
		}

		Installment existingInstallment = installmentRepository.findById(installment.getId()).orElse(null);
		if (existingInstallment == null) {
			throw new RuntimeException("Installment not found with id: " + installment.getId());
		}

		existingInstallment.setAmount(installment.getAmount());
		existingInstallment.setDueDate(installment.getDueDate());
		existingInstallment.setTerm(installment.getTerm());
		existingInstallment.setStudent(installment.getStudent());

		return installmentRepository.save(existingInstallment);
	}

	@Transactional
	public void deleteInstallment(Long id) {
		installmentRepository.deleteById(id);
	}
	
	public List<Installment> getPaidInstallments() {
        return installmentRepository.findPaidInstallments();
    }

    public List<Installment> getUnpaidInstallments() {
        return installmentRepository.findUnpaidInstallments();

    }
}
