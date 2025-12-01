package domain.cards.implementations;

import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class CatomicBombCardTests {
    private CatomicBombCard catomicBombCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private DeckManager mockDeckManager;
    private Deck mockDeck;

    @BeforeEach
    public void setUp() {
        catomicBombCard = new CatomicBombCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockDeckManager = EasyMock.createMock(DeckManager.class);
        mockDeck = EasyMock.createMock(Deck.class);
    }

    @Test
    public void testGetName_returnsCatomicBomb() {
        assertEquals("Catomic Bomb", catomicBombCard.getName());
    }

    @Test
    public void testExecuteEffect_displaysMessage() {
        mockContext.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.moveExplodingKittensToTop(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Catomic Bomb");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        catomicBombCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_movesExplodingKittensToTop() {
        mockContext.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.moveExplodingKittensToTop(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Catomic Bomb");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        catomicBombCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_endsTurn() {
        mockContext.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.moveExplodingKittensToTop(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Catomic Bomb");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        catomicBombCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_removesCardFromPlayer() {
        mockContext.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.moveExplodingKittensToTop(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Catomic Bomb");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        catomicBombCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }

    @Test
    public void testExecuteEffect_updatesDeck() {
        mockContext.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockContext.getDeckManager()).andReturn(mockDeckManager);
        mockDeckManager.moveExplodingKittensToTop(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setDeck(mockDeck);
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Catomic Bomb");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockDeckManager, mockDeck);
        
        catomicBombCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockDeckManager, mockDeck);
    }
}