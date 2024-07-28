package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Player;
import com.example.master_thesis.persistance.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<Player> getPlayersWithoutImage(int page, int count) {
        return playerRepository.findByImageUrlIsNullOrderByIdAsc(PageRequest.of(page, count));
    }

    public List<Player> getPlayersPageable(int page, int count) {
        return playerRepository.findByOrderByIdAsc(PageRequest.of(page, count));
    }

    public void saveAllPlayers(List<Player> players) {
        playerRepository.saveAll(players);
    }

    public void updatePlayerImage(Long id, String imageUrl) {
        var player = playerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        player.setImageUrl(imageUrl);
        playerRepository.save(player);
    }
}
