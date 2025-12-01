# BVA Analysis for UI Classes

## CardInfo

### Method 1: `public static String getName(Card card)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Card instance            | Card name as key for localization   |
| Step 2 | Any Card object          | String name of the card             |
| Step 3 | Various card types       | Matching name strings               |

### Step 4:
|             | System under test    | Expected output              | Implemented? |
|-------------|---------------------|------------------------------|--------------|
| Test Case 1 | AttackCard           | "attack"                     | Yes          |
| Test Case 2 | SkipCard             | "skip"                       | Yes          |
| Test Case 3 | DefuseCard           | "defuse"                     | Yes          |
| Test Case 4 | ExplodingKittenCard  | "exploding_kitten"           | Yes          |
| Test Case 5 | NopeCard             | "nope"                       | Yes          |
| Test Case 6 | CatCard (Taco Cat)   | "taco_cat"                   | Yes          |
| Test Case 7 | FeralCatCard         | "feral_cat"                  | Yes          |
| Test Case 8 | ImplodingKittenCard  | "imploding_kitten"           | Yes          |
| Test Case 9 | StreakingKittenCard  | "streaking_kitten"           | Yes          |

### Method 2: `public static String getDescription(Card card)`
|             | System under test    | Expected output                     | Implemented? |
|-------------|---------------------|-------------------------------------|--------------|
| Test Case 1 | AttackCard           | Attack card description key         | Yes          |
| Test Case 2 | SkipCard             | Skip card description key           | Yes          |
| Test Case 3 | SeeTheFutureCard     | See the future description key      | Yes          |
| Test Case 4 | FavorCard            | Favor card description key          | Yes          |