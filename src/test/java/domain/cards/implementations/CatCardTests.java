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

public class CatCardTests {
    private CatCard catCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;

    @BeforeEach
    public void setUp() {
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
    }

    @Test
    public void testGetName_tacoCat() {
        catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        assertEquals("Taco Cat", catCard.getName());
    }

    @Test
    public void testGetName_beardedCat() {
        catCard = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        assertEquals("Bearded Cat", catCard.getName());
    }

    @Test
    public void testGetName_hairyPotatoCat() {
        catCard = new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT);
        assertEquals("Hairy Potato Cat", catCard.getName());
    }

    @Test
    public void testGetName_rainbowRalphingCat() {
        catCard = new CatCard(CatCard.CatCardType.RAINBOW_RALPHING_CAT);
        assertEquals("Rainbow Ralphing Cat", catCard.getName());
    }

    @Test
    public void testGetName_cattermelon() {
        catCard = new CatCard(CatCard.CatCardType.CATTERMELON);
        assertEquals("Cattermelon", catCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = CatCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 8}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = CatCard.getCounts();
        int[] counts2 = CatCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testCatCardType_fiveTypes() {
        assertEquals(5, CatCard.CatCardType.values().length);
    }

    @Test
    public void testCatCardType_cardName() {
        assertEquals("Bearded Cat", CatCard.CatCardType.BEARDED_CAT.cardName());
        assertEquals("Hairy Potato Cat", CatCard.CatCardType.HAIRY_POTATO_CAT.cardName());
        assertEquals("Rainbow Ralphing Cat", CatCard.CatCardType.RAINBOW_RALPHING_CAT.cardName());
        assertEquals("Taco Cat", CatCard.CatCardType.TACO_CAT.cardName());
        assertEquals("Cattermelon", CatCard.CatCardType.CATTERMELON.cardName());
    }

    @Test
    public void testExecuteEffect_hasTwoOfSameType_success() {
        catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card takenCard = new SkipCard();
        
        mockContext.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasTwoOf(currentPlayer, "Taco Cat")).andReturn(true);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerID")).andReturn(chosenPlayer);
        EasyMock.expect(mockCardManager.takeCardFromPlayer(mockContext, chosenPlayer)).andReturn(takenCard);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Taco Cat");
        EasyMock.expectLastCall().times(2);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        catCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_doesNotHaveTwo_throwsException() {
        catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasTwoOf(currentPlayer, "Taco Cat")).andReturn(false);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, 
            () -> catCard.executeEffect(mockContext));
        assertEquals("needTwo", exception.getMessage());
        
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_beardedCat_hasTwoOfSameType() {
        catCard = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player chosenPlayer = new Player(2, new ArrayList<>());
        Card takenCard = new SkipCard();
        
        mockContext.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasTwoOf(currentPlayer, "Bearded Cat")).andReturn(true);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        EasyMock.expect(mockCardManager.chooseValidTargetPlayer(mockContext, "playerID")).andReturn(chosenPlayer);
        EasyMock.expect(mockCardManager.takeCardFromPlayer(mockContext, chosenPlayer)).andReturn(takenCard);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Bearded Cat");
        EasyMock.expectLastCall().times(2);
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        catCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }
}