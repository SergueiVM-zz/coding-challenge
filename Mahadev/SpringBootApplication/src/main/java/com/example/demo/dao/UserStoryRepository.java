package com.example.demo.dao;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserStory;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Integer> {

	@Transactional
	public void deleteBySessionId(UUID sessionId);

}

