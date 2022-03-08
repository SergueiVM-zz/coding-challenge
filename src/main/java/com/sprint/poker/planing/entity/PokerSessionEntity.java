package com.sprint.poker.planing.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Poker_Session")
public class PokerSessionEntity {

    @Id
    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "title")
    private String title;

    @Column(name = "deckType")
    private String deckType;

    @Column(name="active")
    private boolean active;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "destroy_date")
    private LocalDateTime destroyDate;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<PokerStoryEntity> pokerStoryEntity;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<PokerMemberEntity> pokerMemberEntity;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<PokerMemberUserStoryEntity> PokerMemberUserStoryEntity;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getDestroyDate() {
        return destroyDate;
    }

    public void setDestroyDate(LocalDateTime destroyDate) {
        this.destroyDate = destroyDate;
    }

    public Set<PokerStoryEntity> getPokerStoryEntity() {
        return pokerStoryEntity;
    }

    public void setPokerStoryEntity(Set<PokerStoryEntity> pokerStoryEntity) {
        this.pokerStoryEntity = pokerStoryEntity;
    }

    public Set<PokerMemberEntity> getPokerMemberEntity() {
        return pokerMemberEntity;
    }

    public void setPokerMemberEntity(Set<PokerMemberEntity> pokerMemberEntity) {
        this.pokerMemberEntity = pokerMemberEntity;
    }

    public Set<PokerMemberUserStoryEntity> getPokerMemberUserStoryEntity() {
        return PokerMemberUserStoryEntity;
    }

    public void setPokerMemberUserStoryEntity(Set<PokerMemberUserStoryEntity> pokerMemberUserStoryEntity) {
        PokerMemberUserStoryEntity = pokerMemberUserStoryEntity;
    }


}
