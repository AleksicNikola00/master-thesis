package com.example.master_thesis.controller;

import com.example.master_thesis.elasticsearch.persistence.ArticleElastic;
import com.example.master_thesis.elasticsearch.service.ArticleElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleElasticController {
    private final ArticleElasticService articleElasticService;

    @GetMapping()
    public ResponseEntity<SearchHits<ArticleElastic>> getArticleHits(@RequestParam String query) {
        return ResponseEntity.ok(articleElasticService.getByQuery(query));
    }
}
