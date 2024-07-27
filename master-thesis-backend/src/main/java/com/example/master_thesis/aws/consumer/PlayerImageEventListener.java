package com.example.master_thesis.aws.consumer;

import com.example.master_thesis.aws.consumer.event.PlayerImageEventDto;
import com.example.master_thesis.service.PlayerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerImageEventListener {

    private final PlayerService playerService;

    @SqsListener("${localstack.sqs.euroleague-players-response}")
    public void playerImageEventHandler(PlayerImageEventDto playerImageEventDto) {
        playerService.updatePlayerImage(playerImageEventDto.id(), playerImageEventDto.imageUrl());
    }
}
