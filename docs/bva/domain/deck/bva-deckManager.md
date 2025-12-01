# BVA Analysis for DeckManager

## DeckManager Class

### Method 1: `public Card drawCard(Deck deck)`
### Step 1-3 Results
|        | Input        | Output                              |
|--------|--------------|-------------------------------------|
| Step 1 | Deck         | Top card drawn and returned         |
| Step 2 | Valid deck   | Card removed from deck              |
| Step 3 | Various sizes| Card or exception                   |

### Step 4:
|             | System under test     | Expected output                | Implemented? |
|-------------|-----------------------|--------------------------------|--------------|
| Test Case 1 | Empty deck            | UnsupportedOperationException  | Yes          |
| Test Case 2 | Deck with 1 card      | Card returned, deck empty      | Yes          |
| Test Case 3 | Deck with 10 cards    | Top card returned              | Yes          |

### Method 2: `public Card drawBottomCard(Deck deck)`
|             | System under test     | Expected output                | Implemented? |
|-------------|-----------------------|--------------------------------|--------------|
| Test Case 1 | Empty deck            | UnsupportedOperationException  | Yes          |
| Test Case 2 | Deck with 1 card      | Card returned, deck empty      | Yes          |
| Test Case 3 | Deck with 10 cards    | Bottom card returned           | Yes          |

### Method 3: `public void insertCard(Deck deck, Card card, int position)`
### Step 1-3 Results
|        | Input                | Output                              |
|--------|----------------------|-------------------------------------|
| Step 1 | Deck, card, position | Card inserted at position           |
| Step 2 | Valid inputs         | Deck size increases by 1            |
| Step 3 | Various positions    | Card at correct position            |

### Step 4:
|             | System under test           | Expected output                | Implemented? |
|-------------|----------------------------|--------------------------------|--------------|
| Test Case 1 | Position 0 (top)           | Card at top of deck            | Yes          |
| Test Case 2 | Position = size (bottom)    | Card at bottom of deck         | Yes          |
| Test Case 3 | Position = middle          | Card in middle                 | Yes          |
| Test Case 4 | Position < 0               | IndexOutOfBoundsException      | Yes          |
| Test Case 5 | Position > size            | IndexOutOfBoundsException      | Yes          |

### Method 4: `public void shuffle(Deck deck)`
|             | System under test     | Expected output              | Implemented? |
|-------------|-----------------------|------------------------------|--------------|
| Test Case 1 | Empty deck            | UnsupportedOperationException| Yes          |
| Test Case 2 | Deck with 1 card      | Same order                   | Yes          |
| Test Case 3 | Deck with many cards  | Random order                 | Yes          |

### Method 5: `public List<Card> peekTop(Deck deck, int count)`
|             | System under test           | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | count = 0                   | Empty list                   | Yes          |
| Test Case 2 | count = 3, deck size 10     | 3 cards returned             | Yes          |
| Test Case 3 | count = 5, deck size 3      | 3 cards returned             | Yes          |
| Test Case 4 | count = 3, empty deck       | Empty list                   | Yes          |

### Method 6: `public void swapTopAndBottom(Deck deck)`
|             | System under test     | Expected output                 | Implemented? |
|-------------|-----------------------|---------------------------------|--------------|
| Test Case 1 | Empty deck            | UnsupportedOperationException   | Yes          |
| Test Case 2 | Single card           | No change                       | Yes          |
| Test Case 3 | Multiple cards        | Top and bottom swapped          | Yes          |

### Method 7: `public int getDeckSize(Deck deck)`
|             | System under test     | Expected output | Implemented? |
|-------------|-----------------------|-----------------|--------------|
| Test Case 1 | Empty deck            | 0               | Yes          |
| Test Case 2 | Deck with 1 card      | 1               | Yes          |
| Test Case 3 | Deck with 50 cards    | 50              | Yes          |

### Method 8: `public void reorderTop(Deck deck, List<Card> cards, List<Integer> order)`
|             | System under test                | Expected output              | Implemented? |
|-------------|----------------------------------|------------------------------|--------------|
| Test Case 1 | Same order [0,1,2]               | No change                    | Yes          |
| Test Case 2 | Reversed [2,1,0]                 | Top 3 reversed               | Yes          |
| Test Case 3 | Arbitrary [1,2,0]                | Cards rearranged             | Yes          |
| Test Case 4 | Invalid order length             | IllegalArgumentException     | Yes          |
| Test Case 5 | Duplicate indices                | IllegalArgumentException     | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Draw from empty          | Exception thrown              | Yes          |
| Test Case 2 | Insert at position 0     | Becomes new top               | Yes          |
| Test Case 3 | Insert at deck size      | Becomes new bottom            | Yes          |
| Test Case 4 | Peek more than available | Returns available cards       | Yes          |
