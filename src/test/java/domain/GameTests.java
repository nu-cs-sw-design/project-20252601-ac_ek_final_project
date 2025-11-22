package domain;

import domain.Cards.AttackCard;
import domain.Cards.Card;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @Test
    public void testDeletePlayer_emptyList_throwsException() {
        int numOfPlayers = 0;
        String expectMsg = "emptyPlayers";
        Game game = new Game(numOfPlayers);

        int playerId = 0;
        Exception exception = assertThrows(NoSuchElementException.class, () -> game.deletePlayer(playerId));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testDeletePlayer_listWithTwoPlayersAndPlayerNotInList_throwsException() {
        int numOfPlayers = 2;
        String expectedMsg = "invalidPlayerID";
        Game game = new Game(numOfPlayers);

        int playerId = 3;
        Exception exception = assertThrows(NoSuchElementException.class, () -> game.deletePlayer(playerId));
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testDeletePlayer_listWithTenPlayersAndHasPlayer_returnsListWithNinePlayers() {
        int numOfPlayers = 10;
        Game game = new Game(numOfPlayers);

        int playerId = 2;
        game.deletePlayer(playerId);

        int numOfPlayersAfterDelete = game.getNumberOfPlayers();
        int expectedNumOfPlayers = numOfPlayers - 1;
        assertEquals(numOfPlayersAfterDelete, expectedNumOfPlayers);
    }

    @Test
    public void testDeletePlayer_listWithOnePlayerAndHasPlayer_throwsException() {
        String expectMsg = "onePlayer";
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        int playerId = 2;
        game.deletePlayer(playerId);

        int finalPlayerId = 1;
        Exception exception = assertThrows(IllegalStateException.class, () -> game.deletePlayer(finalPlayerId));
        assertEquals(expectMsg, exception.getMessage());
    }

    @Test
    public void testNextPlayer_smallGame() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        Player firstPlayer = game.getCurrentPlayer();
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        assertEquals(playersInitial.get(currentPlayerIndex).getId(), firstPlayer.getId());
        game.nextPlayer();
        Player secondPlayer = game.getCurrentPlayer();
        List<Player> playersAfter = game.getPlayers();

        assertEquals(playersAfter.get(currentPlayerIndex).getId(), secondPlayer.getId());
        assertNotEquals(playersAfter.get(currentPlayerIndex).getId(), firstPlayer.getId());
    }

    @Test
    public void testNextPlayer_mediumGame() {
        int numOfPlayers = 5;
        Game game = new Game(numOfPlayers);
        Player firstPlayer = game.getCurrentPlayer();
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        int expectedOriginalCurrentPlayerIndexAfterNext = 4;
        assertEquals(playersInitial.get(currentPlayerIndex).getId(), firstPlayer.getId());
        game.nextPlayer();
        Player secondPlayer = game.getCurrentPlayer();
        List<Player> playersAfter = game.getPlayers();

        assertEquals(playersAfter.get(currentPlayerIndex).getId(), secondPlayer.getId());
        assertNotEquals(playersAfter.get(currentPlayerIndex).getId(), firstPlayer.getId());
        assertEquals(playersAfter.get(expectedOriginalCurrentPlayerIndexAfterNext).getId(), firstPlayer.getId());
    }

    @Test
    public void testNextPlayer_largeGame() {
        int numOfPlayers = 9;
        Game game = new Game(numOfPlayers);
        Player firstPlayer = game.getCurrentPlayer();
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        int expectedOriginalCurrentPlayerIndexAfterNext = 8;
        assertEquals(playersInitial.get(currentPlayerIndex).getId(), firstPlayer.getId());
        game.nextPlayer();
        Player secondPlayer = game.getCurrentPlayer();
        List<Player> playersAfter = game.getPlayers();

        assertEquals(playersAfter.get(currentPlayerIndex).getId(), secondPlayer.getId());
        assertNotEquals(playersAfter.get(currentPlayerIndex).getId(), firstPlayer.getId());
        assertEquals(playersAfter.get(expectedOriginalCurrentPlayerIndexAfterNext).getId(), firstPlayer.getId());
    }

    @Test
    public void testSetNextPlayerTargetPlayer_TargetLastPlayer_small(){
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        int targetPlayerIndex = 1;
        Player targetPlayer = playersInitial.get(targetPlayerIndex);

        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(targetPlayer);
        expectedPlayers.add(game.getPlayers().get(currentPlayerIndex));

        game.setNextPlayerTargetPlayer(targetPlayer);

        Player currentPlayer = game.getCurrentPlayer();
        List<Player> playersAfter = game.getPlayers();

        assertEquals(playersAfter, expectedPlayers);
        assertEquals(currentPlayer.getId(), targetPlayer.getId());
    }

    @Test
    public void testSetNextPlayerTargetPlayer_TargetFirstPlayerThrowError_medium(){
        int numOfPlayers = 5;
        Game game = new Game(numOfPlayers);
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        Player targetPlayer = playersInitial.get(currentPlayerIndex);

        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> game.setNextPlayerTargetPlayer(targetPlayer),
                "Expected exception not thrown when target player is already the current player."
        );
        assertEquals("invalidTargetPlayer", exception.getMessage());
    }

    @Test
    public void testSetNextPlayerTargetPlayer_TargetSecondPlayer_large(){
        int numOfPlayers = 9;
        Game game = new Game(numOfPlayers);
        List<Player> playersInitial = game.getPlayers();
        int currentPlayerIndex = 0;
        int targetPlayerIndex = 1;
        Player targetPlayer = playersInitial.get(targetPlayerIndex);

        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.addAll(playersInitial.subList(targetPlayerIndex, playersInitial.size()));
        expectedPlayers.addAll(playersInitial.subList(currentPlayerIndex, targetPlayerIndex));

        game.setNextPlayerTargetPlayer(targetPlayer);

        Player currentPlayer = game.getCurrentPlayer();
        List<Player> playersAfter = game.getPlayers();

        assertEquals(playersAfter, expectedPlayers);
        assertEquals(currentPlayer.getId(), targetPlayer.getId());
    }

    @Test
    public void testSetNextPlayerTargetPlayer_TargetInvalidPlayerThrowError_medium(){
        int numOfPlayers = 5;
        Game game = new Game(numOfPlayers);
        Player invalidPlayer = EasyMock.createMock(Player.class);

        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> game.setNextPlayerTargetPlayer(invalidPlayer),
                "Expected exception not thrown when target player is invalid."
        );
        assertEquals("targetPlayerOutOfRange", exception.getMessage());
    }

    @Test
    public void testPlayerHasInitialEightCards_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        for (Player player : game.getPlayers()) {
            assertEquals(game.INITIAL_CARDS_PER_PLAYER, player.getHand().size());
        }
    }

    @Test
    public void testGetCurrentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        int currentPlayerIndex = 0;
        assertEquals(game.getCurrentPlayer().getId(), game.getPlayers().get(currentPlayerIndex).getId());
    }

    @Test
    public void testGetPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        int currentPlayerIndex = 0;
        int playerID = 1;
        Player player = game.getPlayers().get(currentPlayerIndex);
        assertEquals(game.getPlayer(playerID).getId(), player.getId());
    }

    @Test
    public void testGetPlayer_throwsException_codeCoverage() {
        int numOfPlayers = 2;
        String expectedMsg = "invalidPlayerID";
        Game game = new Game(numOfPlayers);
        int invalidPlayerId = 3;
        Exception exception = assertThrows(NoSuchElementException.class, () -> game.getPlayer(invalidPlayerId));
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    public void testSetCurrentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        Player player = new Player(3, new ArrayList<Card>());
        game.setCurrentPlayer(player);
        assertEquals(game.getCurrentPlayer().getId(), player.getId());
        assertEquals(game.getPlayers().size(), numOfPlayers);
    }

    @Test
    public void testSetPlayer_invalidID_codeCoverage() {
        int numOfPlayers = 2;
        String expectedMsg = "invalidPlayerID";
        Game game = new Game(numOfPlayers);
        Player player = new Player(3, new ArrayList<>());
        Exception expection = assertThrows(NoSuchElementException.class, () -> game.setPlayer(player));
        assertEquals(expectedMsg, expection.getMessage());
    }

    @Test
    public void testSetPlayer_notCurrentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        Player player = new Player(2, new ArrayList<>());
        game.setPlayer(player);
        int notCurrentPlayerIndex = 1;
        assertEquals(game.getPlayers().get(notCurrentPlayerIndex).getId(), player.getId());
        assertEquals(game.getPlayers().size(), numOfPlayers);
    }

    @Test
    public void testSetPlayer_currentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        Player player = new Player(1, new ArrayList<>());
        game.setPlayer(player);
        int currentPlayerIndex = 0;
        assertEquals(game.getPlayers().get(currentPlayerIndex).getId(), player.getId());
        assertEquals(game.getPlayers().size(), numOfPlayers);
        assertEquals(game.getCurrentPlayer().getId(), player.getId());
    }

    @Test
    public void testGetPlayers_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        assertEquals(numOfPlayers, game.getPlayers().size());
    }

    @Test
    public void testRestOfCards_small_codeCoverage() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        Deck deck = game.getDeck();
        int numCards = deck.numberOfCards();
        for (Player player : game.getPlayers()) {
            for (Card ignored : player.getHand()) {
                numCards++;
            }
        }
        int numCardsTotal = 77;
        assertEquals(numCardsTotal, numCards);
    }

    @Test
    public void testRestOfCards_medium_codeCoverage() {
        int numOfPlayers = 7;
        Game game = new Game(numOfPlayers);
        Deck deck = game.getDeck();
        int numCards = deck.numberOfCards();
        for (Player player : game.getPlayers()) {
            for (Card ignored : player.getHand()) {
                numCards++;
            }
        }
        int numCardsTotal = 109;
        assertEquals(numCardsTotal, numCards);
    }

    @Test
    public void testInitialShuffle_game2players_codeCoverage_magicNumbersUnavoidable() {
        List<String> allCardNames = new ArrayList<>();
        allCardNames.add("Cattermelon");
        for (int i = 0; i < 4; i++) {
            allCardNames.add("Feral Cat");
        }
        for (int i = 0; i < 4; i++) {
            allCardNames.add("Nope");
        }
        for (int i = 0; i < 2; i++) {
            allCardNames.add("Attack");
        }
        for (int i = 0; i < 5; i++) {
            allCardNames.add("Targeted Attack");
        }
        for (int i = 0; i < 2; i++) {
            allCardNames.add("Shuffle");
        }
        for (int i = 0; i < 2; i++) {
            allCardNames.add("Favor");
        }
        for (int i = 0; i < 6; i++) {
            allCardNames.add("Draw From The Bottom");
        }
        for (int i = 0; i < 4; i++) {
            allCardNames.add("Skip");
        }
        for (int i = 0; i < 5; i++) {
            allCardNames.add("See The Future");
        }
        for (int i = 0; i < 6; i++) {
            allCardNames.add("Alter The Future");
        }
        allCardNames.add("Streaking Kitten");
        allCardNames.add("Super Skip");
        allCardNames.add("Catomic Bomb");
        allCardNames.add("Garbage Collection");
        for (int i = 0; i < 2; i++) {
            allCardNames.add("Curse of the Cat Butt");
        }
        for (int i = 0; i < 3; i++) {
            allCardNames.add("Swap Top and Bottom");
        }
        for (int i = 0; i < 3; i++) {
            allCardNames.add("Mark");
        }
        for (int i = 0; i < 4; i++) {
            allCardNames.add("Reverse");
        }
        allCardNames.add("Imploding Kitten");
        allCardNames.add("Exploding Kitten");

        int numPlayers = 2;
        Game game = new Game(numPlayers);
        Deck deck = game.getDeck();
        List<String> cardsAfterDistributing = new ArrayList<>();
        int numCards = deck.numberOfCards();
        for (int i = 0; i < numCards; i++) {
            Card card = deck.drawTopCard();
            cardsAfterDistributing.add(card.getName());
        }
        int numSame = 0;
        for (int i = 0; i < numCards; i++) {
            if (allCardNames.get(i).equals(cardsAfterDistributing.get(i))) {
                numSame++;
            }
        }
        assertTrue(numSame < numCards);
        assertNotEquals(allCardNames, cardsAfterDistributing);
        assertEquals(allCardNames.size(), cardsAfterDistributing.size());
    }

    @Test
    public void testSetPlayer_setNotCurrentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        Player player = EasyMock.createMock(Player.class);
        int playerId = 2;
        EasyMock.expect(player.getId()).andReturn(playerId).anyTimes();
        EasyMock.expect(player.getHand()).andReturn(new ArrayList<Card>()).anyTimes();
        EasyMock.replay(player);
        game.setPlayer(player);
        assertNotEquals(game.getCurrentPlayer().getId(), player.getId());
        EasyMock.verify(player);
    }

    @Test
    public void testSetPlayer_setCurrentPlayer_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        Player player = EasyMock.createMock(Player.class);
        int playerId = 1;
        EasyMock.expect(player.getId()).andReturn(playerId).anyTimes();
        EasyMock.expect(player.getHand()).andReturn(new ArrayList<Card>()).anyTimes();
        EasyMock.expect(player.getVisibleHand()).andReturn(new ArrayList<Card>()).anyTimes();
        int numTurns = 0;
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns).anyTimes();
        EasyMock.expect(player.getIsTurnOver()).andReturn(false).anyTimes();
        EasyMock.expect(player.getHandVisibility()).andReturn(true).anyTimes();
        EasyMock.replay(player);
        game.setPlayer(player);
        assertEquals(game.getCurrentPlayer().getId(), player.getId());
        assertEquals(game.getCurrentPlayer().getHand(), new ArrayList<>());
        EasyMock.verify(player);
    }

    @Test
    public void testUI_notNull_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        assertNotNull(game.getUI());
    }

    @Test
    public void testSetDeckCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        Deck deck = game.getDeck();
        Deck copyDeck = deck.copy();
        game.setDeck(copyDeck);
        assertEquals(deck.numberOfCards(), copyDeck.numberOfCards());
        for (int i = 0; i < deck.numberOfCards(); i++) {
            assertEquals(deck.drawTopCard().getName(), copyDeck.drawTopCard().getName());
        }
    }
    @Test
    public void testTakeTurn_emptyHand_zeroTurns_setsCurrentPlayerNext() {
        int numberOfPlayers = 2;
        Game game = new Game(numberOfPlayers);

        Player currentPlayer = game.getCurrentPlayer();
        game.emptyCurrentPlayerHand();
        game.setCurrentPlayerTurns(0);

        game.takeTurn();

        Player nextPlayer = game.getCurrentPlayer();

        assertNotEquals(currentPlayer, nextPlayer);

        game.nextPlayer();
        currentPlayer = game.getCurrentPlayer();

        assertTrue(currentPlayer.isHandEmpty());
    }

    @Test
    public void testTakeTurn_handWithEightCards_zeroTurn_setsCurrentPlayerNext_drawsCard() {
        int numberOfPlayers = 2;

        UI mockUI = EasyMock.createMock(UI.class);

        Game game = new Game(numberOfPlayers, mockUI);

        Player currentPlayer = game.getCurrentPlayer();
        game.setCurrentPlayerTurns(0);

        mockUI.clearScreen();
        EasyMock.expectLastCall();
        mockUI.displayMessage("activePlayers");
        EasyMock.expectLastCall();
        mockUI.displayFormattedMessage("playerInfo", 1);
        EasyMock.expectLastCall();
        mockUI.displayFormattedMessage("playerInfo", 2);
        EasyMock.expectLastCall();
        mockUI.displayMessage("separator");
        EasyMock.expectLastCall();
        mockUI.displayFormattedMessage("nextPlayer", 2);
        EasyMock.expectLastCall();
        mockUI.displayMessage("separator");
        EasyMock.expectLastCall();
        mockUI.displayFormattedMessage("player", currentPlayer.getId());
        EasyMock.expectLastCall();
        mockUI.displayMessage("turnStart");
        EasyMock.expectLastCall();
        mockUI.displayFormattedMessage("endTurn", currentPlayer.getId());
        EasyMock.expectLastCall();

        EasyMock.replay(mockUI);

        game.takeTurn();

        Player nextPlayer = game.getCurrentPlayer();

        assertNotEquals(currentPlayer, nextPlayer);

        game.nextPlayer();
        currentPlayer = game.getCurrentPlayer();

        assertEquals(currentPlayer.getHandCount(), 8);

        EasyMock.verify(mockUI);
    }

    @Test
    public void testSetPlayerHasNope_setTrue_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        game.setCurrentPlayerHasNope(true);
        assertTrue(game.getCurrentPlayerHasNope());
    }

    @Test
    public void testSetPlayerHasNope_setFalse_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        game.setCurrentPlayerHasNope(false);
        assertFalse(game.getCurrentPlayerHasNope());
    }

    @Test
    public void testSetGameOver_setTrue_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        game.setGameOver(true);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testSetGameOver_setFalse_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        game.setGameOver(false);
        assertFalse(game.isGameOver());
    }

    @Test
    public void testEmptyCurrentPlayerHand_removesAllCards_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        Player currentPlayer = game.getCurrentPlayer();
        List<Card> currentHand = currentPlayer.getHand();
        assertFalse(currentHand.isEmpty());

        game.emptyCurrentPlayerHand();
        currentPlayer = game.getCurrentPlayer();
        assertTrue(currentPlayer.isHandEmpty());
    }

    @Test
    public void testNoVisibleCardsWhenCallingGetVisibleCards() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);

        Map<Integer, List<Card>> map = game.getVisibleCards();

        assertTrue(map.isEmpty(), "Expected the visible cards map to be empty");
    }

    @Test
    public void testFivePlayersTwoHaveVisibleCardsWhenCallingGetVisibleCards() {
        int numOfPlayers = 5;
        Game game = new Game(numOfPlayers);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);

        List<Player> players = game.getPlayers();


        players.get(0).addCard(card1);
        players.get(0).addVisibleCard(card1);

        players.get(1).addCard(card2);
        players.get(1).addVisibleCard(card2);

        Map<Integer, List<Card>> visibleCardsMap = game.getVisibleCards();

        assertEquals(2, visibleCardsMap.size(), "Expected map to contain two entries for players with visible cards");

        assertTrue(visibleCardsMap.containsKey(players.get(0).getId()));
        assertTrue(visibleCardsMap.containsKey(players.get(1).getId()));

        assertEquals(List.of(card1), visibleCardsMap.get(players.get(0).getId()));
        assertEquals(List.of(card2), visibleCardsMap.get(players.get(1).getId()));
    }

    @Test
    public void testNinePlayersOneHasVisibleCardsWhenCallingGetVisibleCards() {
        int numOfPlayers = 9;
        Game game = new Game(numOfPlayers);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);

        List<Player> players = game.getPlayers();


        players.get(0).addCard(card1);
        players.get(0).addVisibleCard(card1);

        players.get(0).addCard(card2);
        players.get(0).addVisibleCard(card2);

        Map<Integer, List<Card>> visibleCardsMap = game.getVisibleCards();

        assertEquals(1, visibleCardsMap.size(), "Expected map to contain one entries for players with visible cards");

        assertTrue(visibleCardsMap.containsKey(players.get(0).getId()));

        assertEquals(List.of(card1, card2), visibleCardsMap.get(players.get(0).getId()));
    }



    @Test
    public void testRemoveCurrentPlayerCard_cardNotInVisibleHand() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        int initialHandSize = game.getCurrentPlayer().getHand().size();

        int cardIndex = 0;
        game.removeCurrentPlayerCard(cardIndex);
        assertEquals(initialHandSize - 1, game.getCurrentPlayer().getHand().size());
    }

    @Test
    public void testAddCurrentPlayerCard() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        int initialHandSize = game.getCurrentPlayer().getHand().size();

        Card card = EasyMock.createMock(AttackCard.class);
        game.addToCurrentPlayer(card);
        assertEquals(initialHandSize + 1, game.getCurrentPlayer().getHand().size());
    }

    @Test
    public void testSetCurrentPlayerTurns_codeCoverage() {
        int numOfPlayers = 2;
        Game game = new Game(numOfPlayers);
        int turns = 6;
        game.setCurrentPlayerTurns(turns);
        assertEquals(turns, game.getCurrentPlayer().getNumberOfTurns());
    }

    @Test
    public void testSetPlayerTurn_indexInvalidZero_throwsException() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        int turns = 5;

        int playerIndex = 0;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            game.setPlayerTurns(playerIndex, turns);
        });
    }

    @Test
    public void testSetPlayerTurn_indexValidOne_setsValidMoves() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        int turns = 5;

        int playerIndex = 1;
        game.setPlayerTurns(playerIndex, turns);
        assertEquals(game.getPlayer(playerIndex).getNumberOfTurns(), turns);
    }

    @Test
    public void testSetPlayerTurn_indexValidThree_setsValidMoves() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        int turns = 5;

        int playerIndex = 3;
        game.setPlayerTurns(playerIndex, turns);
        assertEquals(game.getPlayer(playerIndex).getNumberOfTurns(), turns);
    }

    @Test
    public void testSetPlayerTurn_indexInvalidFour_throwsException() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        int turns = 5;

        int playerIndex = 4;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            game.setPlayerTurns(playerIndex, turns);
        });
    }

    @Test
    public void testSetNextPlayerTurn_5Turns_setsNextPlayerWith5Turns() {
        int numOfPlayers = 3;
        Game game = new Game(numOfPlayers);
        int turns = 5;

        game.setNextPlayerTurns(turns);
        int nextId = game.getNextPlayerId();
        game.nextPlayer();

        assertEquals(game.getCurrentPlayer().getNumberOfTurns(), turns);
        assertEquals(game.getCurrentPlayer().getId(), nextId);
    }
}