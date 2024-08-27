package com.example.master_thesis.service;

import com.example.master_thesis.elasticsearch.persistence.Article;
import com.example.master_thesis.elasticsearch.persistence.ArticleRepository;
import com.example.master_thesis.persistance.model.PlayerArticle;
import com.example.master_thesis.persistance.repository.PlayerArticleRepository;
import com.example.master_thesis.persistance.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerArticleService {
    private final PlayerArticleRepository playerArticleRepository;
    private final PlayerRepository playerRepository;
    private final ArticleRepository articleRepository;

    public void addPlayerArticle(Long playerId, String articleContent, String articleUrl) {
        if (playerArticleRepository.checkIfAlreadyExists(playerId, articleUrl)) {
            log.warn("Player article already exists with id [{}] and url [{}]", playerId, articleUrl);
            return;
        }

        var player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
        var playerArticle = PlayerArticle.builder()
                .player(player)
                .articleUrl(articleUrl)
                .build();

        var article = Article.builder()
                .playerFirstName(player.getFirstName())
                .playerLastName(player.getLastName())
                .articleContent(articleContent)
                .articleUrl(articleUrl)
                .build();

        articleRepository.save(article);

        log.info("Article url for player with id [{}]: {}", playerId, articleUrl);
        playerArticleRepository.save(playerArticle);
    }
}
