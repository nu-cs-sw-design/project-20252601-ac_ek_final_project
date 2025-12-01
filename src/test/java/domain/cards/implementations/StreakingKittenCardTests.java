package domain.cards.implementations;

import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class StreakingKittenCardTests {
    private StreakingKittenCard streakingKittenCard;
    private GameContext mockContext;

    @BeforeEach
    public void setUp() {
        streakingKittenCard = new StreakingKittenCard();
        mockContext = EasyMock.createMock(GameContext.class);
    }

    @Test
    public void testGetName_returnsStreakingKitten() {
        assertEquals("Streaking Kitten", streakingKittenCard.getName());
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("streakingKittenCard");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext);
        
        streakingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext);
    }

    @Test
    public void testExecuteEffect_onlyDisplaysMessage() {
        mockContext.displayMessage("streakingKittenCard");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext);
        
        streakingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext);
    }

    @Test
    public void testConstructor_createsValidCard() {
        StreakingKittenCard card = new StreakingKittenCard();
        assertNotNull(card);
        assertEquals("Streaking Kitten", card.getName());
    }
}