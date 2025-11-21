package domain;

import domain.Cards.*;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
    @Test
    public void testAddCard_givenEmptyHand_returns1() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        Card card = EasyMock.createMock(Card.class);

        int expectedCount = 1;
        assertEquals(expectedCount, player.addCard(card));
    }

    @Test
    public void testAddCard_handSize1_returns2() {
        Card card1 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1));
        Player player = new Player(1, hand);
        Card card2 = EasyMock.createMock(Card.class);

        int expectedCount = 2;
        assertEquals(expectedCount, player.addCard(card2));
    }
  
    @Test
    public void testAddCard_handSize2_returns3() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player(1, hand);

        int expectedCount = 3;
        assertEquals(expectedCount, player.addCard(card3));
    }

    @Test
    public void testAddCard_handSize3_returns4() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3));
        Player player = new Player(1, hand);

        int expectedCount = 4;
        assertEquals(expectedCount, player.addCard(card4));
    }
  
    @Test
    public void testAddCard_handSize90_returns91() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>();
        int handCount = 90;
        for(int i = 0; i < handCount; i++) {
            hand.add(card);
        }
        Player player = new Player(1, hand);

        int expectedCount = 91;
        assertEquals(expectedCount, player.addCard(card));
    }

    @Test
    public void testAddVisibleCard_givenEmptyVisibleHand_returns1() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        Card card = EasyMock.createMock(Card.class);
        int expectedCount = 1;

        assertEquals(expectedCount, player.addVisibleCard(card));
    }

    @Test
    public void testAddVisibleCard_visibleHandSize1_returns2() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player(1, hand);
        player.addVisibleCard(card1);

        int expectedCount = 2;
        assertEquals(expectedCount, player.addVisibleCard(card2));

        ArrayList<Card> visibleHand = player.getVisibleHand();
        assertEquals(hand, visibleHand);
    }

    @Test
    public void testRemoveCard_givenEmptyHand_throwsException() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        int index = 0;
        String expectedMsg = "emptyHand";
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {player.removeCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_givenHandSize1_indexNegative1_throwsException() {
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(1, hand);
        int index = -1;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {player.removeCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_givenHandSize1_index0_throwsException() {
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(1, hand);
        int index = 0;
        int expectedHandSize = 0;
        assertEquals(expectedHandSize, player.removeCard(index));
    }

    @Test
    public void testRemoveCard_givenHandSize1_index1_throwsException() {
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(1, hand);
        int index = 1;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {player.removeCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_givenHandSize3_index0_removesFirstCard() {
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(SkipCard.class);
        Card card3 = EasyMock.createMock(NopeCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3));
        Player player = new Player(1, hand);
        ArrayList<Card> expectedHandAfter = new ArrayList<>(Arrays.asList(card2, card3));
        int expectedSize = 2;
        int index = 0;
        assertEquals(expectedSize,  player.removeCard(index));
        assertEquals(expectedHandAfter, player.getHand());
    }

    @Test
    public void testRemoveCard_givenHandSize91_index89_removesSecondLastCard() {
        int totalNumberOfCards = 91;
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(SkipCard.class);
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards; i++) {
            if(i == 89) {
                hand.add(card2);
            }
            else {
                hand.add(card1);
            }
        }
        Player player = new Player(1, hand);
        int expectedSize = 90;
        ArrayList<Card> expectedHandAfter = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards - 1; i++) {
            expectedHandAfter.add(card1);
        }
        int index = 89;
        assertEquals(expectedSize, player.removeCard(index));
        assertEquals(expectedHandAfter, player.getHand());
    }

    @Test
    public void testRemoveCard_givenHandSize91_index90_removesLastCard() {
        int totalNumberOfCards = 91;
        Card card1 = EasyMock.createMock(AttackCard.class);
        Card card2 = EasyMock.createMock(SkipCard.class);
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards; i++) {
            if(i == 90) {
                hand.add(card2);
            }
            else {
                hand.add(card1);
            }
        }
        Player player = new Player(1, hand);
        int expectedSize = 90;
        ArrayList<Card> expectedHandAfter = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards - 1; i++) {
            expectedHandAfter.add(card1);
        }
        int index = 90;
        assertEquals(expectedSize, player.removeCard(index));
        assertEquals(expectedHandAfter, player.getHand());
    }

    @Test
    public void testRemoveCard_givenHandSize91_index91_throwsException() {
        int totalNumberOfCards = 91;
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards; i++) {
            hand.add(card);
        }
        Player player = new Player(1, hand);
        int index = 91;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {player.removeCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testRemoveCard_givenHandSize91_index90_visibleHandCardRemoved() {
        int totalNumberOfCards = 91;
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards; i++) {
            hand.add(card);
        }
        Player player = new Player(1, hand);
        int index = 90;
        int expectedSize = 90;
        ArrayList<Card> expectedHandAfter = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards - 1; i++) {
            expectedHandAfter.add(card);
        }
        assertEquals(expectedSize, player.removeCard(index));
        assertEquals(expectedHandAfter, player.getHand());
        assertEquals(new ArrayList<>(), player.getVisibleHand());
    }

    @Test
    public void testRemoveCard_givenHandSize91_index90_visibleHandCardNotRemoved() {
        int totalNumberOfCards = 90;
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < totalNumberOfCards; i++) {
            hand.add(card);
        }
        Player player = new Player(1, hand);
        Card card2 = EasyMock.createMock(AttackCard.class);
        player.addCard(card2);
        player.addVisibleCard(card2);

        player.removeCard(1);
        assertEquals(1, player.getVisibleHand().size());
    }

    @Test
    public void testChooseCard_givenEmptyHand_indexNegative1_throwsException() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        int index = -1;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {Card returnCard = player.chooseCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testChooseCard_handSize1_index0_returnOnlyCard() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        int index = 0;
        assertEquals(card, player.chooseCard(index));
    }

    @Test
    public void testChooseCard_handSize2_index2_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card, card));
        Player player = new Player(1, hand);
        int index = 2;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {Card returnCard = player.chooseCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }
  
    @Test
    public void testChooseCard_handSize3_index2_returnsThirdCard() {
        Card card1 = EasyMock.createMock(FavorCard.class);
        Card card2 = EasyMock.createMock(FavorCard.class);
        Card card3 = EasyMock.createMock(NopeCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3));
        Player player = new Player(1, hand);
        int index = 2;
        assertEquals(card3, player.chooseCard(index));
    }

    @Test
    public void testChooseCard_handSize3_index0_returnsFirstCard() {
        Card card1 = EasyMock.createMock(FavorCard.class);
        Card card2 = EasyMock.createMock(FavorCard.class);
        Card card3 = EasyMock.createMock(NopeCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3));
        Player player = new Player(1, hand);
        int index = 0;
        assertEquals(card1, player.chooseCard(index));
    }
  
    @Test
    public void testChooseCard_handSize91_index91_throwsException() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>();
        int handCount = 91;
        for(int i = 0; i < handCount; i++) {
            hand.add(card);
        }
        Player player = new Player(1, hand);
        int index = 91;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {Card returnCard = player.chooseCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testChooseCard_givenEmptyHand_index0_throwsException() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        int index = 0;
        String expectedMsg = "indexOutOfBounds";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {Card returnCard = player.chooseCard(index);});
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testHasCard_givenEmptyHand_searchAttackCard_throwsException() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(0, hand);
        String cardWanted = "Attack";
        assertEquals(-1, player.hasCard(cardWanted));
    }

    @Test
    public void testHasCard_givenHandWithAttackCard_searchFavorCard_throwsException() {
        Card attackCard = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(0, hand);

        EasyMock.expect(attackCard.getName()).andReturn("Attack");
        EasyMock.replay(attackCard);

        String cardWanted = "Favor";
        assertEquals(player.hasCard(cardWanted), -1);
        EasyMock.verify(attackCard);
    }

    @Test
    public void testHasCard_givenHandWithDefuseAndAttackCards_searchDefuseCard_returnIndex() {
        Card defuseCard = EasyMock.createMock(DefuseCard.class);
        Card attackCard = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(defuseCard, attackCard));
        Player player = new Player(0, hand);
        int expectedIndex = 0;

        EasyMock.expect(defuseCard.getName()).andReturn("Defuse").times(1);
        EasyMock.expect(attackCard.getName()).andReturn("Attack").times(1);
        EasyMock.replay(defuseCard, attackCard);

        String cardWanted = "Defuse";
        assertEquals(expectedIndex, player.hasCard(cardWanted));
        EasyMock.verify(defuseCard);
    }

    @Test
    public void testHasCard_givenHandWithDefuseAndAttackCards_searchDefuseAndAttack_returnsIndexes() {
        Card card1 = EasyMock.createMock(DefuseCard.class);
        Card card2 = EasyMock.createMock(AttackCard.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player(1, hand);

        int expectedIndexDefuse = 0;
        int expectedIndexAttack = 1;

        EasyMock.expect(card1.getName()).andReturn("Defuse").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card1, card2);

        assertEquals(expectedIndexDefuse, player.hasCard("Defuse"));
        assertEquals(expectedIndexAttack, player.hasCard("Attack"));

        EasyMock.verify(card1, card2);
    }

    @Test
    public void testDecreaseTurnByOne_given0Turns_throwsException() {
        ArrayList<Card> hand = new ArrayList<>();
        String expectedMsg = "nonNegTurns";
        Player player = new Player(1, hand);

        player.decreaseTurnByOne();
        Exception excepetion = assertThrows(UnsupportedOperationException.class, player::decreaseTurnByOne);
        assertEquals(expectedMsg, excepetion.getMessage());
    }

    @Test
    public void testDecreaseTurnByOne_given1Turn_returns0() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        int numberOfTurns = 1;
        player.setNumberOfTurns(numberOfTurns);

        int expectedNumberOfTurns = 0;
        player.decreaseTurnByOne();
        assertEquals(expectedNumberOfTurns, player.getNumberOfTurns());
    }

    @Test
    public void testDecreaseTurnByOne_given2Turns_returns1() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        int numberOfTurns = 2;
        player.setNumberOfTurns(numberOfTurns);

        int expectedNumberOfTurns = 1;
        player.decreaseTurnByOne();
        assertEquals(expectedNumberOfTurns, player.getNumberOfTurns());
    }

    @Test
    public void testHasTwoOf_oneCardInHand_returnsFalse() {
        Card desiredCard = EasyMock.createMock(Card.class);
        Card otherCard1 = EasyMock.createMock(Card.class);
        Card otherCard2 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(desiredCard, otherCard1, otherCard2));
        Player player = new Player(1, hand);

        EasyMock.expect(desiredCard.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.expect(otherCard1.getName()).andReturn("OtherCard1").anyTimes();
        EasyMock.expect(otherCard2.getName()).andReturn("OtherCard2").anyTimes();
        EasyMock.replay(desiredCard, otherCard1, otherCard2);

        assertFalse(player.hasTwoOf("DesiredCard"));
        EasyMock.verify(desiredCard, otherCard1, otherCard2);
    }

    @Test
    public void testHasTwoOf_twoCardInHand_returnsTrue() {
        Card desiredCard = EasyMock.createMock(Card.class);
        Card otherCard1 = EasyMock.createMock(Card.class);
        Card otherCard2 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(desiredCard, otherCard1, otherCard2));
        Player player = new Player(1, hand);

        EasyMock.expect(desiredCard.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.expect(otherCard1.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.expect(otherCard2.getName()).andReturn("OtherCard").anyTimes();
        EasyMock.replay(desiredCard, otherCard1, otherCard2);

        assertTrue(player.hasTwoOf("DesiredCard"));
        EasyMock.verify(desiredCard, otherCard1, otherCard2);
    }

    @Test
    public void testHasTwoOf_greaterTwoCardInHand_returnsTrue() {
        Card desiredCard = EasyMock.createMock(Card.class);
        Card otherCard1 = EasyMock.createMock(Card.class);
        Card otherCard2 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(desiredCard, otherCard1, otherCard2));
        Player player = new Player(1, hand);

        EasyMock.expect(desiredCard.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.expect(otherCard1.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.expect(otherCard2.getName()).andReturn("DesiredCard").anyTimes();
        EasyMock.replay(desiredCard, otherCard1, otherCard2);

        assertTrue(player.hasTwoOf("DesiredCard"));
        EasyMock.verify(desiredCard, otherCard1, otherCard2);
    }

    @Test
    public void testShuffleHand_givenEmptyHand_throwsException() {
        String expectMsg = "emptyHand";
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        Exception exception = assertThrows(UnsupportedOperationException.class, player::shuffleHand);
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testShuffleHand_handSize1_returnsSameHand() {
        Card card1 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1));
        Player player = new Player(1, hand);

        ArrayList<Card> handBeforeShuffle = new ArrayList<>(player.getHand());
        player.shuffleHand();
        ArrayList<Card> handAfterShuffle = player.getHand();

        assertEquals(handBeforeShuffle.size(), handAfterShuffle.size());
        assertTrue(handAfterShuffle.containsAll(handBeforeShuffle));
    }

    @Test
    public void testShuffleHand_handSize4_returnsShuffledHand() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));
        Player player = new Player(1, hand);

        ArrayList<Card> handBeforeShuffle = new ArrayList<>(player.getHand());
        player.shuffleHand();
        ArrayList<Card> handAfterShuffle = player.getHand();

        assertEquals(handBeforeShuffle.size(), handAfterShuffle.size());
        assertTrue(handAfterShuffle.containsAll(handBeforeShuffle));
    }

    @Test
    public void testShuffleHand_handSize1000_returnsShuffledHand() {
        ArrayList<Card> hand = new ArrayList<>();
        int handSize = 1000;
        for (int i = 0; i < handSize; i++) {
            Card card = EasyMock.createMock(Card.class);
            hand.add(card);
        }
        Player player = new Player(1, hand);

        ArrayList<Card> handBeforeShuffle = new ArrayList<>(player.getHand());
        player.shuffleHand();
        ArrayList<Card> handAfterShuffle = player.getHand();

        assertEquals(handBeforeShuffle.size(), handAfterShuffle.size());
        assertTrue(handAfterShuffle.containsAll(handBeforeShuffle));
        assertNotEquals(handBeforeShuffle, handAfterShuffle);
    }

    @Test
    public void testSetVisibility_setTrue_getSameVisibility_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(true);
        assertTrue(player.getHandVisibility());
    }

    @Test
    public void testSetVisibility_setFalse_getSameVisibility_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(false);
        assertFalse(player.getHandVisibility());
    }

    @Test
    public void testSetTurnOver_setTrue_getSameTurnOver_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        player.decreaseTurnByOne();
        assertTrue(player.getIsTurnOver());
    }

    @Test
    public void testSetTurnOver_setFalse_getSameTurnOver_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        int numTurns = 1;
        player.setNumberOfTurns(numTurns);
        assertFalse(player.getIsTurnOver());
    }

    @Test
    public void testSetTurns_givenPlayerHasTwoTurnsIsTurnOverReturnsFalse(){
        Player player = new Player(1, new ArrayList<>());
        int numTurns = 2;
        player.setNumberOfTurns(numTurns);
        assertFalse(player.getIsTurnOver());
    }

    @Test
    public void testSetHandVisibility_setTrue_getSameHandVisibility_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(true);
        assertTrue(player.getHandVisibility());
    }

    @Test
    public void testSetHandVisibility_setFalse_getSameHandVisibility_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(false);
        assertFalse(player.getHandVisibility());
    }

    @Test
    public void testGetHand_falseVisibility_returnsEmptyHand_codeCoverage() {
        ArrayList<Card> hand = new ArrayList<>();
        int handSize = 1000;
        for (int i = 0; i < handSize; i++) {
            Card card = EasyMock.createMock(Card.class);
            hand.add(card);
        }
        Player player = new Player(1, hand);
        assertEquals(handSize, player.getHand().size());
        player.setHandVisibility(false);
        assertEquals(new ArrayList<>(), player.getHand());
    }

    @Test
    public void testSetHasNope_setFalse_returnsFalse_codeCoverage() {;
        Player player = new Player(1, new ArrayList<>());
        assertFalse(player.getHasNope());
        player.setHasNope(false);
        assertFalse(player.getHasNope());
    }

    @Test
    public void testSetHasNope_setTrue_returnsTrue_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        assertFalse(player.getHasNope());
        player.setHasNope(true);
        assertTrue(player.getHasNope());
    }

    @Test
    public void testIsHandEmpty_emptyHand_returnsTrue_codeCoverage() {
        Player player = new Player(1, new ArrayList<>());
        assertTrue(player.isHandEmpty());
    }

    @Test
    public void testIsHandEmpty_nonEmptyHand_returnsFalse_codeCoverage() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        assertFalse(player.isHandEmpty());
    }
}