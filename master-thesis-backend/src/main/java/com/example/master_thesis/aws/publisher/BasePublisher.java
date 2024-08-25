package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class BasePublisher {
    protected final SqsTemplate template;
    protected final AwsLocalStackProperties properties;

    protected void sendToQueue(String queueName, Object payload) {
        template.send(queueName, payload);
    }
}
