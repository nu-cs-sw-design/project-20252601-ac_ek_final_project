package domain.player;

import domain.cards.Card;
import domain.cards.implementations.SkipCard;
import domain.deck.Deck;
import domain.game.Game;
import domain.game.GameConfiguration;
import ui.GameUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerManagerTests {
    private PlayerManager playerManager;
    private Game mockGame;

    @BeforeEach
    public void setUp() {
        playerManager = new PlayerManager();
        mockGame = EasyMock.createMock(Game.class);
    }

    @Test
    public void testGetCurrentPlayer_emptyPlayers_throwsException() {
        EasyMock.expect(mockGame.getPlayers()).andReturn(new ArrayList<>());
        EasyMock.replay(mockGame);

        assertThrows(NoSuchElementException.class, () -> playerManager.getCurrentPlayer(mockGame));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetCurrentPlayer_returnsFirstPlayer() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player1, player2);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        Player result = playerManager.getCurrentPlayer(mockGame);
        assertEquals(1, result.getId());
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetCurrentPlayer_updatesFirstPlayer() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        Player newPlayer = new Player(1, new ArrayList<>());
        newPlayer.setNumberOfTurns(5);

        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setCurrentPlayer(mockGame, newPlayer);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetCurrentPlayer_emptyPlayers_noChange() {
        EasyMock.expect(mockGame.getPlayers()).andReturn(new ArrayList<>());
        EasyMock.replay(mockGame);

        Player newPlayer = new Player(1, new ArrayList<>());
        playerManager.setCurrentPlayer(mockGame, newPlayer);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetPlayer_playerExists_returnsPlayer() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player1, player2);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        Player result = playerManager.getPlayer(mockGame, 2);
        assertEquals(2, result.getId());
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetPlayer_playerNotExists_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = Arrays.asList(player1);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(NoSuchElementException.class, () -> playerManager.getPlayer(mockGame, 5));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetPlayers_returnsPlayersList() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = Arrays.asList(player1);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        List<Player> result = playerManager.getPlayers(mockGame);
        assertEquals(1, result.size());
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetPlayers_setsNewPlayersList() {
        List<Player> newPlayers = Arrays.asList(new Player(1, new ArrayList<>()));
        
        mockGame.setPlayers(newPlayers);
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setPlayers(mockGame, newPlayers);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testNextPlayer_rotatesPlayersLeft() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.nextPlayer(mockGame);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetPlayerCount_returnsSizeOfPlayers() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player1, player2);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertEquals(2, playerManager.getPlayerCount(mockGame));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testEliminatePlayer_emptyPlayers_throwsException() {
        EasyMock.expect(mockGame.getPlayers()).andReturn(new ArrayList<>());
        EasyMock.replay(mockGame);

        assertThrows(NoSuchElementException.class, () -> playerManager.eliminatePlayer(mockGame, 1));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testEliminatePlayer_onlyOnePlayer_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(IllegalStateException.class, () -> playerManager.eliminatePlayer(mockGame, 1));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testEliminatePlayer_playerNotExists_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(NoSuchElementException.class, () -> playerManager.eliminatePlayer(mockGame, 5));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testEliminatePlayer_validPlayer_removesFromList() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.eliminatePlayer(mockGame, 1);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testUpdatePlayer_playerExists_updatesPlayer() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        Player updatedPlayer = new Player(1, new ArrayList<>());
        updatedPlayer.setNumberOfTurns(5);

        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.updatePlayer(mockGame, updatedPlayer);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testUpdatePlayer_playerNotExists_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        Player updatedPlayer = new Player(5, new ArrayList<>());
        assertThrows(NoSuchElementException.class, () -> playerManager.updatePlayer(mockGame, updatedPlayer));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetPlayerTurns_validPlayer_setsTurns() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setPlayerTurns(mockGame, 1, 5);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetPlayerTurns_invalidPlayer_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(IndexOutOfBoundsException.class, () -> playerManager.setPlayerTurns(mockGame, 5, 3));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetNextPlayerId_moreThanOnePlayer_returnsSecondPlayerId() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = Arrays.asList(player1, player2);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertEquals(2, playerManager.getNextPlayerId(mockGame));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetNextPlayerId_onlyOnePlayer_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        List<Player> players = Arrays.asList(player1);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(NoSuchElementException.class, () -> playerManager.getNextPlayerId(mockGame));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetNextPlayerTurns_setsNextPlayerTurns() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(3);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setNextPlayerTurns(mockGame, 4);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetNextPlayerTargetPlayer_targetIsSelf_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(UnsupportedOperationException.class, 
            () -> playerManager.setNextPlayerTargetPlayer(mockGame, player1));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetNextPlayerTargetPlayer_targetNotInList_throwsException() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        
        Player invalidTarget = new Player(5, new ArrayList<>());

        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertThrows(UnsupportedOperationException.class, 
            () -> playerManager.setNextPlayerTargetPlayer(mockGame, invalidTarget));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetNextPlayerTargetPlayer_validTarget_reordersPlayers() {
        Player player1 = new Player(1, new ArrayList<>());
        Player player2 = new Player(2, new ArrayList<>());
        Player player3 = new Player(3, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2, player3));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setNextPlayerTargetPlayer(mockGame, player3);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testAddCard_addsCardToPlayer() {
        Player player = new Player(1, new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        
        int result = playerManager.addCard(player, card);
        assertEquals(1, result);
        assertEquals(1, player.getHand().size());
    }

    @Test
    public void testAddVisibleCard_addsToVisibleCards() {
        Player player = new Player(1, new ArrayList<>());
        Card card = EasyMock.createMock(Card.class);
        
        int result = playerManager.addVisibleCard(player, card);
        assertEquals(1, result);
    }

    @Test
    public void testRemoveCard_removesCardFromPlayer() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        int result = playerManager.removeCard(player, 0);
        assertEquals(0, result);
    }

    @Test
    public void testChooseCard_returnsCardAtIndex() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        Card result = playerManager.chooseCard(player, 0);
        assertEquals(card, result);
    }

    @Test
    public void testHasCard_cardExists_returnsIndex() {
        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        assertEquals(0, playerManager.hasCard(player, "Attack"));
        EasyMock.verify(card);
    }

    @Test
    public void testHasCard_cardNotExists_returnsNegative1() {
        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        assertEquals(-1, playerManager.hasCard(player, "Defuse"));
        EasyMock.verify(card);
    }

    @Test
    public void testHasTwoOf_hasTwoCards_returnsTrue() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getName()).andReturn("Attack").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Attack").anyTimes();
        EasyMock.replay(card1, card2);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player(1, hand);
        
        assertTrue(playerManager.hasTwoOf(player, "Attack"));
        EasyMock.verify(card1, card2);
    }

    @Test
    public void testShuffleHand_shufflesPlayerHand() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(EasyMock.createMock(Card.class));
        }
        Player player = new Player(1, new ArrayList<>(cards));
        
        playerManager.shuffleHand(player);
        assertEquals(10, player.getHand().size());
    }

    @Test
    public void testIsHandEmpty_emptyHand_returnsTrue() {
        Player player = new Player(1, new ArrayList<>());
        assertTrue(playerManager.isHandEmpty(player));
    }

    @Test
    public void testIsHandEmpty_nonEmptyHand_returnsFalse() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        
        assertFalse(playerManager.isHandEmpty(player));
    }

    @Test
    public void testGetHandCount_returnsHandSize() {
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));
        Player player = new Player(1, hand);
        
        assertEquals(2, playerManager.getHandCount(player));
    }

    @Test
    public void testDecreaseTurnByOne_decreasesTurn() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(3);
        
        playerManager.decreaseTurnByOne(player);
        assertEquals(2, player.getNumberOfTurns());
    }

    @Test
    public void testDecreaseTurnByOne_zeroTurns_throwsException() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(0);
        
        assertThrows(UnsupportedOperationException.class, () -> playerManager.decreaseTurnByOne(player));
    }

    @Test
    public void testIsTurnOver_zeroTurns_returnsTrue() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(0);
        
        assertTrue(playerManager.isTurnOver(player));
    }

    @Test
    public void testIsTurnOver_nonZeroTurns_returnsFalse() {
        Player player = new Player(1, new ArrayList<>());
        player.setNumberOfTurns(1);
        
        assertFalse(playerManager.isTurnOver(player));
    }

    @Test
    public void testGetHand_visibilityTrue_returnsHand() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        player.setHandVisibility(true);
        
        assertEquals(1, playerManager.getHand(player).size());
    }

    @Test
    public void testGetHand_visibilityFalse_returnsEmptyList() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        player.setHandVisibility(false);
        
        assertEquals(0, playerManager.getHand(player).size());
    }

    @Test
    public void testGetVisibleHand_returnsVisibleCards() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        player.getHandObject().addVisibleCard(card);
        
        assertEquals(1, playerManager.getVisibleHand(player).size());
    }

    @Test
    public void testSetCurrentPlayerTurns_setsTurns() {
        Player player = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setCurrentPlayerTurns(mockGame, 5);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testEmptyCurrentPlayerHand_emptiesHand() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.emptyCurrentPlayerHand(mockGame);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetCurrentPlayerHasNope_returnsHasNope() {
        Player player = new Player(1, new ArrayList<>());
        player.setHasNope(true);
        List<Player> players = Arrays.asList(player);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        assertTrue(playerManager.getCurrentPlayerHasNope(mockGame));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testSetCurrentPlayerHasNope_setsHasNope() {
        Player player = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.setCurrentPlayerHasNope(mockGame, true);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testAddToCurrentPlayer_addsCard() {
        Player player = new Player(1, new ArrayList<>());
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        Card card = EasyMock.createMock(Card.class);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.addToCurrentPlayer(mockGame, card);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testRemoveCurrentPlayerCard_removesCard() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players).times(2);
        mockGame.setPlayers(EasyMock.anyObject());
        EasyMock.expectLastCall();
        EasyMock.replay(mockGame);

        playerManager.removeCurrentPlayerCard(mockGame, 0);
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetVisibleCards_returnsMapOfVisibleCards() {
        Card card = EasyMock.createMock(Card.class);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card));
        Player player = new Player(1, hand);
        player.getHandObject().addVisibleCard(card);
        List<Player> players = Arrays.asList(player);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        Map<Integer, List<Card>> result = playerManager.getVisibleCards(mockGame);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(1));
        EasyMock.verify(mockGame);
    }

    @Test
    public void testGetVisibleCards_noVisibleCards_returnsEmptyMap() {
        Player player = new Player(1, new ArrayList<>());
        List<Player> players = Arrays.asList(player);
        
        EasyMock.expect(mockGame.getPlayers()).andReturn(players);
        EasyMock.replay(mockGame);

        Map<Integer, List<Card>> result = playerManager.getVisibleCards(mockGame);
        assertTrue(result.isEmpty());
        EasyMock.verify(mockGame);
    }

    @Test
    public void testInitializePlayers_createsCorrectNumberOfPlayers() {
        GameConfiguration config = new GameConfiguration(2, 1, new HashSet<>());
        GameUI mockUI = EasyMock.createMock(GameUI.class);
        EasyMock.replay(mockUI);

        Deck deck = new Deck();
        for (int i = 0; i < 15; i++) {
            deck.addCard(new SkipCard());
        }

        List<Player> players = playerManager.initializePlayers(config, deck, mockUI);

        assertEquals(3, players.size());
        assertFalse(players.get(0).isAI());
        assertFalse(players.get(1).isAI());
        assertTrue(players.get(2).isAI());
        EasyMock.verify(mockUI);
    }
}