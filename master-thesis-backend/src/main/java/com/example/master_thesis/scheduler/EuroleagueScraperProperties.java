package com.example.master_thesis.scheduler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("euroleague.scraper")
@Data
public class EuroleagueScraperProperties {
    private String prefix;
    private int startingSeason;
}