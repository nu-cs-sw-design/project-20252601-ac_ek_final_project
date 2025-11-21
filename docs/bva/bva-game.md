# BVA Analysis for Game Class

## Method 1: ```public void deletePlayer(int id)```
### Step 1-3 Results
|        | Input                             | (if more to consider for input)                             | Output                                            |
|--------|-----------------------------------|-------------------------------------------------------------|---------------------------------------------------|
| Step 1 | Player ID                         | Players                                                     | List of players with player found and removed     |
| Step 2 | Interval: [1, #players]           | Collection of players                                       | Collection of players or exception                |
| Step 3 | Player IDs as integers 0, 1, 2, 3 | [], [Player1], [Player1, Player2], [Player1, ..., Player10] | Exception, [], [Player1], [Player1, ..., Player9] |

### Step 4:
##### Each-choice
###### BVA Catalog: Using the position of the matching element
|             | System under test                                      | Expected output           | Implemented? |
|-------------|--------------------------------------------------------|---------------------------|--------------|
| Test Case 1 | PlayerIDs=[1, 2, ..., 10], case: id in collection -> 2 | PlayerIDs=[1, 3, ..., 10] | Yes          |
| Test Case 2 | PlayerIDs=[1, 2], case: id not in collection -> 3      | NoSuchElementException    | Yes          |
| Test Case 3 | PlayerIDs=[], case: id not in collection -> 2          | NoSuchElementException    | Yes          |
| Test Case 4 | PlayerIDs=[1], case: id is in collection -> 1          | IllegalStateException     | Yes          |

## Method 2: ```public void nextPlayer()```
### Step 1-3 Results
|        | Input                        | Output                                                                                                          |
|--------|------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Step 1 | Players                      | Updated players in game, updated current player                                                                 |
| Step 2 | Players collection           | Internal fields players and current_player updated                                                              |
| Step 3 | Players list of size 2, 5, 9 | 1st element of players becomes last, and others shift by 1 and current_player updated to 1st element of players |

### Step 4:
##### Each-choice
|             | System under test                              | Expected output                             | Implemented? |
|-------------|------------------------------------------------|---------------------------------------------|--------------|
| Test Case 1 | Game with two players (initial state - small)  | 1st element in players becomes last element | Yes          |
| Test Case 2 | Game with two players (initial state - medium) | 1st element in players becomes last element | Yes          |
| Test Case 3 | Game with two players (initial state - large)  | 1st element in players becomes last element | Yes          |

## Method 3: ```public void setnextPlayerTargetPlayer(Player targetPlayer)```
### Step 1-3 Results
|        | Input                        | (if more to consider for input)                   | Output                                                                                                                                  |
|--------|------------------------------|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| Step 1 | Players                      | targeted player                                   | Updated players in game, updated current player to target player                                                                        |
| Step 2 | Players collection           | integer interval [0, size of players collection]  | collection                                                                                                                              |
| Step 3 | Players list of size 2, 5, 9 | themself, last player, 2nd player, invalid Player | players collection rotates such that 1st element is target player and currentPlayer set to target player, UnsupportedOperationException |

### Step 4:
##### Each-choice
|             | System under test                                           | Expected output                                                                                 | Implemented? |
|-------------|-------------------------------------------------------------|-------------------------------------------------------------------------------------------------|--------------|
| Test Case 1 | target player is last player (initial state - small)        | players rotates such that 1st element is now last player and currentPlayer set to target player | Yes          |
| Test Case 2 | target player is themself (initial state - medium)          | UnsupportedOperationException                                                                   | Yes          |
| Test Case 3 | targetPlayer is second player (initial state - large)       | players rotates such that 1st element is target player and currentPlayer set to target player   | Yes          |
| Test Case 3 | targetPlayer is invalid 5th player (initial state - medium) | UnsupportedOperationException                                                                   | Yes          |

## Method 4: ```public void takeTurn()```
### Step 1-3 Results
|        | Input               | (if more to consider for input) | Output                                              |
|--------|---------------------|---------------------------------|-----------------------------------------------------|
| Step 1 | Size of hand        | Number of turns                 | Player has taken turn                               |
| Step 2 | Collection of Cards | Case: 0                         | Cases: card not drawn, card played, card not played |
| Step 3 | empty, >1 cards     | 0                               | no cards drawn, card played or not played           |

### Step 4:
##### Each-choice
|             | System under test          | Expected output                                        | Implemented? |
|-------------|----------------------------|--------------------------------------------------------|--------------|
| Test Case 1 | Hand empty, 0 turns        | No cards drawn, no cards played                        | Yes          |
| Test Case 2 | Hand has 8 cards, 0 turn   | Player may play 1 card (optional), no cards drawn      | Yes          |


## Method 5: ```public void removeCurrentPlayerCard(int index)```
### Step 1-3 Results
|        | Input                                    | (if more to consider for input)                          | Output 1                                      |
|--------|------------------------------------------|----------------------------------------------------------|-----------------------------------------------|
| Step 1 | Size of hand                             | Index of card                                            | Players hand with one less card               |
| Step 2 | Collection of Cards                      | Interval: [0, #cards in hand]                            | Collection of cards with one removed          |
| Step 3 | empty, >1 cards (test only 1 value here) | 1 (tested elsewhere as well - so only 1 value used here) | Collection as ArrayList with one less element |

### Step 4:
##### Each-choice
|             | System under test            | Expected output                                  | Implemented? |
|-------------|------------------------------|--------------------------------------------------|--------------|
| Test Case 1 | Hand has 8 cards, index = 1  | Hand has 7 cards, with card from index 1 removed | Yes          |

## Method 6: ```public void addToCurrentPlayer(Card cardToAdd)```
### Step 1-3 Results
|        | Input                                    | (if more to consider for input)  | Output 1                                      |
|--------|------------------------------------------|----------------------------------|-----------------------------------------------|
| Step 1 | Size of hand                             | Card                             | Players hand with one more card               |
| Step 2 | Collection of Cards                      | Cases (different types of cards) | Collection of cards with one added at end     |
| Step 3 | empty, >1 cards (test only 1 value here) | Specific card type               | Collection as ArrayList with one more element |

### Step 4:
##### Each-choice
|             | System under test               | Expected output                                 | Implemented? |
|-------------|---------------------------------|-------------------------------------------------|--------------|
| Test Case 1 | Hand has 8 cards, card = Attack | Hand has 9 cards, with Attack card added at end | Yes          |

## Method 7: ```public void getMarkedCards()```
### Step 1-3 Results
|        | Input              | (if more to consider for input)                                                                     | Output                                             |
|--------|--------------------|-----------------------------------------------------------------------------------------------------|----------------------------------------------------|
| Step 1 | Players            | visible hand field                                                                                  | returns dictionary of visible hand for each player |
| Step 2 | Players collection | Collection of Cards                                                                                 | Pairs of Collections                               |
| Step 3 | 2,5,9              | 0,1 (two players have one card in visible hand field) ,one player has 2 cards in visible hand field | empty, 1:1,2:1, 1:2                                |

### Step 4:
##### All-combination or each-choice: EACH-CHOICE
|             | System under test                                      | Expected output                                   | Implemented? |
|-------------|--------------------------------------------------------|---------------------------------------------------|--------------|
| Test Case 1 | 2 players, none have visible cards                     | map is empty                                      | Yes          |
| Test Case 2 | 5 players, player 1 and 2 have 2 cards in visible hand | map with 2 players that have 2 cards as the value | Yes          |
| Test Case 3 | 9 players, one player has 2 cards in visible hand      | map with one player with 2 cards as value         | Yes          |

## Method 8: ```public void setPlayerTurns(int playerIndex, int turns)```
### Step 1-3 Results
|        | Input                                    | (if more to consider for input) | Output                                  |
|--------|------------------------------------------|---------------------------------|-----------------------------------------|
| Step 1 | playerIndex                              |                                 | sets turn to selected player            |
| Step 2 | Interval: [1, numOfPlayers + 1]          |                                 | exception                               |
| Step 3 | 0, 1, numOfPlayers + 1, numOfPlayers + 2 |                                 | sets turn to selected player, exception |

### Step 4:
##### All-combination or each-choice: EACH-CHOICE
|             | System under test | Expected output               | Implemented? |
|-------------|-------------------|-------------------------------|--------------|
| Test Case 1 | playerIndex: 0    | IndexOutOfBoundsException     | Yes          |
| Test Case 2 | playerIndex: 1    | sets turn to selected player  | Yes          |
| Test Case 3 | playerIndex: 3    | sets turn to selected player  | Yes          |
| Test Case 3 | playerIndex: 4    | IndexOutOfBoundsException     | Yes          |
