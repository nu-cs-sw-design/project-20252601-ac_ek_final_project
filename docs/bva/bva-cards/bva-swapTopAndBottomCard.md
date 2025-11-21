# BVA Analysis for Swap Top And Bottom Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input | (if more to consider for input)       | Output                                             |
|--------|-------|---------------------------------------|----------------------------------------------------|
| Step 1 | Card  | the deck                              | deck modified with top and bottom swapped          |
| Step 2 | Card  | Collection of cards                   | Collection of Cards                                |
| Step 3 | Card  | Deck as ArrayList with 0, 1, >1 cards | Deck as ArrayList with top and bottom card swapped |

### Step 4:
##### Each-choice
|             | System under test | Expected output                                     | Implemented? |
|-------------|-------------------|-----------------------------------------------------|--------------|
| Test Case 1 | Deck has 0 cards  | UnsupportedOperationException                       | Yes          |
| Test Case 2 | Deck has 1 card   | Deck is the same                                    | Yes          |
| Test Case 3 | Deck has 4 cards  | Deck is modified with top and bottom card different | Yes          |