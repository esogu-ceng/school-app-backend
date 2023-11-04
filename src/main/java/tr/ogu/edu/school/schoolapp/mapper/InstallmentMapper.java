package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.InstallmentDto;
import tr.ogu.edu.school.schoolapp.model.Installment;

public class InstallmentMapper {
	private InstallmentMapper() {
	}

	public static InstallmentDto toInstallmentDto(Installment installment) {
		InstallmentDto installmentDto = new InstallmentDto();
		return installmentDto;
	}

	public static Installment fromInstallmentDto(InstallmentDto installmentDto) {
		Installment installment = new Installment();
		return installment;
	}
}
