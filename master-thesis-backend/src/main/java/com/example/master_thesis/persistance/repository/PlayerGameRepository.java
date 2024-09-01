package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.player.PlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long> {
    //This can be paginated if needed
    List<PlayerGame> findByPlayerId(Long playerId);
}
