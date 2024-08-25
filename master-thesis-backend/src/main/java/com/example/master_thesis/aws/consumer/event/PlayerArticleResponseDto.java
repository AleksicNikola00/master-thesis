package com.example.master_thesis.aws.consumer.event;

import lombok.Builder;

@Builder
public record PlayerArticleResponseDto(Long id, String articleContent, String articleUrl) {
}
