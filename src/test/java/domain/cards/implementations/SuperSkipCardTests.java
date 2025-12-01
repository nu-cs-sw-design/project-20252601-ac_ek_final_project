package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSkipCardTests {
    private SuperSkipCard superSkipCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        superSkipCard = new SuperSkipCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockPlayer = EasyMock.createMock(Player.class);
    }

    @Test
    public void testGetName_returnsSuperSkip() {
        assertEquals("Super Skip", superSkipCard.getName());
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(1);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withOneTurn_endsTurn() {
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(1);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withMultipleTurns_endsAllTurns() {
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(5);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withZeroTurns_noSetTurnsCalled() {
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(0);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_removesCardFromPlayer() {
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withRealPlayer() {
        Player realPlayer = new Player(1, new ArrayList<>());
        realPlayer.setNumberOfTurns(3);
        
        mockContext.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(realPlayer);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Super Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        superSkipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}