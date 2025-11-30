package domain.deck;

import domain.cards.Card;
import domain.cards.CardCreator;
import domain.cards.ExpansionPack;
import domain.cards.implementations.AlterTheFutureCard;
import domain.cards.implementations.AttackCard;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.FavorCard;
import domain.cards.implementations.ImplodingKittenCard;
import domain.cards.implementations.NopeCard;
import domain.cards.implementations.SeeTheFutureCard;
import domain.cards.implementations.ShuffleCard;
import domain.cards.implementations.SkipCard;

import java.util.Set;

public class DeckCreator {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS_BASE = 5;
    private static final int MAX_PLAYERS_PARTY = 10;
    
    private static final int BASE_CAT_CARD_COUNT = 4;
    private static final int BASE_NOPE_CARD_COUNT = 5;
    private static final int BASE_ATTACK_CARD_COUNT = 4;
    private static final int BASE_SHUFFLE_CARD_COUNT = 4;
    private static final int BASE_FAVOR_CARD_COUNT = 4;
    private static final int BASE_SKIP_CARD_COUNT = 4;
    private static final int BASE_SEE_FUTURE_CARD_COUNT = 5;
    
    private static final int STREAKING_KITTEN_COUNT = 1;
    private static final int SUPER_SKIP_COUNT = 1;
    private static final int SEE_FUTURE_FIVE_COUNT = 1;
    private static final int ALTER_FUTURE_FIVE_COUNT = 1;
    private static final int SWAP_TOP_BOTTOM_COUNT = 3;
    private static final int GARBAGE_COLLECTION_COUNT = 1;
    private static final int CATOMIC_BOMB_COUNT = 1;
    private static final int MARK_CARD_COUNT = 3;
    private static final int CURSE_CAT_BUTT_COUNT = 2;
    
    private static final int REVERSE_CARD_COUNT = 4;
    private static final int DRAW_BOTTOM_CARD_COUNT = 4;
    private static final int FERAL_CAT_COUNT = 4;
    private static final int ALTER_FUTURE_THREE_COUNT = 3;
    private static final int TARGETED_ATTACK_COUNT = 2;
    
    private static final int IMPLODING_KITTEN_COUNT = 1;
    private static final int BASE_DEFUSE_COUNT = 6;
    private static final int PARTY_DEFUSE_OFFSET = 2;
    private static final int STREAKING_KITTEN_EXTRA = 1;
    
    private static final int SMALL_GAME_MAX = 3;
    private static final int MEDIUM_GAME_MAX = 7;
    private static final int SMALL_GAME_INDEX = 0;
    private static final int MEDIUM_GAME_INDEX = 1;
    private static final int LARGE_GAME_INDEX = 2;

    private final CardCreator cardCreator;

    public DeckCreator() {
        this.cardCreator = new CardCreator();
    }

    public DeckCreator(CardCreator cardCreator) {
        this.cardCreator = cardCreator;
    }

    public Deck createDeck(int playerCount, Set<ExpansionPack> expansionPacks) {
        Deck deck = new Deck();
        
        boolean hasPartyPack = expansionPacks.contains(ExpansionPack.PARTY_PACK);
        boolean hasStreakingKittens = expansionPacks.contains(ExpansionPack.STREAKING_KITTENS);
        boolean hasImplodingKittens = expansionPacks.contains(ExpansionPack.IMPLODING_KITTENS);
        
        int maxPlayers = hasPartyPack ? MAX_PLAYERS_PARTY : MAX_PLAYERS_BASE;
        if (playerCount < MIN_PLAYERS || playerCount > maxPlayers) {
            throw new IllegalArgumentException("Invalid player count for selected expansions");
        }
        
        if (hasPartyPack) {
            createPartyPackDeck(deck, playerCount);
        } else {
            createBaseGameDeck(deck);
        }
        
        if (hasStreakingKittens) {
            addStreakingKittensExpansion(deck);
        }
        
        if (hasImplodingKittens) {
            addImplodingKittensExpansion(deck);
        }
        
        return deck;
    }

