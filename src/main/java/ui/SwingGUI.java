package ui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SwingGUI implements GameUI {    
    private JFrame mainFrame;
    private JTextArea messageArea;
    private JPanel buttonPanel;
    
    private Locale locale;
    private ResourceBundle resourceBundle;
    
    private final BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>();
    
    public SwingGUI() {
        this.locale = new Locale("en", "US");
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }
    
    @Override
    public void initialize() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void createAndShowGUI() {
        mainFrame = new JFrame("Exploding Kittens");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1024, 768);
        mainFrame.setLayout(new BorderLayout());
        
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        messageArea.setBackground(new Color(40, 44, 52));
        messageArea.setForeground(new Color(171, 178, 191));
        JScrollPane scrollPane = new JScrollPane(messageArea);
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(33, 37, 43));
        buttonPanel.setPreferredSize(new Dimension(1024, 100));
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);
        
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    @Override
    public void shutdown() {
        if (mainFrame != null) {
            SwingUtilities.invokeLater(() -> mainFrame.dispose());
        }
    }
    
    @Override
    public void displayMessage(String key) {
        String message = resourceBundle.getString(key);
        appendMessage(message);
    }
    
    @Override
    public void displayFormattedMessage(String key, Object... args) {
        String pattern = resourceBundle.getString(key);
        String message = java.text.MessageFormat.format(pattern, args);
        appendMessage(message);
    }
    
    private void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n");
            messageArea.setCaretPosition(messageArea.getDocument().getLength());
        });
    }
    
    @Override
    public void clearScreen() {
        SwingUtilities.invokeLater(() -> {
            messageArea.setText("");
        });
    }
    
    private JButton createStyledButton(String text, int value) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(97, 175, 239));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addActionListener(e -> {
            inputQueue.offer(value);
        });
        
        return button;
    }
    
    private int showButtonsAndWait(String prompt, String[] buttonLabels, int[] buttonValues) {
        appendMessage(prompt);
        
        inputQueue.clear();
        
        SwingUtilities.invokeLater(() -> {
            buttonPanel.removeAll();
            for (int i = 0; i < buttonLabels.length; i++) {
                JButton btn = createStyledButton(buttonLabels[i], buttonValues[i]);
                buttonPanel.add(btn);
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        } finally {
            SwingUtilities.invokeLater(() -> {
                buttonPanel.removeAll();
                buttonPanel.revalidate();
                buttonPanel.repaint();
            });
        }
    }
    
    private int showNumberButtons(String prompt, int min, int max) {
        int count = max - min + 1;
        String[] labels = new String[count];
        int[] values = new int[count];
        
        for (int i = 0; i < count; i++) {
            int num = min + i;
            labels[i] = String.valueOf(num);
            values[i] = num;
        }
        
        return showButtonsAndWait(prompt, labels, values);
    }
    
    @Override
    public int promptPlayer(String promptKey) {
        String message = resourceBundle.getString(promptKey);
        
        if (promptKey.equals("playCardPrompt") || promptKey.equals("chooseNope")) {
            return showButtonsAndWait(message,
                new String[]{"Yes", "No"},
                new int[]{1, 0});
        } else if (promptKey.equals("keepOrAddExploding")) {
            return showButtonsAndWait(message,
                new String[]{"Add to Deck", "Keep in Hand"},
                new int[]{0, 1});
        } else if (promptKey.equals("language")) {
            return showButtonsAndWait(message,
                new String[]{"English", "Français"},
                new int[]{0, 1});
        } else if (promptKey.equals("enterNumHumanPlayers")) {
            return showNumberButtons(message, 0, 10);
        } else if (promptKey.equals("enterNumAIPlayers")) {
            return showNumberButtons(message, 0, 10);
        } else if (promptKey.equals("chooseCardPrompt") || promptKey.equals("discard") 
                || promptKey.equals("takeCard") || promptKey.equals("chosenPlayerCardIndex")
                || promptKey.equals("targetCardIndex")) {
            return showNumberButtons(message, 0, 9);
        } else if (promptKey.equals("whereToInsert")) {
            return showNumberButtons(message, 0, 20);
        } else if (promptKey.equals("targetPlayerId") || promptKey.equals("playerID") 
                || promptKey.equals("playerIDCurse")) {
            return showNumberButtons(message, 1, 10);
        } else if (promptKey.equals("newIndex")) {
            return showNumberButtons(message, 0, 9);
        } else {
            return showNumberButtons(message, 0, 10);
        }
    }
    
    @Override
    public Set<Integer> promptExpansionPackNumbers() {
        Set<Integer> selections = new HashSet<>();
        
        appendMessage(resourceBundle.getString("expansionPackPrompt"));
        
        inputQueue.clear();
        
        JToggleButton[] toggles = new JToggleButton[3];
        String[] expansionNames = {"Party Pack", "Streaking Kittens", "Imploding Kittens"};
        
        SwingUtilities.invokeLater(() -> {
            buttonPanel.removeAll();
            
            for (int i = 0; i < 3; i++) {
                final int expansionNum = i + 1;
                JToggleButton toggle = new JToggleButton(expansionNames[i]);
                toggle.setFont(new Font("Arial", Font.BOLD, 12));
                toggle.setPreferredSize(new Dimension(140, 50));
                toggle.setBackground(new Color(80, 80, 80));
                toggle.setForeground(Color.WHITE);
                toggle.setFocusPainted(false);
                toggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
                toggles[i] = toggle;
                
                toggle.addActionListener(e -> {
                    if (toggle.isSelected()) {
                        toggle.setBackground(new Color(97, 175, 239));
                    } else {
                        toggle.setBackground(new Color(80, 80, 80));
                    }
                });
                
                buttonPanel.add(toggle);
            }
            
            JButton confirmBtn = new JButton("Confirm");
            confirmBtn.setFont(new Font("Arial", Font.BOLD, 14));
            confirmBtn.setBackground(new Color(152, 195, 121));
            confirmBtn.setForeground(Color.WHITE);
            confirmBtn.setFocusPainted(false);
            confirmBtn.setBorderPainted(false);
            confirmBtn.setPreferredSize(new Dimension(100, 50));
            confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            confirmBtn.addActionListener(e -> {inputQueue.offer(-1);});
            
            buttonPanel.add(confirmBtn);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        
        try {
            inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        for (int i = 0; i < 3; i++) {
            if (toggles[i] != null && toggles[i].isSelected()) {
                selections.add(i + 1);
            }
        }
        
        SwingUtilities.invokeLater(() -> {
            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        
        if (selections.isEmpty()) {
            displayMessage("noExpansions");
        }
        
        return selections;
    }
    
    @Override
    public void changeLanguage(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }
}