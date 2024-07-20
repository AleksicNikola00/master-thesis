package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import com.example.master_thesis.aws.publisher.dto.PlayerDto;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerDetailsPublisher {
    private final SqsTemplate template;
    private final AwsLocalStackProperties properties;

    public void publishToQue(PlayerDto playerDto) {
        template.send(to -> to.queue(properties.getEuroleaguePlayers()).payload(playerDto));
    }
}
