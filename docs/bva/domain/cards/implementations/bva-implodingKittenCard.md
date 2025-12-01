# BVA Analysis for ImplodingKittenCard

## ImplodingKittenCard Class

### Constructor: `public ImplodingKittenCard(DrawnBefore state)`
|             | System under test         | Expected output           | Implemented? |
|-------------|--------------------------|---------------------------|--------------|
| Test Case 1 | DrawnBefore.NOT_DRAWN     | Face-down imploding card  | Yes          |
| Test Case 2 | DrawnBefore.DRAWN         | Face-up imploding card    | Yes          |

### Method 1: `public String getName()`
|             | System under test     | Expected output      | Implemented? |
|-------------|----------------------|----------------------|--------------|
| Test Case 1 | ImplodingKittenCard   | "Imploding Kitten"   | Yes          |

### Method 2: `public DrawnBefore getDrawnState()`
|             | System under test         | Expected output          | Implemented? |
|-------------|--------------------------|--------------------------|--------------|
| Test Case 1 | NOT_DRAWN state card      | DrawnBefore.NOT_DRAWN    | Yes          |
| Test Case 2 | DRAWN state card          | DrawnBefore.DRAWN        | Yes          |

### Method 3: `public void setDrawnState(DrawnBefore state)`
|             | System under test              | Expected output          | Implemented? |
|-------------|-------------------------------|--------------------------|--------------|
| Test Case 1 | Set NOT_DRAWN to DRAWN         | State becomes DRAWN      | Yes          |
| Test Case 2 | Set DRAWN to NOT_DRAWN         | State becomes NOT_DRAWN  | Yes          |

### Method 4: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                              |
|--------|------------------|-----------------------------------------------------|
| Step 1 | Game engine      | Behavior depends on drawn state                     |
| Step 2 | Valid engine     | NOT_DRAWN: put back face-up, DRAWN: eliminate       |
| Step 3 | State changes    | First draw -> flip, second draw -> elimination      |

### Step 4:
|             | System under test               | Expected output                     | Implemented? |
|-------------|--------------------------------|-------------------------------------|--------------|
| Test Case 1 | First draw (NOT_DRAWN)          | Card returned to deck face-up       | Yes          |
| Test Case 2 | Second draw (DRAWN)             | Player eliminated (no defuse works) | Yes          |
| Test Case 3 | After first draw, state changes | DrawnBefore becomes DRAWN           | Yes          |
| Test Case 4 | Cannot be defused               | Player eliminated regardless        | Yes          |

### Method 5: `public boolean isNopeable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | ImplodingKittenCard   | false           | Yes          |

### Method 6: `public boolean isPlayable()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | ImplodingKittenCard   | false           | Yes          |

### State Transitions
|             | Initial state | Action        | Final state | Result         | Implemented? |
|-------------|--------------|---------------|-------------|----------------|--------------|
| Test Case 1 | NOT_DRAWN    | First draw    | DRAWN       | Card goes back | Yes          |
| Test Case 2 | DRAWN        | Second draw   | DRAWN       | Elimination    | Yes          |

### Placement After First Draw
|             | Deck size | Placement position | Implemented? |
|-------------|-----------|--------------------|--------------|
| Test Case 1 | 0         | Only card          | Yes          |
| Test Case 2 | 10        | Player chooses     | Yes          |
| Test Case 3 | Any       | Face-up in deck    | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | First draw by any player   | Card flipped, put back        | Yes          |
| Test Case 2 | Defuse in hand (2nd draw)  | Still eliminated              | Yes          |
| Test Case 3 | Last 2 players             | Elimination ends game         | Yes          |
