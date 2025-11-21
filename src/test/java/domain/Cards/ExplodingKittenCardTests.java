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

public class ExplodingKittenCardTests {
    private final static int PLAYER_ID = 1;
    private final static int CARD_INDEX = 1;
    private final static int INSERT_INDEX = 3;
    private final static int HAND_SIZE = 5;
    private final static int ADD = 0;

    private Game game;
    private UI ui;
    private Player currentPlayer;
    private ExplodingKittenCard explodingKittenCard;

    @BeforeEach
    public void setUp() {
        game = EasyMock.createMock(Game.class);
        ui = EasyMock.createMock(UI.class);
        currentPlayer = EasyMock.createMock(Player.class);
        explodingKittenCard = new ExplodingKittenCard();
    }

    @Test
    public void testPlayCard_givenNoDefuse_whenExplodingKittenPlayed_thenCurrentPlayerEliminated() {
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();
        List<Player> players = new ArrayList<>();
        players.add(EasyMock.createMock(Player.class));
        players.add(EasyMock.createMock(Player.class));
        players.add(currentPlayer);
        int currentPlayerID = 1;

        ui.displayMessage("explodingKitten");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasCard("Defuse")).andReturn(-1).times(2);
        EasyMock.expect(currentPlayer.hasCard("Streaking Kitten")).andReturn(-1).once();
        EasyMock.expect(currentPlayer.getId()).andReturn(PLAYER_ID).once();
        game.deletePlayer(PLAYER_ID);
        EasyMock.expectLastCall().once();
        ui.displayMessage("playerEliminated");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getPlayers()).andReturn(players).once();

        game.nextPlayer();
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(game, ui, currentPlayer);

        explodingKittenCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_givenDefuseAndNoStreaking_whenExplodingKittenPlayed_ThenExplodingKittenIsAddedBackToDeck() {
        Deck deck = EasyMock.createMock(Deck.class);
        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();

        ui.displayMessage("explodingKitten");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasCard("Defuse")).andReturn(CARD_INDEX).times(3);
        game.removeCurrentPlayerCard(CARD_INDEX);
        EasyMock.expectLastCall().once();
        ui.displayMessage("defuseCard");
        EasyMock.expectLastCall().once();
        EasyMock.expect(currentPlayer.hasCard("Streaking Kitten")).andReturn(-1).once();
        EasyMock.expect(game.getDeck()).andReturn(deck).once();
        EasyMock.expect(ui.promptPlayer("whereToInsert")).andReturn(INSERT_INDEX).once();
        deck.insertCardAtIndex(EasyMock.anyObject(ExplodingKittenCard.class), EasyMock.eq(INSERT_INDEX));
        EasyMock.expectLastCall().once();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        ui.displayMessage("insertedCard");
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, currentPlayer, deck);

        explodingKittenCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer, deck);
    }

    @Test
    public void testPlayCard_playerChoosesToKeep_GivenTheyHaveDefuseAndStreaking_WhenExplodingKittenPlayed() {
        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();

        ui.displayMessage("explodingKitten");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasCard("Defuse")).andReturn(CARD_INDEX).times(1);
        EasyMock.expect(currentPlayer.hasCard("Streaking Kitten")).andReturn(CARD_INDEX).times(1);
        EasyMock.expect(ui.promptPlayer("keepOrAddExploding")).andReturn(1).once();
        game.addToCurrentPlayer(EasyMock.anyObject(ExplodingKittenCard.class));
        EasyMock.expectLastCall();
        ui.displayMessage("addExplodingKitten");
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, currentPlayer);

        explodingKittenCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_playerChoosesToAddToDeck_GivenTheyHaveDefuseAndStreaking_WhenExplodingKittenPlayed() {
        Deck deck = EasyMock.createMock(Deck.class);

        ui.displayMessage("explodingKitten");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(currentPlayer.hasCard("Defuse")).andReturn(PLAYER_ID).times(2);
        game.removeCurrentPlayerCard(CARD_INDEX);
        EasyMock.expectLastCall().once();
        ui.displayMessage("defuseCard");
        EasyMock.expectLastCall();
        EasyMock.expect(currentPlayer.hasCard("Streaking Kitten")).andReturn(CARD_INDEX);
        EasyMock.expect(ui.promptPlayer("keepOrAddExploding")).andReturn(ADD);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(ui.promptPlayer("whereToInsert")).andReturn(INSERT_INDEX);
        deck.insertCardAtIndex(EasyMock.anyObject(ExplodingKittenCard.class), EasyMock.eq(INSERT_INDEX));
        EasyMock.expectLastCall();
        ui.displayMessage("insertedCard");
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, currentPlayer, deck);

        explodingKittenCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer, deck);

    }

    @Test
    public void testPlayCard_givenStreakingAndNoDefuse_whenExplodingKittenPlayed_thenExplodingKittenIsAddedToHand() {
        Deck deck = EasyMock.createMock(Deck.class);
        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();

        ui.displayMessage("explodingKitten");
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).once();
        EasyMock.expect(currentPlayer.hasCard("Defuse")).andReturn(-1).times(2);
        EasyMock.expect(currentPlayer.hasCard("Streaking Kitten")).andReturn(CARD_INDEX).once();
        ui.displayMessage("addExplodingKitten");
        EasyMock.expectLastCall().once();
        game.addToCurrentPlayer(EasyMock.anyObject(ExplodingKittenCard.class));
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, currentPlayer, deck);

        explodingKittenCard.playCard(game, ui);
        EasyMock.verify(game, ui, currentPlayer, deck);
    }
}