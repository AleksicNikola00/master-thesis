package com.example.master_thesis.aws.publisher.dto;

import lombok.Builder;

@Builder
public record PlayerDto(String firstName, String lastName, String euroleagueId) {
}
