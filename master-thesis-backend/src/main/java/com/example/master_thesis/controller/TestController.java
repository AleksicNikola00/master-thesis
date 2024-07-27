package com.example.master_thesis.controller;

import com.example.master_thesis.aws.publisher.PlayerDetailsPublisher;
import com.example.master_thesis.aws.publisher.event.PlayerEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PlayerDetailsPublisher playerDetailsPublisher;


    @PostMapping
    public ResponseEntity<Void> publishUserToQue(@RequestBody PlayerEventDto playerEventDto) {
        playerDetailsPublisher.publishToQue(playerEventDto);
        return ResponseEntity.accepted().build();
    }
}
