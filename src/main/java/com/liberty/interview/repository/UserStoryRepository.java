package com.liberty.interview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liberty.interview.domain.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, String> {

	@Query("select us from UserStory us where us.session.id=?1")
	public List<UserStory> findByIdSession(String idSession);

	@Query("select us from UserStory us where us.session.id=?1 and us.id=?2")
	public Optional<UserStory> findUserStory(String idSession, String idUserStory);

}
