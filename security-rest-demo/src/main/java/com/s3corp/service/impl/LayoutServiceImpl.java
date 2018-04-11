package com.s3corp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3corp.dto.LayoutDTO;
import com.s3corp.entity.Layout;
import com.s3corp.exception.BadRequestException;
import com.s3corp.repository.LayoutRepository;
import com.s3corp.service.LayoutService;
import com.s3corp.util.NumberUtils;
import com.s3corp.util.converter.DAOConverter;
import com.s3corp.util.converter.DTOConverter;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private LayoutRepository layoutRepository;

	@Override
	public List<LayoutDTO> findAll() throws BadRequestException {
		List<Layout> layouts = layoutRepository.findAll();
		if (layouts.isEmpty()) {
			return new ArrayList<>();
		}
		return layouts.stream().map(layout -> DTOConverter.convertLayout(layout)).collect(Collectors.toList());
	}

	@Override
	public LayoutDTO findOne(Integer id) throws BadRequestException {
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(id);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + id + " is no exists.");
		}
		return DTOConverter.convertLayout(layout);
	}

	@Override
	public LayoutDTO create(LayoutDTO layoutDTO) throws BadRequestException {
		return DTOConverter.convertLayout(layoutRepository.save(DAOConverter.convertLayout(layoutDTO)));
	}

	@Override
	public LayoutDTO update(LayoutDTO layoutDTO) throws BadRequestException {
		Integer id = layoutDTO.getId();
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(id);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + id + " is no longer exists.");
		}

		layout.setId(id);
		layout.setLayoutName(layoutDTO.getLayoutName());
		return DTOConverter.convertLayout(layoutRepository.save(layout));
	}

	@Override
	public void delete(Integer id) throws BadRequestException {
		if (NumberUtils.isEmpty(id)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(id);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + id + " is no exists.");
		}
		layoutRepository.delete(id);
	}

}
