package domain.deck;

import domain.cards.Card;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DrawPileTests {

    @Test
    public void testDrawPile_defaultConstructor_createsEmptyPile() {
        DrawPile pile = new DrawPile();
        assertEquals(0, pile.size());
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testDrawPile_constructorWithCards_createsWithCards() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        DrawPile pile = new DrawPile(cards);
        assertEquals(2, pile.size());
    }

    @Test
    public void testAdd_emptyPile_addsCard() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        
        pile.add(card);
        assertEquals(1, pile.size());
    }

    @Test
    public void testAdd_atIndex_addsCardAtPosition() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        
        pile.add(card1);
        pile.add(card3);
        pile.add(1, card2);
        
        assertEquals(3, pile.size());
        assertEquals(card2, pile.get(1));
    }

    @Test
    public void testAdd_atIndexZero_addsAtBeginning() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        
        pile.add(card1);
        pile.add(0, card2);
        
        assertEquals(card2, pile.get(0));
        assertEquals(card1, pile.get(1));
    }

    @Test
    public void testRemoveTop_emptyPile_throwsException() {
        DrawPile pile = new DrawPile();
        String expectMsg = "deckEmpty";
        
        Exception exception = assertThrows(UnsupportedOperationException.class, pile::removeTop);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testRemoveTop_singleCard_returnsCard() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        Card removed = pile.removeTop();
        assertEquals(card, removed);
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testRemoveTop_multipleCards_returnsFirstCard() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        Card removed = pile.removeTop();
        assertEquals(card1, removed);
        assertEquals(1, pile.size());
    }

    @Test
    public void testRemoveBottom_emptyPile_throwsException() {
        DrawPile pile = new DrawPile();
        String expectMsg = "deckEmpty";
        
        Exception exception = assertThrows(UnsupportedOperationException.class, pile::removeBottom);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testRemoveBottom_singleCard_returnsCard() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        Card removed = pile.removeBottom();
        assertEquals(card, removed);
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testRemoveBottom_multipleCards_returnsLastCard() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        Card removed = pile.removeBottom();
        assertEquals(card2, removed);
        assertEquals(1, pile.size());
    }

    @Test
    public void testSize_emptyPile_returnsZero() {
        DrawPile pile = new DrawPile();
        assertEquals(0, pile.size());
    }

    @Test
    public void testSize_afterAddingCards_returnsCorrectCount() {
        DrawPile pile = new DrawPile();
        pile.add(EasyMock.createMock(Card.class));
        pile.add(EasyMock.createMock(Card.class));
        pile.add(EasyMock.createMock(Card.class));
        assertEquals(3, pile.size());
    }

    @Test
    public void testIsEmpty_emptyPile_returnsTrue() {
        DrawPile pile = new DrawPile();
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testIsEmpty_nonEmptyPile_returnsFalse() {
        DrawPile pile = new DrawPile();
        pile.add(EasyMock.createMock(Card.class));
        assertFalse(pile.isEmpty());
    }

    @Test
    public void testShuffle_emptyPile_throwsException() {
        DrawPile pile = new DrawPile();
        String expectMsg = "deckEmpty";
        
        Exception exception = assertThrows(UnsupportedOperationException.class, pile::shuffle);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testShuffle_singleCard_sameCard() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        pile.shuffle();
        assertEquals(1, pile.size());
        assertEquals(card, pile.get(0));
    }

    @Test
    public void testShuffle_multipleCards_sameSize() {
        DrawPile pile = new DrawPile();
        for (int i = 0; i < 10; i++) {
            pile.add(EasyMock.createMock(Card.class));
        }
        
        pile.shuffle();
        assertEquals(10, pile.size());
    }

    @Test
    public void testGetAll_emptyPile_returnsEmptyList() {
        DrawPile pile = new DrawPile();
        List<Card> cards = pile.getAll();
        assertTrue(cards.isEmpty());
    }

    @Test
    public void testGetAll_nonEmptyPile_returnsAllCards() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        List<Card> cards = pile.getAll();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void testGetAll_returnsCopy() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        List<Card> cards = pile.getAll();
        cards.clear();
        
        assertEquals(1, pile.size());
    }

    @Test
    public void testGet_validIndex_returnsCard() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        assertEquals(card1, pile.get(0));
        assertEquals(card2, pile.get(1));
    }

    @Test
    public void testSet_validIndex_replacesCard() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        
        pile.set(0, card2);
        assertEquals(card2, pile.get(0));
    }

    @Test
    public void testClear_emptyPile_staysEmpty() {
        DrawPile pile = new DrawPile();
        pile.clear();
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testClear_nonEmptyPile_becomesEmpty() {
        DrawPile pile = new DrawPile();
        pile.add(EasyMock.createMock(Card.class));
        pile.add(EasyMock.createMock(Card.class));
        
        pile.clear();
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testAddAll_emptyList_noChange() {
        DrawPile pile = new DrawPile();
        pile.addAll(new ArrayList<>());
        assertTrue(pile.isEmpty());
    }

    @Test
    public void testAddAll_nonEmptyList_addsAllCards() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        
        pile.addAll(cards);
        assertEquals(2, pile.size());
    }

    @Test
    public void testAddAll_atIndex_insertsCardsAtPosition() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        pile.add(card3);
        
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        
        pile.addAll(0, cards);
        assertEquals(3, pile.size());
        assertEquals(card1, pile.get(0));
        assertEquals(card2, pile.get(1));
        assertEquals(card3, pile.get(2));
    }

    @Test
    public void testSwap_validIndices_swapsCards() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        pile.swap(0, 1);
        assertEquals(card2, pile.get(0));
        assertEquals(card1, pile.get(1));
    }

    @Test
    public void testSwap_sameIndex_noChange() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        pile.swap(0, 0);
        assertEquals(card, pile.get(0));
    }

    @Test
    public void testSubList_validRange_returnsSubList() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        pile.add(card3);
        
        List<Card> subList = pile.subList(0, 2);
        assertEquals(2, subList.size());
        assertEquals(card1, subList.get(0));
        assertEquals(card2, subList.get(1));
    }

    @Test
    public void testSubList_entireList_returnsAllCards() {
        DrawPile pile = new DrawPile();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        pile.add(card1);
        pile.add(card2);
        
        List<Card> subList = pile.subList(0, 2);
        assertEquals(2, subList.size());
    }

    @Test
    public void testSubList_emptyRange_returnsEmptyList() {
        DrawPile pile = new DrawPile();
        Card card = EasyMock.createMock(Card.class);
        pile.add(card);
        
        List<Card> subList = pile.subList(0, 0);
        assertTrue(subList.isEmpty());
    }
}