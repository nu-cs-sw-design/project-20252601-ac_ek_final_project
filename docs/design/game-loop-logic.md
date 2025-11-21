## Algorithm

1. **Initialize the Game**:
    - Ensure the deck, players, and any necessary game state are properly initialized.

2. **Start Game Loop**:
    - Repeat until only one player remains in the game.

3. **Player's Turn**:
   - The current player takes the number of turns they have
   - If they choose to play a card, every player with a nope card is asked to use it before the card is actually played
   - End the turn by drawing a card from the deck and handling any edge cases.
   - If the player has multiple turns (e.g., due to card effects), they play additional turns consecutively.

4. **Check Win Condition**:
    - If only one player remains, declare them the winner and exit the loop.

5. **Advance to the Next Player**:
    - Rotate the turn to the next player in the list.

---

## End of Game
- Announce the winner and finalize the game state.