package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.InstallmentDto;
import tr.ogu.edu.school.schoolapp.mapper.InstallmentMapper;
import tr.ogu.edu.school.schoolapp.model.Installment;
import tr.ogu.edu.school.schoolapp.repository.InstallmentRepository;

@Service
@RequiredArgsConstructor
public class InstallmentService {

	private final InstallmentRepository installmentRepository;

	// FIXME: Bu metod, Spring Security entegrasyonu tamamlandığında, oturum açan
	// kullanıcının gerçek kimliği ile güncellenmeli.
	public List<InstallmentDto> getInstallmentsByUserId(Long userId) {
		// Şu anda geçici olarak 'userId' sabit bir değer olarak kullanılan bu kısım,
		// oturum açan kullanıcının kimliğiyle değiştirilmeli.
		return installmentRepository.findInstallmentsByUserId(userId).stream().map(InstallmentMapper::toInstallmentDto)
				.collect(Collectors.toList());
	}

	public InstallmentDto createInstallment(InstallmentDto installmentDto) {
		Installment installment = InstallmentMapper.fromInstallmentDto(installmentDto);
		Installment savedInstallment = installmentRepository.save(installment);
		return InstallmentMapper.toInstallmentDto(savedInstallment);
	}

	public InstallmentDto updateInstallment(InstallmentDto installmentDto) {
		Long id = installmentDto.getId();
		if (!installmentRepository.existsById(id)) {
			throw new RuntimeException("Installment not found with id: " + id);
		}
		Installment installment = InstallmentMapper.fromInstallmentDto(installmentDto);
		installment.setId(id);
		Installment updatedInstallment = installmentRepository.save(installment);
		return InstallmentMapper.toInstallmentDto(updatedInstallment);
	}

	public void deleteInstallment(Long id) {
		installmentRepository.deleteById(id);
	}
}
