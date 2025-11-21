package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShuffleCardTests {
    @Test
    public void testPlayCard_emptyDeck_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card shuffleCard = new ShuffleCard();

        ui.displayMessage("shuffleCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        deck.shuffle();
        EasyMock.expectLastCall().andThrow(new UnsupportedOperationException("Deck is empty"));

        EasyMock.replay(game, deck, ui);

        assertThrows(UnsupportedOperationException.class, () -> shuffleCard.playCard(game, ui));
        EasyMock.verify(game, deck, ui);
    }

    @Test
    public void testPlayCard_deckSize1_returnsSameDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        int numCardsToPeek = 1;
        Card shuffleCard = new ShuffleCard();

        ui.displayMessage("shuffleCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(List.of(card)).times(2);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(shuffleCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card, ui, player);

        List<Card> deckBeforeShuffle = deck.peekTopDeck(numCardsToPeek);
        shuffleCard.playCard(game, ui);
        List<Card> deckAfterShuffle = deck.peekTopDeck(numCardsToPeek);

        assertEquals(deckBeforeShuffle, deckAfterShuffle);
        EasyMock.verify(game, deck, card, ui, player);
    }

    @Test
    public void testPlayCard_deckSize4_returnsDifferentDeck() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        List<Card> initialOrder = List.of(card1, card2, card3, card4);
        List<Card> shuffledOrder = List.of(card3, card2, card4, card1);
        int numCardsToPeek = 4;
        Card shuffleCard = new ShuffleCard();

        ui.displayMessage("shuffleCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(initialOrder);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(shuffledOrder);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(shuffleCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card1, card2, card3, card4, ui, player);

        List<Card> deckBeforeShuffle = deck.peekTopDeck(numCardsToPeek);
        shuffleCard.playCard(game, ui);
        List<Card> deckAfterShuffle = deck.peekTopDeck(numCardsToPeek);

        assertNotEquals(deckBeforeShuffle, deckAfterShuffle);
        assertEquals(deckBeforeShuffle.size(), deckAfterShuffle.size());
        assertTrue(deckBeforeShuffle.containsAll(deckAfterShuffle));
        assertTrue(deckAfterShuffle.containsAll(deckBeforeShuffle));
        EasyMock.verify(game, deck, card1, card2, card3, card4, ui, player);
    }
}