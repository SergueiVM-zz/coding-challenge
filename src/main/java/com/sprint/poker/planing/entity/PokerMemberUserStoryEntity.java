package com.sprint.poker.planing.entity;

import javax.persistence.*;

@Entity
@Table(name = "Poker_Member_User_Story")
public class PokerMemberUserStoryEntity {

    @Id
    @Column(name = "memberUserStoryId")
    private String memberUserStoryId;

    @Column(name = "votePoint")
    private String votePoint;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="memberId")
    private PokerMemberEntity pokerMemberEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="storyId")
    private PokerStoryEntity pokerStoryEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sessionId")
    private PokerSessionEntity pokerSessionEntity;


    public String getMemberUserStoryId() {
        return memberUserStoryId;
    }

    public void setMemberUserStoryId(String memberUserStoryId) {
        this.memberUserStoryId = memberUserStoryId;
    }

    public String getVotePoint() {
        return votePoint;
    }

    public void setVotePoint(String votePoint) {
        this.votePoint = votePoint;
    }

    public PokerMemberEntity getPokerMemberEntity() {
        return pokerMemberEntity;
    }

    public void setPokerMemberEntity(PokerMemberEntity pokerMemberEntity) {
        this.pokerMemberEntity = pokerMemberEntity;
    }

    public PokerStoryEntity getPokerStoryEntity() {
        return pokerStoryEntity;
    }

    public void setPokerStoryEntity(PokerStoryEntity pokerStoryEntity) {
        this.pokerStoryEntity = pokerStoryEntity;
    }

    public PokerSessionEntity getPokerSessionEntity() {
        return pokerSessionEntity;
    }

    public void setPokerSessionEntity(PokerSessionEntity pokerSessionEntity) {
        this.pokerSessionEntity = pokerSessionEntity;
    }
}

