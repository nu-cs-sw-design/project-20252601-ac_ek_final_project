package domain.model.cards.special;

import domain.model.GameContext;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class DefuseCard extends Card{
    public DefuseCard() {
        super(new DefuseCardEffect());
    }

    @Override
    public String getName() {
        return "Defuse";
    }

    private static class DefuseCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            throw new UnsupportedOperationException("cantPlayCard");
        }
    }
}