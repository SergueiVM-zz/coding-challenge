package com.liberty.interview.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.liberty.interview.dto.SessionDto;
import com.liberty.interview.service.SessionService;

@RestController
@RequestMapping("/sessions")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@GetMapping("/{idSession}")
	public ResponseEntity<SessionDto> findById(@PathVariable("idSession") String idSession) {
		return ResponseEntity.ok(sessionService.findSessionById(idSession));
	}

	@GetMapping
	public ResponseEntity<List<SessionDto>> findAll() {
		return ResponseEntity.ok(sessionService.findAllSessions());
	}

	@PostMapping
	public ResponseEntity<SessionDto> create(@Valid @RequestBody SessionDto sessionDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.createSession(sessionDto));
	}

	@DeleteMapping("/{idSession}")
	public ResponseEntity<SessionDto> delete(@PathVariable("idSession") String idSession) {
		return ResponseEntity.ok(sessionService.deleteSessionById(idSession));
	}

}
