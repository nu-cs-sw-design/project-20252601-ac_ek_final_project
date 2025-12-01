package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCardTests {
    private MarkCard markCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        markCard = new MarkCard();
        mockContext = EasyMock.createNiceMock(GameContext.class);
        mockCardManager = EasyMock.createNiceMock(CardManager.class);
        mockPlayerManager = EasyMock.createNiceMock(PlayerManager.class);
        mockPlayer = EasyMock.createNiceMock(Player.class);
    }

    @Test
    public void testGetName_returnsMark() {
        assertEquals("Mark", markCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = MarkCard.getCounts();
        assertArrayEquals(new int[]{3, 4, 5}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = MarkCard.getCounts();
        int[] counts2 = MarkCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_marksCard() {
        Card targetCard = new SkipCard();
        List<Card> emptyVisibleHand = new ArrayList<>();
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(0);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        EasyMock.expect(mockPlayerManager.chooseCard(mockPlayer, 0)).andReturn(targetCard);
        EasyMock.expect(mockPlayerManager.getVisibleHand(mockPlayer)).andReturn(emptyVisibleHand);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        markCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_negativeIndex_throwsException() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            markCard.executeEffect(mockContext);
        });
    }

    @Test
    public void testExecuteEffect_indexOutOfBounds_throwsException() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(5);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            markCard.executeEffect(mockContext);
        });
    }

    @Test
    public void testExecuteEffect_cardAlreadyVisible_throwsException() {
        Card targetCard = new SkipCard();
        List<Card> visibleHand = new ArrayList<>(Arrays.asList(targetCard));
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(0);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        EasyMock.expect(mockPlayerManager.chooseCard(mockPlayer, 0)).andReturn(targetCard);
        EasyMock.expect(mockPlayerManager.getVisibleHand(mockPlayer)).andReturn(visibleHand);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            markCard.executeEffect(mockContext);
        });
    }

    @Test
    public void testExecuteEffect_validIndex_atBoundary() {
        Card targetCard = new AttackCard();
        List<Card> emptyVisibleHand = new ArrayList<>();
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(2);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        EasyMock.expect(mockPlayerManager.chooseCard(mockPlayer, 2)).andReturn(targetCard);
        EasyMock.expect(mockPlayerManager.getVisibleHand(mockPlayer)).andReturn(emptyVisibleHand);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        markCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_indexEqualsHandCount_throwsException() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(mockPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.promptPlayer("targetCardIndex")).andReturn(3);
        EasyMock.expect(mockPlayerManager.getHandCount(mockPlayer)).andReturn(3);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockPlayer);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            markCard.executeEffect(mockContext);
        });
    }
}