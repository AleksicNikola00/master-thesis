package com.example.master_thesis.controller;

import com.example.master_thesis.elasticsearch.persistence.Article;
import com.example.master_thesis.elasticsearch.service.ArticleService;
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
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public ResponseEntity<SearchHits<Article>> getArticleHits(@RequestParam String query) {
        return ResponseEntity.ok(articleService.getByQuery(query));
    }
}
