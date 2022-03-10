package com.example.demo.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Voting;
import com.example.demo.model.VotingStatus;
import com.example.demo.service.VotingService;
import com.example.demo.util.Constants;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Voting")
public class VotingController  {

	@Autowired
	private VotingService votingService;

	@GetMapping("/startVote/{sessionId}")
	public ResponseEntity<String> startVote(@PathVariable UUID sessionId) {
		if(votingService.startVote(sessionId)!=null) {
			return new ResponseEntity<>("Voting has started",HttpStatus.OK);
		}
		return new ResponseEntity<>("Session doesn't exist", HttpStatus.OK);
	}

	@GetMapping("/stopVote/{sessionId}")
	public ResponseEntity<String> stopVote(@PathVariable UUID sessionId) {
		if(votingService.stopVote(sessionId)!=null) {
			return new ResponseEntity<>("Voting has stopped",HttpStatus.OK);
		}
		return new ResponseEntity<>("Session doesn't exist", HttpStatus.OK);
	}

	@PostMapping("/sessions/{sessionId}/voting")
	public ResponseEntity<Object> emitVote(@PathVariable UUID sessionId, @RequestBody Voting voting) {
		VotingStatus votingStatus = votingService.findStatusBySessionId(sessionId);
		if (votingStatus!=null && votingStatus.getStatus().equals(Constants.VOTING)) {
			voting.setSessionId(sessionId);
			votingService.emitVote(voting);
			return new ResponseEntity<>(voting, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/sessions/{sessionId}/voting")
	public ResponseEntity<List<Voting>> findVotesBySession(@PathVariable UUID sessionId) {
		VotingStatus votingStatus = votingService.findStatusBySessionId(sessionId);
		if (votingStatus!=null && votingStatus.getStatus().equals(Constants.VOTED)) {
			var votes = votingService.findVotesBySessionId(sessionId);
			return new ResponseEntity<>(votes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
