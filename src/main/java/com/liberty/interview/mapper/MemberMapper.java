package com.liberty.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.liberty.interview.domain.Member;
import com.liberty.interview.dto.MemberDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper extends GenericMapper<Member, MemberDto> {

	@Override
	@Mapping(source = "id", target = "idMember")
	MemberDto modelToDto(Member member);

	@Override
	@Mapping(source = "idMember", target = "id")
	Member dtoToModel(MemberDto memberDto);

}
