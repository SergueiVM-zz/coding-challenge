package com.sprint.poker.planing.repository;

import com.sprint.poker.planing.entity.PokerMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerMemberRepository extends JpaRepository<PokerMemberEntity, String>{

}
