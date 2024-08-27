package com.example.master_thesis.elasticsearch.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "article")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @Field(type = FieldType.Keyword, index = false)
    private String id;

    @Field(type = FieldType.Long)
    private Long playerId;

    @Field(type = FieldType.Text, searchAnalyzer = "english", analyzer = "english")
    private String playerFirstName;

    @Field(type = FieldType.Text, searchAnalyzer = "english", analyzer = "english")
    private String playerLastName;

    @Field(type = FieldType.Keyword, index = false)
    private String articleUrl;

    @Field(type = FieldType.Text, searchAnalyzer = "english", analyzer = "english")
    private String articleContent;
}
