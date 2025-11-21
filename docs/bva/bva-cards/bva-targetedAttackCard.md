# BVA Analysis for Targeted Attack Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                                         | (if more to consider for input) | Output                                 |
|--------|---------------------------------------------------------------|---------------------------------|----------------------------------------|
| Step 1 | selected player                                               | number of turns of that player  | selected player has more cards to draw |
| Step 2 | interval integer of player index                              | count variable                  | count variable increased by 2          |
| Step 3 | interval [0-4] 5 players, target 4th, 6th, yourself, 1st, 1st | 0, 1 , >1                       | 2, 3 , >3                              |

### Step 4:
##### Each-choice
|             | System under test                                         | Expected output                                     | Implemented? |
|-------------|-----------------------------------------------------------|-----------------------------------------------------|--------------|
| Test Case 1 | 5 players, targeted 4th player, currentPlayer has 0 turns | currentPlayer is now 4th player and has two turns   | Yes          |
| Test Case 2 | 5 players, targeted 6th player                            | UnsupportedOperationException                       | Yes          |
| Test Case 3 | 5 players, targeted -1st player                           | UnsupportedOperationException                       | Yes          |
| Test Case 4 | 5 players, targeted yourself                              | UnsupportedOperationException                       | Yes          |
| Test Case 5 | 5 players, targeted 1st player, currentPlayer has 1 turn  | currentPlayer is now 1st player and has three turns | Yes          |
| Test Case 6 | 5 players, targeted 1st player, currentPlayer has 2 turn  | currentPlayer is now 1st player and has four turns  | Yes          |