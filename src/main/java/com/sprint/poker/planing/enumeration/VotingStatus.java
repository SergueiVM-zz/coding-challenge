package com.sprint.poker.planing.enumeration;


public enum VotingStatus {
    PENDING("Pending"),
    VOTING("Voting"),
    VOTED("Voted");

    private String status;

    VotingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

