package com.poker.planning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserStrories {
@Id
	private String sessinId;
    private String title;
    private String deslink;
    private String desktype;
	public String getSessinId() {
		return sessinId;
	}
	public void setSessinId(String sessinId) {
		this.sessinId = sessinId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeslink() {
		return deslink;
	}
	public void setDeslink(String deslink) {
		this.deslink = deslink;
	}
	public String getDesktype() {
		return desktype;
	}
	public void setDesktype(String desktype) {
		this.desktype = desktype;
	}
    
}
