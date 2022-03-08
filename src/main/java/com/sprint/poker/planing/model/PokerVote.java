package com.sprint.poker.planing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokerVote {

    @JsonProperty("storyId")
    private String storyId;

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("memberId")
    private String memberId;

    @JsonProperty("votePoint")
    private String votePoint;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getVotePoint() {
        return votePoint;
    }

    public void setVotePoint(String votePoint) {
        this.votePoint = votePoint;
    }


}
