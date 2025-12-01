package domain.cards.expansions;

import domain.deck.Deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BaseGameExpansionTests {
    private BaseGameExpansion baseGameExpansion;

    @BeforeEach
    public void setUp() {
        baseGameExpansion = new BaseGameExpansion();
    }

    @Test
    public void testGetId_returnsBase() {
        assertEquals("base", baseGameExpansion.getId());
    }

    @Test
    public void testGetDisplayName_returnsBaseGame() {
        assertEquals("Base Game", baseGameExpansion.getDisplayName());
    }

    @Test
    public void testGetSelectionNumber_returnsZero() {
        assertEquals(0, baseGameExpansion.getSelectionNumber());
    }

    @Test
    public void testGetMaxPlayers_returnsFive() {
        assertEquals(5, baseGameExpansion.getMaxPlayers());
    }

    @Test
    public void testAddCardsToDeck_addsCards() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        baseGameExpansion.addCardsToDeck(realDeck, 4);
        
        assertTrue(realDeck.numberOfCards() > initialSize);
    }

    @Test
    public void testAddCardsToDeck_withTwoPlayers() {
        Deck realDeck = new Deck();
        
        baseGameExpansion.addCardsToDeck(realDeck, 2);
        
        assertTrue(realDeck.numberOfCards() > 0);
    }

    @Test
    public void testAddCardsToDeck_withFivePlayers() {
        Deck realDeck = new Deck();
        
        baseGameExpansion.addCardsToDeck(realDeck, 5);
        
        assertTrue(realDeck.numberOfCards() > 0);
    }

    @Test
    public void testAddPostDealCards_twoPlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        baseGameExpansion.addPostDealCards(realDeck, 2);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_threePlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        baseGameExpansion.addPostDealCards(realDeck, 3);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_fivePlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        baseGameExpansion.addPostDealCards(realDeck, 5);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_sixPlayers_noExtraDefuse() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        baseGameExpansion.addPostDealCards(realDeck, 6);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testIsBaseGameModifier_returnsFalse() {
        assertFalse(baseGameExpansion.isBaseGameModifier());
    }

    @Test
    public void testGetAdditionalExplodingKittens_returnsZero() {
        assertEquals(0, baseGameExpansion.getAdditionalExplodingKittens());
    }

    @Test
    public void testGetAdditionalDefuseCards_returnsZero() {
        assertEquals(0, baseGameExpansion.getAdditionalDefuseCards(4));
    }

    @Test
    public void testDefaultAddPostDealCards_empty() {
        ExpansionStrategy mockStrategy = new ExpansionStrategy() {
            @Override
            public String getId() { return "test"; }
            @Override
            public String getDisplayName() { return "Test"; }
            @Override
            public int getSelectionNumber() { return 99; }
            @Override
            public void addCardsToDeck(Deck deck, int playerCount) { }
        };
        
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        mockStrategy.addPostDealCards(realDeck, 4);
        
        assertEquals(initialSize, realDeck.numberOfCards());
    }
}