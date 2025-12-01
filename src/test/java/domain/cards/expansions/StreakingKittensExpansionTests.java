package domain.cards.expansions;

import domain.deck.Deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StreakingKittensExpansionTests {
    private StreakingKittensExpansion streakingKittensExpansion;

    @BeforeEach
    public void setUp() {
        streakingKittensExpansion = new StreakingKittensExpansion();
    }

    @Test
    public void testGetId_returnsStreakingKittens() {
        assertEquals("streaking_kittens", streakingKittensExpansion.getId());
    }

    @Test
    public void testGetDisplayName_returnsStreakingKittens() {
        assertEquals("Streaking Kittens", streakingKittensExpansion.getDisplayName());
    }

    @Test
    public void testGetSelectionNumber_returnsTwo() {
        assertEquals(2, streakingKittensExpansion.getSelectionNumber());
    }

    @Test
    public void testGetMaxPlayers_returnsFive() {
        assertEquals(5, streakingKittensExpansion.getMaxPlayers());
    }

    @Test
    public void testGetAdditionalExplodingKittens_returnsOne() {
        assertEquals(1, streakingKittensExpansion.getAdditionalExplodingKittens());
    }

    @Test
    public void testAddCardsToDeck_addsCards() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        streakingKittensExpansion.addCardsToDeck(realDeck, 4);
        
        assertEquals(initialSize + 14, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_withTwoPlayers() {
        Deck realDeck = new Deck();
        
        streakingKittensExpansion.addCardsToDeck(realDeck, 2);
        
        assertEquals(14, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_withFivePlayers() {
        Deck realDeck = new Deck();
        
        streakingKittensExpansion.addCardsToDeck(realDeck, 5);
        
        assertEquals(14, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_doesNothing() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        streakingKittensExpansion.addPostDealCards(realDeck, 4);
        
        assertEquals(initialSize, realDeck.numberOfCards());
    }

    @Test
    public void testIsBaseGameModifier_returnsFalse() {
        assertFalse(streakingKittensExpansion.isBaseGameModifier());
    }

    @Test
    public void testGetAdditionalDefuseCards_returnsZero() {
        assertEquals(0, streakingKittensExpansion.getAdditionalDefuseCards(4));
    }

    @Test
    public void testGetAdditionalDefuseCards_sameForAllPlayerCounts() {
        assertEquals(0, streakingKittensExpansion.getAdditionalDefuseCards(2));
        assertEquals(0, streakingKittensExpansion.getAdditionalDefuseCards(5));
        assertEquals(0, streakingKittensExpansion.getAdditionalDefuseCards(10));
    }
}