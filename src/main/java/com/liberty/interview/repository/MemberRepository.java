package com.liberty.interview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liberty.interview.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	@Query("select m from Member m where m.session.id=?1")
	public List<Member> findByIdSession(String idSession);

	@Query("select m from Member m where m.session.id=?1 and m.id=?2")
	public Optional<Member> findMember(String idSession, String idMember);

}
