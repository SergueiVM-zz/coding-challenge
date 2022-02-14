package com.liberty.interview.service;

import java.util.List;
import java.util.Optional;

import com.liberty.interview.domain.Member;
import com.liberty.interview.dto.MemberDto;

public interface MemberService {

	List<MemberDto> findMembersFromSession(String idSession);

	Member findMemberAsEntity(String idSession, String idMember);

	MemberDto joinSession(String idSession, MemberDto memberDto);

	Optional<MemberDto> logout(String idSession, String idMember);

	void validateMemberExists(String idSession, String idMember);

}
