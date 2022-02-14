package com.liberty.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.liberty.interview.domain.Vote;
import com.liberty.interview.dto.VoteDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper extends GenericMapper<Vote, VoteDto> {

	@Override
	@Mapping(source = "member.id", target = "idMember")
	@Mapping(source = "userStory.id", target = "idUserStory")
	public VoteDto modelToDto(Vote vote);

}
