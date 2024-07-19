package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Season;
import com.example.master_thesis.persistance.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Transactional
    public Season getFirstUncompletedSeason(int year) {
        var optionalUncompletedSeason = seasonRepository.findTopByOrderByYearDesc();
        if (optionalUncompletedSeason.isPresent()) {
            var existingSeason = optionalUncompletedSeason.get();
            //If the last season is completed start from next year
            if (existingSeason.isCompleted())
                year = existingSeason.getYear() + 1;
            else return existingSeason;
        }

        var newSeason = Season.builder()
                .year(year)
                .gamesCount(0)
                .isCompleted(false)
                .build();

        return seasonRepository.save(newSeason);
    }

    @Transactional
    public Season updateSeason(Season season) {
        return seasonRepository.save(season);
    }

    public Season getSeasonByYear(int year) {
        return seasonRepository.findByYear(year).orElseThrow(EntityNotFoundException::new);
    }
}
