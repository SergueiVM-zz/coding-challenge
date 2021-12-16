package com.nt.planningpoker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nt.planningpoker.model.PokerSession;
import com.nt.planningpoker.repo.SessionRepository;
import com.nt.planningpoker.service.PPSessionService;

@Component
@Service
public class PPSessionServiceImpl implements PPSessionService {

	@Autowired
	SessionRepository sessionRepo;
	
	@Override
	public PokerSession createPokerPlanningSession(PokerSession pokerSession) {
		// TODO Auto-generated method stub
		
		PokerSession pkrSession = sessionRepo.save(pokerSession);
		return pkrSession;
	}
	
	
	@Override
	public PokerSession createPokerPlanningSessionDetailed(PokerSession pokerSession) {
		// TODO Auto-generated method stub
		
		PokerSession pkrSession = sessionRepo.save(pokerSession);
		return pkrSession;
	}
	
	@Override
	public List<PokerSession> getAllPPSessions() {
			return sessionRepo.findAll();
	}
	
	
	@Override
	public List<PokerSession> getDetailedInfoOfSession(String nameOfUser, String sessionId) {
		
		//return sessionRepo.findByNameOfUserOrSessionId(nameOfUser,sessionId);
		return sessionRepo.findByNameOfUserAndSessionId(nameOfUser,sessionId);
			
	}
	
	@Override
	public void destroyPokerSession(String sessionId) {
		sessionRepo.deleteById(sessionId);
		
	}
	
	

}
