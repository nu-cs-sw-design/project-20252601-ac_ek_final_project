# BVA Analysis for ShuffleCard

## ShuffleCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | ShuffleCard       | "Shuffle"       | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                |
|--------|------------------|---------------------------------------|
| Step 1 | Game engine      | Draw pile is shuffled randomly        |
| Step 2 | Valid engine     | Cards in deck in new random order     |
| Step 3 | Various sizes    | Deck shuffled regardless of size      |

### Step 4:
|             | System under test      | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Deck with 10 cards     | Cards in randomized order    | Yes          |
| Test Case 2 | Deck with 1 card       | No visible change            | Yes          |
| Test Case 3 | Deck with 50 cards     | Full shuffle                 | Yes          |
| Test Case 4 | Deck size preserved    | Same number of cards         | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | ShuffleCard       | true            | Yes          |

### Strategic Uses
|             | Scenario                      | Expected behavior           | Implemented? |
|-------------|------------------------------|----------------------------|--------------|
| Test Case 1 | After See The Future          | Known cards now shuffled   | Yes          |
| Test Case 2 | Kitten near top               | May move kitten position   | Yes          |
| Test Case 3 | Before own draw               | Unknown card order         | Yes          |

### Boundary Cases
|             | Boundary condition    | Expected behavior              | Implemented? |
|-------------|-----------------------|-------------------------------|--------------|
| Test Case 1 | Single card in deck    | Card stays (trivial shuffle)  | Yes          |
| Test Case 2 | Large deck            | Full randomization             | Yes          |
| Test Case 3 | Empty deck            | No operation/exception         | Yes          |
