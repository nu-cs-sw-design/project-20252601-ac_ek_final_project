# BVA Analysis for DeckCreator

## DeckCreator Class

### Method 1: `public Deck createDeck(GameConfiguration config)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Game configuration       | Initialized deck with cards         |
| Step 2 | Valid config             | Deck with appropriate card set      |
| Step 3 | Various expansions       | Different card compositions         |

### Step 4:
|             | System under test               | Expected output                  | Implemented? |
|-------------|--------------------------------|----------------------------------|--------------|
| Test Case 1 | Base game config                | Standard deck cards              | Yes          |
| Test Case 2 | Party pack expansion            | Scaled cards for player count    | Yes          |
| Test Case 3 | Imploding kittens expansion     | Includes imploding kitten cards  | Yes          |
| Test Case 4 | Streaking kittens expansion     | Includes streaking kitten cards  | Yes          |
| Test Case 5 | Multiple expansions             | Combined card sets               | Yes          |

### Method 2: `public void addExpansionCards(Deck deck, ExpansionStrategy expansion, int playerCount)`
|             | System under test               | Expected output                  | Implemented? |
|-------------|--------------------------------|----------------------------------|--------------|
| Test Case 1 | BaseGameExpansion, 2 players    | Base cards added                 | Yes          |
| Test Case 2 | PartyPackExpansion, 5 players   | Medium game scaled cards         | Yes          |
| Test Case 3 | ImplodingKittens, 4 players     | Imploding expansion cards added  | Yes          |
| Test Case 4 | StreakingKittens, 3 players     | Streaking expansion cards added  | Yes          |

### Method 3: `public void addPostDealCards(Deck deck, List<ExpansionStrategy> expansions, int playerCount)`
### Step 1-3 Results
|        | Input                        | Output                                   |
|--------|------------------------------|------------------------------------------|
| Step 1 | Deck, expansions, count      | Exploding/imploding kittens added        |
| Step 2 | Post-deal additions          | Correct kitten counts                    |
| Step 3 | Various player counts        | n-1 exploding, modifiers applied         |

### Step 4:
|             | System under test               | Expected output                        | Implemented? |
|-------------|--------------------------------|----------------------------------------|--------------|
| Test Case 1 | Base game, 2 players            | 1 exploding kitten                     | Yes          |
| Test Case 2 | Base game, 5 players            | 4 exploding kittens                    | Yes          |
| Test Case 3 | Imploding expansion, 4 players  | 3 exploding + 1 imploding              | Yes          |
| Test Case 4 | Streaking expansion, 3 players  | 3 exploding kittens (2+1 extra)        | Yes          |

### Method 4: `public void shuffleDeck(Deck deck)`
|             | System under test        | Expected output              | Implemented? |
|-------------|--------------------------|------------------------------|--------------|
| Test Case 1 | Deck with cards          | Cards in random order        | Yes          |
| Test Case 2 | Single card deck         | Same card                    | Yes          |
| Test Case 3 | Large deck               | Full randomization           | Yes          |

### Deck Composition by Configuration
|             | Configuration          | Key cards included                    | Implemented? |
|-------------|------------------------|---------------------------------------|--------------|
| Test Case 1 | Base game only          | Attack, Skip, Nope, Favor, etc.       | Yes          |
| Test Case 2 | + Party Pack            | Catomic Bomb, Super Skip, etc.        | Yes          |
| Test Case 3 | + Imploding Kittens     | Reverse, Targeted Attack, Feral Cat   | Yes          |
| Test Case 4 | + Streaking Kittens     | Mark, Swap Top and Bottom, etc.       | Yes          |

### Boundary Cases
|             | Boundary condition           | Expected behavior              | Implemented? |
|-------------|------------------------------|-------------------------------|--------------|
| Test Case 1 | Minimum players (2)           | Correct card scaling          | Yes          |
| Test Case 2 | Maximum players (10)          | Correct card scaling          | Yes          |
| Test Case 3 | No expansions                 | Base game cards only          | Yes          |
| Test Case 4 | All expansions combined       | Full card set                 | Yes          |
