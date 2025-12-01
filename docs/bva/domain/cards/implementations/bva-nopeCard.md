# BVA Analysis for NopeCard

## NopeCard Class

### Method 1: `public String getName()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | NopeCard          | "Nope"          | Yes          |

### Method 2: `public void applyEffect(GameEngine engine)`
### Step 1-3 Results
|        | Input            | Output                                         |
|--------|------------------|------------------------------------------------|
| Step 1 | Game engine      | Previously played card's effect is negated     |
| Step 2 | Valid engine     | Last nopeable action is cancelled              |
| Step 3 | Stackable nopes  | Nope can nope a nope                           |

### Step 4:
|             | System under test         | Expected output                 | Implemented? |
|-------------|--------------------------|--------------------------------|--------------|
| Test Case 1 | Nope an Attack card       | Attack effect cancelled        | Yes          |
| Test Case 2 | Nope a Skip card          | Skip effect cancelled          | Yes          |
| Test Case 3 | Nope a Favor card         | Favor effect cancelled         | Yes          |
| Test Case 4 | Nope a Nope (counter)     | Original effect restored       | Yes          |
| Test Case 5 | Nope a Cat card pair      | Steal effect cancelled         | Yes          |

### Method 3: `public boolean isNopeable()`
|             | System under test | Expected output | Implemented? |
|-------------|------------------|-----------------|--------------|
| Test Case 1 | NopeCard          | true            | Yes          |

### Method 4: `public boolean isPlayable()`
|             | System under test         | Expected output | Implemented? |
|-------------|--------------------------|-----------------|--------------|
| Test Case 1 | During own turn           | false           | Yes          |
| Test Case 2 | During nope window        | true            | Yes          |
| Test Case 3 | No card to nope           | false           | Yes          |

### Cards That Can Be Noped
|             | Card type            | Can be Noped? | Implemented? |
|-------------|---------------------|---------------|--------------|
| Test Case 1 | Attack               | Yes           | Yes          |
| Test Case 2 | Skip                 | Yes           | Yes          |
| Test Case 3 | Favor                | Yes           | Yes          |
| Test Case 4 | Shuffle              | Yes           | Yes          |
| Test Case 5 | See The Future       | Yes           | Yes          |
| Test Case 6 | Cat Card pairs       | Yes           | Yes          |
| Test Case 7 | Defuse               | No            | Yes          |
| Test Case 8 | Exploding Kitten     | No            | Yes          |

### Nope Stacking
|             | Number of Nopes | Original effect | Implemented? |
|-------------|-----------------|-----------------|--------------|
| Test Case 1 | 0               | Applies         | Yes          |
| Test Case 2 | 1               | Cancelled       | Yes          |
| Test Case 3 | 2               | Applies         | Yes          |
| Test Case 4 | 3               | Cancelled       | Yes          |
| Test Case 5 | 4               | Applies         | Yes          |

### Boundary Cases
|             | Boundary condition         | Expected behavior           | Implemented? |
|-------------|---------------------------|----------------------------|--------------|
| Test Case 1 | Nope played immediately    | Effect cancelled           | Yes          |
| Test Case 2 | Nope window expired        | Cannot nope                | Yes          |
| Test Case 3 | Multiple players have Nope | Each can respond           | Yes          |
