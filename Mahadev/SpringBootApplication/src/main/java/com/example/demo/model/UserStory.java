package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Component
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userStoryId;
	private String userStorydesc;
	@JsonProperty(access = Access.WRITE_ONLY)
	private UUID sessionId;
	public String getUserStorydesc() {
		return userStorydesc;
	}
	public void setUserStorydesc(String userStorydesc) {
		this.userStorydesc = userStorydesc;
	}
	public UUID getSessionId() {
		return sessionId;
	}
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	public int getUserStoryId() {
		return userStoryId;
	}
	public void setUserStoryId(int userStoryId) {
		this.userStoryId = userStoryId;
	}


}
