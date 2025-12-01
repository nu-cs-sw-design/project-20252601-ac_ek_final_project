package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardInfoTests {

    @Test
    public void testConstructor_createsCardInfo() {
        CardInfo cardInfo = new CardInfo(0, "Skip", true);
        assertNotNull(cardInfo);
    }

    @Test
    public void testConstructor_withZeroIndex() {
        CardInfo cardInfo = new CardInfo(0, "Attack", true);
        assertEquals(0, cardInfo.index());
    }

    @Test
    public void testConstructor_withPositiveIndex() {
        CardInfo cardInfo = new CardInfo(5, "Shuffle", false);
        assertEquals(5, cardInfo.index());
    }

    @Test
    public void testConstructor_withNegativeIndex() {
        CardInfo cardInfo = new CardInfo(-1, "Nope", true);
        assertEquals(-1, cardInfo.index());
    }

    @Test
    public void testConstructor_withEmptyName() {
        CardInfo cardInfo = new CardInfo(0, "", true);
        assertEquals("", cardInfo.name());
    }

    @Test
    public void testConstructor_withNullName() {
        CardInfo cardInfo = new CardInfo(0, null, true);
        assertNull(cardInfo.name());
    }

    @Test
    public void testIndex_returnsCorrectValue() {
        CardInfo cardInfo = new CardInfo(3, "Favor", true);
        assertEquals(3, cardInfo.index());
    }

    @Test
    public void testIndex_withLargeValue() {
        CardInfo cardInfo = new CardInfo(100, "Card", false);
        assertEquals(100, cardInfo.index());
    }

    @Test
    public void testName_returnsCorrectValue() {
        CardInfo cardInfo = new CardInfo(0, "Exploding Kitten", false);
        assertEquals("Exploding Kitten", cardInfo.name());
    }

    @Test
    public void testName_withLongName() {
        CardInfo cardInfo = new CardInfo(0, "Curse of the Cat Butt", true);
        assertEquals("Curse of the Cat Butt", cardInfo.name());
    }

    @Test
    public void testName_withSpecialCharacters() {
        CardInfo cardInfo = new CardInfo(0, "Card #1", true);
        assertEquals("Card #1", cardInfo.name());
    }

    @Test
    public void testIsPlayable_true() {
        CardInfo cardInfo = new CardInfo(0, "Skip", true);
        assertTrue(cardInfo.isPlayable());
    }

    @Test
    public void testIsPlayable_false() {
        CardInfo cardInfo = new CardInfo(0, "Defuse", false);
        assertFalse(cardInfo.isPlayable());
    }

    @Test
    public void testMultipleInstances_differentValues() {
        CardInfo card1 = new CardInfo(0, "Skip", true);
        CardInfo card2 = new CardInfo(1, "Attack", false);
        CardInfo card3 = new CardInfo(2, "Shuffle", true);

        assertEquals(0, card1.index());
        assertEquals(1, card2.index());
        assertEquals(2, card3.index());

        assertEquals("Skip", card1.name());
        assertEquals("Attack", card2.name());
        assertEquals("Shuffle", card3.name());

        assertTrue(card1.isPlayable());
        assertFalse(card2.isPlayable());
        assertTrue(card3.isPlayable());
    }

    @Test
    public void testImmutability_valuesDoNotChange() {
        CardInfo cardInfo = new CardInfo(5, "Test Card", true);
        
        assertEquals(5, cardInfo.index());
        assertEquals("Test Card", cardInfo.name());
        assertTrue(cardInfo.isPlayable());
        
        assertEquals(5, cardInfo.index());
        assertEquals("Test Card", cardInfo.name());
        assertTrue(cardInfo.isPlayable());
    }
}