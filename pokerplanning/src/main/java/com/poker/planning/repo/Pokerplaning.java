package com.poker.planning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poker.planning.entity.UserStrories;

public interface Pokerplaning extends JpaRepository<UserStrories,String> {

}