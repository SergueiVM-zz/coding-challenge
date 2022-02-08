package com.example.demo.repository;

import com.example.demo.model.Memebers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Memebers, Integer> {
    Optional<List<Memebers>> findByIdSession(String idSession);
    Optional<Void> deleteByIdSession(String idSession);
}
