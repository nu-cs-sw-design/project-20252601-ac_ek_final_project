# BVA Analysis for Player

## Method 1: ```public int addCard(Card card)```
### Step 1-3 Results
|        | Input 1                                  | Input 2                                                  | Output                             |
|--------|------------------------------------------|----------------------------------------------------------|------------------------------------|
| Step 1 | The hand of cards the player has         | The card that is added to Player's deck                  | The new size of the hands of cards |
| Step 2 | Collection of cards                      | Cases                                                    | Interval                           |
| Step 3 | Hand as ArrayList of size 0, 1, 2, 3, 90 | Different types of cards (Alter The Future, Attack etc.) | 1, 2 90, 91                        |

### Step 4:
##### Each-choice
###### BVA Catalog: Appending a single element
|             | System under test                      | Expected output | Implemented? |
|-------------|----------------------------------------|-----------------|--------------|
| Test Case 1 | Hand: [], add Card                     | 1               | Yes          |
| Test Case 2 | Hand: [Card1], add Card2               | 2               | Yes          |
| Test Case 3 | Hand: [Card1, Card2], add Card3        | 3               | Yes          |
| Test Case 4 | Hand: [Card1, Card2, Card3], add Card4 | 4               | Yes          |
| Test Case 5 | Hand: [Card1, ..., Card90], add Card91 | 91              | Yes          |

## Method 2: ```public int addVisibleCard(Card card)```
### Step 1-3 Results
|        | Input 1                                        | Input 2                                                  | Output                             |
|--------|------------------------------------------------|----------------------------------------------------------|------------------------------------|
| Step 1 | The hand of cards the player shows to everyone | The card that is added to Player's deck                  | The new size of the hands of cards |
| Step 2 | Collection of cards                            | Cases                                                    | interval                           |
| Step 3 | Hand as ArrayList of size 0, 1                 | Different types of cards (Alter The Future, Attack etc.) | 1, 2                               |

### Step 4:
##### Each-choice
###### BVA Catalog: Appending a single element
|             | System under test | Expected output | Implemented? |
|-------------|-------------------|-----------------|--------------|
| Test Case 1 | [], Card          | 1               | Yes          |
| Test Case 2 | [Card1], Card2    | 2               | Yes          |

## Method 3: ```public int removeCard(int index)```
### Step 1-3 Results
|        | Input1                                | Input 2           | Output 1                                               | Output 2                                        |
|--------|---------------------------------------|-------------------|--------------------------------------------------------|-------------------------------------------------|
| Step 1 | The hand of cards the player has      | Index of the card | The  player's hand of card after removing the ith card | the number of cards after removing the ith card |
| Step 2 | Collection of cards                   | interval: [0, 90] | Collection of cards or exception                       | interval:  [0, 91]                              |
| Step 3 | Hand as ArrayList of size 0, 1, 3, 91 | -1, 0, 90, 91     | Exception, 0, 2, 90                                    | 0, 1, 90, 91                                    |

### Step 4:
##### Each-choice
###### BVA Catalog: Deleting elements from any position
|             | System under test                     | Expected output                            | Implemented? |
|-------------|---------------------------------------|--------------------------------------------|--------------|
| Test Case 1 | hand=[], index = 0                    | UnSupportedOperationException              | yes          |
| Test Case 2 | hand=[Card1], index = -1              | IndexOutOfBoundsException                  | Yes          |
| Test Case 3 | hand=[Card1], index = 0               | hand=[], size=0                            | Yes          |
| Test Case 4 | hand=[Card1], index = 1               | IndexOutOfBoundsException                  | Yes          |
| Test Case 5 | hand=[Card1, Card2, Card3], index=0   | hand=[Card2, Card3], size=2                | Yes          |
| Test Case 6 | hand=[Card1, ..., Card91] , index=89  | hand=[Card1, ..., Card88, Card90], size=90 | Yes          |
| Test Case 7 | hand=[Card1, ..., Card91], index = 90 | hand=[Card1, ..., Card90], size=90         | Yes          |
| Test Case 8 | hand=[Card1, ..., Card91], index = 91 | IndexOutOfBounds Exception                 | Yes          |

## Method 4: ```public Card chooseCard(int index)```
### Step 1-3 Results
|        | Input 1                                  | Input 2                                       | Output                                                   |
|--------|------------------------------------------|-----------------------------------------------|----------------------------------------------------------|
| Step 1 | The hand of cards the player has         | The index of the Card to play                 | A card from the hand to play                             |
| Step 2 | Collection of cards                      | Interval: [0, sizeOfPlayerHand]               | Cases                                                    |
| Step 3 | Hand as ArrayList of size 0, 1, 2, 3, 91 | -1, 0, sizeOfPlayerHand - 1, sizeOfPlayerHand | Different types of cards (Alter The Future, Attack etc.) |

