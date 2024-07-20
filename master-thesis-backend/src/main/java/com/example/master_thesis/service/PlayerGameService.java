package com.example.master_thesis.service;


import com.example.master_thesis.persistance.model.PlayerGame;
import com.example.master_thesis.persistance.repository.PlayerGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerGameService {
    private final PlayerGameRepository playerGameRepository;

    @Transactional
    public PlayerGame savePlayerGame(PlayerGame playerGame) {
        return playerGameRepository.save(playerGame);
    }

    public List<PlayerGame> getAllPlayerGames(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }
}
