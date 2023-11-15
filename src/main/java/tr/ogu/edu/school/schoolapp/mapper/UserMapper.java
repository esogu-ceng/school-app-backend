package tr.ogu.edu.school.schoolapp.mapper;

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
		return userDto;
	}

	public static User fromUserDto(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setSurname(userDto.getSurname());
		user.setMail(userDto.getMail());
		return user;
	}
}
