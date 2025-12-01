# BVA Analysis for GarbageCollectionCard

## GarbageCollectionCard Class

### Method 1: `public String getName()`
|             | System under test        | Expected output          | Implemented? |
|-------------|--------------------------|--------------------------|--------------|
| Test Case 1 | GarbageCollectionCard    | "Garbage Collection"     | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                                |
|--------|------------------|-------------------------------------------------------|
| Step 1 | Game engine      | All hands shuffled, redistributed evenly              |
| Step 2 | Valid engine     | Each player gets same # cards as before               |
| Step 3 | Various players  | Cards collected, shuffled, dealt back out             |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | 3 players, equal hand sizes     | Hands shuffled and redistributed  | Yes          |
| Test Case 2 | Players with different sizes    | Each keeps same card count        | Yes          |
| Test Case 3 | One player with 0 cards         | Still gets 0 cards back           | Yes          |
| Test Case 4 | All cards pooled then dealt     | Random redistribution             | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test        | Expected output | Implemented? |
|-------------|--------------------------|-----------------|--------------|
| Test Case 1 | GarbageCollectionCard    | true            | Yes          |

### Card Redistribution
|             | Player hand sizes before | After GC        | Implemented? |
|-------------|-------------------------|-----------------|--------------|
| Test Case 1 | [5, 5, 5]               | [5, 5, 5]       | Yes          |
| Test Case 2 | [3, 7, 2]               | [3, 7, 2]       | Yes          |
| Test Case 3 | [10, 0, 5]              | [10, 0, 5]      | Yes          |
| Test Case 4 | [4, 4]                  | [4, 4]          | Yes          |

### Strategic Implications
|             | Scenario                      | Effect                         | Implemented? |
|-------------|------------------------------|--------------------------------|--------------|
| Test Case 1 | Known player has Defuse       | Defuse may move to other player| Yes          |
| Test Case 2 | Player has marked cards       | Marked status may transfer     | Yes          |
| Test Case 3 | Player has many Nopes         | Nopes redistributed randomly   | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | 2 players only            | Cards swap between two         | Yes          |
| Test Case 2 | One player has all cards  | Keeps all cards                | Yes          |
| Test Case 3 | Total cards = 0           | No redistribution needed       | Yes          |
| Test Case 4 | Very uneven distribution  | Original counts preserved      | Yes          |
