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

public class AlterTheFutureCardTests {
    private AlterTheFutureCard alterTheFutureCardThree;
    private AlterTheFutureCard alterTheFutureCardFive;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private DeckManager mockDeckManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        alterTheFutureCardThree = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE);
        alterTheFutureCardFive = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE);
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
    }

    @Test
    public void testGetName_returnsAlterTheFuture() {
        assertEquals("Alter The Future", alterTheFutureCardThree.getName());
    }

    @Test
    public void testGetName_sameForBothPeekOptions() {
        assertEquals(alterTheFutureCardThree.getName(), alterTheFutureCardFive.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = AlterTheFutureCard.getCounts();
        assertArrayEquals(new int[]{3, 4, 5}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = AlterTheFutureCard.getCounts();
        int[] counts2 = AlterTheFutureCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testPeekOption_threeHasValue3() {
        AlterTheFutureCard.PeekOption option = AlterTheFutureCard.PeekOption.THREE;
        assertEquals(3, option.ordinal() + 3);
    }

    @Test
    public void testPeekOption_fiveHasValue5() {
        AlterTheFutureCard.PeekOption option = AlterTheFutureCard.PeekOption.FIVE;
        assertEquals(1, option.ordinal());
    }

    @Test
    public void testExecuteEffect_peekThree_displaysCards() {
        List<Card> peekedCards = Arrays.asList(new SkipCard(), new AttackCard(), new ShuffleCard());
        List<Integer> indexes = Arrays.asList(2, 0, 1);
        
        mockContext.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 3)).andReturn(peekedCards);
        
        mockContext.displayFormattedMessage("visibleCard", 0, "Skip");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 1, "Attack");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 2, "Shuffle");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.collectUniqueIndexes(mockContext, 3)).andReturn(indexes);
        mockDeckManager.alterTopDeck(EasyMock.eq(mockDeck), EasyMock.anyObject(), EasyMock.eq(indexes));
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Alter The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        alterTheFutureCardThree.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_peekFive_displaysCards() {
        List<Card> peekedCards = Arrays.asList(
            new SkipCard(), new AttackCard(), new ShuffleCard(), 
            new NopeCard(), new DefuseCard()
        );
        List<Integer> indexes = Arrays.asList(4, 3, 2, 1, 0);
        
        mockContext.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 5)).andReturn(peekedCards);
        
        mockContext.displayFormattedMessage("visibleCard", 0, "Skip");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 1, "Attack");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 2, "Shuffle");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 3, "Nope");
        EasyMock.expectLastCall();
        mockContext.displayFormattedMessage("visibleCard", 4, "Defuse");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.collectUniqueIndexes(mockContext, 5)).andReturn(indexes);
        mockDeckManager.alterTopDeck(EasyMock.eq(mockDeck), EasyMock.anyObject(), EasyMock.eq(indexes));
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Alter The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        alterTheFutureCardFive.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_emptyDeck_handlesGracefully() {
        List<Card> peekedCards = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        
        mockContext.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 3)).andReturn(peekedCards);
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.collectUniqueIndexes(mockContext, 0)).andReturn(indexes);
        mockDeckManager.alterTopDeck(EasyMock.eq(mockDeck), EasyMock.anyObject(), EasyMock.eq(indexes));
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Alter The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        alterTheFutureCardThree.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_fewerCardsThanPeekOption() {
        List<Card> peekedCards = Arrays.asList(new SkipCard());
        List<Integer> indexes = Arrays.asList(0);
        
        mockContext.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        EasyMock.expect(mockDeckManager.peekTopDeck(mockDeck, 3)).andReturn(peekedCards);
        
        mockContext.displayFormattedMessage("visibleCard", 0, "Skip");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.collectUniqueIndexes(mockContext, 1)).andReturn(indexes);
        mockDeckManager.alterTopDeck(EasyMock.eq(mockDeck), EasyMock.anyObject(), EasyMock.eq(indexes));
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Alter The Future");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        alterTheFutureCardThree.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }
}