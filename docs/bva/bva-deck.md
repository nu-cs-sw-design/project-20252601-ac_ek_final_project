# BVA Analysis for Deck

## Method 1: ```public void addCard(Card card)```
### Step 1-3 Results
|        | Input                                | (if more to consider for input)                          | Output                                            |
|--------|--------------------------------------|----------------------------------------------------------|---------------------------------------------------|
| Step 1 | The deck                             | A card to add to the deck                                | A deck with one more card added at the end        |
| Step 2 | Collection of cards                  | Cases                                                    | Collection of cards one more card appended at end |
| Step 3 | Deck as ArrayList with 0, 1, 3 cards | Different types of cards (Alter The Future, Attack etc.) | Deck with 1, 2, 4 cards                           |

### Step 4:
##### Each-choice
###### BVA Catalog: Appending a single element
|             | System under test                | Expected output              | Implemented? |
|-------------|----------------------------------|------------------------------|--------------|
| Test Case 1 | [], add Card                     | [Card]                       | Yes          |
| Test Case 2 | [Card1], add Card2               | [Card1, Card2]               | Yes          |
| Test Case 3 | [Card1, Card2, Card3], add Card4 | [Card1, Card2, Card3, Card4] | Yes          |

## Method 2: ```public Card drawTopCard()```
### Step 1-3 Results
|        | Input                                | Output                                                        |
|--------|--------------------------------------|---------------------------------------------------------------|
| Step 1 | The deck                             | The top card from the deck                                    |
| Step 2 | Collection of cards                  | Collection of cards with one less element                     |
| Step 3 | Deck as ArrayList with 0, 1, 5 cards | Deck with 1 less card, which was removed from the first index |

### Step 4:
##### Each-choice
###### BVA Catalog: Deleting elements from any position
|             | System under test                   | Expected output                                 | Implemented? |
|-------------|-------------------------------------|-------------------------------------------------|--------------|
| Test Case 1 | []                                  | UnsupportedOperationException                   | Yes          |
| Test Case 2 | [Card1]                             | Deck: [], Card: Card1                           | Yes          |
| Test Case 3 | [Card1, Card2, Card3, Card4, Card5] | Deck: [Card2, Card3, Card4, Card5], Card: Card1 | Yes          |

## Method 3: ```public Card drawBottomCard()```
### Step 1-3 Results
|        | Input                                | Output                                                                 |
|--------|--------------------------------------|------------------------------------------------------------------------|
| Step 1 | The deck                             | The bottom card from the deck                                          |
| Step 2 | Collection of cards                  | Collection of cards with one less element                              |
| Step 3 | Deck as ArrayList with 0, 1, 3 cards | Deck with 1 less card, which was removed from the length-of-deck-index |

### Step 4:
##### Each-choice
###### BVA Catalog: Deleting elements from any position
|             | System under test     | Expected output                   | Implemented? |
|-------------|-----------------------|-----------------------------------|--------------|
| Test Case 1 | []                    | UnsupportedOperationException     | Yes          |
| Test Case 2 | [Card1]               | Deck: [], Card: Card1             | Yes          |
| Test Case 5 | [Card1, Card2, Card3] | Deck: [Card1, Card2], Card: Card3 | Yes          | 

## Method 4: ```public void Shuffle()```
### Step 1-3 Results
|        | Input                                      | Output                                              |
|--------|--------------------------------------------|-----------------------------------------------------|
| Step 1 | The deck                                   | Deck with all cards shuffled                        |
| Step 2 | Collection of cards                        | Collection of cards with all elements shuffled      |
| Step 3 | Deck as ArrayList with 0, 1, 4, 1000 cards | Deck with all cards in randomly different positions |

### Step 4:
##### Each-choice
|             | System under test   | Expected output                         | Implemented? |
|-------------|---------------------|-----------------------------------------|--------------|
| Test Case 1 | Deck has 0 cards    | IllegalArgumentException                | Yes          |
| Test Case 2 | Deck has 1 card     | Same deck with 1 card                   | Yes          |
| Test Case 3 | Deck has 4 cards    | Deck has 4 cards, which are shuffled    | Yes          |
| Test Case 4 | Deck has 1000 cards | Deck has 1000 cards, which are shuffled | Yes          |

