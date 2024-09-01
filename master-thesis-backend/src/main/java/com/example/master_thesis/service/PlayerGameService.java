package com.example.master_thesis.service;


import com.example.master_thesis.persistance.model.player.PlayerGame;
import com.example.master_thesis.persistance.repository.PlayerGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerGameService {
    private final PlayerGameRepository playerGameRepository;

    public void savePlayerGame(PlayerGame playerGame) {
        playerGameRepository.save(playerGame);
    }

    public List<PlayerGame> getAllPlayerGames(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }
}
