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

import static org.junit.jupiter.api.Assertions.*;

public class FavorCardTests {
    private FavorCard favorCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;

    @BeforeEach
    public void setUp() {
        favorCard = new FavorCard();
        mockContext = EasyMock.createNiceMock(GameContext.class);
        mockCardManager = EasyMock.createNiceMock(CardManager.class);
        mockPlayerManager = EasyMock.createNiceMock(PlayerManager.class);
    }

    @Test
    public void testGetName_returnsFavor() {
        assertEquals("Favor", favorCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = FavorCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = FavorCard.getCounts();
        int[] counts2 = FavorCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_normalCard_addsToCurrentPlayer() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card normalCard = new AttackCard();
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager).anyTimes();
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(chosenPlayer);
        EasyMock.expect(mockContext.promptPlayer("chosenPlayerCardIndex")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(chosenPlayer, 0)).andReturn(normalCard);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        favorCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_explodingKitten_triggersEffect() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        
        Card mockExplodingKitten = EasyMock.createMock(Card.class);
        EasyMock.expect(mockExplodingKitten.getName()).andReturn("Exploding Kitten").anyTimes();
        mockExplodingKitten.executeEffect(mockContext);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager).anyTimes();
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "targetPlayerId")).andReturn(chosenPlayer);
        EasyMock.expect(mockContext.promptPlayer("chosenPlayerCardIndex")).andReturn(0);
        EasyMock.expect(mockPlayerManager.chooseCard(chosenPlayer, 0)).andReturn(mockExplodingKitten);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager, mockExplodingKitten);
        
        favorCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager, mockExplodingKitten);
    }
}