package com.example.master_thesis.aws.consumer;

import com.example.master_thesis.aws.consumer.event.PlayerArticleResponseDto;
import com.example.master_thesis.service.PlayerArticleService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerArticleEventListener {
    private final PlayerArticleService playerArticleService;

    @SqsListener("${localstack.sqs.euroleague-players-article-response}")
    public void playerArticleEventHandler(PlayerArticleResponseDto playerArticleResponseDto) {
        playerArticleService.addPlayerArticle(playerArticleResponseDto.id(), playerArticleResponseDto.articleContent(), playerArticleResponseDto.articleUrl());
    }
}
