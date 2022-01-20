package com.poker.planning.model;

import org.springframework.stereotype.Component;

import com.poker.planning.entity.UserStrories;
@Component
public class UserLink {

private UserStrories userlist;
private int  dekType;
public UserStrories getUserlist() {
	return userlist;
}
public void setUserlist(UserStrories userlist) {
	this.userlist = userlist;
}
public int getDekType() {
	return dekType;
}
public void setDekType(int dekType) {
	this.dekType = dekType;
}


}
