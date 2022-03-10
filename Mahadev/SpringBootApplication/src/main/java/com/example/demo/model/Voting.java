package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Voting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private long memberId;
	private String userStoryId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private UUID sessionId;
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getUserStoryId() {
		return userStoryId;
	}
	public void setUserStoryId(String userStoryId) {
		this.userStoryId = userStoryId;
	}
	public UUID getSessionId() {
		return sessionId;
	}
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}

}
