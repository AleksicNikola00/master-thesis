package com.example.master_thesis.elasticsearch.service;

import com.example.master_thesis.elasticsearch.persistence.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;


}
