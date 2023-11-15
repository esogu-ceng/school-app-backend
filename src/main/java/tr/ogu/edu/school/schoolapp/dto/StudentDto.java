package tr.ogu.edu.school.schoolapp.dto;

import lombok.Data;

@Data
public class StudentDto {
	private Long id;
	private String name;
	private String surname;
	private Integer grade;
}
