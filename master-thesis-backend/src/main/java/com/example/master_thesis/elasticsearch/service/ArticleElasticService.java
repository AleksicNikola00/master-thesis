package com.example.master_thesis.elasticsearch.service;

import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import com.example.master_thesis.elasticsearch.persistence.ArticleElastic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightParameters;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.master_thesis.elasticsearch.config.ElasticSearchConstants.*;

@Service
@RequiredArgsConstructor
public class ArticleElasticService {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchHits<ArticleElastic> getByQuery(String query) {
        var parameters = HighlightParameters
                .builder()
                .withPreTags(HIGHLIGHTER_PRE_TAG)
                .withPostTags(HIGHLIGHTER_POST_TAG)
                .withFragmentSize(HIGHLIGHTER_FRAGMENT_SIZE)
                .withNumberOfFragments(1)
                .build();

        var multiMatchQuery = NativeQuery.builder()
                .withQuery(q -> q.multiMatch(
                        mm -> mm.query(query)
                                .fields(PLAYER_FIRST_NAME + "^2",
                                        PLAYER_LAST_NAME + "^2",
                                        ARTICLE_CONTENT)
                                .type(TextQueryType.BestFields)
                                .analyzer(DEFAULT_ANALYZER)
                ))
                .withHighlightQuery(
                        new HighlightQuery(
                                new Highlight(
                                        parameters,
                                        Arrays.asList(
                                                new HighlightField(PLAYER_FIRST_NAME),
                                                new HighlightField(PLAYER_LAST_NAME),
                                                new HighlightField(ARTICLE_CONTENT)
                                        )
                                ), ArticleElastic.class
                        )
                )
                .build();

        return elasticsearchOperations.search(multiMatchQuery, ArticleElastic.class);
    }

}
