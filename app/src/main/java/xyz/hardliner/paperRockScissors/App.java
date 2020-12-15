package xyz.hardliner.paperRockScissors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.service.Host;
import xyz.hardliner.paperRockScissors.service.PlayerInteractor;
import xyz.hardliner.paperRockScissors.service.ScoreCounter;
import xyz.hardliner.paperRockScissors.service.VictoryResolver;
import xyz.hardliner.paperRockScissors.service.io.ConsoleHandler;
import xyz.hardliner.paperRockScissors.strategy.ChoiceGenerator;
import xyz.hardliner.paperRockScissors.strategy.RandomChoiceGenerator;

import java.util.Scanner;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("\n\t\t\t\t\t\t\t\tPaper, Rock & Scissors 1.0");

        PlayerInteractor playerInteractor = new PlayerInteractor(new ConsoleHandler(new Scanner(System.in)));
        playerInteractor.printGreetings();
        VictoryResolver victoryResolver = new VictoryResolver();
        ChoiceGenerator choiceGenerator = new RandomChoiceGenerator();
        ScoreCounter scoreCounter = new ScoreCounter(playerInteractor.askForMatchWinScore());

        final var host = new Host(playerInteractor, victoryResolver, choiceGenerator, scoreCounter);
        host.playMatch();
    }
}
