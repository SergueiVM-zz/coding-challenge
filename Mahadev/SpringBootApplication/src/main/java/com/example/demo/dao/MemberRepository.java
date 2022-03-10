package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Member;
import com.example.demo.model.PlanningSession;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public List<Member> findMemberBySession(PlanningSession session); 

	@Transactional
	public void deleteMemberBySession(PlanningSession session);

	public Optional<Member> findByMemberId(int memberId); 
}

