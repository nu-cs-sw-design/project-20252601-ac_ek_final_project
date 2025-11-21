# BVA Analysis for Skip Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input        | (if more to consider for input) | Output                                 |
|--------|--------------|---------------------------------|----------------------------------------|
| Step 1 | The Player   |                                 | Player field numberOfTurns modified    |
| Step 2 | Player Class |                                 | Integer numberOfTurns                  |
| Step 3 | Player Class |                                 | Integer numberOfTurns decreased by one |

### Step 4:
##### Each-choice
|             | System under test     | Expected output          | Implemented? |
|-------------|-----------------------|--------------------------|--------------|
| Test Case 1 | Player has two turns  | they now have one turns  | Yes          |
| Test Case 2 | Player has zero turns | Nothing changes          | Yes          |
| Test Case 3 | Player has one turn   | they now have zero turns | Yes          |
