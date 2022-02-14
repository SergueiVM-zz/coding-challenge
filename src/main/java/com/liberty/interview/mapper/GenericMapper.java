package com.liberty.interview.mapper;

import org.mapstruct.MappingTarget;

import com.liberty.interview.dto.Dto;

public interface GenericMapper<M, D extends Dto> {

	public M dtoToModel(D d);

	public D modelToDto(M m);

	public void dtoToModel(D d, @MappingTarget M m);
}