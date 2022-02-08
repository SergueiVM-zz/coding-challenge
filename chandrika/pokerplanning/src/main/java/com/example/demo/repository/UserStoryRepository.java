package com.example.demo.repository;

import com.example.demo.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory,Integer> {

  Optional<List<UserStory>> findByIdSession(String idSession);


}
