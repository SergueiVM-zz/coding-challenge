package com.sprint.poker.planing.entity;

import com.sprint.poker.planing.enumeration.VotingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Poker_Story")
public class PokerStoryEntity {

    @Id
    @Column(name = "storyId")
    private String storyId;

    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private String count;

    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus;

    @Column(name="status")
    private boolean status;

    @Column(name = "created_date")
    private LocalDateTime createAt;

    @Column(name = "destroy_date")
    private LocalDateTime destroyAt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sessionId")
    private PokerSessionEntity pokerSessionEntity;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<PokerMemberUserStoryEntity> PokerMemberUserStoryEntity;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public VotingStatus getVotingStatus() {
        return votingStatus;
    }

    public void setVotingStatus(VotingStatus votingStatus) {
        this.votingStatus = votingStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getDestroyAt() {
        return destroyAt;
    }

    public void setDestroyAt(LocalDateTime destroyAt) {
        this.destroyAt = destroyAt;
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

