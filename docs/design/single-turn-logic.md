1. **Check Visibility**:
    - Determine if the player's hand should be visible to themselves due to **Curse of the Cat Butt**.
    - Display any cards that are publicly visible to other players.

2. **Play a Card** *(Optional)*:
    - The player may choose to play a card from their hand.
    - If a card is played, execute its logic via the `playCard` method.

3. **Nope Card Check**:
    - Check if any other player plays a **Nope Card** to counter the played card.

4. **End Turn**:
    - Keep looping steps 2 and 3 until either the player chooses not to play a card or the player has no more cards left to play
    - The player ends their turn by drawing a card (or several, depending on previous actions) from the deck.
    - Set hand to be visible if it was invisible before

5. **Handle Edge Cases**:
    - **Exploding Kitten**:
        - If the drawn card is an **Exploding Kitten**, handle it by using a **Defuse Card** if available, or face elimination if no Defuse Card is present.
    - **Imploding Kitten**:
        - If the drawn card is a face-up **Imploding Kitten**, the player is immediately eliminated, as it cannot be defused. Otherwise, it is inserted back into deck.
    - There is only **one** player left:
        - Declare the remaining player as the winner and end the game.