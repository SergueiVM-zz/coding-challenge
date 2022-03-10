package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int memberId;
	private String name;
	@OneToOne
	private PlanningSession session;

	public PlanningSession getSession() {
		return session;
	}
	public void setSession(PlanningSession session) {
		this.session = session;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
