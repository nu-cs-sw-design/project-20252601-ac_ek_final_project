# BVA Analysis for Reverse Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                    | Output                                                          |
|--------|------------------------------------------|-----------------------------------------------------------------|
| Step 1 | The order of players in the game         | Next player is reversed                                         |
| Step 2 | Collection of players in order           | Cases: Order of players is reversed, Skip Card is played        |
| Step 3 | ArrayList of players with 2, >2 elements | Skip card functionality ,ArrayList of order of players reversed |

### Step 4:
##### Each-choice
|             | System under test  | Expected output        | Implemented? |
|-------------|--------------------|------------------------|--------------|
| Test Case 1 | Game has 2 players | Now works as Skip Card | Yes          |
| Test Case 2 | Game has 4 players | Players reversed       | Yes          |