package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

public class CatomicBombCardTests {
    @Test
    public void testPlayCard_deckIsEmpty_throwsException() {
        String expectedMessage = "deckEmpty";
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        CatomicBombCard card = new CatomicBombCard();

        ui.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andStubReturn(deck);
        EasyMock.expect(deck.isEmpty()).andStubReturn(true);

        EasyMock.replay(game, deck, ui);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> card.playCard(game, ui));
        assertEquals(expectedMessage, exception.getMessage());


        EasyMock.verify(game, deck, ui);
    }

    @Test
    public void testPlayCard_deckHasCards_zeroExplodingKittens_returnsSameDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        Card card = new CatomicBombCard();

        ui.displayMessage("catomicBombCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck).anyTimes();
        EasyMock.expect(deck.isEmpty()).andReturn(false).anyTimes();
        deck.moveExplodingKittensToTop();
        EasyMock.expectLastCall().once();
        game.setDeck(deck);
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int mockCardIndex = 0;
        int setNumberOfTurns = 0;
        EasyMock.expect(player.hasCard(card.getName())).andReturn(mockCardIndex);
        game.removeCurrentPlayerCard(mockCardIndex);
        EasyMock.expectLastCall().once();
        player.setNumberOfTurns(setNumberOfTurns);
        EasyMock.expectLastCall().once();
        game.setPlayer(player);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, deck, ui, player);

        card.playCard(game, ui);
        EasyMock.verify(game, deck, ui, player);
    }
}