# BVA Analysis for PlayerCreator

## PlayerCreator Class

### Method 1: `public static Player createPlayer(String name)`
### Step 1-3 Results
|        | Input        | Output                              |
|--------|-------------|-------------------------------------|
| Step 1 | Name string  | New Player object                   |
| Step 2 | Valid name   | Player with empty hand              |
| Step 3 | null, empty  | Exception or default handling       |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid name "Alice"    | Player named Alice           | Yes          |
| Test Case 2 | Empty string ""       | Exception thrown             | Yes          |
| Test Case 3 | null name             | Exception thrown             | Yes          |
| Test Case 4 | Whitespace "   "      | Exception thrown             | Yes          |
| Test Case 5 | Name with numbers     | Player created               | Yes          |

### Method 2: `public static Player createPlayer(String name, boolean isHuman)`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | "Alice", true            | Human player                 | Yes          |
| Test Case 2 | "Bot1", false            | AI player                    | Yes          |
| Test Case 3 | null name, true          | Exception thrown             | Yes          |
| Test Case 4 | "", false                | Exception thrown             | Yes          |

### Method 3: `public static Player createHumanPlayer(String name)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid name            | Human player created         | Yes          |
| Test Case 2 | null name             | Exception thrown             | Yes          |
| Test Case 3 | Empty name            | Exception thrown             | Yes          |

### Method 4: `public static Player createAIPlayer(String name)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid name            | AI player created            | Yes          |
| Test Case 2 | null name             | Exception thrown             | Yes          |
| Test Case 3 | Empty name            | Exception thrown             | Yes          |

### Method 5: `public static List<Player> createPlayers(int count, int humanCount)`
### Step 1-3 Results
|        | Input               | Output                              |
|--------|---------------------|-------------------------------------|
| Step 1 | Total, human count  | List of players                     |
| Step 2 | Valid ranges        | Mix of human and AI                 |
| Step 3 | 2-10 total          | Boundary validation                 |

### Step 4:
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | 2 total, 1 human         | 1 human, 1 AI                | Yes          |
| Test Case 2 | 5 total, 5 human         | 5 humans                     | Yes          |
| Test Case 3 | 5 total, 0 human         | 5 AIs                        | Yes          |
| Test Case 4 | 1 total (invalid)        | Exception thrown             | Yes          |
| Test Case 5 | 11 total (invalid)       | Exception thrown             | Yes          |
| Test Case 6 | humanCount > total       | Exception thrown             | Yes          |
| Test Case 7 | Negative humanCount      | Exception thrown             | Yes          |
| Test Case 8 | 10 total, 10 human       | 10 humans (max)              | Yes          |

### Method 6: `public static List<Player> createPlayers(List<String> names)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | ["Alice", "Bob"]        | 2 players with names         | Yes          |
| Test Case 2 | Empty list              | Exception thrown             | Yes          |
| Test Case 3 | null list               | Exception thrown             | Yes          |
| Test Case 4 | List with null element  | Exception thrown             | Yes          |
| Test Case 5 | Duplicate names         | Exception or allowed         | Yes          |

### Boundary Value Tests
|             | Player count | Human count | Expected behavior           | Implemented? |
|-------------|-------------|-------------|----------------------------|--------------|
| Test Case 1 | 1           | 1           | Below minimum, exception    | Yes          |
| Test Case 2 | 2           | 0           | Minimum valid, 2 AIs       | Yes          |
| Test Case 3 | 2           | 2           | Minimum valid, 2 humans    | Yes          |
| Test Case 4 | 10          | 5           | Maximum valid, mixed       | Yes          |
| Test Case 5 | 10          | 10          | Maximum valid, all human   | Yes          |
| Test Case 6 | 11          | 5           | Above maximum, exception    | Yes          |
| Test Case 7 | 5           | 6           | humanCount > total, error   | Yes          |
| Test Case 8 | 5           | -1          | Negative, exception         | Yes          |

### Default Naming
|             | Scenario                | Expected behavior            | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | No names provided       | Default names generated      | Yes          |
| Test Case 2 | Mixed (some named)      | Defaults for unnamed         | Yes          |
| Test Case 3 | All names provided      | Uses provided names          | Yes          |
