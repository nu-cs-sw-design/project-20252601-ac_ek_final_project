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

public class ExplodingKittenCardTests {
    private ExplodingKittenCard explodingKittenCard;
    private GameContext mockContext;
    private CardManager mockCardManager;
    private PlayerManager mockPlayerManager;

    @BeforeEach
    public void setUp() {
        explodingKittenCard = new ExplodingKittenCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
        mockPlayerManager = EasyMock.createMock(PlayerManager.class);
    }

    @Test
    public void testGetName_returnsExplodingKitten() {
        assertEquals("Exploding Kitten", explodingKittenCard.getName());
    }

    @Test
    public void testExecuteEffect_hasDefuse_defusesAndInserts() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager).anyTimes();
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(0).times(2);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(-1);
        mockContext.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();
        mockContext.displayMessage("defuseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_noDefuse_noStreakingKitten_eliminatesPlayer() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(-1);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.eliminatePlayer(mockContext, currentPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_hasStreakingKitten_noDefuse_addsToHand() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(0);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(-1);
        mockContext.addToCurrentPlayer(EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        mockContext.displayMessage("addExplodingKitten");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_hasDefuseAndStreakingKitten_choosesToAddToDeck() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager).anyTimes();
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(0).times(2);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(-1);
        EasyMock.expect(mockContext.promptPlayer("keepOrAddExploding")).andReturn(0);
        mockContext.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();
        mockContext.displayMessage("defuseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_hasDefuseAndStreakingKitten_choosesToKeep() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(0);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(-1);
        EasyMock.expect(mockContext.promptPlayer("keepOrAddExploding")).andReturn(1);
        mockContext.addToCurrentPlayer(EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        mockContext.displayMessage("addExplodingKitten");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_alreadyHasExplodingKitten_defuses() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager).anyTimes();
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(0).times(2);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(2);
        mockContext.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();
        mockContext.displayMessage("defuseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.insertCardAtValidIndex(EasyMock.eq(mockContext), EasyMock.isA(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }

    @Test
    public void testExecuteEffect_alreadyHasExplodingKitten_noDefuse_eliminates() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        
        mockContext.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockContext.getPlayerManager()).andReturn(mockPlayerManager);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Defuse")).andReturn(-1);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Streaking Kitten")).andReturn(0);
        EasyMock.expect(mockPlayerManager.hasCard(currentPlayer, "Exploding Kitten")).andReturn(1);
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.eliminatePlayer(mockContext, currentPlayer);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager, mockPlayerManager);
        
        explodingKittenCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager, mockPlayerManager);
    }
}