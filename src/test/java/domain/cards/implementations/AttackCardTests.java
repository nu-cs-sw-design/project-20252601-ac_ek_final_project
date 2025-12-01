package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttackCardTests {
    private AttackCard attackCard;
    private GameContext mockContext;
    private CardManager mockCardManager;

    @BeforeEach
    public void setUp() {
        attackCard = new AttackCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
    }

    @Test
    public void testGetName_returnsAttack() {
        assertEquals("Attack", attackCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = AttackCard.getCounts();
        assertArrayEquals(new int[]{2, 3, 5}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = AttackCard.getCounts();
        int[] counts2 = AttackCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_currentPlayerHasOneTurn() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setNumberOfTurns(1);
        
        mockContext.displayMessage("attackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTurns(2);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Attack");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        attackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_currentPlayerHasMultipleTurns() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setNumberOfTurns(3);
        
        mockContext.displayMessage("attackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.calculateAttackTurns(3, 2)).andReturn(5);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTurns(5);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Attack");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        attackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}