package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeeTheFutureCardTests {
    @Test
    public void testPlayCard_see3_emptyDeck_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        int numCardsToPeek = 3;
        Card seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andThrow(new UnsupportedOperationException("Deck is empty"));
        EasyMock.replay(game, deck, ui);

        assertThrows(UnsupportedOperationException.class, () -> seeTheFutureCard.playCard(game, ui));
        EasyMock.verify(game, deck, ui);
    }

    @Test
    public void testPlayCard_see5_emptyDeck_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        int numCardsToPeek = 5;
        Card seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andThrow(new UnsupportedOperationException("Deck is empty"));

        EasyMock.replay(game, deck, ui);

        assertThrows(UnsupportedOperationException.class, () -> seeTheFutureCard.playCard(game, ui));
        EasyMock.verify(game, deck, ui);
    }

    @Test
    public void testPlayCard_see3_deckSize1_returnsDeck() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        int numCardsToPeek = 3;
        int expectedCardsPeeked = 1;
        int deckSize = 1;
        int cardIndexToPeek = 0;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(List.of(card));
        EasyMock.expect(card.getName()).andReturn("Card 1").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card, ui, player);

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(card, peekedCards.get(cardIndexToPeek));
        EasyMock.verify(game, deck, card, ui, player);
    }

    @Test
    public void testPlayCard_see5_deckSize2_returnsDeck() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        int numCardsToPeek = 5;
        int deckSize = 2;
        int expectedCardsPeeked = 2;
        int cardIndex1ToPeek = 0;
        int cardIndex2ToPeek = 1;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(List.of(card1, card2));
        EasyMock.expect(card1.getName()).andReturn("Card 1").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Card 2").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 1, "Card 2");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card1, card2, ui, player);

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(card1, peekedCards.get(cardIndex1ToPeek));
        assertEquals(card2, peekedCards.get(cardIndex2ToPeek));
        EasyMock.verify(game, deck, card1, card2, ui, player);
    }

    @Test
    public void testPlayCard_see3_deckSize3_returnsDeck() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        int deckSize = 3;
        int expectedCardsPeeked = 3;
        int numCardsToPeek = 3;
        int cardIndex1ToPeek = 0;
        int cardIndex2ToPeek = 1;
        int cardIndex3ToPeek = 2;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(List.of(card1, card2, card3));
        EasyMock.expect(card1.getName()).andReturn("Card 1").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Card 2").anyTimes();
        EasyMock.expect(card3.getName()).andReturn("Card 3").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 1, "Card 2");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 2, "Card 3");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card1, card2, card3, ui, player);

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(card1, peekedCards.get(cardIndex1ToPeek));
        assertEquals(card2, peekedCards.get(cardIndex2ToPeek));
        assertEquals(card3, peekedCards.get(cardIndex3ToPeek));
        EasyMock.verify(game, deck, card1, card2, card3, ui, player);
    }

    @Test
    public void testPlayCard_see5_deckSize5_returnsDeck() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card card1 = EasyMock.createMock(Card.class);
        Card card2 = EasyMock.createMock(Card.class);
        Card card3 = EasyMock.createMock(Card.class);
        Card card4 = EasyMock.createMock(Card.class);
        Card card5 = EasyMock.createMock(Card.class);
        Player player = EasyMock.createMock(Player.class);
        int deckSize = 5;
        int expectedCardsPeeked = 5;
        int numCardsToPeek = 5;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(List.of(card1, card2, card3, card4, card5));
        EasyMock.expect(card1.getName()).andReturn("Card 1").anyTimes();
        EasyMock.expect(card2.getName()).andReturn("Card 2").anyTimes();
        EasyMock.expect(card3.getName()).andReturn("Card 3").anyTimes();
        EasyMock.expect(card4.getName()).andReturn("Card 4").anyTimes();
        EasyMock.expect(card5.getName()).andReturn("Card 5").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 1, "Card 2");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 2, "Card 3");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 3, "Card 4");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 4, "Card 5");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, card1, card2, card3, card4, card5, ui, player);

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(card1, peekedCards.get(0));
        assertEquals(card2, peekedCards.get(1));
        assertEquals(card3, peekedCards.get(2));
        assertEquals(card4, peekedCards.get(3));
        assertEquals(card5, peekedCards.get(4));
        EasyMock.verify(game, deck, card1, card2, card3, card4, card5, ui, player);
    }

    @Test
    public void testPlayCard_see3_deckSize7_returnsTop3() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        int deckSize = 7;
        int expectedCardsPeeked = 3;
        int numCardsToPeek = 3;
        List<Card> cards = new ArrayList<>();
        List<Card> topThreeCards = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            cards.add(EasyMock.createMock(Card.class));
            if (i < numCardsToPeek) {
                topThreeCards.add(cards.get(i));
            }
        }
        int cardIndex1ToPeek = 0;
        int cardIndex2ToPeek = 1;
        int cardIndex3ToPeek = 2;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(topThreeCards);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(cards.get(0).getName()).andReturn("Card 1").anyTimes();
        EasyMock.expect(cards.get(1).getName()).andReturn("Card 2").anyTimes();
        EasyMock.expect(cards.get(2).getName()).andReturn("Card 3").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 1, "Card 2");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 2, "Card 3");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, ui, player);
        EasyMock.replay(cards.toArray());

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(cards.get(cardIndex1ToPeek), peekedCards.get(cardIndex1ToPeek));
        assertEquals(cards.get(cardIndex2ToPeek), peekedCards.get(cardIndex2ToPeek));
        assertEquals(cards.get(cardIndex3ToPeek), peekedCards.get(cardIndex3ToPeek));
        EasyMock.verify(game, deck, ui, player);
        EasyMock.verify(cards.toArray());
    }

    @Test
    public void testPlayCard_see5_deckSize10_returnsTop5() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        List<Card> cards = new ArrayList<>();
        List<Card> topFiveCards = new ArrayList<>();
        int deckSize = 10;
        int expectedCardsPeeked = 5;
        int numCardsToPeek = 5;
        for (int i = 0; i < deckSize; i++) {
            cards.add(EasyMock.createMock(Card.class));
            if (i < numCardsToPeek) {
                topFiveCards.add(cards.get(i));
            }
        }
        int cardIndex1ToPeek = 0;
        int cardIndex2ToPeek = 1;
        int cardIndex3ToPeek = 2;
        int cardIndex4ToPeek = 3;
        int cardIndex5ToPeek = 4;
        SeeTheFutureCard seeTheFutureCard = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE);

        ui.displayMessage("seeTheFutureCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.peekTopDeck(numCardsToPeek)).andReturn(topFiveCards);
        EasyMock.expect(deck.numberOfCards()).andReturn(deckSize).anyTimes();
        EasyMock.expect(cards.get(0).getName()).andReturn("Card 1").anyTimes();
        EasyMock.expect(cards.get(1).getName()).andReturn("Card 2").anyTimes();
        EasyMock.expect(cards.get(2).getName()).andReturn("Card 3").anyTimes();
        EasyMock.expect(cards.get(3).getName()).andReturn("Card 4").anyTimes();
        EasyMock.expect(cards.get(4).getName()).andReturn("Card 5").anyTimes();
        ui.displayFormattedMessage("visibleCard", 0, "Card 1");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 1, "Card 2");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 2, "Card 3");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 3, "Card 4");
        EasyMock.expectLastCall().once();
        ui.displayFormattedMessage("visibleCard", 4, "Card 5");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(seeTheFutureCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, ui, player);
        EasyMock.replay(cards.toArray());

        seeTheFutureCard.playCard(game, ui);
        List<Card> peekedCards = seeTheFutureCard.getPeekedCards();
        assertEquals(expectedCardsPeeked, peekedCards.size());
        assertEquals(cards.get(cardIndex1ToPeek), peekedCards.get(cardIndex1ToPeek));
        assertEquals(cards.get(cardIndex2ToPeek), peekedCards.get(cardIndex2ToPeek));
        assertEquals(cards.get(cardIndex3ToPeek), peekedCards.get(cardIndex3ToPeek));
        assertEquals(cards.get(cardIndex4ToPeek), peekedCards.get(cardIndex4ToPeek));
        assertEquals(cards.get(cardIndex5ToPeek), peekedCards.get(cardIndex5ToPeek));
        EasyMock.verify(game, deck, ui, player);
        EasyMock.verify(cards.toArray());
    }
}