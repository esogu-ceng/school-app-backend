package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.InstallmentDto;
import tr.ogu.edu.school.schoolapp.model.Installment;

public class InstallmentMapper {
	private InstallmentMapper() {
	}

	public static InstallmentDto toInstallmentDto(Installment installment) {
		InstallmentDto dto = new InstallmentDto();
		dto.setId(installment.getId());
		dto.setAmount(installment.getAmount());
		dto.setDueDate(installment.getDueDate());
		return dto;
	}

	public static Installment fromInstallmentDto(InstallmentDto installmentDto) {
		Installment installment = new Installment();
		installment.setId(installmentDto.getId());
		installment.setAmount(installmentDto.getAmount());
		installment.setDueDate(installmentDto.getDueDate());
		return installment;
	}
}
