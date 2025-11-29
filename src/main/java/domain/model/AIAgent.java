package domain.model;

import domain.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AIAgent {
    private final Random random = new Random();
    private static final Set<String> CAT_CARDS = Set.of(
        "Bearded Cat", "Hairy Potato Cat", "Rainbow Ralphing Cat", "Taco Cat", "Cattermelon"
    );
    private final List<Integer> futureIndexes = new ArrayList<>();

    public int getDecision(String promptKey, Game game) {
        switch (promptKey) {
            case "playCardPrompt":
                // 0: Pass, 1: Play Card
                if (!hasPlayableCards(game.getCurrentPlayer())) {
                    return 0;
                }
                return random.nextInt(2);
            case "chooseCardPrompt":
                return chooseCard(game.getCurrentPlayer());
            case "chooseNope":
                // 0: Pass, 1: Play Nope
                return random.nextInt(2);
            case "keepOrAddExploding":
                // 0: Add to Deck (Defuse), 1: Keep (Streaking)
                return random.nextInt(2);
            case "whereToInsert":
                return chooseInsertIndex(game);
            case "playerID":
            case "playerIDCurse":
            case "targetPlayerId":
                return chooseTargetPlayer(game);
            case "newIndex":
                return getNextFutureIndex();
            case "takeCard":
            case "chosenPlayerCardIndex":
            case "targetCardIndex":
            case "discard":
                return 0;
            default:
                throw new IllegalArgumentException("Unknown prompt key for AI: " + promptKey);
        }
    }

    private int getNextFutureIndex() {
        if (futureIndexes.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                futureIndexes.add(i);
            }
            Collections.shuffle(futureIndexes);
        }
        return futureIndexes.remove(0);
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
        
        if (name.equals("Defuse") || 
            name.equals("Exploding Kitten") || 
            name.equals("Imploding Kitten") || 
            name.equals("Nope")) {
            return false;
        }

        if (CAT_CARDS.contains(name)) {
            return player.hasTwoOf(name);
        }

        if (name.equals("Feral Cat")) {
            if (player.hasTwoOf("Feral Cat")) {
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

    private int chooseInsertIndex(Game game) {
        int deckSize = game.getDeckSize();
        return random.nextInt(deckSize + 1);
    }

    private int chooseTargetPlayer(Game game) {
        List<Player> players = game.getPlayers();
        Player currentPlayer = game.getCurrentPlayer();
        List<Integer> validTargets = new ArrayList<>();

        for (Player p : players) {
            if (p.getId() != currentPlayer.getId() && p.getHandCount() > 0) {
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
