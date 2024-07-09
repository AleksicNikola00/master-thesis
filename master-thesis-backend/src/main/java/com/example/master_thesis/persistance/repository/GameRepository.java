package com.example.master_thesis.persistance.repository;

import com.example.master_thesis.persistance.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
