package xyz.hardliner.paperRockScissors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.domain.GameResult;
import xyz.hardliner.paperRockScissors.strategy.ChoiceGenerator;

public class Host {

    private static final Logger LOG = LoggerFactory.getLogger(Host.class);

    private final CommandLineInteractor commandLineInteractor;
    private final VictoryResolver victoryResolver;
    private final ChoiceGenerator choiceGenerator;
    private final ScoreCounter scoreCounter;

    public Host(CommandLineInteractor commandLineInteractor,
                VictoryResolver victoryResolver,
                ChoiceGenerator choiceGenerator,
                ScoreCounter scoreCounter) {
        this.commandLineInteractor = commandLineInteractor;
        this.victoryResolver = victoryResolver;
        this.choiceGenerator = choiceGenerator;
        this.scoreCounter = scoreCounter;
    }

    /*
    Player is on the left, computer is on the right.
     */
    public void playMatch() {
        LOG.info("Starting new match");

        var endOfMatch = false;

        do {
            final var playerToken = commandLineInteractor.askForToken();
            final var computerToken = choiceGenerator.makeChoice();
            commandLineInteractor.printComputerToken(computerToken);

            final var gameResult = victoryResolver.resolveGame(playerToken, computerToken);

            if (gameResult == GameResult.LEFT_WINS) {
                endOfMatch = scoreCounter.playerWins();
                commandLineInteractor.printPlayerWin(endOfMatch, scoreCounter);
            } else if (gameResult == GameResult.RIGHT_WINS) {
                endOfMatch = scoreCounter.computerWins();
                commandLineInteractor.printComputerWin(endOfMatch, scoreCounter);
            } else {
                scoreCounter.logDraw();
                commandLineInteractor.printDraw(scoreCounter);
            }
        } while (!endOfMatch);
    }

}
