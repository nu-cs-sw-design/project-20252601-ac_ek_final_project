# BVA Analysis for Mark Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                              | (if more to consider for input)                           | Output                                   |
|--------|----------------------------------------------------|-----------------------------------------------------------|------------------------------------------|
| Step 1 | selected player                                    | selected card from selected players deck                  | selected card of player is now visible   |
| Step 2 | interval integer of player id                      | interval integer index of player card                     | Boolean                                  |
| Step 3 | interval [0-4], 5 players, 3,5,0, currentPlayer, 0 | integer between [0, 3] 0,n/a,2,n/a,3, card already marked | false,true,UnsupportedOperationException |

### Step 4:
##### Each-choice
|             | System under test                                                           | Expected output                                        | Implemented? |
|-------------|-----------------------------------------------------------------------------|--------------------------------------------------------|--------------|
| Test Case 1 | 5 players, targeted 4th player, deck of 4 cards, 1st card chosen            | card is now in visible field for selected player, true | Yes          |
| Test Case 2 | 5 players, targeted 6th player                                              | UnsupportedOperationException, false                   | Yes          |
| Test Case 3 | 5 players, targeted -1st player                                             | UnsupportedOperationException, false                   | Yes          |
| Test Case 4 | 5 players, targeted 1st player, deck of 4 cards, 3th card chosen            | card is now in visible field for selected player, true | Yes          |
| Test Case 5 | 5 players, targeted yourself                                                | UnsupportedOperationException, false                   | Yes          |
| Test Case 6 | 5 players, targeted 1st player, deck of 4 cards, 5th card chosen            | UnsupportedOperationException, false                   | Yes          |
| Test Case 7 | 5 players, targeted 1st player, deck of 4 cards, -1st card chosen           | UnsupportedOperationException, false                   | Yes          |
| Test Case 8 | 5 players, targeted 1st player, deck of 4 cards, chosen card that is marked | UnsupportedOperationException, false                   | Yes          |