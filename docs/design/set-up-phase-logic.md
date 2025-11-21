# Game Setup Algorithm

## Initializing the Game
1. Create an empty list of players.
2. Create and initialize a local deck with the appropriate count of each card type based on the number of players.
3. Shuffle the deck after adding all cards to players hands.

---

## Initializing Players
1. For each player (up to `numberOfPlayers`):
    - Add a **Defuse Card** to the player's hand.
    - Draw enough cards from the deck to fill the player's hand to the initial size of 7.
    - Add the player to the game's player list.
2. The first player in the list becomes the **current player**.