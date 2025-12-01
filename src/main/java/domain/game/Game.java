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

    public Game(Game other) {
        if (other == null) {
            this.players = new ArrayList<>();
            this.isGameOver = false;
            this.deck = null;
            return;
        }
        this.isGameOver = other.isGameOver;
        this.deck = other.deck == null ? null : new Deck(other.deck);
        this.players = new ArrayList<>();
        if (other.players != null) {
            for (Player p : other.players) {
                this.players.add(new Player(p));
            }
        }
    }

    public Deck getDeck() {
        return deck == null ? null : new Deck(deck);
    }

    public void setDeck(Deck deck) {
        this.deck = deck == null ? null : new Deck(deck);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void setPlayers(List<Player> players) {
        if (players == null) {
            this.players = new ArrayList<>();
            return;
        }
        this.players = new ArrayList<>();
        for (Player p : players) {
            this.players.add(new Player(p));
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}