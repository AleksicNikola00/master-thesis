package com.example.master_thesis.service;

import com.example.master_thesis.elasticsearch.persistence.ArticleElastic;
import com.example.master_thesis.elasticsearch.persistence.ArticleElasticRepository;
import com.example.master_thesis.persistance.model.player.PlayerArticle;
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
    private final ArticleElasticRepository articleElasticRepository;

    public void addPlayerArticle(Long playerId, String articleContent, String articleUrl) {
        if (playerArticleRepository.checkIfAlreadyExists(playerId, articleUrl)) {
            log.warn("Player article already exists with id [{}] and url [{}]", playerId, articleUrl);
            return;
        }

        var player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);


        var article = ArticleElastic.builder()
                .playerId(playerId)
                .playerFirstName(player.getFirstName())
                .playerLastName(player.getLastName())
                .articleContent(articleContent)
                .articleUrl(articleUrl)
                .build();

        var savedArticle = articleElasticRepository.save(article);

        var playerArticle = PlayerArticle.builder()
                .player(player)
                .articleUrl(articleUrl)
                .articleId(savedArticle.getId())
                .build();

        playerArticleRepository.save(playerArticle);
    }
}
