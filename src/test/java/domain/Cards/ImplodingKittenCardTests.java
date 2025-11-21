package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;


public class ImplodingKittenCardTests {
    @Test
    public void testPlayCard_notDrawn_insertAt0() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN);
        int numberOfCards = 10;

        ui.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(deck.numberOfCards()).andReturn(numberOfCards);
        ui.displayFormattedMessage("ImplodingKittenIndexHelp", numberOfCards);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        int insertImplodingKittenAtIndex = 0;
        EasyMock.expect(ui.promptPlayer("whereToInsert")).andReturn(insertImplodingKittenAtIndex);
        deck.insertCardAtIndex(EasyMock.anyObject(ImplodingKittenCard.class), EasyMock.eq(insertImplodingKittenAtIndex));
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, deck);

        card.playCard(game, ui);
        EasyMock.verify(game, ui, deck);
    }

    @Test
    public void testPlayCard_notDrawn_insertAt4() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN);
        int numberOfCards = 10;

        ui.displayMessage("implodingKittenFaceDown");
        EasyMock.expectLastCall();
        EasyMock.expect(deck.numberOfCards()).andReturn(numberOfCards);
        ui.displayFormattedMessage("ImplodingKittenIndexHelp", numberOfCards);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        int insertImplodingKittenAtIndex = 4;
        EasyMock.expect(ui.promptPlayer("whereToInsert")).andReturn(insertImplodingKittenAtIndex);
        deck.insertCardAtIndex(EasyMock.anyObject(ImplodingKittenCard.class), EasyMock.eq(insertImplodingKittenAtIndex));
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, deck);

        card.playCard(game, ui);
        EasyMock.verify(game, ui, deck);
    }

    @Test
    public void testPlayCard_drawnBefore() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Player player1 = EasyMock.createMock(Player.class);
        Card card = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.DRAWN);

        ui.displayMessage("implodingKittenFaceUp");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player1);
        EasyMock.expect(player1.getId()).andReturn(1);
        game.deletePlayer(1);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, player1);

        card.playCard(game, ui);
        EasyMock.verify(game, ui, player1);
    }
}