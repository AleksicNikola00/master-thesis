package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.PlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long> {
}
