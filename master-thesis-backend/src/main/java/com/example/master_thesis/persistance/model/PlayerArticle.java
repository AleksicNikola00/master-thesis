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
    private Player player;

    private String articleId;

    private String articleUrl;
}
