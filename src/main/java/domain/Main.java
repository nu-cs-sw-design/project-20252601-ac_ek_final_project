package domain;

import ui.UI;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();

        GameController gameController = new GameController(ui);

        gameController.startGame();
    }
}