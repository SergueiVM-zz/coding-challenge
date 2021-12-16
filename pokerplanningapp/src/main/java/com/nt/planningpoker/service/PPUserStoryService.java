package com.nt.planningpoker.service;

import java.util.List;

import com.nt.planningpoker.model.PokerUserStory;


public interface PPUserStoryService {

	
	public PokerUserStory addPPUserStory(PokerUserStory pokerUserStory,String sessionId);
	public void deletePPUserStory(String userStoryId);
	public List<PokerUserStory> getAllPPStories();
	
}
