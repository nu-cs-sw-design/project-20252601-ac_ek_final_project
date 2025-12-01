package domain.cards.implementations;

import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class ShuffleCardTests {
    private ShuffleCard shuffleCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        shuffleCard = new ShuffleCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
    }

    @Test
    public void testGetName_returnsShuffle() {
        assertEquals("Shuffle", shuffleCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = ShuffleCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = ShuffleCard.getCounts();
        int[] counts2 = ShuffleCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_shufflesDeckAndRemovesCard() {
        mockContext.displayMessage("shuffleCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        mockDeck.shuffle();
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Shuffle");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck);
        
        shuffleCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck);
    }
}