package com.example.master_thesis.controller;

import com.example.master_thesis.aws.publisher.PlayerImagePublisher;
import com.example.master_thesis.aws.publisher.event.PlayerImageEventRequestDto;
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

    private final PlayerImagePublisher playerImagePublisher;


    @PostMapping
    public ResponseEntity<Void> publishUserToQue(@RequestBody PlayerImageEventRequestDto playerImageEventRequestDto) {
        playerImagePublisher.publishToQue(playerImageEventRequestDto);
        return ResponseEntity.accepted().build();
    }
}
