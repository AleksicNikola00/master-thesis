package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Game;
import com.example.master_thesis.persistance.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final TeamService teamService;
    private final SeasonService seasonService;

    public Game createGame(int attendance, int seasonYear, String homeTeamName, String awayTeamName) {
        var homeTeam = teamService.createTeam(homeTeamName);
        var awayTeam = teamService.createTeam(awayTeamName);
        var season = seasonService.getSeasonByYear(seasonYear);

        var newGame = Game.builder()
                .attendance(attendance)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .season(season)
                .build();

        return gameRepository.save(newGame);
    }
}
