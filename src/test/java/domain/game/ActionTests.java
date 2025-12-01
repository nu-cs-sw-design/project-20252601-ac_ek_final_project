package domain.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTests {

    @Test
    public void testPass_createsPassAction() {
        Action action = Action.pass();
        assertEquals(Action.Type.PASS, action.getType());
    }

    @Test
    public void testPass_cardIndexIsNegative1() {
        Action action = Action.pass();
        assertEquals(-1, action.getCardIndex());
    }

    @Test
    public void testNope_createsNopeAction() {
        Action action = Action.nope();
        assertEquals(Action.Type.NOPE, action.getType());
    }

    @Test
    public void testNope_cardIndexIsNegative1() {
        Action action = Action.nope();
        assertEquals(-1, action.getCardIndex());
    }

    @Test
    public void testPlayCard_createsPlayCardAction() {
        Action action = Action.playCard(3);
        assertEquals(Action.Type.PLAY_CARD, action.getType());
    }

    @Test
    public void testPlayCard_storesCardIndex() {
        Action action = Action.playCard(3);
        assertEquals(3, action.getCardIndex());
    }

    @Test
    public void testPlayCard_indexZero() {
        Action action = Action.playCard(0);
        assertEquals(0, action.getCardIndex());
    }

    @Test
    public void testPlayCard_negativeIndex() {
        Action action = Action.playCard(-1);
        assertEquals(-1, action.getCardIndex());
    }

    @Test
    public void testPlayCard_largeIndex() {
        Action action = Action.playCard(100);
        assertEquals(100, action.getCardIndex());
    }

    @Test
    public void testGetType_passReturnsPass() {
        Action action = Action.pass();
        assertEquals(Action.Type.PASS, action.getType());
    }

    @Test
    public void testGetType_nopeReturnsNope() {
        Action action = Action.nope();
        assertEquals(Action.Type.NOPE, action.getType());
    }

    @Test
    public void testGetType_playCardReturnsPlayCard() {
        Action action = Action.playCard(0);
        assertEquals(Action.Type.PLAY_CARD, action.getType());
    }

    @Test
    public void testActionType_hasThreeValues() {
        assertEquals(3, Action.Type.values().length);
    }

    @Test
    public void testActionType_containsPass() {
        assertNotNull(Action.Type.valueOf("PASS"));
    }

    @Test
    public void testActionType_containsNope() {
        assertNotNull(Action.Type.valueOf("NOPE"));
    }

    @Test
    public void testActionType_containsPlayCard() {
        assertNotNull(Action.Type.valueOf("PLAY_CARD"));
    }
}