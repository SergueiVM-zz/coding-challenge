package com.planning.poker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Entity
@Table(name = "PokerSession")
public class PokerSession {

	@Id
	@Column(name = "session")
	private String session;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "deckType")
	private String deckType;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	
	
	@Column(name = "isSessionAlive")
	private Boolean isSessionAlive = true;
	
	
	@Column(name = "destroyedAt")
	private LocalDateTime destroyedAt;
	
	//@OneToMany(mappedBy="pokerSession")
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="session")
    private Set<UserStory> userStories;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="session")
	private Set<Member> members;
//    private Set<Member> members = new HashSet<Member>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="session")
	private Set<MemberUserStory> memberUserStory;
	
	
	public PokerSession() {
		super();
	}
	public PokerSession(String title, String deckType) {
		super();
		this.title = title;
		this.deckType = deckType;
	}
	
	public PokerSession(String session, String title, String deckType, Set<UserStory> userStories,
			Set<Member> members) {
		super();
		this.session = session;
		this.title = title;
		this.deckType = deckType;
		this.userStories = userStories;
		this.members = members;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeckType() {
		return deckType;
	}
	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Boolean getIsSessionAlive() {
		return isSessionAlive;
	}
	public void setIsSessionAlive(Boolean isSessionAlive) {
		this.isSessionAlive = isSessionAlive;
	}
	public LocalDateTime getDestroyedAt() {
		return destroyedAt;
	}
	public void setDestroyedAt(LocalDateTime destroyedAt) {
		this.destroyedAt = destroyedAt;
	}
	public Set<UserStory> getUserStories() {
		return userStories;
	}
	public void setUserStories(Set<UserStory> userStories) {
		this.userStories = userStories;
	}
	public Set<Member> getMembers() {
		return members;
	}
	public void setMembers(Set<Member> members) {
		this.members = members;
	}
	public Set<MemberUserStory> getMemberUserStory() {
		return memberUserStory;
	}
	public void setMemberUserStory(Set<MemberUserStory> memberUserStory) {
		this.memberUserStory = memberUserStory;
	}
	
	/*
	 * @Override public int hashCode() { return Objects.hash(deckType, members,
	 * session, title, userStories); }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; PokerSession other = (PokerSession) obj; return
	 * Objects.equals(deckType, other.deckType) && Objects.equals(members,
	 * other.members) && Objects.equals(session, other.session) &&
	 * Objects.equals(title, other.title) && Objects.equals(userStories,
	 * other.userStories); }
	 * 
	 * @Override public String toString() { return "PokerSession [session=" +
	 * session + ", title=" + title + ", deckType=" + deckType + ", userStories=" +
	 * userStories + ", members=" + members + "]"; }
	 */
	
	
}
