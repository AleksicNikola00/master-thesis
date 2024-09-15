package com.example.master_thesis.elasticsearch.config;

public interface ElasticSearchConstants {
    String PLAYER_FIRST_NAME = "playerFirstName";
    String PLAYER_LAST_NAME = "playerLastName";
    String ARTICLE_CONTENT = "articleContent";
    String DEFAULT_ANALYZER = "english";
    String HIGHLIGHTER_PRE_TAG = "<mark>";
    String HIGHLIGHTER_POST_TAG = "</mark>";
    int HIGHLIGHTER_FRAGMENT_SIZE = 400;
}
