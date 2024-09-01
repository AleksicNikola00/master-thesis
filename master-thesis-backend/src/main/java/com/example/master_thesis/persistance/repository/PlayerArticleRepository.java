package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.player.PlayerArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerArticleRepository extends JpaRepository<PlayerArticle, Long> {
    @Query("SELECT CASE WHEN COUNT(pa) > 0 THEN true ELSE false END FROM PlayerArticle pa WHERE pa.articleUrl = :articleUrl and pa.player.id = :playerId")
    Boolean checkIfAlreadyExists(Long playerId, String articleUrl);
}
