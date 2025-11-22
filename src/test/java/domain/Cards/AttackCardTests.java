package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackCardTests {
    @Test
    public void testPlayCard_noTurn_returnsNextPlayerWith2Turns() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player nextPlayer = EasyMock.createMock(Player.class);
        Card card = new AttackCard();

        int initialTurns = 0;
        int setTurns = 2;

        ui.displayMessage("attackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).times(1);
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(initialTurns).times(2);
        EasyMock.expect(nextPlayer.getNumberOfTurns()).andReturn(setTurns);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();
        game.setCurrentPlayerTurns(initialTurns);
        EasyMock.expectLastCall();
        game.setNextPlayerTurns(setTurns);
        EasyMock.expectLastCall();

        EasyMock.replay(currentPlayer, nextPlayer, game, ui);

        card.playCard(game, ui);
        assertEquals(setTurns, nextPlayer.getNumberOfTurns());
        EasyMock.verify(currentPlayer, nextPlayer, game, ui);
    }

    @Test
    public void testPlayCard_oneTurn_returnsNextPlayerWith3Turns() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player nextPlayer = EasyMock.createMock(Player.class);
        Card card = new AttackCard();

        int initialTurns = 1;
        int setTurns = 2;

        ui.displayMessage("attackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(initialTurns);
        EasyMock.expect(nextPlayer.getNumberOfTurns()).andReturn(setTurns);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();
        game.setCurrentPlayerTurns(mockIndex);
        EasyMock.expectLastCall();
        game.setNextPlayerTurns(setTurns);
        EasyMock.expectLastCall();

        EasyMock.replay(currentPlayer, nextPlayer, game, ui);

        card.playCard(game, ui);
        assertEquals(setTurns, nextPlayer.getNumberOfTurns());
        EasyMock.verify(currentPlayer, nextPlayer, game, ui);
    }

    @Test
    public void testPlayCard_twoTurns_returnsNextPlayerWith4Turns() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player nextPlayer = EasyMock.createMock(Player.class);
        Card card = new AttackCard();

        int initialTurns = 2;
        int setTurns = 4;

        ui.displayMessage("attackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(initialTurns).times(2);
        EasyMock.expect(nextPlayer.getNumberOfTurns()).andReturn(setTurns);

        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();
        game.setCurrentPlayerTurns(mockIndex);
        EasyMock.expectLastCall();
        game.setNextPlayerTurns(setTurns);
        EasyMock.expectLastCall();

        EasyMock.replay(currentPlayer, nextPlayer, game, ui);

        card.playCard(game, ui);
        assertEquals(setTurns, nextPlayer.getNumberOfTurns());
        EasyMock.verify(currentPlayer, nextPlayer, game, ui);
    }
}