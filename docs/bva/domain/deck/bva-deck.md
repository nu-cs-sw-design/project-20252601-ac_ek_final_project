# BVA Analysis for Deck Class

## Deck Class

### Constructor: `public Deck()`
|             | System under test | Expected output       | Implemented? |
|-------------|-------------------|-----------------------|--------------|
| Test Case 1 | New Deck()        | Empty deck, size = 0  | Yes          |

### Constructor: `public Deck(List<Card> cards)`
|             | System under test        | Expected output           | Implemented? |
|-------------|--------------------------|---------------------------|--------------|
| Test Case 1 | Deck with empty list     | Empty deck                | Yes          |
| Test Case 2 | Deck with 5 cards        | Deck size = 5             | Yes          |
| Test Case 3 | Deck with null list      | Empty deck or exception   | Yes          |

### Method 1: `public void addCard(Card card)`
### Step 1-3 Results
|        | Input             | Output                              |
|--------|-------------------|-------------------------------------|
| Step 1 | Card to add       | Card added to end of deck           |
| Step 2 | Card object       | Deck size increases by 1            |
| Step 3 | 0, 1, many cards  | Deck with 1, 2, n+1 cards           |

### Step 4:
|             | System under test                | Expected output              | Implemented? |
|-------------|----------------------------------|------------------------------|--------------|
| Test Case 1 | [], add Card                     | [Card]                       | Yes          |
| Test Case 2 | [Card1], add Card2               | [Card1, Card2]               | Yes          |
| Test Case 3 | [Card1, Card2, Card3], add Card4 | [Card1, Card2, Card3, Card4] | Yes          |

### Method 2: `public Card drawTopCard()`
|             | System under test                   | Expected output                                 | Implemented? |
|-------------|-------------------------------------|-------------------------------------------------|--------------|
| Test Case 1 | []                                  | UnsupportedOperationException                   | Yes          |
| Test Case 2 | [Card1]                             | Deck: [], Card: Card1                           | Yes          |
| Test Case 3 | [Card1, Card2, Card3, Card4, Card5] | Deck: [Card2, Card3, Card4, Card5], Card: Card1 | Yes          |

### Method 3: `public Card drawBottomCard()`
|             | System under test     | Expected output                   | Implemented? |
|-------------|-----------------------|-----------------------------------|--------------|
| Test Case 1 | []                    | UnsupportedOperationException     | Yes          |
| Test Case 2 | [Card1]               | Deck: [], Card: Card1             | Yes          |
| Test Case 3 | [Card1, Card2, Card3] | Deck: [Card1, Card2], Card: Card3 | Yes          |

### Method 4: `public void shuffle()`
|             | System under test   | Expected output                         | Implemented? |
|-------------|---------------------|-----------------------------------------|--------------|
| Test Case 1 | Deck has 0 cards    | UnsupportedOperationException           | Yes          |
| Test Case 2 | Deck has 1 card     | Same deck with 1 card                   | Yes          |
| Test Case 3 | Deck has 4 cards    | Deck has 4 cards, shuffled              | Yes          |
| Test Case 4 | Deck has 1000 cards | Deck has 1000 cards, shuffled           | Yes          |

### Method 5: `public List<Card> peekTopDeck(int numCardsToSee)`
|             | System under test                   | Expected output               | Implemented? |
|-------------|-------------------------------------|-------------------------------|--------------|
| Test Case 1 | Deck = [], i = 3                    | UnsupportedOperationException | Yes          |
| Test Case 2 | Deck = [Card1], i = 5               | [Card1]                       | Yes          |
| Test Case 3 | Deck = [Card1, ..., Card10], i = 5  | [Card1, ..., Card5]           | Yes          |
| Test Case 4 | Deck = [Card1, ..., Card10], i = 3  | [Card1, ..., Card3]           | Yes          |

### Method 6: `public void alterTopDeck(List<Card> topCards, List<Integer> newIndexes)`
|              | System under test                            | Expected output               | Implemented? |
|--------------|----------------------------------------------|-------------------------------|--------------|
| Test Case 1  | Cards have 0 cards, indexes: [0, 1, 2, 3, 4] | UnsupportedOperationException | Yes          |
| Test Case 2  | Cards have 1 card, indexes: [0]              | unmodified deck               | Yes          |
| Test Case 3  | Cards have 2 cards, indexes: [1, 0]          | Deck reordered                | Yes          |
| Test Case 4  | Cards have 3 cards, indexes: [2, 0, 1]       | Deck reordered                | Yes          |
| Test Case 5  | Cards have 5 cards, indexes: [0, 1, 2, 3, 4] | unmodified deck               | Yes          |
| Test Case 6  | Cards have 5 cards, indexes: [2, 4, 0, 1, 3] | Deck reordered                | Yes          |
| Test Case 7  | Cards have 1 card, indexes: [-1]             | IndexOutOfBoundsException     | Yes          |
| Test Case 8  | Cards have 2 card, indexes: [1, 1]           | IllegalArgumentException      | Yes          |

### Method 7: `public void swapTopAndBottom()`
|             | System under test                   | Expected output                     | Implemented? |
|-------------|-------------------------------------|-------------------------------------|--------------|
| Test Case 1 | Deck = []                           | UnsupportedOperationException       | Yes          |
| Test Case 2 | Deck = [Card1]                      | Deck = [Card1]                      | Yes          |
| Test Case 3 | Deck = [Card1, Card2, Card3, Card4] | Deck = [Card4, Card2, Card3, Card1] | Yes          |

### Method 8: `public void insertCardAtIndex(Card card, int i)`
|             | System under test     | Expected output            | Implemented? |
|-------------|-----------------------|----------------------------|--------------|
| Test Case 1 | Deck is empty, -1     | IndexOutOfBoundsException  | Yes          |
| Test Case 2 | Deck is empty, 0      | Deck with 1 card           | Yes          |
| Test Case 3 | Deck is empty, 1      | IndexOutOfBoundsException  | Yes          |
| Test Case 4 | Deck has 1 card, -2   | IndexOutOfBoundsException  | Yes          |
| Test Case 5 | Deck has 1 card, 1    | Deck with 2 cards          | Yes          |
| Test Case 6 | Deck has 1 card, 2    | IndexOutOfBoundsException  | Yes          |
| Test Case 7 | Deck has 4 cards, 3   | Deck with 5 cards          | Yes          |

### Method 9: `public int size()`
|             | System under test   | Expected output | Implemented? |
|-------------|---------------------|-----------------|--------------|
| Test Case 1 | Empty deck          | 0               | Yes          |
| Test Case 2 | Deck with 1 card    | 1               | Yes          |
| Test Case 3 | Deck with 10 cards  | 10              | Yes          |

### Method 10: `public boolean isEmpty()`
|             | System under test   | Expected output | Implemented? |
|-------------|---------------------|-----------------|--------------|
| Test Case 1 | Empty deck          | true            | Yes          |
| Test Case 2 | Deck with 1 card    | false           | Yes          |
