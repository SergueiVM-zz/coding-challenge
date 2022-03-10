package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberRepository;
import com.example.demo.dao.SessionManagementRepository;
import com.example.demo.dao.UserStoryRepository;
import com.example.demo.dao.VotingStatusRepository;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.model.PlanningSession;
import com.example.demo.model.VotingStatus;
import com.example.demo.util.Constants;

@Service
public class SessionManagementService {

	@Autowired
	private SessionManagementRepository sessionManagementRepository;

	@Autowired
	private VotingStatusRepository votingStatusRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private UserStoryRepository userStoryRepository;

	public PlanningSession createSession(PlanningSession planningSession) {
		if(sessionManagementRepository.save(planningSession)!=null) {
			VotingStatus votingStatus = setVotingStatus(planningSession);
			votingStatusRepository.save(votingStatus);	
		}
		return planningSession;
	}

	private VotingStatus setVotingStatus(PlanningSession planningSession) {
		VotingStatus votingStatus = new VotingStatus();
		votingStatus.setSessionId(planningSession.getSessionId());
		votingStatus.setStatus(Constants.NOTSTARTED);
		return votingStatus;
	}

	public List<PlanningSession> fetchSessions() {
		return sessionManagementRepository.findAll();
	}

	public Optional<PlanningSession> findSessionById(UUID id) {
		return sessionManagementRepository.findById(id);
	}

	public void deleteSessionById(PlanningSession session) {
		memberRepository.deleteMemberBySession(session);
		sessionManagementRepository.deleteById(session.getSessionId());
		votingStatusRepository.deleteBySessionId(session.getSessionId());
		userStoryRepository.deleteBySessionId(session.getSessionId());
	}

	public void createMember(Member member) {
		memberRepository.save(member);
	}
	;
	public List<Member> findMembersBySessionId(PlanningSession sessionId) {
		return memberRepository.findMemberBySession(sessionId);

	}
	public Member findById(int memberId) {
		Optional<Member> member = memberRepository.findById(memberId);
		try {
			if(member.get()!=null) {
				return member.get();
			}
		} catch (NoSuchElementException e) {
			throw new ItemNotFoundException("MemberId doesn't exist");
		}
		return null;
	}

	public void deleteById(int memberId) {
		Optional<Member> member = memberRepository.findById(memberId);
		try {
			if(member.get()!=null) {
				memberRepository.deleteById(memberId);	
			}
		} catch (NoSuchElementException e) {
			throw new ItemNotFoundException("MemberId doesn't exist");
		}
	}
}
