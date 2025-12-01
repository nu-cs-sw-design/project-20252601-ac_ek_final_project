# BVA Analysis for Action

## Action Class

### Constructor 1: `Action(Type type)`
### Step 1-3 Results
|        | Input          | Output                              |
|--------|---------------|-------------------------------------|
| Step 1 | Action type    | Action object created               |
| Step 2 | Valid Type enum| Action with type and null card      |
| Step 3 | PASS, PLAY_CARD| Different action behaviors          |

### Step 4:
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Action.Type.PASS      | Action created, no card      | Yes          |
| Test Case 2 | Action.Type.PLAY_CARD | Action created, no card yet  | Yes          |
| Test Case 3 | null type             | Exception thrown             | Yes          |

### Constructor 2: `Action(Type type, Card card)`
### Step 1-3 Results
|        | Input           | Output                              |
|--------|----------------|-------------------------------------|
| Step 1 | Type and Card   | Action object with card             |
| Step 2 | Valid type/card | Action ready for execution          |
| Step 3 | Various combos  | Different actions                   |

### Step 4:
|             | System under test              | Expected output              | Implemented? |
|-------------|-------------------------------|------------------------------|--------------|
| Test Case 1 | PLAY_CARD with AttackCard      | Action with attack           | Yes          |
| Test Case 2 | PLAY_CARD with SkipCard        | Action with skip             | Yes          |
| Test Case 3 | PASS with null card            | Valid pass action            | Yes          |
| Test Case 4 | PLAY_CARD with null card       | Exception thrown             | Yes          |

### Constructor 3: `Action(Type type, Card card, Player target)`
|             | System under test                  | Expected output              | Implemented? |
|-------------|------------------------------------|------------------------------|--------------|
| Test Case 1 | PLAY_CARD, FavorCard, target       | Targeted action              | Yes          |
| Test Case 2 | PLAY_CARD, TargetedAttack, target  | Attack with target           | Yes          |
| Test Case 3 | null target when required          | Exception thrown             | Yes          |

### Method 1: `public Type getType()`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Action with PASS      | Returns PASS                 | Yes          |
| Test Case 2 | Action with PLAY_CARD | Returns PLAY_CARD            | Yes          |

### Method 2: `public Card getCard()`
|             | System under test        | Expected output              | Implemented? |
|-------------|-------------------------|------------------------------|--------------|
| Test Case 1 | PASS action              | Returns null                 | Yes          |
| Test Case 2 | PLAY_CARD with card      | Returns the card             | Yes          |
| Test Case 3 | Action with AttackCard   | Returns AttackCard           | Yes          |

### Method 3: `public Player getTarget()`
|             | System under test          | Expected output              | Implemented? |
|-------------|---------------------------|------------------------------|--------------|
| Test Case 1 | Non-targeted action        | Returns null                 | Yes          |
| Test Case 2 | Targeted action            | Returns target player        | Yes          |
| Test Case 3 | Favor action with target   | Returns target               | Yes          |

### Method 4: `public void setCard(Card card)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Set valid card        | Card updated                 | Yes          |
| Test Case 2 | Set null              | Exception or null set        | Yes          |

### Method 5: `public void setTarget(Player target)`
|             | System under test     | Expected output              | Implemented? |
|-------------|----------------------|------------------------------|--------------|
| Test Case 1 | Set valid target      | Target updated               | Yes          |
| Test Case 2 | Set null target       | Target cleared               | Yes          |

### Action.Type Enum
|             | Type value   | Description                          | Implemented? |
|-------------|-------------|--------------------------------------|--------------|
| Test Case 1 | PASS         | End turn and draw                    | Yes          |
| Test Case 2 | PLAY_CARD    | Play a card from hand                | Yes          |
| Test Case 3 | NOPE         | Cancel previous action               | Yes          |
| Test Case 4 | DRAW         | Draw a card                          | Yes          |

### Action Validity
|             | Scenario                    | Expected behavior            | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | PLAY_CARD without card      | Invalid                      | Yes          |
| Test Case 2 | PASS with card              | Valid (card ignored)         | Yes          |
| Test Case 3 | Targeted card without target| Invalid                      | Yes          |
| Test Case 4 | Non-targeted with target    | Valid (target ignored)       | Yes          |
