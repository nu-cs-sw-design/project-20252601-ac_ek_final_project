# BVA Analysis for See The Future Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                   | (if more to consider for input)                        | Output                                              |
|--------|-----------------------------------------|--------------------------------------------------------|-----------------------------------------------------|
| Step 1 | The deck                                | Number of cards to look at                             | Top 3 or 5 cards of deck, depending on card         |
| Step 2 | Collection of cards                     | Integer number of cards to see with two cases (3 or 5) | Collection of 3/5 cards from the beginning of deck  |
| Step 3 | Deck as ArrayList with 0, 1-5, >5 cards | Cases 3 or 5 cards to look at                          | ArrayList of 3 or 5 consecutive cards from index 0  |

### Step 4:
##### Each-choice
|             | System under test                      | Expected output                    | Implemented? |
|-------------|----------------------------------------|------------------------------------|--------------|
| Test Case 1 | Deck has 0 cards, look at top 3 cards  | UnsupportedOperationException      | Yes          |
| Test Case 2 | Deck has 0 cards, look at top 5 cards  | UnsupportedOperationException      | Yes          |
| Test Case 3 | Deck has 1 card, look at top 3 cards   | Top card (only card in deck)       | Yes          |
| Test Case 4 | Deck has 2 cards, look at top 5 cards  | Top two cards (only cards in deck) | Yes          |
| Test Case 5 | Deck has 3 cards, look at top 3 cards  | Top three cards (whole deck)       | Yes          |
| Test Case 6 | Deck has 5 cards, look at top 5 cards  | Top five cards (whole deck)        | Yes          |
| Test Case 7 | Deck has 7 cards, look at top 3 cards  | Top three cards                    | Yes          |
| Test Case 8 | Deck has 15 cards, look at top 5 cards | Top five cards                     | Yes          |