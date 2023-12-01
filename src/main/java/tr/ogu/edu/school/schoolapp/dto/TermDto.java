package tr.ogu.edu.school.schoolapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TermDto {
	private Long id;
	private String termName;
	private Date startDate;
	private Date endDate;
}
