# Instructor feedback
## Progress evaluation :scroll:
- :white_check_mark: Game Setup Phase is done.
- :white_check_mark: Good progress on single turn.
- :question: UI implementation is found in the main branch.

## Comments :speech_balloon:
1. There are some code coverage issues. For instance, Game's constructor is not fully covered. Particularly, it's
``` 
if (numberOfPlayers > MAXIMUM_PLAYERS) {
    numberOfPlayers = MAXIMUM_PLAYERS;
    System.out.println("Player count exceeds max limit. Setting to " + MAXIMUM_PLAYERS);
}
```
And this exposes a design issue too. It shouldn't print, but throw an exception.

2. Mutation testing also reveals some issues.  For instance, a couple of mutants survived in Game, like
``` 
 private int determineNumberOfCards(int numberOfPlayers) {
50		
        if (numberOfPlayers < 4) {
51		
            return SMALL_GAME_CARDS;
52		
        }
53		
        else if (numberOfPlayers <= 7) {
54		
            return MEDIUM_GAME_CARDS;
55		
        }
```

3. Not a ton of code review comments are found in the 
closed PRs so far. This could mean one of the two things:
maybe the code has been clean upon review and there is no 
further revision needed, or maybe the team should conduct
more manual code review. 

But if I were to review the Game class, for instance, I would point out 
the problems above and suggest that 1) Game is not unit testable, and 2) the nested for loop in the constructor should be put in some private method.