### Step 4:
##### Each-choice
###### BVA Catalog: Using the position of the matching element
|             | System under test                   | Expected output           | Implemented? |
|-------------|-------------------------------------|---------------------------|--------------|
| Test Case 1 | Hand=[], index=-1                   | IndexOutOfBoundsException | Yes          |
| Test Case 2 | Hand=[Card1], index=0               | Card1                     | Yes          |
| Test Case 3 | Hand=[Card1, Card2], index=2        | IndexOutOfBoundsException | Yes          |
| Test Case 4 | Hand=[Card1, Card2, Card3], index=2 | Card3                     | Yes          |
| Test Case 5 | Hand=[Card1, Card2, Card3], index=0 | Card1                     | Yes          |
| Test Case 6 | Hand=[Card1, ..., Card90], index=91 | IndexOutOfBoundsException | Yes          |
| Test Case 7 | Hand=[], index=0                    | IndexOutOfBoundsException | Yes          |

## Method 5: ```public int hasCard(String Card)```
### Step 1-3 Results
|        | Input 1                            | Input 2                                                       | Output                                               |
|--------|------------------------------------|---------------------------------------------------------------|------------------------------------------------------|
| Step 1 | The hand of cards the player has   | Name of card to be found                                      | The index of the same card type in the player's hand |
| Step 2 | Collection of cards                | Cases                                                         | interval: [0, 91]                                    |
| Step 3 | Hand as ArrayList of size 0, 1, 2  | Different types of card names (Alter The Future, Attack etc.) | Exception, 0, 1, 2                                   |

### Step 4:
##### Each-choice
###### BVA Catalog: Using the position of the matching element
|             | System under test                      | Expected output        | Implemented? |
|-------------|----------------------------------------|------------------------|--------------|
| Test Case 1 | hand = [], card = Attack               | NoSuchElementException | Yes          |
| Test Case 2 | hand = [Attack], card = Favor          | NoSuchElementException | Yes          |
| Test Case 3 | hand = [Defuse, Attack], card = Defuse | 0                      | Yes          |
| Test Case 4 | hand = [Defuse, Attack], card = Attack | 1                      | Yes          |

## Method 6: ```public void decreaseTurnByOne()```
### Step 1-3 Results
|        | Input                            | Output                                |
|--------|----------------------------------|---------------------------------------|
| Step 1 | Number of turns field            | number of turns is lowered by one     |
| Step 2 | Interval [0, MAX_INT]            | Exception or Interval: [0, MAX_INT-1] |
| Step 3 | Number of turns field is 0, 1, 2 | Exception, 0, 1                       |

### Step 4:
##### Each-choice
|             | System under test | Expected output               | Implemented? |
|-------------|-------------------|-------------------------------|--------------|
| Test Case 1 | numberOfTurns = 0 | UnsupportedOperationException | Yes          |
| Test Case 2 | numberOfTurns = 1 | numberOfTurns = 0             | Yes          |
| Test Case 3 | numberOfTurns = 2 | numberOfTurns = 1             | No           |

## Method 7: ```public boolean hasTwoOf(String cardName)```
### Step 1-3 Results
|        | Input1                                     | Input 2                                                       | Output            |
|--------|--------------------------------------------|---------------------------------------------------------------|-------------------|
| Step 1 | The hand of cards the player has           | Name of card to be found                                      | Yes or no         |
| Step 2 | Collection of cards                        | Cases                                                         | Boolean           |
| Step 3 | Hand with 1, 2, 3 of the same type of card | Different types of card names (Alter The Future, Attack etc.) | false, true, true |

### Step 4:
##### Each-choice
|             | System under test                                                 | Expected output | Implemented? |
|-------------|-------------------------------------------------------------------|-----------------|--------------|
| Test Case 1 | hand=[thisCard, notThisCard, notThisCard], card needed = thisCard | false           | yes          |
| Test Case 2 | hand=[thisCard, thisCard, notThisCard], card needed = thisCard    | true            | Yes          |
| Test Case 3 | hand=[thisCard, thisCard, thisCard], card needed  = thisCard      | true            | Yes          |

