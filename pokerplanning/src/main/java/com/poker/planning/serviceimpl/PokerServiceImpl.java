package com.poker.planning.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poker.planning.entity.UserStrories;
import com.poker.planning.repo.Pokerplaning;
import com.poker.planning.service.PokerService;
@Service
public class PokerServiceImpl implements PokerService {

	@Autowired
	Pokerplaning poker;
	@Override
	public UserStrories proess(UserStrories user) {
		UserStrories pokerplanningcreated=poker.save(user);
		return pokerplanningcreated;
	}

}
