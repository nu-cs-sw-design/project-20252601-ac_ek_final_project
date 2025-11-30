package domain.cards.expansions;

import domain.cards.Card;
import domain.cards.implementations.*;
import domain.deck.Deck;

public class StreakingKittensExpansion implements ExpansionStrategy {    
    private static final int STREAKING_KITTEN_COUNT = 1;
    private static final int SUPER_SKIP_COUNT = 1;
    private static final int SEE_FUTURE_FIVE_COUNT = 1;
    private static final int ALTER_FUTURE_FIVE_COUNT = 1;
    private static final int SWAP_TOP_BOTTOM_COUNT = 3;
    private static final int GARBAGE_COLLECTION_COUNT = 1;
    private static final int CATOMIC_BOMB_COUNT = 1;
    private static final int MARK_CARD_COUNT = 3;
    private static final int CURSE_CAT_BUTT_COUNT = 2;
    private static final int ADDITIONAL_EXPLODING_KITTENS = 1;

    @Override
    public String getId() {
        return "streaking_kittens";
    }

    @Override
    public String getDisplayName() {
        return "Streaking Kittens";
    }

    @Override
    public int getSelectionNumber() {
        return 2;
    }

    @Override
    public void addCardsToDeck(Deck deck, int playerCount) {
        addCards(deck, StreakingKittenCard::new, STREAKING_KITTEN_COUNT);
        addCards(deck, SuperSkipCard::new, SUPER_SKIP_COUNT);
        addCards(deck, () -> new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE), SEE_FUTURE_FIVE_COUNT);
        addCards(deck, () -> new AlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE), ALTER_FUTURE_FIVE_COUNT);
        addCards(deck, SwapTopAndBottomCard::new, SWAP_TOP_BOTTOM_COUNT);
        addCards(deck, GarbageCollectionCard::new, GARBAGE_COLLECTION_COUNT);
        addCards(deck, CatomicBombCard::new, CATOMIC_BOMB_COUNT);
        addCards(deck, MarkCard::new, MARK_CARD_COUNT);
        addCards(deck, CurseOfTheCatButtCard::new, CURSE_CAT_BUTT_COUNT);
    }

    @Override
    public int getAdditionalExplodingKittens() {
        return ADDITIONAL_EXPLODING_KITTENS;
    }

    private void addCards(Deck deck, java.util.function.Supplier<Card> cardFactory, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(cardFactory.get());
        }
    }
}