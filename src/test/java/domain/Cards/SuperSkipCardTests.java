package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

public class SuperSkipCardTests {
    @Test
    public void testPlayCard_currentPlayerPlaysSuperSkipWithTwoTurns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        SuperSkipCard superSkipCard = new SuperSkipCard();
        int numTurns = 2;
        int setTurns = 0;

        ui.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        game.setCurrentPlayerTurns(setTurns);
        EasyMock.expect(player.hasCard(superSkipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player, ui);

        superSkipCard.playCard(game, ui);
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysSuperSkipWithZeroTurns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        SuperSkipCard superSkipCard = new SuperSkipCard();
        int numTurns = 0;

        ui.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(superSkipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player, ui);

        superSkipCard.playCard(game, ui);
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysSuperSkipWithOneTurn(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        SuperSkipCard superSkipCard = new SuperSkipCard();
        int numTurns = 1;
        int setTurns = 0;

        ui.displayMessage("superSkipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        game.setCurrentPlayerTurns(setTurns);
        EasyMock.expect(player.hasCard(superSkipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player, ui);

        superSkipCard.playCard(game, ui);
        EasyMock.verify(game, player, ui);
    }
}
