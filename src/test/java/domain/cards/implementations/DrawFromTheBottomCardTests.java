package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class DrawFromTheBottomCardTests {
    private DrawFromTheBottomCard drawFromTheBottomCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private Deck mockDeck;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        drawFromTheBottomCard = new DrawFromTheBottomCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
        mockPlayer = EasyMock.createMock(Player.class);
    }

    @Test
    public void testGetName_returnsDrawFromTheBottom() {
        assertEquals("Draw From The Bottom", drawFromTheBottomCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = DrawFromTheBottomCard.getCounts();
        assertArrayEquals(new int[]{6, 8, 10}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = DrawFromTheBottomCard.getCounts();
        int[] counts2 = DrawFromTheBottomCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_normalCard_addedToPlayer() {
        Card normalCard = new SkipCard();
        
        mockContext.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.drawBottomCard()).andReturn(normalCard);
        mockContext.addToCurrentPlayer(normalCard);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(1);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Draw From The Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck, mockPlayer);
        
        drawFromTheBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck, mockPlayer);
    }

    @Test
    public void testExecuteEffect_explodingKitten_triggersEffect() {
        ExplodingKittenCard explodingCard = EasyMock.createMock(ExplodingKittenCard.class);
        
        mockContext.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.drawBottomCard()).andReturn(explodingCard);
        explodingCard.executeEffect(mockContext);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(1);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Draw From The Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck, mockPlayer, explodingCard);
        
        drawFromTheBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck, mockPlayer, explodingCard);
    }

    @Test
    public void testExecuteEffect_implodingKitten_triggersEffect() {
        ImplodingKittenCard implodingCard = EasyMock.createMock(ImplodingKittenCard.class);
        
        mockContext.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.drawBottomCard()).andReturn(implodingCard);
        implodingCard.executeEffect(mockContext);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(1);
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Draw From The Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck, mockPlayer, implodingCard);
        
        drawFromTheBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck, mockPlayer, implodingCard);
    }

    @Test
    public void testExecuteEffect_withMultipleTurns_decreasesTurns() {
        Card normalCard = new ShuffleCard();
        
        mockContext.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.drawBottomCard()).andReturn(normalCard);
        mockContext.addToCurrentPlayer(normalCard);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(3);
        mockContext.setCurrentPlayerTurns(2);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Draw From The Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck, mockPlayer);
        
        drawFromTheBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck, mockPlayer);
    }

    @Test
    public void testExecuteEffect_withZeroTurns_noDecrease() {
        Card normalCard = new AttackCard();
        
        mockContext.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.drawBottomCard()).andReturn(normalCard);
        mockContext.addToCurrentPlayer(normalCard);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        EasyMock.expect(mockPlayer.getNumberOfTurns()).andReturn(0);
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Draw From The Bottom");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck, mockPlayer);
        
        drawFromTheBottomCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck, mockPlayer);
    }
}