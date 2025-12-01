# BVA Analysis for Player

## Player Class

### Constructor 1: `Player(String name)`
### Step 1-3 Results
|        | Input        | Output                              |
|--------|-------------|-------------------------------------|
| Step 1 | Name string  | Player object created               |
| Step 2 | Valid name   | Player with empty hand              |
| Step 3 | Empty, null  | Exception or success                |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid name "Alice"    | Player created               | Yes          |
| Test Case 2 | Empty string ""       | Exception thrown             | Yes          |
| Test Case 3 | null name             | Exception thrown             | Yes          |
| Test Case 4 | Whitespace only       | Exception thrown             | Yes          |
| Test Case 5 | Very long name        | Truncated or allowed         | Yes          |

### Constructor 2: `Player(String name, Hand hand)`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | Valid name and hand      | Player with hand             | Yes          |
| Test Case 2 | Valid name, null hand    | Exception thrown             | Yes          |
| Test Case 3 | null name, valid hand    | Exception thrown             | Yes          |

### Copy Constructor: `Player(Player other)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid player          | Deep copy created            | Yes          |
| Test Case 2 | null player           | Exception thrown             | Yes          |
| Test Case 3 | Player with cards     | Cards also copied            | Yes          |

### Method 1: `public String getName()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Player named "Bob"    | Returns "Bob"                | Yes          |
| Test Case 2 | Player named "Alice"  | Returns "Alice"              | Yes          |

### Method 2: `public Hand getHand()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | New player            | Empty hand                   | Yes          |
| Test Case 2 | Player with cards     | Hand with cards              | Yes          |
| Test Case 3 | Same reference        | Returns same Hand object     | Yes          |

### Method 3: `public void setHand(Hand hand)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Valid hand            | Hand updated                 | Yes          |
| Test Case 2 | null hand             | Exception thrown             | Yes          |
| Test Case 3 | Empty hand            | Hand set to empty            | Yes          |

### Method 4: `public boolean isAlive()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | New player            | true                         | Yes          |
| Test Case 2 | Eliminated player     | false                        | Yes          |

### Method 5: `public void eliminate()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Alive player          | Player marked dead           | Yes          |
| Test Case 2 | Already dead          | No change                    | Yes          |

### Method 6: `public boolean equals(Object o)`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | Same player              | true                         | Yes          |
| Test Case 2 | Different player         | false                        | Yes          |
| Test Case 3 | Same name different obj  | Depends on impl              | Yes          |
| Test Case 4 | null                     | false                        | Yes          |
| Test Case 5 | Non-Player object        | false                        | Yes          |

### Method 7: `public int hashCode()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Same player twice     | Same hash code               | Yes          |
| Test Case 2 | Equal players         | Equal hash codes             | Yes          |

### Method 8: `public String toString()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Player "Alice"        | String with "Alice"          | Yes          |
| Test Case 2 | Player with hand      | Includes hand info           | Yes          |

### State Transitions
|             | Initial state     | Action       | Final state            | Implemented? |
|-------------|------------------|--------------|------------------------|--------------|
| Test Case 1 | Alive, no cards   | Add card     | Alive, has card        | Yes          |
| Test Case 2 | Alive, has cards  | Eliminate    | Dead, has cards        | Yes          |
| Test Case 3 | Alive             | Draw exploding| Dead (if no defuse)   | Yes          |
