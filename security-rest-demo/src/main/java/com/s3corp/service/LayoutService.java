package com.s3corp.service;

import java.util.List;

import com.s3corp.dto.LayoutDTO;
import com.s3corp.exception.BadRequestException;

public interface LayoutService {

	List<LayoutDTO> findAll() throws BadRequestException;

	LayoutDTO findOne(Integer id) throws BadRequestException;

	LayoutDTO create(LayoutDTO layoutDTO) throws BadRequestException;

	LayoutDTO update(LayoutDTO layoutDTO) throws BadRequestException;

	void delete(Integer id) throws BadRequestException;

}
