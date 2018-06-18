package com.spring.security.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.security.dto.ApiResponse;
import com.spring.security.dto.UserDto;
import com.spring.security.entity.User;
import com.spring.security.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<?>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
		User user = userService.createUser(userDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto) {
		userService.updateUser(userDto);
		return ResponseEntity.ok(new ApiResponse(true, "User updated successfully!"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully!"));
	}

}
