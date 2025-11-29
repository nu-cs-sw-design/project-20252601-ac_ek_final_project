package application;

import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();

        GameController gameController = new GameController(ui, ui);

        gameController.startGame();
    }
}