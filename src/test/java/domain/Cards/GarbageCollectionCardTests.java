package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

public class GarbageCollectionCardTests {
    private GarbageCollectionCard garbageCollectionCard;
    private Game game;
    private UI ui;
    private Card card;
    private Deck deck;
    private Player player;

    @BeforeEach
    public void setup() {
        ui = EasyMock.createMock(UI.class);
        game = EasyMock.createMock(Game.class);
        deck = EasyMock.createMock(Deck.class);
        player = EasyMock.createMock(Player.class);
        card = EasyMock.createMock(Card.class);
        garbageCollectionCard = new GarbageCollectionCard();
    }

    @Test
    public void testPlayCard_given2Players_WhenGarbageCollectionCardPlayed_Then2CardsAreAddedToDeck() {
        int numPlayers = 2;

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(player);
        }
        int cardIndex = 0;
        int handSize = 5;

        ui.displayMessage("garbageCollectionCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(ui.promptPlayer("discard")).andReturn(cardIndex).times(numPlayers);
        EasyMock.expect(player.chooseCard(cardIndex)).andReturn(card).times(numPlayers);
        EasyMock.expect(player.removeCard(cardIndex)).andReturn(handSize).times(numPlayers);
        int i = 1;
        for (Player player : players) {
            EasyMock.expect(player.getId()).andReturn(i);
            ui.displayFormattedMessage("player", i);
            EasyMock.expectLastCall();
            i += 1;
        }
        deck.addCard(card);
        EasyMock.expectLastCall().times(numPlayers);
        game.setPlayer(player);
        EasyMock.expectLastCall().times(numPlayers);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard(garbageCollectionCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, player, deck, card);

        garbageCollectionCard.playCard(game, ui);
        EasyMock.verify(game, ui, player, deck, card);
    }

    @Test
    public void testPlayCard_given3Players_WhenGarbageCollectionCardPlayed_Then3CardsAddedToDeck() {
        int numPlayers = 3;

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(player);
        }
        int cardIndex = 0;
        int handSize = 5;

        ui.displayMessage("garbageCollectionCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(ui.promptPlayer("discard")).andReturn(cardIndex).times(numPlayers);
        EasyMock.expect(player.chooseCard(cardIndex)).andReturn(card).times(numPlayers);
        EasyMock.expect(player.removeCard(cardIndex)).andReturn(handSize).times(numPlayers);
        int i = 1;
        for (Player player : players) {
            EasyMock.expect(player.getId()).andReturn(i);
            ui.displayFormattedMessage("player", i);
            EasyMock.expectLastCall();
            i += 1;
        }
        deck.addCard(card);
        EasyMock.expectLastCall().times(numPlayers);
        game.setPlayer(player);
        EasyMock.expectLastCall().times(numPlayers);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard(garbageCollectionCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, player, deck, card);

        garbageCollectionCard.playCard(game, ui);
        EasyMock.verify(game, ui, player, deck, card);
    }

    @Test
    public void testPlayCard_given9Players_WhenGarbageCollectionCardPlayed_Then9CardsAreAddedToDeck() {
        int numPlayers = 9;

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(player);
        }
        int cardIndex = 0;
        int handSize = 5;

        ui.displayMessage("garbageCollectionCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(ui.promptPlayer("discard")).andReturn(cardIndex).times(numPlayers);
        EasyMock.expect(player.chooseCard(cardIndex)).andReturn(card).times(numPlayers);
        EasyMock.expect(player.removeCard(cardIndex)).andReturn(handSize).times(numPlayers);
        int i = 1;
        for (Player player : players) {
            EasyMock.expect(player.getId()).andReturn(i);
            ui.displayFormattedMessage("player", i);
            EasyMock.expectLastCall();
            i += 1;
        }
        deck.addCard(card);
        EasyMock.expectLastCall().times(numPlayers);
        game.setPlayer(player);
        EasyMock.expectLastCall().times(numPlayers);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard(garbageCollectionCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, player, deck, card);

        garbageCollectionCard.playCard(game, ui);
        EasyMock.verify(game, ui, player, deck, card);
    }

    @Test
    public void testPlayCard_given10Players_WhenGarbageCollectionCardPlayed_Then10CardsAreAddedToDeck() {
        int numPlayers = 10;

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(player);
        }
        int cardIndex = 0;
        int handSize = 5;

        ui.displayMessage("garbageCollectionCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(ui.promptPlayer("discard")).andReturn(cardIndex).times(numPlayers);
        EasyMock.expect(player.chooseCard(cardIndex)).andReturn(card).times(numPlayers);
        EasyMock.expect(player.removeCard(cardIndex)).andReturn(handSize).times(numPlayers);
        int i = 1;
        for (Player player : players) {
            EasyMock.expect(player.getId()).andReturn(i);
            ui.displayFormattedMessage("player", i);
            EasyMock.expectLastCall();
            i += 1;
        }
        deck.addCard(card);
        EasyMock.expectLastCall().times(numPlayers);
        game.setPlayer(player);
        EasyMock.expectLastCall().times(numPlayers);
        deck.shuffle();
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        EasyMock.expect(player.hasCard(garbageCollectionCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, player, deck, card);

        garbageCollectionCard.playCard(game, ui);
        EasyMock.verify(game, ui, player, deck, card);
    }
}