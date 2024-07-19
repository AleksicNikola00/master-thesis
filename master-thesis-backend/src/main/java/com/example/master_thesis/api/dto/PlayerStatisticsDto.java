package com.example.master_thesis.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlayerStatisticsDto {
    @JsonProperty("Player_ID")
    private String playerId;

    @JsonProperty("IsStarter")
    private int isStarter;

    @JsonProperty("IsPlaying")
    private int isPlaying;

    @JsonProperty("Team")
    private String team;

    @JsonProperty("Dorsal")
    private String dorsal;

    @JsonProperty("Player")
    private String player;

    @JsonProperty("Minutes")
    private String minutes;

    @JsonProperty("Points")
    private int points;

    @JsonProperty("FieldGoalsMade2")
    private int fieldGoalsMade2;

    @JsonProperty("FieldGoalsAttempted2")
    private int fieldGoalsAttempted2;

    @JsonProperty("FieldGoalsMade3")
    private int fieldGoalsMade3;

    @JsonProperty("FieldGoalsAttempted3")
    private int fieldGoalsAttempted3;

    @JsonProperty("FreeThrowsMade")
    private int freeThrowsMade;

    @JsonProperty("FreeThrowsAttempted")
    private int freeThrowsAttempted;

    @JsonProperty("OffensiveRebounds")
    private int offensiveRebounds;

    @JsonProperty("DefensiveRebounds")
    private int defensiveRebounds;

    @JsonProperty("TotalRebounds")
    private int totalRebounds;

    @JsonProperty("Assistances")
    private int assists;

    @JsonProperty("Steals")
    private int steals;

    @JsonProperty("Turnovers")
    private int turnovers;

    @JsonProperty("BlocksFavour")
    private int blocksFavour;

    @JsonProperty("BlocksAgainst")
    private int blocksAgainst;

    @JsonProperty("FoulsCommited")
    private int foulsCommited;

    @JsonProperty("FoulsReceived")
    private int foulsReceived;

    @JsonProperty("Valuation")
    private int valuation;

    @JsonProperty("Plusminus")
    private int plusMinus;
}