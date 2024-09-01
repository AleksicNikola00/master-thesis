package com.example.master_thesis.consumer;

import com.example.master_thesis.aws.publisher.PlayerArticlePublisher;
import com.example.master_thesis.aws.publisher.event.PlayerArticleEventRequestDto;
import com.example.master_thesis.consumer.event.ScrapingPlayerGamesCompletedEvent;
import com.example.master_thesis.persistance.model.player.Player;
import com.example.master_thesis.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetPlayerArticlesConsumer extends BasePlayerConsumer {

    private final PlayerService playerService;
    private final PlayerArticlePublisher playerArticlePublisher;


    public static final int PLAYER_PAGE_SIZE = 5;
    public static final int MAX_PLAYER_PAGE = 30;
    public static final long DELAY_PERIOD_SECONDS = 40;

    public GetPlayerArticlesConsumer(PlayerService playerService, PlayerArticlePublisher playerArticlePublisher) {
        super(PLAYER_PAGE_SIZE, MAX_PLAYER_PAGE, DELAY_PERIOD_SECONDS, "GET_ARTICLES");
        this.playerService = playerService;
        this.playerArticlePublisher = playerArticlePublisher;
    }


    @EventListener(condition = "#event.hadAdditionalData()")
    @Async
    public void getPlayerArticles(ScrapingPlayerGamesCompletedEvent event) {
        startProcessingWithDelay(0);
    }


    @Override
    List<Player> getPlayers(int page, int count) {
        return playerService.getPlayersWithoutArticles(page, count);
    }

    @Override
    void processPlayers(List<Player> players) {
        players.stream().map(player ->
                PlayerArticleEventRequestDto.builder()
                        .id(player.getId())
                        .firstName(player.getFirstName())
                        .lastName(player.getLastName())
                        .build()
        ).forEach(playerArticlePublisher::publishToQue);
    }
}
