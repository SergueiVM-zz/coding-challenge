package com.liberty.interview.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Session {

	@Id
	private String id;

	private String title;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<>();

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserStory> userStories = new ArrayList<>();

	public void addMember(Member member) {
		member.setSession(this);
		members.add(member);
	}

	public void removeMember(Member member) {
		members.remove(member);
		member.setSession(null);
	}

}
