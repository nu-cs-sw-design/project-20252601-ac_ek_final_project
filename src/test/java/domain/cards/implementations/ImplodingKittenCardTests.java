package domain.cards.implementations;

import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ImplodingKittenCardTests {
    private ImplodingKittenCard implodingCardNotDrawn;
    private ImplodingKittenCard implodingCardDrawn;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private Deck mockDeck;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        implodingCardNotDrawn = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN);
        implodingCardDrawn = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.DRAWN);
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
        mockPlayer = EasyMock.createMock(Player.class);
    }

    @Test
    public void testGetName_returnsImplodingKitten() {
        assertEquals("Imploding Kitten", implodingCardNotDrawn.getName());
    }

    @Test
    public void testGetName_sameForBothStates() {
        assertEquals(implodingCardNotDrawn.getName(), implodingCardDrawn.getName());
    }

    @Test
    public void testDrawnBefore_notDrawnValue() {
        ImplodingKittenCard.DrawnBefore notDrawn = ImplodingKittenCard.DrawnBefore.NOT_DRAWN;
        assertEquals(0, notDrawn.ordinal());
    }

    @Test
    public void testDrawnBefore_drawnValue() {
        ImplodingKittenCard.DrawnBefore drawn = ImplodingKittenCard.DrawnBefore.DRAWN;
        assertEquals(1, drawn.ordinal());
    }

    @Test
    public void testExecuteEffect_notDrawn_displaysMessage() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.numberOfCards()).andReturn(10);
        mockContext.displayFormattedMessage("ImplodingKittenIndexHelp", 10);
        EasyMock.expectLastCall();
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.anyObject(ImplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck);
        
        implodingCardNotDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_notDrawn_insertsNewImplodingKitten() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.numberOfCards()).andReturn(5);
        mockContext.displayFormattedMessage("ImplodingKittenIndexHelp", 5);
        EasyMock.expectLastCall();
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.anyObject(ImplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck);
        
        implodingCardNotDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_notDrawn_showsIndexHelp() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.numberOfCards()).andReturn(15);
        mockContext.displayFormattedMessage("ImplodingKittenIndexHelp", 15);
        EasyMock.expectLastCall();
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.anyObject(ImplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck);
        
        implodingCardNotDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_drawn_displaysMessage() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceUp");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        mockCardManager.eliminatePlayer(mockContext, mockPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        implodingCardDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_drawn_eliminatesCurrentPlayer() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceUp");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(mockPlayer);
        mockCardManager.eliminatePlayer(mockContext, mockPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayer);
        
        implodingCardDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayer);
    }

    @Test
    public void testExecuteEffect_drawn_withRealPlayer() {
        Player realPlayer = new Player(1, new ArrayList<>());
        
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceUp");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(realPlayer);
        mockCardManager.eliminatePlayer(mockContext, realPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        implodingCardDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_notDrawn_emptyDeck() {
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockContext.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.numberOfCards()).andReturn(0);
        mockContext.displayFormattedMessage("ImplodingKittenIndexHelp", 0);
        EasyMock.expectLastCall();
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.anyObject(ImplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeck);
        
        implodingCardNotDrawn.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeck);
    }
}