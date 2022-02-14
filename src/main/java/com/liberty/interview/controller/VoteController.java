package com.liberty.interview.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberty.interview.dto.VoteDto;
import com.liberty.interview.service.VoteService;

@RestController
@RequestMapping("/sessions")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@GetMapping("/{idSession}/votes")
	public ResponseEntity<List<VoteDto>> findAll(@PathVariable("idSession") String idSession) {
		return ResponseEntity.ok(voteService.findAllVotes(idSession));
	}

	@PostMapping("/{idSession}/votes")
	public ResponseEntity<VoteDto> create(@PathVariable("idSession") String idSession,
			@Valid @RequestBody VoteDto voteDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(voteService.registerVote(idSession, voteDto));
	}

}
