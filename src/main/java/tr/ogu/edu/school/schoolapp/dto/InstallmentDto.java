package tr.ogu.edu.school.schoolapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InstallmentDto {
	private Long id;
	private Double amount;
	private Date dueDate;
	private Long termId;
	private Long studentId;
	private Long paymentId;
}
