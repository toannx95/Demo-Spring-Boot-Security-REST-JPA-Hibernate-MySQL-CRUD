package com.spring.security.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.dto.UserDto;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.enumeration.RoleEnum;
import com.spring.security.exception.AppException;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.UserService;
import com.spring.security.util.NumberUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new NotFoundException("User", "id", id);
		}
		return user;
	}

	@Override
	public User getUser(String username) {
		User user = userRepository.findByUsername(username);
		if (Objects.isNull(user)) {
			throw new NotFoundException("User", "username", username);
		}
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new NotFoundException("User", "id", id);
		}
		userRepository.delete(id);
	}

	@Override
	public User createUser(UserDto userDto) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new BadRequestException("Username is already taken!");
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new BadRequestException("Email already in use!");
		}

		User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role memberRole = roleRepository.findByName(RoleEnum.MEMBER.getRole());
		if (Objects.isNull(memberRole)) {
			throw new AppException("Member Role not set.");
		}
		user.setRoles(new HashSet<>(Arrays.asList(memberRole)));

		return userRepository.save(user);
	}

	@Override
	public User updateUser(UserDto userDto) {
		Long id = userDto.getId();

		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentify Id!");
		}

		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new NotFoundException("User", "id", id);
		}

		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());

		return userRepository.save(user);
	}

}
