# BVA Analysis for CatomicBombCard

## CatomicBombCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output  | Implemented? |
|-------------|------------------|------------------|--------------|
| Test Case 1 | CatomicBombCard   | "Catomic Bomb"   | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                               |
|--------|------------------|------------------------------------------------------|
| Step 1 | Game engine      | All EKs move to top, everyone but current defuses    |
| Step 2 | Valid engine     | Massive disruption, defuse cards used                |
| Step 3 | Various states   | Drastic game state change                            |

### Step 4:
|             | System under test               | Expected output                        | Implemented? |
|-------------|--------------------------------|----------------------------------------|--------------|
| Test Case 1 | 3 EKs in deck                   | All 3 moved to top of deck             | Yes          |
| Test Case 2 | Player who played is immune     | Current player doesn't need defuse     | Yes          |
| Test Case 3 | Other players must defuse       | Each other player loses a Defuse       | Yes          |
| Test Case 4 | Player without defuse           | That player is eliminated              | Yes          |
| Test Case 5 | Deck shuffled except EK on top  | EKs on top, rest shuffled below        | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | CatomicBombCard   | true            | Yes          |

### Effect on Other Players
|             | Player's hand       | After Catomic Bomb | Implemented? |
|-------------|--------------------|--------------------|--------------|
| Test Case 1 | Has Defuse          | Defuse removed     | Yes          |
| Test Case 2 | No Defuse           | Player eliminated  | Yes          |
| Test Case 3 | Multiple Defuses    | Loses one Defuse   | Yes          |

### Exploding Kitten Placement
|             | EKs in deck | Deck state after        | Implemented? |
|-------------|-------------|-------------------------|--------------|
| Test Case 1 | 1           | [EK, shuffled rest]     | Yes          |
| Test Case 2 | 3           | [EK,EK,EK, shuffled]    | Yes          |
| Test Case 3 | 0           | Just shuffled           | Yes          |

### Strategic Implications
|             | Scenario                       | Outcome                        | Implemented? |
|-------------|-------------------------------|--------------------------------|--------------|
| Test Case 1 | Know opponent lacks Defuse     | Eliminate that player          | Yes          |
| Test Case 2 | All opponents have Defuse      | Force Defuse usage             | Yes          |
| Test Case 3 | Current player draws next      | Draws EK, needs own Defuse     | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | 2 players, 1 EK            | Opponent either defuses or out | Yes          |
| Test Case 2 | All players have Defuse    | Everyone loses a Defuse        | Yes          |
| Test Case 3 | No EKs left in deck        | Just shuffle, no eliminations  | Yes          |
| Test Case 4 | Multiple players eliminated| Mass elimination possible      | Yes          |
