package com.example.master_thesis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.master_thesis.elasticsearch.persistence")
public class ElasticSearchConfig {
}
