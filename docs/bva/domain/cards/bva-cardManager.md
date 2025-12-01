# BVA Analysis for CardManager

## CardManager Class

### Method 1: `public void registerCard(String name, Supplier<Card> cardFactory)`
### Step 1-3 Results
|        | Input                         | Output                              |
|--------|-------------------------------|-------------------------------------|
| Step 1 | Card name and factory         | Card registered for creation        |
| Step 2 | String, Supplier<Card>        | Entry added to registry             |
| Step 3 | Valid, null, duplicate        | Success or exception                |

### Step 4:
|             | System under test            | Expected output              | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Register new card type       | Card retrievable via create  | Yes          |
| Test Case 2 | Register duplicate name      | Overwrites previous          | Yes          |
| Test Case 3 | Register null name           | Exception thrown             | Yes          |
| Test Case 4 | Register null factory        | Exception thrown             | Yes          |
| Test Case 5 | Register empty string name   | Appropriate handling         | Yes          |

### Method 2: `public Card createCard(String name)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Card name                | New instance of that card           |
| Step 2 | Registered name          | Card object                         |
| Step 3 | Valid, invalid, null     | Card or exception                   |

### Step 4:
|             | System under test            | Expected output              | Implemented? |
|-------------|------------------------------|------------------------------|--------------|
| Test Case 1 | Create registered card       | New card instance            | Yes          |
| Test Case 2 | Create unregistered card     | IllegalArgumentException     | Yes          |
| Test Case 3 | Create with null name        | Exception thrown             | Yes          |
| Test Case 4 | Create multiple of same type | Distinct instances           | Yes          |
| Test Case 5 | Create "Attack"              | AttackCard instance          | Yes          |
| Test Case 6 | Create "Skip"                | SkipCard instance            | Yes          |
| Test Case 7 | Create "Defuse"              | DefuseCard instance          | Yes          |

### Method 3: `public List<Card> createCards(String name, int count)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Card name and count      | List of new card instances          |
| Step 2 | String, int >= 0         | List of cards                       |
| Step 3 | 0, 1, 5, negative        | Empty list, list, exception         |

### Step 4:
|             | System under test          | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Count = 0                  | Empty list                   | Yes          |
| Test Case 2 | Count = 1                  | List with 1 card             | Yes          |
| Test Case 3 | Count = 5                  | List with 5 cards            | Yes          |
| Test Case 4 | Count = -1                 | Exception or empty list      | Yes          |
| Test Case 5 | Unregistered card name     | Exception thrown             | Yes          |

### Method 4: `public Set<String> getRegisteredCardNames()`
|             | System under test          | Expected output              | Implemented? |
|-------------|----------------------------|------------------------------|--------------|
| Test Case 1 | Empty registry             | Empty set                    | Yes          |
| Test Case 2 | With registered cards      | Set of all registered names  | Yes          |
| Test Case 3 | After adding/removing      | Updated set                  | Yes          |

### Method 5: `public boolean isRegistered(String name)`
|             | System under test          | Expected output | Implemented? |
|-------------|----------------------------|-----------------|--------------|
| Test Case 1 | Registered card name       | true            | Yes          |
| Test Case 2 | Unregistered card name     | false           | Yes          |
| Test Case 3 | null                       | false           | Yes          |
| Test Case 4 | Empty string               | false           | Yes          |

### Boundary Cases
|             | Boundary condition              | Expected behavior              | Implemented? |
|-------------|---------------------------------|-------------------------------|--------------|
| Test Case 1 | First card registration         | Card becomes available         | Yes          |
| Test Case 2 | Maximum cards registered        | All cards functional           | Yes          |
| Test Case 3 | Case sensitivity in names       | Names are case-sensitive       | Yes          |
