# BVA Analysis for Draw From The Bottom Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input 1                              | Input 2                                       | Output 1                                                   | Output 2                                            | Output 3                         |
|--------|--------------------------------------|-----------------------------------------------|------------------------------------------------------------|-----------------------------------------------------|----------------------------------|
| Step 1 | The deck                             | The current player's hand                     | the community card deck after drawing bottom card or error | the current player's hand after drawing bottom card | the current players turn is over |
| Step 2 | Collection of cards                  | Collection of cards                           | collection or exception                                    | collection                                          | boolean                          |
| Step 3 | Deck as ArrayList of size 0, 1, 2, 3 | Player's hand as ArrayList of size 0, 1, 2, 3 | Empty, 1 Card, 2+ Cards, UnsupportedOperationException     | 1 Card, 2+ Cards                                    | True False                       | 

### Step 4:
##### All-combination or each-choice: each-choice
|             | System under test                                          | Expected output                                            | Implemented? |
|-------------|------------------------------------------------------------|------------------------------------------------------------|--------------|
| Test Case 1 | deck=[], hand=[]                                           | UnsupportedOperationException                              | Yes          |
| Test Case 2 | deck=[Attack], hand=[]                                     | deck=[], hand=[Attack], isTurnOver = True                  | Yes          |
| Test Case 3 | deck=[Attack, Nope], hand = [Attack]                       | deck=[Attack], hand=[Attack, Nope], isTurnOver = True      | Yes          |
| Test Case 4 | deck=[Attack, Attack, Skip], hand = [Attack, Attack, Skip] | deck = [Attack, Attack], hand=[Attack, Attack, Skip, Skip] | Yes          |
| Test Case 5 | deck=[Attack], hand=[Attack, Nope]                         | deck=[], hand=[Attack, Nope, Attack], isTurnOver = True    | Yes          |