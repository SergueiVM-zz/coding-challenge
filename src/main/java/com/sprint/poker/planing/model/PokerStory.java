package com.sprint.poker.planing.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokerStory {

    private String storyId;

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
