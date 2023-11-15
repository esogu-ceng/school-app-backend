package tr.ogu.edu.school.schoolapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TermDto {
	private Long id;
	private String termName;
	private Date startDate;
	private Date endDate;
}
