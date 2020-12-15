package xyz.hardliner.paperRockScissors.service;

import org.junit.jupiter.api.Test;
import xyz.hardliner.paperRockScissors.service.io.TextInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static xyz.hardliner.paperRockScissors.domain.Token.ROCK;

public class PlayerInteractorTest {

    @Test
    public void shouldAskForProperWinScore() {
        final var textInterface = mock(TextInterface.class);
        final var sut = new PlayerInteractor(textInterface);

        when(textInterface.requestInput()).thenReturn("non-digit", "99999", "-1", "2");

        final var input = sut.askForMatchWinScore();

        assertEquals(2, input);
        verify(textInterface, times(4)).requestInput();
        verify(textInterface, times(5)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldAskForProperToken() {
        final var textInterface = mock(TextInterface.class);
        final var sut = new PlayerInteractor(textInterface);

        when(textInterface.requestInput()).thenReturn("3", "t", "r");

        final var input = sut.askForToken();

        assertEquals(ROCK, input);
        verify(textInterface, times(3)).requestInput();
        verify(textInterface, times(4)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldNotifyAboutGameWin() {
        final var textInterface = mock(TextInterface.class);
        final var scoreCounter = mock(ScoreCounter.class);
        final var sut = new PlayerInteractor(textInterface);

        when(scoreCounter.playerScore()).thenReturn(1);
        when(scoreCounter.computerScore()).thenReturn(1);

        sut.printPlayerWin(false, scoreCounter);

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldNotifyAboutMatchWin() {
        final var textInterface = mock(TextInterface.class);
        final var scoreCounter = mock(ScoreCounter.class);
        final var sut = new PlayerInteractor(textInterface);

        when(scoreCounter.playerScore()).thenReturn(1);
        when(scoreCounter.computerScore()).thenReturn(1);

        sut.printPlayerWin(true, scoreCounter);

        verify(textInterface, times(2)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldNotifyAboutGameLoss() {
        final var textInterface = mock(TextInterface.class);
        final var scoreCounter = mock(ScoreCounter.class);
        final var sut = new PlayerInteractor(textInterface);

        when(scoreCounter.playerScore()).thenReturn(1);
        when(scoreCounter.computerScore()).thenReturn(1);

        sut.printComputerWin(false, scoreCounter);

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldNotifyAboutMatchLoss() {
        final var textInterface = mock(TextInterface.class);
        final var scoreCounter = mock(ScoreCounter.class);
        final var sut = new PlayerInteractor(textInterface);

        when(scoreCounter.playerScore()).thenReturn(1);
        when(scoreCounter.computerScore()).thenReturn(1);

        sut.printComputerWin(true, scoreCounter);

        verify(textInterface, times(2)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldNotifyAboutDraw() {
        final var textInterface = mock(TextInterface.class);
        final var scoreCounter = mock(ScoreCounter.class);
        final var sut = new PlayerInteractor(textInterface);

        when(scoreCounter.playerScore()).thenReturn(1);
        when(scoreCounter.computerScore()).thenReturn(1);

        sut.printDraw(scoreCounter);

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldPrintGreetings() {
        final var textInterface = mock(TextInterface.class);
        final var sut = new PlayerInteractor(textInterface);

        sut.printGreetings();

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldPrintHelp() {
        final var textInterface = mock(TextInterface.class);
        final var sut = new PlayerInteractor(textInterface);

        sut.printHelp();

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

    @Test
    public void shouldPrintComputerToken() {
        final var textInterface = mock(TextInterface.class);
        final var sut = new PlayerInteractor(textInterface);

        sut.printComputerToken(ROCK);

        verify(textInterface, times(1)).sendOutput(isA(String.class));
        verifyNoMoreInteractions(textInterface);
    }

}