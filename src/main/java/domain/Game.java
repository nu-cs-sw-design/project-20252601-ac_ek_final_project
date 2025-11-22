package domain;

import domain.Cards.*;
import ui.UI;

import java.util.*;

public class Game {
    private final int numberOfPlayers;
    private final List<Player> players;
    private final UI ui;

    private Deck deck;
    private Player currentPlayer;
    private Boolean isGameOver = false;


    int INITIAL_CARDS_PER_PLAYER = 8;
    int MINIMUM_PLAYERS = 1;
    int CURRENT_PLAYER_INDEX = 0;

    public Game(int numberOfPlayers) {
        this(numberOfPlayers, new UI());
    }

    public Game(Game game) {
        this.numberOfPlayers = 0;
        this.players = null;
        this.deck = null;
        this.currentPlayer = null;
        this.ui = null;
        this.isGameOver = game.isGameOver();
    }

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public Game(int numberOfPlayers, UI ui) {
        this.players = new ArrayList<>(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        this.ui = ui;

        Deck localDeck = new Deck();
        int countToAdd = computeCountToAdd(this.numberOfPlayers);
        initializeDeck(localDeck, countToAdd);
        localDeck.shuffle();
        this.deck = localDeck;

        for (int i = 0; i < numberOfPlayers; i++) {
            Player newPlayer = new Player(i + 1, new ArrayList<Card>(8));

            newPlayer.addCard(new DefuseCard());

            for (int j = 0; j < INITIAL_CARDS_PER_PLAYER - 1; j++) {
                newPlayer.addCard(deck.drawTopCard());
            }

            players.add(newPlayer);

            if (i == CURRENT_PLAYER_INDEX) {
                currentPlayer = newPlayer;
            }
        }

        addToCards(localDeck, new ExplodingKittenCard(), numberOfPlayers - 1);
        addToCards(localDeck, new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN), 1);
        localDeck.shuffle();
        this.deck = localDeck;
    }

