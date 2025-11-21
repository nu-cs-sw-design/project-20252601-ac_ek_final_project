package domain.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Cards.Card;
import domain.Cards.ExplodingKittenCard;
import domain.Cards.StreakingKittenCard;
import domain.Game;
import domain.Player;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.UI;

public class ExplodingKittenITest {

    private static final int NUM_PLAYERS = 4;

    private UI ui;
    private Game game;


    @BeforeEach
    public void setUp() {
        ui = new UI();
        game = new Game(NUM_PLAYERS, ui);
    }

    @Test
    public void playerDoesNotHaveDefuseOrStreaking_ThenPlayerRemoved() {
        Player curPlayer = game.getCurrentPlayer();
        ArrayList<Card> newHand = removeCard("Defuse", curPlayer.getHand());
        newHand = removeCard("Streaking Kitten", newHand);
        curPlayer.setHand(newHand);
        game.setCurrentPlayer(curPlayer);

        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();
        explodingKittenCard.playCard(game, ui);
        assertEquals(game.getPlayers().size(), NUM_PLAYERS - 1);
    }

    @Test
    public void playerHasDefuse_ThenDefuseRemovedAndExplodingKittenReInserted() {
        Player curPlayer = game.getCurrentPlayer();
        ArrayList<Card> newHand = removeCard("Streaking Kitten", curPlayer.getHand());
        curPlayer.setHand(newHand);
        game.setCurrentPlayer(curPlayer);
        int expectedNewHandSize = game.getCurrentPlayer().getHand().size() - 1;

        String index = "0";
        System.setIn(new ByteArrayInputStream(index.getBytes(StandardCharsets.UTF_8)));

        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();
        explodingKittenCard.playCard(game, ui);
        assertEquals(expectedNewHandSize , game.getCurrentPlayer().getHand().size());
    }

    @Test
    public void playerHasStreaking_ThenExplodingKittenAddedToHand() {
        Player curPlayer = game.getCurrentPlayer();
        ArrayList<Card> newHand = removeCard("Defuse", curPlayer.getHand());
        curPlayer.setHand(newHand);
        curPlayer.addCard(new StreakingKittenCard());
        game.setCurrentPlayer(curPlayer);
        int expectedNewHandSize = game.getCurrentPlayer().getHand().size() + 1;

        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();
        explodingKittenCard.playCard(game, ui);
        assertEquals(expectedNewHandSize , game.getCurrentPlayer().getHand().size());
    }

    @Test
    public void playerHasChoiceAndChoosesAddToDeck_ThenExplodingKittenAddedToHand() {
        Player curPlayer = game.getCurrentPlayer();
        curPlayer.addCard(new StreakingKittenCard());
        game.setCurrentPlayer(curPlayer);
        int expectedNewHandSize = game.getCurrentPlayer().getHand().size() + 1;

        String choice = "1";
        System.setIn(new ByteArrayInputStream(choice.getBytes(StandardCharsets.UTF_8)));

        ExplodingKittenCard explodingKittenCard = new ExplodingKittenCard();
        explodingKittenCard.playCard(game, ui);
        assertEquals(expectedNewHandSize , game.getCurrentPlayer().getHand().size());
    }


    private ArrayList<Card> removeCard(String card, ArrayList<Card> hand) {
        Iterator<Card> iterator = hand.iterator();
        while(iterator.hasNext()) {
            Card cardInHand = iterator.next();
            if(cardInHand.getName().equals(card)) {
                iterator.remove();
            }
        }

        return hand;
    }
}
