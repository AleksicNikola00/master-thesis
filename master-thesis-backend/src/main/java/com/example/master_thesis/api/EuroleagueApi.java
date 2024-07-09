package com.example.master_thesis.api;

import com.example.master_thesis.api.dto.BoxscoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "euroleague", url = "https://live.euroleague.net/api")
public interface EuroleagueApi {
    @GetMapping("/Boxscore")
    BoxscoreDto getBoxScore(@RequestParam("gamecode") final String gameCode,
                            @RequestParam("seasoncode") final String seasonCode);
}
