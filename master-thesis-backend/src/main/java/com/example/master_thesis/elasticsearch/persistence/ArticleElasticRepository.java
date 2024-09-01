package com.example.master_thesis.elasticsearch.persistence;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleElasticRepository extends ElasticsearchRepository<ArticleElastic, String> {
}
