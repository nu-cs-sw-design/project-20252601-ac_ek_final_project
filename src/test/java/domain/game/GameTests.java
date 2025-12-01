package domain.game;

import domain.deck.Deck;
import domain.cards.implementations.SkipCard;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testConstructor_initializesWithEmptyPlayers() {
        assertNotNull(game.getPlayers());
        assertTrue(game.getPlayers().isEmpty());
    }

    @Test
    public void testConstructor_initializesWithGameNotOver() {
        assertFalse(game.isGameOver());
    }

    @Test
    public void testSetPlayers_setsPlayersList() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player1, player2);
        
        game.setPlayers(players);
        
        assertEquals(2, game.getPlayers().size());
        assertEquals(1, game.getPlayers().get(0).getId());
        assertEquals(2, game.getPlayers().get(1).getId());
    }

    @Test
    public void testSetPlayers_emptyList() {
        game.setPlayers(new ArrayList<>());
        assertTrue(game.getPlayers().isEmpty());
    }

    @Test
    public void testSetPlayers_overwritesPreviousPlayers() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        
        game.setPlayers(Arrays.asList(player1));
        assertEquals(1, game.getPlayers().size());
        
        game.setPlayers(Arrays.asList(player1, player2));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testSetGameOver_toTrue() {
        game.setGameOver(true);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testSetGameOver_toFalse() {
        game.setGameOver(true);
        game.setGameOver(false);
        assertFalse(game.isGameOver());
    }

    @Test
    public void testIsGameOver_defaultFalse() {
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGetDeck_initiallyNull() {
        assertNull(game.getDeck());
    }

    @Test
    public void testSetDeck_setsDeck() {
        Deck deck = new Deck();
        game.setDeck(deck);
        assertNotNull(game.getDeck());
    }

    @Test
    public void testSetDeck_setsNullDeck() {
        Deck deck = new Deck();
        game.setDeck(deck);
        game.setDeck(null);
        assertNull(game.getDeck());
    }

    @Test
    public void testCopyConstructor_withNullOther() {
        Game copy = new Game(null);
        assertNotNull(copy.getPlayers());
        assertTrue(copy.getPlayers().isEmpty());
        assertNull(copy.getDeck());
        assertFalse(copy.isGameOver());
    }

    @Test
    public void testCopyConstructor_withNullDeck() {
        Game original = new Game();
        original.setDeck(null);
        original.setPlayers(Arrays.asList(new Player(1, new ArrayList<>())));
        
        Game copy = new Game(original);
        assertNull(copy.getDeck());
        assertNotNull(copy.getPlayers());
        assertEquals(1, copy.getPlayers().size());
    }

    @Test
    public void testCopyConstructor_withDeck() {
        Game original = new Game();
        Deck deck = new Deck();
        deck.addCard(new SkipCard());
        original.setDeck(deck);
        original.setPlayers(Arrays.asList(new Player(1, new ArrayList<>())));
        
        Game copy = new Game(original);
        assertNotNull(copy.getDeck());
        assertNotSame(original.getDeck(), copy.getDeck());
    }

    @Test
    public void testCopyConstructor_withNullPlayers() {
        Game original = new Game();
        try {
            java.lang.reflect.Field playersField = Game.class.getDeclaredField("players");
            playersField.setAccessible(true);
            playersField.set(original, null);
        } catch (Exception e) {
            fail("Failed to set players to null via reflection");
        }
        
        Game copy = new Game(original);
        assertNotNull(copy.getPlayers());
        assertTrue(copy.getPlayers().isEmpty());
    }

    @Test
    public void testCopyConstructor_copiesPlayers() {
        Game original = new Game();
        Player p1 = new Player(1, new ArrayList<>());
        Player p2 = new Player(2, new ArrayList<>());
        original.setPlayers(Arrays.asList(p1, p2));
        
        Game copy = new Game(original);
        assertNotNull(copy.getPlayers());
        assertEquals(2, copy.getPlayers().size());
        assertNotSame(original.getPlayers(), copy.getPlayers());
    }

    @Test
    public void testCopyConstructor_copiesGameOverState() {
        Game original = new Game();
        original.setGameOver(true);
        
        Game copy = new Game(original);
        assertTrue(copy.isGameOver());
    }

    @Test
    public void testSetPlayers_withNull() {
        game.setPlayers(null);
        assertNotNull(game.getPlayers());
        assertTrue(game.getPlayers().isEmpty());
    }
}