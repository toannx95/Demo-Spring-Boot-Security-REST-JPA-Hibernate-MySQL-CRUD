package com.spring.security.service;

import com.spring.security.dto.RoleDTO;
import com.spring.security.exception.BadRequestException;

public interface RoleService {

	RoleDTO create(RoleDTO roleDTO) throws BadRequestException;

	RoleDTO findByName(String name) throws BadRequestException;

}
