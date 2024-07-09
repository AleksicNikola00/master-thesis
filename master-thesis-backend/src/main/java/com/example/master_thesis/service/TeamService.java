package com.example.master_thesis.service;

import com.example.master_thesis.persistance.model.Team;
import com.example.master_thesis.persistance.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team createTeam(String name) {
        Optional<Team> optionalExistingTeam = teamRepository.findByName(name);
        if (optionalExistingTeam.isPresent()) return optionalExistingTeam.get();

        Team newTeam = Team.builder().name(name).build();
        return teamRepository.save(newTeam);
    }
}
