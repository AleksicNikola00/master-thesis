package com.example.master_thesis.elasticsearch.service;

import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import com.example.master_thesis.elasticsearch.persistence.Article;
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

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchHits<Article> getByQuery(String query) {
        var parameters = HighlightParameters
                .builder()
                .withPreTags("<b>")
                .withPostTags("</b>")
                .withFragmentSize(200)
                .withNumberOfFragments(1)
                .build();

        var multiMatchQuery = NativeQuery.builder()
                .withQuery(q -> q.multiMatch(
                        mm -> mm.query(query)
                                .fields("firstName^2", "lastName^2", "articleContent")
                                .type(TextQueryType.BestFields)
                                .analyzer("english")
                ))
                .withHighlightQuery(
                        new HighlightQuery(
                                new Highlight(
                                        parameters,
                                        Arrays.asList(
                                                new HighlightField("firstName"),
                                                new HighlightField("lastName"),
                                                new HighlightField("articleContent")
                                        )
                                ), Article.class
                        )
                )
                .build();

        return elasticsearchOperations.search(multiMatchQuery, Article.class);
    }


}
