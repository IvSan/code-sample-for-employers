package xyz.hardliner.paperRockScissors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hardliner.paperRockScissors.domain.Token;
import xyz.hardliner.paperRockScissors.service.io.TextInterface;

import java.util.regex.Pattern;

public class PlayerInteractor {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerInteractor.class);

    private static final Pattern VALID_WIN_SCORE = Pattern.compile("\\d+");
    private static final int MIN_WIN_SCORE = 1;
    private static final int MAX_WIN_SCORE = 999;

    private static final String HELP_COMMAND = "help";

    private final TextInterface textIO;

    public PlayerInteractor(TextInterface textInterface) {
        this.textIO = textInterface;
    }

    public void printGreetings() {
        textIO.sendOutput("Welcome to Paper, Rock & Scissors emulator 1.0");
    }

    public int askForMatchWinScore() {
        textIO.sendOutput(String.format(
            "If you know the rules - enter the score we are playing to.%nElse you can use '%s' command.",
            HELP_COMMAND));

        while (true) {
            String input = textIO.requestInput();

            if (input.equals(HELP_COMMAND)) {
                printHelp();
            } else if (isProperWinScore(input)) {
                final var winScore = Integer.parseInt(input);
                LOG.info("Win score set to " + winScore);
                textIO.sendOutput(String.format("Match is up to %d win points.", winScore));
                return winScore;
            } else {
                textIO.sendOutput("Wrong win score, please try again. It should be integer number between 1 and 999");
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

    public void printHelp() {
        textIO.sendOutput(
            "Paper-Rock-Scissors is a game for two players. Each player simultaneously picks a token to play:\n" +
                "Rock (r), Paper (p) or Scissors (s).\nThe winner is determined by the following schema:\n" +
                "• Paper beats (wraps) rock\n• Rock beats (blunts) scissors\n• Scissors beats (cuts) paper.\n" +
                "You are able to set the score you want to play to, and play multiple games in one match.\n" +
                "You are playing versus artificial intelligence.\nNow enter the score we are playing to.");
    }

    public Token askForToken() {
        textIO.sendOutput("Please, pick your token: Rock (r), Paper (p) or Scissors (s)");

        while (true) {
            String input = textIO.requestInput();
            if (isProperToken(input)) {
                final var token = Token.parseToken(input);
                LOG.info("Player picking " + token);
                textIO.sendOutput(String.format("Your token is %s.", token));
                return token;
            } else {
                textIO.sendOutput("Wrong token, please try again. Use 'r', 'p' or 's' alias.");
            }
        }
    }

    private boolean isProperToken(String str) {
        return Token.validAliases.contains(str.toLowerCase());
    }

    public void printComputerToken(Token computerToken) {
        textIO.sendOutput(String.format("Computer's token is %s.", computerToken));
    }

    public void printPlayerWin(boolean endOfMatch, ScoreCounter scoreCounter) {
        textIO.sendOutput(String.format("You win! You %d - %d Computer.",
            scoreCounter.playerScore(), scoreCounter.computerScore()));
        if (endOfMatch) {
            textIO.sendOutput("You win the match!");
        }
    }

    public void printComputerWin(boolean endOfMatch, ScoreCounter scoreCounter) {
        textIO.sendOutput(String.format("You lose. You %d - %d Computer.",
            scoreCounter.playerScore(), scoreCounter.computerScore()));
        if (endOfMatch) {
            textIO.sendOutput("Computer wins the match!");
        }
    }

    public void printDraw(ScoreCounter scoreCounter) {
        textIO.sendOutput(String.format("That's draw. You %d - %d Computer.",
            scoreCounter.playerScore(), scoreCounter.computerScore()));
    }
}
