package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.PokerBoardService;
import com.example.demo.service.VotingManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class PokerBoardController {

    @Autowired
    PokerBoardService pokerBoardService;

    @Autowired
    VotingManagement votingManagement;
    
	/*
	 * A member can create a new poker session
	 * 
	 * @param pokerSessionRequestDto
	 * @return session
	 */

    @PostMapping("")
    ResponseEntity<PokerPlanningSessionDto> createPokerSession(@RequestBody PokerSessionRequestDto pokerSessionRequestDto) {
        return ResponseEntity.ok().body(pokerBoardService.createPokerSession(pokerSessionRequestDto));
    }
    
	/*
	 * @param idSession
	 * @return poker session
	 */
    @GetMapping("/{idSession}")
    ResponseEntity<PokerPlanningSessionDto> getPokerSession(@PathVariable String idSession) {
        return ResponseEntity.ok().body(pokerBoardService.getPokerSession(idSession));

    }
	/* 
	 * @return all poker sessions 
	  */

    @GetMapping("")
    ResponseEntity<List<PokerPlanningSessionDto>> getAllPokerSession() {
        return ResponseEntity.ok().body(pokerBoardService.getAllPokerSession());

    }
    
	/*
	 * Add member into a session
	 * @param memberDtoRequest
	 * @return memberDtoRequest
	 */
    @PostMapping("/member")
    ResponseEntity<MemberDto> addMember(@RequestBody MemberDtoRequest memberDtoRequest) {
        return ResponseEntity.ok().body(pokerBoardService.addMember(memberDtoRequest));

    }
     
	/*
	 * @param idSession
	 * @return All sessionMembers
	 */
    @GetMapping("/{idSession}/members")
    ResponseEntity<List<MemberDto>> getSessionMembers(@PathVariable("idSession") String idSession) {
        return ResponseEntity.ok().body(pokerBoardService.getSessionMembers(idSession));

    }
	/*
	 * Add story into a session
	 * @param userStoryDtoRequest
	 * @return userStoryDtoRequest
	 */
    
    @PostMapping("/{idSession}/story")
    ResponseEntity<UserStoryDto> addUserStory(@RequestBody UserStoryDtoRequest userStoryDtoRequest) {
        return ResponseEntity.ok().body(pokerBoardService.addUserStory(userStoryDtoRequest));

    }
     
	/*
	 * @param idSession
	 * @return all stories
	 */
    @GetMapping("/{idSession}/story")
    ResponseEntity<List<UserStoryDto>> getSessionUserStories(@PathVariable String idSession) {
        return ResponseEntity.ok().body(pokerBoardService.getSessionUserStories(idSession));

    }

	/*
	 * @param idSession
	 * @return deleted story successfully
	 */
    @DeleteMapping("/{idSession}")
    ResponseEntity<ResponseDto> deleteSession(@PathVariable String idSession) {
        return ResponseEntity.ok().body(pokerBoardService.deleteSession(idSession));

    }
    
	/*
	 * @param idSession
	 * @param addVoteDto
	 * @return addVoteDto
	 */
    @PostMapping("/{idSession}/votes")
    ResponseEntity<ResponseDto> addVoteToUserStory(@PathVariable("idSession") String idSession, AddVoteDto addVoteDto) {
        return ResponseEntity.ok().body(votingManagement.addVoteToUserStory(idSession, addVoteDto));
    }

	/*
	 * @param idSession
	 * @param storyId
	 * @return vote counts
	 */
    @GetMapping("/{idSession}/story/{storyId}/votes")
    ResponseEntity<List<VotesGetByStory>> getVotesOfUserStory(@PathVariable("idSession") String idSession, @PathVariable("storyId") Integer storyId) {
        return ResponseEntity.ok().body(votingManagement.getVotesOfUserStory(idSession, storyId));

    }
	/*
	 * Here if we click start, status is change to voting & if we click stop, status
	   is change to voted.
	 * @param iSession
	 * @param storyId
	 * @param statusId
	 * @return status changed
	 */

    @PostMapping("/{idSession}/story/{storyId}/status/{status}")
    public ResponseEntity<ResponseDto> startOrStopVotesOfUserStory(@PathVariable("idSession") String idSession, @PathVariable("storyId") Integer userStory, @PathVariable("status") String status) {
        return ResponseEntity.ok().body(pokerBoardService.startOrStopVotesOfUserStory(idSession, userStory, status));
      
    }
	/*
	 * @param storyId
	 * @return deleted story successfully
	 */
    @DeleteMapping("/{idSession}/story/{storyId}")
    public ResponseEntity<ResponseDto> deleteUserStory(@PathVariable("storyId") Integer userStory) {
        return ResponseEntity.ok().body(pokerBoardService.deleteUserStory(userStory));

    }

    }
