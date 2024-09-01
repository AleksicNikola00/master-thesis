package com.example.master_thesis.controller;

import com.example.master_thesis.controller.dto.PlayerResponseDto;
import com.example.master_thesis.controller.mapper.PlayerMapper;
import com.example.master_thesis.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getPlayers(@RequestParam String playerName, @RequestParam(defaultValue = "0",
            required = false) Integer page) {
        var players = playerService.searchPlayerByNamePageable(playerName, page);
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toResponseDtos(players));
    }
}
