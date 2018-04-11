package com.s3corp.util.converter;

import com.s3corp.dto.AccountDTO;
import com.s3corp.dto.LayoutDTO;
import com.s3corp.entity.Account;
import com.s3corp.entity.Layout;
import com.s3corp.exception.BadRequestException;
import com.s3corp.util.BCryptPassword;

public class DAOConverter {

	public static Account convertAccount(AccountDTO accountDTO) throws BadRequestException {
		Account account = new Account();
		account.setUserName(accountDTO.getUserName());
		account.setPassword(BCryptPassword.cryptPasswordEncoder(accountDTO.getPassword()));
		account.setRole(accountDTO.getRole());
		account.setEmail(accountDTO.getEmail());
		account.setName(accountDTO.getName());
		account.setActive(accountDTO.isActive());
		return account;
	}

	public static Layout convertLayout(LayoutDTO layoutDTO) {
		Layout layout = new Layout();
		layout.setId(layoutDTO.getId());
		layout.setLayoutName(layoutDTO.getLayoutName());
		return layout;
	}

}
