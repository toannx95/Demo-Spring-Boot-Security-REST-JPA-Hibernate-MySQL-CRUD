package com.spring.security.service;

import java.util.List;

import com.spring.security.dto.UserDTO;
import com.spring.security.exception.BadRequestException;

public interface UserService {

	List<UserDTO> findAll() throws BadRequestException;

	UserDTO findOne(Integer id) throws BadRequestException;

	UserDTO create(UserDTO userDTO) throws BadRequestException;

	UserDTO updateForAdmin(UserDTO userDTO) throws BadRequestException;
	
	UserDTO updatePassAndEmail(String userName, String password, String email) throws BadRequestException;

	void delete(Integer id) throws BadRequestException;

	UserDTO findByUserNameAndActiveTrue(String userName) throws BadRequestException;

	UserDTO findByUserName(String userName) throws BadRequestException;

}
