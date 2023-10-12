package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.model.User;

public class UserMapper {
	private UserMapper() {
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		return userDto;
	}

	public static User fromUserDto(UserDto userDto) {
		User user = new User();
		return user;
	}
}
