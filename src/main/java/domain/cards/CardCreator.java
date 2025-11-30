package domain.cards;

import domain.cards.implementations.*;

public class CardCreator {

    public Card createCatCard(CatCard.CatCardType type) {
        return new CatCard(type);
    }

    public Card createFeralCatCard() {
        return new FeralCatCard();
    }

    public Card createNopeCard() {
        return new NopeCard();
    }

    public Card createAttackCard() {
        return new AttackCard();
    }

    public Card createTargetedAttackCard() {
        return new TargetedAttackCard();
    }

    public Card createShuffleCard() {
        return new ShuffleCard();
    }

    public Card createFavorCard() {
        return new FavorCard();
    }

    public Card createDrawFromTheBottomCard() {
        return new DrawFromTheBottomCard();
    }

    public Card createSkipCard() {
        return new SkipCard();
    }

    public Card createSeeTheFutureCard(SeeTheFutureCard.PeekOption peekOption) {
        return new SeeTheFutureCard(peekOption);
    }

    public Card createAlterTheFutureCard(AlterTheFutureCard.PeekOption peekOption) {
        return new AlterTheFutureCard(peekOption);
    }

    public Card createStreakingKittenCard() {
        return new StreakingKittenCard();
    }

    public Card createSuperSkipCard() {
        return new SuperSkipCard();
    }

    public Card createCatomicBombCard() {
        return new CatomicBombCard();
    }

    public Card createGarbageCollectionCard() {
        return new GarbageCollectionCard();
    }

    public Card createCurseOfTheCatButtCard() {
        return new CurseOfTheCatButtCard();
    }

    public Card createSwapTopAndBottomCard() {
        return new SwapTopAndBottomCard();
    }

    public Card createMarkCard() {
        return new MarkCard();
    }

    public Card createReverseCard() {
        return new ReverseCard();
    }

    public Card createExplodingKittenCard() {
        return new ExplodingKittenCard();
    }

    public Card createImplodingKittenCard(ImplodingKittenCard.DrawnBefore drawnBefore) {
        return new ImplodingKittenCard(drawnBefore);
    }

    public Card createDefuseCard() {
        return new DefuseCard();
    }
}