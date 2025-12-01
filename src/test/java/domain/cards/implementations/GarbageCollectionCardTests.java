package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GarbageCollectionCardTests {
    private GarbageCollectionCard garbageCollectionCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        garbageCollectionCard = new GarbageCollectionCard();
        mockContext = EasyMock.createNiceMock(GameContext.class);
        mockCardManager = EasyMock.createNiceMock(CardManager.class);
        mockPlayerManager = EasyMock.createNiceMock(PlayerManager.class);
        mockDeck = EasyMock.createNiceMock(Deck.class);
    }

    @Test
    public void testGetName_returnsGarbageCollection() {
        assertEquals("Garbage Collection", garbageCollectionCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = GarbageCollectionCard.getCounts();
        assertArrayEquals(new int[]{2, 3, 4}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = GarbageCollectionCard.getCounts();
        int[] counts2 = GarbageCollectionCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_singlePlayer() {
        Player player1 = new Player(1, new ArrayList<>());
        Card discardedCard = new SkipCard();
        List<Player> players = Arrays.asList(player1);
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(player1, 0)).andReturn(discardedCard);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockDeck);
        
        garbageCollectionCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_multiplePlayers() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        Player player3 = new Player(3, new ArrayList<>());
        Card card1 = new SkipCard();
        Card card2 = new AttackCard();
        Card card3 = new ShuffleCard();
        List<Player> players = Arrays.asList(player1, player2, player3);
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(player1, 0)).andReturn(card1);
        
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(1);
        EasyMock.expect(mockPlayerManager.chooseCard(player2, 1)).andReturn(card2);
        
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(2);
        EasyMock.expect(mockPlayerManager.chooseCard(player3, 2)).andReturn(card3);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockDeck);
        
        garbageCollectionCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_shufflesDeck() {
        Player player1 = new Player(1, new ArrayList<>());
        Card discardedCard = new SkipCard();
        List<Player> players = Arrays.asList(player1);
        
        Deck strictMockDeck = EasyMock.createMock(Deck.class);
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockContext.getDeck()).andReturn(strictMockDeck);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(player1, 0)).andReturn(discardedCard);
        
        strictMockDeck.addCard(discardedCard);
        EasyMock.expectLastCall();
        strictMockDeck.shuffle();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, strictMockDeck);
        
        garbageCollectionCard.executeEffect(mockContext);
        
        EasyMock.verify(strictMockDeck);
    }

    @Test
    public void testExecuteEffect_removesCardFirst() {
        Player player1 = new Player(1, new ArrayList<>());
        Card discardedCard = new SkipCard();
        List<Player> players = Arrays.asList(player1);
        
        CardManager strictMockCardManager = EasyMock.createStrictMock(CardManager.class);
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(strictMockCardManager);
        strictMockCardManager.removeCardFromCurrentPlayer(mockContext, "Garbage Collection");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        EasyMock.expect(mockContext.promptPlayer("discard")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(player1, 0)).andReturn(discardedCard);
        
        EasyMock.replay(mockContext, strictMockCardManager, mockPlayerManager, mockDeck);
        
        garbageCollectionCard.executeEffect(mockContext);
        
        EasyMock.verify(strictMockCardManager);
    }
}