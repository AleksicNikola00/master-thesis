package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;

@Entity
@Table(name="basketball_player")
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
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
}
