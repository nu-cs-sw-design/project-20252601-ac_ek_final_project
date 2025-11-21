package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CatCardTests {
    @Test
    public void testPlayCard_playerHasOnly1CatCard_throwsException() {
        String expectedMsg = "needTwo";
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        CatCard card = new CatCard(CatCard.CatCardType.BEARDED_CAT);

        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasTwoOf("Bearded Cat")).andReturn(false);

        EasyMock.replay(game, player);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> card.playCard(game, EasyMock.createMock(UI.class)));
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, player);
    }

    @Test
    public void testPlayCard_chosenPlayerNotExist_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        CatCard card = new CatCard(CatCard.CatCardType.BEARDED_CAT);

        int invalidPlayerID = 1000;

        ui.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasTwoOf("Bearded Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(invalidPlayerID);
        EasyMock.expect(game.getPlayer(invalidPlayerID)).andThrow(new NoSuchElementException("Player with id 1000 does not exist"));

        EasyMock.replay(game, player, ui);

        assertThrows(NoSuchElementException.class, () -> card.playCard(game, ui));
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_chosenSelfPlayer_throwsException() {
        String expectedMsg = "chosenSelfError";
        UI ui = EasyMock.createMock(UI.class);

        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        CatCard card = new CatCard(CatCard.CatCardType.BEARDED_CAT);

        int playerID = 1;

        ui.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasTwoOf("Bearded Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(playerID);
        EasyMock.expect(game.getPlayer(playerID)).andReturn(player);
        EasyMock.expect(player.getId()).andReturn(playerID);

        EasyMock.replay(game, player, ui);

        Exception exception = assertThrows(InputMismatchException.class, () -> card.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_chosenIndexOOB_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        CatCard card = new CatCard(CatCard.CatCardType.BEARDED_CAT);

        int currentPlayerID = 1;
        int chosenPlayerID = 2;
        int invalidCardIndex = 1000;

        ui.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.hasTwoOf("Bearded Cat")).andReturn(true);
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
    public void testPlayCard_updatesHand() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        Card cardInHand = EasyMock.createMock(Card.class);
        CatCard card = new CatCard(CatCard.CatCardType.BEARDED_CAT);

        int currentPlayerID = 1;
        int chosenPlayerID = 2;
        int cardIndex = 0;
        int currentPlayerHandSize = 1;
        int chosenPlayerHandSize = 0;

        ui.displayMessage("catCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.hasTwoOf("Bearded Cat")).andReturn(true);
        EasyMock.expect(ui.promptPlayer("playerID")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerID);
        EasyMock.expect(ui.promptPlayer("takeCard")).andReturn(cardIndex);
        EasyMock.expect(chosenPlayer.chooseCard(cardIndex)).andReturn(cardInHand);
        EasyMock.expect(chosenPlayer.removeCard(cardIndex)).andStubReturn(chosenPlayerHandSize);
        EasyMock.expect(currentPlayer.addCard(cardInHand)).andStubReturn(currentPlayerHandSize);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        game.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        int firstCardIndex = 0;
        int secondCardIndex = 2;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(firstCardIndex);
        game.removeCurrentPlayerCard(firstCardIndex);
        EasyMock.expectLastCall();
        EasyMock.expect(currentPlayer.removeCard(firstCardIndex)).andReturn(currentPlayerHandSize);
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(secondCardIndex);
        game.removeCurrentPlayerCard(secondCardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, currentPlayer, chosenPlayer, cardInHand, ui);

        card.playCard(game, ui);
        EasyMock.verify(game, currentPlayer, chosenPlayer, cardInHand, ui);
    }
}