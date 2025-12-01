# BVA Analysis for PartyPackExpansion

## PartyPackExpansion Class

### Method 1: `public String getId()`
|             | System under test    | Expected output  | Implemented? |
|-------------|---------------------|------------------|--------------|
| Test Case 1 | PartyPackExpansion   | "party_pack"     | Yes          |

### Method 2: `public String getDisplayName()`
|             | System under test    | Expected output  | Implemented? |
|-------------|---------------------|------------------|--------------|
| Test Case 1 | PartyPackExpansion   | "Party Pack"     | Yes          |

### Method 3: `public int getSelectionNumber()`
|             | System under test    | Expected output  | Implemented? |
|-------------|---------------------|------------------|--------------|
| Test Case 1 | PartyPackExpansion   | 1                | Yes          |

### Method 4: `public int getMaxPlayers()`
|             | System under test    | Expected output | Implemented? |
|-------------|---------------------|-----------------|--------------|
| Test Case 1 | PartyPackExpansion   | 10              | Yes          |

### Method 5: `public boolean isBaseGameModifier()`
|             | System under test    | Expected output | Implemented? |
|-------------|---------------------|-----------------|--------------|
| Test Case 1 | PartyPackExpansion   | true            | Yes          |

### Method 6: `public void addCardsToDeck(Deck deck, int playerCount)`
### Step 1-3 Results
|        | Input                    | Output                                   |
|--------|--------------------------|------------------------------------------|
| Step 1 | Deck and player count    | Cards scaled for game size               |
| Step 2 | Deck, integer 2-10       | Small/medium/large game card counts      |
| Step 3 | 2-3, 4-7, 8-10 players   | Different scaled deck sizes              |

### Step 4 - Small Game (2-3 Players):
|             | System under test     | Expected output        | Implemented? |
|-------------|----------------------|------------------------|--------------|
| Test Case 1 | 2 players            | 3 Attack cards         | Yes          |
| Test Case 2 | 2 players            | 3 Skip cards           | Yes          |
| Test Case 3 | 2 players            | 3 Nope cards           | Yes          |
| Test Case 4 | 2 players            | 2 Favor cards          | Yes          |
| Test Case 5 | 2 players            | 2 Shuffle cards        | Yes          |
| Test Case 6 | 2 players            | 3 See The Future cards | Yes          |
| Test Case 7 | 3 players            | Same as 2 players      | Yes          |

### Step 4 - Medium Game (4-7 Players):
|             | System under test     | Expected output        | Implemented? |
|-------------|----------------------|------------------------|--------------|
| Test Case 1 | 4 players            | 4 Attack cards         | Yes          |
| Test Case 2 | 4 players            | 4 Skip cards           | Yes          |
| Test Case 3 | 4 players            | 5 Nope cards           | Yes          |
| Test Case 4 | 4 players            | 4 Favor cards          | Yes          |
| Test Case 5 | 4 players            | 4 Shuffle cards        | Yes          |
| Test Case 6 | 4 players            | 4 See The Future cards | Yes          |
| Test Case 7 | 7 players            | Same as 4 players      | Yes          |

### Step 4 - Large Game (8-10 Players):
|             | System under test     | Expected output        | Implemented? |
|-------------|----------------------|------------------------|--------------|
| Test Case 1 | 8 players            | 7 Attack cards         | Yes          |
| Test Case 2 | 8 players            | 7 Skip cards           | Yes          |
| Test Case 3 | 8 players            | 7 Nope cards           | Yes          |
| Test Case 4 | 8 players            | 6 Favor cards          | Yes          |
| Test Case 5 | 8 players            | 6 Shuffle cards        | Yes          |
| Test Case 6 | 8 players            | 7 See The Future cards | Yes          |
| Test Case 7 | 10 players           | Same as 8 players      | Yes          |

### Method 7: `public void addPostDealCards(Deck deck, int playerCount)`
|             | System under test | Expected output                         | Implemented? |
|-------------|-------------------|-----------------------------------------|--------------|
| Test Case 1 | 2 players         | 1 exploding kitten, extra defuse cards  | Yes          |
| Test Case 2 | 5 players         | 4 exploding kittens, extra defuse cards | Yes          |
| Test Case 3 | 10 players        | 9 exploding kittens, extra defuse cards | Yes          |

### Party Pack Specific Cards
|             | Card Type            | Small Game | Medium Game | Large Game | Implemented? |
|-------------|---------------------|------------|-------------|------------|--------------|
| Test Case 1 | Catomic Bomb         | 1          | 1           | 1          | Yes          |
| Test Case 2 | Super Skip           | 1          | 1           | 2          | Yes          |
| Test Case 3 | Garbage Collection   | 0          | 1           | 2          | Yes          |
| Test Case 4 | Curse of Cat Butt    | 0          | 2           | 3          | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Minimum players (2)        | Small game configuration      | Yes          |
| Test Case 2 | Small/medium boundary (3-4)| Correct scaling applied       | Yes          |
| Test Case 3 | Medium/large boundary (7-8)| Correct scaling applied       | Yes          |
| Test Case 4 | Maximum players (10)       | Large game configuration      | Yes          |
