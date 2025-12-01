package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TargetedAttackCardTests {
    private TargetedAttackCard targetedAttackCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private Player mockCurrentPlayer;
    private Player mockTargetPlayer;

    @BeforeEach
    public void setUp() {
        targetedAttackCard = new TargetedAttackCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockCurrentPlayer = EasyMock.createMock(Player.class);
        mockTargetPlayer = EasyMock.createMock(Player.class);
    }

    @Test
    public void testGetName_returnsTargetedAttack() {
        assertEquals("Targeted Attack", targetedAttackCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = TargetedAttackCard.getCounts();
        assertArrayEquals(new int[]{5, 6, 8}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = TargetedAttackCard.getCounts();
        int[] counts2 = TargetedAttackCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(1);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(2);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_endsCurrentPlayerTurn() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(1);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(2);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_withStackedTurns() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(3);
        EasyMock.expect(mockCardManager.calculateAttackTurns(3, 2)).andReturn(4);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(4);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_setsTargetPlayerTurns() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(1);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(2);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_setsNextPlayerTargetPlayer() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(1);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(2);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_removesCardFromPlayer() {
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockTargetPlayer);
        EasyMock.expect(mockCurrentPlayer.getNumberOfTurns()).andReturn(1);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockTargetPlayer.setNumberOfTurns(2);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(mockTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
        
        targetedAttackCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockCurrentPlayer, mockTargetPlayer);
    }

    @Test
    public void testExecuteEffect_withRealPlayers() {
        Player realCurrentPlayer = new Player(1, new ArrayList<>());
        realCurrentPlayer.setNumberOfTurns(1);
        Player realTargetPlayer = new Player(2, new ArrayList<>());
        
        mockContext.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(realCurrentPlayer);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(realTargetPlayer);
        EasyMock.expect(mockCardManager.calculateAttackTurns(1, 2)).andReturn(2);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Targeted Attack");
        EasyMock.expectLastCall();
        mockContext.setPlayer(realTargetPlayer);
        EasyMock.expectLastCall();
        mockContext.setNextPlayerTargetPlayer(realTargetPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        targetedAttackCard.executeEffect(mockContext);
        
        assertEquals(2, realTargetPlayer.getNumberOfTurns());
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}