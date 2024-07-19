package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Player;
import com.example.master_thesis.persistance.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player createPlayer(String euroleagueId, String firstname, String lastname) {
        var player = playerRepository.findByEuroleagueId(euroleagueId).orElse(Player.builder()
                .euroleagueId(euroleagueId)
                .build());

        if (!firstname.equals(player.getFirstName()))
            player.setFirstName(firstname);
        if (!lastname.equals(player.getLastName()))
            player.setLastName(lastname);

        return playerRepository.save(player);
    }
}
