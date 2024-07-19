package com.example.master_thesis.scheduler;

import com.example.master_thesis.api.EuroleagueApi;
import com.example.master_thesis.persistance.model.Season;
import com.example.master_thesis.service.SeasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EuroleagueApiScheduler {
    private final EuroleagueApi euroleagueApi;
    private final SeasonService seasonService;
    private final EuroleagueScraperProperties euroleagueScraperProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void collectBoxScoreData() {
        log.info("Collecting boxscore data");
        for (int currentSeasonYear = euroleagueScraperProperties.getStartingSeason(); currentSeasonYear < Integer.MAX_VALUE; currentSeasonYear++) {
            Season currentSeason = seasonService.getFirstUncompletedSeason(currentSeasonYear);
            log.info("Current season: {}", currentSeason);
        }
    }


}
