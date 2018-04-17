package com.spring.security.util.converter;

import com.spring.security.dto.RoleDTO;
import com.spring.security.dto.UserDTO;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.util.NumberUtils;

public class DAOConverter {

	public static User convertUser(UserDTO userDTO) {
		User user = new User();
		user.setId(NumberUtils.isEmpty(userDTO.getId()) ? null : userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.isActive());
		return user;
	}

	public static Role convertRole(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(NumberUtils.isEmpty(roleDTO.getId()) ? null : roleDTO.getId());
		role.setName(roleDTO.getName());
		return role;
	}

}
