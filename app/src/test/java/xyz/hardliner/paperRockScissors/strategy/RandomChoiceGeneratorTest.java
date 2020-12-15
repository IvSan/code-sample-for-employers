package xyz.hardliner.paperRockScissors.strategy;

import org.junit.jupiter.api.Test;
import xyz.hardliner.paperRockScissors.domain.Token;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomChoiceGeneratorTest {

    private final RandomChoiceGenerator sut = new RandomChoiceGenerator();

    @Test
    public void shouldMakeAPick() {
        Token token = sut.makeChoice();
        assertNotNull(token);
    }

}