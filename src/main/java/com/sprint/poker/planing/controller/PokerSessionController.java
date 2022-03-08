package com.sprint.poker.planing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sprint.poker.planing.model.PokerSession;
import com.sprint.poker.planing.service.PokerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/poker"})
public class PokerSessionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokerSessionController.class);

    @Autowired
    private PokerSessionService pokerSessionService;

    /**
     * post resource for new session
     * data mapped to session and member table
     *
     * @param pokerSession
     * @return
     */
    @PostMapping("/session")
    public ResponseEntity<PokerSession> createPokerSession(@RequestBody PokerSession pokerSession) {
        try {
            PokerSession pokerSessionRet = pokerSessionService.createPokerSession(pokerSession);
            return new ResponseEntity<>(pokerSessionRet, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * get list of  session from poker session table
     *
     * @return PokerSession
     */
    @GetMapping("/getAllSession")
    public ResponseEntity<List<PokerSession>> getPokerSession() {
        try {
            List<PokerSession> PokerSessionLst = pokerSessionService.getPokerSession();
            return new ResponseEntity<>(PokerSessionLst, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * add member to the given poker
     * data mapped to member table
     *
     * @param pokerSession
     * @return
     */
    @PostMapping("/addUpdateMemberList")
    public ResponseEntity<PokerSession> addUpdateMemberList(@RequestBody PokerSession pokerSession) {
        try {
            PokerSession pokerSessionRet =
                    pokerSessionService.addUpdateMemberList(pokerSession);
            return new ResponseEntity<>(pokerSessionRet, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * destroy active session
     *
     * @param pokerSession
     * @return
     */
    @PostMapping("/destroyPokerSession")
    public ResponseEntity<PokerSession> destroyPokerSession(@RequestBody PokerSession pokerSession) {
        try {
            PokerSession pokerSessionRet = pokerSessionService.destroyPokerSession(pokerSession);

            return new ResponseEntity<>(pokerSessionRet, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
