# BVA Analysis for GameEngine

## GameEngine Class

### Method 1: `public void startGame()`
### Step 1-3 Results
|        | Input                | Output                              |
|--------|----------------------|-------------------------------------|
| Step 1 | Initialized game     | Game loop begins                    |
| Step 2 | Players and deck set | Turns begin cycling                 |
| Step 3 | 2-10 players         | Game progresses                     |

### Step 4:
|             | System under test        | Expected output              | Implemented? |
|-------------|--------------------------|------------------------------|--------------|
| Test Case 1 | 2 players, valid deck    | Game starts successfully     | Yes          |
| Test Case 2 | 5 players, valid deck    | All players can take turns   | Yes          |
| Test Case 3 | 10 players (max)         | Game handles all players     | Yes          |
| Test Case 4 | First player begins      | Player 1 is current player   | Yes          |

### Method 2: `public void processTurn(Player player, Action action)`
### Step 1-3 Results
|        | Input                | Output                              |
|--------|----------------------|-------------------------------------|
| Step 1 | Player and action    | Action processed, game state updated|
| Step 2 | Various action types | Different behaviors                 |
| Step 3 | PASS, PLAY_CARD      | Draw or play effect                 |

### Step 4:
|             | System under test          | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Action.Type.PASS           | Player draws card            | Yes          |
| Test Case 2 | Action.Type.PLAY_CARD      | Card effect executed         | Yes          |
| Test Case 3 | Action.Type.NOPE           | Last action negated          | Yes          |
| Test Case 4 | Invalid action             | Error handling               | Yes          |

### Method 3: `public void drawCard(Player player)`
|             | System under test          | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Normal card drawn          | Card added to hand           | Yes          |
| Test Case 2 | Exploding Kitten drawn     | Trigger defuse or eliminate  | Yes          |
| Test Case 3 | Imploding Kitten drawn     | Handle based on state        | Yes          |
| Test Case 4 | Empty deck                 | Error handling               | Yes          |

### Method 4: `public void eliminatePlayer(Player player)`
|             | System under test          | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Player with 3 others       | Player removed from game     | Yes          |
| Test Case 2 | 2 players left, 1 elim     | Game ends, winner declared   | Yes          |
| Test Case 3 | Cards discarded            | Cards go to discard pile     | Yes          |

### Method 5: `public Player getCurrentPlayer()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | At game start         | First player                 | Yes          |
| Test Case 2 | After turn            | Next player in order         | Yes          |
| Test Case 3 | After Attack          | Attacked player              | Yes          |

### Method 6: `public void nextTurn()`
|             | System under test           | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Normal forward play         | Next player clockwise        | Yes          |
| Test Case 2 | After Reverse               | Next player counter-clock    | Yes          |
| Test Case 3 | Player has extra turns      | Same player continues        | Yes          |
| Test Case 4 | After Targeted Attack       | Jumps to target              | Yes          |

### Method 7: `public List<Player> getPlayers()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Initial 5 players     | List of 5 players            | Yes          |
| Test Case 2 | After elimination     | List with fewer players      | Yes          |

### Method 8: `public int getDeckSize()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | After setup           | Initial deck size            | Yes          |
| Test Case 2 | After draws           | Reduced size                 | Yes          |

### Method 9: `public PlayerManager getPlayerManager()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Any state             | PlayerManager instance       | Yes          |

### Nope Handling
|             | Scenario                  | Expected behavior            | Implemented? |
|-------------|--------------------------|------------------------------|--------------|
| Test Case 1 | Card played, no nope      | Effect applies               | Yes          |
| Test Case 2 | Card played, 1 nope       | Effect cancelled             | Yes          |
| Test Case 3 | Card played, 2 nopes      | Effect applies               | Yes          |
| Test Case 4 | Defuse played (no nope)   | Cannot be noped              | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Last card in deck drawn    | Game continues               | Yes          |
| Test Case 2 | Single player remaining    | Game ends                    | Yes          |
| Test Case 3 | Maximum turns stacked      | Handled correctly            | Yes          |
