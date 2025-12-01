# BVA Analysis for AIAgent

## AIAgent Class

### Constructor: `AIAgent()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Default constructor   | Agent with default strategy  | Yes          |

### Constructor 2: `AIAgent(Strategy strategy)`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | AGGRESSIVE strategy      | Aggressive agent             | Yes          |
| Test Case 2 | DEFENSIVE strategy       | Defensive agent              | Yes          |
| Test Case 3 | BALANCED strategy        | Balanced agent               | Yes          |
| Test Case 4 | null strategy            | Default or exception         | Yes          |

### Method 1: `public Action determineAction(GameContext context, Player player)`
### Step 1-3 Results
|        | Input                  | Output                              |
|--------|------------------------|-------------------------------------|
| Step 1 | Context and player     | Determined action                   |
| Step 2 | Valid inputs           | Strategic action                    |
| Step 3 | Various game states    | Different actions                   |

### Step 4:
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Safe state, has cards      | Strategic play               | Yes          |
| Test Case 2 | Dangerous state            | Defensive action             | Yes          |
| Test Case 3 | null context               | Exception thrown             | Yes          |
| Test Case 4 | null player                | Exception thrown             | Yes          |
| Test Case 5 | No cards in hand           | PASS action                  | Yes          |

### Method 2: `public Card chooseCardToPlay(List<Card> hand, GameContext context)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single playable card    | Returns that card            | Yes          |
| Test Case 2 | Multiple options        | Best card by strategy        | Yes          |
| Test Case 3 | Empty hand              | null                         | Yes          |
| Test Case 4 | Only Defuse in hand     | May not play it              | Yes          |

### Method 3: `public Player chooseTarget(List<Player> targets, GameContext context)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single target           | That target                  | Yes          |
| Test Case 2 | Target close to winning | That target                  | Yes          |
| Test Case 3 | Target with no defuse   | May target them              | Yes          |
| Test Case 4 | Empty targets           | null                         | Yes          |

### Method 4: `public int chooseInsertPosition(int deckSize, GameContext context)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | deckSize = 1          | 0                            | Yes          |
| Test Case 2 | deckSize = 5          | Strategic position           | Yes          |
| Test Case 3 | Next player threatened | Near top                     | Yes          |
| Test Case 4 | Self needs safety     | Further down                 | Yes          |

### Method 5: `public boolean shouldNope(Card targetCard, Player targetPlayer, GameContext context)`
|             | System under test            | Expected output              | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Attack targeting self         | true                         | Yes          |
| Test Case 2 | Favor targeting self          | true                         | Yes          |
| Test Case 3 | Action helps self             | false                        | Yes          |
| Test Case 4 | Action helps opponent         | Depends on strategy          | Yes          |
| Test Case 5 | Low value to nope             | false                        | Yes          |

### Method 6: `public double evaluateGameState(GameContext context, Player player)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Has defuse              | Higher score                 | Yes          |
| Test Case 2 | No defuse               | Lower score                  | Yes          |
| Test Case 3 | Many cards              | Moderate score               | Yes          |
| Test Case 4 | Few opponents           | Higher score                 | Yes          |

### Strategy Enum
|             | Strategy value | Description                          | Implemented? |
|-------------|---------------|--------------------------------------|--------------|
| Test Case 1 | AGGRESSIVE    | Plays cards early, attacks often     | Yes          |
| Test Case 2 | DEFENSIVE     | Saves cards, avoids risk             | Yes          |
| Test Case 3 | BALANCED      | Mix of both approaches               | Yes          |
| Test Case 4 | RANDOM        | Random decisions                     | Yes          |

### Decision Factors
|             | Factor                    | Impact on decision           | Implemented? |
|-------------|--------------------------|------------------------------|--------------|
| Test Case 1 | Deck size remaining       | Risk assessment              | Yes          |
| Test Case 2 | Number of opponents       | Target selection             | Yes          |
| Test Case 3 | Cards in hand             | Action selection             | Yes          |
| Test Case 4 | Exploding kittens left    | Risk calculation             | Yes          |
| Test Case 5 | Turn order position       | Timing decisions             | Yes          |

### Edge Cases
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Must draw (no options)     | Accepts risk                 | Yes          |
| Test Case 2 | All opponents have defuse  | Different targeting          | Yes          |
| Test Case 3 | Last card in deck          | Special handling             | Yes          |
| Test Case 4 | Exploding kitten known top | Uses avoidance cards         | Yes          |
