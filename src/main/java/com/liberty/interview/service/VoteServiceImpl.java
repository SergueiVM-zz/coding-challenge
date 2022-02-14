package com.liberty.interview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberty.interview.domain.Vote;
import com.liberty.interview.dto.VoteDto;
import com.liberty.interview.mapper.VoteMapper;
import com.liberty.interview.repository.VoteRepository;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private UserStoryService userStoryService;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private VoteMapper voteMapper;

	@Override
	public List<VoteDto> findAllVotes(String idSession) {
		sessionService.validateSessionExists(idSession);
		return voteRepository.findAll().stream().map(voteMapper::modelToDto).collect(Collectors.toList());
	}

	@Override
	public VoteDto registerVote(String idSession, VoteDto voteDto) {
		sessionService.validateSessionExists(idSession);
		memberService.validateMemberExists(idSession, voteDto.getIdMember());
		userStoryService.validateUserStoryExists(idSession, voteDto.getIdUserStory());

		Vote vote = voteMapper.dtoToModel(voteDto);
		vote.setMember(memberService.findMemberAsEntity(idSession, voteDto.getIdMember()));
		vote.setUserStory(userStoryService.findUserStoryAsEntity(idSession, voteDto.getIdUserStory()));

		return voteMapper.modelToDto(voteRepository.save(vote));
	}

}
