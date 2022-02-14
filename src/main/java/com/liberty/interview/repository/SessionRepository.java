package com.liberty.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberty.interview.domain.Session;

public interface SessionRepository extends JpaRepository<Session, String> {

}
