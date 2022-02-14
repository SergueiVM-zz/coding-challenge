package com.liberty.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberty.interview.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
