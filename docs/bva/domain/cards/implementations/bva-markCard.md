# BVA Analysis for MarkCard

## MarkCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | MarkCard          | "Mark"          | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                              |
|--------|------------------|-----------------------------------------------------|
| Step 1 | Game engine      | Target player's card becomes visible to all         |
| Step 2 | Valid engine     | One card marked, shown to everyone                  |
| Step 3 | Various targets  | Card added to visible cards list                    |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Target player with cards        | Random card marked visible        | Yes          |
| Test Case 2 | Target player with 1 card       | That card marked                  | Yes          |
| Test Case 3 | Marked card remains in hand     | Card still playable by target     | Yes          |
| Test Case 4 | Target player with no cards     | No mark applied                   | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | MarkCard          | true            | Yes          |

### Target Selection
|             | Number of other players | Valid targets | Implemented? |
|-------------|------------------------|---------------|--------------|
| Test Case 1 | 1                      | 1             | Yes          |
| Test Case 2 | 4                      | 4             | Yes          |
| Test Case 3 | 9                      | 9             | Yes          |

### Visibility Mechanics
|             | Scenario                      | Visibility result          | Implemented? |
|-------------|------------------------------|----------------------------|--------------|
| Test Case 1 | Card marked                   | All players see card       | Yes          |
| Test Case 2 | Marked card played            | Card no longer visible     | Yes          |
| Test Case 3 | Marked card stolen            | Stays with new owner       | Yes          |
| Test Case 4 | Multiple marks same player    | Multiple cards visible     | Yes          |

### Boundary Cases
|             | Boundary condition           | Expected behavior           | Implemented? |
|-------------|------------------------------|----------------------------|--------------|
| Test Case 1 | Target with 1 card           | That specific card marked   | Yes          |
| Test Case 2 | Target with 0 cards          | No operation                | Yes          |
| Test Case 3 | Mark same card twice         | Still only marked once      | Yes          |
| Test Case 4 | All cards in hand marked     | Full hand visibility        | Yes          |
