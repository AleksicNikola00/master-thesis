package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import com.example.master_thesis.aws.publisher.event.PlayerImageEventRequestDto;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerImagePublisher extends BasePublisher {

    public PlayerImagePublisher(AwsLocalStackProperties properties, SqsTemplate sqsTemplate) {
        super(sqsTemplate, properties);
    }

    public void publishToQue(PlayerImageEventRequestDto playerImageEventRequestDto) {
        sendToQueue(properties.getEuroleaguePlayersImageRequest(), playerImageEventRequestDto);
    }
}
