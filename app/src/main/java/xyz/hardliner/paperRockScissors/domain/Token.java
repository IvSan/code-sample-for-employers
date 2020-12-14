package xyz.hardliner.paperRockScissors.domain;

import java.util.Set;

public enum Token {
    ROCK,
    PAPER,
    SCISSORS;

    public static final Set<String> validAliases = Set.of("r", "rock", "p", "paper", "s", "scissors");

    public static Token parseToken(String str) {
        String lowercaseStr = str.toLowerCase();

        if (lowercaseStr.equals("r") || lowercaseStr.equals("rock")) {
            return ROCK;
        }

        if (lowercaseStr.equals("p") || lowercaseStr.equals("paper")) {
            return PAPER;
        }

        if (lowercaseStr.equals("s") || lowercaseStr.equals("scissors")) {
            return SCISSORS;
        }

        throw new IllegalArgumentException("Wrong token: " + str);
    }
}
