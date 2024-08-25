package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import com.example.master_thesis.aws.publisher.event.PlayerArticleEventRequestDto;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerArticlePublisher extends BasePublisher {
    public PlayerArticlePublisher(SqsTemplate template, AwsLocalStackProperties properties) {
        super(template, properties);
    }

    public void publishToQue(PlayerArticleEventRequestDto playerArticleEventRequestDto) {
        sendToQueue(properties.getEuroleaguePlayersArticleRequest(), playerArticleEventRequestDto);
    }
}
