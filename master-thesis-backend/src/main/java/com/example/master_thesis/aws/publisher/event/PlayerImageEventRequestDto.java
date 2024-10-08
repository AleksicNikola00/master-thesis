package com.example.master_thesis.aws.publisher.event;

import lombok.Builder;

@Builder
public record PlayerImageEventRequestDto(Long id, String firstName, String lastName, String euroleagueId) {
}
