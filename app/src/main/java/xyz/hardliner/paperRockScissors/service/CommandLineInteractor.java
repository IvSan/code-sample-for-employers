package xyz.hardliner.paperRockScissors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.domain.Token;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CommandLineInteractor {

    private static final Logger LOG = LoggerFactory.getLogger(CommandLineInteractor.class);

    private static final Pattern VALID_WIN_SCORE = Pattern.compile("\\d+");
    private static final int MIN_WIN_SCORE = 1;
    private static final int MAX_WIN_SCORE = 999;

    private static final String HELP_COMMAND = "help";

    private final Scanner scanner;

    public CommandLineInteractor() {
        this.scanner = new Scanner(System.in);
    }

    public void greetings() {
        System.out.println("Welcome to Paper, Rock & Scissors emulator 1.0");
    }

    public int askForMatchWinScore() {
        System.out.printf("If you know the rules - enter the score we are playing to.%nElse you can use '%s' command.%n",
            HELP_COMMAND);

        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equals(HELP_COMMAND)) {
                printHelp();
            } else if (isProperWinScore(input)) {
                final var winScore = Integer.parseInt(input);
                LOG.info("Win score set to " + winScore);
                System.out.printf("Match's to %d points.%n", winScore);
                return winScore;
            } else {
                System.out.println("Wrong win score, please try again. It should be integer number between 1 and 999");
            }
        }
    }

    private boolean isProperWinScore(String strInt) {
        if (strInt == null) {
            return false;
        }

        if (!VALID_WIN_SCORE.matcher(strInt).matches()) {
            return false;
        }

        final var winScore = Integer.parseInt(strInt);
        return winScore >= MIN_WIN_SCORE && winScore <= MAX_WIN_SCORE;
    }

    private void printHelp() {
        System.out.println("Paper-Rock-Scissors is a game for two players. Each player simultaneously picks a token to play:\n" +
            "Rock (r), Paper (p) or Scissors (s).\nThe winner is determined by the following schema:\n" +
            "• Paper beats (wraps) rock\n• Rock beats (blunts) scissors\n• Scissors beats (cuts) paper.\n" +
            "You are able to set the score you want to play to, and play multiple games in one match.\n" +
            "You are playing versus artificial intelligence.\nNow enter the score we are playing to.");
    }

    public Token askForToken() {
        System.out.println("Please, pick your token: Rock (r), Paper (p) or Scissors (s)");

        String input;
        while (true) {
            input = scanner.nextLine();
            if (isProperToken(input)) {
                final var token = Token.parseToken(input);
                LOG.info("Player picking " + token);
                System.out.printf("Your token is %s.%n", token);
                return token;
            } else {
                System.out.println("Wrong token, please try again. Use 'r', 'p' or 's' alias.");
            }
        }
    }

    private boolean isProperToken(String str) {
        return Token.validAliases.contains(str.toLowerCase());
    }

    public void printComputerToken(Token computerToken) {
        System.out.printf("Computer's token is %s.%n", computerToken);
    }

    public void printPlayerWin(boolean endOfMatch, ScoreCounter scoreCounter) {
        System.out.printf("You win! You %d - %d Computer.%n", scoreCounter.playerScore(), scoreCounter.computerScore());
        if (endOfMatch) {
            System.out.println("You win the match!");
        }
    }

    public void printComputerWin(boolean endOfMatch, ScoreCounter scoreCounter) {
        System.out.printf("You lose. You %d - %d Computer.%n", scoreCounter.playerScore(), scoreCounter.computerScore());
        if (endOfMatch) {
            System.out.println("Computer wins the match!");
        }
    }

    public void printDraw(ScoreCounter scoreCounter) {
        System.out.printf("That's draw. You %d - %d Computer.%n", scoreCounter.playerScore(), scoreCounter.computerScore());
    }
}
