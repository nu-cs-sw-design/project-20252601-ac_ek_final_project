package ui;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UI {
    private Locale locale;
    private ResourceBundle resourceBundle;

    public UI() {
        this.locale = new Locale("en", "US");
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public int promptPlayer(String key) {
        String message = this.resourceBundle.getString(key);
        Scanner scanner = new Scanner(System.in, "UTF-8");
        int userInput = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.println(message);
                userInput = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input - please enter an integer");
                scanner.nextLine();
            }
        }
        return userInput;
    }

    public void displayMessage(String key) {
        String message = this.resourceBundle.getString(key);
        System.out.println(message);
    }

    public void changeLanguage(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public void displayFormattedMessage(String key, Object... args) {
        String pattern = resourceBundle.getString(key);
        String message = MessageFormat.format(pattern, args);
        System.out.println(message);
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Fallback: print multiple newlines if clearing fails
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}