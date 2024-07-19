package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Season;
import com.example.master_thesis.persistance.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Transactional
    public Season getFirstUncompletedSeason(int year) {
        var optionalUncompletedSeason = seasonRepository.findFirstByIsCompletedFalseAndYearGreaterThanEqual(year);
        if (optionalUncompletedSeason.isPresent()) return optionalUncompletedSeason.get();

        var newSeason = Season.builder()
                .year(year)
                .gamesCount(0)
                .isCompleted(false)
                .build();

        return seasonRepository.save(newSeason);
    }
}
