package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="VOTINGSTATUS")
public class VotingStatus {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonProperty(access = Access.WRITE_ONLY)
	private int id;
	@Column(name="sessionId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private UUID sessionId;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UUID getSessionId() {
		return sessionId;
	}
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
