package com.example.master_thesis.aws.consumer.event;

import lombok.Builder;

@Builder
public record PlayerImageEventDto(Long id, String imageUrl) {
}
