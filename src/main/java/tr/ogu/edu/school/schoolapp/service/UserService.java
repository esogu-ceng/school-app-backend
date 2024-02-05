package tr.ogu.edu.school.schoolapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationService authenticationService;

	@Transactional
	public User createUser(User user) {
		if (user.getName() == null || user.getName().isEmpty() || user.getSurname() == null
				|| user.getSurname().isEmpty() || user.getMail() == null || user.getMail().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Missing or incorrect user information. Please fill in all fields.");
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user = userRepository.save(user);
		return user;
	}

	@Transactional
	public User updateUser(User user) {
		Long id = user.getId();
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
		existingUser.setName(user.getName());
		existingUser.setSurname(user.getSurname());
		existingUser.setMail(user.getMail());
		if (user.getPassword() != null && !user.getPassword().isBlank()) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			existingUser.setPassword(encodedPassword);
		}
		existingUser = userRepository.save(existingUser);
		return existingUser;
	}

	@Transactional
	public boolean deleteUser(Long id) {
		if (id == null || !userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}
}