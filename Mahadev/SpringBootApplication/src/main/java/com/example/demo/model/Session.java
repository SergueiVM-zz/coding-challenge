package com.example.demo.model;

import java.net.URI;
import java.util.UUID;


public class Session   {

	private UUID sessionId;
	private String title;
	private URI location;
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
	public URI getLocation() {
		return location;
	}
	public void setLocation(URI location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "PlanningSession [sessionId=" + sessionId + ", title=" + title + "]";
	}

}
