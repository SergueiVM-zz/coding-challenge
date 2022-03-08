package com.sprint.poker.planing.controller;


import com.sprint.poker.planing.model.PokerStory;
import com.sprint.poker.planing.service.PokerStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/poker"})
public class PokerStoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokerStoryController.class);

    @Autowired
    private PokerStoryService pokerStoryService;

    /**
     * map data to user story table
     * @param pokerStory
     * @return PokerStory
     */
    @PostMapping("/addUserStory")
    public ResponseEntity<PokerStory> createPokerSession(@RequestBody PokerStory pokerStory) {
        try {
            PokerStory pokerStoryRet = pokerStoryService.createPokerStory(pokerStory);
            return new ResponseEntity<>(pokerStoryRet, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param pokerStory
     * @return PokerStoryRet
     */
    @PostMapping("/destroyUserStory")
    public ResponseEntity<PokerStory> destroyUserStory(@Valid @RequestBody PokerStory pokerStory) {
        try {
            PokerStory PokerStoryRet = pokerStoryService.destroyUserStory(pokerStory);
            return new ResponseEntity<>(PokerStoryRet, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
