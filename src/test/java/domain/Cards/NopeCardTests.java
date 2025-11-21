package domain.Cards;

import domain.Game;
import domain.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UI;

public class NopeCardTests {
    @Test
    public void testPlayCard_playerHasOneNope_setsHasNopeFalse() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        NopeCard nopeCard = new NopeCard();

        EasyMock.expect(game.getUI()).andReturn(ui).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasTwoOf("Nope")).andReturn(false).once();
        game.setCurrentPlayerHasNope(false);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, currentPlayer);

        nopeCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_playerHasTwoNopes_setsHasNopeTrue() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        NopeCard nopeCard = new NopeCard();

        EasyMock.expect(game.getUI()).andReturn(ui).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasTwoOf("Nope")).andReturn(true).once();
        game.setCurrentPlayerHasNope(true);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, currentPlayer);

        nopeCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer);
    }
}
