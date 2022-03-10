package com.example.demo.dao;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VotingStatus;

@Repository
public interface VotingStatusRepository extends JpaRepository<VotingStatus, UUID> {

	
	public VotingStatus findStatusBySessionId(UUID sessionId);

	@Transactional
	public void deleteBySessionId(UUID sessionId);
	
	@Transactional
	@Modifying
	@Query("UPDATE VOTINGSTATUS v SET v.status = ?1 WHERE v.sessionId = ?2")
	public void update(String status, UUID sessionId);
}

