package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import com.example.master_thesis.aws.publisher.event.PlayerEventDto;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailsPublisher extends BasePublisher {
    private final AwsLocalStackProperties properties;

    public PlayerDetailsPublisher(AwsLocalStackProperties properties, SqsTemplate sqsTemplate) {
        super(sqsTemplate);
        this.properties = properties;
    }

    public void publishToQue(PlayerEventDto playerEventDto) {
        sendToQueue(properties.getEuroleaguePlayersRequest(), playerEventDto);
    }
}
