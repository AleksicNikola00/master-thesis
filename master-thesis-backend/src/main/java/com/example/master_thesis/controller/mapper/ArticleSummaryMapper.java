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
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

import static com.example.master_thesis.elasticsearch.config.ElasticSearchConstants.ARTICLE_CONTENT;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ArticleSummaryMapper {
    @Autowired
    protected PlayerRepository playerRepository;
    final protected int SUMMARY_LENGTH = 400;
    final protected String HIGHLIGHT_DELIMITER = "...";

    public abstract List<ArticleSummary> articleSearchHitsToArticleSummaries(List<SearchHit<ArticleElastic>> articleElastics);

    @Mapping(source = "articleSearchHit.content.id", target = "id")
    @Mapping(source = "articleSearchHit.content.playerFirstName", target = "playerFirstName")
    @Mapping(source = "articleSearchHit.content.playerLastName", target = "playerLastName")
    @Mapping(source = "articleSearchHit.content.playerId", target = "playerImageUrl", qualifiedByName = "getPlayerImageUrl")
    @Mapping(source = "articleSearchHit", target = "articleContentSummary", qualifiedByName = "getArticleContentSummary")
    abstract ArticleSummary articleSearchHitToArticleSummary(SearchHit<ArticleElastic> articleSearchHit);


    @Named("getPlayerImageUrl")
    protected String getPlayerImageUrl(Long playerId) {
        return playerRepository.findById(playerId).map(Player::getImageUrl)
                .orElse("");
    }

    @Named("getArticleContentSummary")
    protected String getArticleContentSummary(SearchHit<ArticleElastic> articleSearchHit) {
        var hitHighlightField = articleSearchHit.getHighlightField(ARTICLE_CONTENT);
        if (hitHighlightField.isEmpty())
            return articleSearchHit.getContent().getArticleContent().substring(0, SUMMARY_LENGTH);

        return String.join(HIGHLIGHT_DELIMITER, hitHighlightField);
    }
}
