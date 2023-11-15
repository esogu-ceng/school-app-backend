package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;

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
import tr.ogu.edu.school.schoolapp.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

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

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		UserDto updatedUserDto = userService.updateUser(id, userDto);
		return ResponseEntity.ok(updatedUserDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
		boolean result = userService.deleteUser(id);
		return result ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}
}
