package com.example.master_thesis.consumer;

import com.example.master_thesis.persistance.model.player.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public abstract class BasePlayerConsumer {
    protected final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    protected final int playerPageSize;
    protected final int maxPlayerPage;
    protected final long delayInSeconds;
    protected final String processName;

    abstract List<Player> getPlayers(int page, int count);

    abstract void processPlayers(List<Player> players);

    void startProcessingWithDelay(int pageNumber) {
        if (pageNumber > maxPlayerPage)
            pageNumber = 0;

        var players = getPlayers(pageNumber, playerPageSize);
        if (players.isEmpty()) {
            if (pageNumber == 0) {
                log.info("Player process {} finished!", processName);
                return;
            }
            scheduleNextTask(0);
            return;
        }

        processPlayers(players);

        scheduleNextTask(pageNumber + 1);
    }

    private void scheduleNextTask(int pageNumber) {
        executorService.schedule(() ->
                startProcessingWithDelay(pageNumber), delayInSeconds, TimeUnit.SECONDS);
    }
}
