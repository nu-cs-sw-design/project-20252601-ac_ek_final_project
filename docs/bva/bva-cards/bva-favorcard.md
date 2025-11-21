# BVA Analysis for Favor Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input 1                                   | Input 2               | Input 3                                            | Output                                                                                    |
|--------|-------------------------------------------|-----------------------|----------------------------------------------------|-------------------------------------------------------------------------------------------|
| Step 1 | the id of the chosen player to steal from | the current player id | the card being given                               | Result of playing the card or error                                                       |
| Step 2 | interval: [2, 10]                         | interval: [2, 10]     | cases                                              | Cases  or Excpetion                                                                       |
| Step 3 | 2, 3, 9, 10                               | 2, 3, 9, 10           | Exploding Kitten Card or Not Exploding Kitten Card | The card was added to hand, Exploding Kitten Card is triggered, or InputMismatchException | 

### Step 4:
##### Each-choice
|             | System under test                                         | Expected output                    | Implemented? |
|-------------|-----------------------------------------------------------|------------------------------------|--------------|
| Test Case 1 | cur_player = 2, chosen_player = 2, card=Attack            | InputMismatchException             | yes          |
| Test Case 2 | cur_player = 3, chosen_player = 9, card=Attack            | The card was added to hand         | yes          |
| Test Case 3 | cur_player = 9, chosen_player = 10, card=Exploding Kitten | Exploding Kitten Card is triggered | yes          |
| Test Case 4 | cur_player = 10, chosen_player = 3, card =Attack          | The card was added to hand         | yes          |