# BVA Analysis for Shuffle Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                 | (if more to consider for input) | Output                                              |
|--------|---------------------------------------|---------------------------------|-----------------------------------------------------|
| Step 1 | The deck                              |                                 | Deck with all cards shuffled                        |
| Step 2 | Collection of cards                   |                                 | Collection of cards with all elements shuffled      |
| Step 3 | Deck as ArrayList with 0, 1, >1 cards |                                 | Deck with all cards in randomly different positions |

### Step 4:
##### Each-choice
|             | System under test | Expected output                      | Implemented? |
|-------------|-------------------|--------------------------------------|--------------|
| Test Case 1 | Deck has 0 cards  | UnsupportedOperationException        | Yes          |
| Test Case 2 | Deck has 1 card   | Same deck with 1 card                | Yes          |
| Test Case 3 | Deck has 4 cards  | Deck has 4 cards, which are shuffled | Yes          |