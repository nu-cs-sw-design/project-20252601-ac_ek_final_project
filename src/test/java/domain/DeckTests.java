package domain;

import domain.Cards.*;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTests {
    @Test
    public void testAddCard_emptyDeck_expectSize1() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);

        int expectedSize = 1;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddCard_deckSize1_expectSize2() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        int initialSize = 1;
        assertEquals(initialSize, deck.numberOfCards());

        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card2);

        int expectedSize = 2;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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
        int initialSize = 3;
        assertEquals(initialSize, deck.numberOfCards());

        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card4);

        int expectedSize = 4;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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

        int expectedSize = 0;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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
        assertNotEquals(card2, topCard);
        assertNotEquals(card3, topCard);
        assertNotEquals(card4, topCard);
        assertNotEquals(card5, topCard);

        int expectedSize = 4;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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

        int expectedSize = 0;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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
        assertNotEquals(card1, bottomCard);
        assertNotEquals(card2, bottomCard);
        assertNotEquals(card3, bottomCard);
        assertNotEquals(card4, bottomCard);

        int expectedSize = 4;
        int actualSize = deck.numberOfCards();
        assertEquals(expectedSize, actualSize);
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
        int deckSize = 1;
        Card card1 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        deck.shuffle();
        assertEquals(deckSize, deck.numberOfCards());

        Card topCard = deck.drawTopCard();
        assertEquals(card1, topCard);
    }

    @Test
    public void testShuffle_deckSize4_returnsSameSizeDeck() {
        Deck deck = new Deck();
        int deckSize = 4;
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        deck.shuffle();
        assertEquals(deckSize, deck.numberOfCards());

        Card topCard = deck.drawTopCard();
        assertTrue(card1.equals(topCard) || card2.equals(topCard) || card3.equals(topCard) || card4.equals(topCard));
    }

    @Test
    public void testShuffle_deckSize1000_returnsDifferentDeck_sameDeckWithNegligibleProbability() {
        Deck initialDeck = new Deck();
        Deck shuffleDeck = new Deck();
        int deckSize = 1000;
        int minimumDifferentCards = 0;
        for (int i = 0; i < deckSize; i++) {
            Card card = EasyMock.createMock(Card.class);
            initialDeck.addCard(card);
            shuffleDeck.addCard(card);
        }

        shuffleDeck.shuffle();
        assertEquals(deckSize, initialDeck.numberOfCards());

        int numberDifferentCards = 0;
        for (int i = 0; i < deckSize; i++) {
            if (!initialDeck.drawTopCard().equals(shuffleDeck.drawTopCard())) {
                numberDifferentCards++;
            }
        }

        assertTrue(numberDifferentCards > minimumDifferentCards);
    }

    @Test
    public void testPeekTopDeck_deckIsEmpty_see3_throwsException() {
        String expectMsg = "deckEmpty";
        Deck deck = new Deck();
        int numCardsToPeek = 3;
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> deck.peekTopDeck(numCardsToPeek));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testPeekTopDeck_deckHasOneCard_see3_returnsListWithThisOneCard() {
        Deck deck = new Deck();
        int deckSize = 1;
        int numCardsToPeek = 3;
        Card card = EasyMock.createMock(Card.class);
        deck.addCard(card);
        List<Card> cards = deck.peekTopDeck(numCardsToPeek);
        assertEquals(cards.size(), deckSize);
    }

    @Test
    public void testPeekTopDeck_deckHasTenCards_see5_returnsListWithTopFiveCards() {
        Deck deck = new Deck();
        int wantedCardsSize = 5;
        int unwantedCardsSize = 5;
        int numCardsToPeek = 5;
        Card cardsWanted = EasyMock.createMock(AttackCard.class);
        Card cardsUnwanted = EasyMock.createMock(DefuseCard.class);

        for (int j = 0; j < wantedCardsSize; j++) {
            deck.addCard(cardsWanted);
        }
        for (int j = 0; j < unwantedCardsSize; j++) {
            deck.addCard(cardsUnwanted);
        }

        List<Card> cards = deck.peekTopDeck(numCardsToPeek);
        assertEquals(numCardsToPeek, cards.size());

        for (int j = 0; j < numCardsToPeek; j++) {
            assertEquals(cardsWanted, cards.get(j));
        }
    }

    @Test
    public void testPeekTopDeck_deckHasTenCards_see3_returnsListWithTopThreeCards() {
        Deck deck = new Deck();
        int wantedCardsSize = 7;
        int unwantedCardsSize = 3;
        int numCardsToPeek = 3;
        Card cardsWanted = EasyMock.createMock(AttackCard.class);
        Card cardsUnwanted = EasyMock.createMock(DefuseCard.class);

        for (int j = 0; j < wantedCardsSize; j++) {
            deck.addCard(cardsWanted);
        }
        for (int j = 0; j < unwantedCardsSize; j++) {
            deck.addCard(cardsUnwanted);
        }

        List<Card> cards = deck.peekTopDeck(numCardsToPeek);
        assertEquals(numCardsToPeek, cards.size());

        for (int j = 0; j < numCardsToPeek; j++) {
            assertEquals(cardsWanted, cards.get(j));
        }
    }

    @Test
    public void testAlterTopDeck_deckIsEmpty_throwsException() {
        String expectedMsg = "deckEmpty";
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(0, 1, 2, 3, 4);

        Exception excepetion = assertThrows(UnsupportedOperationException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasOneCard_returnsUnmodifiedDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = List.of(0);
        Card card = EasyMock.createMock(AttackCard.class);

        int numCardsToPeek = 1;
        int topCardIndex = 0;

        cards.add(card);
        deck.addCard(card);

        deck.alterTopDeck(cards, indexes);
        assertEquals(card, deck.peekTopDeck(numCardsToPeek).get(topCardIndex));
    }

    @Test
    public void testAlterTopDeck_deckHasTwoCards_differentIndexes_returnsModifiedDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(1, 0);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);

        cards.add(card1);
        cards.add(card2);
        deck.addCard(card1);
        deck.addCard(card2);

        deck.alterTopDeck(cards, indexes);
        assertEquals(card2, deck.drawTopCard());
    }

    @Test
    public void testAlterTopDeck_deckHasThreeCards_differentIndexes_returnsModifiedDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(2, 0, 1);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        deck.alterTopDeck(cards, indexes);
        assertEquals(card2, deck.drawTopCard());
        assertEquals(card3, deck.drawTopCard());
        assertEquals(card1, deck.drawTopCard());
    }

    @Test
    public void testAlterTopDeck_deckHasThreeCards_differentSizeIndexes_throwsException() {
        Deck deck = new Deck();
        String expectMsg = "newIndexSize";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(2, 4, 0, 1, 3);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasFiveCards_sameIndexes_returnsUnmodifiedDeck() {
        Deck deck = new Deck();
        int indexesSize = 5;
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(0, 1, 2, 3, 4);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);
        Card card4 = EasyMock.createMock(SkipCard.class);
        Card card5 = EasyMock.createMock(SeeTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);

        deck.alterTopDeck(cards, indexes);
        for (int i = 0; i < indexesSize; i++) {
            assertEquals(cards.get(i), deck.drawTopCard());
        }
    }

    @Test
    public void testAlterTopDeck_deckHasFiveCards_invalidIndexBoundary_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(0, 1, 2, 3, 5);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);
        Card card4 = EasyMock.createMock(SkipCard.class);
        Card card5 = EasyMock.createMock(SeeTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);

        Exception excepetion = assertThrows(IndexOutOfBoundsException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasFiveCards_invalidIndexAboveBoundary_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(0, 1, 2, 3, 6);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);
        Card card4 = EasyMock.createMock(SkipCard.class);
        Card card5 = EasyMock.createMock(SeeTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);


        Exception excepetion = assertThrows(IndexOutOfBoundsException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasFiveCards_invalidIndex_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(6, 4, 3, 2, 0);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);
        Card card4 = EasyMock.createMock(SkipCard.class);
        Card card5 = EasyMock.createMock(SeeTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);

        Exception excepetion = assertThrows(IndexOutOfBoundsException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasFiveCards_differentIndexes_returnsModifiedDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(2, 4, 0, 1, 3);
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(DefuseCard.class);
        Card card3 = EasyMock.createMock(AlterTheFutureCard.class);
        Card card4 = EasyMock.createMock(SkipCard.class);
        Card card5 = EasyMock.createMock(SeeTheFutureCard.class);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);

        deck.alterTopDeck(cards, indexes);
        assertEquals(card3, deck.drawTopCard());
        assertEquals(card4, deck.drawTopCard());
        assertEquals(card1, deck.drawTopCard());
        assertEquals(card5, deck.drawTopCard());
        assertEquals(card2, deck.drawTopCard());
    }

    @Test
    public void testAlterTopDeck_deckHasOneCard_invalidIndex_throwsException() {
        Deck deck = new Deck();
        String expectMsg = "indexOutOfBounds";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = List.of(-1);
        Card card = EasyMock.createMock(AttackCard.class);

        cards.add(card);
        deck.addCard(card);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testAlterTopDeck_deckHasTwoCards_duplicateIndexes_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "newIndexesUnique";
        List<Card> cards = new ArrayList<>();
        List<Integer> indexes = Arrays.asList(1, 1);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);

        cards.add(card1);
        cards.add(card2);
        deck.addCard(card1);
        deck.addCard(card2);


        Exception excepetion = assertThrows(IllegalArgumentException.class, () -> deck.alterTopDeck(cards, indexes));
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testMoveExplodingKittensToTop_deckIsEmpty_throwsException() {
        String expectedMsg = "deckEmpty";
        Deck deck = new Deck();
        Exception exception = assertThrows(UnsupportedOperationException.class, deck::moveExplodingKittensToTop);
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testMoveExplodingKittensToTop_deckHasOneCard_oneExplodingKitten_returnsSameDeck() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(ExplodingKittenCard.class);

        deck.addCard(card);
        deck.moveExplodingKittensToTop();
        assertEquals(deck.drawTopCard(), card);
    }

    @Test
    public void testMoveExplodingKittensToTop_deckHasOneCard_zeroExplodingKitten_returnsSameDeck() {
        Deck deck = new Deck();
        DefuseCard card = EasyMock.createMock(DefuseCard.class);

        deck.addCard(card);
        deck.moveExplodingKittensToTop();
        assertEquals(deck.drawTopCard(), card);
    }

    @Test
    public void testMoveExplodingKittensToTop_deckHasFortyCards_nineExplodingKittens_returnsModifiedDeck() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        Card explodingKitten = EasyMock.createMock(ExplodingKittenCard.class);

        EasyMock.expect(card.getName()).andReturn("Card").anyTimes();
        EasyMock.expect(explodingKitten.getName()).andReturn("Exploding Kitten").anyTimes();
        EasyMock.replay(card, explodingKitten);

        List<Card> explodingKittensList = new ArrayList<>();

        int numOfOtherCards = 31;
        int numOfExplodingKittens = 9;
        for (int i = 0; i < numOfOtherCards; i++) {
            deck.addCard(card);
        }
        for (int i = 0; i < numOfExplodingKittens; i++) {
            deck.addCard(explodingKitten);
            explodingKittensList.add(explodingKitten);
        }

        deck.moveExplodingKittensToTop();
        assertEquals(deck.peekTopDeck(numOfExplodingKittens), explodingKittensList);
        EasyMock.verify(card, explodingKitten);
    }

    @Test
    public void testMoveExplodingKittensToTop_deckHasFortyCards_tenExplodingKitten_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "tooManyExplodingKitten";
        Card card = EasyMock.createMock(Card.class);
        Card explodingKitten = EasyMock.createMock(ExplodingKittenCard.class);

        int numOfOtherCards = 30;
        int numOfExplodingKittens = 10;
        for (int i = 0; i < numOfOtherCards; i++) {
            deck.addCard(card);
        }
        for (int i = 0; i < numOfExplodingKittens; i++) {
            deck.addCard(explodingKitten);
        }

        Exception exception = assertThrows(IllegalArgumentException.class, deck::moveExplodingKittensToTop);
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testMoveExplodingKittensToTop_deckHasFortyCards_zeroExplodingKitten_returnsSameDeck() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);

        List<Card> cardList = new ArrayList<>();

        int numOfOtherCards = 40;
        for (int i = 0; i < numOfOtherCards; i++) {
            deck.addCard(card);
            cardList.add(card);
        }

        deck.moveExplodingKittensToTop();
        assertEquals(deck.peekTopDeck(numOfOtherCards), cardList);
        assertEquals(numOfOtherCards, deck.numberOfCards());
    }

    @Test
    public void testMoveExplodingKittens_deckIsShuffled() {
        Deck deck = new Deck();
        for (int i = 0; i < 300; i++) {
            Card card = EasyMock.createMock(AttackCard.class);
            deck.addCard(card);
            EasyMock.replay(card);
        }
        for (int i = 0; i < 300; i++) {
            Card card = EasyMock.createMock(FavorCard.class);
            deck.addCard(card);
            EasyMock.replay(card);
        }
        for (int i = 0; i < 300; i++) {
            Card card = EasyMock.createMock(AlterTheFutureCard.class);
            deck.addCard(card);
            EasyMock.replay(card);
        }
        for (int i = 0; i < 300; i++) {
            Card card = EasyMock.createMock(ShuffleCard.class);
            deck.addCard(card);
            EasyMock.replay(card);
        }
        Card explodingKittenCard = EasyMock.createMock(ExplodingKittenCard.class);
        Deck deckCopy = deck.copy();
        deck.addCard(explodingKittenCard);

        deck.moveExplodingKittensToTop();
        deck.drawTopCard();
        int numSameCards = 0;
        int maxSameCards = 1200;
        for (int i = 0; i < 1200; i++) {
            if (deck.drawTopCard().equals(deckCopy.drawTopCard())) {
                numSameCards++;
            }
        }
        assertTrue(numSameCards < maxSameCards);
    }

    @Test
    public void testSwapTopAndBottom_emptyDeck_throwsException() {
        Deck deck = new Deck();
        String expectedMessage = "deckEmpty";
        Exception exception = assertThrows(UnsupportedOperationException.class, deck::swapTopAndBottom);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSwapTopAndBottom_deckSize1_returnsSameDeck(){
        Deck deck = new Deck();
        int deckSize = 1;
        Card card1 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        deck.swapTopAndBottom();
        assertEquals(deckSize, deck.numberOfCards());

        Card topCard = deck.drawTopCard();
        assertEquals(card1, topCard);
    }

    @Test
    public void testSwapTopAndBottom_deckSize4_returnsModifiedDeck(){
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);

        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        int numCardsToPeek = 4;
        int topCardIndex = 0;

        Card topCardInitial = deck.peekTopDeck(numCardsToPeek).get(topCardIndex);
        Card bottomCardInitial = deck.drawBottomCard();
        assertEquals(card1, topCardInitial);
        assertEquals(card4, bottomCardInitial);
        deck.swapTopAndBottom();

        Card actualTopCard = deck.peekTopDeck(numCardsToPeek).get(topCardIndex);
        Card actualBottomCard = deck.drawBottomCard();
        assertEquals(card3, actualTopCard);
        assertEquals(card1, actualBottomCard);
    }

    @Test
    public void testAddCard_emptyList_negativeIndex_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        Card card = EasyMock.createMock(Card.class);
        int index = -1;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testAddCard_emptyList_index0_returnsDeckSize1() {
        Deck deck = new Deck();
        Card card = EasyMock.createMock(Card.class);
        int index = 0;

        deck.insertCardAtIndex(card, index);
        assertEquals(deck.drawTopCard(), card);
    }

    @Test
    public void testAddCard_emptyList_indexGreaterThan0_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        Card card = EasyMock.createMock(Card.class);

        int index = 1;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testAddCard_deckSize1_negativeIndex_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        int index = -2;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card2, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testAddCard_deckSize1_index1_returnsDeckSize2() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        int index = 1;
        int deckSize = 2;

        deck.insertCardAtIndex(card2, index);
        assertEquals(deckSize, deck.numberOfCards());
        assertEquals(card2, deck.drawBottomCard());
    }

    @Test
    public void testAddCard_deckSize1_indexGreaterThan1_throwsException() {
        Deck deck = new Deck();
        String expectedMsg = "indexOutOfBounds";
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        deck.addCard(card1);

        int index = 2;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card2, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testAddCard_deckSize4_negativeIndex_throwsException() {
        String expectedMsg = "indexOutOfBounds";
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        Card card5 = EasyMock.createMock(Card.class);
        int index = -20;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card5, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testAddCard_deckSize4_index3_returnsDeckSize5() {
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        int index = 3;
        int deckSize = 5;

        Card card5 = EasyMock.createMock(Card.class);
        deck.insertCardAtIndex(card5, index);
        assertEquals(deckSize, deck.numberOfCards());
        assertEquals(card4, deck.drawBottomCard());
        assertEquals(card5, deck.drawBottomCard());
    }

    @Test
    public void testAddCard_deckSize4_index100_throwsException() {
        String expectedMsg = "indexOutOfBounds";
        Deck deck = new Deck();
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);

        Card card5 = EasyMock.createMock(Card.class);
        int index = 100;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.insertCardAtIndex(card5, index));
        assertEquals(expectedMsg , exception.getMessage());
    }

    @Test
    public void testCopy_emptyDeck_returnsEmptyDeck() {
        Deck deck = new Deck();
        Deck copiedDeck = deck.copy();
        assertTrue(copiedDeck.isEmpty());
    }

    @Test
    public void testCopy_nonEmptyDeck_returnsSameDeck() {
        Deck deck = new Deck();
        int deckSize = 2;
        deck.addCard(EasyMock.createMock(Card.class));
        deck.addCard(EasyMock.createMock(Card.class));
        Deck copiedDeck = deck.copy();
        assertEquals(deck.peekTopDeck(deckSize), copiedDeck.peekTopDeck(deckSize));
    }

    @Test
    public void testSetDeck_codeCoverage() {
        Deck deck = new Deck();
        int numCards = 5;
        for (int i = 0; i < numCards; i++) {
            Card card = EasyMock.createMock(Card.class);
            deck.addCard(card);
        }
        Deck deckCopy = new Deck(deck);
        assertEquals(numCards, deckCopy.numberOfCards());
        for (int i = 0; i < numCards; i++) {
            assertEquals(deck.drawTopCard(), deckCopy.drawTopCard());
        }
    }

    @Test
    public void testIsEmpty_returnsTrue() {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testIsEmpty_returnsFalse() {
        Deck deck = new Deck();
        deck.addCard(EasyMock.createMock(Card.class));
        assertFalse(deck.isEmpty());
    }
}