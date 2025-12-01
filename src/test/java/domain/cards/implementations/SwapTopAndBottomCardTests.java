package domain.cards.implementations;

import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class SwapTopAndBottomCardTests {
    private SwapTopAndBottomCard swapTopAndBottomCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private DeckManager mockDeckManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        swapTopAndBottomCard = new SwapTopAndBottomCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
    }

    @Test
    public void testGetName_returnsSwapTopAndBottom() {
        assertEquals("Swap Top And Bottom", swapTopAndBottomCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = SwapTopAndBottomCard.getCounts();
        assertArrayEquals(new int[]{2, 3, 4}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = SwapTopAndBottomCard.getCounts();
        int[] counts2 = SwapTopAndBottomCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.swapTopAndBottom(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Swap Top And Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        swapTopAndBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_swapsTopAndBottom() {
        mockContext.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.swapTopAndBottom(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Swap Top And Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        swapTopAndBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_updatesDeck() {
        mockContext.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.swapTopAndBottom(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Swap Top And Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        swapTopAndBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_removesCardFromPlayer() {
        mockContext.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.swapTopAndBottom(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Swap Top And Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        swapTopAndBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }
}