package xyz.hardliner.paperRockScissors.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.paperRockScissors.domain.GameResult.DRAW;
import static xyz.hardliner.paperRockScissors.domain.GameResult.LEFT_WINS;
import static xyz.hardliner.paperRockScissors.domain.GameResult.RIGHT_WINS;
import static xyz.hardliner.paperRockScissors.domain.Token.PAPER;
import static xyz.hardliner.paperRockScissors.domain.Token.ROCK;
import static xyz.hardliner.paperRockScissors.domain.Token.SCISSORS;

class VictoryResolverTest {

    private final VictoryResolver sut = new VictoryResolver();

    @Test
    public void shouldResolveGame() {
        assertEquals(DRAW, sut.resolveGame(PAPER, PAPER));
        assertEquals(DRAW, sut.resolveGame(ROCK, ROCK));
        assertEquals(DRAW, sut.resolveGame(SCISSORS, SCISSORS));

        assertEquals(LEFT_WINS, sut.resolveGame(PAPER, ROCK));
        assertEquals(LEFT_WINS, sut.resolveGame(ROCK, SCISSORS));
        assertEquals(LEFT_WINS, sut.resolveGame(SCISSORS, PAPER));

        assertEquals(RIGHT_WINS, sut.resolveGame(ROCK, PAPER));
        assertEquals(RIGHT_WINS, sut.resolveGame(SCISSORS, ROCK));
        assertEquals(RIGHT_WINS, sut.resolveGame(PAPER, SCISSORS));
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.resolveGame(PAPER, null));
    }

}