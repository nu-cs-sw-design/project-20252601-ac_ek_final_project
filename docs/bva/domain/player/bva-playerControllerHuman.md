# BVA Analysis for PlayerControllerHuman

## PlayerControllerHuman Class

### Constructor: `PlayerControllerHuman(Player player)`
### Step 1-3 Results
|        | Input         | Output                              |
|--------|--------------|-------------------------------------|
| Step 1 | Player        | Controller object created           |
| Step 2 | Valid player  | Controller linked to player         |
| Step 3 | null player   | Exception thrown                    |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid player          | Controller created           | Yes          |
| Test Case 2 | null player           | Exception thrown             | Yes          |

### Method 1: `public Action getAction(GameContext context)`
### Step 1-3 Results
|        | Input            | Output                              |
|--------|-----------------|-------------------------------------|
| Step 1 | Game context     | Action from user input              |
| Step 2 | Valid context    | User chooses action                 |
| Step 3 | Various states   | Different actions available         |

### Step 4:
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | User chooses PASS          | Action.Type.PASS             | Yes          |
| Test Case 2 | User plays a card          | Action.Type.PLAY_CARD        | Yes          |
| Test Case 3 | null context               | Exception thrown             | Yes          |
| Test Case 4 | Empty hand, must draw      | Only PASS available          | Yes          |

### Method 2: `public Card selectCard(List<Card> options)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single option           | Returns that card            | Yes          |
| Test Case 2 | Multiple options        | User selection               | Yes          |
| Test Case 3 | Empty options           | Exception or null            | Yes          |
| Test Case 4 | null options            | Exception thrown             | Yes          |

### Method 3: `public Player selectTarget(List<Player> targets)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Single target           | Returns that target          | Yes          |
| Test Case 2 | Multiple targets        | User selection               | Yes          |
| Test Case 3 | Empty targets           | Exception or null            | Yes          |
| Test Case 4 | null targets            | Exception thrown             | Yes          |

### Method 4: `public int selectPosition(int deckSize)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | deckSize = 1          | 0 (only option)              | Yes          |
| Test Case 2 | deckSize = 10         | 0-9 (user choice)            | Yes          |
| Test Case 3 | deckSize = 0          | Exception thrown             | Yes          |
| Test Case 4 | Negative deckSize     | Exception thrown             | Yes          |

### Method 5: `public boolean promptNope(Card targetCard)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Player has Nope         | true/false (user choice)     | Yes          |
| Test Case 2 | Player has no Nope      | false                        | Yes          |
| Test Case 3 | null targetCard         | false                        | Yes          |

### Method 6: `public void displayMessage(String message)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid message         | Message shown to user        | Yes          |
| Test Case 2 | Empty message         | Nothing shown or handled     | Yes          |
| Test Case 3 | null message          | No error                     | Yes          |

### Method 7: `public Player getPlayer()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid controller      | Returns player               | Yes          |
| Test Case 2 | Same as constructor   | Same player reference        | Yes          |

### User Input Validation
|             | Input scenario          | Expected behavior            | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Valid card index        | Action executed              | Yes          |
| Test Case 2 | Invalid card index      | Prompt again                 | Yes          |
| Test Case 3 | Out of range            | Prompt again                 | Yes          |
| Test Case 4 | Non-numeric input       | Prompt again                 | Yes          |

### Edge Cases
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | No playable cards          | Only pass option             | Yes          |
| Test Case 2 | Timeout on input           | Default action or prompt     | Yes          |
| Test Case 3 | Multiple valid actions     | All displayed to user        | Yes          |
