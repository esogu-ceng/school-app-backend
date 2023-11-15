package tr.ogu.edu.school.schoolapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDto {
	private Long id;
	private Double amount;
	private Date paymentDate;
}
