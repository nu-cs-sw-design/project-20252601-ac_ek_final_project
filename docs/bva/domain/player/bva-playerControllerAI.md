# BVA Analysis for PlayerControllerAI

## PlayerControllerAI Class

### Constructor: `PlayerControllerAI(Player player)`
### Step 1-3 Results
|        | Input         | Output                              |
|--------|--------------|-------------------------------------|
| Step 1 | Player        | AI Controller object created        |
| Step 2 | Valid player  | Controller linked to player         |
| Step 3 | null player   | Exception thrown                    |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid player          | Controller created           | Yes          |
| Test Case 2 | null player           | Exception thrown             | Yes          |

### Constructor 2: `PlayerControllerAI(Player player, AIAgent agent)`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | Valid player and agent   | Controller with agent        | Yes          |
| Test Case 2 | Valid player, null agent | Default agent used           | Yes          |
| Test Case 3 | null player              | Exception thrown             | Yes          |

### Method 1: `public Action getAction(GameContext context)`
### Step 1-3 Results
|        | Input            | Output                              |
|--------|-----------------|-------------------------------------|
| Step 1 | Game context     | AI-determined action                |
| Step 2 | Valid context    | Strategic decision                  |
| Step 3 | Various states   | Different actions chosen            |

### Step 4:
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Safe game state            | May play card or pass        | Yes          |
| Test Case 2 | Exploding kitten on top    | Play defuse or avoid drawing | Yes          |
| Test Case 3 | null context               | Exception thrown             | Yes          |
| Test Case 4 | No playable cards          | PASS action                  | Yes          |
| Test Case 5 | Has attack, dangerous      | Plays attack                 | Yes          |

### Method 2: `public Card selectCard(List<Card> options)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single option           | Returns that card            | Yes          |
| Test Case 2 | Multiple options        | AI selection                 | Yes          |
| Test Case 3 | Contains Defuse         | May prioritize Defuse        | Yes          |
| Test Case 4 | Empty options           | Exception or null            | Yes          |

### Method 3: `public Player selectTarget(List<Player> targets)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single target           | Returns that target          | Yes          |
| Test Case 2 | Multiple targets        | Strategic selection          | Yes          |
| Test Case 3 | Target with few cards   | May select them              | Yes          |
| Test Case 4 | Target with many cards  | May avoid them               | Yes          |

### Method 4: `public int selectPosition(int deckSize)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | deckSize = 1          | 0                            | Yes          |
| Test Case 2 | deckSize = 10         | Strategic position           | Yes          |
| Test Case 3 | deckSize = 0          | Exception thrown             | Yes          |
| Test Case 4 | Place exploding kitten| Often near top               | Yes          |

### Method 5: `public boolean promptNope(Card targetCard)`
|             | System under test            | Expected output              | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Has Nope, targets AI          | true (use nope)              | Yes          |
| Test Case 2 | Has Nope, helps AI            | false (don't nope)           | Yes          |
| Test Case 3 | No Nope                       | false                        | Yes          |
| Test Case 4 | Attack targeting AI           | Likely nope                  | Yes          |

### Method 6: `public Player getPlayer()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid controller      | Returns player               | Yes          |

### Method 7: `public AIAgent getAgent()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Default agent         | Default AIAgent              | Yes          |
| Test Case 2 | Custom agent          | Custom AIAgent               | Yes          |

### AI Decision Making
|             | Scenario                    | Expected behavior            | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Low risk, many cards        | May save cards               | Yes          |
| Test Case 2 | High risk, has defuse       | May take risks               | Yes          |
| Test Case 3 | See the Future available    | Uses for information         | Yes          |
| Test Case 4 | Attack when threatened      | Passes turn to next          | Yes          |
| Test Case 5 | No defuse, cautious         | Plays Skip/Attack more       | Yes          |

### Edge Cases
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | All players eliminated     | Exception handling           | Yes          |
| Test Case 2 | Only one target available  | Selects that target          | Yes          |
| Test Case 3 | Empty hand                 | Must pass                    | Yes          |
| Test Case 4 | Maximum hand size          | Manages cards well           | Yes          |
