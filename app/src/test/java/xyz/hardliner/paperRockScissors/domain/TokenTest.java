package xyz.hardliner.paperRockScissors.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.paperRockScissors.domain.Token.PAPER;
import static xyz.hardliner.paperRockScissors.domain.Token.ROCK;
import static xyz.hardliner.paperRockScissors.domain.Token.SCISSORS;

class TokenTest {

    @Test
    public void shouldParseToken() {
        assertEquals(PAPER, Token.parseToken("p"));
        assertEquals(PAPER, Token.parseToken("pApEr"));

        assertEquals(ROCK, Token.parseToken("R"));
        assertEquals(ROCK, Token.parseToken("rock"));

        assertEquals(SCISSORS, Token.parseToken("s"));
        assertEquals(SCISSORS, Token.parseToken("SCISSORS"));
    }

    @Test
    public void shouldNotParseToken() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Token.parseToken("rs"));
    }

}