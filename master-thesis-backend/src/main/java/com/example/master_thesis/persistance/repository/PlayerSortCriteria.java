package com.example.master_thesis.persistance.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerSortCriteria {
    AVERAGE_MINUTES("averageMinutes"),
    AVERAGE_POINTS("averagePoints"),
    AVERAGE_ASSISTS("averageAssists"),
    AVERAGE_STEALS("averageSteals"),
    AVERAGE_TURNOVERS("averageTurnovers"),
    AVERAGE_REBOUNDS("averageRebounds");

    private final String fieldName;
}
