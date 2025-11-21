package domain.Cards;

import domain.Game;
import domain.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeralCatCardTests {
    @Test
    public void testPlayCard_playerHasNoAdditionalCard_throwsException() {
        String expectedMsg = "needTwo";
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        Card card = new FeralCatCard();
        List<String> catCardNames = List.of("Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon");
        int catCardIndex = -1;

        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        for (String catCardName : catCardNames) {
            EasyMock.expect(player.hasCard(catCardName)).andReturn(catCardIndex);
        }
        EasyMock.expect(player.hasTwoOf("Feral Cat")).andReturn(false);

        EasyMock.replay(game, player);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> card.playCard(game, EasyMock.createMock(UI.class)));
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, player);
    }

    @Test
    public void testPlayCard_playerHasCatCard_invalidChosenPlayer_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int invalidPlayerID = 1000;
        int indexCatCard = 0;
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard("Bearded Cat")).andReturn(indexCatCard);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(invalidPlayerID);
        EasyMock.expect(game.getPlayer(invalidPlayerID)).andThrow(new NoSuchElementException("Player with id 1000 does not exist"));

        EasyMock.replay(game, player, ui);

        assertThrows(NoSuchElementException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_playerHasFeralCatCard_invalidChosenPlayer_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int invalidPlayerID = 1000;
        int catCardIndex = -1;
        List<String> catCardNames = List.of("Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon");
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        for (String catCardName : catCardNames) {
            EasyMock.expect(player.hasCard(catCardName)).andReturn(catCardIndex);
        }
        EasyMock.expect(player.hasTwoOf("Feral Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(invalidPlayerID);
        EasyMock.expect(game.getPlayer(invalidPlayerID)).andThrow(new NoSuchElementException("Player with id 1000 does not exist"));

        EasyMock.replay(game, player, ui);

        assertThrows(NoSuchElementException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_playerHasCatCard_chosenSelfPlayer_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int playerID = 0;
        int indexCatCard = 0;
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard("Bearded Cat")).andReturn(indexCatCard);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(playerID);
        EasyMock.expect(game.getPlayer(playerID)).andThrow(new InputMismatchException("You can't choose yourself"));

        EasyMock.replay(game, player, ui);

        assertThrows(InputMismatchException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_playerHasFeralCatCard_chosenSelfPlayer_throwsException() {
        String expectedMsg = "chosenSelfError";
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int playerID = 1;
        int catCardIndex = -1;
        List<String> catCardNames = List.of("Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon");
        FeralCatCard card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        for (String catCardName : catCardNames) {
            EasyMock.expect(player.hasCard(catCardName)).andReturn(catCardIndex);
        }
        EasyMock.expect(player.hasTwoOf("Feral Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(playerID);
        EasyMock.expect(game.getPlayer(playerID)).andReturn(player);
        EasyMock.expect(player.getId()).andReturn(playerID);

        EasyMock.replay(game, player, ui);

        Exception expcetion = assertThrows(InputMismatchException.class, () -> card.playCard(game, ui));
        assertEquals(expectedMsg, expcetion.getMessage());
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_catCard_chosenValidPlayer_invalidCard_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        int currentPlayer1ID = 0;
        int chosenPlayerID = 1;
        int indexCatCard = 0;
        int invalidCardIndex = 1000;
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.hasCard("Bearded Cat")).andReturn(indexCatCard);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayer1ID);
        EasyMock.expect(ui.promptPlayer("takeCard")).andReturn(invalidCardIndex);
        EasyMock.expect(chosenPlayer.chooseCard(invalidCardIndex)).andThrow(new IndexOutOfBoundsException("Invalid Index"));

        EasyMock.replay(game, currentPlayer, chosenPlayer, ui);

        assertThrows(IndexOutOfBoundsException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, currentPlayer, chosenPlayer, ui);
    }

    @Test
    public void testPlayCard_feralCatCard_chosenValidPlayer_invalidCard_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        int currentPlayerID = 0;
        int chosenPlayerID = 1;
        int invalidCardIndex = 1000;
        int catCardIndex = -1;
        List<String> catCardNames = List.of("Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon");
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        for (String catCardName : catCardNames) {
            EasyMock.expect(currentPlayer.hasCard(catCardName)).andReturn(catCardIndex);
        }
        EasyMock.expect(currentPlayer.hasTwoOf("Feral Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerID);
        EasyMock.expect(ui.promptPlayer("takeCard")).andReturn(invalidCardIndex);
        EasyMock.expect(chosenPlayer.chooseCard(invalidCardIndex)).andThrow(new IndexOutOfBoundsException("Invalid Index"));

        EasyMock.replay(game, currentPlayer, chosenPlayer, ui);

        assertThrows(IndexOutOfBoundsException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, currentPlayer, chosenPlayer, ui);
    }

    @Test
    public void testPlayCard_currentPlayerCatCard_validPlayerChosen_validCardChosen_updatesHands() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        int currentPlayerID = 0;
        int chosenPlayerID = 1;
        int indexCatCard = 0;
        int validCardIndex = 0;
        Card mockChosenCard = EasyMock.createMock(Card.class);
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.hasCard("Bearded Cat")).andReturn(indexCatCard);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerID);
        EasyMock.expect(ui.promptPlayer("takeCard")).andReturn(validCardIndex);
        EasyMock.expect(chosenPlayer.chooseCard(validCardIndex)).andReturn(mockChosenCard);
        EasyMock.expect(chosenPlayer.removeCard(validCardIndex)).andStubReturn(0);
        EasyMock.expect(currentPlayer.addCard(mockChosenCard)).andStubReturn(1);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        game.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard("Bearded Cat")).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        int secondCardIndex = 1;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(secondCardIndex);
        game.removeCurrentPlayerCard(secondCardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, currentPlayer, chosenPlayer, ui);

        card.playCard(game, ui);
        EasyMock.verify(game, currentPlayer, chosenPlayer, ui);
    }

    @Test
    public void testPlayCard_currentPlayerFeralCatCard_validPlayerChosen_validCardChosen_updatesHands() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        int currentPlayerID = 0;
        int chosenPlayerID = 1;
        int validCardIndex = 0;
        int catCardIndex = -1;
        List<String> catCardNames = List.of("Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon");
        Card mockChosenCard = EasyMock.createMock(Card.class);
        Card card = new FeralCatCard();

        ui.displayMessage("feralCatCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        for (String catCardName : catCardNames) {
            EasyMock.expect(currentPlayer.hasCard(catCardName)).andReturn(catCardIndex);
        }
        EasyMock.expect(currentPlayer.hasTwoOf("Feral Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerID);
        EasyMock.expect(ui.promptPlayer("takeCard")).andReturn(validCardIndex);
        EasyMock.expect(chosenPlayer.chooseCard(validCardIndex)).andReturn(mockChosenCard);
        EasyMock.expect(chosenPlayer.removeCard(validCardIndex)).andStubReturn(0);
        EasyMock.expect(currentPlayer.addCard(mockChosenCard)).andStubReturn(1);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        game.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        int secondCardIndex = 1;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(secondCardIndex);
        game.removeCurrentPlayerCard(secondCardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, currentPlayer, chosenPlayer, ui);

        card.playCard(game, ui);
        EasyMock.verify(game, currentPlayer, chosenPlayer, ui);
    }
}