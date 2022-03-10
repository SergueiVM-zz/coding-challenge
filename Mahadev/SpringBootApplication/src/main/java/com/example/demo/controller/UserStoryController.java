package com.example.demo.controller;


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

import com.example.demo.model.PlanningSession;
import com.example.demo.model.UserStory;
import com.example.demo.service.SessionManagementService;
import com.example.demo.service.UserStoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/userstory")
@Tag(name = "UserStory")
public class UserStoryController  {

	@Autowired
	private UserStoryService userStoryService;

	@Autowired
	private SessionManagementService sessionManagementService;

	@GetMapping("/sessions/{sessionId}")
	public ResponseEntity<List<UserStory>> fetchUserStories(@PathVariable UUID sessionId) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			var userStories = userStoryService.fetchUserStories();
			return new ResponseEntity<>(userStories, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sessions/{sessionId}")
	public ResponseEntity<UserStory> createUserStory(@PathVariable UUID sessionId, @RequestBody UserStory userStory) {
		Optional<PlanningSession> sessionObject = sessionManagementService.findSessionById(sessionId);
		if (sessionObject.isPresent()) {
			userStory.setSessionId(sessionId);
			UserStory userStoryObject = userStoryService.createUserStory(userStory);
			return new ResponseEntity<>(userStoryObject, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/sessions/{userStoryId}")
	public ResponseEntity<String> createUserStory(@PathVariable int userStoryId) {
		userStoryService.deleteUserStory(userStoryId);
		return new ResponseEntity<>("Userstory deleted", HttpStatus.OK);
	}
}
