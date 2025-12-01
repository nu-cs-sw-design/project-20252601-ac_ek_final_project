# BVA Analysis for TargetedAttackCard

## TargetedAttackCard Class

### Method 1: `public String getName()`
|             | System under test    | Expected output      | Implemented? |
|-------------|---------------------|----------------------|--------------|
| Test Case 1 | TargetedAttackCard   | "Targeted Attack"    | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                            |
|--------|------------------|---------------------------------------------------|
| Step 1 | Game engine      | Choose target, they take remaining turns + 2      |
| Step 2 | Valid engine     | Any player can be targeted                        |
| Step 3 | Various targets  | Target receives turns, current turn ends          |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Player with 1 turn, target P2   | P2 gets 2 turns                   | Yes          |
| Test Case 2 | Player with 2 turns, target P3  | P3 gets 4 turns                   | Yes          |
| Test Case 3 | Target player not next in order | Order jumps to target             | Yes          |
| Test Case 4 | 5 players, target any           | Can skip multiple players         | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test    | Expected output | Implemented? |
|-------------|---------------------|-----------------|--------------|
| Test Case 1 | TargetedAttackCard   | true            | Yes          |

### Target Selection
|             | Number of players | Valid targets | Implemented? |
|-------------|------------------|---------------|--------------|
| Test Case 1 | 2                | 1 (other)     | Yes          |
| Test Case 2 | 5                | 4 (all others)| Yes          |
| Test Case 3 | 10               | 9 (all others)| Yes          |

### Turn Calculation
|             | Attacker turns | Target previous turns | Target new turns | Implemented? |
|-------------|---------------|----------------------|------------------|--------------|
| Test Case 1 | 1             | 0                    | 2                | Yes          |
| Test Case 2 | 2             | 0                    | 4                | Yes          |
| Test Case 3 | 1             | 1                    | 3                | Yes          |
| Test Case 4 | 3             | 2                    | 8                | Yes          |

### Difference from Regular Attack
|             | Aspect           | Attack Card     | Targeted Attack | Implemented? |
|-------------|-----------------|-----------------|-----------------|--------------|
| Test Case 1 | Target selection | Next player     | Any player      | Yes          |
| Test Case 2 | Skip capability  | None            | Skip players    | Yes          |
| Test Case 3 | Turn transfer    | Same            | Same            | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Target next player anyway  | Same as regular attack        | Yes          |
| Test Case 2 | Target previous player     | Goes backward in order        | Yes          |
| Test Case 3 | Cannot target self         | Must select another player    | Yes          |
| Test Case 4 | 2 players only             | Must target only other        | Yes          |
