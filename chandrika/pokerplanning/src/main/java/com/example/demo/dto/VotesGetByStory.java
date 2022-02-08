package com.example.demo.dto;


public class VotesGetByStory {


    private Integer votesId;
    private Integer value;
    private Integer storyId;
    private Integer memberId;
	public Integer getVotesId() {
		return votesId;
	}
	public void setVotesId(Integer votesId) {
		this.votesId = votesId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getStoryId() {
		return storyId;
	}
	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
    
}
