package com.example.master_thesis.controller;

import com.example.master_thesis.controller.dto.ArticleSummary;
import com.example.master_thesis.controller.mapper.ArticleSummaryMapper;
import com.example.master_thesis.elasticsearch.persistence.ArticleElastic;
import com.example.master_thesis.elasticsearch.service.ArticleElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleElasticController {
    private final ArticleElasticService articleElasticService;
    private final ArticleSummaryMapper articleSummaryMapper;

    @GetMapping()
    public ResponseEntity<SearchHits<ArticleElastic>> getArticleHits(@RequestParam String query) {
        return ResponseEntity.ok(articleElasticService.getByQuery(query));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ArticleSummary>> getPopularArticles(@RequestParam(defaultValue = "0", required = false) Integer page) {
        var searchHits = articleElasticService.getPopularArticles(page);
        var elasticArticles = searchHits
                .stream()
                .map(SearchHit::getContent)
                .toList();
        return ResponseEntity.ok(articleSummaryMapper.convertArticlesElasticToArticlesSummary(elasticArticles));
    }
}
