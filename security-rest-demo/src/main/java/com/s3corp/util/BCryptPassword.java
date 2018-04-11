package com.s3corp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.s3corp.exception.BadRequestException;

public class BCryptPassword {

	public static String cryptPasswordEncoder(String password) throws BadRequestException {
		if (password.isEmpty()) {
			throw new BadRequestException("Empty password!");
		}
		return new BCryptPasswordEncoder().encode(password);
	}

}
