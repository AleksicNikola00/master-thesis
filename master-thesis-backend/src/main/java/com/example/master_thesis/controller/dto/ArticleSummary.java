package com.example.master_thesis.controller.dto;

public record ArticleSummary(
        String id,
        String playerFirstName,
        String playerLastName,
        String playerImageUrl,
        String articleContentSummary
) {

}
