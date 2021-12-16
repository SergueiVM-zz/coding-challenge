package com.nt.planningpoker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.planningpoker.model.PokerUserStory;
import com.nt.planningpoker.repo.SessionRepository;
import com.nt.planningpoker.repo.UserStoryRepository;
import com.nt.planningpoker.service.PPUserStoryService;
import com.nt.planningpoker.model.PokerSession;

@Component
public class PPUserStoryServiceImpl implements PPUserStoryService {
	
	@Autowired
	UserStoryRepository userStoryRepo;
	
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public PokerUserStory addPPUserStory(PokerUserStory pokerUserStory, String sessionId) {
		// TODO Auto-generated method stub
		PokerSession pokerSession = sessionRepository.getById(sessionId);
		//pokerUserStory.setVotingStatus("PENDING)";
		pokerUserStory.setPokerSession(pokerSession);
		return userStoryRepo.save(pokerUserStory);
	}

	@Override
	public void deletePPUserStory(String userStoryId) {
		userStoryRepo.deleteById(userStoryId);
	}

	@Override
	public List<PokerUserStory> getAllPPStories() {
		List<PokerUserStory> userStories = userStoryRepo.findAll();
		return userStories;
	}

	
	
}
