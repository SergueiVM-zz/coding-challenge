package com.example.demo.dto;


public class UserStoryDtoRequest {
    private String idSession;
    private String Descriptions;
    private String status;
	public String getIdSession() {
		return idSession;
	}
	public void setIdSession(String idSession) {
		this.idSession = idSession;
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