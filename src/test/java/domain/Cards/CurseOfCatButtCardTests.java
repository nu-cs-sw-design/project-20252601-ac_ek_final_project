package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CurseOfCatButtCardTests {
    @Test
    public void testPlayCard_playerNotExist_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);

        Card card = new CurseOfTheCatButtCard();

        ui.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(ui.promptPlayer("playerIDCurse")).andThrow(new NoSuchElementException("Player with id 1000 does not exist"));

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

        Card card = new CurseOfTheCatButtCard();

        int playerId = 1;

        ui.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(ui.promptPlayer("playerIDCurse")).andReturn(playerId);
        EasyMock.expect(game.getPlayer(playerId)).andReturn(player);
        EasyMock.expect(player.getId()).andReturn(playerId);

        EasyMock.replay(game, player, ui);

        Exception exception = assertThrows(InputMismatchException.class, () -> card.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_updatesHand() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);

        Card card = new CurseOfTheCatButtCard();

        int currentPlayerID = 1;
        int chosenPlayerID = 2;

        ui.displayMessage("curseCatButt");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(ui.promptPlayer("playerIDCurse")).andReturn(chosenPlayerID);
        EasyMock.expect(game.getPlayer(chosenPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerID);
        chosenPlayer.setHandVisibility(false);
        EasyMock.expectLastCall();
        chosenPlayer.shuffleHand();
        EasyMock.expectLastCall().once();
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall().once();
        EasyMock.expect(chosenPlayer.getHandVisibility()).andReturn(false);
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, currentPlayer, chosenPlayer, ui);

        card.playCard(game, ui);
        assertFalse(chosenPlayer.getHandVisibility());
        EasyMock.verify(game, currentPlayer, chosenPlayer, ui);
    }
}