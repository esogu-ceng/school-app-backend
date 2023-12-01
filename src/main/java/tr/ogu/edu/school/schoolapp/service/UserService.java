package tr.ogu.edu.school.schoolapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	private final PasswordEncoder passwordEncoder;

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
    if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPlainPassword() == null || user.getPlainPassword().isEmpty() || user.getName() == null
				|| user.getName().isEmpty() || user.getSurname() == null || user.getSurname().isEmpty()) {
			return "Missing or incorrect user information. Please fill in all fields";
		}
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		user.setPassword(encodedPassword);
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
		if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
			String encodedPassword = passwordEncoder.encode(userDto.getPassword());
			existingUser.setPassword(encodedPassword);
		}
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