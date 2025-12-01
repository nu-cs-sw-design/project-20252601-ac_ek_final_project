# BVA Analysis for SwapTopAndBottomCard

## SwapTopAndBottomCard Class

### Method 1: `public String getName()`
|             | System under test      | Expected output          | Implemented? |
|-------------|------------------------|--------------------------|--------------|
| Test Case 1 | SwapTopAndBottomCard   | "Swap Top and Bottom"    | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                        |
|--------|------------------|-----------------------------------------------|
| Step 1 | Game engine      | Top and bottom cards of deck swap positions   |
| Step 2 | Valid engine     | Deck modified, card positions swapped         |
| Step 3 | Various decks    | Swap performed correctly                      |

### Step 4:
|             | System under test          | Expected output                   | Implemented? |
|-------------|---------------------------|-----------------------------------|--------------|
| Test Case 1 | Deck [A,B,C,D]             | Deck becomes [D,B,C,A]            | Yes          |
| Test Case 2 | Deck [A,B]                 | Deck becomes [B,A]                | Yes          |
| Test Case 3 | Deck [A]                   | Deck stays [A]                    | Yes          |
| Test Case 4 | EK at bottom swapped       | EK now at top                     | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test      | Expected output | Implemented? |
|-------------|------------------------|-----------------|--------------|
| Test Case 1 | SwapTopAndBottomCard   | true            | Yes          |

### Deck State Changes
|             | Before swap      | After swap       | Implemented? |
|-------------|-----------------|------------------|--------------|
| Test Case 1 | [1,2,3,4,5]     | [5,2,3,4,1]      | Yes          |
| Test Case 2 | [A,B]           | [B,A]            | Yes          |
| Test Case 3 | [X]             | [X]              | Yes          |
| Test Case 4 | [EK,A,B,Safe]   | [Safe,A,B,EK]    | Yes          |

### Strategic Uses
|             | Scenario                       | Benefit                       | Implemented? |
|-------------|-------------------------------|-------------------------------|--------------|
| Test Case 1 | Known EK at top (See Future)   | Move EK to bottom             | Yes          |
| Test Case 2 | Known safe card at bottom      | Bring safe card to top        | Yes          |
| Test Case 3 | Unknown deck state             | Shuffle alternative           | Yes          |

### Boundary Cases
|             | Boundary condition        | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | Single card deck          | No visible change             | Yes          |
| Test Case 2 | Two card deck             | Complete reversal             | Yes          |
| Test Case 3 | Empty deck                | Error or no operation         | Yes          |
| Test Case 4 | Swap then draw            | Former bottom now drawn       | Yes          |
