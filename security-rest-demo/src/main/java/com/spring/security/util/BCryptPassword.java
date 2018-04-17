package com.spring.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.security.exception.BadRequestException;

public class BCryptPassword {

	public static String cryptPasswordEncoder(String password) throws BadRequestException {
		if (password.isEmpty()) {
			throw new BadRequestException("Empty password!");
		}
		return new BCryptPasswordEncoder().encode(password);
	}

}
