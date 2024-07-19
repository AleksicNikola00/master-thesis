package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Optional<Season> findFirstByIsCompletedFalseAndYearGreaterThanEqual(int year);
}