    private void initializeDeck(Deck localDeck, int countToAdd) {
        for (CatCard.CatCardType catCard : CatCard.CatCardType.values()) {
            addToCards(localDeck, new CatCard(catCard), CatCard.getCounts()[countToAdd]);
        }
        addToCards(localDeck, new FeralCatCard(), FeralCatCard.getCounts()[countToAdd]);
        addToCards(localDeck, new NopeCard(), NopeCard.getCounts()[countToAdd]);
        addToCards(localDeck, new AttackCard(), AttackCard.getCounts()[countToAdd]);
        addToCards(localDeck, new TargetedAttackCard(), TargetedAttackCard.getCounts()[countToAdd]);
        addToCards(localDeck, new ShuffleCard(), ShuffleCard.getCounts()[countToAdd]);
        addToCards(localDeck, new FavorCard(), FavorCard.getCounts()[countToAdd]);
        addToCards(localDeck, new DrawFromTheBottomCard(), DrawFromTheBottomCard.getCounts()[countToAdd]);
        addToCards(localDeck, new SkipCard(), SkipCard.getCounts()[countToAdd]);
        addToCards(localDeck, new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), SeeTheFutureCard.getCounts()[countToAdd]);
        addToCards(localDeck, new SeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE), 1);
        addToCards(localDeck, new AlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE), AlterTheFutureCard.getCounts()[countToAdd]);
        addToCards(localDeck, new AlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE), 1);
        addToCards(localDeck, new StreakingKittenCard(), 1);
        addToCards(localDeck, new SuperSkipCard(), 1);
        addToCards(localDeck, new CatomicBombCard(), 1);
        addToCards(localDeck, new GarbageCollectionCard(), 1);
        addToCards(localDeck, new CurseOfTheCatButtCard(), 2);
        addToCards(localDeck, new SwapTopAndBottomCard(), 3);
        addToCards(localDeck, new MarkCard(), 3);
        addToCards(localDeck, new ReverseCard(), 4);
    }

    private void addToCards(Deck localDeck, Card typeOfCard, int count) {
        for (int i = 0; i < count; i++) {
            localDeck.addCard(typeOfCard);
        }
    }

    private int computeCountToAdd(int numberOfPlayers) {
        if (numberOfPlayers <= 3) {
            return 0;
        } else if (numberOfPlayers <= 7) {
            return 1;
        } else {
            return 2;
        }
    }

    public void takeTurn() {
        int playCardYes = 1;
        int numPlayers = getNumberOfPlayers();

        ui.clearScreen();
        displayGameInfo();
        ui.displayFormattedMessage("player", currentPlayer.getId());
        ui.displayMessage("turnStart");
        displayMarkCards();


        if (!currentPlayer.isHandEmpty()) {
            int playCardChoice = 1;

            while (playCardChoice == playCardYes && currentPlayer.getNumberOfTurns() > 0 && !currentPlayer.isHandEmpty()) {
                boolean validCardPlayed = false;
                ui.displayMessage("currentHand");
                displayPlayerHand(currentPlayer, currentPlayer.getHandVisibility());
                playCardChoice = ui.promptPlayer("playCardPrompt");

                if(playCardChoice != playCardYes) {
                    break;
                }

                while (!validCardPlayed && !currentPlayer.isHandEmpty()) {
                    int cardIndex = ui.promptPlayer("chooseCardPrompt");
                    try {
                        Card selectedCard = currentPlayer.chooseCard(cardIndex);
                        boolean actionCanceled = false;

                        for (Player player : players) {
                            if (player != currentPlayer) {
                                int nopeIndex = player.hasCard("Nope");
                                if (nopeIndex != -1) {
                                    ui.displayFormattedMessage("player", player.getId());
                                    int wantsToPlay = ui.promptPlayer("chooseNope");
                                    if (wantsToPlay == 1) {
                                        Card nopeCard = player.chooseCard(nopeIndex);
                                        player.removeCard(nopeIndex);
                                        game.setPlayer(player);
                                        nopeCard.playCard(this, ui);
                                        ui.displayMessage("playedNope");
                                        
                                        currentPlayer.removeCard(cardIndex);
                                        game.setPlayer(currentPlayer);
                                        
                                        actionCanceled = true;
                                        validCardPlayed = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (!actionCanceled) {
                            try{
                                int playerCountBefore = getNumberOfPlayers();
                                selectedCard.playCard(this, ui);
                                validCardPlayed = true;
                                
                                if (getNumberOfPlayers() < playerCountBefore) {
                                    if (players.size() == MINIMUM_PLAYERS) {
                                        setGameOver(true);
                                    }
                                    ui.displayMessage("playerEliminated");
                                    return;
                                }
                            } catch (Exception exception) {
                                ui.displayMessage(exception.getMessage());
                                ui.displayMessage("tryAgain");
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        ui.displayMessage("invalidCardIndex");
                    }
                }
            }
        }

        while (!currentPlayer.getIsTurnOver()) {
            if (deck.isEmpty()) {
                ui.displayMessage("deckEmpty");
                break;
            }
            Card cardDrawn = deck.drawTopCard();
            if (cardDrawn instanceof ExplodingKittenCard) {
                cardDrawn.playCard(this, ui);
                break;
            } else if (cardDrawn instanceof ImplodingKittenCard) {
                cardDrawn.playCard(this, ui);
                break;
            }
            else {
                currentPlayer.addCard(cardDrawn);
                ui.displayMessage("drawCard");
                currentPlayer.decreaseTurnByOne();
            }

        }

        if (players.size() == MINIMUM_PLAYERS) {
            setGameOver(true);
        }
        if (getNumberOfPlayers() != numPlayers - 1) {
            ui.displayFormattedMessage("endTurn", currentPlayer.getId());
        }
        currentPlayer.setNumberOfTurns(1);
        if (!currentPlayer.getHandVisibility()) {
            currentPlayer.setHandVisibility(true);
        }
        nextPlayer();
    }

    public void deletePlayer(int id) {
        if (players.isEmpty()) {
            throw new NoSuchElementException("emptyPlayers");
        } else if (getNumberOfPlayers() == MINIMUM_PLAYERS) {
            throw new IllegalStateException("onePlayer");
        }

        for (Player player : players) {
            if (player.getId() == id) {
                players.remove(player);
                return;
            }
        }

        throw new NoSuchElementException("invalidPlayerID");
    }

    public Player getCurrentPlayer() {
        return new Player(currentPlayer);
    }

    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return new Player(player);
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = new Player(player);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        try {
            int playerId = getPlayer(playerIndex).getId();
            for (Player player : players) {
                if (player.getId() == playerId) {
                    player.setNumberOfTurns(turns);
                }
            }
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Invalid player index");
        }
    }

    public void setNextPlayerTurns(int turns) {
        setPlayerTurns(getNextPlayerId(), turns);
    }

    public int getNextPlayerId() {
        return players.get(1).getId();
    }

    public void setPlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.set(i, player);
                if (i == CURRENT_PLAYER_INDEX) {
                    setCurrentPlayer(player);
                }
                return;
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void nextPlayer() {
        Collections.rotate(players, -1);
        currentPlayer = players.get(CURRENT_PLAYER_INDEX);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public Deck getDeck() {
        return deck.copy();
    }

    @SuppressWarnings("EI_EXPOSE_REP") // UI is a shared service object, not mutable state
    public UI getUI() {
        return this.ui;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public void setPlayers(List<Player> newPlayers) {
        players.clear();
        players.addAll(newPlayers);
    }

    public void setDeck(Deck deck) {
        this.deck = new Deck(deck);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        int targetIndex = players.indexOf(targetPlayer);
        if (targetIndex == 0) {
            throw new UnsupportedOperationException("invalidTargetPlayer");
        }
        if (targetIndex == -1){
            throw new UnsupportedOperationException("targetPlayerOutOfRange");
        }

        List<Player> reorderedPlayers = new ArrayList<>();
        reorderedPlayers.addAll(players.subList(targetIndex, players.size()));
        reorderedPlayers.addAll(players.subList(CURRENT_PLAYER_INDEX, targetIndex));

        setPlayers(reorderedPlayers);
        currentPlayer = players.get(CURRENT_PLAYER_INDEX);
    }

    public void setCurrentPlayerTurns(int i) {
        currentPlayer.setNumberOfTurns(i);
    }

    public void emptyCurrentPlayerHand() {
        currentPlayer.setHand(new ArrayList<>());
    }

    private void displayPlayerHand(Player player, Boolean visibility) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (visibility) {
                ui.displayFormattedMessage("visibleCard", i, player.getHand().get(i).getName());
            } else {
                ui.displayFormattedMessage("hiddenCard", i);
            }
        }
    }


    public void setCurrentPlayerHasNope(Boolean hasNope) {
        currentPlayer.setHasNope(hasNope);
    }

    public Boolean getCurrentPlayerHasNope() {
        return currentPlayer.getHasNope();
    }

    public Boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public Player getWinner() {
        if (players.size() == MINIMUM_PLAYERS) {
            return new Player(players.get(0));
        }
        return null;
    }

    public void removeCurrentPlayerCard(int index) {
        currentPlayer.removeCard(index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        currentPlayer.addCard(cardToAdd);
    }


    public Map<Integer, List<Card>> getVisibleCards() {
        Map<Integer, List<Card>> visibleCardsMap = new HashMap<>();

        for (Player player : players) {
            int playerId = player.getId();
            List<Card> visibleHand = player.getVisibleHand();
            if (!visibleHand.isEmpty()) {
                visibleCardsMap.put(playerId, visibleHand);
            }
        }

        return visibleCardsMap;
    }

    private void displayMarkCards() {
        Map<Integer, List<Card>> visibleCardsMap = getVisibleCards();

        if (!visibleCardsMap.isEmpty()) {
            ui.displayMessage("displayMarkedCards");
        }

        for (Map.Entry<Integer, List<Card>> entry : visibleCardsMap.entrySet()) {
            int playerId = entry.getKey();
            List<Card> visibleCards = entry.getValue();

            ui.displayFormattedMessage("playerIdMessage", playerId);

            for (Card card : visibleCards) {
                ui.displayFormattedMessage("cardDisplay", "-", card.getName());
            }
        }
    }

    private void displayGameInfo() {
        ui.displayMessage("activePlayers");
        for (Player player : players) {
            ui.displayFormattedMessage("playerInfo", player.getId());
        }
        ui.displayMessage("separator");
        int nextPlayerIndex = 1;
        if (players.size() > 1) {
            ui.displayFormattedMessage("nextPlayer", players.get(nextPlayerIndex).getId());
        }
        ui.displayMessage("separator");
    }
}