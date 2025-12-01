# Exploding Kittens - Design Assumptions

## Game Rules Assumptions

### Player Count
- Minimum Players: The game requires at least 2 players (can be any combination of human and AI players).
- Maximum Players: Limited to 5 players for the base game; some expansions may increase this limit.

### Card Dealing
- Each player receives exactly 1 Defuse card at the start of the game.
- Each player's initial hand is filled to 4 or 7 cards (depending on the expansion pack(s) used).
- Exploding Kittens are added to the drawpile after dealing to ensure no player receives one initially.

### Turn Order
- Players take turns in sequential order: 1 -> 2 -> ...
- The Reverse card changes the direction of play.
- When a player is eliminated, the turn order continues with the next active player.

### Nope Card Mechanics
- Nope cards can be played at any time when another player plays a card.
- Nope cards cannot counter Exploding Kitten or Defuse cards.
- Nope cards can counter other Nope cards.
- All players with Nope cards are given the opportunity to respond before a card effect resolves.

### Exploding Kitten Handling
- When an Exploding Kitten is drawn, the player must immediately play a Defuse card or be eliminated.
- A Defused Exploding Kitten can be placed anywhere in the deck.
- If a player has a Streaking Kitten, they can hold exactly one Exploding Kitten secretly without exploding.

### Imploding Kitten Rules
- The Imploding Kitten is placed in the deck face-down initially.
- When first drawn, it must be placed back face-up in the deck.
- A face-up Imploding Kitten cannot be defused and eliminates the player immediately.

### Player Input
- All numeric inputs are validated for range and type.
- Invalid inputs result in re-prompting the user.

### Card Selection
- Players can only select cards by index from their hand.
- Invalid card indices display an error and allow retry.

### Target Selection
- Only active (non-eliminated) players can be targeted that aren't the current player.

### Deck Operations
- Deck shuffling uses randomization.

### Language Support
- All user-facing strings are externalized to property files.
- The application defaults to English if no language is selected.
- Language selection happens only once at game start and cannot be changed mid-game.