package com.nt.planningpoker.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "session")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdBy", "createdAt", "updatedBy", "updatedAt"}, 
        allowGetters = true)

public class PokerSession {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sessionId")
	private String sessionId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "deckType")
	private String deckType;
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "URL")
	private String url;
	
	@Column(name = "nameOfUser") 
	private String nameOfUser;
	//@Column(name = "members") private List<String> members;
	@Column(name = "members") 
	private String members;
	
	
	@OneToMany
	@JoinColumn(name="sessionId")
    private Set<PokerUserStory> userStories;
	
	@CreatedBy
	private String createdBy;
	

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @LastModifiedBy
    private String updatedBy;
   	
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
   
    
    

	public PokerSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PokerSession( String title, String deckType, String url) {
		super();
		this.title = title;
		this.deckType = deckType;
		this.url = url;
	}
	

	public PokerSession(String title, String deckType, String url, String nameOfUser, String members) {
		super();
		this.title = title;
		this.deckType = deckType;
		this.url = url;
		this.nameOfUser = nameOfUser;
		this.members = members;
	}


	public PokerSession(String sessionId, String title, String deckType, String url, String nameOfUser,
			String members, Set<PokerUserStory> userStories, String createdBy, Date createdAt, String updatedBy,
			Date updatedAt) {
		
		super();
		this.sessionId = sessionId;
		this.title = title;
		this.deckType = deckType;
		this.url = url;
		this.nameOfUser = nameOfUser;
		this.members = members;
		this.userStories = userStories;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}



	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public String getNameOfUser() {
		return nameOfUser;
	}



	public void setNameOfUser(String nameOfUser) {
		this.nameOfUser = nameOfUser;
	}



	public String getMembers() {
		return members;
	}



	public void setMembers(String members) {
		this.members = members;
	}



	public Set<PokerUserStory> getUserStories() {
		return userStories;
	}



	public void setUserStories(Set<PokerUserStory> userStories) {
		this.userStories = userStories;
	}



	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
    
    
    
    
}
