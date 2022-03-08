package com.sprint.poker.planing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokerSession {

    private String sessionId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("deckType")
    private String deckType;

    @JsonProperty("member")
    private String member;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
