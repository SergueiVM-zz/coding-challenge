package com.example.demo.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Member;
import com.example.demo.model.PlanningSession;
import com.example.demo.model.Session;
import com.example.demo.service.SessionManagementService;
import com.example.demo.util.SpringConverter;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "SessionManagement", description = "Poker planning session")
public class SessionManagementController  {

	@Autowired
	private SessionManagementService sessionManagementService;

	@PostMapping("/sessions")
	public ResponseEntity<Session> createSession(@RequestBody PlanningSession planningSession) {
		sessionManagementService.createSession(planningSession);
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		    		.buildAndExpand(planningSession.getSessionId()).toUri();
	    Session session = SpringConverter.INSTANCE.planningSessionToSession(planningSession);
	    session.setLocation(location);
		return new ResponseEntity<>(session, HttpStatus.CREATED);
	}
	
	@GetMapping("/sessions")
	public ResponseEntity<List<PlanningSession>> fetchSessions() {
		var sessionList = sessionManagementService.fetchSessions();
		return new ResponseEntity<>(sessionList, HttpStatus.OK);
	}

	@GetMapping("/sessions/{sessionId}")
	public ResponseEntity<Session> findSessionById(@PathVariable UUID sessionId) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			    		.buildAndExpand(sessionId).toUri();
		    Session session = SpringConverter.INSTANCE.planningSessionToSession(sessionObject.get());
		    session.setLocation(location);
			return new ResponseEntity<>(session, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/sessions/{sessionId}")
	public ResponseEntity<String> deleteSessionById(@PathVariable UUID sessionId) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			sessionManagementService.deleteSessionById(sessionObject.get());
			return new ResponseEntity<>("Session deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sessions/{sessionId}/members")
	public ResponseEntity<Member> createMembers(@PathVariable UUID sessionId, @RequestBody Member member) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			member.setSession(sessionObject.get());
			sessionManagementService.createMember(member);
			return new ResponseEntity<>(member, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/sessions/{sessionId}/members")
	public ResponseEntity<List<Member>> findMembersBySessionId(@PathVariable UUID sessionId) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			var members = sessionManagementService.findMembersBySessionId(sessionObject.get());
			return new ResponseEntity<>(members, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/sessions/{sessionId}/member/{memberId}")
	public ResponseEntity<Object> findMemberBySessionId(@PathVariable UUID sessionId, @PathVariable int memberId ) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject!=null & sessionObject.isPresent()) {
			Member member = sessionManagementService.findById(memberId);
			return new ResponseEntity<>(member, HttpStatus.OK);
		}
		return new ResponseEntity<>("Session doesn't exist", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/sessions/{sessionId}/member/{memberId}")
	public ResponseEntity<String> deleteMemberBySessionId(@PathVariable UUID sessionId, @PathVariable int memberId ) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			sessionManagementService.deleteById(memberId);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Session doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
}
