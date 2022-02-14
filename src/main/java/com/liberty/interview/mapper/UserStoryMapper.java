package com.liberty.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.liberty.interview.domain.UserStory;
import com.liberty.interview.dto.UserStoryDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserStoryMapper extends GenericMapper<UserStory, UserStoryDto> {

	@Override
	@Mapping(source = "id", target = "idUserStory")
	public UserStoryDto modelToDto(UserStory plClassification);

	@Override
	@Mapping(source = "idUserStory", target = "id")
	public UserStory dtoToModel(UserStoryDto userStoryDto);

}
