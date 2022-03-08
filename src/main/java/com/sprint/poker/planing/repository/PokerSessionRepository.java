package com.sprint.poker.planing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.poker.planing.entity.PokerSessionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerSessionRepository extends JpaRepository<PokerSessionEntity, String>{
}
