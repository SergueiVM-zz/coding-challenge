package com.nt.planningpoker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.planningpoker.model.PokerUserStory;

public interface UserStoryRepository extends  JpaRepository<PokerUserStory, String>  {

}
