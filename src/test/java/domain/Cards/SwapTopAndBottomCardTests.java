package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SwapTopAndBottomCardTests {
    @Test
    public void testPlayCard_emptyDeck() {
        String expectedMsg = "deckEmpty";
        SwapTopAndBottomCard swapTopAndBottomCard = new SwapTopAndBottomCard();

        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);

        ui.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.isEmpty()).andReturn(true);

        EasyMock.replay(game, deck, ui);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {swapTopAndBottomCard.playCard(game, ui);});
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game,deck, ui);
    }
    @Test
    public void testPlayCard_deckSize1() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        int numCards = 1;
        SwapTopAndBottomCard swapTopAndBottomCard = new SwapTopAndBottomCard();

        ui.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.isEmpty()).andReturn(false);
        EasyMock.expect(deck.numberOfCards()).andReturn(numCards);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(swapTopAndBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, ui, player);

        swapTopAndBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, ui, player);
    }
    @Test
    public void testPlayCard_deckSize4() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        int numCards = 4;
        SwapTopAndBottomCard swapTopAndBottomCard = new SwapTopAndBottomCard();

        ui.displayMessage("swapTopAndBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.isEmpty()).andReturn(false);
        EasyMock.expect(deck.numberOfCards()).andReturn(numCards);
        deck.swapTopAndBottom();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard(swapTopAndBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, ui, player);

        swapTopAndBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, ui, player);
    }
}