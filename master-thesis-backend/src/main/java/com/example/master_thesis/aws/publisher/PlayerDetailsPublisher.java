package com.example.master_thesis.aws.publisher;

import com.example.master_thesis.aws.AwsLocalStackProperties;
import com.example.master_thesis.aws.publisher.event.PlayerEventDto;
import com.example.master_thesis.service.PlayerService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerDetailsPublisher {
    private final SqsTemplate template;
    private final AwsLocalStackProperties properties;
    private final PlayerService playerService;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static final int PLAYER_PAGE_SIZE = 10;
    public static final int MAX_PLAYER_PAGE = 5;
    public static final long DELAY_PERIOD_SECONDS = 6;

    public void publishToQue(PlayerEventDto playerEventDto) {
        template.send(to -> to.queue(properties.getEuroleaguePlayersRequest()).payload(playerEventDto));
    }

    public void startPublishingWithDelay(int pageNumber) {
        // RESET PAGE NUMBER, GIVE 'MAX_PLAYER_PAGE' BUFFER TO SCRAPER, IF IT DOESN'T HANDLE IMAGE BY THEN THE MESSAGE WILL BE REPEATED
        if (pageNumber > MAX_PLAYER_PAGE)
            pageNumber = 0;

        var players = playerService.getPlayersWithoutImage(pageNumber, PLAYER_PAGE_SIZE);
        if (players.isEmpty()) {
            if (pageNumber == 0) {
                log.info("Player images scraped!");
                return;
            }
            scheduleNextTask(0);
        }

        players.stream().map(player -> PlayerEventDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .euroleagueId(player.getEuroleagueId())
                .build()).forEach(this::publishToQue);

        scheduleNextTask(pageNumber + 1);
    }

    private void scheduleNextTask(int pageNumber) {
        executorService.schedule(() ->
                startPublishingWithDelay(pageNumber), DELAY_PERIOD_SECONDS, TimeUnit.SECONDS);
    }
}
