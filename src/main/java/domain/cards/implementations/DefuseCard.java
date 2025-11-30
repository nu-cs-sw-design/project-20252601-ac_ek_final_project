package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;

public class DefuseCard extends Card {

    public DefuseCard() {
        super();
    }

    @Override
    public String getName() {
        return "Defuse";
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("defuseCard");

        CardManager cm = context.getCardManager();
        cm.insertCardAtValidIndex(context, new ExplodingKittenCard());
        cm.removeCardFromCurrentPlayer(context, "Defuse");
    }
}