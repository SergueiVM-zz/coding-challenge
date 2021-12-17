package com.planning.poker.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planning.poker.exception.AddUpdateMemberException;
import com.planning.poker.exception.FetchMemberException;
import com.planning.poker.exception.FetchUserStoryException;
import com.planning.poker.exception.MemberNotFoundException;
import com.planning.poker.exception.SessionNotFoundException;
import com.planning.poker.exception.UserStoryNotFoundException;
import com.planning.poker.model.Member;
import com.planning.poker.model.PokerSession;
import com.planning.poker.model.UserStory;
import com.planning.poker.service.PokerSessionService;

@RestController
//@RequestMapping("/planningPoker/poker")
@RequestMapping("/poker")

public class PlanningPokerSessionController {

	Logger logger = LoggerFactory.getLogger(PlanningPokerSessionController.class);
	
	@Autowired
	PokerSessionService pokerSessionService;
	
	/*
	 * @RequestMapping("/hello") String hello() { return
	 * "Hello World, Spring Boot!"; }
	 */
	
	/**
	 * A member can create a new poker session
	 * 
	 * @param title
	 * @param deckType
	 * @param memberName
	 * @return session
	 */
	@PostMapping("/createPockerPlanningSession")
	 public ResponseEntity<PokerSession> createPockerPlanningSession(@RequestParam String title,@RequestParam String deckType,@RequestParam String memberName) {
		logger.info("createPockerPlanningSession method start");
		try {
			PokerSession pokerSession = pokerSessionService.createPockerPlanningSession(new PokerSession(title,deckType),memberName);
			logger.info("createPockerPlanningSession method end");
			return new ResponseEntity<>(pokerSession, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception in createPockerPlanningSession",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
    }
	
	/**
	 * @return all poker sessions 
	 */
	@GetMapping("/getAllPokerSession")
	public List<PokerSession> getAllPokerSession() {
		logger.info("getAllPokerSession method start");
		List<PokerSession> allSession=pokerSessionService.getAllPokerSession();
		if(allSession.isEmpty())
			throw new SessionNotFoundException("There are no sessions available now");
		logger.info("getAllPokerSession method end");
			return allSession;
	}
	
	
	/**
	 * @param session
	 * @return Poker Session with title dectype all details
	 */
	@GetMapping("/getPokerSessionBySession")
	public PokerSession getPokerSessionBySession(@RequestParam String session) {
		logger.info("getPokerSessionBySession method start");
		
			Optional<PokerSession> pokerSession=pokerSessionService.getPokerSessionBySession(session);
			pokerSession.orElseThrow(() ->
			 new SessionNotFoundException("Invalid session id : " + session));
			logger.info("getPokerSessionBySession method end");
			return pokerSession.get();

		
	}
	// get title of the session
	/**
	 * @param session
	 * @return Title of the session
	 */
	@GetMapping("/getTitleOfTheSession")
	public String getTitleOfTheSession(@RequestParam String session) {

			return pokerSessionService.getTitleOfTheSession(session);
	}
	
	//add current member name to member list
	//get all member list
	/**
	 * A Member can entered to particular session
	 * @param session
	 * @param memberName
	 * @return list of members
	 */
	@PostMapping("/addUpdateMemberList")
    public List<Member> addUpdateMemberList(@RequestParam String session,@RequestParam String memberName) {
		try {
		List<Member> memberList = pokerSessionService.addUpdateMemberList(session,memberName);
		
		return memberList;
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException("Invalid session id : " + session);
		}catch(Exception ex) {
			throw new AddUpdateMemberException("Unable to Add or Update Member to this session "+ex.getMessage());
		}
		
    }
	
	//get user story list
	/**
	 * @param session
	 * @return list of user stories added to that session
	 */
	@GetMapping("/getUserStoryListOfTheSession")
	public List<UserStory> getUserStoryListOfTheSession(@RequestParam String session) {

		try {
			List<UserStory> getUserStoryList= pokerSessionService.getUserStoryListOfTheSession(session);
			
			if(getUserStoryList.isEmpty())
				throw new UserStoryNotFoundException("UserStory not found for session "+session);
		return getUserStoryList;
		}catch(EntityNotFoundException ex) {
			throw new SessionNotFoundException("Invalid session id : " + session);
		}catch(Exception ex) {
			throw new FetchUserStoryException("Unable to fetch UserStory List "+ex.getMessage());
		}
		
		
	}
	
	//get member list against session
		/**
		 * @param session
		 * @return list of members added to that session
		 */
		@GetMapping("/getMemberListOfTheSession")
		public List<Member> getMemberListOfTheSession(@RequestParam String session) {
			try {
				List<Member> memberList= pokerSessionService.getMemberListOfTheSession(session);
				
				if(memberList.isEmpty())
					throw new MemberNotFoundException("Member not found for session "+session);
			return memberList;
			}catch(EntityNotFoundException ex) {
				throw new SessionNotFoundException("Invalid session id : " + session);
			}catch(Exception ex) {
				throw new FetchMemberException("Unable to fetch Member List "+ex.getMessage());
			}
			
			
		}
	/**
	 * It is a soft delete of poker session 
	 * isSessionAlive flag value will be change to False
	 * @param session
	 */
	@PostMapping("/destroyPokerSession")
    public void destroyPokerSession(@RequestParam String session) {
		try {
			// get all data related to the session--pokersession,userstory,member
			//destroy all
			pokerSessionService.destroyPokerSession(session);
		}catch(Exception ex) {
			throw new SessionNotFoundException("Invalid session id : " + session);
		}
		
    }
	
}
