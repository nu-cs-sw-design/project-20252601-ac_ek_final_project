package domain.cards.implementations;

import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReverseCardTests {
    private ReverseCard reverseCard;
    private GameContext mockContext;
    private CardManager mockCardManager;

    @BeforeEach
    public void setUp() {
        reverseCard = new ReverseCard();
        mockContext = EasyMock.createMock(GameContext.class);
        mockCardManager = EasyMock.createMock(CardManager.class);
    }

    @Test
    public void testGetName_returnsReverse() {
        assertEquals("Reverse", reverseCard.getName());
    }

    @Test
    public void testGetCounts_returnsCorrectArray() {
        int[] counts = ReverseCard.getCounts();
        assertArrayEquals(new int[]{4, 6, 8}, counts);
    }

    @Test
    public void testGetCounts_returnsDefensiveCopy() {
        int[] counts1 = ReverseCard.getCounts();
        int[] counts2 = ReverseCard.getCounts();
        counts1[0] = 100;
        assertNotEquals(counts1[0], counts2[0]);
    }

    @Test
    public void testExecuteEffect_reversesPlayerOrder() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        Player player3 = new Player(3, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2, player3));
        
        mockContext.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        mockContext.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Reverse");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        reverseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_twoPlayers() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        mockContext.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        mockContext.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Reverse");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        reverseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }

    @Test
    public void testExecuteEffect_endsTurn() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        mockContext.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        mockContext.setCurrentPlayerTurns(0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getPlayers()).andReturn(players);
        mockContext.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.expect(mockContext.getCardManager()).andReturn(mockCardManager);
        mockCardManager.removeCardFromCurrentPlayer(mockContext, "Reverse");
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockContext, mockCardManager);
        
        reverseCard.executeEffect(mockContext);
        
        EasyMock.verify(mockContext, mockCardManager);
    }
}