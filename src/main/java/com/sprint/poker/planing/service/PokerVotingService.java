package com.sprint.poker.planing.service;

import com.sprint.poker.planing.entity.PokerMemberEntity;
import com.sprint.poker.planing.entity.PokerMemberUserStoryEntity;
import com.sprint.poker.planing.entity.PokerStoryEntity;
import com.sprint.poker.planing.enumeration.VotingStatus;
import com.sprint.poker.planing.model.PokerVote;
import com.sprint.poker.planing.repository.PokerMemberRepository;
import com.sprint.poker.planing.repository.PokerMemberUserStoryRepository;
import com.sprint.poker.planing.repository.PokerSessionRepository;
import com.sprint.poker.planing.repository.PokerStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokerVotingService {

    @Autowired
    private PokerSessionRepository pokerSessionRepository;

    @Autowired
    private PokerStoryRepository pokerStoryRepository;

    @Autowired
    private PokerMemberRepository pokerMemberRepository;

    @Autowired
    private PokerMemberUserStoryRepository pokerMemberUserStoryRepository;


    private PokerMemberUserStoryEntity dtoToEntityPokerMemberUserStory(PokerVote pokerVote) {
        PokerMemberUserStoryEntity pokerMemberUserStoryEntity = new PokerMemberUserStoryEntity();
        PokerStoryEntity pokerStoryEntity = pokerStoryRepository.findById(pokerVote.getStoryId()).get();
        PokerMemberEntity pokerMemberEntity = pokerMemberRepository.findById(pokerVote.getMemberId()).get();
        pokerMemberEntity.setVotingStatus(VotingStatus.VOTED);
        pokerMemberRepository.save(pokerMemberEntity);
        pokerMemberUserStoryEntity.setMemberUserStoryId(UUID.randomUUID().toString());
        pokerMemberUserStoryEntity.setPokerSessionEntity(pokerSessionRepository.findById(pokerVote.getSessionId()).get());
        pokerMemberUserStoryEntity.setPokerMemberEntity(pokerMemberRepository.findById(pokerVote.getMemberId()).get());
        pokerMemberUserStoryEntity.setPokerStoryEntity(pokerStoryEntity);
        pokerMemberUserStoryEntity.setVotePoint(pokerVote.getVotePoint());
        Integer cnt = Integer.valueOf(pokerStoryEntity.getCount());
        pokerStoryEntity.setCount(String.valueOf(cnt));
        pokerStoryEntity.setVotingStatus(VotingStatus.VOTING);
        pokerStoryRepository.save(pokerStoryEntity);
        pokerMemberUserStoryRepository.save(pokerMemberUserStoryEntity);
        return pokerMemberUserStoryEntity;
    }

    public PokerVote submitVotePoint(PokerVote pokerVote) {
        PokerMemberUserStoryEntity pokerMemberUserStoryEntity = this.dtoToEntityPokerMemberUserStory(pokerVote);
        this.pokerMemberUserStoryRepository.save(pokerMemberUserStoryEntity);
        return pokerVote;
    }

    public List<String> getAllVotedMember() {
        String memberName;

        return null;
    }

    public List<String> getAllVotedUserStories() {
        return null;
    }

    public List<PokerVote> stopVotingUserStory(String userStoryId) {
        PokerStoryEntity pokerStoryEntity = pokerStoryRepository.findById(userStoryId).get();
        pokerStoryEntity.setVotingStatus(VotingStatus.VOTED);
        pokerStoryRepository.save(pokerStoryEntity);
        List<PokerMemberUserStoryEntity> pokerMemberUserStoryEntityLst = pokerMemberUserStoryRepository.findAll();
        List<PokerVote> pokerVoteLst = new ArrayList<>();
        pokerMemberUserStoryEntityLst.forEach(entity -> {
            PokerVote pokerVote = entityToDtoPokerMemberUserStory(entity);
            pokerVoteLst.add(pokerVote);
        });
        return pokerVoteLst;
    }

    private PokerVote entityToDtoPokerMemberUserStory(PokerMemberUserStoryEntity entity) {
        PokerVote pokerVote = new PokerVote();
        pokerVote.setStoryId(entity.getPokerStoryEntity().getStoryId());
        pokerVote.setVotePoint(entity.getVotePoint());
        return pokerVote;
    }

    public int getUserStoryCount(String userStoryId) {
        PokerStoryEntity pokerStoryEntity = pokerStoryRepository.findById(userStoryId).get();
        int cnt = Integer.valueOf(pokerStoryEntity.getCount());
        return cnt;
    }


    public int getFinalVotePointForUserStory(String userStoryId, String sessionId) {
        List<PokerMemberUserStoryEntity> pokerMemberUserStoryEntityLst = pokerMemberUserStoryRepository.findAll();
        List<String> votePoints = pokerMemberUserStoryEntityLst.stream().filter(entity -> sessionId.equalsIgnoreCase(entity.getPokerSessionEntity().getSessionId()) && userStoryId.equalsIgnoreCase(entity.getPokerStoryEntity().getStoryId())).map(entity -> entity.getVotePoint()).collect(Collectors.toList());
        Integer max = votePoints
                .stream()
                .mapToInt(v -> Integer.parseInt(v))
                .max().orElseThrow(NoSuchElementException::new);
        return max;
    }
}


