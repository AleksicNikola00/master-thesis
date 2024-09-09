package com.example.master_thesis.controller.mapper;

import com.example.master_thesis.controller.dto.ArticleSummary;
import com.example.master_thesis.elasticsearch.persistence.ArticleElastic;
import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.persistance.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ArticleSummaryMapper {
    @Autowired
    protected PlayerRepository playerRepository;
    final protected int SUMMARY_LENGTH = 200;

    public abstract List<ArticleSummary> convertArticlesElasticToArticlesSummary(List<ArticleElastic> articleElastics);

    @Mapping(source = "articleElastic.playerId", target = "playerImageUrl", qualifiedByName = "getPlayerImageUrl")
    @Mapping(source = "articleElastic.articleContent", target = "articleContentSummary", qualifiedByName = "getArticleContentSummary")
    abstract ArticleSummary articleElasticToArticleSummary(ArticleElastic articleElastic);

    @Named("getPlayerImageUrl")
    protected String getPlayerImageUrl(Long playerId) {
        return playerRepository.findById(playerId).map(Player::getImageUrl)
                .orElse("");
    }

    @Named("getArticleContentSummary")
    protected String getArticleContentSummary(String articleContent) {
        return articleContent.substring(0, SUMMARY_LENGTH);
    }
}
