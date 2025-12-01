package domain.deck;

import domain.cards.Card;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTests {

    @Test
    public void testDeck_constructor_createsEmptyDeck() {
        Deck deck = new Deck();
        assertEquals(0, deck.numberOfCards());
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testDeck_copyConstructor_createsCopyOfDeck() {
        Deck original = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        original.addCard(card1);
        original.addCard(card2);

        Deck copy = new Deck(original);
        assertEquals(2, copy.numberOfCards());
        assertEquals(card1, copy.drawTopCard());
        assertEquals(card2, copy.drawTopCard());
    }

    @Test
    public void testAddCard_emptyDeck_expectSize1() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        assertEquals(1, deck.numberOfCards());
    }

    @Test
    public void testAddCard_deckSize1_expectSize2() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        assertEquals(1, deck.numberOfCards());

        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card2);

        assertEquals(2, deck.numberOfCards());
    }

    @Test
    public void testAddCard_deckSize3_expectSize4() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        assertEquals(3, deck.numberOfCards());

        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card4);

        assertEquals(4, deck.numberOfCards());
    }

    @Test
    public void testDrawTopCard_emptyDeck_throwsException() {
        Deck deck = new Deck();
        String expectMsg = "deckEmpty";
        Exception exception = assertThrows(UnsupportedOperationException.class, deck::drawTopCard);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testDrawTopCard_deckSize1_returnsOnlyCard() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        Card topCard = deck.drawTopCard();
        assertEquals(card, topCard);
        assertEquals(0, deck.numberOfCards());
    }

    @Test
    public void testDrawTopCard_deckSize5_returnsFirstCard() {
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

        Card topCard = deck.drawTopCard();
        assertEquals(card1, topCard);
        assertEquals(4, deck.numberOfCards());
    }

    @Test
    public void testDrawBottomCard_emptyDeck_throwsException() {
        String expectMsg = "deckEmpty";
        Deck deck = new Deck();
        Exception exception = assertThrows(UnsupportedOperationException.class, deck::drawBottomCard);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testDrawBottomCard_deckSize1_returnsOnlyCard() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        Card bottomCard = deck.drawBottomCard();
        assertEquals(card, bottomCard);
        assertEquals(0, deck.numberOfCards());
    }

    @Test
    public void testDrawBottomCard_deckSize5_returnsLastCard() {
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

        Card bottomCard = deck.drawBottomCard();
        assertEquals(card5, bottomCard);
        assertEquals(4, deck.numberOfCards());
    }

    @Test
    public void testShuffle_emptyDeck_throwsException() {
        String expectMsg = "deckEmpty";
        Deck deck = new Deck();
        Exception exception = assertThrows(UnsupportedOperationException.class, deck::shuffle);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testShuffle_deckSize1_returnsSameDeck() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        deck.shuffle();
        assertEquals(1, deck.numberOfCards());

        Card topCard = deck.drawTopCard();
        assertEquals(card1, topCard);
    }

    @Test
    public void testShuffle_deckSize4_returnsSameSizeDeck() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        deck.shuffle();
        assertEquals(4, deck.numberOfCards());
    }

    @Test
    public void testIsEmpty_emptyDeck_returnsTrue() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testIsEmpty_nonEmptyDeck_returnsFalse() {
        Deck deck = new Deck();
        deck.addCard(EasyMock.createMock(Card.class));
        assertFalse(deck.isEmpty());
    }

    @Test
    public void testGetDrawPile_returnsDrawPile() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);
        
        DrawPile drawPile = deck.getDrawPile();
        assertNotNull(drawPile);
        assertEquals(1, drawPile.size());
    }

    @Test
    public void testNumberOfCards_afterMultipleOperations() {
        Deck deck = new Deck();
        assertEquals(0, deck.numberOfCards());
        
        deck.addCard(EasyMock.createMock(Card.class));
        assertEquals(1, deck.numberOfCards());
        
        deck.addCard(EasyMock.createMock(Card.class));
        assertEquals(2, deck.numberOfCards());
        
        deck.drawTopCard();
        assertEquals(1, deck.numberOfCards());
    }
}