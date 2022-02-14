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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberty.interview.dto.UserStoryDto;
import com.liberty.interview.service.UserStoryService;

@RestController
@RequestMapping("/sessions")
public class UserStoryController {

	@Autowired
	private UserStoryService userStoryService;

	@GetMapping("/{idSession}/stories")
	public ResponseEntity<List<UserStoryDto>> findAll(@PathVariable("idSession") String idSession) {
		return ResponseEntity.ok(userStoryService.findAllStories(idSession));
	}

	@PostMapping("/{idSession}/stories")
	public ResponseEntity<UserStoryDto> create(@PathVariable("idSession") String idSession,
			@Valid @RequestBody UserStoryDto userStoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userStoryService.createStory(idSession, userStoryDto));
	}

	@PutMapping("/{idSession}/stories/{idUserStory}")
	public ResponseEntity<UserStoryDto> update(@PathVariable("idSession") String idSession,
			@PathVariable("idUserStory") String idUserStory, @Valid @RequestBody UserStoryDto userStoryDto) {
		return ResponseEntity.ok(userStoryService.updateStory(idSession, idUserStory, userStoryDto));
	}
	
	@DeleteMapping("/{idSession}/stories/{idUserStory}")
	public ResponseEntity<UserStoryDto> delete(@PathVariable("idSession") String idSession,
			@PathVariable("idUserStory") String idUserStory){
		return ResponseEntity.ok(userStoryService.deleteStory(idSession, idUserStory));
	}

}
