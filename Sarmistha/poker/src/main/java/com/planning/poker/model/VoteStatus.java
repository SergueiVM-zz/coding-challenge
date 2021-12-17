package com.planning.poker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VoteStatus")
public class VoteStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String statusId;
	
	@Column(name = "status",nullable = false)
	private String status;
	
	/*
	 * @OneToMany(mappedBy="voteStatus") private Set<UserStory> userStory;
	 */
	/*
	 * @OneToOne(mappedBy = "voteStatus", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private UserStory userStory;
	 */
	 
	 
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*
	 * public UserStory getUserStory() { return userStory; } public void
	 * setUserStory(UserStory userStory) { this.userStory = userStory; }
	 */
	/*
	 * public Set<UserStory> getUserStory() { return userStory; } public void
	 * setUserStory(Set<UserStory> userStory) { this.userStory = userStory; }
	 */
	
	
}
