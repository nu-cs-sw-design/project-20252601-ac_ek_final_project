# BVA Analysis for Attack Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                             | Output                               |
|--------|-----------------------------------|--------------------------------------|
| Step 1 | Number of turns of current player | Next player has 2 more cards to draw |
| Step 2 | Count variable                    | Count variable increased by 2        |
| Step 3 | 0, 1, 2                           | 2, 3, 4                              |

### Step 4:
##### Each-choice
|             | System under test          | Expected output         | Implemented? |
|-------------|----------------------------|-------------------------|--------------|
| Test Case 1 | current player has 0 turns | next player has 2 turns | Yes          |
| Test Case 2 | current player has 1 turn  | next player has 3 turns | Yes          |
| Test Case 3 | current player has 2 turns | next player has 4 turns | Yes          |