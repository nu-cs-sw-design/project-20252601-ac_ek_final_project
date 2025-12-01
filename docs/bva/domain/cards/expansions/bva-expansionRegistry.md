# BVA Analysis for ExpansionRegistry

## ExpansionRegistry Class

### Method 1: `public static Optional<ExpansionStrategy> getById(String id)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Expansion ID string      | Optional containing strategy        |
| Step 2 | Valid/invalid ID         | Present or empty Optional           |
| Step 3 | null, "", valid IDs      | Corresponding expansion or empty    |

### Step 4:
|             | System under test     | Expected output                     | Implemented? |
|-------------|----------------------|-------------------------------------|--------------|
| Test Case 1 | "party_pack"          | Optional with PartyPackExpansion    | Yes          |
| Test Case 2 | "streaking_kittens"   | Optional with StreakingKittens      | Yes          |
| Test Case 3 | "imploding_kittens"   | Optional with ImplodingKittens      | Yes          |
| Test Case 4 | "base"                | Optional with BaseGameExpansion     | Yes          |
| Test Case 5 | "non_existent"        | Empty Optional                      | Yes          |
| Test Case 6 | null                  | Empty Optional                      | Yes          |
| Test Case 7 | ""                    | Empty Optional                      | Yes          |

### Method 2: `public static Optional<ExpansionStrategy> getByNumber(int number)`
### Step 1-3 Results
|        | Input                    | Output                              |
|--------|--------------------------|-------------------------------------|
| Step 1 | Selection number         | Optional containing strategy        |
| Step 2 | Integer 1-3 or other     | Present or empty Optional           |
| Step 3 | -1, 0, 1, 2, 3, 4        | Expansion or empty                  |

### Step 4:
|             | System under test | Expected output               | Implemented? |
|-------------|-------------------|-------------------------------|--------------|
| Test Case 1 | 1                 | Optional with PartyPack       | Yes          |
| Test Case 2 | 2                 | Optional with StreakingKittens| Yes          |
| Test Case 3 | 3                 | Optional with ImplodingKittens| Yes          |
| Test Case 4 | 0                 | Empty Optional                | Yes          |
| Test Case 5 | -1                | Empty Optional                | Yes          |
| Test Case 6 | 100               | Empty Optional                | Yes          |

### Method 3: `public static boolean isValidNumber(int number)`
|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | 1                 | true            | Yes          |
| Test Case 2 | 2                 | true            | Yes          |
| Test Case 3 | 3                 | true            | Yes          |
| Test Case 4 | 0                 | false           | Yes          |
| Test Case 5 | -1                | false           | Yes          |
| Test Case 6 | 4                 | false           | Yes          |

### Method 4: `public static List<ExpansionStrategy> getAllExpansions()`
|             | System under test    | Expected output              | Implemented? |
|-------------|---------------------|------------------------------|--------------|
| Test Case 1 | getAllExpansions()   | List with all 3 expansions   | Yes          |
| Test Case 2 | List size            | Exactly 3 elements           | Yes          |
| Test Case 3 | List contents        | Party, Streaking, Imploding  | Yes          |

### Boundary Cases
|             | Boundary condition    | Expected behavior            | Implemented? |
|-------------|-----------------------|------------------------------|--------------|
| Test Case 1 | Minimum valid number  | Returns expansion            | Yes          |
| Test Case 2 | Maximum valid number  | Returns expansion            | Yes          |
| Test Case 3 | Just below minimum    | Returns empty                | Yes          |
| Test Case 4 | Just above maximum    | Returns empty                | Yes          |
