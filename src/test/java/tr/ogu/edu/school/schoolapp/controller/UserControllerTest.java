package tr.ogu.edu.school.schoolapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

import tr.ogu.edu.school.schoolapp.dto.UserLoginDto;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.service.AuthenticationService;
import tr.ogu.edu.school.schoolapp.service.UserService;
import tr.ogu.edu.school.schoolapp.util.JwtUtil;

public class UserControllerTest {

	@Mock
	private UserService userService;
	@Mock
	private AuthenticationService authenticationService;
	@Mock
	private JwtUtil jwtUtil;

	@Spy
	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenLoginWithValidCredentials_thenShouldReturnToken() {
		UserLoginDto userLoginDto = new UserLoginDto("11111111111", "pass123");
		User authenticatedUser = new User(null, "11111111111", "12345678911", "Testname", "Testsurname", "pass123",
				null, null, null);
		String token = "exampleToken";

		given(authenticationService.authenticateUser(userLoginDto.getTckn(), userLoginDto.getPassword()))
				.willReturn(authenticatedUser);
		given(jwtUtil.generateToken(authenticatedUser)).willReturn(token);

		ResponseEntity<String> response = userController.login(userLoginDto);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		// assertThat(response.getBody()).isEqualTo(token);
		// verify(authenticationService).authenticateUser(userLoginDto.getMail(),
		// userLoginDto.getPassword());
		// verify(jwtUtil).generateToken(authenticatedUser);
	}

	@Test
	void whenLoginWithInvalidCredentials_thenShouldReturnUnauthorized() {
		UserLoginDto userLoginDto = new UserLoginDto("11111111111", "wrongpassword");

		given(authenticationService.authenticateUser(userLoginDto.getTckn(), userLoginDto.getPassword()))
				.willReturn(null);

		ResponseEntity<String> response = userController.login(userLoginDto);

		assertThat(response.getStatusCodeValue()).isEqualTo(401);
		assertThat(response.getBody()).isNull();
		verify(authenticationService).authenticateUser(userLoginDto.getTckn(), userLoginDto.getPassword());
	}
}
