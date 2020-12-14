package xyz.hardliner.paperRockScissors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.domain.GameResult;
import xyz.hardliner.paperRockScissors.domain.Token;

import static xyz.hardliner.paperRockScissors.domain.GameResult.DRAW;
import static xyz.hardliner.paperRockScissors.domain.GameResult.LEFT_WINS;
import static xyz.hardliner.paperRockScissors.domain.GameResult.RIGHT_WINS;
import static xyz.hardliner.paperRockScissors.domain.Token.PAPER;
import static xyz.hardliner.paperRockScissors.domain.Token.ROCK;
import static xyz.hardliner.paperRockScissors.domain.Token.SCISSORS;

public class VictoryResolver {

    private static final Logger LOG = LoggerFactory.getLogger(VictoryResolver.class);

    public GameResult resolveGame(Token leftPlayerToken, Token rightPlayerToken) {
        LOG.info(String.format("Resolving game: leftPlayerToken='%s', rightPlayerToken='%s'",
            leftPlayerToken, rightPlayerToken));

        if (leftPlayerToken == null || rightPlayerToken == null) {
            throw new IllegalArgumentException("Cannot resolve game with null token");
        }

        final var gameResult = compareTokens(leftPlayerToken, rightPlayerToken);
        LOG.info("Game result: " + gameResult);
        return gameResult;
    }

    private GameResult compareTokens(Token leftPlayerToken, Token rightPlayerToken) {
        if (leftPlayerToken == rightPlayerToken) {
            return DRAW;
        }

        if (leftPlayerToken == PAPER) {
            if (rightPlayerToken == SCISSORS) {
                return RIGHT_WINS;
            } else {
                return LEFT_WINS;
            }
        }

        if (leftPlayerToken == ROCK) {
            if (rightPlayerToken == PAPER) {
                return RIGHT_WINS;
            } else {
                return LEFT_WINS;
            }
        }

        if (rightPlayerToken == ROCK) {
            return RIGHT_WINS;
        } else {
            return LEFT_WINS;
        }
    }
}
