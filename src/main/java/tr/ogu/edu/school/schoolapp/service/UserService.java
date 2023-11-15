package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.mapper.UserMapper;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
	}

	public UserDto getUserById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
		return UserMapper.toUserDto(user);
	}

	@Transactional
	public UserDto createUser(UserDto userDto) {
		User user = UserMapper.fromUserDto(userDto);
		user = userRepository.save(user);
		return UserMapper.toUserDto(user);
	}

	@Transactional
	public UserDto updateUser(UserDto userDto) {
		Long id = userDto.getId();
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
		existingUser.setName(userDto.getName());
		existingUser.setSurname(userDto.getSurname());
		existingUser.setMail(userDto.getMail());
		// todo hash password
		existingUser = userRepository.save(existingUser);
		return UserMapper.toUserDto(existingUser);
	}

	public boolean deleteUser(Long id) {
		if (id == null || !userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}
}