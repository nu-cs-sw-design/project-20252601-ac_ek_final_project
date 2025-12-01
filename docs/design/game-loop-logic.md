# Game Loop Logic

## Overview

The game loop is the core mechanism that drives gameplay from start to finish. It continuously processes player turns until the win condition (only 1 player left) is met.

## How it works

### 1. Initialize the Game
- Player chooses preferred language
- Player selects which expansion packs to use
- Number of human and AI players is set
- Deck is populated with cards based on selected expansions
- Each player receives a Defuse card and 4/6 additional cards
- Post-Deal Setup: Exploding Kittens and remaining Defuse cards are added to deck
- First Player: The first player in the list becomes the current player

### 2. Start Game Loop

The main loop forces players to keep taking turns until the game is over - until 1 player is left

### 3. Player's Turn

Each turn consists of the following phases:

1. **Initialization Phase**
   - Clear the screen
   - Display active players
   - Display deck size
   - Display current player ID
   - Display any marked cards visible to all players

2. **Card Playing Phase**
   - Player views their hand (if visible)
   - Player chooses to play a card or pass
   - If card is played:
     - All other players are prompted for Nope card response
     - If not Noped, card effect executes
   - Loop continues until player chooses to not play any card or has no cards

3. **Card Drawing Phase**
   - Player draws required number of cards (usually 1)
   - If Exploding/Imploding Kitten is drawn, handle immediately
   - Reduce remaining draws by 1

4. **Finalization Phase**
   - Announce turn end
   - Reset player state (turns = 1, visibility restored)
   - Advance to next player

### 4. Check Win Condition

After each turn:
- If only one player remains, set `gameOver = true`
- Exit the game loop

### 5. Advance to Next Player

Turn rotation follows these rules:
- Normal: Move to next player in sequence
- Reverse active: Move to previous player in sequence
- Targeted Attack: Move to the targeted player, and continue from there.


## End of Game

1. Display "Game Over" message
2. Identify the winner (last remaining player)
3. Announce the winner's ID
4. Shutdown UI resources