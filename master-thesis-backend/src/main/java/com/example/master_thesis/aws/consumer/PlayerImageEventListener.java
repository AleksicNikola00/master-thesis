package com.example.master_thesis.aws.consumer;

import com.example.master_thesis.aws.consumer.event.PlayerImageEventResponseDto;
import com.example.master_thesis.service.PlayerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerImageEventListener {

    private final PlayerService playerService;

    @SqsListener("${localstack.sqs.euroleague-players-image-response}")
    public void playerImageEventHandler(PlayerImageEventResponseDto playerImageEventResponseDto) {
        playerService.updatePlayerImage(playerImageEventResponseDto.id(), playerImageEventResponseDto.imageUrl());
    }
}
