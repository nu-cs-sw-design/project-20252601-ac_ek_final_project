package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReverseCardTests {
    @Test
    public void testPlayerCard_2Players_worksAsSkipCard(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player1 = EasyMock.createMock(Player.class);
        Player player2 = EasyMock.createMock(Player.class);
        List<Player> players = Arrays.asList(player1, player2);
        Card reverseCard = new ReverseCard();

        ui.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player1);
        int cardIndex = 0;
        int numTurns = 1;
        int newNumTurns = 0;

        EasyMock.expect(player1.hasCard(reverseCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        EasyMock.expect(player1.getNumberOfTurns()).andReturn(numTurns);
        game.setCurrentPlayerTurn(newNumTurns);

        EasyMock.replay(game, player1, player2, ui);

        reverseCard.playCard(game, ui);
        EasyMock.verify(game, player1, player2, ui);
    }

    @Test
    public void testPlayerCard_2PlayersZeroTurns_worksAsSkipCard(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player1 = EasyMock.createMock(Player.class);
        Player player2 = EasyMock.createMock(Player.class);
        List<Player> players = Arrays.asList(player1, player2);
        Card reverseCard = new ReverseCard();

        ui.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player1);
        int cardIndex = 0;
        int numTurns = 0;

        EasyMock.expect(player1.hasCard(reverseCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        EasyMock.expect(player1.getNumberOfTurns()).andReturn(numTurns);

        EasyMock.replay(game, player1, player2, ui);

        reverseCard.playCard(game, ui);
        EasyMock.verify(game, player1, player2, ui);
    }

    @Test
    public void testPlayCard_4Players_returnsReversedPlayers() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player player1 = EasyMock.createMock(Player.class);
        Player player2 = EasyMock.createMock(Player.class);
        Player player3 = EasyMock.createMock(Player.class);
        Player player4 = EasyMock.createMock(Player.class);
        List<Player> players = Arrays.asList(player1, player2, player3, player4);
        List<Player> reversedPlayers = Arrays.asList(player4, player3, player2, player1);
        Card reverseCard = new ReverseCard();

        ui.displayMessage("reverseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player1);
        int cardIndex = 0;
        EasyMock.expect(player1.hasCard(reverseCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        game.setPlayers(reversedPlayers);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPlayers()).andReturn(reversedPlayers);

        EasyMock.replay(game, player1, player2, player3, player4, ui);

        reverseCard.playCard(game, ui);
        assertEquals(reversedPlayers, game.getPlayers());
        EasyMock.verify(game, player1, player2, player3, player4, ui);
    }

    @Test
    public void testPlayCard_0Players_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Card reverseCard = new ReverseCard();

        ui.displayMessage("reverseCard");
        EasyMock.expectLastCall();

        EasyMock.expect(game.getPlayers()).andReturn(Arrays.asList());

        EasyMock.replay(ui, game);

        assertThrows(IllegalStateException.class, () -> {
            reverseCard.playCard(game, ui);
        });

        EasyMock.verify(ui, game);
    }
}