package tr.ogu.edu.school.schoolapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		tr.ogu.edu.school.schoolapp.model.User user = userRepository.findByTckn(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return User.withUsername(user.getTckn()).password(user.getPassword()).roles("USER").build();
	}
}
