package com.spring.security.util.converter;

import com.spring.security.dto.RoleDTO;
import com.spring.security.dto.UserDTO;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;

public class DTOConverter {

	public static UserDTO convertUser(User user) {
		return new UserDTO(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.isActive());
	}

	public static RoleDTO convertRole(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

}
