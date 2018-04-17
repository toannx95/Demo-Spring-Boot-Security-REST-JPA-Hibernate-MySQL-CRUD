package com.spring.security.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.dto.RoleDTO;
import com.spring.security.entity.Role;
import com.spring.security.exception.BadRequestException;
import com.spring.security.repository.RoleRepository;
import com.spring.security.service.RoleService;
import com.spring.security.util.converter.DAOConverter;
import com.spring.security.util.converter.DTOConverter;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO create(RoleDTO roleDTO) throws BadRequestException {
		return DTOConverter.convertRole(roleRepository.save(DAOConverter.convertRole(roleDTO)));
	}

	@Override
	public RoleDTO findByName(String name) throws BadRequestException {
		if (name.isEmpty()) {
			throw new BadRequestException("Unidentified name");
		}
		
		Role role = roleRepository.findByName(name);
		if (Objects.isNull(role)) {
			throw new BadRequestException("Role with " + name + " is no exists.");
		}
		return DTOConverter.convertRole(role);
	}

}
