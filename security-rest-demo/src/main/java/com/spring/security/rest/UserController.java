package com.spring.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.constant.HttpConstant;
import com.spring.security.dto.GenericResponseDTO;
import com.spring.security.exception.BadRequestException;
import com.spring.security.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseDTO> update(@RequestHeader("username") String userName,
			@RequestHeader("password") String password, @RequestHeader("email") String email) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS,
					userService.updatePassAndEmail(userName, password, email)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

}
