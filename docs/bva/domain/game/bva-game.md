# BVA Analysis for Game Class

## Game Class

### Constructor: `public Game()`
|             | System under test                | Expected output                   | Implemented? |
|-------------|----------------------------------|-----------------------------------|--------------|
| Test Case 1 | New Game()                       | Empty players list, not game over | Yes          |
| Test Case 2 | New Game() - deck                | Deck is null initially            | Yes          |

### Copy Constructor: `public Game(Game other)`
|             | System under test              | Expected output                    | Implemented? |
|-------------|--------------------------------|------------------------------------|--------------|
| Test Case 1 | Copy with null other           | Empty players, null deck, not over | Yes          |
| Test Case 2 | Copy with null deck            | Players copied, null deck          | Yes          |
| Test Case 3 | Copy with deck and cards       | Deck copied (not same reference)   | Yes          |
| Test Case 4 | Copy with null players field   | Empty players list created         | Yes          |
| Test Case 5 | Copy with players              | Players copied, not same reference | Yes          |
| Test Case 6 | Copy gameOver state            | GameOver state preserved           | Yes          |

### Method 1: `public void setPlayers(List<Player> players)`
|             | System under test        | Expected output       | Implemented? |
|-------------|--------------------------|------------------------|--------------|
| Test Case 1 | Set with 2 players       | 2 players in game      | Yes          |
| Test Case 2 | Set with empty list      | Empty players list     | Yes          |
| Test Case 3 | Overwrite existing       | New list replaces old  | Yes          |
| Test Case 4 | Set with null            | Empty players list     | Yes          |

### Method 2: `public void setGameOver(boolean gameOver)`
|             | System under test | Expected output  | Implemented? |
|-------------|-------------------|------------------|--------------|
| Test Case 1 | Set to true       | isGameOver=true  | Yes          |
| Test Case 2 | Set to false      | isGameOver=false | Yes          |

### Method 3: `public boolean isGameOver()`
|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | Default state     | false           | Yes          |
| Test Case 2 | After set true    | true            | Yes          |

### Method 4: `public Deck getDeck()`
|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | Initially         | null            | Yes          |
| Test Case 2 | After setDeck     | Deck object     | Yes          |

### Method 5: `public void setDeck(Deck deck)`
|             | System under test | Expected output   | Implemented? |
|-------------|-------------------|-------------------|--------------|
| Test Case 1 | Set valid deck    | getDeck not null  | Yes          |
| Test Case 2 | Set null deck     | getDeck is null   | Yes          |

### Method 6: `public List<Player> getPlayers()`
|             | System under test | Expected output      | Implemented? |
|-------------|-------------------|----------------------|--------------|
| Test Case 1 | Initial state     | Empty list, not null | Yes          |
| Test Case 2 | After setPlayers  | List with players    | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior              | Implemented? |
|-------------|---------------------------|-------------------------------|--------------|
| Test Case 1 | Minimum players (2)        | Game plays with 2             | Yes          |
| Test Case 2 | Maximum players (10)       | Game plays with 10            | Yes          |
| Test Case 3 | Single player remaining    | Game ends                     | Yes          |
