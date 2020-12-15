package xyz.hardliner.paperRockScissors.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreCounterTest {

    @Test
    public void shouldCountMatchScore() {
        ScoreCounter sut = new ScoreCounter(2);

        assertEquals(0, sut.playerScore());
        assertEquals(0, sut.computerScore());

        assertFalse(sut.playerWins());

        assertEquals(1, sut.playerScore());
        assertEquals(0, sut.computerScore());

        sut.logDraw();

        assertEquals(1, sut.playerScore());
        assertEquals(0, sut.computerScore());

        assertFalse(sut.computerWins());
        assertTrue(sut.computerWins());

        assertEquals(1, sut.playerScore());
        assertEquals(2, sut.computerScore());
    }

}