## Method 8: ```public void shuffleHand()```
### Step 1-3 Results
|        | Input1                               | Output                                              |
|--------|--------------------------------------|-----------------------------------------------------|
| Step 1 | The hand of cards the player has     | Hand with all cards shuffled                        |
| Step 2 | Collection of cards                  | Collection of cards with all elements shuffled      |
| Step 3 | Hand as ArrayList of size 1, 2, 1000 | Hand with all cards in randomly different positions |

### Step 4:
##### Each-choice
|             | System under test   | Expected output                         | Implemented? |
|-------------|---------------------|-----------------------------------------|--------------|
| Test Case 1 | Hand has 0 cards    | UnsupportedOperationException           | yes          |
| Test Case 2 | Hand has 1 card     | Same hand with 1 card                   | Yes          |
| Test Case 3 | Hand has 4 cards    | Hand has 4 cards, which are shuffled    | Yes          |
| Test Case 4 | Hand has 1000 cards | Hand has 1000 cards, which are shuffled | Yes          |

## Method 9: ```public boolean getIsTurnOver()```
### Step 1-3 Results
|        | Input 1                        | Output                                          |
|--------|--------------------------------|-------------------------------------------------|
| Step 1 | Player number of turns         | True if number of turns is zero otherwise false |
| Step 2 | Interval: [0, number of turns] | boolean                                         |
| Step 3 | 0, 1, 2                        | True, false , false                             |

### Step 4:
##### Each-choice
|             | System under test       | Expected output | Implemented? |
|-------------|-------------------------|-----------------|--------------|
| Test Case 1 | number of turns is zero | True            | No           |
| Test Case 2 | number of turns is 1    | False           | No           |
| Test Case 3 | number of turns is 2    | False           | No           |

## Method 10: ```public void removeCard(int index)```
### Step 1-3 Results
|        | Input1                                | Input 2           | Output 1                                               | Output 2                                        | Output 3                                                                    |
|--------|---------------------------------------|-------------------|--------------------------------------------------------|-------------------------------------------------|-----------------------------------------------------------------------------|
| Step 1 | The hand of cards the player has      | Index of the card | The  player's hand of card after removing the ith card | the number of cards after removing the ith card | The player's visible hand has one less card if it was removed from the hand |
| Step 2 | Collection of cards                   | interval: [0, 90] | Collection of cards or exception                       | interval:  [0, 91]                              | Collection of cards                                                         |
| Step 3 | Hand as ArrayList of size 0, 1, 3, 91 | -1, 0, 90, 91     | Exception, 0, 2, 90                                    | 0, 1, 90, 91                                    | Hand as ArrayList with size 0 or 1 less                                     |

### Step 4:
##### Each-choice
###### BVA Catalog: Deleting elements from any position
|             | System under test                                           | Expected output                                                  | Implemented? |
|-------------|-------------------------------------------------------------|------------------------------------------------------------------|--------------|
| Test Case 1 | hand=[], index = 0, visibleHand=[]                          | UnSupportedOperationException                                    | yes          |
| Test Case 2 | hand=[Card1], index = -1, visibleHand=[]                    | IndexOutOfBoundsException                                        | Yes          |
| Test Case 3 | hand=[Card1], index = 0, visibleHand=[]                     | hand=[], size=0, visibleHand=[]                                  | Yes          |
| Test Case 4 | hand=[Card1], index = 1, visibleHand=[]                     | IndexOutOfBoundsException                                        | Yes          |
| Test Case 5 | hand=[Card1, Card2, Card3], index=0, visibleHand=[]         | hand=[Card2, Card3], size=2, visibleHand=[]                      | Yes          |
| Test Case 6 | hand=[Card1, ..., Card91] , index=89, visibleHand=[]        | hand=[Card1, ..., Card88, Card90], size=90, visibleHand=[]       | Yes          |
| Test Case 7 | hand=[Card1, ..., Card91], index = 90, visibleHand=[]       | hand=[Card1, ..., Card90], size=90, visibleHand=[]               | Yes          |
| Test Case 8 | hand=[Card1, ..., Card91], index = 91, visibleHand=[]       | IndexOutOfBounds Exception                                       | Yes          |
| Test Case 8 | hand=[Card1, ..., Card91], index = 90, visibleHand=[Card91] | hand=[Card1, ..., Card90], size = 90, visibleHand=[]             | Yes          |
| Test Case 8 | hand=[Card1, ..., Card91], index = 1, visibleHand=[Card1]   | hand=[Card1, Card3, ..., Card91], size = 90, visibleHand=[Card1] | Yes          |