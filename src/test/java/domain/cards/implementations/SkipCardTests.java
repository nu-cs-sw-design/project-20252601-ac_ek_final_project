package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SkipCardTests {
    private SkipCard skipCard;
    private GameContext mockContext;
    private CardManager mockCardManager;

    @BeforeEach
    public void setUp() {
        skipCard = new SkipCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
    }

    @Test
    public void testGetName_returnsSkip() {
        assertEquals("Skip", skipCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = SkipCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = SkipCard.getCounts();
        int[] counts2 = SkipCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_oneTurn_setsToZero() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setNumberOfTurns(1);
        
        mockContext.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        skipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_multipleTurns_decreasesByOne() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setNumberOfTurns(3);
        
        mockContext.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        mockContext.setCurrentPlayerTurns(2);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        skipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_zeroTurns_staysZero() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setNumberOfTurns(0);
        
        mockContext.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Skip");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        skipCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}