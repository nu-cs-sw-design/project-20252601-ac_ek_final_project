# BVA Analysis for SkipCard

## SkipCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | SkipCard          | "Skip"          | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                 |
|--------|------------------|----------------------------------------|
| Step 1 | Game engine      | Current turn ends without drawing      |
| Step 2 | Valid engine     | Turn counter decremented, no card draw |
| Step 3 | 1 or more turns  | Appropriate turn handling              |

### Step 4:
|             | System under test        | Expected output                    | Implemented? |
|-------------|-------------------------|------------------------------------|--------------|
| Test Case 1 | Player with 1 turn      | Turn ends, move to next player     | Yes          |
| Test Case 2 | Player with 2 turns     | 1 turn consumed, 1 turn remaining  | Yes          |
| Test Case 3 | Player with 3 turns     | 1 turn consumed, 2 turns remaining | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | SkipCard          | true            | Yes          |

### Turn Consumption
|             | Initial turns | After Skip | Next player? | Implemented? |
|-------------|--------------|------------|--------------|--------------|
| Test Case 1 | 1            | 0          | Yes          | Yes          |
| Test Case 2 | 2            | 1          | No           | Yes          |
| Test Case 3 | 3            | 2          | No           | Yes          |
| Test Case 4 | 4            | 3          | No           | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior           | Implemented? |
|-------------|--------------------------|----------------------------|--------------|
| Test Case 1 | Single turn remaining     | Ends turn, next player     | Yes          |
| Test Case 2 | Multiple turns from attack| Reduces by 1               | Yes          |
| Test Case 3 | Combined with attack      | Handles turn stack correctly| Yes          |
