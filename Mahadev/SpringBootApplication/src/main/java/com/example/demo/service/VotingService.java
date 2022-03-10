package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.VotingRepository;
import com.example.demo.dao.VotingStatusRepository;
import com.example.demo.model.Voting;
import com.example.demo.model.VotingStatus;
import com.example.demo.util.Constants;

@Service
public class VotingService {

	@Autowired
	private VotingRepository votingRepository;

	@Autowired
	private VotingStatusRepository votingStatusRepository;

	public void emitVote(Voting voting) {
		votingRepository.save(voting);
	}

	public VotingStatus findStatusBySessionId(UUID sessionId) {
		return votingStatusRepository.findStatusBySessionId(sessionId);
	}

	public List<Voting> findVotesBySessionId(UUID sessionId) {
		return votingRepository.findVotesBySessionId(sessionId);
	}

	public VotingStatus startVote(UUID sessionId) {
		VotingStatus status = votingStatusRepository.findStatusBySessionId(sessionId);
		if(status !=null) {
			votingStatusRepository.update(Constants.VOTING,sessionId);
		}
		return status;
	}

	public VotingStatus stopVote(UUID sessionId) {
		VotingStatus status = votingStatusRepository.findStatusBySessionId(sessionId);
		if(status !=null) {
			votingStatusRepository.update(Constants.VOTED,sessionId);
		}
		return status;
	}

}
