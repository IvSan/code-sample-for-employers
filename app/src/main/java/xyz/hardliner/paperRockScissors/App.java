package xyz.hardliner.paperRockScissors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.service.CommandLineInteractor;
import xyz.hardliner.paperRockScissors.service.Host;
import xyz.hardliner.paperRockScissors.service.ScoreCounter;
import xyz.hardliner.paperRockScissors.service.VictoryResolver;
import xyz.hardliner.paperRockScissors.strategy.ChoiceGenerator;
import xyz.hardliner.paperRockScissors.strategy.RandomChoiceGenerator;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("\n\t\t\t\t\t\t\t\tPaper, Rock & Scissors 1.0");

        CommandLineInteractor commandLineInteractor = new CommandLineInteractor();
        commandLineInteractor.greetings();
        VictoryResolver victoryResolver = new VictoryResolver();
        ChoiceGenerator choiceGenerator = new RandomChoiceGenerator();
        ScoreCounter scoreCounter = new ScoreCounter(commandLineInteractor.askForMatchWinScore());

        Host host = new Host(commandLineInteractor, victoryResolver, choiceGenerator, scoreCounter);
        host.playMatch();
    }
}
