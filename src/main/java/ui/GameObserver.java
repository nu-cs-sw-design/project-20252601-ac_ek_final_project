package ui;

public interface GameObserver {
    void displayMessage(String key);
    void displayFormattedMessage(String key, Object... args);
    void clearScreen();
    void changeLanguage(String language, String country);
}
