![Gradle Build](https://github.com/nu-cs-sqe/course-project-20242510-team-04/actions/workflows/main.yml/badge.svg)

# Exploding Kittens

## Contributors
- Clement Kubica
- Aryaman Chawla
- Emran Majidy
- Patrick Chen

## Dependencies
- JDK 11
- JUnit 5.10
- Gradle 8.10

## Acknowledgements
- [Exploding Kittens](https://explodingkittens.com/)
- Prof. Zhang

## Expansion packs
- Imploding kittens
- Streaking kittens
- Party pack

## How to play
- Run Main.main() to start the game

## Missing mutation and code coverage
- We have not implemented mutation testing and code coverage for the UI code. This includes the takeTurn, displayPlayerHand and displayMarkCards functions in Game.
- This also includes GameController and Main.
- Mutation coverage for the game constructor's deck shuffle cannot be achieved due to the inherent randomness of the shuffle process.
