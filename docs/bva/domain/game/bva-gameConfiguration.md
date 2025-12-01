# BVA Analysis for GameConfiguration

## GameConfiguration Class

### Constructor 1: `GameConfiguration(List<Expansion> expansions, int numPlayers)`
### Step 1-3 Results
|        | Input                   | Output                              |
|--------|------------------------|-------------------------------------|
| Step 1 | Expansions, player count| GameConfiguration object            |
| Step 2 | Valid ranges            | Configuration created               |
| Step 3 | 2-10 players, 1+ exp    | Success or validation error         |

### Step 4:
|             | System under test           | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | 2 players, 1 expansion      | Config created               | Yes          |
| Test Case 2 | 10 players (max)            | Config created               | Yes          |
| Test Case 3 | 1 player (below min)        | Exception thrown             | Yes          |
| Test Case 4 | 11 players (above max)      | Exception thrown             | Yes          |
| Test Case 5 | Empty expansion list        | Exception thrown             | Yes          |
| Test Case 6 | null expansion list         | Exception thrown             | Yes          |

### Method 1: `public List<Expansion> getExpansions()`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | 1 expansion             | List with 1 expansion        | Yes          |
| Test Case 2 | 3 expansions            | List with 3 expansions       | Yes          |
| Test Case 3 | Immutability check      | Returns copy or unmodifiable | Yes          |

### Method 2: `public int getNumPlayers()`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | 2 players               | Returns 2                    | Yes          |
| Test Case 2 | 5 players               | Returns 5                    | Yes          |
| Test Case 3 | 10 players              | Returns 10                   | Yes          |

### Method 3: `public void setNumPlayers(int numPlayers)`
|             | System under test       | Expected output              | Implemented? |
|-------------|------------------------|------------------------------|--------------|
| Test Case 1 | Set to 2                | numPlayers = 2               | Yes          |
| Test Case 2 | Set to 10               | numPlayers = 10              | Yes          |
| Test Case 3 | Set to 1 (invalid)      | Exception thrown             | Yes          |
| Test Case 4 | Set to 11 (invalid)     | Exception thrown             | Yes          |

### Method 4: `public void addExpansion(Expansion expansion)`
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Add valid expansion        | Expansion added              | Yes          |
| Test Case 2 | Add null expansion         | Exception thrown             | Yes          |
| Test Case 3 | Add duplicate expansion    | Handled (no duplicate)       | Yes          |

### Method 5: `public void removeExpansion(Expansion expansion)`
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Remove existing            | Expansion removed            | Yes          |
| Test Case 2 | Remove non-existing        | No change                    | Yes          |
| Test Case 3 | Remove last expansion      | Exception or allowed         | Yes          |

### Boundary Value Tests for Player Count
|             | Value    | Expected behavior              | Implemented? |
|-------------|----------|-------------------------------|--------------|
| Test Case 1 | 1        | Below minimum, exception       | Yes          |
| Test Case 2 | 2        | Minimum valid                 | Yes          |
| Test Case 3 | 5        | Mid-range                     | Yes          |
| Test Case 4 | 10       | Maximum valid                 | Yes          |
| Test Case 5 | 11       | Above maximum, exception       | Yes          |
| Test Case 6 | 0        | Invalid, exception             | Yes          |
| Test Case 7 | -1       | Negative, exception            | Yes          |

### Expansion Combinations
|             | Scenario                   | Expected behavior            | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Base game only             | Valid configuration          | Yes          |
| Test Case 2 | Base + Imploding           | Valid configuration          | Yes          |
| Test Case 3 | Base + Party Pack          | Valid configuration          | Yes          |
| Test Case 4 | Base + Streaking           | Valid configuration          | Yes          |
| Test Case 5 | All expansions             | Valid configuration          | Yes          |
