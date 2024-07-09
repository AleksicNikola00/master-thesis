package com.example.master_thesis.scheduler;

import com.example.master_thesis.api.EuroleagueApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EuroleagueApiScheduler {
    public static final String[] SEASON_CODES = {"E2023", "E2022"};

    private final EuroleagueApi euroleagueApi;

    @Scheduled(fixedDelay = 5000)
    public void collectBoxScoreData() {
        var response = euroleagueApi.getBoxScore("1", SEASON_CODES[0]);
        log.info(response.toString());
    }
}
