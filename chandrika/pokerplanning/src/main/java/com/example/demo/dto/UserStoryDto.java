package com.example.demo.dto;


public class UserStoryDto {
    private Integer idUserStory;
    private String Descriptions;
    private String status;
	public Integer getIdUserStory() {
		return idUserStory;
	}
	public void setIdUserStory(Integer idUserStory) {
		this.idUserStory = idUserStory;
	}
	public String getDescriptions() {
		return Descriptions;
	}
	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
