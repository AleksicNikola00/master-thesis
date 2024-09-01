package com.example.master_thesis.elasticsearch.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static com.example.master_thesis.elasticsearch.config.ElasticSearchConstants.DEFAULT_ANALYZER;

@Document(indexName = "article")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleElastic {
    @Id
    @Field(type = FieldType.Keyword, index = false)
    private String id;

    @Field(type = FieldType.Long)
    private Long playerId;

    @Field(type = FieldType.Text, searchAnalyzer = DEFAULT_ANALYZER, analyzer = DEFAULT_ANALYZER)
    private String playerFirstName;

    @Field(type = FieldType.Text, searchAnalyzer = DEFAULT_ANALYZER, analyzer = DEFAULT_ANALYZER)
    private String playerLastName;

    @Field(type = FieldType.Keyword, index = false)
    private String articleUrl;

    @Field(type = FieldType.Text, searchAnalyzer = DEFAULT_ANALYZER, analyzer = DEFAULT_ANALYZER)
    private String articleContent;
}
