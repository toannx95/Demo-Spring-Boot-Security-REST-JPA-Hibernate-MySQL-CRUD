package com.s3corp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.s3corp.constant.HttpConstant;
import com.s3corp.dto.GenericResponseDTO;
import com.s3corp.dto.LayoutDTO;
import com.s3corp.exception.BadRequestException;
import com.s3corp.service.LayoutService;

@RestController
@RequestMapping("/layout")
public class LayoutController {

	@Autowired
	private LayoutService layoutService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GenericResponseDTO> findAll() {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, layoutService.findAll()));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseDTO> findOne(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, layoutService.findOne(id)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GenericResponseDTO> create(@RequestBody LayoutDTO layoutDTO) {
		try {
			return ResponseEntity
					.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, layoutService.create(layoutDTO)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseDTO> update(@RequestBody LayoutDTO layoutDTO) {
		try {
			return ResponseEntity
					.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, layoutService.update(layoutDTO)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseDTO> delete(@PathVariable("id") Integer id) {
		try {
			layoutService.delete(id);
			return ResponseEntity.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

}
