package com.liberty.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.liberty.interview.domain.Session;
import com.liberty.interview.dto.SessionDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper extends GenericMapper<Session, SessionDto> {

	@Override
	@Mapping(source = "id", target = "idSession")
	public SessionDto modelToDto(Session plClassification);

	@Override
	@Mapping(source = "idSession", target = "id")
	public Session dtoToModel(SessionDto sessionDto);

}
