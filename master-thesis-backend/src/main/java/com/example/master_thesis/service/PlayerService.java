package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.persistance.model.player.projection.PlayerBaseInfoProjection;
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

    public int PLAYER_PAGE_COUNT = 10;
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

    public List<Player> getPlayersWithoutArticles(int page, int count) {
        return playerRepository.findByNoArticles(PageRequest.of(page, count));
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

    public List<PlayerBaseInfoProjection> searchPlayerByNamePageable(String playerName, Integer page) {
        return playerRepository.findByName(playerName.toLowerCase(), PageRequest.of(page, PLAYER_PAGE_COUNT));
    }
}
