package domain.player;

import domain.game.Action;
import domain.game.GameEngine;
import ui.UserInterface;

import java.util.List;

public class PlayerControllerHuman implements PlayerController {
    private static final int YES = 1;
    private final UserInterface UserInterface;

    public PlayerControllerHuman(UserInterface UserInterface) {
        this.UserInterface = UserInterface;
    }

    @Override
    public Action getTurnAction(GameEngine engine) {
        int playCardChoice = UserInterface.promptPlayer("playCardPrompt");
        if (playCardChoice == YES) {
            int cardIndex = UserInterface.promptPlayer("chooseCardPrompt");
            return Action.playCard(cardIndex);
        }
        return Action.pass();
    }

    @Override
    public Action getNopeAction(GameEngine engine) {
        int wantsToPlay = UserInterface.promptPlayer("chooseNope");
        if (wantsToPlay == YES) {
            return Action.nope();
        }
        return Action.pass();
    }

    @Override
    public int chooseIndex(GameEngine engine, int max) {
        int deckSize = engine.getDeckSize();
        while (true) {
            int index = UserInterface.promptPlayer("whereToInsert");
            if (index >= 0 && index <= deckSize) {
                return index;
            }
            UserInterface.displayMessage("indexOutOfBounds");
            UserInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int chooseTargetPlayer(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        List<Player> players = engine.getPlayers();
        
        while (true) {
            int targetId = UserInterface.promptPlayer("targetPlayerId");
            
            if (targetId == currentPlayer.getId()) {
                UserInterface.displayMessage("chosenSelfError");
                UserInterface.displayMessage("tryAgain");
                continue;
            }
            
            boolean validPlayer = false;
            for (Player player : players) {
                if (player.getId() == targetId) {
                    validPlayer = true;
                    break;
                }
            }
            
            if (validPlayer) {
                return targetId;
            }
            
            UserInterface.displayMessage("invalidPlayerID");
            UserInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int chooseCardIndex(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        int handSize = engine.getPlayerManager().getHandCount(currentPlayer);
        
        while (true) {
            int cardIndex = UserInterface.promptPlayer("chooseCardPrompt");
            if (cardIndex >= 0 && cardIndex < handSize) {
                return cardIndex;
            }
            UserInterface.displayMessage("invalidCardIndex");
            UserInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int getDecision(GameEngine engine, String prompt) {
        switch (prompt) {
            case "targetPlayerId":
            case "playerID":
            case "playerIDCurse":
                return getValidatedTargetPlayer(engine, prompt);
            case "whereToInsert":
                return getValidatedIndex(engine);
            case "chooseCardPrompt":
            case "takeCard":
            case "chosenPlayerCardIndex":
            case "targetCardIndex":
                return getValidatedCardIndex(engine, prompt);
            default:
                return UserInterface.promptPlayer(prompt);
        }
    }

    private int getValidatedTargetPlayer(GameEngine engine, String prompt) {
        Player currentPlayer = engine.getCurrentPlayer();
        List<Player> players = engine.getPlayers();
        
        while (true) {
            int targetId = UserInterface.promptPlayer(prompt);
            
            if (targetId == currentPlayer.getId()) {
                UserInterface.displayMessage("chosenSelfError");
                UserInterface.displayMessage("tryAgain");
                continue;
            }
            
            boolean validPlayer = false;
            for (Player player : players) {
                if (player.getId() == targetId) {
                    validPlayer = true;
                    break;
                }
            }
            
            if (validPlayer) {
                return targetId;
            }
            
            UserInterface.displayMessage("invalidPlayerID");
            UserInterface.displayMessage("tryAgain");
        }
    }

    private int getValidatedIndex(GameEngine engine) {
        int deckSize = engine.getDeckSize();
        while (true) {
            int index = UserInterface.promptPlayer("whereToInsert");
            if (index >= 0 && index <= deckSize) {
                return index;
            }
            UserInterface.displayMessage("indexOutOfBounds");
            UserInterface.displayMessage("tryAgain");
        }
    }

    private int getValidatedCardIndex(GameEngine engine, String prompt) {
        Player currentPlayer = engine.getCurrentPlayer();
        int handSize = engine.getPlayerManager().getHandCount(currentPlayer);
        
        while (true) {
            int cardIndex = UserInterface.promptPlayer(prompt);
            if (cardIndex >= 0 && cardIndex < handSize) {
                return cardIndex;
            }
            UserInterface.displayMessage("invalidCardIndex");
            UserInterface.displayMessage("tryAgain");
        }
    }
}