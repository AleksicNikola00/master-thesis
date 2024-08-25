package com.example.master_thesis.aws.publisher.event;

import lombok.Builder;

@Builder
public record PlayerArticleEventRequestDto(Long id, String firstName, String lastName) {
}
