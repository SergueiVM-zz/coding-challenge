package com.planning.poker.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planning.poker.exception.VotePointOutOfRangeException;
import com.planning.poker.model.Member;
import com.planning.poker.model.MemberUserStory;
import com.planning.poker.model.UserStory;
import com.planning.poker.service.VoteService;

@RestController
//@RequestMapping("/planningPoker/voting")
@RequestMapping("/voting")
public class PlanningPokerVoteController {

	Logger logger = LoggerFactory.getLogger(PlanningPokerVoteController.class);
	
	@Autowired
	VoteService voteService;
	
	/*
	 * //need to revisit
	 * 
	 * @GetMapping("/getUserStoriesWithStatus") public List<Map<String,
	 * List<UserStory>>> getUserStoriesWithStatus(@RequestParam String session) {
	 * List<Map<String, List<UserStory>>> listStoriesWithStatus =
	 * voteService.getUserStoriesWithStatus(session);
	 * 
	 * return listStoriesWithStatus; }
	 */
	
	/**
	 * A Member can submit vote with some vote point value (Should be in range of 1-9)
	 * (Vote Point value range can be changed based on requirement)
	 * in a particular session for a particular user story id
	 * Status of user story will be changed to VOTING
	 * If Story already in VOTED then throw exception
	 * @param uStoryId
	 * @param memberId
	 * @param session
	 * @param votePoint
	 * @return count of member vote for a particular user story
	 */
	@PostMapping("/submitUserStoryVote")
	public int submitUserStoryVote(@RequestParam String uStoryId,@RequestParam String memberId,@RequestParam String session,@RequestParam Integer votePoint) {

		if(votePoint<1 || votePoint>9) {
			throw new VotePointOutOfRangeException("Vote Ponit is not in range");
		}
		
		return voteService.submitUserStoryVote(uStoryId,memberId,session,votePoint);
	}
	
	@GetMapping("/showMemberVotingStatus")
	public List<Member> showMemberVotingStatus() {

			return voteService.showMemberVotingStatus();
	}
	
	@GetMapping("/showUserStoryVotingStatus")
	public List<UserStory> showUserStoryVotingStatus() {

			return voteService.showUserStoryVotingStatus();
	}
	
	/**
	 * @param uStoryId
	 * @return
	 * Don't use me
	 * Use stopuserStoryVoting and fetchVoteFinalResultForUserStory in sequence
	 */
	@PostMapping("/moveStoryStatusToVoted")
	public UserStory moveUserStoryStatusToVoted(@RequestParam String uStoryId) {

		return voteService.moveUserStoryStatusToVoted(uStoryId);
	}
	/**
	 * Stop user story voting
	 * Status of user story will change to VOTED
	 * MemberUserStory is mapping table of Member ,User Story and Poker Session
	 * From the return value basically Member Vote Point value can be displayed
	 * @param uStoryId
	 * @return MemberUserStory list
	 */
	@PostMapping("/stopuserStoryVoting")
	public List<MemberUserStory> stopuserStoryVoting(@RequestParam String uStoryId) {

		return voteService.stopuserStoryVoting(uStoryId);
	}
	
	/**
	 * @param uStoryId
	 * @return count of vote for a particular user story
	 */
	@GetMapping("/fetchVoteCountForUserStory")
	public int fetchVoteCountForUserStory(@RequestParam String uStoryId) {

			return voteService.fetchVoteCountForUserStory(uStoryId);
	}
	
	/**
	 * Calculating vote final result by maximum value of votePoint 
	 * entered by members for a particular story
	 * (It can be change based upon team decision)
	 * @param uStoryId
	 * @param session
	 * @return final vote point considered for a particular user story
	 *
	 */
	@GetMapping("/fetchVoteFinalResultForUserStory")
	public int fetchVoteFinalResultForUserStory(@RequestParam String uStoryId,@RequestParam String session) {

			return voteService.fetchVoteFinalResultForUserStory(uStoryId,session);
	}
}
