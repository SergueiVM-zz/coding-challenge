package com.example.demo.repository;

import com.example.demo.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {


  Optional<List<Votes>> findByStoryId(Integer storyId);
  Optional<Void>   deleteByStoryId(Integer storyId);

  Optional<Votes>  findByStoryIdAndMemberId(Integer storyId, Integer memberId);


}
