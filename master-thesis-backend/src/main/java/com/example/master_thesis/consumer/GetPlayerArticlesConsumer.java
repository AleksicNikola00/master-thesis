package com.example.master_thesis.consumer;

import com.example.master_thesis.aws.publisher.PlayerArticlePublisher;
import com.example.master_thesis.aws.publisher.event.PlayerArticleEventRequestDto;
import com.example.master_thesis.consumer.event.ScrapingPlayerGamesCompletedEvent;
import com.example.master_thesis.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetPlayerArticlesConsumer {

    private final PlayerService playerService;
    private final PlayerArticlePublisher playerArticlePublisher;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static final int PLAYER_PAGE_SIZE = 5;
    public static final int MAX_PLAYER_PAGE = 10;
    public static final long DELAY_PERIOD_SECONDS = 10;


    @EventListener
    @Async
    public void getPlayerArticles(ScrapingPlayerGamesCompletedEvent event) {
        startPublishingWithDelay(0);
    }

    private void startPublishingWithDelay(int pageNumber) {
        if (pageNumber > MAX_PLAYER_PAGE)
            pageNumber = 0;

        var players = playerService.getPlayersWithoutArticles(pageNumber, PLAYER_PAGE_SIZE);
        if (players.isEmpty()) {
            if (pageNumber == 0) {
                log.info("Player images scraped!");
                return;
            }
            scheduleNextTask(0);
            return;
        }

        players.stream().map(player -> PlayerArticleEventRequestDto.builder()
                .id(player.getId())
                .lastName(player.getLastName())
                .firstName(player.getFirstName())
                .build()
        ).forEach(playerArticlePublisher::publishToQue);


        scheduleNextTask(pageNumber + 1);
    }

    private void scheduleNextTask(int pageNumber) {
        executorService.schedule(() ->
                startPublishingWithDelay(pageNumber), DELAY_PERIOD_SECONDS, TimeUnit.SECONDS);
    }
}
