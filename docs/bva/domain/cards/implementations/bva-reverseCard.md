# BVA Analysis for ReverseCard

## ReverseCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | ReverseCard       | "Reverse"       | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                       |
|--------|------------------|----------------------------------------------|
| Step 1 | Game engine      | Play direction reversed, turn ends           |
| Step 2 | Valid engine     | Next player is previous in order             |
| Step 3 | Various players  | Direction flips correctly                    |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | 3 players, forward direction    | Direction becomes backward        | Yes          |
| Test Case 2 | Already reversed, play again    | Direction becomes forward         | Yes          |
| Test Case 3 | Current turn ends               | No draw required                  | Yes          |
| Test Case 4 | 2 players                       | Still reverses (same as skip)     | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | ReverseCard       | true            | Yes          |

### Direction Changes
|             | Initial direction | After Reverse | Implemented? |
|-------------|------------------|---------------|--------------|
| Test Case 1 | Clockwise        | Counter-clock | Yes          |
| Test Case 2 | Counter-clock    | Clockwise     | Yes          |

### Player Order (4 players: 1,2,3,4)
|             | Current | Direction | Next player | Implemented? |
|-------------|---------|-----------|-------------|--------------|
| Test Case 1 | 1       | Forward   | 4           | Yes          |
| Test Case 2 | 1       | Backward  | 2           | Yes          |
| Test Case 3 | 4       | Forward   | 3           | Yes          |
| Test Case 4 | 4       | Backward  | 1           | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | 2 players (minimum)       | Acts like Skip                | Yes          |
| Test Case 2 | 10 players (maximum)      | Full direction reversal       | Yes          |
| Test Case 3 | Multiple reverses         | Direction toggles each time   | Yes          |
