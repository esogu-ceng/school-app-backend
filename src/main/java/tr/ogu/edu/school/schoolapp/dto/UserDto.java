package tr.ogu.edu.school.schoolapp.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	private Long id;
	private String name;
	private String surname;
	private String mail;
	private String password;
	private String tckn;
	private Set<StudentDto> students;
}
