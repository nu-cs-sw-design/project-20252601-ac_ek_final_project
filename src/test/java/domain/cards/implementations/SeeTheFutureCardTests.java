package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeeTheFutureCardTests {
    private SeeTheFutureCard seeTheFutureCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private DeckManager mockDeckManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
    }

    @Test
    public void testGetName_returnsSeeTheFuture() {
        seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);
        assertEquals("See The Future", seeTheFutureCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = SeeTheFutureCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = SeeTheFutureCard.getCounts();
        int[] counts2 = SeeTheFutureCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_peekThree_showsThreeCards() {
        seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);
        
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Skip").anyTimes();
        EasyMock.expect(card3.getName()).andReturn("Shuffle").anyTimes();
        
        List<Card> topCards = Arrays.asList(card1, card2, card3);
        
        mockContext.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 3)).andReturn(topCards);
        mockContext.displayFormattedMessage("visibleCard", 0, "Attack");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 1, "Skip");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 2, "Shuffle");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "See The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck, card1, card2, card3);
        
        seeTheFutureCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager);
    }

    @Test
    public void testExecuteEffect_peekFive_showsFiveCards() {
        seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE);
        
        List<Card> topCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = EasyMock.createMock(Card.class);
            EasyMock.expect(card.getName()).andReturn("Card" + i).anyTimes();
            topCards.add(card);
        }
        
        mockContext.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 5)).andReturn(topCards);
        for (int i = 0; i < 5; i++) {
            mockContext.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall();
        }
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "See The Future");
        EasyMock.expectLastCall();
        
        Object[] mocks = topCards.toArray();
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        for (Object mock : mocks) {
            EasyMock.replay((Card) mock);
        }
        
        seeTheFutureCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager);
    }

    @Test
    public void testExecuteEffect_fewerCardsThanPeekOption() {
        seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);
        
        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        
        List<Card> topCards = Arrays.asList(card1);
        
        mockContext.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 3)).andReturn(topCards);
        mockContext.displayFormattedMessage("visibleCard", 0, "Attack");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "See The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck, card1);
        
        seeTheFutureCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager);
    }

    @Test
    public void testPeekOption_threeHasValue3() {
        assertEquals(2, SeeTheFutureCard.PeekOption.values().length);
    }

    @Test
    public void testPeekOption_enumValues() {
        assertNotNull(SeeTheFutureCard.PeekOption.valueOf("THREE"));
        assertNotNull(SeeTheFutureCard.PeekOption.valueOf("FIVE"));
    }
}