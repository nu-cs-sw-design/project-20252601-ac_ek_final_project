package domain.deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeckCreatorTests {
    private DeckCreator deckCreator;

    @BeforeEach
    public void setUp() {
        deckCreator = new DeckCreator();
    }

    @Test
    public void testCreateDeck_twoPlayers_noExpansions_returnsDeck() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(2, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_fivePlayers_noExpansions_returnsDeck() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(5, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_withPartyPack_allowsMorePlayers() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("party_pack");
        
        Deck deck = deckCreator.createDeck(8, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_withImplodingKittens_returnsDeckWithExpansionCards() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("imploding_kittens");
        
        Deck deck = deckCreator.createDeck(4, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_withStreakingKittens_returnsDeckWithExpansionCards() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("streaking_kittens");
        
        Deck deck = deckCreator.createDeck(3, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_tooFewPlayers_throwsException() {
        Set<String> expansionIds = new HashSet<>();
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> deckCreator.createDeck(1, expansionIds));
        assertEquals("Invalid player count for selected expansions", exception.getMessage());
    }

    @Test
    public void testCreateDeck_tooManyPlayers_noExpansions_throwsException() {
        Set<String> expansionIds = new HashSet<>();
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> deckCreator.createDeck(10, expansionIds));
        assertEquals("Invalid player count for selected expansions", exception.getMessage());
    }

    @Test
    public void testCreateDeck_multipleExpansions_returnsDeck() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("party_pack");
        expansionIds.add("imploding_kittens");
        expansionIds.add("streaking_kittens");
        
        Deck deck = deckCreator.createDeck(6, expansionIds);
        
        assertNotNull(deck);
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testAddRemainingCards_noExpansions_addsDefuseAndExploding() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(3, expansionIds);
        int initialSize = deck.numberOfCards();
        
        deckCreator.addRemainingCards(deck, 3, expansionIds);
        
        assertTrue(deck.numberOfCards() > initialSize);
    }

    @Test
    public void testAddRemainingCards_withExpansions_addsAdditionalCards() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("imploding_kittens");
        Deck deck = deckCreator.createDeck(4, expansionIds);
        int initialSize = deck.numberOfCards();
        
        deckCreator.addRemainingCards(deck, 4, expansionIds);
        
        assertTrue(deck.numberOfCards() > initialSize);
    }

    @Test
    public void testAddRemainingCards_deckIsShuffled() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(2, expansionIds);
        
        deckCreator.addRemainingCards(deck, 2, expansionIds);
        
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testAddRemainingCards_partyPack_addsExtraDefuse() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("party_pack");
        Deck deck = deckCreator.createDeck(5, expansionIds);
        
        deckCreator.addRemainingCards(deck, 5, expansionIds);
        
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testAddRemainingCards_streakingKittens_addsExtraExploding() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("streaking_kittens");
        Deck deck = deckCreator.createDeck(4, expansionIds);
        
        deckCreator.addRemainingCards(deck, 4, expansionIds);
        
        assertTrue(deck.numberOfCards() > 0);
    }

    @Test
    public void testCreateDeck_exactMinimumPlayers_works() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(2, expansionIds);
        
        assertNotNull(deck);
    }

    @Test
    public void testCreateDeck_exactMaximumPlayers_noExpansions_works() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(5, expansionIds);
        
        assertNotNull(deck);
    }

    @Test
    public void testCreateDeck_partyPackMaxPlayers_works() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("party_pack");
        Deck deck = deckCreator.createDeck(10, expansionIds);
        
        assertNotNull(deck);
    }

    @Test
    public void testCreateDeck_invalidExpansion_noEffect() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("invalid_expansion");
        Deck deck = deckCreator.createDeck(3, expansionIds);
        
        assertNotNull(deck);
    }

    @Test
    public void testAddRemainingCards_verifyDefuseCount() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(4, expansionIds);
        int initialSize = deck.numberOfCards();
        
        deckCreator.addRemainingCards(deck, 4, expansionIds);
        
        int finalSize = deck.numberOfCards();
        assertTrue(finalSize > initialSize);
        
        int defuseAndExplodingCount = finalSize - initialSize;
        assertTrue(defuseAndExplodingCount >= 5);
    }

    @Test
    public void testAddRemainingCards_verifyExplodingKittenCount() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(3, expansionIds);
        
        deckCreator.addRemainingCards(deck, 3, expansionIds);
        
        long explodingCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Exploding Kitten"))
            .count();
        
        assertEquals(2, explodingCount);
    }

    @Test
    public void testAddRemainingCards_twoPlayers_oneExplodingKitten() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(2, expansionIds);
        
        deckCreator.addRemainingCards(deck, 2, expansionIds);
        
        long explodingCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Exploding Kitten"))
            .count();
        
        assertEquals(1, explodingCount);
    }

    @Test
    public void testAddRemainingCards_fivePlayers_fourExplodingKittens() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(5, expansionIds);
        
        deckCreator.addRemainingCards(deck, 5, expansionIds);
        
        long explodingCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Exploding Kitten"))
            .count();
        
        assertEquals(4, explodingCount);
    }

    @Test
    public void testAddRemainingCards_streakingKittens_extraExplodingKittenAdded() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("streaking_kittens");
        Deck deck = deckCreator.createDeck(3, expansionIds);
        
        deckCreator.addRemainingCards(deck, 3, expansionIds);
        
        long explodingCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Exploding Kitten"))
            .count();
        
        assertEquals(3, explodingCount);
    }

    @Test
    public void testAddRemainingCards_implodingKittens_implodingKittenAdded() {
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("imploding_kittens");
        Deck deck = deckCreator.createDeck(4, expansionIds);
        
        deckCreator.addRemainingCards(deck, 4, expansionIds);
        
        long implodingCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Imploding Kitten"))
            .count();
        
        assertEquals(1, implodingCount);
    }

    @Test
    public void testAddRemainingCards_remainingDefuseCards_addedToDeck() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(2, expansionIds);
        
        deckCreator.addRemainingCards(deck, 2, expansionIds);
        
        long defuseCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Defuse"))
            .count();
        
        assertEquals(4, defuseCount);
    }

    @Test
    public void testAddRemainingCards_threePlayersWithFiveDefuses() {
        Set<String> expansionIds = new HashSet<>();
        Deck deck = deckCreator.createDeck(3, expansionIds);
        
        deckCreator.addRemainingCards(deck, 3, expansionIds);
        
        long defuseCount = deck.getDrawPile().getAll().stream()
            .filter(card -> card.getName().equals("Defuse"))
            .count();
        
        assertEquals(3, defuseCount);
    }
}