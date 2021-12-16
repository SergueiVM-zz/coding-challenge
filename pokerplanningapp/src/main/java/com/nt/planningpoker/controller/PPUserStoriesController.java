package com.nt.planningpoker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.planningpoker.model.PokerUserStory;
import com.nt.planningpoker.service.PPUserStoryService;

@RestController
@RequestMapping("/ppUserStory")
public class PPUserStoriesController {
	
	@Autowired
	PPUserStoryService userStoryService;
	
	
	@PostMapping("/createUserStory")
	public ResponseEntity<PokerUserStory> addUserStory(@RequestParam String userStoryId, @RequestParam String description, @RequestParam String sessionId) {
			    try {
			    	PokerUserStory pokerUserStory = new PokerUserStory(userStoryId, description);
			    	PokerUserStory pkrUserStory = userStoryService.addPPUserStory(pokerUserStory, sessionId);
			    	return new ResponseEntity<>(pkrUserStory, HttpStatus.OK);
			    	} catch (Exception e) {
			    			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    	}
		}
	
	@DeleteMapping("/deleteUserStory")
	public void deleteUserStory(@RequestParam String userStoryId) {
		 userStoryService.deletePPUserStory(userStoryId);
		
	}
	
	@GetMapping("/getAllPPUserStories")
//	public ResponseEntity<List<PokerUserStory>> getAllUserStories() {
		public List<PokerUserStory> getAllUserStories() {
		try {
			List<PokerUserStory> userStories = userStoryService.getAllPPStories();
			return userStories;
			/*
			 * if (userStories.isEmpty()) { return new
			 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
			 */
			//return new ResponseEntity<>(userStories, HttpStatus.OK);
		} catch (Exception e) {
			//return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ArrayList<PokerUserStory>();
		}
	}

}
