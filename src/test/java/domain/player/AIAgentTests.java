package domain.player;

import domain.cards.Card;
import domain.cards.implementations.AttackCard;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.DefuseCard;
import domain.cards.implementations.ExplodingKittenCard;
import domain.cards.implementations.FeralCatCard;
import domain.cards.implementations.ImplodingKittenCard;
import domain.cards.implementations.NopeCard;
import domain.cards.implementations.StreakingKittenCard;
import domain.game.GameEngine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAgentTests {
    private AIAgent aiAgent;
    private GameEngine mockEngine;

    @BeforeEach
    public void setUp() {
        aiAgent = new AIAgent();
        mockEngine = EasyMock.createMock(GameEngine.class);
    }

    @Test
    public void testGetDecision_playCardPrompt_noPlayableCards_returnsZero() {
        DefuseCard defuseCard = new DefuseCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(defuseCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_playCardPrompt_withPlayableCards_returnsRandomChoice() {
        AttackCard attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        
        assertTrue(result == 0 || result == 1);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_chooseCardPrompt_selectsPlayableCard() {
        AttackCard attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_chooseCardPrompt_noPlayableCards_returnsNegative1() {
        DefuseCard defuseCard = new DefuseCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(defuseCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertEquals(-1, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_chooseNope_returnsRandomChoice() {
        EasyMock.replay(mockEngine);
        
        boolean foundZero = false;
        boolean foundOne = false;
        for (int i = 0; i < 100; i++) {
            aiAgent = new AIAgent();
            int result = aiAgent.getDecision("chooseNope", mockEngine);
            if (result == 0) foundZero = true;
            if (result == 1) foundOne = true;
            assertTrue(result == 0 || result == 1);
        }
        
        assertTrue(foundZero || foundOne);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_keepOrAddExploding_returnsRandomChoice() {
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("keepOrAddExploding", mockEngine);
        
        assertTrue(result == 0 || result == 1);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_whereToInsert_returnsValidIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("whereToInsert", mockEngine);
        
        assertTrue(result >= 0 && result <= 10);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_whereToInsert_emptyDeck_returnsZero() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(0);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("whereToInsert", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_targetPlayerId_selectsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(new AttackCard());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("targetPlayerId", mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_playerID_selectsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(new AttackCard());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playerID", mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_playerIDCurse_selectsValidTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        player2.getHandObject().addCard(new AttackCard());
        List<Player> players = Arrays.asList(currentPlayer, player2);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playerIDCurse", mockEngine);
        
        assertEquals(2, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_targetPlayerId_allTargetsEmptyHands_selectsAnyTarget() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        Player player3 = new Player(3, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer, player2, player3);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("targetPlayerId", mockEngine);
        
        assertTrue(result == 2 || result == 3);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_takeCard_returnsZero() {
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("takeCard", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_chosenPlayerCardIndex_returnsZero() {
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chosenPlayerCardIndex", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_targetCardIndex_returnsZero() {
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("targetCardIndex", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_discard_returnsZero() {
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("discard", mockEngine);
        
        assertEquals(0, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_newIndex_returnsUniqueIndices() {
        EasyMock.replay(mockEngine);
        
        int result1 = aiAgent.getDecision("newIndex:3", mockEngine);
        int result2 = aiAgent.getDecision("newIndex:3", mockEngine);
        int result3 = aiAgent.getDecision("newIndex:3", mockEngine);
        int result4 = aiAgent.getDecision("newIndex:3", mockEngine);
        
        assertEquals(0, result1);
        assertEquals(1, result2);
        assertEquals(2, result3);
        assertEquals(0, result4);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_unknownPrompt_throwsException() {
        EasyMock.replay(mockEngine);
        
        assertThrows(IllegalArgumentException.class, 
            () -> aiAgent.getDecision("unknownPromptKey", mockEngine));
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_defuseNotPlayable() {
        DefuseCard defuseCard = new DefuseCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(defuseCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_nopeNotPlayable() {
        NopeCard nopeCard = new NopeCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(nopeCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_explodingKittenNotPlayable() {
        ExplodingKittenCard explodingKitten = new ExplodingKittenCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(explodingKitten);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_catCardNeedsTwo() {
        CatCard catCard1 = new CatCard(CatCard.CatCardType.TACO_CAT);
        CatCard catCard2 = new CatCard(CatCard.CatCardType.TACO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(catCard1);
        hand.add(catCard2);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_singleCatCardNotPlayable() {
        CatCard catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithTwoFeralCats() {
        FeralCatCard feralCat1 = new FeralCatCard();
        FeralCatCard feralCat2 = new FeralCatCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat1);
        hand.add(feralCat2);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithCatCard() {
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.TACO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_singleFeralCatNotPlayable() {
        FeralCatCard feralCat = new FeralCatCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_attackCardPlayable() {
        AttackCard attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_implodingKittenNotPlayable() {
        ImplodingKittenCard implodingKitten = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(implodingKitten);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_streakingKittenNotPlayable() {
        StreakingKittenCard streakingKitten = new StreakingKittenCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(streakingKitten);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertEquals(0, result);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_targetPlayerId_onlyCurrentPlayer_returnsNegative1() {
        Player currentPlayer = new Player(1, new ArrayList<>());
        List<Player> players = Arrays.asList(currentPlayer);
        
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players);
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("targetPlayerId", mockEngine);
        
        assertEquals(-1, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithBeardedCat_playable() {
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithHairyPotatoCat_playable() {
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithRainbowRalphingCat_playable() {
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.RAINBOW_RALPHING_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_feralCatWithCattermelon_playable() {
        FeralCatCard feralCat = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.CATTERMELON);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(catCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        EasyMock.replay(mockEngine);
        
        int playResult = aiAgent.getDecision("playCardPrompt", mockEngine);
        assertTrue(playResult == 0 || playResult == 1);
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_singleFeralCatWithNonCatCards_notPlayable() {
        FeralCatCard feralCat = new FeralCatCard();
        AttackCard attack = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCat);
        hand.add(attack);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertEquals(1, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_multipleCatTypes_correctPlayability() {
        CatCard beardedCat = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        CatCard hairyPotatoCat = new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(beardedCat);
        hand.add(hairyPotatoCat);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertEquals(-1, result);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_allCatTypesPlayable() {
        CatCard beardedCat1 = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        CatCard beardedCat2 = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(beardedCat1);
        hand.add(beardedCat2);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertTrue(result >= 0);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_newIndex_cyclesAfterMax() {
        EasyMock.replay(mockEngine);
        
        assertEquals(0, aiAgent.getDecision("newIndex:2", mockEngine));
        assertEquals(1, aiAgent.getDecision("newIndex:2", mockEngine));
        assertEquals(0, aiAgent.getDecision("newIndex:2", mockEngine));
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_newIndex_singleCount_alwaysReturnsZero() {
        EasyMock.replay(mockEngine);
        
        assertEquals(0, aiAgent.getDecision("newIndex:1", mockEngine));
        assertEquals(0, aiAgent.getDecision("newIndex:1", mockEngine));
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testGetDecision_whereToInsert_largeDeck_returnsValidIndex() {
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(50);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("whereToInsert", mockEngine);
        
        assertTrue(result >= 0 && result <= 50);
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testCardPlayability_mixedHandWithPlayableCard() {
        DefuseCard defuseCard = new DefuseCard();
        AttackCard attackCard = new AttackCard();
        NopeCard nopeCard = new NopeCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(defuseCard);
        hand.add(attackCard);
        hand.add(nopeCard);
        Player player = new Player(1, hand);
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player);
        EasyMock.replay(mockEngine);
        
        int result = aiAgent.getDecision("chooseCardPrompt", mockEngine);
        
        assertEquals(1, result);
        EasyMock.verify(mockEngine);
    }
}