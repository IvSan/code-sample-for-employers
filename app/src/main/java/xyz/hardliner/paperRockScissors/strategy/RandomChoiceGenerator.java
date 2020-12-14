package xyz.hardliner.paperRockScissors.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.domain.Token;

import java.util.Random;

public class RandomChoiceGenerator implements ChoiceGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(RandomChoiceGenerator.class);

    private final Random random;

    public RandomChoiceGenerator() {
        this.random = new Random();
    }

    @Override
    public Token makeChoice() {
        final var token = Token.values()[random.nextInt(Token.values().length)];
        LOG.info("Computer picking " + token);
        return token;
    }
}
