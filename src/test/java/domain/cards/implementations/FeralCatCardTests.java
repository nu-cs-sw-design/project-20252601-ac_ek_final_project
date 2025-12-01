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

public class FeralCatCardTests {
    private FeralCatCard feralCatCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;

    @BeforeEach
    public void setUp() {
        feralCatCard = new FeralCatCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
    }

    @Test
    public void testGetName_returnsFeralCat() {
        assertEquals("Feral Cat", feralCatCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = FeralCatCard.getCounts();
        assertArrayEquals(new int[]{4, 8, 12}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = FeralCatCard.getCounts();
        int[] counts2 = FeralCatCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_withCatCard_takeCardFromPlayer() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card takenCard = new SkipCard();
        
        mockContext.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Bearded Cat")).andReturn(0);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerID")).andReturn(chosenPlayer);
        EasyMock.expect(mockCardManager.takeCardFromPlayer(mockContext, chosenPlayer)).andReturn(takenCard);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Bearded Cat");
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Feral Cat");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        feralCatCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_withTwoFeralCats_noOtherCatCard() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card takenCard = new SkipCard();
        
        mockContext.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Bearded Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Hairy Potato Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Rainbow Ralphing Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Taco Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Cattermelon")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasTwoOf(currentPlayer, "Feral Cat")).andReturn(true);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerID")).andReturn(chosenPlayer);
        EasyMock.expect(mockCardManager.takeCardFromPlayer(mockContext, chosenPlayer)).andReturn(takenCard);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Feral Cat");
        EasyMock.expectLastCall().times(2);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        feralCatCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_noCatCards_noTwoFeralCats_throwsException() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Bearded Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Hairy Potato Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Rainbow Ralphing Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Taco Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Cattermelon")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasTwoOf(currentPlayer, "Feral Cat")).andReturn(false);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, 
            () -> feralCatCard.executeEffect(mockContext));
        assertEquals("needTwo", exception.getMessage());
        
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_withTacoCat_usesTacoCat() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card takenCard = new SkipCard();
        
        mockContext.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Bearded Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Hairy Potato Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Rainbow Ralphing Cat")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Taco Cat")).andReturn(0);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerID")).andReturn(chosenPlayer);
        EasyMock.expect(mockCardManager.takeCardFromPlayer(mockContext, chosenPlayer)).andReturn(takenCard);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Taco Cat");
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Feral Cat");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        feralCatCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }
}