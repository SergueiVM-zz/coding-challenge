package com.sprint.poker.planing.service;

import com.sprint.poker.planing.entity.PokerMemberEntity;
import com.sprint.poker.planing.entity.PokerSessionEntity;
import com.sprint.poker.planing.enumeration.VotingStatus;
import com.sprint.poker.planing.model.PokerSession;
import com.sprint.poker.planing.repository.PokerMemberRepository;
import com.sprint.poker.planing.repository.PokerSessionRepository;
import com.sprint.poker.planing.repository.PokerStoryRepository;
import com.sprint.poker.planing.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PokerSessionService {

    @Autowired
    private PokerSessionRepository pokerSessionRepository;

    @Autowired
    private PokerStoryRepository pokerStoryRepository;

    @Autowired
    private PokerMemberRepository pokerMemberRepository;

    public PokerSession createPokerSession(PokerSession pokerSession) {
        pokerSession.setSessionId(UUID.randomUUID().toString());
        final PokerSessionEntity pokerSessionEntity = this.dtoToEntityPokerSession(pokerSession, true);
        pokerSessionEntity.setCreatedDate(LocalDateTime.now());
        this.pokerSessionRepository.save(pokerSessionEntity);
        Set<PokerMemberEntity> pokerMemberEntitySet = pokerSessionEntity.getPokerMemberEntity();
        this.pokerMemberRepository.save(pokerMemberEntitySet.stream().findFirst().get());
        return pokerSession;
    }

    private PokerSessionEntity dtoToEntityPokerSession(PokerSession pokerSession, boolean status) {
        PokerSessionEntity pokerSessionEntity = new PokerSessionEntity();
        pokerSessionEntity.setSessionId(pokerSession.getSessionId());
        pokerSessionEntity.setTitle(pokerSession.getTitle());
        pokerSessionEntity.setDeckType(pokerSession.getDeckType());
        pokerSessionEntity.setActive(status);
        pokerSessionEntity.setPokerMemberEntity(getSetOfMembers(pokerSession, pokerSessionEntity));
        return pokerSessionEntity;
    }

    private Set<PokerMemberEntity> getSetOfMembers(PokerSession pokerSession, PokerSessionEntity pokerSessionEntity) {
        Set<PokerMemberEntity> pokerMemberEntitySet = new HashSet<>();
        PokerMemberEntity pokerMemberEntity = new PokerMemberEntity();
        pokerMemberEntity.setPokerSessionEntity(pokerSessionEntity);
        pokerMemberEntity.setMemberId(UUID.randomUUID().toString());
        pokerMemberEntity.setMemberName(pokerSession.getMember());
        pokerMemberEntity.setVotingStatus(VotingStatus.PENDING);
        pokerMemberEntity.setActive(true);
        pokerMemberEntitySet.add(pokerMemberEntity);
        return pokerMemberEntitySet;
    }

    public List<PokerSession> getPokerSession() {
        List<PokerSessionEntity> pokerSessionEntityLst = pokerSessionRepository.findAll();
        List<PokerSession> pokerSessionLst = new ArrayList<>();
        pokerSessionEntityLst.forEach(entity -> pokerSessionLst.add(this.entityToDto(entity)));
        return pokerSessionLst;
    }

    private PokerSession entityToDto(PokerSessionEntity entity) {
        PokerSession pokerSession = new PokerSession();
        pokerSession.setSessionId(entity.getSessionId());
        pokerSession.setTitle(entity.getTitle());
        pokerSession.setDeckType(entity.getDeckType());
        pokerSession.setMember(entity.getPokerMemberEntity().stream().findFirst().get().getMemberName());
        return pokerSession;
    }


    public PokerSession addUpdateMemberList(PokerSession pokerSession) {
        final PokerSessionEntity pokerSessionEntity = this.dtoToEntityPokerSession(pokerSession, true);
        Set<PokerMemberEntity> pokerMemberEntitySet = pokerSessionEntity.getPokerMemberEntity();
        this.pokerMemberRepository.save(pokerMemberEntitySet.stream().findFirst().get());
        return pokerSession;
    }

    public PokerSession destroyPokerSession(PokerSession pokerSession) {
        final PokerSessionEntity pokerSessionEntity = this.dtoToEntityPokerSession(pokerSession, false);
        pokerSessionEntity.setDestroyDate(LocalDateTime.now());
        this.pokerSessionRepository.save(pokerSessionEntity);
        return pokerSession;
    }
}

