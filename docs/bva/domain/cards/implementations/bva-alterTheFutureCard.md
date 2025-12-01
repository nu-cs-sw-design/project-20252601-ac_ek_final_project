# BVA Analysis for AlterTheFutureCard

## AlterTheFutureCard Class

### Method 1: `public String getName()`
|             | System under test     | Expected output       | Implemented? |
|-------------|----------------------|----------------------|--------------|
| Test Case 1 | AlterTheFutureCard    | "Alter The Future"   | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                    |
|--------|------------------|-------------------------------------------|
| Step 1 | Game engine      | Top 3 cards revealed and reorderable      |
| Step 2 | Valid engine     | Player sees top 3 cards, can reorder      |
| Step 3 | < 3 cards in deck| Shows available cards, allows reordering  |

### Step 4:
|             | System under test          | Expected output                   | Implemented? |
|-------------|---------------------------|-----------------------------------|--------------|
| Test Case 1 | Deck with 3+ cards        | Top 3 cards revealed              | Yes          |
| Test Case 2 | Deck with 2 cards         | Top 2 cards revealed              | Yes          |
| Test Case 3 | Deck with 1 card          | Top 1 card revealed               | Yes          |
| Test Case 4 | Empty deck                | No cards revealed                 | Yes          |
| Test Case 5 | Player reorders [2,0,1]   | Cards placed in new order         | Yes          |
| Test Case 6 | Player keeps same order   | Deck unchanged                    | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | AlterTheFutureCard    | true            | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Exactly 3 cards in deck   | All 3 shown and reorderable   | Yes          |
| Test Case 2 | Fewer than 3 cards        | Available cards shown         | Yes          |
| Test Case 3 | Large deck (50+ cards)    | Only top 3 shown              | Yes          |
