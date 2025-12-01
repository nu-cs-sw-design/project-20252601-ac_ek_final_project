# BVA Analysis for Turn

## Turn Class

### Constructor 1: `Turn(Player player)`
### Step 1-3 Results
|        | Input         | Output                              |
|--------|--------------|-------------------------------------|
| Step 1 | Player        | Turn object created                 |
| Step 2 | Valid player  | Turn initialized with 1 remaining   |
| Step 3 | null vs valid | Exception or success                |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid player          | Turn created, turns = 1      | Yes          |
| Test Case 2 | null player           | Exception thrown             | Yes          |

### Constructor 2: `Turn(Player player, int turnsRemaining)`
|             | System under test         | Expected output              | Implemented? |
|-------------|--------------------------|------------------------------|--------------|
| Test Case 1 | Player, 1 turn            | Normal turn                  | Yes          |
| Test Case 2 | Player, 2 turns           | Extra turn (attack)          | Yes          |
| Test Case 3 | Player, 0 turns           | Exception thrown             | Yes          |
| Test Case 4 | Player, negative turns    | Exception thrown             | Yes          |

### Method 1: `public Player getPlayer()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid turn            | Returns player               | Yes          |
| Test Case 2 | Same as constructor   | Same player reference        | Yes          |

### Method 2: `public int getTurnsRemaining()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Initial turn          | Returns 1                    | Yes          |
| Test Case 2 | After attack          | Returns 2                    | Yes          |
| Test Case 3 | After targeted attack | Returns 3+                   | Yes          |

### Method 3: `public void decrementTurns()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 1 turn remaining      | 0 turns remaining            | Yes          |
| Test Case 2 | 2 turns remaining     | 1 turn remaining             | Yes          |
| Test Case 3 | Already at 0          | Exception or stays at 0      | Yes          |

### Method 4: `public void addTurns(int turns)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Add 1 turn            | Turns increased by 1         | Yes          |
| Test Case 2 | Add 2 turns           | Turns increased by 2         | Yes          |
| Test Case 3 | Add 0 turns           | No change                    | Yes          |
| Test Case 4 | Add negative turns    | Exception thrown             | Yes          |

### Method 5: `public boolean hasMoreTurns()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | 1 turn remaining      | true                         | Yes          |
| Test Case 2 | 0 turns remaining     | false                        | Yes          |
| Test Case 3 | 2 turns remaining     | true                         | Yes          |

### Method 6: `public void setPlayer(Player player)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Set valid player      | Player updated               | Yes          |
| Test Case 2 | Set null player       | Exception thrown             | Yes          |
| Test Case 3 | Set different player  | New player stored            | Yes          |

### Method 7: `public void setTurnsRemaining(int turns)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Set to 1              | 1 turn remaining             | Yes          |
| Test Case 2 | Set to 3              | 3 turns remaining            | Yes          |
| Test Case 3 | Set to 0              | Exception or allowed         | Yes          |
| Test Case 4 | Set to negative       | Exception thrown             | Yes          |

### Turn Flow Scenarios
|             | Scenario                     | Expected behavior            | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Normal turn ends             | Decrement to 0, next player  | Yes          |
| Test Case 2 | Attack played                | Target gets 2 turns          | Yes          |
| Test Case 3 | Attack on attack             | Turns stack (4 turns)        | Yes          |
| Test Case 4 | Skip played                  | Ends one turn                | Yes          |
| Test Case 5 | Skip on multiple turns       | Only ends current turn       | Yes          |

### Boundary Values for Turns
|             | Turn count | Expected behavior              | Implemented? |
|-------------|------------|-------------------------------|--------------|
| Test Case 1 | 0          | Invalid or no more turns       | Yes          |
| Test Case 2 | 1          | Normal single turn            | Yes          |
| Test Case 3 | 2          | Attack scenario               | Yes          |
| Test Case 4 | 4          | Double attack stack           | Yes          |
| Test Case 5 | 10+        | Edge case handling            | Yes          |
