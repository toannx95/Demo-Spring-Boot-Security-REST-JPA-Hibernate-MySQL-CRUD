package com.s3corp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.s3corp.dto.AccountDTO;
import com.s3corp.exception.BadRequestException;

@Service
public interface AccountService {

	List<AccountDTO> findAll() throws BadRequestException;

	AccountDTO findOne(String userName) throws BadRequestException;

	AccountDTO create(AccountDTO accountDTO) throws BadRequestException;

	AccountDTO update(AccountDTO accountDTO) throws BadRequestException;

	void delete(String userName) throws BadRequestException;

	AccountDTO findByUserNameAndActiveTrue(String userName) throws BadRequestException;

}
