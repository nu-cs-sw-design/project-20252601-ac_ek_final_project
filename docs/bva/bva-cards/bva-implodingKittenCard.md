# BVA Analysis for Imploding Kitten Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input 1                                            | Output                                                                 |
|--------|----------------------------------------------------|------------------------------------------------------------------------|
| Step 1 | if the card was previously drawn (yes/no)          | put card back into deck or remove player                               |
| Step 2 | boolean and possible interval [0 - #cards in deck] | Cases: collection of cards (deck) or collection of players (game)      |
| Step 3 | false + 0, false + #cards=4, true                  | Imploding kittens card put back into deck, player eliminated from game |

### Step 4:
##### Each-choice
|             | System under test                                                                    | Expected output                                                  | Implemented? |
|-------------|--------------------------------------------------------------------------------------|------------------------------------------------------------------|--------------|
| Test Case 1 | drawnBefore = false, index to insert card = 0                                        | Card added to deck using insertCardAtIndex (0) by current player | Yes          |
| Test Case 2 | drawnBefore = false, index to insert card = #cardsInDeck (assume deck size of 4) = 4 | Card added to deck using insertCardAtIndex (4) by current player | Yes          |
| Test Case 3 | drawnBefore = true                                                                   | Current player removed from players                              | Yes          |