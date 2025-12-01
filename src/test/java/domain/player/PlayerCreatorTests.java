package domain.player;

import domain.cards.implementations.SkipCard;
import domain.deck.Deck;
import domain.game.GameConfiguration;
import ui.GameUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerCreatorTests {
    private PlayerCreator playerCreator;
    private GameUI mockUI;

    @BeforeEach
    public void setUp() {
        playerCreator = new PlayerCreator();
        mockUI = EasyMock.createMock(GameUI.class);
    }

    @Test
    public void testCreatePlayers_allHumanPlayers_createsCorrectCount() {
        GameConfiguration config = new GameConfiguration(3, 0, new HashSet<>());
        Deck deck = createDeckWithCards(15);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        assertEquals(3, players.size());
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_allAIPlayers_setsAIFlag() {
        GameConfiguration config = new GameConfiguration(0, 3, new HashSet<>());
        Deck deck = createDeckWithCards(15);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        assertEquals(3, players.size());
        for (Player player : players) {
            assertTrue(player.isAI());
            assertTrue(player.getController() instanceof PlayerControllerAI);
        }
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_mixedPlayers_correctAIAssignment() {
        GameConfiguration config = new GameConfiguration(2, 1, new HashSet<>());
        Deck deck = createDeckWithCards(15);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        assertEquals(3, players.size());
        assertFalse(players.get(0).isAI());
        assertFalse(players.get(1).isAI());
        assertTrue(players.get(2).isAI());
        assertTrue(players.get(0).getController() instanceof PlayerControllerHuman);
        assertTrue(players.get(1).getController() instanceof PlayerControllerHuman);
        assertTrue(players.get(2).getController() instanceof PlayerControllerAI);
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_playersHaveCorrectIds() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        Deck deck = createDeckWithCards(10);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        assertEquals(1, players.get(0).getId());
        assertEquals(2, players.get(1).getId());
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_playersReceiveDefuseCard() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        Deck deck = createDeckWithCards(10);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        for (Player player : players) {
            boolean hasDefuse = player.getHandObject().getCards().stream()
                    .anyMatch(card -> card.getName().equals("Defuse"));
            assertTrue(hasDefuse);
        }
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_noExpansions_fourCardsPerPlayer() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        Deck deck = createDeckWithCards(10);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        for (Player player : players) {
            assertEquals(4, player.getHandObject().size());
        }
        EasyMock.verify(mockUI);
    }

    @Test
    public void testCreatePlayers_withExpansions_eightCardsPerPlayer() {
        Set<String> expansions = new HashSet<>();
        expansions.add("imploding");
        GameConfiguration config = new GameConfiguration(2, 0, expansions);
        Deck deck = createDeckWithCards(20);
        EasyMock.replay(mockUI);

        List<Player> players = playerCreator.createPlayers(config, deck, mockUI);

        for (Player player : players) {
            assertEquals(8, player.getHandObject().size());
        }
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetInitialCardsPerPlayer_noExpansions_returnsFour() {
        int result = playerCreator.getInitialCardsPerPlayer(false);
        assertEquals(4, result);
    }

    @Test
    public void testGetInitialCardsPerPlayer_withExpansions_returnsEight() {
        int result = playerCreator.getInitialCardsPerPlayer(true);
        assertEquals(8, result);
    }

    @Test
    public void testCreatePlayers_drawsCardsFromDeck() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        Deck deck = createDeckWithCards(10);
        int initialDeckSize = deck.numberOfCards();
        EasyMock.replay(mockUI);

        playerCreator.createPlayers(config, deck, mockUI);

        assertEquals(initialDeckSize - 6, deck.numberOfCards());
        EasyMock.verify(mockUI);
    }

    private Deck createDeckWithCards(int count) {
        Deck deck = new Deck();
        for (int i = 0; i < count; i++) {
            deck.addCard(new SkipCard());
        }
        return deck;
    }
}