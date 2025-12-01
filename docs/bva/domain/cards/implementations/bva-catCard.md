# BVA Analysis for CatCard

## CatCard Class

### Method 1: `public String getName()`
|             | System under test                    | Expected output          | Implemented? |
|-------------|-------------------------------------|--------------------------|--------------|
| Test Case 1 | CatCard(CatCardType.TACO_CAT)        | "Taco Cat"               | Yes          |
| Test Case 2 | CatCard(CatCardType.HAIRY_POTATO_CAT)| "Hairy Potato Cat"       | Yes          |
| Test Case 3 | CatCard(CatCardType.CATTERMELON)     | "Cattermelon"            | Yes          |
| Test Case 4 | CatCard(CatCardType.RAINBOW_RALPHING_CAT) | "Rainbow Ralphing Cat" | Yes          |
| Test Case 5 | CatCard(CatCardType.BEARDED_CAT)     | "Bearded Cat"            | Yes          |

### Method 2: `public CatCardType getType()`
|             | System under test           | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | TACO_CAT type card          | CatCardType.TACO_CAT         | Yes          |
| Test Case 2 | HAIRY_POTATO_CAT type card  | CatCardType.HAIRY_POTATO_CAT | Yes          |
| Test Case 3 | CATTERMELON type card       | CatCardType.CATTERMELON      | Yes          |
| Test Case 4 | RAINBOW_RALPHING_CAT card   | CatCardType.RAINBOW_RALPHING_CAT | Yes      |
| Test Case 5 | BEARDED_CAT type card       | CatCardType.BEARDED_CAT      | Yes          |

### Method 3: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                         |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Paired cat effect: steal card from target      |
| Step 2 | Valid engine     | Target player selected, random card stolen     |
| Step 3 | 2+ players       | Card transferred to current player             |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Two matching cat cards played   | Target player loses random card   | Yes          |
| Test Case 2 | Target has 1 card               | That card stolen                  | Yes          |
| Test Case 3 | Target has multiple cards       | Random card selected              | Yes          |
| Test Case 4 | Target has no cards             | No card stolen                    | Yes          |

### Method 4: `public boolean isCatCard()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | Any CatCard       | true            | Yes          |

### Method 5: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | CatCard           | true            | Yes          |

### Pair Requirement
|             | Number of matching cards | Can be played? | Implemented? |
|-------------|-------------------------|----------------|--------------|
| Test Case 1 | 0                       | No             | Yes          |
| Test Case 2 | 1                       | No             | Yes          |
| Test Case 3 | 2                       | Yes            | Yes          |
| Test Case 4 | 3                       | Yes            | Yes          |

### Boundary Cases
|             | Boundary condition           | Expected behavior              | Implemented? |
|-------------|------------------------------|-------------------------------|--------------|
| Test Case 1 | Exact pair (2 cards)          | Effect applies                | Yes          |
| Test Case 2 | Different cat types           | Cannot pair                   | Yes          |
| Test Case 3 | Mix with feral cat            | Can pair with any cat type    | Yes          |