    private void createBaseGameDeck(Deck deck) {
        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, cardCreator.createCatCard(catCardType), BASE_CAT_CARD_COUNT);
        }

        addCards(deck, cardCreator.createNopeCard(), BASE_NOPE_CARD_COUNT);
        addCards(deck, cardCreator.createAttackCard(), BASE_ATTACK_CARD_COUNT);
        addCards(deck, cardCreator.createShuffleCard(), BASE_SHUFFLE_CARD_COUNT);
        addCards(deck, cardCreator.createFavorCard(), BASE_FAVOR_CARD_COUNT);
        addCards(deck, cardCreator.createSkipCard(), BASE_SKIP_CARD_COUNT);
        addCards(deck, cardCreator.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), BASE_SEE_FUTURE_CARD_COUNT);        
    }

    private void createPartyPackDeck(Deck deck, int playerCount) {
        int countIndex = computeCountIndex(playerCount);

        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, cardCreator.createCatCard(catCardType), CatCard.getCounts()[countIndex]);
        }

        addCards(deck, cardCreator.createNopeCard(), NopeCard.getCounts()[countIndex]);
        addCards(deck, cardCreator.createAttackCard(), AttackCard.getCounts()[countIndex]);
        addCards(deck, cardCreator.createShuffleCard(), ShuffleCard.getCounts()[countIndex]);
        addCards(deck, cardCreator.createFavorCard(), FavorCard.getCounts()[countIndex]);
        addCards(deck, cardCreator.createSkipCard(), SkipCard.getCounts()[countIndex]);
        addCards(deck, cardCreator.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), 
                SeeTheFutureCard.getCounts()[countIndex]);
    }

    private void addStreakingKittensExpansion(Deck deck) {
        addCards(deck, cardCreator.createStreakingKittenCard(), STREAKING_KITTEN_COUNT);
        addCards(deck, cardCreator.createSuperSkipCard(), SUPER_SKIP_COUNT);
        addCards(deck, cardCreator.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE), SEE_FUTURE_FIVE_COUNT);
        addCards(deck, cardCreator.createAlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE), ALTER_FUTURE_FIVE_COUNT);
        addCards(deck, cardCreator.createSwapTopAndBottomCard(), SWAP_TOP_BOTTOM_COUNT);
        addCards(deck, cardCreator.createGarbageCollectionCard(), GARBAGE_COLLECTION_COUNT);
        addCards(deck, cardCreator.createCatomicBombCard(), CATOMIC_BOMB_COUNT);
        addCards(deck, cardCreator.createMarkCard(), MARK_CARD_COUNT);
        addCards(deck, cardCreator.createCurseOfTheCatButtCard(), CURSE_CAT_BUTT_COUNT);
    }

    private void addImplodingKittensExpansion(Deck deck) {
        addCards(deck, cardCreator.createReverseCard(), REVERSE_CARD_COUNT);
        addCards(deck, cardCreator.createDrawFromTheBottomCard(), DRAW_BOTTOM_CARD_COUNT);
        addCards(deck, cardCreator.createFeralCatCard(), FERAL_CAT_COUNT);
        addCards(deck, cardCreator.createAlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE), ALTER_FUTURE_THREE_COUNT);
        addCards(deck, cardCreator.createTargetedAttackCard(), TARGETED_ATTACK_COUNT);
    }

    public int getExplodingKittenCount(int playerCount, boolean hasStreakingKittens) {
        int baseCount = playerCount - 1;
        return hasStreakingKittens ? baseCount + STREAKING_KITTEN_EXTRA : baseCount;
    }

    public int getDefuseCardCount(int playerCount, boolean hasPartyPack) {
        if (hasPartyPack) {
            return Math.max(BASE_DEFUSE_COUNT, playerCount + PARTY_DEFUSE_OFFSET);
        }
        return BASE_DEFUSE_COUNT;
    }

    public void addExplodingKittens(Deck deck, int count) {
        addCards(deck, cardCreator.createExplodingKittenCard(), count);
    }

    public void addImplodingKitten(Deck deck) {
        addCards(deck, cardCreator.createImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN), IMPLODING_KITTEN_COUNT);
    }

    public void addRemainingCards(Deck deck, int numberOfPlayers, Set<ExpansionPack> expansionPacks) {
        boolean hasPartyPack = expansionPacks.contains(ExpansionPack.PARTY_PACK);
        boolean hasStreakingKittens = expansionPacks.contains(ExpansionPack.STREAKING_KITTENS);
        boolean hasImplodingKittens = expansionPacks.contains(ExpansionPack.IMPLODING_KITTENS);

        int totalDefuseCards = getDefuseCardCount(numberOfPlayers, hasPartyPack);
        int remainingDefuseCards = totalDefuseCards - numberOfPlayers;
        addCards(deck, cardCreator.createDefuseCard(), remainingDefuseCards);

        int explodingKittenCount = getExplodingKittenCount(numberOfPlayers, hasStreakingKittens);
        addExplodingKittens(deck, explodingKittenCount);

        if (hasImplodingKittens) {
            addImplodingKitten(deck);
        }

        deck.shuffle();
    }

    private void addCards(Deck deck, Card card, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(card);
        }
    }

    private int computeCountIndex(int playerCount) {
        if (playerCount <= SMALL_GAME_MAX) {
            return SMALL_GAME_INDEX;
        } else if (playerCount <= MEDIUM_GAME_MAX) {
            return MEDIUM_GAME_INDEX;
        } else {
            return LARGE_GAME_INDEX;
        }
    }
}