# BVA Analysis for ImplodingKittensExpansion

## ImplodingKittensExpansion Class

### Method 1: `public String getId()`
|             | System under test          | Expected output       | Implemented? |
|-------------|---------------------------|----------------------|--------------|
| Test Case 1 | ImplodingKittensExpansion  | "imploding_kittens"  | Yes          |

### Method 2: `public String getDisplayName()`
|             | System under test          | Expected output       | Implemented? |
|-------------|---------------------------|----------------------|--------------|
| Test Case 1 | ImplodingKittensExpansion  | "Imploding Kittens"  | Yes          |

### Method 3: `public int getSelectionNumber()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | ImplodingKittensExpansion  | 3               | Yes          |

### Method 4: `public int getMaxPlayers()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | ImplodingKittensExpansion  | 6               | Yes          |

### Method 5: `public void addCardsToDeck(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Deck and player count    | Imploding kittens cards added       |
| Step 2 | Deck, integer 2-6        | Fixed number of expansion cards     |
| Step 3 | 2, 3, 4, 5, 6 players    | Same cards regardless of players    |

### Step 4 - Player Count Independence:
|             | System under test | Expected output                      | Implemented? |
|-------------|-------------------|--------------------------------------|--------------|
| Test Case 1 | 2 players         | All 19 imploding cards added         | Yes          |
| Test Case 2 | 4 players         | All 19 imploding cards added         | Yes          |
| Test Case 3 | 6 players         | All 19 imploding cards added         | Yes          |

### Method 6: `public void addPostDealCards(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                                   |
|--------|--------------------------|------------------------------------------|
| Step 1 | Deck and player count    | Imploding kitten card added              |
| Step 2 | Deck, integer 2-6        | Exactly 1 imploding kitten (face down)   |
| Step 3 | Any player count         | Same result                              |

### Step 4:
|             | System under test | Expected output                         | Implemented? |
|-------------|-------------------|-----------------------------------------|--------------|
| Test Case 1 | Any player count  | 1 Imploding Kitten added (NOT_DRAWN)    | Yes          |
| Test Case 2 | 2 players         | 1 Imploding Kitten + 1 exploding kitten | Yes          |
| Test Case 3 | 6 players         | 1 Imploding Kitten + 5 exploding kittens| Yes          |

### Method 7: `public int getExplodingKittenReduction()`
|             | System under test          | Expected output | Implemented? |
|-------------|---------------------------|-----------------|--------------|
| Test Case 1 | ImplodingKittensExpansion  | 1               | Yes          |

### Imploding Kitten States
|             | State                     | Expected behavior              | Implemented? |
|-------------|--------------------------|-------------------------------|--------------|
| Test Case 1 | NOT_DRAWN (initial)       | Card is face-down in deck     | Yes          |
| Test Case 2 | DRAWN (after first draw)  | Card is face-up on draw pile  | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior                | Implemented? |
|-------------|---------------------------|----------------------------------|--------------|
| Test Case 1 | Minimum players (2)        | 1 imploding, 1 exploding         | Yes          |
| Test Case 2 | Maximum players (6)        | 1 imploding, 5 exploding         | Yes          |
| Test Case 3 | Empty deck                 | All cards added successfully     | Yes          |
| Test Case 4 | Card state initialization  | Imploding kitten starts NOT_DRAWN| Yes          |