## Method 5: ```public List<Card> peekTopDeck(int numCardsToSee)```
### Step 1-3 Results
|        | Input                                 | (if more to consider for input)        | Output                                          |
|--------|---------------------------------------|----------------------------------------|-------------------------------------------------|
| Step 1 | The deck                              | number of top cards on deck to be seen | top i cards on deck                             |
| Step 2 | Collection of cards                   | cases                                  | Collection of cards containing top i cards      |
| Step 3 | Deck as ArrayList with 0, 1, >1 cards | 3, 5                                   | top i cards, exception or remaining cards if <i |

### Step 4:
##### Each-choice
###### BVA Catalog: Using a subset of the collection
|             | System under test                   | Expected output               | Implemented? |
|-------------|-------------------------------------|-------------------------------|--------------|
| Test Case 1 | Deck = [], i = 3                    | UnsupportedOperationException | Yes          |
| Test Case 2 | Deck = [Card1], i = 5               | [Card1]                       | Yes          |
| Test Case 3 | Deck = [Card1, ..., Card10], i = 5  | [Card1, ..., Card5]           | Yes          |
| Test Case 4 | Deck = [Card1, ..., Card10], i = 3  | [Card1, ..., Card3]           | Yes          |

## Method 6: ```public void alterTopDeck(List<Card> topCards, List<Integer> newIndexes)```
### Step 1-3 Results
|        | Input                                      | (if more to consider for input)                                                                                            | Output                              |
|--------|--------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-------------------------------------|
| Step 1 | cards to be altered                        | new indexes of cards                                                                                                       | deck with altered top cards         |
| Step 2 | Collection of cards                        | Collection of Integers, cases: same order, different order, index not in range, wrong number of indexes, duplicate indexes | exception, deck collection of cards |
| Step 3 | Deck as ArrayList with 0, 1, 2, 3, 5 cards | [0, 1, 2, 3, 4], [2, 4, 0, 1, 3], [6, 4, 3, 2, 0], [2, 0, 1], [1, 0], [-1], [1, 1]                                         | collection of cards, exception      |

### Step 4:
##### Each-choice
###### BVA Catalog: Using a subset of the collection
|              | System under test                            | Expected output               | Implemented? |
|--------------|----------------------------------------------|-------------------------------|--------------|
| Test Case 1  | Cards have 0 cards, indexes: [0, 1, 2, 3, 4] | UnsupportedOperationException | Yes          |
| Test Case 2  | Cards have 1 card, indexes: [0]              | unmodified deck               | Yes          |
| Test Case 3  | Cards have 2 cards, indexes: [1, 0]          | Deck reordered                | Yes          |
| Test Case 4  | Cards have 3 cards, indexes: [2, 0, 1]       | Deck reordered                | Yes          |
| Test Case 5  | Cards have 3 cards, indexes: [2, 4, 0, 1, 3] | IllegalArgumentException      | Yes          |
| Test Case 6  | Cards have 5 cards, indexes: [0, 1, 2, 3, 4] | unmodified deck               | Yes          |
| Test Case 7  | Cards have 5 cards, indexes: [0, 1, 2, 3, 5] | IllegalArgumentException      | Yes          |
| Test Case 8  | Cards have 5 cards, indexes: [0, 1, 2, 3, 6] | IllegalArgumentException      | Yes          |
| Test Case 9  | Cards have 5 cards, indexes: [6, 4, 3, 2, 0] | IllegalArgumentException      | Yes          |
| Test Case 10 | Cards have 5 cards, indexes: [2, 4, 0, 1, 3] | Deck reordered                | Yes          |
| Test Case 11 | Cards have 1 card, indexes: [-1]             | IndexOutOfBoundsException     | Yes          |
| Test Case 12 | Cards have 2 card, indexes: [1, 1]           | IllegalArgumentException      | Yes          |

