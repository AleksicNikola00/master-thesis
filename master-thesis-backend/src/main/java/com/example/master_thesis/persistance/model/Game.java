package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "basketball_game")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int attendance;
    private int season;
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
}
