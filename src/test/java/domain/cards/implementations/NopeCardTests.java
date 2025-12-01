package domain.cards.implementations;

import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class NopeCardTests {
    private NopeCard nopeCard;
    private GameContext mockContext;

    @BeforeEach
    public void setUp() {
        nopeCard = new NopeCard();
        mockContext = EasyMock.createMock(GameContext.class);
    }

    @Test
    public void testGetName_returnsNope() {
        assertEquals("Nope", nopeCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = NopeCard.getCounts();
        assertArrayEquals(new int[]{5, 7, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = NopeCard.getCounts();
        int[] counts2 = NopeCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("nopeCard");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext);
        
        nopeCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext);
    }
}