package tr.ogu.edu.school.schoolapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		tr.ogu.edu.school.schoolapp.model.User user = userRepository.findByMail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return User.withUsername(user.getMail()).password(user.getPassword()).roles("USER").build();
	}
}
