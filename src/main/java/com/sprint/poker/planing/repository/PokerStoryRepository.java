package com.sprint.poker.planing.repository;

import com.sprint.poker.planing.entity.PokerStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerStoryRepository extends JpaRepository<PokerStoryEntity, String>{

}
