package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basketball_team")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
