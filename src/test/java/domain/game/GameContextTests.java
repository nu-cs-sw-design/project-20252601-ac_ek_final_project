package domain.game;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.player.Player;
import domain.player.PlayerManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameContextTests {
    private GameContext gameContext;
    private GameEngine mockEngine;
    private Player mockPlayer;
    private PlayerManager mockPlayerManager;
    private DeckManager mockDeckManager;
    private domain.player.PlayerController mockController;

    @BeforeEach
    public void setUp() {
        mockEngine = EasyMock.createMock(GameEngine.class);
        mockPlayer = EasyMock.createMock(Player.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
        mockController = EasyMock.createMock(domain.player.PlayerController.class);
        gameContext = new GameContext(mockEngine);
    }

    @Test
    public void testDisplayMessage_notifiesEngine() {
        mockEngine.notifyMessage("testMessage");
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.displayMessage("testMessage");
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testDisplayFormattedMessage_notifiesEngine() {
        mockEngine.notifyFormattedMessage("testKey", "arg1", 2);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.displayFormattedMessage("testKey", "arg1", 2);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testPromptPlayer_delegatesToController() {
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getController()).andReturn(mockController);
        EasyMock.expect(mockController.getDecision(mockEngine, "testPrompt")).andReturn(42);
        EasyMock.replay(mockEngine, mockPlayer, mockController);

        int result = gameContext.promptPlayer("testPrompt");
        assertEquals(42, result);
        EasyMock.verify(mockEngine, mockPlayer, mockController);
    }

    @Test
    public void testGetCurrentPlayer_delegatesToEngine() {
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.replay(mockEngine);

        Player result = gameContext.getCurrentPlayer();
        assertEquals(mockPlayer, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetCurrentPlayer_delegatesToEngine() {
        mockEngine.setCurrentPlayer(mockPlayer);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setCurrentPlayer(mockPlayer);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetPlayer_delegatesToEngine() {
        EasyMock.expect(mockEngine.getPlayer(5)).andReturn(mockPlayer);
        EasyMock.replay(mockEngine);

        Player result = gameContext.getPlayer(5);
        assertEquals(mockPlayer, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetPlayer_delegatesToEngine() {
        mockEngine.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setPlayer(mockPlayer);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testDeletePlayer_delegatesToEngine() {
        mockEngine.deletePlayer(3);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.deletePlayer(3);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetPlayers_delegatesToEngine() {
        List<Player> players = Arrays.asList(mockPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.replay(mockEngine);

        List<Player> result = gameContext.getPlayers();
        assertEquals(1, result.size());
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetPlayers_delegatesToEngine() {
        List<Player> players = Arrays.asList(mockPlayer);
        mockEngine.setPlayers(players);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setPlayers(players);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testNextPlayer_delegatesToEngine() {
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.nextPlayer();
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetNextPlayerTargetPlayer_delegatesToEngine() {
        mockEngine.setNextPlayerTargetPlayer(mockPlayer);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setNextPlayerTargetPlayer(mockPlayer);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetCurrentPlayerTurns_delegatesToEngine() {
        mockEngine.setCurrentPlayerTurns(3);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setCurrentPlayerTurns(3);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetNextPlayerTurns_delegatesToEngine() {
        mockEngine.setNextPlayerTurns(2);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setNextPlayerTurns(2);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testRemoveCurrentPlayerCard_delegatesToEngine() {
        mockEngine.removeCurrentPlayerCard(1);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.removeCurrentPlayerCard(1);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testAddToCurrentPlayer_delegatesToEngine() {
        Card mockCard = EasyMock.createMock(Card.class);
        mockEngine.addToCurrentPlayer(mockCard);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.addToCurrentPlayer(mockCard);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDeck_delegatesToEngine() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        EasyMock.expect(mockEngine.getDeck()).andReturn(mockDeck);
        EasyMock.replay(mockEngine);

        Deck result = gameContext.getDeck();
        assertEquals(mockDeck, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testSetDeck_delegatesToEngine() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        mockEngine.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.replay(mockEngine);

        gameContext.setDeck(mockDeck);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetPlayerManager_delegatesToEngine() {
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.replay(mockEngine);

        PlayerManager result = gameContext.getPlayerManager();
        assertEquals(mockPlayerManager, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDeckManager_delegatesToEngine() {
        EasyMock.expect(mockEngine.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.replay(mockEngine);

        DeckManager result = gameContext.getDeckManager();
        assertEquals(mockDeckManager, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetCardManager_returnsCardManager() {
        CardManager result = gameContext.getCardManager();
        assertNotNull(result);
    }

    @Test
    public void testGetCardManager_returnsSameInstance() {
        CardManager result1 = gameContext.getCardManager();
        CardManager result2 = gameContext.getCardManager();
        assertNotSame(result1, result2);
    }
}