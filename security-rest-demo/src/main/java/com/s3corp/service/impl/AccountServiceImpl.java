package com.s3corp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3corp.dto.AccountDTO;
import com.s3corp.entity.Account;
import com.s3corp.exception.BadRequestException;
import com.s3corp.repository.AccountRepository;
import com.s3corp.service.AccountService;
import com.s3corp.util.BCryptPassword;
import com.s3corp.util.converter.DAOConverter;
import com.s3corp.util.converter.DTOConverter;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountDTO> findAll() throws BadRequestException {
		Iterable<Account> accounts = accountRepository.findAll();
		if (accounts == null) {
			return new ArrayList<>();
		}
		Stream<Account> companyStream = StreamSupport.stream(accounts.spliterator(), false);
		return companyStream.map(DTOConverter::convertAccount).collect(Collectors.toList());
	}

	@Override
	public AccountDTO findOne(String userName) throws BadRequestException {
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName");
		}

		Account account = accountRepository.findOne(userName);
		if (Objects.isNull(account)) {
			throw new BadRequestException("Account with " + userName + " is no exists.");
		}

		return DTOConverter.convertAccount(account);
	}

	@Override
	public AccountDTO create(AccountDTO accountDTO) throws BadRequestException {
		return DTOConverter.convertAccount(accountRepository.save(DAOConverter.convertAccount(accountDTO)));
	}

	@Override
	public AccountDTO update(AccountDTO accountDTO) throws BadRequestException {
		String userName = accountDTO.getUserName();
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName");
		}

		Account account = accountRepository.findOne(userName);
		if (Objects.isNull(account)) {
			throw new BadRequestException("Account with " + userName + " is no longer exists.");
		}

		if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
			throw new BadRequestException("Can not update admin account.");
		}

		account.setUserName(userName);
		account.setPassword(BCryptPassword.cryptPasswordEncoder(accountDTO.getPassword()));
		account.setRole(accountDTO.getRole());
		account.setEmail(accountDTO.getEmail());
		account.setName(accountDTO.getName());
		account.setActive(accountDTO.isActive());
		return DTOConverter.convertAccount(accountRepository.save(account));
	}

	@Override
	public void delete(String userName) throws BadRequestException {
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName");
		}

		Account account = accountRepository.findOne(userName);
		if (Objects.isNull(account)) {
			throw new BadRequestException("Account with " + userName + " is no exists.");
		}
		accountRepository.delete(userName);
	}

	@Override
	public AccountDTO findByUserNameAndActiveTrue(String userName) throws BadRequestException {
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName");
		}

		Account account = accountRepository.findByUserNameAndActiveTrue(userName);
		if (Objects.isNull(account)) {
			throw new BadRequestException("Account with " + userName + " is no exists or no active.");
		}
		return DTOConverter.convertAccount(account);
	}

}
