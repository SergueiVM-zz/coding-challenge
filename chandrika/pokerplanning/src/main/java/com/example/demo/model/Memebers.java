package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Memebers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMemeber;
    private String name;
    private String idSession;
	public Integer getIdMemeber() {
		return idMemeber;
	}
	public void setIdMemeber(Integer idMemeber) {
		this.idMemeber = idMemeber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdSession() {
		return idSession;
	}
	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}


}
