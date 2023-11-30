package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.dto.UserDto;
import tr.ogu.edu.school.schoolapp.dto.UserLoginDto;
import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.service.AuthenticationService;
import tr.ogu.edu.school.schoolapp.service.UserService;
import tr.ogu.edu.school.schoolapp.util.JwtUtil;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;
	private final AuthenticationService authenticationService;
	private final JwtUtil jwtUtil;

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		try {
			UserDto userDto = userService.getUserById(id);
			return ResponseEntity.ok(userDto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUserDto = userService.createUser(userDto);
		return ResponseEntity.ok(createdUserDto);
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		UserDto updatedUserDto = userService.updateUser(userDto);
		return ResponseEntity.ok(updatedUserDto);
	}

	@DeleteMapping
	public ResponseEntity<Boolean> deleteUser(@RequestBody UserDto userDto) {
		boolean result = userService.deleteUser(userDto.getId());
		return result ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
		User authenticatedUser = authenticationService.authenticateUser(userLoginDto.getMail(),
				userLoginDto.getPassword());
		if (authenticatedUser != null) {
			String token = jwtUtil.generateToken(authenticatedUser);
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
