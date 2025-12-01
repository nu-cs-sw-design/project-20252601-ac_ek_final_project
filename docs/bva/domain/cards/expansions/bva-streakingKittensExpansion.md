# BVA Analysis for StreakingKittensExpansion

## StreakingKittensExpansion Class

### Method 1: `public String getId()`
|             | System under test          | Expected output       | Implemented? |
|-------------|---------------------------|----------------------|--------------|
| Test Case 1 | StreakingKittensExpansion  | "streaking_kittens"  | Yes          |

### Method 2: `public String getDisplayName()`
|             | System under test          | Expected output       | Implemented? |
|-------------|---------------------------|----------------------|--------------|
| Test Case 1 | StreakingKittensExpansion  | "Streaking Kittens"  | Yes          |

### Method 3: `public int getSelectionNumber()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | StreakingKittensExpansion  | 2               | Yes          |

### Method 4: `public int getMaxPlayers()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | StreakingKittensExpansion  | 5               | Yes          |

### Method 5: `public int getAdditionalExplodingKittens()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | StreakingKittensExpansion  | 1               | Yes          |

### Method 6: `public void addCardsToDeck(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Deck and player count    | Streaking kittens cards added       |
| Step 2 | Deck, integer 2-5        | Fixed number of expansion cards     |
| Step 3 | 2, 3, 4, 5 players       | Same cards regardless of players    |

### Step 4 - Player Count Independence:
|             | System under test | Expected output                    | Implemented? |
|-------------|-------------------|------------------------------------|--------------|
| Test Case 1 | 2 players         | All 14 streaking cards added       | Yes          |
| Test Case 2 | 3 players         | All 14 streaking cards added       | Yes          |
| Test Case 3 | 4 players         | All 14 streaking cards added       | Yes          |
| Test Case 4 | 5 players         | All 14 streaking cards added       | Yes          |

### Method 7: `public void addPostDealCards(Deck deck, int playerCount)`
|             | System under test | Expected output                         | Implemented? |
|-------------|-------------------|-----------------------------------------|--------------|
| Test Case 1 | 2 players         | 2 exploding kittens (1 base + 1 extra)  | Yes          |
| Test Case 2 | 3 players         | 3 exploding kittens (2 base + 1 extra)  | Yes          |
| Test Case 3 | 5 players         | 5 exploding kittens (4 base + 1 extra)  | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior                | Implemented? |
|-------------|---------------------------|----------------------------------|--------------|
| Test Case 1 | Minimum players (2)        | 14 cards added + 2 kittens       | Yes          |
| Test Case 2 | Maximum players (5)        | 14 cards added + 5 kittens       | Yes          |
| Test Case 3 | Empty deck                 | All cards added successfully     | Yes          |
| Test Case 4 | Deck with existing cards   | Cards appended correctly         | Yes          |
