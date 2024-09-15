package com.example.master_thesis.controller.dto;

public record ArticleDetails(
        String id,
        Long playerId,
        String playerImageUrl,
        String playerFirstName,
        String playerLastName,
        String articleUrl,
        String articleContent
) {
}
