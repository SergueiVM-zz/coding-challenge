package com.nt.planningpoker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.planningpoker.model.PokerSession;

@Repository
public interface SessionRepository extends  JpaRepository<PokerSession, String> {
	
	List<PokerSession> findByNameOfUser(String name);
	List<PokerSession> findByNameOfUserOrSessionId(String nameOfUser, String sessionId);
	List<PokerSession> findByNameOfUserAndSessionId(String nameOfUser, String sessionId);
	
	

}
