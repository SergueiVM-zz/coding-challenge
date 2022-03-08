package com.sprint.poker.planing.service;

import com.sprint.poker.planing.entity.PokerSessionEntity;
import com.sprint.poker.planing.entity.PokerStoryEntity;
import com.sprint.poker.planing.enumeration.VotingStatus;
import com.sprint.poker.planing.model.PokerStory;
import com.sprint.poker.planing.repository.PokerSessionRepository;
import com.sprint.poker.planing.repository.PokerStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class PokerStoryService {

    @Autowired
    private PokerSessionRepository pokerSessionRepository;

    @Autowired
    private PokerStoryRepository pokerStoryRepository;
    ;

    public PokerStory createPokerStory(@Valid PokerStory pokerStory) {
        PokerSessionEntity pokerSessionEntity = pokerSessionRepository.findById(pokerStory.getSessionId()).get();
        if (pokerSessionEntity.isActive()) {
            pokerStory.setStoryId(UUID.randomUUID().toString());
            final PokerStoryEntity pokerStoryEntity = this.dtoToEntityPokerUserStory(pokerStory, pokerSessionEntity, true);
            pokerStoryEntity.setCreateAt(LocalDateTime.now());
            this.pokerStoryRepository.save(pokerStoryEntity);
        }
        return pokerStory;
    }

    private PokerStoryEntity dtoToEntityPokerUserStory(PokerStory pokerStory, PokerSessionEntity pokerSessionEntity, boolean status) {
        PokerStoryEntity pokerStoryEntity = new PokerStoryEntity();
        pokerStoryEntity.setPokerSessionEntity(pokerSessionEntity);
        pokerStoryEntity.setStoryId(pokerStory.getStoryId());
        pokerStoryEntity.setCount(String.valueOf(0));
        pokerStoryEntity.setDescription(pokerStory.getDescription());
        pokerStoryEntity.setVotingStatus(VotingStatus.PENDING);
        pokerStoryEntity.setStatus(status);
        return pokerStoryEntity;
    }

    public PokerStory destroyUserStory(PokerStory pokerStory) {
        PokerStoryEntity pokerStoryEntity = pokerStoryRepository.findById(pokerStory.getStoryId()).get();
        PokerSessionEntity pokerSessionEntity = pokerSessionRepository.findById(pokerStory.getSessionId()).get();
        if (pokerStoryEntity.isStatus() && pokerStoryEntity.getVotingStatus() == VotingStatus.PENDING) {
            final PokerStoryEntity PokerStoryEntityRet = this.dtoToEntityPokerUserStory(pokerStory, pokerSessionEntity, false);
            PokerStoryEntityRet.setDestroyAt(LocalDateTime.now());
            this.pokerStoryRepository.save(PokerStoryEntityRet);
        }
        return pokerStory;
    }


}


