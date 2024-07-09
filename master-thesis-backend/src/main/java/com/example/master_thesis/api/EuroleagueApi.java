package com.example.master_thesis.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "euroleague", url = "https://live.euroleague.net/api")
public interface EuroleagueApi {
    @GetMapping("/Boxscore")
    List<Object> getBoxscore(final String gamecode, final String seasoncode);
}
