package domain.player;

import domain.cards.Card;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandTests {

    @Test
    public void testHand_constructorWithEmptyList_createsEmptyHand() {
        Hand hand = new Hand(new ArrayList<>());
        assertEquals(0, hand.size());
        assertTrue(hand.isEmpty());
    }

    @Test
    public void testHand_constructorWithCards_createsHandWithCards() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        List<Card> cards = new ArrayList<>(Arrays.asList(card1, card2));
        
        Hand hand = new Hand(cards);
        assertEquals(2, hand.size());
    }

    @Test
    public void testHand_copyConstructor_copiesCards() {
        Card card = EasyMock.createMock(Card.class);
        List<Card> cards = new ArrayList<>(Arrays.asList(card));
        Hand original = new Hand(cards);
        original.addVisibleCard(card);
        
        Hand copy = new Hand(original);
        assertEquals(original.size(), copy.size());
        assertEquals(original.getVisibleCards().size(), copy.getVisibleCards().size());
    }

    @Test
    public void testAddCard_emptyHand_returns1() {
        Hand hand = new Hand(new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        
        hand.addCard(card);
        assertEquals(1, hand.size());
    }

    @Test
    public void testAddCard_handSize1_returns2() {
        Card card1 = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1)));
        Card card2 = EasyMock.createMock(Card.class);
        
        hand.addCard(card2);
        assertEquals(2, hand.size());
    }

    @Test
    public void testAddCard_handSize90_returns91() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 90; i++) {
            cards.add(EasyMock.createMock(Card.class));
        }
        Hand hand = new Hand(cards);
        
        hand.addCard(EasyMock.createMock(Card.class));
        assertEquals(91, hand.size());
    }

    @Test
    public void testAddVisibleCard_emptyVisibleHand_returns1() {
        Hand hand = new Hand(new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        
        hand.addVisibleCard(card);
        assertEquals(1, hand.getVisibleCards().size());
    }

    @Test
    public void testAddVisibleCard_visibleHandSize1_returns2() {
        Hand hand = new Hand(new ArrayList<>());
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        hand.addVisibleCard(card1);
        
        hand.addVisibleCard(card2);
        assertEquals(2, hand.getVisibleCards().size());
    }

    @Test
    public void testRemoveCard_emptyHand_throwsException() {
        Hand hand = new Hand(new ArrayList<>());
        String expectMsg = "emptyHand";
        
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> hand.removeCard(0));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_negativeIndex_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        String expectMsg = "indexOutOfBounds";
        
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> hand.removeCard(-1));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_indexTooLarge_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        String expectMsg = "indexOutOfBounds";
        
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> hand.removeCard(5));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_validIndex_removesCard() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        
        Card removed = hand.removeCard(0);
        assertEquals(card1, removed);
        assertEquals(1, hand.size());
    }

    @Test
    public void testRemoveCard_alsoRemovesFromVisibleCards() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        hand.addVisibleCard(card);
        
        hand.removeCard(0);
        assertEquals(0, hand.getVisibleCards().size());
    }

    @Test
    public void testGetCard_negativeIndex_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        String expectMsg = "indexOutOfBounds";
        
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> { Object ignoredValue = hand.getCard(-1); });
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testGetCard_indexTooLarge_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        String expectMsg = "indexOutOfBounds";
        
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> { Object ignoredValue = hand.getCard(5); });
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testGetCard_validIndex_returnsCard() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        
        assertEquals(card1, hand.getCard(0));
        assertEquals(card2, hand.getCard(1));
    }

    @Test
    public void testFindCardIndex_cardNotFound_returnsNegative1() {
        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        
        assertEquals(-1, hand.findCardIndex("Defuse"));
        EasyMock.verify(card);
    }

    @Test
    public void testFindCardIndex_cardFound_returnsIndex() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Defuse").anyTimes();
        EasyMock.replay(card1, card2);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        
        assertEquals(0, hand.findCardIndex("Attack"));
        assertEquals(1, hand.findCardIndex("Defuse"));
        EasyMock.verify(card1, card2);
    }

    @Test
    public void testFindCardIndex_emptyHand_returnsNegative1() {
        Hand hand = new Hand(new ArrayList<>());
        assertEquals(-1, hand.findCardIndex("Attack"));
    }

    @Test
    public void testHasTwoOf_oneCard_returnsFalse() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Defuse").anyTimes();
        EasyMock.replay(card1, card2);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        
        assertFalse(hand.hasTwoOf("Attack"));
        EasyMock.verify(card1, card2);
    }

    @Test
    public void testHasTwoOf_twoCards_returnsTrue() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card1, card2);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        
        assertTrue(hand.hasTwoOf("Attack"));
        EasyMock.verify(card1, card2);
    }

    @Test
    public void testHasTwoOf_threeCards_returnsTrue() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card3.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card1, card2, card3);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2, card3)));
        
        assertTrue(hand.hasTwoOf("Attack"));
        EasyMock.verify(card1, card2, card3);
    }

    @Test
    public void testShuffle_emptyHand_throwsException() {
        Hand hand = new Hand(new ArrayList<>());
        String expectMsg = "emptyHand";
        
        Exception exception = assertThrows(UnsupportedOperationException.class, hand::shuffle);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testShuffle_singleCard_sameSizeHand() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        
        hand.shuffle();
        assertEquals(1, hand.size());
    }

    @Test
    public void testShuffle_multipleCards_sameSizeHand() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(EasyMock.createMock(Card.class));
        }
        Hand hand = new Hand(cards);
        
        hand.shuffle();
        assertEquals(10, hand.size());
    }

    @Test
    public void testGetCards_returnsCopy() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        
        List<Card> cards = hand.getCards();
        cards.clear();
        
        assertEquals(1, hand.size());
    }

    @Test
    public void testGetVisibleCards_returnsCopy() {
        Hand hand = new Hand(new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        hand.addVisibleCard(card);
        
        List<Card> visibleCards = hand.getVisibleCards();
        visibleCards.clear();
        
        assertEquals(1, hand.getVisibleCards().size());
    }

    @Test
    public void testIsEmpty_emptyHand_returnsTrue() {
        Hand hand = new Hand(new ArrayList<>());
        assertTrue(hand.isEmpty());
    }

    @Test
    public void testIsEmpty_nonEmptyHand_returnsFalse() {
        Card card = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card)));
        assertFalse(hand.isEmpty());
    }

    @Test
    public void testSize_emptyHand_returnsZero() {
        Hand hand = new Hand(new ArrayList<>());
        assertEquals(0, hand.size());
    }

    @Test
    public void testSize_nonEmptyHand_returnsCorrectSize() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(card1, card2)));
        assertEquals(2, hand.size());
    }
}