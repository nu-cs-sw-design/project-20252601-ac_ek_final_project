package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class DefuseCardTests {
    private DefuseCard defuseCard;
    private GameContext mockContext;
    private CardManager mockCardManager;

    @BeforeEach
    public void setUp() {
        defuseCard = new DefuseCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
    }

    @Test
    public void testGetName_returnsDefuse() {
        assertEquals("Defuse", defuseCard.getName());
    }

    @Test
    public void testExecuteEffect_insertsExplodingKittenAndRemovesDefuse() {
        mockContext.displayMessage("defuseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Defuse");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        defuseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}