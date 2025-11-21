# BVA Analysis for Targeted Attack Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                      | (if more to consider for input) | Output                                           |
|--------|----------------------------|---------------------------------|--------------------------------------------------|
| Step 1 | number of nope cards owned |                                 | card is gone                                     |
| Step 2 | Cases: 1, >1               |                                 | Cases: has nope card left, doesn't have any left |
| Step 3 | 1 card, >1 card            |                                 | 0 cards, >=1 cards                               |

### Step 4:
##### Each-choice
|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | 1 nope Card       | 0 nope cards    | No           |
| Test Case 2 | 2 nope Cards      | 1 nope card     | No           |