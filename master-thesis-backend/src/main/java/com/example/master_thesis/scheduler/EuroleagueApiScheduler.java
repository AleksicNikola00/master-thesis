package com.example.master_thesis.scheduler;

import com.example.master_thesis.api.EuroleagueApi;
import com.example.master_thesis.api.dto.BoxscoreDto;
import com.example.master_thesis.api.dto.TeamStatisticsDto;
import com.example.master_thesis.persistance.model.Game;
import com.example.master_thesis.persistance.model.Team;
import com.example.master_thesis.service.GameService;
import com.example.master_thesis.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EuroleagueApiScheduler {
    public static final String[] SEASON_CODES = {"E2023", "E2022"};;

    private final EuroleagueApi euroleagueApi;
    private final GameService gameService;

    @EventListener(ApplicationReadyEvent.class)
    public void collectBoxScoreData() {
       for (String seasonCode : SEASON_CODES) {
           log.info("Started collecting box score data for season {}", seasonCode);
           for (int gameNumber = 1; gameNumber < Integer.MAX_VALUE; gameNumber++) {
               BoxscoreDto boxscoreDto = euroleagueApi.getBoxScore(gameNumber, seasonCode);
                if(boxscoreDto == null) break;
                TeamStatisticsDto homeTeamStatistics = boxscoreDto.getStatistics().getFirst();
                TeamStatisticsDto awayTeamStatistics = boxscoreDto.getStatistics().get(1);
                int attendance = boxscoreDto.getAttendance();
                int seasonNumber = getSeasonNumber(seasonCode);
                try {
                    Game game = gameService.createGame(attendance, seasonNumber, homeTeamStatistics.getTeam(), awayTeamStatistics.getTeam());
                } catch (Exception e) {
                    log.error("Error while parsing season {} game {}", seasonNumber, gameNumber, e);
                }
           }
           log.info("Successfully collected box score data for season {}", seasonCode);
       }
       //TODO Calculate for each player
    }

    private int getSeasonNumber(String seasonCode) {
        if(seasonCode == null || seasonCode.length() <= 1) return -1;
        return Integer.parseInt(seasonCode.substring(1));
    }

}
