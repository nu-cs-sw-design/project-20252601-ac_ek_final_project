package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlterTheFutureCardTests {
    @Test
    public void testPlayCard_twoCards_differentIndexes_returnModifiedDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);

        AlterTheFutureCard card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE);

        ui.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getDeck()).andReturn(deck).once();

        List<Card> cardsToAlter = List.of(
                EasyMock.createMock(DefuseCard.class),
                EasyMock.createMock(AttackCard.class)
        );
        EasyMock.expect(deck.peekTopDeck(3)).andReturn(cardsToAlter).once();

        for (int i = 0; i < cardsToAlter.size(); i++) {
            EasyMock.expect(cardsToAlter.get(i).getName()).andReturn("Card" + i).once();
            ui.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall().once();
        }

        List<Integer> newIndexes = List.of(1, 0);
        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            EasyMock.expectLastCall().once();
            EasyMock.expect(ui.promptPlayer("newIndex")).andReturn(newIndexes.get(i)).once();
        }

        card.setIndexes(newIndexes);

        deck.alterTopDeck(cardsToAlter, newIndexes);
        EasyMock.expectLastCall().once();
        game.setDeck(deck);
        EasyMock.expectLastCall().once();

        Player currentPlayer = EasyMock.createMock(Player.class);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, deck, ui, currentPlayer, cardsToAlter.get(0), cardsToAlter.get(1));

        card.playCard(game, ui);

        EasyMock.verify(game, deck, ui, currentPlayer, cardsToAlter.get(0), cardsToAlter.get(1));
    }


    @Test
    public void testPlayCard_threeCards_differentIndexes_returnModifiedDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);

        AlterTheFutureCard card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE);

        ui.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getDeck()).andReturn(deck).once();

        List<Card> cardsToAlter = List.of(
                EasyMock.createMock(DefuseCard.class),
                EasyMock.createMock(AttackCard.class),
                EasyMock.createMock(CatCard.class)
        );
        EasyMock.expect(deck.peekTopDeck(3)).andReturn(cardsToAlter).once();

        for (int i = 0; i < cardsToAlter.size(); i++) {
            EasyMock.expect(cardsToAlter.get(i).getName()).andReturn("Card" + i).once();
            ui.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall().once();
        }

        List<Integer> newIndexes = List.of(1, 0, 2);
        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            EasyMock.expectLastCall().once();
            EasyMock.expect(ui.promptPlayer("newIndex")).andReturn(newIndexes.get(i)).once();
        }

        card.setIndexes(newIndexes);

        deck.alterTopDeck(cardsToAlter, newIndexes);
        EasyMock.expectLastCall().once();
        game.setDeck(deck);
        EasyMock.expectLastCall().once();

        Player currentPlayer = EasyMock.createMock(Player.class);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, deck, ui, currentPlayer, cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2));

        card.playCard(game, ui);
        EasyMock.verify(game, deck, ui, currentPlayer, cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2));
    }

    @Test
    public void testPlayCard_fiveCards_sameIndexes_returnSameDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);

        AlterTheFutureCard card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getDeck()).andReturn(deck).once();

        List<Card> cardsToAlter = List.of(
                EasyMock.createMock(DefuseCard.class),
                EasyMock.createMock(AttackCard.class),
                EasyMock.createMock(CatCard.class),
                EasyMock.createMock(CatomicBombCard.class),
                EasyMock.createMock(CurseOfTheCatButtCard.class)
        );
        int numCardsToPeek = 5;
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(cardsToAlter).once();

        for (int i = 0; i < cardsToAlter.size(); i++) {
            EasyMock.expect(cardsToAlter.get(i).getName()).andReturn("Card" + i).once();
            ui.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall().once();
        }

        List<Integer> newIndexes = List.of(0, 1, 2, 3, 4);
        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            EasyMock.expectLastCall().once();
            EasyMock.expect(ui.promptPlayer("newIndex")).andReturn(newIndexes.get(i)).once();
        }

        card.setIndexes(newIndexes);

        deck.alterTopDeck(cardsToAlter, newIndexes);
        EasyMock.expectLastCall().once();

        game.setDeck(deck);
        EasyMock.expectLastCall().once();

        Player currentPlayer = EasyMock.createMock(Player.class);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();

        EasyMock.replay(cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2), cardsToAlter.get(3), cardsToAlter.get(4));
        EasyMock.replay(game, deck, ui, currentPlayer);

        card.playCard(game, ui);

        EasyMock.verify(cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2), cardsToAlter.get(3), cardsToAlter.get(4));
        EasyMock.verify(game, deck, ui, currentPlayer);
    }


    @Test
    public void testPlayCard_fiveCards_differentIndexes_returnModifiedDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);

        AlterTheFutureCard card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getDeck()).andReturn(deck).once();

        List<Card> cardsToAlter = List.of(
                EasyMock.createMock(DefuseCard.class),
                EasyMock.createMock(AttackCard.class),
                EasyMock.createMock(CatCard.class),
                EasyMock.createMock(CatomicBombCard.class),
                EasyMock.createMock(CurseOfTheCatButtCard.class)
        );
        int numCardsToPeek = 5;
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(cardsToAlter).once();

        for (int i = 0; i < cardsToAlter.size(); i++) {
            EasyMock.expect(cardsToAlter.get(i).getName()).andReturn("Card" + i).once();
            ui.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall().once();
        }

        List<Integer> newIndexes = List.of(4, 2, 0, 1, 3);
        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            EasyMock.expectLastCall().once();
            EasyMock.expect(ui.promptPlayer("newIndex")).andReturn(newIndexes.get(i)).once();
        }

        deck.alterTopDeck(EasyMock.eq(cardsToAlter), EasyMock.eq(newIndexes));
        EasyMock.expectLastCall().once();

        game.setDeck(deck);
        EasyMock.expectLastCall().once();

        Player currentPlayer = EasyMock.createMock(Player.class);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        int mockIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(card.getName())).andReturn(mockIndex);
        game.removeCurrentPlayerCard(mockIndex);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, deck, ui, currentPlayer);
        EasyMock.replay(cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2), cardsToAlter.get(3), cardsToAlter.get(4));

        card.playCard(game, ui);

        EasyMock.verify(cardsToAlter.get(0), cardsToAlter.get(1), cardsToAlter.get(2), cardsToAlter.get(3), cardsToAlter.get(4));
        EasyMock.verify(game, deck, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_twoCards_emptyIndexes_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        String expectedMsg = "emptyIndexes";

        AlterTheFutureCard card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE);

        ui.displayMessage("alterTheFutureCard");
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getDeck()).andReturn(deck).once();

        List<Card> cardsToAlter = List.of(
                EasyMock.createMock(DefuseCard.class),
                EasyMock.createMock(AttackCard.class)
        );
        EasyMock.expect(deck.peekTopDeck(3)).andReturn(cardsToAlter).once();

        for (int i = 0; i < cardsToAlter.size(); i++) {
            EasyMock.expect(cardsToAlter.get(i).getName()).andReturn("Card" + i).once();
            ui.displayFormattedMessage("visibleCard", i, "Card" + i);
            EasyMock.expectLastCall().once();
        }

        ui.displayFormattedMessage("cardAtIndex", 0);
        EasyMock.expectLastCall().once();
        EasyMock.expect(ui.promptPlayer("newIndex")).andReturn(1).once();

        ui.displayFormattedMessage("cardAtIndex", 1);
        EasyMock.expectLastCall().once();
        EasyMock.expect(ui.promptPlayer("newIndex")).andThrow(new IllegalArgumentException("emptyIndexes"));

        EasyMock.replay(game, deck, ui, cardsToAlter.get(0), cardsToAlter.get(1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> card.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, deck, ui, cardsToAlter.get(0), cardsToAlter.get(1));
    }
}