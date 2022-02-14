package com.liberty.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberty.interview.domain.Member;
import com.liberty.interview.dto.MemberDto;
import com.liberty.interview.exception.NotFoundException;
import com.liberty.interview.mapper.MemberMapper;
import com.liberty.interview.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<MemberDto> findMembersFromSession(String idSession) {
		return memberRepository.findByIdSession(idSession).stream().map(memberMapper::modelToDto)
				.collect(Collectors.toList());
	}

	@Override
	public Member findMemberAsEntity(String idSession, String idMember) {
		Optional<Member> memberOpt = memberRepository.findMember(idSession, idMember);
		if (!memberOpt.isPresent()) {
			throw new NotFoundException(
					String.format("The member with id '%s' belonging to the session with id '%s' doesn't exist",
							idMember, idSession));
		}
		return memberOpt.get();
	}

	@Override
	public MemberDto joinSession(String idSession, MemberDto memberDto) {
		sessionService.validateSessionExists(idSession);

		Member member = memberMapper.dtoToModel(memberDto);
		member.setSession(sessionService.findSessionByIdAsEntity(idSession));
		return memberMapper.modelToDto(memberRepository.save(member));
	}

	@Override
	public Optional<MemberDto> logout(String idSession, String idMember) {
		Optional<Member> memberOpt = memberRepository.findMember(idSession, idMember);

		if (memberOpt.isEmpty()) {
			return Optional.empty();
		}
		Member member = memberOpt.get();
		memberRepository.delete(member);

		return Optional.of(memberMapper.modelToDto(member));
	}

	@Override
	public void validateMemberExists(String idSession, String idMember) {
		Optional<Member> member = memberRepository.findMember(idSession, idMember);
		if (!member.isPresent()) {
			throw new NotFoundException(
					String.format("The member with id '%s' belonging to the session with id '%s' doesn't exist",
							idMember, idSession));
		}
	}

}
