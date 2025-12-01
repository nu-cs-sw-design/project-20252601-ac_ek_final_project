# BVA Analysis for StreakingKittenCard

## StreakingKittenCard Class

### Method 1: `public String getName()`
|             | System under test     | Expected output      | Implemented? |
|-------------|----------------------|----------------------|--------------|
| Test Case 1 | StreakingKittenCard   | "Streaking Kitten"   | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                                    |
|--------|------------------|-----------------------------------------------------------|
| Step 1 | Game engine      | Allows holding exploding kitten secretly                  |
| Step 2 | Valid engine     | Exploding kitten can be kept in hand                      |
| Step 3 | With/without EK  | Different behaviors based on hand contents                |

### Step 4:
|             | System under test                    | Expected output                    | Implemented? |
|-------------|-------------------------------------|------------------------------------|--------------|
| Test Case 1 | Draw EK with Streaking in hand      | Can keep EK hidden                 | Yes          |
| Test Case 2 | Streaking removed from hand         | EK in hand immediately explodes    | Yes          |
| Test Case 3 | Play Streaking with EK in hand      | EK can stay or be placed in deck   | Yes          |
| Test Case 4 | No EK in hand                       | Card cannot be played              | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | StreakingKittenCard   | false           | Yes          |

### Method 4: `public boolean isPlayable()`
|             | System under test                 | Expected output | Implemented? |
|-------------|----------------------------------|-----------------|--------------|
| Test Case 1 | During normal turn               | false           | Yes          |
| Test Case 2 | With Exploding Kitten drawn      | true            | Yes          |

### Exploding Kitten Interaction
|             | Scenario                           | Behavior                          | Implemented? |
|-------------|-----------------------------------|-----------------------------------|--------------|
| Test Case 1 | Draw EK, has Streaking             | EK can be kept secretly           | Yes          |
| Test Case 2 | Draw EK, no Streaking              | Normal EK rules apply             | Yes          |
| Test Case 3 | Has EK + Streaking, forced discard | Player must handle EK             | Yes          |
| Test Case 4 | Streaking stolen via favor         | EK in hand becomes problem        | Yes          |

### Protection Scenarios
|             | Cards in hand           | EK drawn | Result                    | Implemented? |
|-------------|------------------------|----------|---------------------------|--------------|
| Test Case 1 | Streaking only          | Yes      | Keep EK secretly          | Yes          |
| Test Case 2 | Streaking + Defuse      | Yes      | Choose: Streaking or Defuse | Yes        |
| Test Case 3 | No Streaking, Defuse    | Yes      | Must use Defuse           | Yes          |
| Test Case 4 | No Streaking, no Defuse | Yes      | Eliminated                | Yes          |

### Boundary Cases
|             | Boundary condition              | Expected behavior              | Implemented? |
|-------------|---------------------------------|-------------------------------|--------------|
| Test Case 1 | Only protection card in hand    | EK stays hidden               | Yes          |
| Test Case 2 | Multiple Streaking cards        | Can protect multiple EKs      | Yes          |
| Test Case 3 | Streaking stolen mid-game       | Must resolve EK immediately   | Yes          |
