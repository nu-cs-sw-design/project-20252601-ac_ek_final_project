package domain.cards.expansions;

import domain.cards.Card;
import domain.cards.implementations.*;
import domain.deck.Deck;

public class ImplodingKittensExpansion implements ExpansionStrategy {    
    private static final int REVERSE_CARD_COUNT = 4;
    private static final int DRAW_BOTTOM_CARD_COUNT = 4;
    private static final int FERAL_CAT_COUNT = 4;
    private static final int ALTER_FUTURE_THREE_COUNT = 3;
    private static final int TARGETED_ATTACK_COUNT = 2;

    @Override
    public String getId() {
        return "imploding_kittens";
    }

    @Override
    public String getDisplayName() {
        return "Imploding Kittens";
    }

    @Override
    public int getSelectionNumber() {
        return 3;
    }

    @Override
    public void addCardsToDeck(Deck deck, int playerCount) {
        addCards(deck, ReverseCard::new, REVERSE_CARD_COUNT);
        addCards(deck, DrawFromTheBottomCard::new, DRAW_BOTTOM_CARD_COUNT);
        addCards(deck, FeralCatCard::new, FERAL_CAT_COUNT);
        addCards(deck, () -> new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE), ALTER_FUTURE_THREE_COUNT);
        addCards(deck, TargetedAttackCard::new, TARGETED_ATTACK_COUNT);
    }

    @Override
    public void addPostDealCards(Deck deck, int playerCount) {
        deck.addCard(new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN));
    }

    private void addCards(Deck deck, java.util.function.Supplier<Card> cardFactory, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(cardFactory.get());
        }
    }
}