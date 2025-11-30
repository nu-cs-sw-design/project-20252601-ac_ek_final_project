package domain.deck;

import domain.cards.Card;

import java.util.List;

public class Deck {
    private DrawPile drawPile;

    public Deck() {
        this.drawPile = new DrawPile();
    }

    public Deck(Deck other) {
        this.drawPile = new DrawPile(other.drawPile.getAll());
    }

    public int numberOfCards() {
        return drawPile.size();
    }

    public void addCard(Card card) {
        drawPile.add(card);
    }

    public Card drawTopCard() {
        return drawPile.removeTop();
    }

    public Card drawBottomCard() {
        return drawPile.removeBottom();
    }

    public void shuffle() {
        drawPile.shuffle();
    }

    public boolean isEmpty() {
        return drawPile.isEmpty();
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    public List<Card> getAllCards() {
        return drawPile.getAll();
    }
}