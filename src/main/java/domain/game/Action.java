package domain.game;

public class Action {
    public enum Type {
        PASS,
        NOPE,
        PLAY_CARD
    }

    private final Type type;
    private final int cardIndex;

    private Action(Type type, int cardIndex) {
        this.type = type;
        this.cardIndex = cardIndex;
    }

    public static Action pass() {
        return new Action(Type.PASS, -1);
    }

    public static Action nope() {
        return new Action(Type.NOPE, -1);
    }

    public static Action playCard(int cardIndex) {
        return new Action(Type.PLAY_CARD, cardIndex);
    }

    public Type getType() {
        return type;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}