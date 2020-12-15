package xyz.hardliner.paperRockScissors.service;

import org.junit.jupiter.api.Test;
import xyz.hardliner.paperRockScissors.domain.Token;
import xyz.hardliner.paperRockScissors.strategy.ChoiceGenerator;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static xyz.hardliner.paperRockScissors.domain.Token.ROCK;
import static xyz.hardliner.paperRockScissors.domain.Token.SCISSORS;

public class HostTest {

    @Test
    public void shouldHostAMatch() {
        final var playerInteractor = mock(PlayerInteractor.class);
        final var victoryResolver = spy(new VictoryResolver());
        final var choiceGenerator = mock(ChoiceGenerator.class);
        final var scoreCounter = spy(new ScoreCounter(2));

        when(playerInteractor.askForToken()).thenReturn(ROCK);
        when(choiceGenerator.makeChoice()).thenReturn(SCISSORS);

        final var sut = new Host(playerInteractor, victoryResolver, choiceGenerator, scoreCounter);
        sut.playMatch();

        verify(playerInteractor, times(2)).askForToken();
        verify(choiceGenerator, times(2)).makeChoice();
        verify(scoreCounter, times(2)).playerWins();
        verify(playerInteractor, times(2)).printComputerToken(isA(Token.class));
        verify(playerInteractor, times(1)).printPlayerWin(eq(false), isA(ScoreCounter.class));
        verify(playerInteractor, times(1)).printPlayerWin(eq(true), isA(ScoreCounter.class));
        verifyNoMoreInteractions(playerInteractor, choiceGenerator, scoreCounter, playerInteractor);
    }

}