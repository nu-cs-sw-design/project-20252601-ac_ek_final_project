package domain.game;

import domain.deck.Deck;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck;
    private List<Player> players;
    private boolean isGameOver;

    public Game() {
        this.players = new ArrayList<>();
        this.isGameOver = false;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}