# BVA Analysis for PlayerManager

## PlayerManager Class

### Constructor 1: `PlayerManager(List<Player> players)`
### Step 1-3 Results
|        | Input           | Output                              |
|--------|----------------|-------------------------------------|
| Step 1 | List of players | PlayerManager object created        |
| Step 2 | 2-10 players    | Manager with player list            |
| Step 3 | 0, 1, 2, 10, 11 | Validation errors or success        |

### Step 4:
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | 2 players (min)         | Manager created              | Yes          |
| Test Case 2 | 10 players (max)        | Manager created              | Yes          |
| Test Case 3 | 1 player (below min)    | Exception thrown             | Yes          |
| Test Case 4 | 0 players               | Exception thrown             | Yes          |
| Test Case 5 | null list               | Exception thrown             | Yes          |
| Test Case 6 | 5 players               | Manager created              | Yes          |

### Method 1: `public List<Player> getPlayers()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 3 players             | List of 3 players            | Yes          |
| Test Case 2 | After elimination     | List without eliminated      | Yes          |
| Test Case 3 | Immutability          | Returns copy or unmodifiable | Yes          |

### Method 2: `public List<Player> getAlivePlayers()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | All alive             | All players                  | Yes          |
| Test Case 2 | 1 eliminated          | List without eliminated      | Yes          |
| Test Case 3 | All but 1 eliminated  | Single player list           | Yes          |

### Method 3: `public Player getCurrentPlayer()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | At game start         | First player                 | Yes          |
| Test Case 2 | After turn            | Current player               | Yes          |

### Method 4: `public void setCurrentPlayer(Player player)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Valid player in list    | Current player updated       | Yes          |
| Test Case 2 | Player not in list      | Exception thrown             | Yes          |
| Test Case 3 | null player             | Exception thrown             | Yes          |
| Test Case 4 | Eliminated player       | Exception thrown             | Yes          |

### Method 5: `public Player getNextPlayer()`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Normal order            | Next alive player            | Yes          |
| Test Case 2 | Skip eliminated         | Next alive player            | Yes          |
| Test Case 3 | At end of list          | Wraps to first              | Yes          |
| Test Case 4 | Reverse order           | Previous alive player        | Yes          |

### Method 6: `public void eliminatePlayer(Player player)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Valid alive player      | Player eliminated            | Yes          |
| Test Case 2 | Already eliminated      | No change or exception       | Yes          |
| Test Case 3 | null player             | Exception thrown             | Yes          |
| Test Case 4 | Last alive player       | Exception (game over)        | Yes          |

### Method 7: `public int getAlivePlayerCount()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 5 players, 0 elim     | 5                            | Yes          |
| Test Case 2 | 5 players, 2 elim     | 3                            | Yes          |
| Test Case 3 | 2 players, 1 elim     | 1 (game over)                | Yes          |

### Method 8: `public boolean isGameOver()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 2+ alive              | false                        | Yes          |
| Test Case 2 | 1 alive               | true                         | Yes          |
| Test Case 3 | 0 alive               | true (error state)           | Yes          |

### Method 9: `public Player getWinner()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 1 alive               | Returns winner               | Yes          |
| Test Case 2 | 2+ alive              | null or exception            | Yes          |
| Test Case 3 | 0 alive               | null or exception            | Yes          |

### Method 10: `public void setDirection(boolean clockwise)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Set to true           | Clockwise order              | Yes          |
| Test Case 2 | Set to false          | Counter-clockwise            | Yes          |

### Method 11: `public void reverseDirection()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Currently clockwise   | Now counter-clockwise        | Yes          |
| Test Case 2 | Currently counter     | Now clockwise                | Yes          |

### Player Ordering
|             | Scenario                    | Expected behavior            | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Normal clockwise            | 1→2→3→4→1                    | Yes          |
| Test Case 2 | Reversed                    | 1→4→3→2→1                    | Yes          |
| Test Case 3 | With eliminated players     | Skip eliminated              | Yes          |
| Test Case 4 | After targeted attack       | Jump to target               | Yes          |
