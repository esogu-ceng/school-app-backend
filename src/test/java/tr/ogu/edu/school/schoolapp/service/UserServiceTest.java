package tr.ogu.edu.school.schoolapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private AuthenticationService authenticationService;

	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userRepository, passwordEncoder, authenticationService);
	}

	@Test
	void whenCreateUser_thenShouldReturnSavedUser() {
		User userToSave = new User(null, "test@mail.com", "12345678911", "Testname", "Testsurname", "pass123", null,
				null, null);
		User savedUser = new User(null, "test@mail.com", "12345678911", "Testname", "Testsurname", "hashedpass", null,
				null, null);
		when(userRepository.save(any(User.class))).thenReturn(savedUser);
		when(passwordEncoder.encode("pass123")).thenReturn("hashedpass");

		User actual = userService.createUser(userToSave);

		assertNotNull(actual);
		assertEquals(userToSave.getMail(), actual.getMail());
	}

	@Test
	void givenUserId_whenDeletingUser_thenShouldPerformDeletion() {
		Long userId = 1L;
		when(userRepository.existsById(userId)).thenReturn(true);
		doNothing().when(userRepository).deleteById(userId);

		boolean result = userService.deleteUser(userId);

		assertTrue(result);
		verify(userRepository).deleteById(userId);
	}

}
