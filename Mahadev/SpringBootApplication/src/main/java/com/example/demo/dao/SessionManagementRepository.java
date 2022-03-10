package com.example.demo.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PlanningSession;

public interface SessionManagementRepository extends JpaRepository<PlanningSession, UUID>{


}

