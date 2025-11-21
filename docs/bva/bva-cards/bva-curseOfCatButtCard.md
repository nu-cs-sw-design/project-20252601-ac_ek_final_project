# BVA Analysis for Curse of Cat Butt Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                                                      | (if more to consider for input) | Output                                                     |
|--------|----------------------------------------------------------------------------|---------------------------------|------------------------------------------------------------|
| Step 1 | player index                                                               |                                 | players cards are not visible and shuffled until next turn |
| Step 2 | interval                                                                   |                                 | players cards are not visible and shuffled until next turn |
| Step 3 | [0 to #players] - only need to test 1 - getPlayer method is tested in game |                                 | players cards are not visible and shuffled until next turn |

### Step 4:
##### Each-choice
|             | System under test           | Expected output                             | Implemented? |
|-------------|-----------------------------|---------------------------------------------|--------------|
| Test Case 1 | Player chosen doesn't exist | NoSuchElementException                      | Yes          |
| Test Case 2 | Player chosen is self       | InputMismatchException                      | Yes          |
| Test Case 3 | Valid player chosen         | Players cards are not visible, and shuffled | Yes          |