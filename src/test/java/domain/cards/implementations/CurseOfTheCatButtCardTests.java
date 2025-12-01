package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CurseOfTheCatButtCardTests {
    private CurseOfTheCatButtCard curseCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        curseCard = new CurseOfTheCatButtCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
        mockPlayer = EasyMock.createMock(Player.class);
    }

    @Test
    public void testGetName_returnsCurseOfTheCatButt() {
        assertEquals("Curse of the Cat Butt", curseCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = CurseOfTheCatButtCard.getCounts();
        assertArrayEquals(new int[]{2, 3, 4}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = CurseOfTheCatButtCard.getCounts();
        int[] counts2 = CurseOfTheCatButtCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        mockPlayerManager.shuffleHand(mockPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        curseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_hidesTargetPlayerHand() {
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        mockPlayerManager.shuffleHand(mockPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        curseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_shufflesTargetPlayerHand() {
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        mockPlayerManager.shuffleHand(mockPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        curseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_updatesPlayer() {
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        mockPlayerManager.shuffleHand(mockPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        curseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_removesCardFromCurrentPlayer() {
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        mockPlayerManager.shuffleHand(mockPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(mockPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        curseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withRealPlayer() {
        Player realPlayer = new Player(1, new ArrayList<>());
        realPlayer.setHandVisibility(true);
        
        mockContext.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerIDCurse")).andReturn(realPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        mockPlayerManager.shuffleHand(realPlayer);
        EasyMock.expectLastCall();
        mockContext.setPlayer(realPlayer);
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Curse of the Cat Butt");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        curseCard.executeEffect(mockContext);
        
        assertFalse(realPlayer.getHandVisibility());
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }
}