# BVA Analysis for BaseGameExpansion

## BaseGameExpansion Class

### Method 1: `public String getId()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | New BaseGameExpansion | "base"          | Yes          |

### Method 2: `public String getDisplayName()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | New BaseGameExpansion | "Base Game"     | Yes          |

### Method 3: `public int getSelectionNumber()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | New BaseGameExpansion | 0               | Yes          |

### Method 4: `public int getMaxPlayers()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | New BaseGameExpansion | 5               | Yes          |

### Method 5: `public void addCardsToDeck(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Deck and player count    | Base game cards added to deck       |
| Step 2 | Deck, integer 2-5        | Standard card set added             |
| Step 3 | 2, 3, 4, 5 players       | Same card count (player-independent)|

### Step 4:
|             | System under test      | Expected output               | Implemented? |
|-------------|------------------------|-------------------------------|--------------|
| Test Case 1 | 2 players              | 4 Attack cards added          | Yes          |
| Test Case 2 | 2 players              | 4 Skip cards added            | Yes          |
| Test Case 3 | 2 players              | 5 Nope cards added            | Yes          |
| Test Case 4 | 2 players              | 4 Favor cards added           | Yes          |
| Test Case 5 | 2 players              | 4 Shuffle cards added         | Yes          |
| Test Case 6 | 2 players              | 5 See The Future cards added  | Yes          |
| Test Case 7 | 2 players              | 4 of each Cat card type       | Yes          |
| Test Case 8 | 5 players              | Same card counts as 2 players | Yes          |

### Method 6: `public void addPostDealCards(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                                     |
|--------|--------------------------|-------------------------------------------|
| Step 1 | Deck and player count    | Exploding kittens and defuse cards added  |
| Step 2 | Deck, integer 2-5        | (players-1) kittens + remaining defuses   |
| Step 3 | 2, 3, 4, 5, 6 players    | Different counts of cards                 |

### Step 4:
|             | System under test | Expected output                    | Implemented? |
|-------------|-------------------|------------------------------------|--------------|
| Test Case 1 | 2 players         | 1 kitten, 4 extra defuse           | Yes          |
| Test Case 2 | 3 players         | 2 kittens, 3 extra defuse          | Yes          |
| Test Case 3 | 4 players         | 3 kittens, 2 extra defuse          | Yes          |
| Test Case 4 | 5 players         | 4 kittens, 1 extra defuse          | Yes          |
| Test Case 5 | 6 players         | 5 kittens, 0 extra defuse          | Yes          |

### Method 7: `public int getCardsPerPlayer()`
|             | System under test     | Expected output | Implemented? |
|-------------|----------------------|-----------------|--------------|
| Test Case 1 | BaseGameExpansion    | 4               | Yes          |

### Boundary Cases
|             | Boundary condition    | Expected behavior            | Implemented? |
|-------------|-----------------------|------------------------------|--------------|
| Test Case 1 | Minimum players (2)   | 1 exploding kitten added     | Yes          |
| Test Case 2 | Maximum players (5)   | 4 exploding kittens added    | Yes          |
| Test Case 3 | Empty deck            | All cards added successfully | Yes          |
