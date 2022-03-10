package com.example.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Voting;

public interface VotingRepository extends JpaRepository<Voting, Integer> {

	public List<Voting> findVotesBySessionId(UUID sessionId); 
	
}

