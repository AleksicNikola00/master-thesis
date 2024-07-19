package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basketball_season")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int gamesCount;
    private int year;
    private boolean isCompleted;
}
