# BVA Analysis for Hand

## Hand Class

### Constructor 1: `Hand()`
### Step 1-3 Results
|        | Input  | Output                              |
|--------|--------|-------------------------------------|
| Step 1 | None   | Empty Hand object created           |
| Step 2 | N/A    | Hand with 0 cards                   |
| Step 3 | N/A    | size = 0                            |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Default constructor   | Empty hand, size = 0         | Yes          |

### Constructor 2: `Hand(List<Card> cards)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty list            | Empty hand                   | Yes          |
| Test Case 2 | List with 1 card      | Hand with 1 card             | Yes          |
| Test Case 3 | List with 5 cards     | Hand with 5 cards            | Yes          |
| Test Case 4 | null list             | Exception thrown             | Yes          |

### Copy Constructor: `Hand(Hand other)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty hand            | Empty copy                   | Yes          |
| Test Case 2 | Hand with cards       | Copy with same cards         | Yes          |
| Test Case 3 | null hand             | Exception thrown             | Yes          |

### Method 1: `public void addCard(Card card)`
### Step 1-3 Results
|        | Input   | Output                              |
|--------|---------|-------------------------------------|
| Step 1 | Card    | Card added to hand                  |
| Step 2 | Valid   | Size increases by 1                 |
| Step 3 | null    | Exception thrown                    |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Add to empty hand     | Size = 1                     | Yes          |
| Test Case 2 | Add to hand with 5    | Size = 6                     | Yes          |
| Test Case 3 | Add null card         | Exception thrown             | Yes          |
| Test Case 4 | Add duplicate card    | Both cards in hand           | Yes          |

### Method 2: `public void removeCard(Card card)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Remove existing card    | Card removed, size - 1       | Yes          |
| Test Case 2 | Remove from empty       | Exception or no change       | Yes          |
| Test Case 3 | Remove non-existing     | Exception or no change       | Yes          |
| Test Case 4 | Remove null             | Exception thrown             | Yes          |

### Method 3: `public Card removeCardAt(int index)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Index 0 (first)       | First card removed           | Yes          |
| Test Case 2 | Last valid index      | Last card removed            | Yes          |
| Test Case 3 | Negative index        | Exception thrown             | Yes          |
| Test Case 4 | Index >= size         | Exception thrown             | Yes          |

### Method 4: `public Card getCard(int index)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Index 0               | Returns first card           | Yes          |
| Test Case 2 | Valid index           | Returns correct card         | Yes          |
| Test Case 3 | Negative index        | Exception thrown             | Yes          |
| Test Case 4 | Index >= size         | Exception thrown             | Yes          |

### Method 5: `public List<Card> getCards()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty hand            | Empty list                   | Yes          |
| Test Case 2 | Hand with cards       | List of cards                | Yes          |
| Test Case 3 | Immutability          | Returns copy or unmodifiable | Yes          |

### Method 6: `public int size()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty hand            | 0                            | Yes          |
| Test Case 2 | Hand with 1 card      | 1                            | Yes          |
| Test Case 3 | Hand with 10 cards    | 10                           | Yes          |

### Method 7: `public boolean isEmpty()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Empty hand            | true                         | Yes          |
| Test Case 2 | Hand with 1 card      | false                        | Yes          |

### Method 8: `public boolean contains(Card card)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Card in hand          | true                         | Yes          |
| Test Case 2 | Card not in hand      | false                        | Yes          |
| Test Case 3 | null card             | false or exception           | Yes          |

### Method 9: `public boolean hasCardOfType(Class<? extends Card> type)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Has DefuseCard          | true for DefuseCard.class    | Yes          |
| Test Case 2 | No DefuseCard           | false for DefuseCard.class   | Yes          |
| Test Case 3 | null type               | Exception thrown             | Yes          |

### Method 10: `public Card getFirstCardOfType(Class<? extends Card> type)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Has matching card       | Returns first match          | Yes          |
| Test Case 2 | No matching card        | Returns null                 | Yes          |
| Test Case 3 | Multiple matches        | Returns first one            | Yes          |

### Boundary Cases
|             | Scenario                | Expected behavior            | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Max hand size           | All cards accessible         | Yes          |
| Test Case 2 | Add/remove same card    | Size returns to original     | Yes          |
| Test Case 3 | Iterate while removing  | Proper handling              | Yes          |
