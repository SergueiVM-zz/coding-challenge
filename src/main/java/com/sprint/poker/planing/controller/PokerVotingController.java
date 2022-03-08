package com.sprint.poker.planing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sprint.poker.planing.model.PokerVote;
import com.sprint.poker.planing.service.PokerVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/poker"})
public class PokerVotingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokerStoryController.class);

    @Autowired
    private PokerVotingService pokerVotingService;

    /**
     * submit vote to user story given by member
     *
     * @param pokerVote
     * @return
     */
    @PostMapping("/submitVotePoint")
    public ResponseEntity<PokerVote> submitVotePoint(@RequestBody PokerVote pokerVote) {
        try {
            PokerVote pokerVoteRet = pokerVotingService.submitVotePoint(pokerVote);
            return new ResponseEntity<>(pokerVoteRet, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * get  member who have voted
     *
     * @return
     */
    @GetMapping("/getAllVotedMember")
    public ResponseEntity<List<String>> getAllVotedMember() {
        try {
            List<String> members= pokerVotingService.getAllVotedMember();
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * get voted user stories
     *
     * @return
     */
    @GetMapping("/getAllVotedUserStories")
    public ResponseEntity<List<String>> getAllVotedUserStories() {
        try {
            List<String> userStories= pokerVotingService.getAllVotedUserStories();
            return new ResponseEntity<>(userStories, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * stop user story for voting
     *
     * @param userStoryId
     * @return
     */
    @PostMapping("/stopVotingUserStory")
    public ResponseEntity<List<PokerVote>> stopVotingUserStory(@RequestParam String userStoryId) {
        try {
            List<PokerVote> PokerVoteLst = pokerVotingService.stopVotingUserStory(userStoryId);

            return new ResponseEntity<>(PokerVoteLst, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * count number of vote given to story
     *
     * @param userStoryId
     * @return int
     */
    @GetMapping("/getUserStoryCount")
    public ResponseEntity<Integer> getUserStoryCount(@RequestParam String userStoryId) {
        try {
            Integer cnt = pokerVotingService.getUserStoryCount(userStoryId);
            return new ResponseEntity<>(cnt, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * calculate the highest given vote point
     *
     * @param userStoryId
     * @param sessionId
     * @return int
     */
    @GetMapping("/getFinalVotePointForUserStory")
    public ResponseEntity<Integer> getFinalVotePointForUserStory(@RequestParam String userStoryId, @RequestParam String sessionId) {
        try {
            Integer finalVotePoint= pokerVotingService.getFinalVotePointForUserStory(userStoryId, sessionId);
            return new ResponseEntity<>(finalVotePoint, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
