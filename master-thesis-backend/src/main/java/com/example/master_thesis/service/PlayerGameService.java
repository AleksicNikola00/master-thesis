package com.example.master_thesis.service;


import com.example.master_thesis.persistance.model.PlayerGame;
import com.example.master_thesis.persistance.repository.PlayerGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerGameService {
    private final PlayerGameRepository playerGameRepository;

    @Transactional
    public PlayerGame savePlayerGame(PlayerGame playerGame) {
        return playerGameRepository.save(playerGame);
    }
}
