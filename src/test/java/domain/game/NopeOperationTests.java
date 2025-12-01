package domain.game;

import domain.cards.Card;
import domain.cards.implementations.NopeCard;
import domain.player.Player;
import domain.player.PlayerController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NopeOperationTests {
    private NopeOperation nopeOperation;
    private GameEngine mockEngine;

    @BeforeEach
    public void setUp() {
        nopeOperation = new NopeOperation();
        mockEngine = EasyMock.createNiceMock(GameEngine.class);
    }

    @Test
    public void testCheckForNopeInterruption_noPlayerHasNope_returnsFalse() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.replay(mockEngine);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCheckForNopeInterruption_playerHasNopeButDoesNotPlay_returnsFalse() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new NopeCard());
        Player player2 = new Player(2, hand);
        
        PlayerController mockController = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController);
        
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockController.getNopeAction(mockEngine)).andReturn(Action.pass());
        EasyMock.replay(mockEngine, mockController);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(mockEngine, mockController);
    }

    @Test
    public void testCheckForNopeInterruption_playerPlaysNope_noCounterNope_returnsTrue() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new NopeCard());
        Player player2 = new Player(2, hand);
        
        Player player3 = new Player(3, new ArrayList<>());
        
        PlayerController mockController = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController);
        
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockController.getNopeAction(mockEngine)).andReturn(Action.nope());
        EasyMock.replay(mockEngine, mockController);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertTrue(result);
        EasyMock.verify(mockEngine, mockController);
    }

    @Test
    public void testCheckForNopeInterruption_currentPlayerExcluded() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new NopeCard());
        Player currentPlayer = new Player(1, hand);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.replay(mockEngine);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCheckForNopeInterruption_multiplePlayersWithNope_firstPlays() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand2 = new ArrayList<>();
        hand2.add(new NopeCard());
        Player player2 = new Player(2, hand2);
        
        Player player3 = new Player(3, new ArrayList<>());
        
        PlayerController mockController2 = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController2);
        
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockController2.getNopeAction(mockEngine)).andReturn(Action.nope());
        EasyMock.replay(mockEngine, mockController2);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertTrue(result);
        EasyMock.verify(mockEngine, mockController2);
    }

    @Test
    public void testCheckForNopeInterruption_nopeCounteredByNope_returnsFalse() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand2 = new ArrayList<>();
        hand2.add(new NopeCard());
        Player player2 = new Player(2, hand2);
        
        ArrayList<Card> hand3 = new ArrayList<>();
        hand3.add(new NopeCard());
        Player player3 = new Player(3, hand3);
        
        PlayerController mockController2 = EasyMock.createNiceMock(PlayerController.class);
        PlayerController mockController3 = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController2);
        player3.setController(mockController3);
        
        List<Player> players = new ArrayList<>(Arrays.asList(currentPlayer, player2, player3));
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockController2.getNopeAction(mockEngine)).andReturn(Action.nope());
        EasyMock.expect(mockController3.getNopeAction(mockEngine)).andReturn(Action.nope());
        
        EasyMock.replay(mockEngine, mockController2, mockController3);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(mockEngine, mockController2, mockController3);
    }

    @Test
    public void testCheckForNopeInterruption_singlePlayer_returnsFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new NopeCard());
        Player currentPlayer = new Player(1, hand);
        List<Player> players = Arrays.asList(currentPlayer);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.replay(mockEngine);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(mockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCheckForNopeInterruption_nopeExecuted_verifyPlayCardCalled() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand = new ArrayList<>();
        NopeCard nopeCard = new NopeCard();
        hand.add(nopeCard);
        Player player2 = new Player(2, hand);
        
        Player player3 = new Player(3, new ArrayList<>());
        
        PlayerController mockController = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController);
        
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        GameEngine strictMockEngine = EasyMock.createNiceMock(GameEngine.class);
        
        EasyMock.expect(strictMockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(strictMockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockController.getNopeAction(strictMockEngine)).andReturn(Action.nope());
        
        strictMockEngine.notifyMessage("nopeCard");
        EasyMock.expectLastCall();
        
        EasyMock.replay(strictMockEngine, mockController);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(strictMockEngine, mockCard);
        
        assertTrue(result);
        EasyMock.verify(strictMockEngine, mockController);
    }

    @Test
    public void testCheckForNopeInterruption_nopeExecuted_verifyNotifyMessageCalled() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand = new ArrayList<>();
        NopeCard nopeCard = new NopeCard();
        hand.add(nopeCard);
        Player player2 = new Player(2, hand);
        
        Player player3 = new Player(3, new ArrayList<>());
        
        PlayerController mockController = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController);
        
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        GameEngine strictMockEngine = EasyMock.createMock(GameEngine.class);
        
        EasyMock.expect(strictMockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(strictMockEngine.getPlayers()).andReturn(players).anyTimes();
        strictMockEngine.notifyFormattedMessage("player", 2);
        EasyMock.expectLastCall();
        EasyMock.expect(mockController.getNopeAction(strictMockEngine)).andReturn(Action.nope());
        
        strictMockEngine.setPlayer(EasyMock.eq(player2));
        EasyMock.expectLastCall();
        
        strictMockEngine.notifyMessage("nopeCard");
        EasyMock.expectLastCall();
        
        strictMockEngine.notifyMessage("playedNope");
        EasyMock.expectLastCall();
        
        EasyMock.replay(strictMockEngine, mockController);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(strictMockEngine, mockCard);
        
        assertTrue(result);
        EasyMock.verify(strictMockEngine, mockController);
    }

    @Test
    public void testCheckForNopeInterruption_notifyFormattedMessage_calledForPlayerWithNope() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new NopeCard());
        Player player2 = new Player(2, hand);
        
        PlayerController mockController = EasyMock.createNiceMock(PlayerController.class);
        player2.setController(mockController);
        
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        GameEngine strictMockEngine = EasyMock.createMock(GameEngine.class);
        
        EasyMock.expect(strictMockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(strictMockEngine.getPlayers()).andReturn(players).anyTimes();
        
        strictMockEngine.notifyFormattedMessage("player", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockController.getNopeAction(strictMockEngine)).andReturn(Action.pass());
        
        EasyMock.replay(strictMockEngine, mockController);
        
        Card mockCard = EasyMock.createNiceMock(Card.class);
        boolean result = nopeOperation.checkForNopeInterruption(strictMockEngine, mockCard);
        
        assertFalse(result);
        EasyMock.verify(strictMockEngine, mockController);
    }
}