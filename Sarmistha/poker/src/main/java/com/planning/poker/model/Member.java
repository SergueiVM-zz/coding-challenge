package com.planning.poker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memberId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "status")
	private String memberStatus;
	
	/*
	 * @Column(name = "votePoint") private Integer votePoint;
	 */
	
	/*
	 * @JsonIgnore
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="uStoryId") private UserStory userStory;
	 */
//	@ManyToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JoinTable(name = "member_userstory",
//            joinColumns = { @JoinColumn(name = "memberId") },
//            inverseJoinColumns = { @JoinColumn(name = "uStoryId") })
	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "members") private Set<UserStory> userStories = new HashSet<>();
	 */
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="session")
    //@JoinColumn(name="session",nullable=false)
    private PokerSession pokerSession;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="memberId")
	private Set<MemberUserStory> memberUstory;
	
	
	public Member() {
		super();
	}
	public Member(String name, String memberStatus) {
		super();
		this.name = name;
		this.memberStatus = memberStatus;
		//this.votePoint = votePoint;
	}
	
	
	public PokerSession getPokerSession() {
		return pokerSession;
	}
	public void setPokerSession(PokerSession pokerSession) {
		this.pokerSession = pokerSession;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	/*
	 * public UserStory getUserStory() { return userStory; } public void
	 * setUserStory(UserStory userStory) { this.userStory = userStory; }
	 */
	/*
	 * @Override public String toString() { return "Member [memberId=" + memberId +
	 * ", name=" + name + ", memberStatus=" + memberStatus + ", userStory=" +
	 * userStory + ", pokerSession=" + pokerSession + "]"; }
	 */
	/*
	 * public Integer getVotePoint() { return votePoint; } public void
	 * setVotePoint(Integer votePoint) { this.votePoint = votePoint; }
	 */
	/*
	 * public Set<UserStory> getUserStories() { return userStories; } public void
	 * setUserStories(Set<UserStory> userStories) { this.userStories = userStories;
	 * }
	 */
	public Set<MemberUserStory> getMemberUstory() {
		return memberUstory;
	}
	public void setMemberUstory(Set<MemberUserStory> memberUstory) {
		this.memberUstory = memberUstory;
	}
	
	
}
