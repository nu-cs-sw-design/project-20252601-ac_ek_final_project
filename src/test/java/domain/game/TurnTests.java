package domain.game;

import domain.cards.Card;
import domain.cards.implementations.AttackCard;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.DefuseCard;
import domain.cards.implementations.FeralCatCard;
import domain.player.Player;
import domain.player.PlayerController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTests {

    private Turn turn;
    private GameEngine mockEngine;
    private NopeOperation mockNopeOperation;
    private PlayerController mockController;

    @BeforeEach
    public void setUp() {
        mockEngine = EasyMock.createMock(GameEngine.class);
        mockNopeOperation = EasyMock.createMock(NopeOperation.class);
        mockController = EasyMock.createMock(PlayerController.class);
        turn = new Turn(mockEngine, mockNopeOperation);
    }

    @Test
    public void testExecuteTurn_emptyHand_drawsCard() {
        Player player = new Player(1, new ArrayList<>());
        player.setController(mockController);
        player.setNumberOfTurns(1);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_playerPassesImmediately() {
        Card attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(true);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        mockEngine.notifyMessage("currentHand");
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("visibleCard", 0, "Attack");
        EasyMock.expectLastCall();
        EasyMock.expect(mockController.getTurnAction(mockEngine)).andReturn(Action.pass());
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine, mockController);
    }

    @Test
    public void testExecuteTurn_deckEmpty_skipsDrawing() {
        Player player = new Player(1, new ArrayList<>());
        player.setController(mockController);
        player.setNumberOfTurns(1);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(0).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 0);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(true);
        mockEngine.notifyMessage("deckEmpty");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_onePlayerLeft_setsGameOver() {
        Player player = new Player(1, new ArrayList<>());
        player.setController(mockController);
        player.setNumberOfTurns(1);
        
        List<Player> players = Arrays.asList(player);
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(0).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 0);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(true);
        mockEngine.notifyMessage("deckEmpty");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(1).anyTimes();
        mockEngine.setGameOver(true);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_displayMarkedCards() {
        Player player = new Player(1, new ArrayList<>());
        player.setController(mockController);
        player.setNumberOfTurns(1);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card markedCard = new AttackCard();
        Map<Integer, List<Card>> visibleCards = new HashMap<>();
        visibleCards.put(2, Arrays.asList(markedCard));
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(visibleCards);
        mockEngine.notifyMessage("displayMarkedCards");
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerIdMessage", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("cardDisplay", "-", "Attack");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(true);
        mockEngine.notifyMessage("deckEmpty");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_hiddenHand() {
        Card attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(false);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        mockEngine.notifyMessage("currentHand");
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("hiddenCard", 0);
        EasyMock.expectLastCall();
        EasyMock.expect(mockController.getTurnAction(mockEngine)).andReturn(Action.pass());
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine, mockController);
    }

    @Test
    public void testExecuteTurn_catCardNoped_removesBothCatCards() {
        CatCard catCard1 = new CatCard(CatCard.CatCardType.TACO_CAT);
        CatCard catCard2 = new CatCard(CatCard.CatCardType.TACO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(catCard1);
        hand.add(catCard2);
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(true);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        mockEngine.notifyMessage("currentHand");
        EasyMock.expectLastCall().anyTimes();
        mockEngine.notifyFormattedMessage("visibleCard", 0, "Taco Cat");
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("visibleCard", 1, "Taco Cat");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockController.getTurnAction(mockEngine)).andReturn(Action.playCard(0));
        
        EasyMock.expect(mockNopeOperation.checkForNopeInterruption(EasyMock.eq(mockEngine), EasyMock.anyObject(Card.class)))
                .andReturn(true);
        
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        assertTrue(player.getHand().isEmpty());
        
        EasyMock.verify(mockEngine, mockNopeOperation, mockController);
    }

    @Test
    public void testExecuteTurn_feralCatCardNoped_removesBothCards() {
        FeralCatCard feralCatCard = new FeralCatCard();
        CatCard catCard = new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(feralCatCard);
        hand.add(catCard);
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(true);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        mockEngine.notifyMessage("currentHand");
        EasyMock.expectLastCall().anyTimes();
        mockEngine.notifyFormattedMessage("visibleCard", 0, "Feral Cat");
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("visibleCard", 1, "Hairy Potato Cat");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockController.getTurnAction(mockEngine)).andReturn(Action.playCard(0));
        
        EasyMock.expect(mockNopeOperation.checkForNopeInterruption(EasyMock.eq(mockEngine), EasyMock.anyObject(Card.class)))
                .andReturn(true);
        
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        assertTrue(player.getHand().isEmpty());
        
        EasyMock.verify(mockEngine, mockNopeOperation, mockController);
    }

    @Test
    public void testExecuteTurn_invalidCardIndex_notifiesAndContinues() {
        ArrayList<Card> hand = new ArrayList<>();
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(true);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_boundaryCondition_zeroTurns() {
        Card attackCard = new AttackCard();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(attackCard);
        Player player = new Player(1, hand);
        player.setController(mockController);
        player.setNumberOfTurns(0);
        player.setHandVisibility(true);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        EasyMock.verify(mockEngine);
    }

    @Test
    public void testExecuteTurn_resetPlayerState_calledDuringFinalize() {
        Player player = new Player(1, new ArrayList<>());
        player.setController(mockController);
        player.setNumberOfTurns(1);
        player.setHandVisibility(false);
        
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player, player2);
        
        Card drawnCard = new DefuseCard();
        Map<Integer, List<Card>> emptyVisibleCards = new HashMap<>();
        
        mockEngine.notifyClearScreen();
        EasyMock.expectLastCall();
        
        mockEngine.notifyMessage("activePlayers");
        EasyMock.expectLastCall();
        EasyMock.expect(mockEngine.getPlayers()).andReturn(players).anyTimes();
        mockEngine.notifyFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("separator");
        EasyMock.expectLastCall().times(2);
        mockEngine.notifyFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getDeckSize()).andReturn(10).anyTimes();
        mockEngine.notifyFormattedMessage("deckSize", 10);
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getCurrentPlayer()).andReturn(player).anyTimes();
        mockEngine.notifyFormattedMessage("player", 1);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("turnStart");
        EasyMock.expectLastCall();
        
        EasyMock.expect(mockEngine.getVisibleCards()).andReturn(emptyVisibleCards);
        
        EasyMock.expect(mockEngine.isDeckEmpty()).andReturn(false);
        EasyMock.expect(mockEngine.drawTopCard()).andReturn(drawnCard);
        mockEngine.addToCurrentPlayer(drawnCard);
        EasyMock.expectLastCall();
        mockEngine.notifyMessage("drawCard");
        EasyMock.expectLastCall();
        mockEngine.setPlayer(EasyMock.anyObject(Player.class));
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.expect(mockEngine.getNumberOfPlayers()).andReturn(2).anyTimes();
        mockEngine.notifyFormattedMessage("endTurn", 1);
        EasyMock.expectLastCall();
        mockEngine.nextPlayer();
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockEngine, mockNopeOperation, mockController);
        
        turn.executeTurn();
        
        assertTrue(player.getHandVisibility());
        
        EasyMock.verify(mockEngine);
    }
}