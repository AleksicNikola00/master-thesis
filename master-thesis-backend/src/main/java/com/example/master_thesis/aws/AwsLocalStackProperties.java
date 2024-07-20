package com.example.master_thesis.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("localstack.sqs")
@Data
public class AwsLocalStackProperties {
    private String euroleaguePlayers;
}
