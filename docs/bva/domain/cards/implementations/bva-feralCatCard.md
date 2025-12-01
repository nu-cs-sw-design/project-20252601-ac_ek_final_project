# BVA Analysis for FeralCatCard

## FeralCatCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | FeralCatCard      | "Feral Cat"     | Yes          |

### Method 2: `public boolean isCatCard()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | FeralCatCard      | true            | Yes          |

### Method 3: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                          |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Acts as wildcard for cat card pairs            |
| Step 2 | Valid engine     | Can pair with any cat card type                |
| Step 3 | Various pairs    | Steal effect when paired                       |

### Step 4:
|             | System under test               | Expected output                   | Implemented? |
|-------------|--------------------------------|-----------------------------------|--------------|
| Test Case 1 | Paired with another Feral Cat   | Steal effect activates            | Yes          |
| Test Case 2 | Paired with Taco Cat            | Steal effect activates            | Yes          |
| Test Case 3 | Paired with Hairy Potato Cat    | Steal effect activates            | Yes          |
| Test Case 4 | Paired with Bearded Cat         | Steal effect activates            | Yes          |
| Test Case 5 | Paired with Cattermelon         | Steal effect activates            | Yes          |
| Test Case 6 | Paired with Rainbow Ralphing    | Steal effect activates            | Yes          |
| Test Case 7 | Single Feral Cat (no pair)      | Cannot be played                  | Yes          |

### Method 4: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | FeralCatCard      | true            | Yes          |

### Pairing Options
|             | Paired with            | Valid pair? | Implemented? |
|-------------|------------------------|-------------|--------------|
| Test Case 1 | Another Feral Cat       | Yes         | Yes          |
| Test Case 2 | Any regular Cat Card    | Yes         | Yes          |
| Test Case 3 | Attack Card             | No          | Yes          |
| Test Case 4 | Skip Card               | No          | Yes          |
| Test Case 5 | Defuse Card             | No          | Yes          |

### Playability
|             | Hand contents                    | Feral Cat playable? | Implemented? |
|-------------|----------------------------------|---------------------|--------------|
| Test Case 1 | 1 Feral Cat, no other cats       | No                  | Yes          |
| Test Case 2 | 2 Feral Cats                     | Yes                 | Yes          |
| Test Case 3 | 1 Feral Cat + 1 regular Cat      | Yes                 | Yes          |
| Test Case 4 | 1 Feral Cat + non-cat cards      | No                  | Yes          |
| Test Case 5 | 3 Feral Cats                     | Yes                 | Yes          |

### Boundary Cases
|             | Boundary condition              | Expected behavior           | Implemented? |
|-------------|---------------------------------|----------------------------|--------------|
| Test Case 1 | Only card in hand               | Cannot be played alone     | Yes          |
| Test Case 2 | Paired with different cat types | Acts as that cat type      | Yes          |
| Test Case 3 | All 5 cat types in hand         | Can pair with any          | Yes          |
