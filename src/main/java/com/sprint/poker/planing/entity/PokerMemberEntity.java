package com.sprint.poker.planing.entity;

import com.sprint.poker.planing.enumeration.VotingStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Poker_Member")
public class PokerMemberEntity {

    @Id
    @Column(name = "memberId")
    private String memberId;

    @Column(name = "memberName")
    private String memberName;

    @Column(name = "active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessionId")
    private PokerSessionEntity pokerSessionEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PokerMemberUserStoryEntity> PokerMemberUserStoryEntity;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public VotingStatus getVotingStatus() {
        return votingStatus;
    }

    public void setVotingStatus(VotingStatus votingStatus) {
        this.votingStatus = votingStatus;
    }

    public PokerSessionEntity getPokerSessionEntity() {
        return pokerSessionEntity;
    }

    public void setPokerSessionEntity(PokerSessionEntity pokerSessionEntity) {
        this.pokerSessionEntity = pokerSessionEntity;
    }

    public Set<PokerMemberUserStoryEntity> getPokerMemberUserStoryEntity() {
        return PokerMemberUserStoryEntity;
    }

    public void setPokerMemberUserStoryEntity(Set<PokerMemberUserStoryEntity> pokerMemberUserStoryEntity) {
        PokerMemberUserStoryEntity = pokerMemberUserStoryEntity;
    }
}

