package tr.ogu.edu.school.schoolapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User authenticateUser(String email, String password) {
		User user = userRepository.findByMail(email);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	public User getAuthenticatedUser() {

		// FIXME oturum açan kullanıcı bilgi bir şekilde alınmalı.
		return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

	}
}
