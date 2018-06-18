package com.spring.security.service;

import java.util.List;

import com.spring.security.dto.UserDto;
import com.spring.security.entity.User;

public interface UserService {

	List<User> getAllUsers();

	User getUser(Long id);

	User getUser(String username);

	User createUser(UserDto userDto);

	User updateUser(UserDto userDto);

	void deleteUser(Long id);

}
