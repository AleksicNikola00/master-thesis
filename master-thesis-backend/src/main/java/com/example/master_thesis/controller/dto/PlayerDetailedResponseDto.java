package com.example.master_thesis.controller.dto;

public record PlayerDetailedResponseDto(
        Long id,
        String firstName,
        String lastName,
        String imageUrl,
        Double averageMinutes,
        Double averagePoints,
        Double averageAssists,
        Double averageSteals,
        Double averageTurnovers,
        Double averageRebounds
) {
}
