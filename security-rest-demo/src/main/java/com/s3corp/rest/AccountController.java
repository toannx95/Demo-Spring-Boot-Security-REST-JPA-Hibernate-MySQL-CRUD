package com.s3corp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.s3corp.constant.HttpConstant;
import com.s3corp.dto.AccountDTO;
import com.s3corp.dto.GenericResponseDTO;
import com.s3corp.exception.BadRequestException;
import com.s3corp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseDTO> update(@RequestBody AccountDTO accountDTO) {
		try {
			return ResponseEntity
					.ok(new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, accountService.update(accountDTO)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(new GenericResponseDTO(e.getMessage()));
		}
	}

}
