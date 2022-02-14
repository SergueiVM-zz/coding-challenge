package com.liberty.interview.service;

import java.util.List;

import com.liberty.interview.dto.VoteDto;

public interface VoteService {

	List<VoteDto> findAllVotes(String idSession);

	VoteDto registerVote(String idSession, VoteDto voteDto);

}
