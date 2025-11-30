package domain.player;

import domain.cards.Card;
import domain.deck.Deck;
import domain.game.GameEngine;
import domain.game.GameContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AIAgent {
    private final Random random = new Random();
    private final PlayerManager PlayerManager = new PlayerManager();
    private static final Set<String> CAT_CARDS = Set.of(
        "Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon"
    );
    private int newIndexCounter = 0;

    public int getDecision(String promptKey, GameEngine game) {
        switch (promptKey) {
            case "playCardPrompt":
                if (!hasPlayableCards(game.getCurrentPlayer())) {
                    return 0;
                }
                return random.nextInt(2);
            case "chooseCardPrompt":
                return chooseCard(game.getCurrentPlayer());
            case "chooseNope":
                return random.nextInt(2);
            case "keepOrAddExploding":
                return random.nextInt(2);
            case "whereToInsert":
                return chooseInsertIndex(game);
            case "playerID":
            case "playerIDCurse":
            case "targetPlayerId":
                return chooseTargetPlayer(game);
            case "takeCard":
            case "chosenPlayerCardIndex":
            case "targetCardIndex":
            case "discard":
                return 0;
            default:
                if (promptKey.startsWith("newIndex:")) {
                    int count = Integer.parseInt(promptKey.split(":")[1]);
                    return getNextUniqueNewIndex(count);
                }
                throw new IllegalArgumentException("Unknown prompt key for AI: " + promptKey);
        }
    }

    private int getNextUniqueNewIndex(int count) {
        int result = newIndexCounter;
        newIndexCounter++;
        if (newIndexCounter >= count) {
            newIndexCounter = 0;
        }
        return result;
    }

    private int chooseCard(Player player) {
        List<Integer> playableIndices = getPlayableCardIndices(player);
        if (playableIndices.isEmpty()) {
            return -1;
        }
        return playableIndices.get(random.nextInt(playableIndices.size()));
    }

    private boolean hasPlayableCards(Player player) {
        return !getPlayableCardIndices(player).isEmpty();
    }

    private List<Integer> getPlayableCardIndices(Player player) {
        List<Integer> indices = new ArrayList<>();
        List<Card> hand = player.getHand();
        
        for (int i = 0; i < hand.size(); i++) {
            if (isCardPlayable(hand.get(i), player)) {
                indices.add(i);
            }
        }
        return indices;
    }

    private boolean isCardPlayable(Card card, Player player) {
        String name = card.getName();
        
        if (name.equals("Defuse") || name.equals("Exploding Kitten") || name.equals("Imploding Kitten") || name.equals("Nope") || name.equals("Streaking Kitten")) {
            return false;
        }

        if (CAT_CARDS.contains(name)) {
            return PlayerManager.hasTwoOf(player, name);
        }

        if (name.equals("Feral Cat")) {
            if (PlayerManager.hasTwoOf(player, "Feral Cat")) {
                return true;
            }
            for (Card c : player.getHand()) {
                if (CAT_CARDS.contains(c.getName())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    private int chooseInsertIndex(GameEngine game) {
        int deckSize = game.getDeckSize();
        return random.nextInt(deckSize + 1);
    }

    private int chooseTargetPlayer(GameEngine game) {
        List<Player> players = game.getPlayers();
        Player currentPlayer = game.getCurrentPlayer();
        List<Integer> validTargets = new ArrayList<>();

        for (Player p : players) {
            if (p.getId() != currentPlayer.getId() && PlayerManager.getHandCount(p) > 0) {
                validTargets.add(p.getId());
            }
        }

        if (validTargets.isEmpty()) {
            for (Player p : players) {
                if (p.getId() != currentPlayer.getId()) {
                    validTargets.add(p.getId());
                }
            }
        }

        if (validTargets.isEmpty()) {
            return -1;
        }

        return validTargets.get(random.nextInt(validTargets.size()));
    }
}