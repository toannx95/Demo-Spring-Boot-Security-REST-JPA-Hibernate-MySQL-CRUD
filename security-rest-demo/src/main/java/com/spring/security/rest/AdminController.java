package com.spring.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.constant.HttpConstant;
import com.spring.security.dto.GenericResponseDTO;
import com.spring.security.dto.UserDTO;
import com.spring.security.exception.BadRequestException;
import com.spring.security.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericResponseDTO> findAll() {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, userService.findAll()));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseDTO> findOne(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, userService.findOne(id)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GenericResponseDTO> create(@RequestBody UserDTO userDTO) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, userService.create(userDTO)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseDTO> update(@RequestBody UserDTO userDTO) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, userService.updateForAdmin(userDTO)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseDTO> delete(@PathVariable("id") Integer id) {
		try {
			userService.delete(id);
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

}
