package com.example.master_thesis.controller.dto;

import lombok.Builder;

@Builder
public record PlayerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String imageUrl
) {
}
