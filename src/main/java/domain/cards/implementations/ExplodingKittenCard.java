package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class ExplodingKittenCard extends Card {
    private static final int ADD_TO_DECK = 0;
    private static final int NOT_FOUND = -1;

    public ExplodingKittenCard() {
        super();
    }

    @Override
    public String getName() {
        return "Exploding Kitten";
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("explodingKitten");
        Player currentPlayer = context.getCurrentPlayer();
        PlayerManager ps = context.getPlayerManager();

        boolean hasDefuse = ps.hasCard(currentPlayer, "Defuse") != NOT_FOUND;
        boolean hasStreakingKitten = ps.hasCard(currentPlayer, "Streaking Kitten") != NOT_FOUND;
        boolean alreadyHasExplodingKitten = ps.hasCard(currentPlayer, "Exploding Kitten") != NOT_FOUND;

        if (hasDefuse && hasStreakingKitten && !alreadyHasExplodingKitten) {
            int choice = context.promptPlayer("keepOrAddExploding");
            if (choice == ADD_TO_DECK) {
                defuseAndInsert(context, ps, currentPlayer);
            } else {
                context.addToCurrentPlayer(new ExplodingKittenCard());
                context.displayMessage("addExplodingKitten");
            }
        } else if (hasDefuse) {
            defuseAndInsert(context, ps, currentPlayer);
        } else if (hasStreakingKitten && !alreadyHasExplodingKitten) {
            context.addToCurrentPlayer(new ExplodingKittenCard());
            context.displayMessage("addExplodingKitten");
        } else {
            CardManager cm = context.getCardManager();
            cm.eliminatePlayer(context, currentPlayer);
        }
    }

    private void defuseAndInsert(GameContext context, PlayerManager ps, Player currentPlayer) {
        int defuseIndex = ps.hasCard(currentPlayer, "Defuse");
        context.removeCurrentPlayerCard(defuseIndex);
        context.displayMessage("defuseCard");
        CardManager cm = context.getCardManager();
        cm.insertCardAtValidIndex(context, new ExplodingKittenCard());
    }
}