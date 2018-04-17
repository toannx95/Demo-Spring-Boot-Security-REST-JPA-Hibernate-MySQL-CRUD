package com.spring.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.security.dto.RoleDTO;
import com.spring.security.dto.UserDTO;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.enumeration.RoleEnum;
import com.spring.security.exception.BadRequestException;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.RoleService;
import com.spring.security.service.UserService;
import com.spring.security.util.BCryptPassword;
import com.spring.security.util.NumberUtils;
import com.spring.security.util.converter.DAOConverter;
import com.spring.security.util.converter.DTOConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Override
	public List<UserDTO> findAll() throws BadRequestException {
		Iterable<User> users = userRepository.findAll();
		if (users == null) {
			return new ArrayList<>();
		}
		Stream<User> companyStream = StreamSupport.stream(users.spliterator(), false);
		return companyStream.map(DTOConverter::convertUser).collect(Collectors.toList());
	}

	@Override
	public UserDTO findOne(Integer id) throws BadRequestException {
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified Id");
		}

		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + id + " is no exists.");
		}
		return DTOConverter.convertUser(user);
	}

	@Override
	public UserDTO create(UserDTO userDTO) throws BadRequestException {
		User userExists = userRepository.findByUserName(userDTO.getUserName());
		if (!Objects.isNull(userExists)) {
			throw new BadRequestException("Username is already registered!");
		}

		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(BCryptPassword.cryptPasswordEncoder(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.isActive());

		HashSet<RoleDTO> roles = new HashSet<>();
		roles.add(roleService.findByName(RoleEnum.MEMBER.getRole()));
		user.setRoles(roles.stream().map(DAOConverter::convertRole).collect(Collectors.toSet()));

		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public UserDTO updateForAdmin(UserDTO userDTO) throws BadRequestException {
		Integer id = userDTO.getId();
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified Id.");
		}

		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + id + " is no longer exists.");
		}

		user.setUserName(userDTO.getUserName());
		user.setPassword(BCryptPassword.cryptPasswordEncoder(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.isActive());

		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public UserDTO updatePassAndEmail(String userName, String password, String email) throws BadRequestException {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
			throw new BadRequestException("Unidentified UserName or Password or Email.");
		}

		User user = userRepository.findByUserName(userName);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + userName + " is no longer exists.");
		}

		Set<String> roleNames = new HashSet<>();
		user.getRoles().forEach(role -> roleNames.add(role.getName()));
		Role adminRole = DAOConverter.convertRole(roleService.findByName(RoleEnum.ADMIN.getRole()));
		if (roleNames.contains(adminRole.getName())) {
			throw new BadRequestException("Can not update admin user!");
		}

		user.setPassword(BCryptPassword.cryptPasswordEncoder(password));
		user.setEmail(email);
		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public void delete(Integer id) throws BadRequestException {
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified Id");
		}

		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + id + " is no exists.");
		}
		userRepository.delete(id);
	}

	@Override
	public UserDTO findByUserNameAndActiveTrue(String userName) throws BadRequestException {
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName.");
		}

		User user = userRepository.findByUserNameAndActiveTrue(userName);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + userName + " is no exists or no active.");
		}
		return DTOConverter.convertUser(user);
	}

	@Override
	public UserDTO findByUserName(String userName) throws BadRequestException {
		if (userName.isEmpty()) {
			throw new BadRequestException("Unidentified userName.");
		}

		User user = userRepository.findByUserName(userName);
		if (Objects.isNull(user)) {
			throw new BadRequestException("User with " + userName + " is no exists or no active.");
		}
		return DTOConverter.convertUser(user);
	}

}
