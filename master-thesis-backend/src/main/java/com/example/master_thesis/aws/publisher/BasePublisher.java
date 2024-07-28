package com.example.master_thesis.aws.publisher;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class BasePublisher {
    protected final SqsTemplate template;

    protected void sendToQueue(String queueName, Object payload) {
        template.send(queueName, payload);
    }
}
