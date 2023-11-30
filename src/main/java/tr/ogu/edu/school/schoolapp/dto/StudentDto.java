package tr.ogu.edu.school.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
	private Long id;
	private String name;
	private String surname;
	private Integer grade;
}
