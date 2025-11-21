# BVA Analysis for Garbage Collection Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input 1                           | Output                                   |
|--------|-----------------------------------|------------------------------------------|
| Step 1 | the number of players in the game | the number of cards in added to the deck |
| Step 2 | interval: [2, 10]                 | interval:  [2, 10]                       |
| Step 3 | 2, 3, 9, 10                       | 2, 3, 9, 10                              |

### Step 4:
##### Each-choice
|             | System under test | Expected output  | Implemented? |
|-------------|-------------------|------------------|--------------|
| Test Case 1 | num players = 2   | Cards Added = 2  | Yes          |
| Test Case 2 | num players = 3   | Cards Added = 4  | Yes          |
| Test Case 3 | num players = 9   | Cards Added = 11 | Yes          |
| Test Case 4 | num players = 10  | Cards Added = 13 | Yes          |