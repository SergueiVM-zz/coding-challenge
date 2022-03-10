package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class PlanningSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID sessionId;
	private String title;
	public UUID getSessionId() {
		return sessionId;
	}
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "PlanningSession [sessionId=" + sessionId + ", title=" + title + "]";
	}

}
