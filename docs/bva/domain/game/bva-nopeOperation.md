# BVA Analysis for NopeOperation

## NopeOperation Class

### Constructor: `NopeOperation(GameContext context, Card targetedCard)`
### Step 1-3 Results
|        | Input                   | Output                              |
|--------|------------------------|-------------------------------------|
| Step 1 | Context, targeted card  | NopeOperation object created        |
| Step 2 | Valid context and card  | Ready to handle nopes               |
| Step 3 | null vs valid           | Exception or success                |

### Step 4:
|             | System under test            | Expected output              | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Valid context and card        | Operation created            | Yes          |
| Test Case 2 | null context                  | Exception thrown             | Yes          |
| Test Case 3 | null targeted card            | Exception thrown             | Yes          |

### Method 1: `public boolean executeNopeChain()`
### Step 1-3 Results
|        | Input                   | Output                              |
|--------|------------------------|-------------------------------------|
| Step 1 | Current nope state      | Whether original card executes      |
| Step 2 | Chain of nopes          | true/false based on parity          |
| Step 3 | 0, 1, 2, 3 nopes        | Alternating execution status        |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | No nopes played       | true (card executes)         | Yes          |
| Test Case 2 | 1 nope played         | false (card cancelled)       | Yes          |
| Test Case 3 | 2 nopes played        | true (card executes)         | Yes          |
| Test Case 4 | 3 nopes played        | false (card cancelled)       | Yes          |
| Test Case 5 | 4 nopes played        | true (card executes)         | Yes          |

### Method 2: `public void promptForNope()`
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Player has nope            | Option to play presented     | Yes          |
| Test Case 2 | Player has no nope         | Skipped                      | Yes          |
| Test Case 3 | All players prompted       | Round complete               | Yes          |
| Test Case 4 | Nope played                | Chain continues              | Yes          |

### Method 3: `public boolean canBeNoped(Card card)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | AttackCard            | true                         | Yes          |
| Test Case 2 | SkipCard              | true                         | Yes          |
| Test Case 3 | FavorCard             | true                         | Yes          |
| Test Case 4 | DefuseCard            | false                        | Yes          |
| Test Case 5 | ExplodingKittenCard   | false                        | Yes          |
| Test Case 6 | NopeCard              | true                         | Yes          |

### Method 4: `public int getNopeCount()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | No nopes played       | 0                            | Yes          |
| Test Case 2 | 1 nope played         | 1                            | Yes          |
| Test Case 3 | Multiple nopes        | Correct count                | Yes          |

### Method 5: `public boolean isNoped()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Even nope count       | false                        | Yes          |
| Test Case 2 | Odd nope count        | true                         | Yes          |
| Test Case 3 | 0 nopes               | false                        | Yes          |

### Nope Chain Scenarios
|             | Scenario                     | Expected behavior            | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Card played, timeout         | Card executes                | Yes          |
| Test Case 2 | Card → Nope → timeout        | Card cancelled               | Yes          |
| Test Case 3 | Card → Nope → Nope           | Card executes                | Yes          |
| Test Case 4 | Multiple players with nope   | All get chance               | Yes          |
| Test Case 5 | Timer expires                | Decision finalized           | Yes          |

### Cards That Cannot Be Noped
|             | Card type            | Reason                            | Implemented? |
|-------------|---------------------|-----------------------------------|--------------|
| Test Case 1 | Defuse              | Always allowed when exploding     | Yes          |
| Test Case 2 | Exploding Kitten    | Not played by player              | Yes          |
| Test Case 3 | Imploding Kitten    | Not played by player              | Yes          |

### Edge Cases
|             | Scenario                     | Expected behavior            | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Nope own card                | Not allowed                  | Yes          |
| Test Case 2 | Nope after resolution        | Not allowed                  | Yes          |
| Test Case 3 | Out of time to nope          | Card resolves as-is          | Yes          |
| Test Case 4 | Multiple nopes same player   | Each valid nope counted      | Yes          |
