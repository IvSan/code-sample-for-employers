package xyz.hardliner.paperRockScissors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class ScoreCounter {

    private static final Logger LOG = LoggerFactory.getLogger(ScoreCounter.class);

    private final int matchWinScore;
    private final AtomicInteger playerScore;
    private final AtomicInteger computerScore;

    public ScoreCounter(int matchWinScore) {
        this.matchWinScore = matchWinScore;
        playerScore = new AtomicInteger();
        computerScore = new AtomicInteger();
    }

    public int playerScore() {
        return playerScore.get();
    }

    public int computerScore() {
        return computerScore.get();
    }

    public boolean playerWins() {
        final var playerWinsMatch = playerScore.incrementAndGet() >= matchWinScore;

        if (playerWinsMatch) {
            LOG.info(String.format("Player wins, player:%d - computer:%d", playerScore.get(), computerScore.get()));
        } else {
            LOG.info(String.format("Keep playing, player:%d - computer:%d", playerScore.get(), computerScore.get()));
        }

        return playerWinsMatch;
    }

    public boolean computerWins() {
        final var playerWinsMatch = computerScore.incrementAndGet() >= matchWinScore;

        if (playerWinsMatch) {
            LOG.info(String.format("Computer wins, player:%d - computer:%d", playerScore.get(), computerScore.get()));
        } else {
            LOG.info(String.format("Keep playing, player:%d - computer:%d", playerScore.get(), computerScore.get()));
        }

        return playerWinsMatch;
    }

    public void logDraw() {
        LOG.info(String.format("Draw, keep playing, player:%d - computer:%d", playerScore.get(), computerScore.get()));
    }

}
