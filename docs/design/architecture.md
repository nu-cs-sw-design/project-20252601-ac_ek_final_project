# Exploding Kittens - Architecture Documentation

## Overview

The Exploding Kittens game follows a modified Three Layers pattern.

### Layers

#### 1. Application Layer

This layer allows the user to actually run the program.

#### 2. Domain Layer

- This layer contains all core business logic and game rules, and is independent of the UI.
- It is broken down into 4 sub-packages, each of which handles a core aspect of the game (game, player, deck, cards)

### 3. UI Layer

This layer handles user interaction through various interfaces.