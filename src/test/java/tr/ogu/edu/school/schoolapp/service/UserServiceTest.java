package tr.ogu.edu.school.schoolapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.repository.UserRepository;

class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userRepository, passwordEncoder);
	}

	@Test
	void whenCreateUser_thenShouldReturnSavedUser() {
		UserDto userDtoToSave = new UserDto(null, "Test", "User", "test@mail.com", "pass123");
		User savedUser = new User("Test", "User", "test@mail.com", "hashedpass", new HashSet<>());
		when(userRepository.save(any(User.class))).thenReturn(savedUser);
		when(passwordEncoder.encode("pass123")).thenReturn("hashedpass");

		UserDto actual = userService.createUser(userDtoToSave);

		assertNotNull(actual);
		assertEquals(userDtoToSave.getMail(), actual.getMail());
	}

	@Test
	void whenRetrievingAllUsers_thenShouldReturnListOfUsers() {
		List<User> users = Arrays.asList(new User("Test1", "User1", "test1@mail.com", "pass1", new HashSet<>()),
				new User("Test2", "User2", "test2@mail.com", "pass2", new HashSet<>()));
		when(userRepository.findAll()).thenReturn(users);

		List<UserDto> userDtos = userService.getAllUsers();

		assertNotNull(userDtos);
		assertEquals(2, userDtos.size());
	}

	@Test
	void givenUserId_whenRetrievingUser_thenShouldReturnUser() {
		Long userId = 1L;
		User user = new User("Test", "User", "test@mail.com", "pass123", new HashSet<>());
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		UserDto actual = userService.getUserById(userId);

		assertNotNull(actual);
		assertEquals("test@mail.com", actual.getMail());
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

	@Test
	void givenNonExistentUserId_whenRetrievingUser_thenShouldReturnNull() {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> userService.getUserById(userId));
	}

}
