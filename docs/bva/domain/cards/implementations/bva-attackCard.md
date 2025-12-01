# BVA Analysis for AttackCard

## AttackCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | AttackCard        | "Attack"        | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                        |
|--------|------------------|-----------------------------------------------|
| Step 1 | Game engine      | Current player's turn ends, next player +2   |
| Step 2 | Valid engine     | Turn skipped, next player gets extra turn    |
| Step 3 | 2+ players       | Correct turn management                       |

### Step 4:
|             | System under test               | Expected output                         | Implemented? |
|-------------|--------------------------------|-----------------------------------------|--------------|
| Test Case 1 | Player with 1 turn remaining   | Turn ends, next player has 2 turns      | Yes          |
| Test Case 2 | Player with 2 turns remaining  | Turn ends, next player has 4 turns      | Yes          |
| Test Case 3 | 2 players in game              | Other player receives turns             | Yes          |
| Test Case 4 | 5 players in game              | Next player in order receives turns     | Yes          |
| Test Case 5 | Attack when next player has 1  | Next player now has 3 turns             | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | AttackCard        | true            | Yes          |

### Turn Calculation Logic
|             | Current turns | Added turns | Next player total | Implemented? |
|-------------|--------------|-------------|-------------------|--------------|
| Test Case 1 | 1            | 2           | 2                 | Yes          |
| Test Case 2 | 2            | 2           | 4                 | Yes          |
| Test Case 3 | 3            | 2           | 6                 | Yes          |
| Test Case 4 | 4            | 2           | 8                 | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Minimum turns (1)          | Next player gets 2 turns      | Yes          |
| Test Case 2 | Stacked attacks            | Turns multiply correctly      | Yes          |
| Test Case 3 | Last player in order       | First player becomes next     | Yes          |
