package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Player;
import com.example.master_thesis.persistance.repository.PlayerRepository;
import com.example.master_thesis.utils.NumberUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGameService playerGameService;

    public static final int PLAYER_PAGE_SIZE = 10;

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

    public void calculatePlayersAverages() {
        for (int pageNumber = 0; pageNumber < Integer.MAX_VALUE; pageNumber++) {
            var players = playerRepository.findByOrderByIdAsc(PageRequest.of(pageNumber, PLAYER_PAGE_SIZE));
            if (players.isEmpty()) {
                log.info("Player averages calculated!");
                break;
            }
            players.forEach(this::calculatePlayerAverages);
            playerRepository.saveAll(players);
        }
    }

    private void calculatePlayerAverages(final Player player) {
        var playerGames = playerGameService.getAllPlayerGames(player.getId());
        if (playerGames.isEmpty()) {
            log.info("Player with id [{}] hasn't played any games", player.getId());
            return;
        }
        var totalGames = playerGames.size();
        var totalMinutes = 0;
        var totalPoints = 0;
        var total2PointAttempted = 0;
        var total2PointsMade = 0;
        var total3PointsMade = 0;
        var total3PointsAttempted = 0;
        var totalFtAttempted = 0;
        var totalFtMade = 0;
        var totalAssists = 0;
        var totalSteals = 0;
        var totalTOs = 0;
        var totalRebounds = 0;
        var totalOffensiveRebounds = 0;
        var totalDefensiveRebounds = 0;
        var totalPlusMinus = 0;

        for (var playerGame : playerGames) {
            totalMinutes += playerGame.getMinutes();
            totalPoints += playerGame.getPoints();
            total2PointAttempted += playerGame.get_2PointsAttempted();
            total2PointsMade += playerGame.get_2PointsMade();
            total3PointsMade += playerGame.get_3PointsMade();
            total3PointsAttempted += playerGame.get_3PointsAttempted();
            totalFtAttempted += playerGame.getFtAttempted();
            totalFtMade += playerGame.getFtMade();
            totalAssists += playerGame.getAssists();
            totalSteals += playerGame.getSteals();
            totalTOs += playerGame.getTurnovers();
            totalRebounds += playerGame.getRebounds();
            totalOffensiveRebounds += playerGame.getOffensiveRebounds();
            totalDefensiveRebounds += playerGame.getDefensiveRebounds();
            totalPlusMinus += playerGame.getPlusMinus();
        }

        player.setAverageMinutes(NumberUtils.round((double) totalMinutes / totalGames, 2));
        player.setAveragePoints(NumberUtils.round((double) totalPoints / totalGames, 2));
        player.setAverage2PointsAttempted(NumberUtils.round((double) total2PointAttempted / totalGames, 2));
        player.setAverage2PointsMade(NumberUtils.round((double) total2PointsMade / totalGames, 2));
        player.setAverage3PointsMade(NumberUtils.round((double) total3PointsMade / totalGames, 2));
        player.setAverage3PointsAttempted(NumberUtils.round((double) total3PointsAttempted / totalGames, 2));
        player.setAverageFtAttempted(NumberUtils.round((double) totalFtAttempted / totalGames, 2));
        player.setAverageFtMade(NumberUtils.round((double) totalFtMade / totalGames, 2));
        player.setAverageAssists(NumberUtils.round((double) totalAssists / totalGames, 2));
        player.setAverageSteals(NumberUtils.round((double) totalSteals / totalGames, 2));
        player.setAverageTurnovers(NumberUtils.round((double) totalTOs / totalGames, 2));
        player.setAverageRebounds(NumberUtils.round((double) totalRebounds / totalGames, 2));
        player.setAverageOffensiveRebounds(NumberUtils.round((double) totalOffensiveRebounds / totalGames, 2));
        player.setAverageDefensiveRebounds(NumberUtils.round((double) totalDefensiveRebounds / totalGames, 2));
        player.setAveragePlusMinus(NumberUtils.round((double) totalPlusMinus / totalGames, 2));
    }

    @Transactional
    public void updatePlayerImage(Long id, String imageUrl) {
        var player = playerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        player.setImageUrl(imageUrl);
        playerRepository.save(player);
    }
}
