package com.example.master_thesis.persistance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basketball_player_article")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;
    String articleUrl;
}
