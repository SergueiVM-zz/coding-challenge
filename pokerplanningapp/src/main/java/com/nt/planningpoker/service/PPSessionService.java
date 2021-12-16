package com.nt.planningpoker.service;

import java.util.List;

import com.nt.planningpoker.model.PokerSession;

public interface PPSessionService {
	
	PokerSession createPokerPlanningSession(PokerSession pokerSession);
	
	PokerSession createPokerPlanningSessionDetailed(PokerSession pokerSession);

	List<PokerSession> getAllPPSessions();

	List<PokerSession> getDetailedInfoOfSession(String nameOfUser, String sessionId);

	void destroyPokerSession(String sessionId);

}
