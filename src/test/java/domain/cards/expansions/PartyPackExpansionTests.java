package domain.cards.expansions;

import domain.deck.Deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PartyPackExpansionTests {
    private PartyPackExpansion partyPackExpansion;

    @BeforeEach
    public void setUp() {
        partyPackExpansion = new PartyPackExpansion();
    }

    @Test
    public void testGetId_returnsPartyPack() {
        assertEquals("party_pack", partyPackExpansion.getId());
    }

    @Test
    public void testGetDisplayName_returnsPartyPack() {
        assertEquals("Party Pack", partyPackExpansion.getDisplayName());
    }

    @Test
    public void testGetSelectionNumber_returnsOne() {
        assertEquals(1, partyPackExpansion.getSelectionNumber());
    }

    @Test
    public void testGetMaxPlayers_returnsTen() {
        assertEquals(10, partyPackExpansion.getMaxPlayers());
    }

    @Test
    public void testIsBaseGameModifier_returnsTrue() {
        assertTrue(partyPackExpansion.isBaseGameModifier());
    }

    @Test
    public void testGetAdditionalDefuseCards_returnsTwo() {
        assertEquals(2, partyPackExpansion.getAdditionalDefuseCards(4));
    }

    @Test
    public void testGetAdditionalDefuseCards_sameForAllPlayerCounts() {
        assertEquals(partyPackExpansion.getAdditionalDefuseCards(2), 
                     partyPackExpansion.getAdditionalDefuseCards(10));
    }

    @Test
    public void testAddCardsToDeck_twoPlayers_smallGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 2);
        
        assertEquals(43, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_threePlayers_smallGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 3);
        
        assertEquals(43, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_fourPlayers_mediumGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 4);
        
        assertEquals(64, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_sevenPlayers_mediumGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 7);
        
        assertEquals(64, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_eightPlayers_largeGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 8);
        
        assertEquals(93, realDeck.numberOfCards());
    }

    @Test
    public void testAddCardsToDeck_tenPlayers_largeGameCounts() {
        Deck realDeck = new Deck();
        
        partyPackExpansion.addCardsToDeck(realDeck, 10);
        
        assertEquals(93, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_twoPlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        partyPackExpansion.addPostDealCards(realDeck, 2);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_fourPlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        partyPackExpansion.addPostDealCards(realDeck, 4);
        
        assertEquals(initialSize + 5, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_sevenPlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        partyPackExpansion.addPostDealCards(realDeck, 7);
        
        assertEquals(initialSize + 8, realDeck.numberOfCards());
    }

    @Test
    public void testAddPostDealCards_tenPlayers() {
        Deck realDeck = new Deck();
        int initialSize = realDeck.numberOfCards();
        
        partyPackExpansion.addPostDealCards(realDeck, 10);
        
        assertEquals(initialSize + 11, realDeck.numberOfCards());
    }

    @Test
    public void testGetAdditionalExplodingKittens_returnsZero() {
        assertEquals(0, partyPackExpansion.getAdditionalExplodingKittens());
    }
}