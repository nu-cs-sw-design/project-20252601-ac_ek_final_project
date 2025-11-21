# BVA Analysis for Alter The Future Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                          | (if more to consider for input)                                                      | Output                                               |
|--------|--------------------------------|--------------------------------------------------------------------------------------|------------------------------------------------------|
| Step 1 | number of cards to be modified | indexes                                                                              | deck modified with top 3 or 5 cards in correct order |
| Step 2 | Cases: 3, 5                    | Collection of Integers, cases: same order, different order, wrong number of indexes  | Collection of Cards                                  |
| Step 3 | 3, 5                           | [0, 1, 2, 3, 4], [2, 4, 0, 1, 3], [2, 0, 1], [1, 0], []                              | Deck as ArrayList with top and bottom card swaped    |

### Step 4:
##### Each-choice
|             | System under test  | Expected output                    | Implemented? |
|-------------|--------------------|------------------------------------|--------------|
| Test Case 1 | 3, [1, 0]          | Modified deck with 2 cards swapped | Yes          |
| Test Case 2 | 3, [2, 0, 1]       | Modified Deck                      | Yes          |
| Test Case 3 | 5, [0, 1, 2, 3, 4] | same Deck                          | Yes          |
| Test Case 4 | 5, [2, 4, 0, 1, 3] | Modified deck                      | Yes          |
| Test Case 5 | 3, []              | IllegalArgumentException           | Yes          |