package domain;

import domain.Cards.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTests {
    @Test
    public void testGetName_alterTheFuture(){
        Card card = new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE);
        String expected = "Alter The Future";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_attack() {
        Card card = new AttackCard();
        String expected = "Attack";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_bearded() {
        Card card = new CatCard(CatCard.CatCardType.BEARDED_CAT);
        String expected = "Bearded Cat";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_hairyPotato() {
        Card card = new CatCard(CatCard.CatCardType.HAIRY_POTATO_CAT);
        String expected = "Hairy Potato Cat";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_rainbowRalphing() {
        Card card = new CatCard(CatCard.CatCardType.RAINBOW_RALPHING_CAT);
        String expected = "Rainbow Ralphing Cat";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_taco() {
        Card card = new CatCard(CatCard.CatCardType.TACO_CAT);
        String expected = "Taco Cat";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_cattermelon() {
        Card card = new CatCard(CatCard.CatCardType.CATTERMELON);
        String expected = "Cattermelon";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_catomicBomb() {
        Card card = new CatomicBombCard();
        String expected = "Catomic Bomb";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_curseCatButt() {
        Card card = new CurseOfTheCatButtCard();
        String expected = "Curse of the Cat Butt";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_defuse() {
        DefuseCard defuseCard = new DefuseCard();
        assertEquals(defuseCard.getName(), "Defuse");
    }

    @Test
    public void testGetName_drawFromBottom() {
        Card card = new DrawFromTheBottomCard();
        String expected = "Draw From The Bottom";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_explodingKitten() {
        Card card = new ExplodingKittenCard();
        String expected = "Exploding Kitten";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_favor() {
        Card card = new FavorCard();
        String expected = "Favor";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_feralCat() {
        Card card = new FeralCatCard();
        String expected = "Feral Cat";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_garbage() {
        Card card = new GarbageCollectionCard();
        String expected = "Garbage Collection";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_implodingKitten() {
        Card card = new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.DRAWN);
        String expected = "Imploding Kitten";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_mark() {
        Card card = new MarkCard();
        String expected = "Mark";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_nope() {
        Card card = new NopeCard();
        String expected = "Nope";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_reverse() {
        Card card = new ReverseCard();
        String expected = "Reverse";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_seeFuture() {
        Card card = new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE);
        String expected = "See The Future";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_shuffle() {
        Card card = new ShuffleCard();
        String expected = "Shuffle";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_skip() {
        Card card = new SkipCard();
        String expected = "Skip";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_streakingKitten() {
        StreakingKittenCard streakingKittenCard = new StreakingKittenCard();
        assertEquals("Streaking Kitten", streakingKittenCard.getName());
    }

    @Test
    public void testGetName_superSkip() {
        Card card = new SuperSkipCard();
        String expected = "Super Skip";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_swapTopBottom() {
        Card card = new SwapTopAndBottomCard();
        String expected = "Swap Top and Bottom";
        assertEquals(expected, card.getName());
    }

    @Test
    public void testGetName_targetedAttack() {
        Card card = new TargetedAttackCard();
        String expected = "Targeted Attack";
        assertEquals(expected, card.getName());
    }
}