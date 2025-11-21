# BVA Analysis for Exploding Kitten Card Class

## Method 1: ```public void playCard(Game game, UI ui)```
### Step 1-3 Results
|        | Input 1                                    | Input 2                                                | Output                                                                                             |
|--------|--------------------------------------------|--------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| Step 1 | Does the current player have a defuse card | Does the current player have the streaking kitten card | The result of playing the Exploding Kitten Card                                                    |
| Step 2 | boolean                                    | boolean                                                | Cases                                                                                              |
| Step 3 | true, false                                | true, false                                            | Exploding Kitten Card reinserted in deck, player elminated, or Exploding Kitten Card Added to Hand |

### Step 4:
##### Each-choice
|             | System under test                      | Expected output                            | Implemented? |
|-------------|----------------------------------------|--------------------------------------------|--------------|
| Test Case 1 | hasDefuse = False, hasStreaking = True | Player is Eliminated                       | Yes          |
| Test Case 2 | hasDefuse = True, hasStreaking = False | Exploding Kitten Card reinserted in deck   | Yes          |
| Test Case 3 | hasDefuse = True, hasStreaking = True  | Exploding Kitten Card is added to the hand | Yes          |
| Test Case 4 | hasDefuse = True, hasStreaking = True  | Exploding Kitten Card reinserted in deck   | Yes          |
| Test Case 5 | hasDefuse = False, hasStreaking = True | Exploding Kitten Card is added to the hand | Yes          |