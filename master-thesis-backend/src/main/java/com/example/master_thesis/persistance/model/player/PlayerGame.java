package com.example.master_thesis.persistance.model.player;

import com.example.master_thesis.persistance.model.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "basketball_player_game")
@Getter
@Setter
public class PlayerGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private int minutes;
    private int points;
    private int _2PointsAttempted;
    private int _2PointsMade;
    private int _3PointsAttempted;
    private int _3PointsMade;
    private int ftAttempted;
    private int ftMade;
    private int assists;
    private int steals;
    private int turnovers;
    private int rebounds;
    private int offensiveRebounds;
    private int defensiveRebounds;
    private int plusMinus;
}
