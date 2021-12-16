package com.nt.planningpoker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "UserStory")
public class PokerUserStory {

	@Id
	@Column(name = "userStoryId")
	private String userStoryId;
	
	@Column(name = "description")
	private String description;
	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="sessionId")
    private PokerSession pokerSession;
	


	public PokerUserStory() {
		super();
	}


	public PokerUserStory(String userStoryId, String description) {
		super();
		this.userStoryId = userStoryId;
		this.description = description;
	}
	

	public String getUserStoryId() {
		return userStoryId;
	}
	public void setUserStoryId(String userStoryId) {
		this.userStoryId = userStoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public PokerSession getPokerSession() {
		return pokerSession;
	}

	public void setPokerSession(PokerSession pokerSession) {
		this.pokerSession = pokerSession;
	}


	
	
	
}
