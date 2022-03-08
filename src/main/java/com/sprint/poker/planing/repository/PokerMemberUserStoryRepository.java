package com.sprint.poker.planing.repository;

import com.sprint.poker.planing.entity.PokerMemberEntity;
import com.sprint.poker.planing.entity.PokerMemberUserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokerMemberUserStoryRepository extends JpaRepository<PokerMemberUserStoryEntity, String>{


}
