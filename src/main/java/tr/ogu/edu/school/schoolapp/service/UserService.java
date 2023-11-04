package tr.ogu.edu.school.schoolapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		if (id == null) {
			return null;
		}
		return userRepository.findById(id).orElse(null);
	}

	public String saveUser(User user) {
		String username = user.getName();
		String password = user.getPassword();
		String plainPassword = password; // Kullanıcının girdiği şifre
		String name = user.getName();
		String surname = user.getSurname();

		if (username == null || username.isEmpty() || password == null || password.isEmpty() || name == null
				|| name.isEmpty() || surname == null || surname.isEmpty()) {
			return "Missing or incorrect user information. Please fill in all fields";
		}

		  // Kullanıcı şifresini hashle
        String hashedPassword = bCryptPasswordEncoder.encode(plainPassword);
        user.setPassword(hashedPassword);
		userRepository.save(user);
		return "User saved";
	}

	public String deleteUser(Long id) {
		if (id == null) {
			return "User id is null";
		}
		userRepository.deleteById(id);
		return "User deleted";
	}
}