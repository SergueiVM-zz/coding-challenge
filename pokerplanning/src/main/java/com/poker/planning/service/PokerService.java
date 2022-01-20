package com.poker.planning.service;

import org.jvnet.hk2.annotations.Service;

import com.poker.planning.entity.UserStrories;

@Service
public interface PokerService {

	public UserStrories proess(UserStrories user);
}
