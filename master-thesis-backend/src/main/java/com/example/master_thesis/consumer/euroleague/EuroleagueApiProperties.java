package com.example.master_thesis.consumer.euroleague;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("euroleague.api")
@Data
public class EuroleagueApiProperties {
    private String prefix;
    private int startingSeason;
}