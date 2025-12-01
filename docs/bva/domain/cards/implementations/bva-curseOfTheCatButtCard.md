# BVA Analysis for CurseOfTheCatButtCard

## CurseOfTheCatButtCard Class

### Method 1: `public String getName()`
|             | System under test        | Expected output            | Implemented? |
|-------------|--------------------------|----------------------------|--------------|
| Test Case 1 | CurseOfTheCatButtCard    | "Curse of the Cat Butt"    | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                            |
|--------|------------------|---------------------------------------------------|
| Step 1 | Game engine      | Target player blocked from viewing hand           |
| Step 2 | Valid engine     | Target plays blind until curse lifted             |
| Step 3 | Various targets  | Hand visibility set to false                      |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Target player selected          | Target cannot see their hand      | Yes          |
| Test Case 2 | Target must play blind          | Card selection random/unknown     | Yes          |
| Test Case 3 | Curse persists across turns     | Until another curse played        | Yes          |
| Test Case 4 | Can target any player           | Any opponent valid                | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test        | Expected output | Implemented? |
|-------------|--------------------------|-----------------|--------------|
| Test Case 1 | CurseOfTheCatButtCard    | true            | Yes          |

### Curse Mechanics
|             | Scenario                     | Hand visibility | Implemented? |
|-------------|------------------------------|-----------------|--------------|
| Test Case 1 | Before curse                 | Visible         | Yes          |
| Test Case 2 | After curse applied          | Hidden          | Yes          |
| Test Case 3 | Curse lifted                 | Visible again   | Yes          |
| Test Case 4 | Curse on already cursed      | Lifts curse     | Yes          |

### Target Selection
|             | Number of other players | Valid targets | Implemented? |
|-------------|------------------------|---------------|--------------|
| Test Case 1 | 1                      | 1             | Yes          |
| Test Case 2 | 4                      | 4             | Yes          |
| Test Case 3 | 9                      | 9             | Yes          |

### Curse Toggling
|             | Initial state | After 1st curse | After 2nd curse | Implemented? |
|-------------|--------------|-----------------|-----------------|--------------|
| Test Case 1 | Visible      | Hidden          | Visible         | Yes          |
| Test Case 2 | Hidden       | Visible         | Hidden          | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Curse self (if allowed)    | Player plays blind            | Yes          |
| Test Case 2 | Curse AI player            | AI unaffected (plays randomly)| Yes          |
| Test Case 3 | Double curse same target   | Toggles visibility back       | Yes          |
| Test Case 4 | Cursed player eliminated   | Curse ends with elimination   | Yes          |
