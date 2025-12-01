package domain.player;

import domain.cards.Card;
import domain.cards.implementations.AttackCard;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.DefuseCard;
import domain.cards.implementations.FeralCatCard;
import domain.cards.implementations.NopeCard;
import domain.cards.implementations.SkipCard;
import domain.cards.implementations.StreakingKittenCard;
import domain.game.Action;
import domain.game.GameEngine;
import ui.GameUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerControllerHumanTests {
    
    @Test
    public void testIsCardPlayable_DefuseCard_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        DefuseCard defuseCard = new DefuseCard();
        hand.add(defuseCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, defuseCard, hand);
        
        assertFalse(result, "Defuse card should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_NopeCard_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        NopeCard nopeCard = new NopeCard();
        hand.add(nopeCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, nopeCard, hand);
        
        assertFalse(result, "Nope card should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_StreakingKitten_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        StreakingKittenCard streakingKitten = new StreakingKittenCard();
        hand.add(streakingKitten);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, streakingKitten, hand);
        
        assertFalse(result, "Streaking Kitten should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_CatCardWithPair_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        CatCard catCard1 = new CatCard(CatCard.CatCardType.TACO_CAT);
        CatCard catCard2 = new CatCard(CatCard.CatCardType.TACO_CAT);
        hand.add(catCard1);
        hand.add(catCard2);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, catCard1, hand);
        
        assertTrue(result, "Cat card with pair should be playable");
    }
    
    @Test
    public void testIsCardPlayable_CatCardNoPair_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        CatCard catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        hand.add(catCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, catCard, hand);
        
        assertFalse(result, "Cat card without pair should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_FeralCatWithTwoFeralCats_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        FeralCatCard feralCat1 = new FeralCatCard();
        FeralCatCard feralCat2 = new FeralCatCard();
        hand.add(feralCat1);
        hand.add(feralCat2);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, feralCat1, hand);
        
        assertTrue(result, "Feral cat with pair should be playable");
    }
    
    @Test
    public void testIsCardPlayable_FeralCatWithCatCard_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        hand.add(feralCat);
        hand.add(catCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, feralCat, hand);
        
        assertTrue(result, "Feral cat with cat card should be playable");
    }
    
    @Test
    public void testIsCardPlayable_FeralCatAlone_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        FeralCatCard feralCat = new FeralCatCard();
        hand.add(feralCat);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, feralCat, hand);
        
        assertFalse(result, "Feral cat alone should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_FeralCatWithNonCatCard_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        FeralCatCard feralCat = new FeralCatCard();
        SkipCard skipCard = new SkipCard();
        hand.add(feralCat);
        hand.add(skipCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, feralCat, hand);
        
        assertFalse(result, "Feral cat with only non-cat cards should not be playable");
    }
    
    @Test
    public void testIsCardPlayable_RegularCard_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        SkipCard skipCard = new SkipCard();
        hand.add(skipCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, skipCard, hand);
        
        assertTrue(result, "Skip card should be playable");
    }
    
    @Test
    public void testIsCardPlayable_AttackCard_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        AttackCard attackCard = new AttackCard();
        hand.add(attackCard);
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "isCardPlayable", Card.class, List.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, attackCard, hand);
        
        assertTrue(result, "Attack card should be playable");
    }
        
    @Test
    public void testHasTwoOfName_WithZeroMatches_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new SkipCard());
        hand.add(new AttackCard());
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasTwoOfName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertFalse(result, "Should return false with zero matches");
    }
    
    @Test
    public void testHasTwoOfName_WithOneMatch_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        hand.add(new SkipCard());
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasTwoOfName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertFalse(result, "Should return false with only one match");
    }
    
    @Test
    public void testHasTwoOfName_WithTwoMatches_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasTwoOfName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertTrue(result, "Should return true with two matches");
    }
    
    @Test
    public void testHasTwoOfName_WithThreeMatches_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasTwoOfName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertTrue(result, "Should return true with three matches");
    }
    
    @Test
    public void testHasTwoOfName_EmptyHand_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasTwoOfName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertFalse(result, "Should return false with empty hand");
    }
    
    @Test
    public void testHasCardWithName_WithMatch_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasCardWithName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertTrue(result, "Should return true when card with name exists");
    }
    
    @Test
    public void testHasCardWithName_WithNoMatch_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new SkipCard());
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasCardWithName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertFalse(result, "Should return false when card with name does not exist");
    }
    
    @Test
    public void testHasCardWithName_EmptyHand_returnsFalse() throws Exception {
        List<Card> hand = new ArrayList<>();
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasCardWithName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertFalse(result, "Should return false with empty hand");
    }
    
    @Test
    public void testHasCardWithName_MatchAtEnd_returnsTrue() throws Exception {
        List<Card> hand = new ArrayList<>();
        hand.add(new SkipCard());
        hand.add(new AttackCard());
        hand.add(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        java.lang.reflect.Method method = PlayerControllerHuman.class.getDeclaredMethod(
            "hasCardWithName", List.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(controller, hand, "Taco Cat");
        
        assertTrue(result, "Should return true when card is at end of list");
    }
    
    @Test
    public void testChooseIndex_invalidThenValid_displaysErrorMessages() {
        EasyMock.reset(mockUI, mockEngine);
        
        mockUI = EasyMock.createMock(GameUI.class);
        controller = new PlayerControllerHuman(mockUI);
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(-1).once();
        mockUI.displayMessage("indexOutOfBounds");
        EasyMock.expectLastCall().once();
        mockUI.displayMessage("tryAgain");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(3).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 5);
        
        assertEquals(3, result);
        EasyMock.verify(mockEngine, mockUI);
    }
    
    @Test
    public void testChooseIndex_indexAboveMax_displaysErrorMessages() {
        EasyMock.reset(mockUI, mockEngine);
        
        mockUI = EasyMock.createMock(GameUI.class);
        controller = new PlayerControllerHuman(mockUI);
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(10).once();
        mockUI.displayMessage("indexOutOfBounds");
        EasyMock.expectLastCall().once();
        mockUI.displayMessage("tryAgain");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(3).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 5);
        
        assertEquals(3, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    private PlayerControllerHuman controller;
    private GameUI mockUI;
    private GameEngine mockEngine;

    @BeforeEach
    public void setUp() {
        mockUI = EasyMock.createNiceMock(GameUI.class);
        controller = new PlayerControllerHuman(mockUI);
        mockEngine = EasyMock.createNiceMock(GameEngine.class);
    }

    @Test
    public void testGetNopeAction_userChoosesYes_returnsNopeAction() {
        EasyMock.expect(mockUI.promptPlayer("chooseNope")).andReturn(1);
        EasyMock.replay(mockUI, mockEngine);
        
        Action result = controller.getNopeAction(mockEngine);
        
        assertEquals(Action.Type.NOPE, result.getType());
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetNopeAction_userChoosesNo_returnsPassAction() {
        EasyMock.expect(mockUI.promptPlayer("chooseNope")).andReturn(0);
        EasyMock.replay(mockUI, mockEngine);
        
        Action result = controller.getNopeAction(mockEngine);
        
        assertEquals(Action.Type.PASS, result.getType());
        EasyMock.verify(mockUI);
    }

    @Test
    public void testChooseIndex_validIndex_returnsIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(5);
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 10);
        
        assertEquals(5, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseIndex_invalidThenValid_retriesUntilValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(-1).once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(5).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 10);
        
        assertEquals(5, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseIndex_edgeCase_zeroIsValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(0);
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 10);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseIndex_edgeCase_maxIsValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(10);
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 10);
        
        assertEquals(10, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseTargetPlayer_validTarget_returnsTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(2);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseTargetPlayer(mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseTargetPlayer_chooseSelf_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(1).once();
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(2).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseTargetPlayer(mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseTargetPlayer_invalidPlayer_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(99).once();
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(2).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseTargetPlayer(mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseCardIndex_validIndex_returnsIndex() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(1);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseCardIndex(mockEngine);
        
        assertEquals(1, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseCardIndex_invalidIndex_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(5).once();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseCardIndex(mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_targetPlayerId_returnsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockUI.promptPlayer("targetPlayerId")).andReturn(2);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "targetPlayerId");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_playerID_returnsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockUI.promptPlayer("playerID")).andReturn(2);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "playerID");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_whereToInsert_returnsValidIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(5);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "whereToInsert");
        
        assertEquals(5, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_chooseCardPrompt_returnsValidCardIndex() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "chooseCardPrompt");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_defaultPrompt_returnsUIResponse() {
        EasyMock.expect(mockUI.promptPlayer("customPrompt")).andReturn(42);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "customPrompt");
        
        assertEquals(42, result);
        EasyMock.verify(mockUI);
    }

    @Test
    public void testGetTurnAction_userPlaysCard_returnsPlayCardAction() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new SkipCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        assertEquals(0, result.getCardIndex());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_userPasses_returnsPassAction() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new SkipCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PASS, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_defuseCard_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new DefuseCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_nopeCard_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new NopeCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_streakingKittenCard_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new StreakingKittenCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_catCardWithPair_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.TACO_CAT));
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_catCardWithoutPair_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.TACO_CAT));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_feralCatWithTwoFeralCats_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_feralCatWithCatCard_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.BEARDED_CAT));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_feralCatAlone_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new SkipCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_regularCard_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new AttackCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        controller.getTurnAction(mockEngine);
        
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_emptyHand_returnsPassAction() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PASS, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_playerIDCurse_returnsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockUI.promptPlayer("playerIDCurse")).andReturn(2);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "playerIDCurse");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_takeCard_returnsValidCardIndex() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("takeCard")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "takeCard");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_chosenPlayerCardIndex_returnsValidCardIndex() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("chosenPlayerCardIndex")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "chosenPlayerCardIndex");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_targetCardIndex_returnsValidCardIndex() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("targetCardIndex")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "targetCardIndex");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_whereToInsert_invalidThenValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(-1).once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(3).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "whereToInsert");
        
        assertEquals(3, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_cardPrompt_invalidThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(10).once();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(1).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "chooseCardPrompt");
        
        assertEquals(1, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_targetPlayer_selfThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("playerID")).andReturn(1).once();
        EasyMock.expect(mockUI.promptPlayer("playerID")).andReturn(2).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "playerID");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_targetPlayer_invalidPlayerThenValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("playerID")).andReturn(99).once();
        EasyMock.expect(mockUI.promptPlayer("playerID")).andReturn(2).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "playerID");
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseIndex_indexAboveMax_retriesUntilValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(10).once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(3).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseIndex(mockEngine, 5);
        
        assertEquals(3, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testChooseCardIndex_negativeIndex_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(-1).once();
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.chooseCardIndex(mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_whereToInsert_indexAboveMax_retriesUntilValid() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(5).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(10).once();
        EasyMock.expect(mockUI.promptPlayer("whereToInsert")).andReturn(3).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "whereToInsert");
        
        assertEquals(3, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_cardPrompt_indexAboveHandSize_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("takeCard")).andReturn(5).once();
        EasyMock.expect(mockUI.promptPlayer("takeCard")).andReturn(0).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "takeCard");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetDecision_cardPrompt_negativeIndex_retriesUntilValid() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.getHandObject().addCard(EasyMock.createNiceMock(Card.class));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager).anyTimes();
        EasyMock.expect(mockUI.promptPlayer("targetCardIndex")).andReturn(-1).once();
        EasyMock.expect(mockUI.promptPlayer("targetCardIndex")).andReturn(0).once();
        
        EasyMock.replay(mockEngine, mockUI);
        
        int result = controller.getDecision(mockEngine, "targetCardIndex");
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_defuseOnly_cardShownAsNotPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new DefuseCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_nopeOnly_cardShownAsNotPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new NopeCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_streakingKittenOnly_cardShownAsNotPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new StreakingKittenCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_singleCatCard_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.CATTERMELON));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PASS, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_twoCatCardsOfSameType_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.CATTERMELON));
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.CATTERMELON));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_feralCatWithRegularCatCard_playable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.RAINBOW_RALPHING_CAT));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_twoFeralCats_canPlayTogether() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_feralCatWithNoOtherCatCards_notPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new FeralCatCard());
        currentPlayer.getHandObject().addCard(new DefuseCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PASS, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_regularCardIsPlayable() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new SkipCard());
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(0);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testGetTurnAction_multipleCards_correctPlayabilityMarked() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        currentPlayer.setHandVisibility(true);
        currentPlayer.getHandObject().addCard(new DefuseCard());
        currentPlayer.getHandObject().addCard(new SkipCard());
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT));
        currentPlayer.getHandObject().addCard(new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT));
        
        PlayerManager realPlayerManager = new PlayerManager();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(mockEngine.getPlayerManager()).andReturn(realPlayerManager);
        EasyMock.expect(mockUI.promptPlayer("playCardPrompt")).andReturn(1);
        EasyMock.expect(mockUI.promptPlayer("chooseCardPrompt")).andReturn(1);
        
        EasyMock.replay(mockEngine, mockUI);
        
        Action result = controller.getTurnAction(mockEngine);
        
        assertEquals(Action.Type.PLAY_CARD, result.getType());
        assertEquals(1, result.getCardIndex());
        EasyMock.verify(mockEngine, mockUI);
    }

    @Test
    public void testWrapper_displayFormattedMessage_delegatesToRealUI() throws Exception {
        GameUI realUI = EasyMock.createMock(GameUI.class);
        realUI.displayFormattedMessage("testKey", "arg1", 42);
        EasyMock.expectLastCall();
        EasyMock.replay(realUI);
        
        PlayerControllerHuman humanController = new PlayerControllerHuman(realUI);
        
        Field userInterfaceField = PlayerControllerHuman.class.getDeclaredField("userInterface");
        userInterfaceField.setAccessible(true);
        GameUI wrapper = (GameUI) userInterfaceField.get(humanController);
        
        wrapper.displayFormattedMessage("testKey", "arg1", 42);
        
        EasyMock.verify(realUI);
    }

    @Test
    public void testWrapper_clearScreen_delegatesToRealUI() throws Exception {
        GameUI realUI = EasyMock.createMock(GameUI.class);
        realUI.clearScreen();
        EasyMock.expectLastCall();
        EasyMock.replay(realUI);
        
        PlayerControllerHuman humanController = new PlayerControllerHuman(realUI);
        
        Field userInterfaceField = PlayerControllerHuman.class.getDeclaredField("userInterface");
        userInterfaceField.setAccessible(true);
        GameUI wrapper = (GameUI) userInterfaceField.get(humanController);
        
        wrapper.clearScreen();
        
        EasyMock.verify(realUI);
    }

    @Test
    public void testWrapper_promptExpansionPackNumbers_delegatesToRealUI() throws Exception {
        GameUI realUI = EasyMock.createMock(GameUI.class);
        Set<Integer> expectedResult = new HashSet<>(Arrays.asList(1, 2, 3));
        EasyMock.expect(realUI.promptExpansionPackNumbers()).andReturn(expectedResult);
        EasyMock.replay(realUI);
        
        PlayerControllerHuman humanController = new PlayerControllerHuman(realUI);
        
        Field userInterfaceField = PlayerControllerHuman.class.getDeclaredField("userInterface");
        userInterfaceField.setAccessible(true);
        GameUI wrapper = (GameUI) userInterfaceField.get(humanController);
        
        Set<Integer> result = wrapper.promptExpansionPackNumbers();
        
        assertEquals(expectedResult, result);
        EasyMock.verify(realUI);
    }

    @Test
    public void testWrapper_changeLanguage_delegatesToRealUI() throws Exception {
        GameUI realUI = EasyMock.createMock(GameUI.class);
        realUI.changeLanguage("en", "US");
        EasyMock.expectLastCall();
        EasyMock.replay(realUI);
        
        PlayerControllerHuman humanController = new PlayerControllerHuman(realUI);
        
        Field userInterfaceField = PlayerControllerHuman.class.getDeclaredField("userInterface");
        userInterfaceField.setAccessible(true);
        GameUI wrapper = (GameUI) userInterfaceField.get(humanController);
        
        wrapper.changeLanguage("en", "US");
        
        EasyMock.verify(realUI);
    }
}