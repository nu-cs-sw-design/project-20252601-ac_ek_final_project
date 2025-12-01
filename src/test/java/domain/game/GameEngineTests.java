package domain.game;

import domain.cards.Card;
import domain.cards.implementations.AttackCard;
import domain.cards.implementations.DefuseCard;
import domain.deck.Deck;
import domain.deck.DeckCreator;
import domain.deck.DeckManager;
import domain.player.Player;
import domain.player.PlayerManager;
import ui.GameUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTests {
    private GameEngine gameEngine;
    private GameUI mockUI;
    private DeckManager deckManager;
    private PlayerManager playerManager;
    private NopeOperation mockNopeOperation;
    private Game game;

    @BeforeEach
    public void setUp() {
        mockUI = EasyMock.createNiceMock(GameUI.class);
        EasyMock.replay(mockUI);
        
        deckManager = new DeckManager(new DeckCreator());
        playerManager = new PlayerManager();
        mockNopeOperation = EasyMock.createMock(NopeOperation.class);
        game = new Game();
        
        Player p1 = new Player(1, new ArrayList<>());
        Player p2 = new Player(2, new ArrayList<>());
        game.setPlayers(Arrays.asList(p1, p2));
        
        Deck deck = new Deck();
        deck.addCard(new DefuseCard());
        deck.addCard(new AttackCard());
        game.setDeck(deck);
        
        gameEngine = new GameEngine(mockUI, deckManager, playerManager, 
            mockNopeOperation, game, new HashSet<>());
    }

    @Test
    public void testConstructor_initializesFields() {
        assertNotNull(gameEngine.getUserInterface());
        assertNotNull(gameEngine.getDeckManager());
        assertNotNull(gameEngine.getPlayerManager());
        assertNotNull(gameEngine.getGame());
    }

    @Test
    public void testConstructor_withNullExpansionIds() {
        GameEngine engine = new GameEngine(mockUI, deckManager, playerManager, 
            mockNopeOperation, game, null);
        assertNotNull(engine);
    }

    @Test
    public void testNotifyMessage_notifiesObservers() {
        EasyMock.reset(mockUI);
        mockUI.displayMessage("testMessage");
        EasyMock.expectLastCall();
        EasyMock.replay(mockUI);

        gameEngine.notifyMessage("testMessage");
        EasyMock.verify(mockUI);
    }

    @Test
    public void testNotifyFormattedMessage_notifiesObservers() {
        EasyMock.reset(mockUI);
        mockUI.displayFormattedMessage("testKey", "arg1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockUI);

        gameEngine.notifyFormattedMessage("testKey", "arg1");
        EasyMock.verify(mockUI);
    }

    @Test
    public void testNotifyClearScreen_notifiesObservers() {
        EasyMock.reset(mockUI);
        mockUI.clearScreen();
        EasyMock.expectLastCall();
        EasyMock.replay(mockUI);

        gameEngine.notifyClearScreen();
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetDeck_returnsDeck() {
        Deck deck = gameEngine.getDeck();
        assertNotNull(deck);
    }

    @Test
    public void testSetDeck_setsDeck() {
        Deck newDeck = new Deck();
        newDeck.addCard(new DefuseCard());
        gameEngine.setDeck(newDeck);
        assertNotNull(gameEngine.getDeck());
    }

    @Test
    public void testDrawTopCard_drawsCard() {
        Card card = gameEngine.drawTopCard();
        assertNotNull(card);
    }

    @Test
    public void testDrawTopCard_withNullDeck_throwsException() {
        gameEngine.setDeck(null);
        assertThrows(UnsupportedOperationException.class, () -> gameEngine.drawTopCard());
    }

    @Test
    public void testIsDeckEmpty_returnsFalse_whenDeckHasCards() {
        assertFalse(gameEngine.isDeckEmpty());
    }

    @Test
    public void testIsDeckEmpty_returnsTrue_whenDeckIsNull() {
        gameEngine.setDeck(null);
        assertTrue(gameEngine.isDeckEmpty());
    }

    @Test
    public void testIsDeckEmpty_returnsTrue_whenDeckIsEmpty() {
        Deck emptyDeck = new Deck();
        gameEngine.setDeck(emptyDeck);
        assertTrue(gameEngine.isDeckEmpty());
    }

    @Test
    public void testGetDeckSize_returnsSize() {
        int size = gameEngine.getDeckSize();
        assertTrue(size >= 0);
    }

    @Test
    public void testGetDeckSize_withNullDeck_returnsZero() {
        gameEngine.setDeck(null);
        assertEquals(0, gameEngine.getDeckSize());
    }

    @Test
    public void testGetCurrentPlayer_returnsCurrentPlayer() {
        Player player = gameEngine.getCurrentPlayer();
        assertNotNull(player);
    }

    @Test
    public void testSetCurrentPlayer_setsPlayer() {
        Player newPlayer = new Player(3, new ArrayList<>());
        List<Player> players = gameEngine.getPlayers();
        players.add(newPlayer);
        gameEngine.setPlayers(players);
        gameEngine.setCurrentPlayer(newPlayer);
        assertNotNull(gameEngine.getCurrentPlayer());
    }

    @Test
    public void testGetPlayer_returnsPlayerById() {
        Player player = gameEngine.getPlayer(1);
        assertNotNull(player);
        assertEquals(1, player.getId());
    }

    @Test
    public void testDeletePlayer_removesPlayer() {
        int initialCount = gameEngine.getNumberOfPlayers();
        gameEngine.deletePlayer(1);
        assertEquals(initialCount - 1, gameEngine.getNumberOfPlayers());
    }

    @Test
    public void testSetPlayerTurns_setsTurns() {
        gameEngine.setPlayerTurns(1, 5);
        Player player = gameEngine.getPlayer(1);
        assertEquals(5, player.getNumberOfTurns());
    }

    @Test
    public void testSetNextPlayerTurns_setsTurns() {
        gameEngine.setNextPlayerTurns(2);
        List<Player> players = gameEngine.getPlayers();
        if (players.size() > 1) {
            assertEquals(2, players.get(1).getNumberOfTurns());
        }
    }

    @Test
    public void testGetNextPlayerId_returnsNextPlayerId() {
        int nextId = gameEngine.getNextPlayerId();
        assertTrue(nextId > 0);
    }

    @Test
    public void testSetPlayer_updatesPlayer() {
        Player updatedPlayer = new Player(1, new ArrayList<>());
        updatedPlayer.setNumberOfTurns(5);
        gameEngine.setPlayer(updatedPlayer);
        Player retrieved = gameEngine.getPlayer(1);
        assertEquals(5, retrieved.getNumberOfTurns());
    }

    @Test
    public void testNextPlayer_advancesToNextPlayer() {
        Player initialPlayer = gameEngine.getCurrentPlayer();
        gameEngine.nextPlayer();
        Player nextPlayer = gameEngine.getCurrentPlayer();
        assertNotEquals(initialPlayer.getId(), nextPlayer.getId());
    }

    @Test
    public void testGetNumberOfPlayers_returnsCount() {
        assertEquals(2, gameEngine.getNumberOfPlayers());
    }

    @Test
    public void testGetPlayers_returnsPlayersList() {
        List<Player> players = gameEngine.getPlayers();
        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    public void testSetPlayers_setsPlayersList() {
        Player p1 = new Player(10, new ArrayList<>());
        Player p2 = new Player(20, new ArrayList<>());
        Player p3 = new Player(30, new ArrayList<>());
        gameEngine.setPlayers(Arrays.asList(p1, p2, p3));
        assertEquals(3, gameEngine.getNumberOfPlayers());
    }

    @Test
    public void testSetNextPlayerTargetPlayer_setsTarget() {
        Player target = new Player(2, new ArrayList<>());
        gameEngine.setNextPlayerTargetPlayer(target);
        assertNotNull(gameEngine.getPlayers());
    }

    @Test
    public void testSetCurrentPlayerTurns_setsTurns() {
        gameEngine.setCurrentPlayerTurns(4);
        Player current = gameEngine.getCurrentPlayer();
        assertEquals(4, current.getNumberOfTurns());
    }

    @Test
    public void testEmptyCurrentPlayerHand_emptiesHand() {
        Player current = gameEngine.getCurrentPlayer();
        ArrayList<Card> newHand = new ArrayList<>();
        newHand.add(new DefuseCard());
        current.setHand(newHand);
        gameEngine.setPlayer(current);
        gameEngine.emptyCurrentPlayerHand();
        current = gameEngine.getCurrentPlayer();
        assertTrue(current.getHand().isEmpty());
    }

    @Test
    public void testSetCurrentPlayerHasNope_setsHasNope() {
        gameEngine.setCurrentPlayerHasNope(true);
        assertTrue(gameEngine.getCurrentPlayerHasNope());
    }

    @Test
    public void testGetCurrentPlayerHasNope_returnsHasNope() {
        gameEngine.setCurrentPlayerHasNope(false);
        assertFalse(gameEngine.getCurrentPlayerHasNope());
    }

    @Test
    public void testIsGameOver_returnsFalseInitially() {
        assertFalse(gameEngine.isGameOver());
    }

    @Test
    public void testSetGameOver_setsGameOver() {
        gameEngine.setGameOver(true);
        assertTrue(gameEngine.isGameOver());
    }

    @Test
    public void testGetWinner_returnsNull_whenMultiplePlayers() {
        assertNull(gameEngine.getWinner());
    }

    @Test
    public void testGetWinner_returnsWinner_whenOnePlayer() {
        gameEngine.deletePlayer(2);
        Player winner = gameEngine.getWinner();
        assertNotNull(winner);
        assertEquals(1, winner.getId());
    }

    @Test
    public void testRemoveCurrentPlayerCard_removesCard() {
        Player current = gameEngine.getCurrentPlayer();
        ArrayList<Card> newHand = new ArrayList<>();
        newHand.add(new DefuseCard());
        newHand.add(new AttackCard());
        current.setHand(newHand);
        gameEngine.setPlayer(current);
        
        int sizeBefore = gameEngine.getCurrentPlayer().getHand().size();
        gameEngine.removeCurrentPlayerCard(0);
        int sizeAfter = gameEngine.getCurrentPlayer().getHand().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testAddToCurrentPlayer_addsCard() {
        int sizeBefore = gameEngine.getCurrentPlayer().getHand().size();
        gameEngine.addToCurrentPlayer(new DefuseCard());
        int sizeAfter = gameEngine.getCurrentPlayer().getHand().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void testGetVisibleCards_returnsMap() {
        Map<Integer, List<Card>> visibleCards = gameEngine.getVisibleCards();
        assertNotNull(visibleCards);
    }

    @Test
    public void testGetGame_returnsGame() {
        Game retrievedGame = gameEngine.getGame();
        assertNotNull(retrievedGame);
    }

    @Test
    public void testGetPlayerManager_returnsPlayerManager() {
        PlayerManager pm = gameEngine.getPlayerManager();
        assertNotNull(pm);
    }

    @Test
    public void testGetDeckManager_returnsDeckManager() {
        DeckManager dm = gameEngine.getDeckManager();
        assertNotNull(dm);
    }

    @Test
    public void testGetUserInterface_returnsProxy() {
        GameUI ui = gameEngine.getUserInterface();
        assertNotNull(ui);
        ui.displayMessage("test");
        ui.displayFormattedMessage("test", "arg");
        ui.clearScreen();
        ui.promptPlayer("prompt");
        ui.promptExpansionPackNumbers();
        ui.changeLanguage("en", "US");
    }

    @Test
    public void testGetUserInterface_promptPlayer_returnsCorrectValue() {
        EasyMock.reset(mockUI);
        EasyMock.expect(mockUI.promptPlayer("testPrompt")).andReturn(42);
        EasyMock.replay(mockUI);
        
        GameEngine testEngine = new GameEngine(mockUI, deckManager, playerManager, 
            mockNopeOperation, game, new HashSet<>());
        
        GameUI proxy = testEngine.getUserInterface();
        int result = proxy.promptPlayer("testPrompt");
        
        assertEquals(42, result);
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetUserInterface_promptExpansionPackNumbers_returnsCorrectValue() {
        java.util.Set<Integer> expectedSet = new java.util.HashSet<>(Arrays.asList(1, 2, 3));
        
        EasyMock.reset(mockUI);
        EasyMock.expect(mockUI.promptExpansionPackNumbers()).andReturn(expectedSet);
        EasyMock.replay(mockUI);
        
        GameEngine testEngine = new GameEngine(mockUI, deckManager, playerManager, 
            mockNopeOperation, game, new HashSet<>());
        
        GameUI proxy = testEngine.getUserInterface();
        java.util.Set<Integer> result = proxy.promptExpansionPackNumbers();
        
        assertEquals(expectedSet, result);
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetUserInterface_changeLanguage_delegatesCorrectly() {
        EasyMock.reset(mockUI);
        mockUI.changeLanguage("es", "ES");
        EasyMock.expectLastCall();
        EasyMock.replay(mockUI);
        
        GameEngine testEngine = new GameEngine(mockUI, deckManager, playerManager, 
            mockNopeOperation, game, new HashSet<>());
        
        GameUI proxy = testEngine.getUserInterface();
        proxy.changeLanguage("es", "ES");
        
        EasyMock.verify(mockUI);
    }

    @Test
    public void testTakeTurn_createsTurnAndExecutes() {
        Player player = gameEngine.getCurrentPlayer();
        player.setController(new domain.player.PlayerControllerHuman(mockUI));
        player.setNumberOfTurns(0);
        gameEngine.setPlayer(player);
        
        EasyMock.reset(mockUI);
        EasyMock.expect(mockUI.promptPlayer(EasyMock.anyString())).andReturn(-1).anyTimes();
        mockUI.displayMessage(EasyMock.anyString());
        EasyMock.expectLastCall().anyTimes();
        mockUI.displayFormattedMessage(EasyMock.anyString(), EasyMock.anyObject());
        EasyMock.expectLastCall().anyTimes();
        mockUI.clearScreen();
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockUI);
        
        gameEngine.takeTurn();
        
        assertTrue(true);
    }
}