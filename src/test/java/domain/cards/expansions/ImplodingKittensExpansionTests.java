package domain.cards.expansions;

import domain.deck.Deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ImplodingKittensExpansionTests {
    private ImplodingKittensExpansion implodingKittensExpansion;

    @BeforeEach
    public void setUp() {
        implodingKittensExpansion = new ImplodingKittensExpansion();
    }

    @Test
    public void testGetId_returnsImplodingKittens() {
        assertEquals("imploding_kittens", implodingKittensExpansion.getId());
    }

    @Test
    public void testGetDisplayName_returnsImplodingKittens() {
        assertEquals("Imploding Kittens", implodingKittensExpansion.getDisplayName());
    }

    @Test
    public void testGetSelectionNumber_returnsThree() {
        assertEquals(3, implodingKittensExpansion.getSelectionNumber());
    }

    @Test
    public void testGetMaxPlayers_returnsFive() {
        assertEquals(5, implodingKittensExpansion.getMaxPlayers());
    }

    @Test
    public void testAddCardsToDeck_addsCards() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        implodingKittensExpansion.addCardsToDeck(realDeck, 4);
        
        assertEquals(initialSize + 17, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_withTwoPlayers() {
        Deck realDeck = new Deck();
        
        implodingKittensExpansion.addCardsToDeck(realDeck, 2);
        
        assertEquals(17, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_withFivePlayers() {
        Deck realDeck = new Deck();
        
        implodingKittensExpansion.addCardsToDeck(realDeck, 5);
        
        assertEquals(17, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_addsImplodingKitten() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        implodingKittensExpansion.addPostDealCards(realDeck, 4);
        
        assertEquals(initialSize + 1, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_twoPlayers() {
        Deck realDeck = new Deck();
        
        implodingKittensExpansion.addPostDealCards(realDeck, 2);
        
        assertEquals(1, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_tenPlayers() {
        Deck realDeck = new Deck();
        
        implodingKittensExpansion.addPostDealCards(realDeck, 10);
        
        assertEquals(1, realDeck.numberOfCards());
    }

    @Test
    public void testIsBaseGameModifier_returnsFalse() {
        assertFalse(implodingKittensExpansion.isBaseGameModifier());
    }

    @Test
    public void testGetAdditionalExplodingKittens_returnsZero() {
        assertEquals(0, implodingKittensExpansion.getAdditionalExplodingKittens());
    }

    @Test
    public void testGetAdditionalDefuseCards_returnsZero() {
        assertEquals(0, implodingKittensExpansion.getAdditionalDefuseCards(4));
    }
}