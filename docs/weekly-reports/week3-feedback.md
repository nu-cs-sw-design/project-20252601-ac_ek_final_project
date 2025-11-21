# Intructor feedback
## Progress evaluation :scroll:
- :white_check_mark: gitignore is set up.
- :white_check_mark: Branch protection rule for requiring PR is set up.
- :white_check_mark: Branch protection rule for requiring status check is set up.
- :question: Status check badge on README is set up. (But this is not on you for not setting it up -- it was missed from the project setup instruction)
- :white_check_mark: Project manegement board is set up.
- :question: Project management board shows clear planning of action items.
- :question: Team has started working on implementing Game Setup Phase.
- :white_check_mark: The weekly report is written.

  
## Comments :speech_balloon:
Overall good setup!

1)  .gitignore starts with "# Waiting for the team to complete this doc." -- not sure whether the doc is fully done or not. If it is done, delete this comment.
2)  I storngly suggest the team to start working on the Game Setup Phase. To do that, first thoroughly understand the game rules and identify what is required for players to begin taking a turn. For instance, perhaps the software needs to first ask how many players are playing, and then set up the game board/deck/cards, and then maybe needs to deal cards/assign resources/let player place initial troops etc. After understanding the scope of this phase, break it down further into steps of computation that should happen. Then, draft what classes are needed and what methods are needed for each step. Split the tasks among the teammates, and then each implements their parts using BVA and TDD.
3) In the weekly report (and in any future docs), if you are provided a template, but some sections are not relevant or completed, please delete them. The goal is to produce clean docs (and code). 
