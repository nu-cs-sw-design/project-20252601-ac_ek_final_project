# BVA Analysis for Player

## Method 1: ```public int startGame()```

### Step 1-3 Results

|        | Input 1           | Input 2         | Output                                     |
|--------|-------------------|-----------------|--------------------------------------------|
| Step 1 | Number of Players | Langauge        | the start of the game                      |
| Step 2 | Interval: [2, 10] | Cases           | Cases                                      |
| Step 3 | 1, 2, 10, 11      | English, French | successful gameLoop with English or French |

### Step 4:

##### Each-choice

###### BVA Catalog: Appending a single element

|             | System under test                                                  | Expected output                  | Implemented? |
|-------------|--------------------------------------------------------------------|----------------------------------|--------------|
| Test Case 1 | 1 Player -> 2 Players (Reprompting the number of Players), English | successful gameLoop with English | Yes          |
| Test Case 2 | 2 Players, English                                                 | successful gameLoop with English | Yes          |
| Test Case 3 | 11 Players -> 2 Players, English                                   | successful gameLoop with English | Yes          |
| Test Case 4 | 10 Players, French                                                 | successful gameLoop with French  | Yes          |



