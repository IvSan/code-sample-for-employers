package xyz.hardliner.paperRockScissors.service.io;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ConsoleHandlerTest {

    @Test
    public void shouldInput() {
        final var sc = new Scanner(System.in);
        final var scanner = mock(Scanner.class);
        final var sut = new ConsoleHandler(scanner);

        when(scanner.nextLine()).thenReturn("line");

        final var input = sut.requestInput();

        assertEquals("line", input);
        verify(scanner, times(1)).nextLine();
        verifyNoMoreInteractions(scanner);
    }

    @Test
    public void shouldOutput() {
        final var sc = new Scanner(System.in);
        final var scanner = mock(Scanner.class);
        final var sut = new ConsoleHandler(scanner);

        sut.sendOutput("line");

        verifyNoMoreInteractions(scanner);
    }

}