package com.example.master_thesis.consumer;


import com.example.master_thesis.aws.publisher.PlayerImagePublisher;
import com.example.master_thesis.aws.publisher.event.PlayerImageEventRequestDto;
import com.example.master_thesis.consumer.event.ScrapingPlayerGamesCompletedEvent;
import com.example.master_thesis.persistance.model.Player;
import com.example.master_thesis.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetPlayerImagesConsumer extends BasePlayerConsumer {

    private final PlayerImagePublisher playerImagePublisher;
    private final PlayerService playerService;

    public static final int PLAYER_PAGE_SIZE = 10;
    public static final int MAX_PLAYER_PAGE = 5;
    public static final long DELAY_PERIOD_SECONDS = 5;

    public GetPlayerImagesConsumer(PlayerImagePublisher playerImagePublisher, PlayerService playerService) {
        super(PLAYER_PAGE_SIZE, MAX_PLAYER_PAGE, DELAY_PERIOD_SECONDS, "GET_IMAGE");
        this.playerImagePublisher = playerImagePublisher;
        this.playerService = playerService;
    }

    @EventListener(condition = "#event.hadAdditionalData()")
    @Async
    public void getPlayerImages(ScrapingPlayerGamesCompletedEvent event) {
        startProcessingWithDelay(0);
    }

    @Override
    List<Player> getPlayers(int page, int count) {
        return playerService.getPlayersWithoutImage(page, count);
    }

    @Override
    void processPlayers(List<Player> players) {
        players.stream().map(player -> PlayerImageEventRequestDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .euroleagueId(player.getEuroleagueId())
                .build()).forEach(playerImagePublisher::publishToQue);
    }
}
