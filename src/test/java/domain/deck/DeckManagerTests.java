package domain.deck;

import domain.cards.Card;
import domain.cards.implementations.ExplodingKittenCard;
import domain.cards.implementations.SkipCard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeckManagerTests {
    private DeckManager deckManager;
    private DeckCreator mockDeckCreator;

    @BeforeEach
    public void setUp() {
        mockDeckCreator = EasyMock.createMock(DeckCreator.class);
        deckManager = new DeckManager(mockDeckCreator);
    }

    @Test
    public void testCopyConstructor_withNullOther_setsNullDeckCreator() {
        DeckManager nullManager = new DeckManager((DeckManager) null);
        assertNotNull(nullManager);
    }

    @Test
    public void testCopyConstructor_withValidOther_copiesDeckCreator() {
        DeckManager original = new DeckManager(mockDeckCreator);
        DeckManager copy = new DeckManager(original);
        assertNotNull(copy);
    }

    @Test
    public void testInitializeDeck_twoPlayers_returnsDeckWithCards() {
        int numberOfPlayers = 2;
        Set<String> expansionIds = new HashSet<>();
        Deck mockDeck = EasyMock.createMock(Deck.class);

        EasyMock.expect(mockDeckCreator.createDeck(numberOfPlayers, expansionIds)).andReturn(mockDeck);
        mockDeck.shuffle();
        EasyMock.expectLastCall();
        EasyMock.replay(mockDeckCreator, mockDeck);

        Deck result = deckManager.initializeDeck(numberOfPlayers, expansionIds);
        assertEquals(mockDeck, result);

        EasyMock.verify(mockDeckCreator, mockDeck);
    }

    @Test
    public void testInitializeDeck_withExpansions_callsCreatorWithExpansions() {
        int numberOfPlayers = 4;
        Set<String> expansionIds = new HashSet<>();
        expansionIds.add("party_pack");
        expansionIds.add("imploding_kittens");
        Deck mockDeck = EasyMock.createMock(Deck.class);

        EasyMock.expect(mockDeckCreator.createDeck(numberOfPlayers, expansionIds)).andReturn(mockDeck);
        mockDeck.shuffle();
        EasyMock.expectLastCall();
        EasyMock.replay(mockDeckCreator, mockDeck);

        Deck result = deckManager.initializeDeck(numberOfPlayers, expansionIds);
        assertNotNull(result);

        EasyMock.verify(mockDeckCreator, mockDeck);
    }

    @Test
    public void testAddRemainingCards_delegatesToDeckCreator() {
        int numberOfPlayers = 3;
        Set<String> expansionIds = new HashSet<>();
        Deck mockDeck = EasyMock.createMock(Deck.class);

        mockDeckCreator.addRemainingCards(mockDeck, numberOfPlayers, expansionIds);
        EasyMock.expectLastCall();
        EasyMock.replay(mockDeckCreator, mockDeck);

        deckManager.addRemainingCards(mockDeck, numberOfPlayers, expansionIds);

        EasyMock.verify(mockDeckCreator, mockDeck);
    }

    @Test
    public void testDrawTopCard_returnsTopCard() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        Card drawn = realManager.drawTopCard(deck);
        assertEquals(card, drawn);
    }

    @Test
    public void testGetDeckSize_emptyDeck_returnsZero() {
        Deck deck = new Deck();
        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        assertEquals(0, realManager.getDeckSize(deck));
    }

    @Test
    public void testGetDeckSize_deckWithCards_returnsCorrectSize() {
        Deck deck = new Deck();
        deck.addCard(EasyMock.createMock(Card.class));
        deck.addCard(EasyMock.createMock(Card.class));
        deck.addCard(EasyMock.createMock(Card.class));

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        assertEquals(3, realManager.getDeckSize(deck));
    }

    @Test
    public void testPeekTopDeck_emptyDeck_throwsException() {
        Deck deck = new Deck();
        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "deckEmpty";

        Exception exception = assertThrows(UnsupportedOperationException.class, 
            () -> realManager.peekTopDeck(deck, 3));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testPeekTopDeck_deckHasOneCard_see3_returnsListWithOneCard() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        List<Card> cards = realManager.peekTopDeck(deck, 3);
        assertEquals(1, cards.size());
        assertEquals(card, cards.get(0));
    }

    @Test
    public void testPeekTopDeck_deckHas5Cards_see3_returnsListWith3Cards() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        Card card5 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        List<Card> cards = realManager.peekTopDeck(deck, 3);
        assertEquals(3, cards.size());
        assertEquals(card1, cards.get(0));
        assertEquals(card2, cards.get(1));
        assertEquals(card3, cards.get(2));
    }

    @Test
    public void testAlterTopDeck_emptyDeck_throwsException() {
        Deck deck = new Deck();
        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "deckEmpty";

        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(0, 1, 2);

        Exception exception = assertThrows(UnsupportedOperationException.class,
            () -> realManager.alterTopDeck(deck, cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_differentSizeInputs_throwsException() {
        Deck deck = new Deck();
        Card mockCard1 = EasyMock.createNiceMock(Card.class);
        Card mockCard2 = EasyMock.createNiceMock(Card.class);
        deck.addCard(mockCard1);
        deck.addCard(mockCard2);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "newIndexSize";

        Card mockCard3 = EasyMock.createNiceMock(Card.class);
        List<Card> cards = new ArrayList<>();
        cards.add(mockCard3);
        List<Integer> indexes = Arrays.asList(0, 1, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> realManager.alterTopDeck(deck, cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_duplicateIndexes_throwsException() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "newIndexesUnique";

        List<Card> cards = Arrays.asList(card1, card2);
        List<Integer> indexes = Arrays.asList(0, 0);

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> realManager.alterTopDeck(deck, cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_negativeIndex_throwsException() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "indexOutOfBounds";

        List<Card> cards = Arrays.asList(card);
        List<Integer> indexes = Arrays.asList(-1);

        Exception exception = assertThrows(IndexOutOfBoundsException.class,
            () -> realManager.alterTopDeck(deck, cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_indexTooLarge_throwsException() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "indexOutOfBounds";

        List<Card> cards = Arrays.asList(card);
        List<Integer> indexes = Arrays.asList(5);

        Exception exception = assertThrows(IndexOutOfBoundsException.class,
            () -> realManager.alterTopDeck(deck, cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_validReorder_reordersCards() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        List<Card> cards = Arrays.asList(card1, card2);
        List<Integer> indexes = Arrays.asList(1, 0);

        realManager.alterTopDeck(deck, cards, indexes);

        assertEquals(card2, deck.drawTopCard());
        assertEquals(card1, deck.drawTopCard());
    }

    @Test
    public void testMoveExplodingKittensToTop_emptyDeck_throwsException() {
        Deck deck = new Deck();
        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "deckEmpty";

        Exception exception = assertThrows(UnsupportedOperationException.class,
            () -> realManager.moveExplodingKittensToTop(deck));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testMoveExplodingKittensToTop_noExplodingKittens_sameDeck() {
        Deck deck = new Deck();
        Card card = new SkipCard();
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.moveExplodingKittensToTop(deck);
        assertEquals(1, deck.numberOfCards());
    }

    @Test
    public void testMoveExplodingKittensToTop_oneExplodingKitten_movesToTop() {
        Deck deck = new Deck();
        Card skipCard = new SkipCard();
        Card explodingKitten = new ExplodingKittenCard();
        deck.addCard(skipCard);
        deck.addCard(explodingKitten);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.moveExplodingKittensToTop(deck);
        Card topCard = deck.drawTopCard();
        assertTrue(topCard instanceof ExplodingKittenCard);
    }

    @Test
    public void testMoveExplodingKittensToTop_tooManyExplodingKittens_throwsException() {
        Deck deck = new Deck();
        for (int i = 0; i < 10; i++) {
            deck.addCard(new ExplodingKittenCard());
        }

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "tooManyExplodingKitten";

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> realManager.moveExplodingKittensToTop(deck));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testMoveExplodingKittensToTop_onlyExplodingKittens_noShuffle() {
        Deck deck = new Deck();
        Card explodingKitten1 = new ExplodingKittenCard();
        Card explodingKitten2 = new ExplodingKittenCard();
        deck.addCard(explodingKitten1);
        deck.addCard(explodingKitten2);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.moveExplodingKittensToTop(deck);
        
        assertEquals(2, deck.numberOfCards());
        assertTrue(deck.drawTopCard() instanceof ExplodingKittenCard);
        assertTrue(deck.drawTopCard() instanceof ExplodingKittenCard);
    }

    @Test
    public void testSwapTopAndBottom_emptyDeck_throwsException() {
        Deck deck = new Deck();
        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "deckEmpty";

        Exception exception = assertThrows(UnsupportedOperationException.class,
            () -> realManager.swapTopAndBottom(deck));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testSwapTopAndBottom_singleCard_sameDeck() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.swapTopAndBottom(deck);
        assertEquals(card, deck.drawTopCard());
    }

    @Test
    public void testSwapTopAndBottom_multipleCards_swapsCards() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.swapTopAndBottom(deck);
        assertEquals(card3, deck.drawTopCard());
        assertEquals(card2, deck.drawTopCard());
        assertEquals(card1, deck.drawTopCard());
    }

    @Test
    public void testInsertCardAtIndex_negativeIndex_throwsException() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "indexOutOfBounds";

        Exception exception = assertThrows(IndexOutOfBoundsException.class,
            () -> realManager.insertCardAtIndex(deck, card, -1));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testInsertCardAtIndex_indexTooLarge_throwsException() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);
        String expectMsg = "indexOutOfBounds";

        Exception exception = assertThrows(IndexOutOfBoundsException.class,
            () -> realManager.insertCardAtIndex(deck, card, 5));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testInsertCardAtIndex_emptyDeck_indexZero_insertsCard() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.insertCardAtIndex(deck, card, 0);
        assertEquals(1, deck.numberOfCards());
        assertEquals(card, deck.drawTopCard());
    }

    @Test
    public void testInsertCardAtIndex_nonEmptyDeck_insertsAtPosition() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card3);

        DeckCreator realCreator = new DeckCreator();
        DeckManager realManager = new DeckManager(realCreator);

        realManager.insertCardAtIndex(deck, card2, 1);
        assertEquals(3, deck.numberOfCards());
        assertEquals(card1, deck.drawTopCard());
        assertEquals(card2, deck.drawTopCard());
        assertEquals(card3, deck.drawTopCard());
    }
}