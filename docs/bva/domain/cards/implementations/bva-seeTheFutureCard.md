# BVA Analysis for SeeTheFutureCard

## SeeTheFutureCard Class

### Method 1: `public String getName()`
|             | System under test   | Expected output      | Implemented? |
|-------------|--------------------|-----------------------|--------------|
| Test Case 1 | SeeTheFutureCard    | "See The Future"     | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                     |
|--------|------------------|-------------------------------------------|
| Step 1 | Game engine      | Player sees top 3 cards of draw pile      |
| Step 2 | Valid engine     | Top 3 cards revealed to current player    |
| Step 3 | < 3 cards        | Reveals all available cards               |

### Step 4:
|             | System under test      | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Deck with 3+ cards     | Top 3 cards shown            | Yes          |
| Test Case 2 | Deck with 2 cards      | 2 cards shown                | Yes          |
| Test Case 3 | Deck with 1 card       | 1 card shown                 | Yes          |
| Test Case 4 | Empty deck             | No cards shown               | Yes          |
| Test Case 5 | Cards order preserved  | Deck unchanged after viewing | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test   | Expected output | Implemented? |
|-------------|--------------------|--------------------|--------------|
| Test Case 1 | SeeTheFutureCard    | true            | Yes          |

### Cards Revealed
|             | Deck size | Cards revealed | Implemented? |
|-------------|-----------|----------------|--------------|
| Test Case 1 | 0         | 0              | Yes          |
| Test Case 2 | 1         | 1              | Yes          |
| Test Case 3 | 2         | 2              | Yes          |
| Test Case 4 | 3         | 3              | Yes          |
| Test Case 5 | 10        | 3              | Yes          |
| Test Case 6 | 50        | 3              | Yes          |

### Boundary Cases
|             | Boundary condition      | Expected behavior           | Implemented? |
|-------------|------------------------|----------------------------|--------------|
| Test Case 1 | Exactly 3 cards         | All 3 shown                | Yes          |
| Test Case 2 | Fewer than 3 cards      | Available cards shown      | Yes          |
| Test Case 3 | View doesn't alter deck | Cards remain in position   | Yes          |
