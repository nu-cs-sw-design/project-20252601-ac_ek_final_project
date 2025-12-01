# BVA Analysis for ExplodingKittenCard

## ExplodingKittenCard Class

### Method 1: `public String getName()`
|             | System under test     | Expected output      | Implemented? |
|-------------|----------------------|----------------------|--------------|
| Test Case 1 | ExplodingKittenCard   | "Exploding Kitten"   | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                         |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Player must defuse or is eliminated            |
| Step 2 | Valid engine     | Check for defuse, trigger elimination if none  |
| Step 3 | Has/no defuse    | Survive or eliminate                           |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Player has Defuse card          | Defuse triggered, player survives | Yes          |
| Test Case 2 | Player has no Defuse            | Player eliminated from game       | Yes          |
| Test Case 3 | Player has multiple Defuses     | Only one Defuse used              | Yes          |
| Test Case 4 | Last player draws exploding     | Game over triggered               | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | ExplodingKittenCard   | false           | Yes          |

### Method 4: `public boolean isPlayable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | ExplodingKittenCard   | false           | Yes          |

### Elimination Logic
|             | Has Defuse | Has Streaking Kitten | Result              | Implemented? |
|-------------|------------|---------------------|---------------------|--------------|
| Test Case 1 | Yes        | No                  | Defuse used         | Yes          |
| Test Case 2 | No         | No                  | Player eliminated   | Yes          |
| Test Case 3 | Yes        | Yes                 | Defuse used         | Yes          |
| Test Case 4 | No         | Yes                 | Streaking protects  | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | 2 players, 1 eliminated   | Game over, winner declared    | Yes          |
| Test Case 2 | Multiple kittens in deck  | Each handled independently    | Yes          |
| Test Case 3 | First player draws kitten | Normal elimination process    | Yes          |
