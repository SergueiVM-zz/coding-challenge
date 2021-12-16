package com.nt.planningpoker.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.planningpoker.model.PokerSession;
import com.nt.planningpoker.service.PPSessionService;

@RestController
@RequestMapping("/pokerPlanning")
public class PPSessionContoller {
	
	
	@Autowired
	PPSessionService ppSessionService;
	
	private static String partURL = "https://www.google.com/search?q=";
	private static AtomicLong numberGenerator = new AtomicLong(910000000000L);

	
	@PostMapping("/createNewPPSession")
	 public ResponseEntity<PokerSession> createPockerPlanningSession(@RequestParam String title,@RequestParam String deckType) {
		try {
			String sessionUrl = partURL + String.valueOf(numberGenerator.getAndIncrement());
			PokerSession pokerSession = ppSessionService.createPokerPlanningSession(new PokerSession(title,deckType,sessionUrl));
			return new ResponseEntity<>(pokerSession, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
  }
	
	@PostMapping("/createNewPPSessionDetailed")
	 public ResponseEntity<PokerSession> createPockerPlanningSessionDetailed(@RequestParam String title,
			 @RequestParam String deckType,  @RequestParam String members, @RequestParam String nameOfUser) {
		try {
			String sessionUrl = partURL + String.valueOf(numberGenerator.getAndIncrement());
			PokerSession pokerSession = ppSessionService.createPokerPlanningSessionDetailed(new PokerSession(title,deckType,sessionUrl,nameOfUser,members));
			return new ResponseEntity<>(pokerSession, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
 }
	
	
	@GetMapping("/getAllPPSessions")
	public List<PokerSession> getAllPokerSession() {
		List<PokerSession> allSession = ppSessionService.getAllPPSessions();
			return allSession;
	}
	
	/*
	 * @GetMapping("/getSessionInfo/{name}&{sessionId}") public List<PokerSession>
	 * getDetailedInfoSession(@PathVariable String name, @PathVariable String
	 * sessionId) {
	 * 
	 * List<PokerSession> sessionInfo =
	 * ppSessionService.getDetailedInfoOfSession(name); //resp=new
	 * ResponseEntity<String>("destroySession'"+sId+"' sucessfully",HttpStatus.OK);
	 * return sessionInfo; }
	 */
	
	@GetMapping("/getSessionInfo")
	public List<PokerSession> getDetailedInfoSession(@RequestParam String nameOfUser,@RequestParam String sessionId) {

		List<PokerSession> sessionInfo = ppSessionService.getDetailedInfoOfSession(nameOfUser, sessionId);
		return sessionInfo;
	}
	
	@DeleteMapping("/destroyPPSession")
    public void destroyPokerSession(@RequestParam String sessionId) {
		try {
			ppSessionService.destroyPokerSession(sessionId);
		}catch(Exception ex) {
			System.out.println("Invalid session id : " + sessionId);
		}
	}

}
