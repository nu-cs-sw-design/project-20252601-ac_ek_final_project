# BVA Analysis for FavorCard

## FavorCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | FavorCard         | "Favor"         | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                          |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Target player gives a card to current player    |
| Step 2 | Valid engine     | Target selected, they choose card to give       |
| Step 3 | Various targets  | Card transferred to current player              |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Target has multiple cards       | Target chooses which to give      | Yes          |
| Test Case 2 | Target has 1 card               | Must give that card               | Yes          |
| Test Case 3 | Target has no cards             | No card given                     | Yes          |
| Test Case 4 | Target is AI                    | AI chooses card automatically     | Yes          |
| Test Case 5 | Target is human                 | Prompted to choose card           | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | FavorCard         | true            | Yes          |

### Target Selection
|             | Number of other players | Valid targets | Implemented? |
|-------------|------------------------|---------------|--------------|
| Test Case 1 | 1                      | 1             | Yes          |
| Test Case 2 | 4                      | 4             | Yes          |
| Test Case 3 | 9                      | 9             | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Target with 1 card         | That card is given            | Yes          |
| Test Case 2 | Target with 0 cards        | No card transferred           | Yes          |
| Test Case 3 | All players have 0 cards   | Card wasted                   | Yes          |
| Test Case 4 | Cannot target self         | Must select other player      | Yes          |
