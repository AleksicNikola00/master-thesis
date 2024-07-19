package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEuroleagueId(String euroleagueId);
}
