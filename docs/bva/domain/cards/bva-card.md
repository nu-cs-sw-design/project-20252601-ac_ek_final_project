# BVA Analysis for Card Class

## Card Interface/Abstract Class

### Method 1: `public String getName()`
### Step 1-3 Results
|        | Input | Output                |
|--------|-------|-----------------------|
| Step 1 | Card  | Name of the card type |
| Step 2 | Card  | String                |
| Step 3 | Card  | String name of card   |

### Step 4:
##### All-combination
|              | System under test          | Expected output         | Implemented? |
|--------------|----------------------------|-------------------------|--------------|
| Test Case 1  | Alter the future card      | "Alter The Future"      | Yes          |
| Test Case 2  | Attack card                | "Attack"                | Yes          |
| Test Case 3  | Bearded cat card           | "Bearded Cat"           | Yes          |
| Test Case 4  | Hairy potato cat card      | "Hairy Potato Cat"      | Yes          |
| Test Case 5  | Rainbow ralphing cat card  | "Rainbow Ralphing Cat"  | Yes          |
| Test Case 6  | Taco cat card              | "Taco Cat"              | Yes          |
| Test Case 7  | Cattermelon card           | "Cattermelon"           | Yes          |
| Test Case 8  | Catomic Bomb card          | "Catomic Bomb"          | Yes          |
| Test Case 9  | Curse of the Cat Butt card | "Curse of the Cat Butt" | Yes          |
| Test Case 10 | Defuse card                | "Defuse"                | Yes          |
| Test Case 11 | Draw from the Bottom card  | "Draw From The Bottom"  | Yes          |
| Test Case 12 | Exploding Kitten card      | "Exploding Kitten"      | Yes          |
| Test Case 13 | Favor card                 | "Favor"                 | Yes          |
| Test Case 14 | Feral Cat card             | "Feral Cat"             | Yes          |
| Test Case 15 | Garbage Collection card    | "Garbage Collection"    | Yes          |
| Test Case 16 | Imploding Kitten card      | "Imploding Kitten"      | Yes          |
| Test Case 17 | Mark card                  | "Mark"                  | Yes          |
| Test Case 18 | Nope card                  | "Nope"                  | Yes          |
| Test Case 19 | Reverse card               | "Reverse"               | Yes          |
| Test Case 20 | See the Future card        | "See The Future"        | Yes          |
| Test Case 21 | Shuffle card               | "Shuffle"               | Yes          |
| Test Case 22 | Skip card                  | "Skip"                  | Yes          |
| Test Case 23 | Streaking Kitten card      | "Streaking Kitten"      | Yes          |
| Test Case 24 | Super Skip card            | "Super Skip"            | Yes          |
| Test Case 25 | Swap Top and Bottom card   | "Swap Top and Bottom"   | Yes          |
| Test Case 26 | Targeted Attack card       | "Targeted Attack"       | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input       | Output                            |
|--------|-------------|-----------------------------------|
| Step 1 | Game engine | Card effect applied to game state |
| Step 2 | GameEngine  | Modified game state               |
| Step 3 | Valid engine| Effect executed successfully      |

### Step 4:
|             | System under test           | Expected output              | Implemented? |
|-------------|-----------------------------|------------------------------|--------------|
| Test Case 1 | Card on valid game state    | Effect applied correctly     | Yes          |
| Test Case 2 | Card with necessary context | Required decisions prompted  | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test       | Expected output | Implemented? |
|-------------|-------------------------|-----------------|--------------|
| Test Case 1 | Most action cards       | true            | Yes          |
| Test Case 2 | Defuse card             | false           | Yes          |
| Test Case 3 | Exploding Kitten card   | false           | Yes          |
| Test Case 4 | Nope card               | true            | Yes          |

### Method 4: `public boolean isCatCard()`
|             | System under test       | Expected output | Implemented? |
|-------------|-------------------------|-----------------|--------------|
| Test Case 1 | CatCard (any type)      | true            | Yes          |
| Test Case 2 | FeralCatCard            | true            | Yes          |
| Test Case 3 | AttackCard              | false           | Yes          |
| Test Case 4 | SkipCard                | false           | Yes          |
| Test Case 5 | DefuseCard              | false           | Yes          |

### Method 5: `public String getDescription()`
|             | System under test    | Expected output                | Implemented? |
|-------------|---------------------|--------------------------------|--------------|
| Test Case 1 | Any card type       | Non-null description string    | Yes          |
| Test Case 2 | CatCard variants    | Cat-specific description       | Yes          |
