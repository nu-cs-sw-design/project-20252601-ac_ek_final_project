package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

public class SkipCardTests {
    @Test
    public void testPlayCard_currentPlayerPlaysSkipWithTwoTurns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int numTurns = 2;
        SkipCard skipCard = new SkipCard();

        ui.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        game.setCurrentPlayerTurn(numTurns-1);
        EasyMock.expect(player.hasCard(skipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player, ui);

        skipCard.playCard(game, ui);
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysSkipWithOneTurn(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int numTurns = 1;
        SkipCard skipCard = new SkipCard();

        ui.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        game.setCurrentPlayerTurn(numTurns-1);
        EasyMock.expect(player.hasCard(skipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player, ui);

        skipCard.playCard(game, ui);
        EasyMock.verify(game, player, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysSkipWithZeroTurns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player = EasyMock.createMock(Player.class);
        int numTurns = 0;
        SkipCard skipCard = new SkipCard();

        ui.displayMessage("skipCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.getNumberOfTurns()).andReturn(numTurns);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(skipCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, player);

        skipCard.playCard(game, ui);
        EasyMock.verify(game, player);
    }
}