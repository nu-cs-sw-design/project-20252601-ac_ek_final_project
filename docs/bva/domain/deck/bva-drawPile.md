# BVA Analysis for DrawPile

## DrawPile Class

### Constructor 1: `DrawPile(List<Card> cards)`
### Step 1-3 Results
|        | Input           | Output                              |
|--------|----------------|-------------------------------------|
| Step 1 | List of cards   | DrawPile object created             |
| Step 2 | Valid list      | Pile with cards                     |
| Step 3 | Empty, null     | Specific handling                   |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | List with 10 cards    | Pile with 10 cards           | Yes          |
| Test Case 2 | Empty list            | Empty pile                   | Yes          |
| Test Case 3 | null list             | Exception thrown             | Yes          |
| Test Case 4 | Single card           | Pile with 1 card             | Yes          |

### Method 1: `public Card draw()`
### Step 1-3 Results
|        | Input         | Output                              |
|--------|--------------|-------------------------------------|
| Step 1 | Pile state    | Top card removed and returned       |
| Step 2 | Non-empty     | Card returned, size decreases       |
| Step 3 | Empty pile    | Exception thrown                    |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Pile with 5 cards     | Returns top card, size = 4   | Yes          |
| Test Case 2 | Pile with 1 card      | Returns card, size = 0       | Yes          |
| Test Case 3 | Empty pile            | Exception thrown             | Yes          |
| Test Case 4 | ExplodingKitten on top| Returns ExplodingKitten      | Yes          |

### Method 2: `public void addCardOnTop(Card card)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Add to non-empty      | Card at position 0           | Yes          |
| Test Case 2 | Add to empty pile     | Pile has 1 card              | Yes          |
| Test Case 3 | Add null card         | Exception thrown             | Yes          |

### Method 3: `public void addCardAtPosition(Card card, int position)`
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Position 0 (top)           | Card at top                  | Yes          |
| Test Case 2 | Position size (bottom)     | Card at bottom               | Yes          |
| Test Case 3 | Middle position            | Card at position             | Yes          |
| Test Case 4 | Negative position          | Exception thrown             | Yes          |
| Test Case 5 | Position > size            | Exception thrown             | Yes          |
| Test Case 6 | null card                  | Exception thrown             | Yes          |

### Method 4: `public Card peek()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Non-empty pile        | Top card (not removed)       | Yes          |
| Test Case 2 | Empty pile            | Exception or null            | Yes          |
| Test Case 3 | After draw            | New top card                 | Yes          |

### Method 5: `public List<Card> peekTop(int count)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | peek 3 from 10        | List of top 3 cards          | Yes          |
| Test Case 2 | peek 0                | Empty list                   | Yes          |
| Test Case 3 | peek more than size   | All cards or exception       | Yes          |
| Test Case 4 | Negative count        | Exception thrown             | Yes          |

### Method 6: `public int size()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty pile            | 0                            | Yes          |
| Test Case 2 | Pile with 10 cards    | 10                           | Yes          |
| Test Case 3 | After draw            | Size - 1                     | Yes          |

### Method 7: `public boolean isEmpty()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty pile            | true                         | Yes          |
| Test Case 2 | Non-empty pile        | false                        | Yes          |
| Test Case 3 | After drawing last    | true                         | Yes          |

### Method 8: `public void shuffle()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Pile with cards       | Cards reordered              | Yes          |
| Test Case 2 | Empty pile            | No effect                    | Yes          |
| Test Case 3 | Single card           | No change                    | Yes          |

### Method 9: `public List<Card> getCards()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Non-empty pile        | List of all cards            | Yes          |
| Test Case 2 | Empty pile            | Empty list                   | Yes          |
| Test Case 3 | Immutability check    | Returns copy                 | Yes          |

### Method 10: `public void removeCard(Card card)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Card in pile            | Card removed                 | Yes          |
| Test Case 2 | Card not in pile        | No change or exception       | Yes          |
| Test Case 3 | null card               | Exception thrown             | Yes          |

### Boundary Cases
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Insert at size + 1         | Exception                    | Yes          |
| Test Case 2 | Draw from size 1           | Pile becomes empty           | Yes          |
| Test Case 3 | Multiple operations        | Correct state maintained     | Yes          |
| Test Case 4 | Shuffle maintains size     | Same number of cards         | Yes          |

### Integration with Game
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Player draws              | Top card to player hand      | Yes          |
| Test Case 2 | Defuse reinserts          | Exploding kitten placed      | Yes          |
| Test Case 3 | See the Future            | Peek top 3                   | Yes          |
| Test Case 4 | Alter the Future          | Rearrange top 3              | Yes          |
