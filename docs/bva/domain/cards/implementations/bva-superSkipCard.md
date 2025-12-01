# BVA Analysis for SuperSkipCard

## SuperSkipCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | SuperSkipCard     | "Super Skip"    | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                       |
|--------|------------------|----------------------------------------------|
| Step 1 | Game engine      | Ends ALL remaining turns, not just one       |
| Step 2 | Valid engine     | Turn counter set to 0, move to next player   |
| Step 3 | 1+ turns         | All turns ended without drawing              |

### Step 4:
|             | System under test        | Expected output                    | Implemented? |
|-------------|-------------------------|------------------------------------|--------------|
| Test Case 1 | Player with 1 turn      | Turn ends, move to next player     | Yes          |
| Test Case 2 | Player with 2 turns     | Both turns end, next player        | Yes          |
| Test Case 3 | Player with 4 turns     | All 4 turns end, next player       | Yes          |
| Test Case 4 | After multiple attacks  | All stacked turns cleared          | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | SuperSkipCard     | true            | Yes          |

### Comparison with Regular Skip
|             | Turns remaining | Skip result | Super Skip result | Implemented? |
|-------------|-----------------|-------------|-------------------|--------------|
| Test Case 1 | 1               | 0, next     | 0, next           | Yes          |
| Test Case 2 | 2               | 1 remains   | 0, next           | Yes          |
| Test Case 3 | 4               | 3 remains   | 0, next           | Yes          |
| Test Case 4 | 6               | 5 remains   | 0, next           | Yes          |

### Strategic Uses
|             | Scenario                    | Super Skip value        | Implemented? |
|-------------|----------------------------|-------------------------|--------------|
| Test Case 1 | After 2x Attack            | Clears 4 turn debt      | Yes          |
| Test Case 2 | After 3x Attack            | Clears 6 turn debt      | Yes          |
| Test Case 3 | Normal single turn         | Same as regular skip    | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Single turn (minimum)     | Equivalent to Skip            | Yes          |
| Test Case 2 | Max stacked turns         | All cleared at once           | Yes          |
| Test Case 3 | Combined with Attack      | Counters Attack completely    | Yes          |
