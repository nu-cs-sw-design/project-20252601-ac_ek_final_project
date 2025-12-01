package domain.cards;

import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardManagerTests {
    private CardManager cardManager;
    private GameContext mockContext;
    private PlayerManager mockPlayerManager;
    private DeckManager mockDeckManager;

    @BeforeEach
    public void setUp() {
        cardManager = new CardManager();
        mockContext = EasyMock.createMock(GameContext.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
    }

    @Test
    public void testCopyConstructor() {
        CardManager original = new CardManager();
        CardManager copy = new CardManager(original);
        assertNotNull(copy);
    }

    @Test
    public void testChooseValidTargetPlayer_validChoiceFirstTry() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player targetPlayer = new Player(2, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(2);
        EasyMock.expect(mockContext.getPlayer(2)).andReturn(targetPlayer);
        EasyMock.replay(mockContext);
        
        Player result = cardManager.chooseValidTargetPlayer(mockContext, "playerID");
        
        assertEquals(targetPlayer, result);
        EasyMock.verify(mockContext);
    }

    @Test
    public void testChooseValidTargetPlayer_chooseSelfThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player targetPlayer = new Player(2, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(1);
        mockContext.displayMessage("chosenSelfError");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(2);
        EasyMock.expect(mockContext.getPlayer(2)).andReturn(targetPlayer);
        EasyMock.replay(mockContext);
        
        Player result = cardManager.chooseValidTargetPlayer(mockContext, "playerID");
        
        assertEquals(targetPlayer, result);
        EasyMock.verify(mockContext);
    }

    @Test
    public void testChooseValidTargetPlayer_invalidPlayerThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player targetPlayer = new Player(2, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(99);
        EasyMock.expect(mockContext.getPlayer(99)).andThrow(new RuntimeException("Player not found"));
        mockContext.displayMessage("invalidPlayerID");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(2);
        EasyMock.expect(mockContext.getPlayer(2)).andReturn(targetPlayer);
        EasyMock.replay(mockContext);
        
        Player result = cardManager.chooseValidTargetPlayer(mockContext, "playerID");
        
        assertEquals(targetPlayer, result);
        EasyMock.verify(mockContext);
    }

    @Test
    public void testChooseValidTargetPlayer_nullPlayerThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player targetPlayer = new Player(2, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(99);
        EasyMock.expect(mockContext.getPlayer(99)).andReturn(null);
        mockContext.displayMessage("invalidPlayerID");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("playerID")).andReturn(2);
        EasyMock.expect(mockContext.getPlayer(2)).andReturn(targetPlayer);
        EasyMock.replay(mockContext);
        
        Player result = cardManager.chooseValidTargetPlayer(mockContext, "playerID");
        
        assertEquals(targetPlayer, result);
        EasyMock.verify(mockContext);
    }

    @Test
    public void testRemoveCardFromCurrentPlayer_cardExists() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Attack")).andReturn(0);
        mockContext.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext, mockPlayerManager);
        
        cardManager.removeCardFromCurrentPlayer(mockContext, "Attack");
        
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testEliminatePlayer_removesAndRotates() {
        Player player = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, new ArrayList<>()));
        players.add(new Player(3, new ArrayList<>()));
        
        mockContext.deletePlayer(2);
        EasyMock.expectLastCall();
        mockContext.displayMessage("playerEliminated");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        mockContext.nextPlayer();
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext);
        
        cardManager.eliminatePlayer(mockContext, player);
        
        EasyMock.verify(mockContext);
    }

    @Test
    public void testEliminatePlayer_threePlayersRemaining() {
        Player player = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, new ArrayList<>()));
        players.add(new Player(3, new ArrayList<>()));
        players.add(new Player(4, new ArrayList<>()));
        
        mockContext.deletePlayer(2);
        EasyMock.expectLastCall();
        mockContext.displayMessage("playerEliminated");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        mockContext.nextPlayer();
        EasyMock.expectLastCall().times(2);
        EasyMock.replay(mockContext);
        
        cardManager.eliminatePlayer(mockContext, player);
        
        EasyMock.verify(mockContext);
    }

    @Test
    public void testInsertCardAtValidIndex_validIndexFirstTry() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Card mockCard = EasyMock.createMock(Card.class);
        
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockContext.promptPlayer("whereToInsert")).andReturn(5);
        mockDeckManager.insertCardAtIndex(mockDeck, mockCard, 5);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.displayMessage("insertedCard");
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext, mockDeckManager, mockDeck);
        
        cardManager.insertCardAtValidIndex(mockContext, mockCard);
        
        EasyMock.verify(mockContext, mockDeckManager);
    }

    @Test
    public void testInsertCardAtValidIndex_invalidThenValid() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Card mockCard = EasyMock.createMock(Card.class);
        
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockContext.promptPlayer("whereToInsert")).andReturn(-1);
        mockDeckManager.insertCardAtIndex(mockDeck, mockCard, -1);
        EasyMock.expectLastCall().andThrow(new IndexOutOfBoundsException());
        mockContext.displayMessage("indexOutOfBounds");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("whereToInsert")).andReturn(5);
        mockDeckManager.insertCardAtIndex(mockDeck, mockCard, 5);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.displayMessage("insertedCard");
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext, mockDeckManager, mockDeck);
        
        cardManager.insertCardAtValidIndex(mockContext, mockCard);
        
        EasyMock.verify(mockContext, mockDeckManager);
    }

    @Test
    public void testTakeCardFromPlayer_takesCard() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card mockCard = EasyMock.createMock(Card.class);
        
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.promptPlayer("takeCard")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(chosenPlayer, 0)).andReturn(mockCard);
        EasyMock.expect(mockPlayerManager.removeCard(chosenPlayer, 0)).andReturn(0);
        EasyMock.expect(mockPlayerManager.addCard(currentPlayer, mockCard)).andReturn(1);
        mockContext.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext, mockPlayerManager, mockCard);
        
        Card result = cardManager.takeCardFromPlayer(mockContext, chosenPlayer);
        
        assertEquals(mockCard, result);
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testCalculateAttackTurns_currentTurnIsOne_returnsAddedTurns() {
        int result = cardManager.calculateAttackTurns(1, 2);
        assertEquals(2, result);
    }

    @Test
    public void testCalculateAttackTurns_currentTurnIsTwo_addsTurns() {
        int result = cardManager.calculateAttackTurns(2, 2);
        assertEquals(4, result);
    }

    @Test
    public void testCalculateAttackTurns_currentTurnIsThree_addsTurns() {
        int result = cardManager.calculateAttackTurns(3, 2);
        assertEquals(5, result);
    }

    @Test
    public void testCalculateAttackTurns_zeroTurnsToAdd() {
        int result = cardManager.calculateAttackTurns(2, 0);
        assertEquals(2, result);
    }

    @Test
    public void testCalculateAttackTurns_currentTurnIsOne_addThree() {
        int result = cardManager.calculateAttackTurns(1, 3);
        assertEquals(3, result);
    }

    @Test
    public void testCollectUniqueIndexes_allUnique() {
        EasyMock.expect(mockContext.promptPlayer("newIndex:3")).andReturn(0);
        EasyMock.expect(mockContext.promptPlayer("newIndex:3")).andReturn(1);
        EasyMock.expect(mockContext.promptPlayer("newIndex:3")).andReturn(2);
        mockContext.displayFormattedMessage("cardAtIndex", 0);
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("cardAtIndex", 1);
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("cardAtIndex", 2);
        EasyMock.expectLastCall();
        EasyMock.replay(mockContext);
        
        List<Integer> result = cardManager.collectUniqueIndexes(mockContext, 3);
        
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(1, result.get(1));
        assertEquals(2, result.get(2));
        EasyMock.verify(mockContext);
    }

    @Test
    public void testCollectUniqueIndexes_invalidIndexThenValid() {
        mockContext.displayFormattedMessage("cardAtIndex", 0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(5);
        mockContext.displayMessage("indexOutOfBounds");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(0);
        mockContext.displayFormattedMessage("cardAtIndex", 1);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(1);
        EasyMock.replay(mockContext);
        
        List<Integer> result = cardManager.collectUniqueIndexes(mockContext, 2);
        
        assertEquals(2, result.size());
        EasyMock.verify(mockContext);
    }

    @Test
    public void testCollectUniqueIndexes_duplicatesThenUnique() {
        mockContext.displayFormattedMessage("cardAtIndex", 0);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(0);
        mockContext.displayFormattedMessage("cardAtIndex", 1);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(0);
        mockContext.displayMessage("newIndexesUnique");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(0);
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(1);
        EasyMock.replay(mockContext);
        
        List<Integer> result = cardManager.collectUniqueIndexes(mockContext, 2);
        
        assertEquals(2, result.size());
        assertEquals(0, result.get(0));
        assertEquals(1, result.get(1));
        EasyMock.verify(mockContext);
    }

    @Test
    public void testCollectUniqueIndexes_negativeIndexInvalid() {
        mockContext.displayFormattedMessage("cardAtIndex", 0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(-1);
        mockContext.displayMessage("indexOutOfBounds");
        EasyMock.expectLastCall();
        mockContext.displayMessage("tryAgain");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(0);
        mockContext.displayFormattedMessage("cardAtIndex", 1);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.promptPlayer("newIndex:2")).andReturn(1);
        EasyMock.replay(mockContext);
        
        List<Integer> result = cardManager.collectUniqueIndexes(mockContext, 2);
        
        assertEquals(2, result.size());
        EasyMock.verify(mockContext);
    }
}