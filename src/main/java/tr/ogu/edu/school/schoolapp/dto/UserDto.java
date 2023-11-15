package tr.ogu.edu.school.schoolapp.dto;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String name;
	private String surname;
	private String mail;
}
