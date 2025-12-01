# BVA Analysis for DefuseCard

## DefuseCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | DefuseCard        | "Defuse"        | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                         |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Exploding kitten defused, placed back in deck  |
| Step 2 | Valid engine     | Player prompted for insertion position         |
| Step 3 | Various positions| Kitten inserted at chosen index                |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Defuse with exploding kitten    | Kitten removed from player        | Yes          |
| Test Case 2 | Player chooses top (0)          | Kitten at top of deck             | Yes          |
| Test Case 3 | Player chooses bottom           | Kitten at bottom of deck          | Yes          |
| Test Case 4 | Player chooses middle           | Kitten at middle position         | Yes          |
| Test Case 5 | Deck is empty                   | Kitten becomes only card          | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | DefuseCard        | false           | Yes          |

### Method 4: `public boolean isPlayable()`
|             | System under test               | Expected output | Implemented? |
|-------------|--------------------------------|-----------------|--------------|
| Test Case 1 | During normal turn              | false           | Yes          |
| Test Case 2 | When exploding kitten drawn     | true            | Yes          |

### Insertion Position
|             | Deck size | Position chosen | Resulting index | Implemented? |
|-------------|-----------|-----------------|-----------------|--------------|
| Test Case 1 | 10        | 0 (top)         | 0               | Yes          |
| Test Case 2 | 10        | 10 (bottom)     | 10              | Yes          |
| Test Case 3 | 10        | 5 (middle)      | 5               | Yes          |
| Test Case 4 | 0         | 0               | 0               | Yes          |
| Test Case 5 | 1         | 0               | 0               | Yes          |
| Test Case 6 | 1         | 1               | 1               | Yes          |

### Boundary Cases
|             | Boundary condition       | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Empty deck               | Kitten becomes first card     | Yes          |
| Test Case 2 | Single card deck         | Positions 0 or 1 valid        | Yes          |
| Test Case 3 | Index = deck size        | Kitten at very bottom         | Yes          |
| Test Case 4 | Index = 0                | Kitten at very top            | Yes          |
| Test Case 5 | Index out of bounds      | Invalid index rejected        | Yes          |
