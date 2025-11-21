# BVA Analysis for Catomic Bomb Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input                                             | (if more to consider for input)                     | Output                                                      |
|--------|---------------------------------------------------|-----------------------------------------------------|-------------------------------------------------------------|
| Step 1 | Deck                                              | Exploding Kittens                                   | deck modified with top cards being exploding kittens if any |
| Step 2 | Collection of cards -> cases: has cards, no cards | Cases: has some or doesn't have any                 | Collection of Cards                                         |
| Step 3 | Deck is empty, Deck has cards                     | has exploding kitten, doesn't have exploding kitten | Modified Deck, Exception                                    |

### Step 4:
##### Each-choice
|             | System under test                              | Expected output               | Implemented? |
|-------------|------------------------------------------------|-------------------------------|--------------|
| Test Case 1 | Deck is empty, does not have exploding kittens | UnsupportedOperationException | Yes          |
| Test Case 2 | Deck has cards, has exploding kittens          | Modified deck                 | Yes          |