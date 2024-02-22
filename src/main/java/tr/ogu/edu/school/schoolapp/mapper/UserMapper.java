package tr.ogu.edu.school.schoolapp.mapper;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import tr.ogu.edu.school.schoolapp.dto.StudentDto;
import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.model.User;

public class UserMapper {
	private UserMapper() {
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setSurname(user.getSurname());
		userDto.setMail(user.getMail());
		userDto.setPassword(user.getPassword());
		userDto.setTckn(user.getTckn());
		Set<StudentDto> studentDtos = user.getStudents().stream().map(StudentMapper::toStudentDto)
				.collect(Collectors.toSet());
		userDto.setStudents(studentDtos);
		return userDto;
	}

	public static User fromUserDto(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setSurname(userDto.getSurname());
		user.setMail(userDto.getMail());
		user.setPassword(userDto.getPassword());
		user.setTckn(userDto.getTckn());
		if (userDto.getStudents() != null && !userDto.getStudents().isEmpty()) {
			user.setStudents(
					userDto.getStudents().stream().map(StudentMapper::fromStudentDto).collect(Collectors.toList()));
		} else {// Eğer students boşsa boş set oluşturulur.
			user.setStudents(new ArrayList<>());
		}
		return user;
	}
}
