package domain.player;

import domain.cards.Card;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    public void testPlayer_constructor_createsPlayerWithId() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        assertEquals(1, player.getId());
    }

    @Test
    public void testPlayer_constructor_createsPlayerWithHand() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        assertEquals(1, player.getHand().size());
    }

    @Test
    public void testPlayer_constructor_defaultTurnsIsOne() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        assertEquals(1, player.getNumberOfTurns());
    }

    @Test
    public void testPlayer_copyConstructor_copiesAllFields() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(EasyMock.createMock(Card.class));
        Player original = new Player(1, hand);
        original.setNumberOfTurns(3);
        original.setHandVisibility(false);
        original.setHasNope(true);
        original.setAI(true);
        PlayerController controller = EasyMock.createMock(PlayerController.class);
        original.setController(controller);

        Player copy = new Player(original);
        
        assertEquals(original.getId(), copy.getId());
        assertEquals(original.getNumberOfTurns(), copy.getNumberOfTurns());
        assertEquals(original.getHandVisibility(), copy.getHandVisibility());
        assertEquals(original.getHasNope(), copy.getHasNope());
        assertEquals(original.isAI(), copy.isAI());
        assertEquals(original.getController(), copy.getController());
    }

    @Test
    public void testGetId_returnsCorrectId() {
        Player player = new Player(5, new ArrayList<>());
        assertEquals(5, player.getId());
    }

    @Test
    public void testGetHand_returnsCopyOfHand() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        ArrayList<Card> returnedHand = player.getHand();
        returnedHand.clear();
        
        assertEquals(1, player.getHand().size());
    }

    @Test
    public void testSetHand_replacesHand() {
        Player player = new Player(1, new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> newHand = new ArrayList<>(Arrays.asList(card));
        
        player.setHand(newHand);
        assertEquals(1, player.getHand().size());
    }

    @Test
    public void testGetHandObject_returnsHandObject() {
        Player player = new Player(1, new ArrayList<>());
        assertNotNull(player.getHandObject());
    }

    @Test
    public void testGetNumberOfTurns_defaultIsOne() {
        Player player = new Player(1, new ArrayList<>());
        assertEquals(1, player.getNumberOfTurns());
    }

    @Test
    public void testSetNumberOfTurns_setsCorrectValue() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(5);
        assertEquals(5, player.getNumberOfTurns());
    }

    @Test
    public void testSetNumberOfTurns_zeroTurns() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(0);
        assertEquals(0, player.getNumberOfTurns());
    }

    @Test
    public void testGetHandVisibility_defaultIsTrue() {
        Player player = new Player(1, new ArrayList<>());
        assertTrue(player.getHandVisibility());
    }

    @Test
    public void testSetHandVisibility_setsFalse() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(false);
        assertFalse(player.getHandVisibility());
    }

    @Test
    public void testSetHandVisibility_setsTrue() {
        Player player = new Player(1, new ArrayList<>());
        player.setHandVisibility(false);
        player.setHandVisibility(true);
        assertTrue(player.getHandVisibility());
    }

    @Test
    public void testGetHasNope_defaultIsFalse() {
        Player player = new Player(1, new ArrayList<>());
        assertFalse(player.getHasNope());
    }

    @Test
    public void testSetHasNope_setsTrue() {
        Player player = new Player(1, new ArrayList<>());
        player.setHasNope(true);
        assertTrue(player.getHasNope());
    }

    @Test
    public void testSetHasNope_setsFalse() {
        Player player = new Player(1, new ArrayList<>());
        player.setHasNope(true);
        player.setHasNope(false);
        assertFalse(player.getHasNope());
    }

    @Test
    public void testIsAI_defaultIsFalse() {
        Player player = new Player(1, new ArrayList<>());
        assertFalse(player.isAI());
    }

    @Test
    public void testSetAI_setsTrue() {
        Player player = new Player(1, new ArrayList<>());
        player.setAI(true);
        assertTrue(player.isAI());
    }

    @Test
    public void testSetAI_setsFalse() {
        Player player = new Player(1, new ArrayList<>());
        player.setAI(true);
        player.setAI(false);
        assertFalse(player.isAI());
    }

    @Test
    public void testGetController_initiallyNull() {
        Player player = new Player(1, new ArrayList<>());
        assertNull(player.getController());
    }

    @Test
    public void testSetController_setsController() {
        Player player = new Player(1, new ArrayList<>());
        PlayerController controller = EasyMock.createMock(PlayerController.class);
        player.setController(controller);
        assertEquals(controller, player.getController());
    }
}