package com.planning.poker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planning.poker.model.VoteStatus;

public interface VoteStatusRepository extends JpaRepository<VoteStatus, String> {

	@Query(value = "SELECT v FROM VoteStatus v WHERE v.status = ?1")
	VoteStatus findByStatusDesc(String status);
	
}
