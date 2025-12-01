package domain.player;

import domain.cards.Card;
import domain.game.Action;
import domain.game.GameEngine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerControllerAITests {

    private PlayerControllerAI controller;
    private GameEngine mockEngine;

    @BeforeEach
    public void setUp() {
        controller = new PlayerControllerAI();
        mockEngine = EasyMock.createMock(GameEngine.class);
    }

    @Test
    public void testGetTurnAction_noPlayableCards_returnsPass() {
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getName()).andReturn("Defuse").anyTimes();
        
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(mockCard));
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        
        EasyMock.replay(mockEngine, mockCard);
        
        Action result = controller.getTurnAction(mockEngine);
        assertEquals(Action.Type.PASS, result.getType());
        
        EasyMock.verify(mockEngine, mockCard);
    }

    @Test
    public void testGetTurnAction_withPlayableCards_returnsActionType() {
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getName()).andReturn("Attack").anyTimes();
        
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(mockCard));
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        
        EasyMock.replay(mockEngine, mockCard);
        
        boolean foundPass = false;
        boolean foundPlayCard = false;
        for (int i = 0; i < 100 && !(foundPass && foundPlayCard); i++) {
            controller = new PlayerControllerAI();
            EasyMock.reset(mockEngine, mockCard);
            EasyMock.expect(mockCard.getName()).andReturn("Attack").anyTimes();
            EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
            EasyMock.replay(mockEngine, mockCard);
            
            Action result = controller.getTurnAction(mockEngine);
            if (result.getType() == Action.Type.PASS) {
                foundPass = true;
            } else if (result.getType() == Action.Type.PLAY_CARD) {
                foundPlayCard = true;
            }
        }
        
        assertTrue(foundPass || foundPlayCard);
    }

    @Test
    public void testGetNopeAction_randomDecision() {
        EasyMock.replay(mockEngine);
        
        boolean foundPass = false;
        boolean foundNope = false;
        for (int i = 0; i < 100 && !(foundPass && foundNope); i++) {
            controller = new PlayerControllerAI();
            Action result = controller.getNopeAction(mockEngine);
            if (result.getType() == Action.Type.PASS) {
                foundPass = true;
            } else if (result.getType() == Action.Type.NOPE) {
                foundNope = true;
            }
        }
        
        assertTrue(foundPass || foundNope);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testChooseIndex_returnsValidIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.replay(mockEngine);
        
        int result = controller.chooseIndex(mockEngine, 10);
        
        assertTrue(result >= 0 && result <= 10);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testChooseIndex_emptyDeck_returnsZero() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(0);
        EasyMock.replay(mockEngine);
        
        int result = controller.chooseIndex(mockEngine, 0);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testChooseTargetPlayer_multipleValidTargets() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(EasyMock.createMock(Card.class));
        Player player3 = new Player(3, new ArrayList<>());
        player3.getHandObject().addCard(EasyMock.createMock(Card.class));
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = controller.chooseTargetPlayer(mockEngine);
        
        assertNotEquals(1, result);
        assertTrue(result == 2 || result == 3);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testChooseTargetPlayer_onlyOneOtherPlayer() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(EasyMock.createMock(Card.class));
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = controller.chooseTargetPlayer(mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testChooseCardIndex_selectsFromPlayableCards() {
        Card attackCard = EasyMock.createMock(Card.class);
        EasyMock.expect(attackCard.getName()).andReturn("Attack").anyTimes();
        
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine, attackCard);
        
        int result = controller.chooseCardIndex(mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, attackCard);
    }
    
    @Test
    public void testGetDecision_whereToInsert_returnsValidIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5);
        EasyMock.replay(mockEngine);
        
        int result = controller.getDecision(mockEngine, "whereToInsert");
        
        assertTrue(result >= 0 && result <= 5);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_targetPlayerId_returnsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(EasyMock.createMock(Card.class));
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = controller.getDecision(mockEngine, "targetPlayerId");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_chooseCardPrompt_returnsCardIndex() {
        Card attackCard = EasyMock.createMock(Card.class);
        EasyMock.expect(attackCard.getName()).andReturn("Attack").anyTimes();
        
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(attackCard));
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine, attackCard);
        
        int result = controller.getDecision(mockEngine, "chooseCardPrompt");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, attackCard);
    }
}