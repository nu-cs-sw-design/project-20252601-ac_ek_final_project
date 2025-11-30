package ui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SwingGUI implements GameUI {    
    private JFrame mainFrame;
    private JTextArea messageArea;
    private JPanel buttonPanel;
    private JPanel handPanel;
    
    private Locale locale;
    private ResourceBundle resourceBundle;
    
    private final BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>();
    
    private static final Map<String, Color> CARD_COLORS = new HashMap<>();
    static {
        CARD_COLORS.put("Exploding Kitten", new Color(220, 53, 69));
        CARD_COLORS.put("Imploding Kitten", new Color(255, 140, 0));
        CARD_COLORS.put("Defuse", new Color(40, 167, 69));
        CARD_COLORS.put("Nope", new Color(0, 123, 255));
        CARD_COLORS.put("Attack", new Color(255, 193, 7));
        CARD_COLORS.put("Skip", new Color(23, 162, 184));
        CARD_COLORS.put("Favor", new Color(111, 66, 193));
        CARD_COLORS.put("Shuffle", new Color(108, 117, 125));
        CARD_COLORS.put("See The Future", new Color(32, 201, 151));
        CARD_COLORS.put("Streaking Kitten", new Color(253, 126, 20));
        CARD_COLORS.put("Feral Cat", new Color(173, 181, 189));
        CARD_COLORS.put("Taco Cat", new Color(255, 205, 86));
        CARD_COLORS.put("Cattermelon", new Color(75, 192, 192));
        CARD_COLORS.put("Hairy Potato Cat", new Color(153, 102, 255));
        CARD_COLORS.put("Rainbow Ralphing Cat", new Color(255, 99, 132));
        CARD_COLORS.put("Bearded Cat", new Color(201, 203, 207));
    }
    
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
        mainFrame.setSize(1200, 800);
        mainFrame.setLayout(new BorderLayout());
        
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        messageArea.setBackground(new Color(40, 44, 52));
        messageArea.setForeground(new Color(171, 178, 191));
        JScrollPane scrollPane = new JScrollPane(messageArea);
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        
        handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        handPanel.setBackground(new Color(25, 28, 34));
        handPanel.setPreferredSize(new Dimension(1200, 200));
        JScrollPane handScrollPane = new JScrollPane(handPanel);
        handScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        handScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        handScrollPane.setPreferredSize(new Dimension(1200, 220));
        mainFrame.add(handScrollPane, BorderLayout.SOUTH);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(33, 37, 43));
        buttonPanel.setPreferredSize(new Dimension(1200, 60));
        mainFrame.add(buttonPanel, BorderLayout.NORTH);
        
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
            handPanel.removeAll();
            handPanel.revalidate();
            handPanel.repaint();
        });
    }
    
    private Color getCardColor(String cardName) {
        for (Map.Entry<String, Color> entry : CARD_COLORS.entrySet()) {
            if (cardName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return new Color(100, 100, 100);
    }
    
    private JPanel createCardPanel(CardInfo card) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(100, 150));
        
        Color baseColor = getCardColor(card.name());
        
        if (card.isPlayable()) {
            cardPanel.setBackground(baseColor);
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 205, 50), 3),
                BorderFactory.createLineBorder(Color.BLACK, 1)
            ));
            cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            cardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    inputQueue.offer(card.index());
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 255, 0), 4),
                        BorderFactory.createLineBorder(Color.WHITE, 2)
                    ));
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(50, 205, 50), 3),
                        BorderFactory.createLineBorder(Color.BLACK, 1)
                    ));
                }
            });
        } else {
            cardPanel.setBackground(new Color(
                (int)(baseColor.getRed() * 0.4),
                (int)(baseColor.getGreen() * 0.4),
                (int)(baseColor.getBlue() * 0.4)
            ));
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1)
            ));
        }
        
        JLabel nameLabel = new JLabel("<html><center>" + card.name() + "</center></html>");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 11));
        nameLabel.setForeground(card.isPlayable() ? Color.WHITE : Color.GRAY);
        cardPanel.add(nameLabel, BorderLayout.CENTER);
        
        JLabel indexLabel = new JLabel("[" + card.index() + "]");
        indexLabel.setHorizontalAlignment(SwingConstants.CENTER);
        indexLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        indexLabel.setForeground(card.isPlayable() ? Color.WHITE : Color.GRAY);
        cardPanel.add(indexLabel, BorderLayout.SOUTH);
        
        JLabel statusLabel = new JLabel(card.isPlayable() ? "✓ PLAY" : "✗");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 10));
        statusLabel.setForeground(card.isPlayable() ? new Color(50, 255, 50) : new Color(150, 150, 150));
        cardPanel.add(statusLabel, BorderLayout.NORTH);
        
        return cardPanel;
    }
    
    @Override
    public int promptTurnAction(List<CardInfo> cards) {
        inputQueue.clear();
        
        SwingUtilities.invokeLater(() -> {
            handPanel.removeAll();
            
            for (CardInfo card : cards) {
                JPanel cardPanel = createCardPanel(card);
                handPanel.add(cardPanel);
            }
            
            handPanel.revalidate();
            handPanel.repaint();
            
            buttonPanel.removeAll();
            JButton drawButton = new JButton("Draw Card (End Turn)");
            drawButton.setFont(new Font("Arial", Font.BOLD, 14));
            drawButton.setBackground(new Color(220, 53, 69));
            drawButton.setForeground(Color.WHITE);
            drawButton.setFocusPainted(false);
            drawButton.setPreferredSize(new Dimension(200, 40));
            drawButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            drawButton.addActionListener(e -> inputQueue.offer(-1));
            buttonPanel.add(drawButton);
            
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        
        appendMessage(resourceBundle.getString("currentHand"));
        appendMessage("Click a playable card (green border) or Draw Card to end your turn.");
        
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        } finally {
            SwingUtilities.invokeLater(() -> {
                buttonPanel.removeAll();
                buttonPanel.revalidate();
                buttonPanel.repaint();
            });
        }
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
                new String[]{"English", "Français", "Español", "Deutsch", "日本語"},
                new int[]{0, 1, 2, 3, 4});
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