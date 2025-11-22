package domain;

import ui.UI;

public class GameController {
    private static final int ENGLISH = 0;
    private static final int FRENCH = 1;

    private final UI ui;
    private Game game;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public GameController(UI ui) {
        this.ui = ui;
    }

    public void startGame() {
        ui.displayMessage("welcomeMessage");
        int language = -1;
        while( language != FRENCH && language != ENGLISH ) {
            language = ui.promptPlayer("language");
        }

        if (language == FRENCH) {
            ui.changeLanguage("fr", "Fr");
        }


        int numberOfPlayers = promptNumberOfPlayers();

        if (this.game == null) {
            this.game = new Game(numberOfPlayers, ui);
        }

        while(!game.isGameOver()) {
            game.takeTurn();
        }

        ui.displayMessage("gameOver");
        Player winner = game.getWinner();
        if (winner != null) {
            ui.displayFormattedMessage("winner", winner.getId());
        }
    }

    private int promptNumberOfPlayers() {
        int numberOfPlayers;
        while (true) {
            numberOfPlayers = ui.promptPlayer("enterNumPlayers");
            if (numberOfPlayers < 2 || numberOfPlayers > 10) {
                ui.displayMessage("invalidNumPlayers");
            } else {
                return numberOfPlayers;
            }
        }
    }

    public void setGame(Game game) {
        this.game = new Game(game);
    }
}