package tr.ogu.edu.school.schoolapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.dto.UserLoginDto;
import tr.ogu.edu.school.schoolapp.mapper.UserMapper;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.service.AuthenticationService;
import tr.ogu.edu.school.schoolapp.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;
	private final AuthenticationService authenticationService;
//	private final JwtUtil jwtUtil;

	@GetMapping(value = "authenticated")
	public ResponseEntity<UserDto> getAuthenticatedUser() {
		try {
			User user = authenticationService.getAuthenticatedUser();
			UserDto userDto = UserMapper.toUserDto(user);
			return ResponseEntity.ok(userDto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		User user = UserMapper.fromUserDto(userDto);
		User createdUser = userService.createUser(user);
		UserDto createdUserDto = UserMapper.toUserDto(createdUser);
		return ResponseEntity.ok(createdUserDto);
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		User user = UserMapper.fromUserDto(userDto);
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(UserMapper.toUserDto(updatedUser));
	}

	@DeleteMapping
	public ResponseEntity<Boolean> deleteUser(@RequestBody UserDto userDto) {
		boolean result = userService.deleteUser(userDto.getId());
		return result ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
		User authenticatedUser = authenticationService.authenticateUser(userLoginDto.getTckn(),
				userLoginDto.getPassword());
		if (authenticatedUser != null) {
//			String token = jwtUtil.generateToken(authenticatedUser);
//			return ResponseEntity.ok(token);
			return ResponseEntity.ok("User successfully authenticated");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
