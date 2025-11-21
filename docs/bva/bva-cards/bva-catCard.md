# BVA Analysis for Cat Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                                                            | (if more to consider for input)                                                     | Output                                                                                                                    |
|--------|----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| Step 1 | player ID                                                                        | index of card from chosen player                                                    | chosen card gets added to hand of current player from chosen player's hand                                                |
| Step 2 | interval                                                                         | interval                                                                            | array of cards: current player's hand, array of cards: chosen player's hand                                               |
| Step 3 | [2 to #players] - only need to test 1 value - getPlayer method is tested in game | [0 to #cards-1] - only need to test 1 value - chooseCard method is tested in player | list of cards: current player's hand with one more card taken from list of cards: chosen player's hand with one less card |

### Step 4:
##### Each-choice
|             | System under test                                                                                                                                   | Expected output                                                             | Implemented? |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|--------------|
| Test Case 1 | Current player doesn't have two cat cards                                                                                                           | UnsupportedOperationException                                               | Yes          |
| Test Case 2 | Chosen player doesn't exist (ID > #players)                                                                                                         | NoSuchElementException                                                      | Yes          |
| Test Case 3 | Chosen player is themself (ID = 1, current player's ID = 1)                                                                                         | InputMismatchException                                                      | Yes          |
| Test Case 4 | Chosen card doesn't exist from mocked player (card index > #cards)                                                                                  | IndexOutOfBoundsException                                                   | Yes          |
| Test Case 5 | Card chosen successfully from mocked player (chosen player ID = 2 in game with 2 players, chosen card index = 0 with chosen player having >0 cards) | Chosen card removed from chosen player, chosen card added to current player | Yes          |