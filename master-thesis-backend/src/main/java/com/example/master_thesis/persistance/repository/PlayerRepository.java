package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.persistance.model.player.projection.PlayerBaseInfoProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEuroleagueId(String euroleagueId);

    List<Player> findByOrderByIdAsc(Pageable pageable);

    List<Player> findByImageUrlIsNullOrderByIdAsc(Pageable pageable);

    @Query("select p from Player p where p.articleCount=0")
    List<Player> findByNoArticles(Pageable pageable);

    @Query("""
            SELECT p.id AS id, p.firstName AS firstName, p.lastName AS lastName, p.imageUrl AS imageUrl
            FROM Player p
            WHERE LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE %:name%
               OR LOWER(p.firstName) LIKE %:name%
               OR LOWER(p.lastName) LIKE %:name%
            """)
    List<PlayerBaseInfoProjection> findByName(String name, Pageable pageable);
}
