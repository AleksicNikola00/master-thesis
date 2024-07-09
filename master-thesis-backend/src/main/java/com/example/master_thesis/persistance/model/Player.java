package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="basketball_player")
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double averageMinutes;
    private double averagePoints;
    private double average2PointsAttempted;
    private double average2PointsMade;
    private double average3PointsAttempted;
    private double average3PointsMade;
    private double averageFtAttempted;
    private double averageFtMade;
    private double averageAssists;
    private double averageSteals;
    private double averageTurnovers;
    private double averageRebounds;
    private double averageOffensiveRebounds;
    private double averageDefensiveRebounds;
    private double averagePlusMinus;
    @ManyToMany
    private List<Game> games;
}
