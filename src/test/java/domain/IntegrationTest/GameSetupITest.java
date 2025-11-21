package domain.IntegrationTest;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameSetupITest {
    private static final int HAND_SIZE = 8;

    @Test
    public void gameSetupIT_Given3Players(){
        int numPlayers = 3;
        int expectedDeckSize = 53;

        UI ui = new UI();
        Game game = new Game(numPlayers, ui);

        assertEquals(numPlayers, game.getPlayers().size());
        for (Player player : game.getPlayers()) {
            assertEquals(HAND_SIZE, player.getHand().size());
            assertNotEquals(-1, player.hasCard("Defuse"));
        }

        Deck deck = game.getDeck();
        assertEquals(expectedDeckSize, deck.numberOfCards());
    }

    @Test
    public void gameSetupIT_Given4Players(){
        int numPlayers = 4;
        int expectedDeckSize = 71;

        UI ui = new UI();
        Game game = new Game(numPlayers, ui);

        assertEquals(numPlayers, game.getPlayers().size());
        for (Player player : game.getPlayers()) {
            assertEquals(HAND_SIZE, player.getHand().size());
            assertNotEquals(-1, player.hasCard("Defuse"));
        }

        Deck deck = game.getDeck();
        assertEquals(expectedDeckSize, deck.numberOfCards());
    }

    @Test
    public void gameSetupIT_Given7Players(){
        int numPlayers = 7;
        int expectedDeckSize = 53;

        UI ui = new UI();
        Game game = new Game(numPlayers, ui);

        assertEquals(numPlayers, game.getPlayers().size());
        for (Player player : game.getPlayers()) {
            assertEquals(HAND_SIZE, player.getHand().size());
            assertNotEquals(-1, player.hasCard("Defuse"));
        }

        Deck deck = game.getDeck();
        assertEquals(expectedDeckSize, deck.numberOfCards());
    }

    @Test
    public void gameSetupIT_Given10Players(){
        int numPlayers = 10;
        int expectedDeckSize = 77;

        UI ui = new UI();
        Game game = new Game(numPlayers, ui);

        assertEquals(numPlayers, game.getPlayers().size());
        for (Player player : game.getPlayers()) {
            assertEquals(HAND_SIZE, player.getHand().size());
            assertNotEquals(-1, player.hasCard("Defuse"));
        }

        Deck deck = game.getDeck();
        assertEquals(expectedDeckSize, deck.numberOfCards());
    }
}