package domain.cards;

import domain.game.GameContext;
import domain.game.GameEngine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class CardTests {
    private GameEngine mockGameEngine;
    
    @BeforeEach
    public void setUp() {
        mockGameEngine = EasyMock.createMock(GameEngine.class);
    }
    
    @Test
    public void testPlayCard_callsExecuteEffect() {
        TestCard testCard = new TestCard();
        
        EasyMock.replay(mockGameEngine);
        
        assertFalse(testCard.wasExecuteEffectCalled());
        
        testCard.playCard(mockGameEngine);
        
        assertTrue(testCard.wasExecuteEffectCalled());
        assertNotNull(testCard.getReceivedContext());
        
        EasyMock.verify(mockGameEngine);
    }
    
    @Test
    public void testPlayCard_passesCorrectContext() {
        TestCard testCard = new TestCard();
        
        EasyMock.replay(mockGameEngine);
        
        testCard.playCard(mockGameEngine);
        
        GameContext receivedContext = testCard.getReceivedContext();
        assertNotNull(receivedContext);
        
        EasyMock.verify(mockGameEngine);
    }
    
    @Test
    public void testGetName_returnsCorrectName() {
        TestCard testCard = new TestCard();
        assertEquals("TestCard", testCard.getName());
    }
    
    private static class TestCard extends Card {
        private boolean executeEffectCalled = false;
        private GameContext receivedContext = null;
        
        @Override
        public void executeEffect(GameContext context) {
            this.executeEffectCalled = true;
            this.receivedContext = context;
        }
        
        @Override
        public String getName() {
            return "TestCard";
        }
        
        public boolean wasExecuteEffectCalled() {
            return executeEffectCalled;
        }
        
        public GameContext getReceivedContext() {
            return receivedContext;
        }
    }
}