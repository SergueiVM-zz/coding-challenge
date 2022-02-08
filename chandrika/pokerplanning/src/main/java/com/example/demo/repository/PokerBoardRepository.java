package com.example.demo.repository;

import com.example.demo.model.PokerBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokerBoardRepository extends JpaRepository<PokerBoard,String> {

  Optional<PokerBoard> findByTitle(String title);
}