## Method 7: ```public void moveExplodingKittensToTop()```
### Step 1-3 Results
|        | Input                                       | (if more to consider for input)          | Output                              |
|--------|---------------------------------------------|------------------------------------------|-------------------------------------|
| Step 1 | The deck                                    | Number of Exploding Kittens              | deck                                |
| Step 2 | Collection of cards                         | Interval: [0, MAX number of players - 1] | exception, deck collection of cards |
| Step 3 | Deck as ArrayList with 0, 1, 40, 1200 cards | [0, 9]                                   | collection of cards, exception      |

### Step 4:
##### Each-choice
|              | System under test                       | Expected output                               | Implemented? |
|--------------|-----------------------------------------|-----------------------------------------------|--------------|
| Test Case 1  | Deck is empty, 0 exploding kittens      | UnsupportedOperationException                 | Yes          |
| Test Case 2  | Deck has 1 card, 1 exploding kitten     | Same Deck                                     | Yes          |
| Test Case 3  | Deck has 1 card, 0 exploding kittens    | Same Deck                                     | Yes          |
| Test Case 4  | Deck has 40 cards, 9 exploding kittens  | Deck with top 9 cards being exploding kittens | Yes          |
| Test Case 5  | Deck has 40 cards, 10 exploding kittens | IllegalArgumentException                      | Yes          | 
| Test Case 6  | Deck has 40 cards, 0 exploding kittens  | Same Deck                                     | Yes          |
| Test Case 7  | Deck has 1200 cards, 1 exploding kitten | Deck with top card being an exploding kitten  | Yes          |

## Method 8: ```public void swapTopAndBottom()```
### Step 1-3 Results
|        | Input | (if more to consider for input)      | Output                                             |
|--------|-------|--------------------------------------|----------------------------------------------------|
| Step 1 | Card  | The deck                             | Deck modified with top and bottom swapped          |
| Step 2 | Card  | Collection of cards                  | Collection of Cards                                |
| Step 3 | Card  | Deck as ArrayList with 0, 1, 4 cards | Deck as ArrayList with top and bottom card swapped |

### Step 4:
##### Each-choice
###### BVA Catalog: Overwriting the previous contents
|             | System under test                   | Expected output                     | Implemented? |
|-------------|-------------------------------------|-------------------------------------|--------------|
| Test Case 1 | Deck = []                           | UnsupportedOperationException       | Yes          |
| Test Case 2 | Deck = [Card1]                      | Deck = [Card1]                      | Yes          |
| Test Case 3 | Deck = [Card1, Card2, Card3, Card4] | Deck = [Card4, Card2, Card3, Card1] | Yes          |

## Method 9: ```public void insertCardAtIndex(Card card, int i)```
### Step 1-3 Results
|        | Input                                | (if more to consider for input)                                   | Output                                          |
|--------|--------------------------------------|-------------------------------------------------------------------|-------------------------------------------------|
| Step 1 | The deck                             | Position at which to insert card at                               | deck with one more card                         |
| Step 2 | Collection of cards                  | Interval: [0, length of deck - 1]                                 | deck with an additional card at specified index |
| Step 3 | Deck as ArrayList with 0, 1, 4 cards | Indexes including <0, 0, 1 to length of deck, and >length of deck | collection of cards                             |

### Step 4:
##### Each-choice
|             | System under test     | Expected output            | Implemented? |
|-------------|-----------------------|----------------------------|--------------|
| Test Case 1 | Deck is empty, -1     | IndexOutOfBoundsException  | Yes          |
| Test Case 2 | Deck is empty, 0      | Deck with 1 card           | Yes          |
| Test Case 3 | Deck is empty, 1      | IndexOutOfBoundsException  | Yes          |
| Test Case 4 | Deck has 1 card, -2   | IndexOutOfBoundsException  | Yes          |
| Test Case 5 | Deck has 1 card, 1    | Deck with 2 cards          | Yes          |
| Test Case 6 | Deck has 1 card, 2    | IndexOutOfBoundsException  | Yes          |
| Test Case 7 | Deck has 4 cards, -20 | IndexOutOfBoundsException  | Yes          |
| Test Case 8 | Deck has 4 cards, 3   | Deck with 5 cards          | Yes          | 
| Test Case 9 | Deck has 4 cards, 100 | IndexOutOfBoundsException  | Yes          |