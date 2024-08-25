package com.example.master_thesis.consumer.euroleague;

import com.example.master_thesis.api.EuroleagueApi;
import com.example.master_thesis.api.dto.PlayerStatisticsDto;
import com.example.master_thesis.api.mapper.PlayerGameMapper;
import com.example.master_thesis.consumer.event.ScrapingCompletedEvent;
import com.example.master_thesis.persistance.model.Game;
import com.example.master_thesis.service.GameService;
import com.example.master_thesis.service.PlayerGameService;
import com.example.master_thesis.service.PlayerService;
import com.example.master_thesis.service.SeasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EuroleagueApiConsumer {
    private final EuroleagueApi euroleagueApi;
    private final SeasonService seasonService;
    private final GameService gameService;
    private final PlayerService playerService;
    private final PlayerGameService playerGameService;
    private final EuroleagueApiProperties euroleagueApiProperties;
    private final ApplicationEventPublisher applicationEventPublisher;

    public static final int GAME_SCRAPER_LIMIT = 10000;
    public static final String PLAYER_NAME_SEPARATOR = ",";

    @EventListener(ApplicationReadyEvent.class)
    public void collectBoxScoreData() {
        log.info("Collecting boxscore data");
        var startingSeasonYear = euroleagueApiProperties.getStartingSeason();
        var collectedAdditionalData = false;
        while (true) {
            var currentSeason = seasonService.getFirstUncompletedSeason(startingSeasonYear);
            var collectedGamesNumber = collectGamesData(currentSeason.getYear(), currentSeason.getGamesCount());
            if (collectedGamesNumber <= 1) {
                log.info("Finished collecting boxscore data");
                break;
            }
            collectedAdditionalData = true;
            currentSeason.setGamesCount(collectedGamesNumber);
            currentSeason.setCompleted(true);
            seasonService.updateSeason(currentSeason);
            log.info("Finished collecting boxscore data for season year {}", currentSeason.getYear());
        }

        applicationEventPublisher.publishEvent(ScrapingCompletedEvent.builder()
                .hadAdditionalData(collectedAdditionalData)
                .build());
    }

    private int collectGamesData(int seasonYear, int gamesCount) {
        try {
            // Add logic to start from
            int startGameNumber = gamesCount == 0 ? 1 : gamesCount;
            for (int gameNumber = startGameNumber; gameNumber < GAME_SCRAPER_LIMIT; gameNumber++) {
                var boxscoreDto = euroleagueApi.getBoxScore(gameNumber, euroleagueApiProperties.getPrefix() + seasonYear);
                if (boxscoreDto == null)
                    return gameNumber;

                var homeTeamStatistics = boxscoreDto.getStatistics().getFirst();
                var awayTeamStatistics = boxscoreDto.getStatistics().getLast();
                var game = gameService.createGame(boxscoreDto.getAttendance(), seasonYear, homeTeamStatistics.getTeam(), awayTeamStatistics.getTeam());
                homeTeamStatistics.getPlayersStatistics().forEach(player -> collectPlayerData(game, player));
                awayTeamStatistics.getPlayersStatistics().forEach(player -> collectPlayerData(game, player));
            }
            return 0;
        } catch (final Exception exception) {
            log.error(exception.getMessage(), exception);
            return 0;
        }
    }

    private void collectPlayerData(Game game, PlayerStatisticsDto playerStatistics) {
        String[] playerName = playerStatistics.getPlayer().split(PLAYER_NAME_SEPARATOR);
        var firstName = playerName[1];
        var lastName = playerName[0];
        var player = playerService.createPlayer(playerStatistics.getPlayerId(), firstName, lastName);
        var playerGameData = PlayerGameMapper.INSTANCE.toEntity(playerStatistics, game, player);
        playerGameService.savePlayerGame(playerGameData);
    }
}
