package com.s3corp.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.s3corp.dto.AccountDTO;
import com.s3corp.exception.BadRequestException;
import com.s3corp.service.AccountService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			AccountDTO activeAccount = accountService.findByUserNameAndActiveTrue(userName);
			GrantedAuthority authority = new SimpleGrantedAuthority(activeAccount.getRole());
			UserDetails userDetails = (UserDetails) new User(activeAccount.getUserName(), activeAccount.getPassword(),
					Arrays.asList(authority));

			return userDetails;
		} catch (BadRequestException e) {
			e.printStackTrace();
			return null;
		}
	}
}
