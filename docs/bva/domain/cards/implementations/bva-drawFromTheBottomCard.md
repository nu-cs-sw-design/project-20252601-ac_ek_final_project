# BVA Analysis for DrawFromTheBottomCard

## DrawFromTheBottomCard Class

### Method 1: `public String getName()`
|             | System under test        | Expected output          | Implemented? |
|-------------|--------------------------|--------------------------|--------------|
| Test Case 1 | DrawFromTheBottomCard    | "Draw From The Bottom"   | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                       |
|--------|------------------|----------------------------------------------|
| Step 1 | Game engine      | Draw bottom card instead of top              |
| Step 2 | Valid engine     | Turn ends with bottom card drawn             |
| Step 3 | Various decks    | Bottom card removed and added to hand        |

### Step 4:
|             | System under test          | Expected output                   | Implemented? |
|-------------|---------------------------|-----------------------------------|--------------|
| Test Case 1 | Deck with multiple cards   | Bottom card drawn                 | Yes          |
| Test Case 2 | Deck with 1 card           | That card drawn (same as top)     | Yes          |
| Test Case 3 | Turn ends after draw       | Move to next player               | Yes          |
| Test Case 4 | Draw is Exploding Kitten   | Normal EK rules apply             | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test        | Expected output | Implemented? |
|-------------|--------------------------|-----------------|--------------|
| Test Case 1 | DrawFromTheBottomCard    | true            | Yes          |

### Draw Position
|             | Deck size | Top card | Bottom card | Drawn card | Implemented? |
|-------------|-----------|----------|-------------|------------|--------------|
| Test Case 1 | 1         | A        | A           | A          | Yes          |
| Test Case 2 | 2         | A        | B           | B          | Yes          |
| Test Case 3 | 10        | A        | J           | J          | Yes          |

### Strategic Uses
|             | Scenario                      | Benefit                    | Implemented? |
|-------------|------------------------------|----------------------------|--------------|
| Test Case 1 | Known EK at top               | Avoid EK                   | Yes          |
| Test Case 2 | After See The Future          | Draw known safe card       | Yes          |
| Test Case 3 | Unknown deck state            | Random alternative         | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Single card deck          | Same card either way          | Yes          |
| Test Case 2 | Deck with EK at bottom    | EK drawn, must defuse         | Yes          |
| Test Case 3 | Empty deck                | Error or no draw              | Yes          |
