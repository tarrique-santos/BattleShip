# Battleship Game - DocumentedVersion

## Overview

This project is a text-based implementation of the classic Battleship game, where players take turns attempting to sink each other's fleet of ships on a 10x10 grid. The game offers two modes: Player vs. Machine and Player vs. Player.

## Features

- **Game Modes**:
  - Player vs. Machine
  - Player vs. Player
- **Ships**:
  - Submarine (1 slot)
  - Frigate (2 slots)
  - Corvette (3 slots)
  - Destroyer (4 slots)
- **Colored Board Representation** for different game elements:
  - Reset
  - Bold Magenta
  - Bold Red
  - Bold Yellow
  - Bold Green
  - Bold Cyan
  - Bold Light Green
  - Bold Blue
  - Bold Fuchsia
  - Bold White

## How to Play

1. **Start the Game**: Run the main method in the `DocumentedVersion` class.
2. **Choose Game Mode**:
   - `R` for Player vs. Machine
   - `M` for Player vs. Player
3. **Enter Player Names**:
   - Enter the name for Player 1.
   - If playing against the machine, the name for Player 2 will be selected randomly from predefined bot names. Otherwise, enter the name for Player 2.
4. **View and Interact with the Board**:
   - The board is a 10x10 grid with coordinates ranging from `A` to `J` for columns and `0` to `9` for rows.
   - Players take turns to input their target coordinates to shoot.
   - The game continues until one player sinks all the opponent's ships.

## Project Structure

- **Class**: `DocumentedVersion`
  - **Methods**:
    - `main(String[] args)`: The entry point of the program.
    - `ChooseMode()`: Allows players to choose the game mode.
    - `ChooseNamePlayers()`: Prompts players to enter their names.
    - `StartBoard()`: Initializes the game board.
    - `ScoreBoard()`: Displays the current score.
    - `ViewBoard(String[][] b)`: Displays the game board.
    - `AllocateShips(String[][] b)`: Allocates ships on the board.
    - `CanAllocate(int x, int y, boolean position, int length, String[][] b)`: Checks if a ship can be placed on the board.
    - `XCoordinateInput()`: Prompts for the X coordinate input.
    - `YCoordinateInput()`: Prompts for the Y coordinate input.
    - `LetterToNumber(String y)`: Converts a letter coordinate to a number.
    - `PlayGame()`: Controls the main game loop.
    - `InsertSeaIntoBoard(String[][] fb, int x, int y)`: Inserts a sea mark on the board.
    - `Shoot(String target, String fakeTarget)`: Handles shooting logic.
    - `TargetAlreadyReached(String fakeTarget)`: Checks if a target has already been shot.
    - `PassingTurn()`: Handles turn passing logic.
    - `Scoring(String[][] fb, int x, int y, String target)`: Handles scoring logic.
    - `TextPositionInvalid()`: Displays an invalid position message.
    - `IsNumberValid(String xIndex)`: Validates numeric input.
    - `GetNameShip(String target)`: Returns the name of the ship based on the target.

## Color Codes

The game uses ANSI escape codes to represent colors in the console:

- `\u001B[0m`: Reset
- `\u001B[1;35m`: Bold Magenta
- `\u001B[1;31m`: Bold Red
- `\u001B[1;33m`: Bold Yellow
- `\u001B[1;32m`: Bold Green
- `\u001B[1;36m`: Bold Cyan
- `\u001B[1;92m`: Bold Light Green
- `\u001B[1;34m`: Bold Blue
- `\u001B[1;1m`: Bold Fuchsia
- `\u001B[1;37m`: Bold White

## Running the Game

1. **Compile** the `DocumentedVersion` class.
2. **Run** the compiled class from the command line or an IDE of your choice.

# Battleship Game

## Cloning and Setting Up the Project

### Prerequisites

Make sure you have the following items installed on your machine:

1. [Git](https://git-scm.com/downloads)
2. [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)

### Step 1: Cloning the Repository

1. Open the terminal (or command prompt on Windows).
2. Navigate to the directory where you want to clone the repository.
3. Run the following command to clone the repository:

```bash
git clone https://github.com/tarrique-santos/BattleShip
cd BattleShip
```

### Opening in Visual Studio Code (VSCode)

1. Open Visual Studio Code.
2. Click on "File" in the menu bar.
3. Select "Open Folder...".
4. Navigate to the directory where you cloned the repository and click "Open".
5. Alternatively, you can open the project directly from the terminal by running:

```bash
code .
```
### Opening in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Click on "File" in the menu bar.
3. Select "Open..." or "Open Project...".
4. Navigate to the directory where you cloned the repository and select the project folder.
5. Click "Open".
6. Alternatively, you can open the project directly from the terminal by running:

```bash
idea .
```

# Author
## Created by Tarrique D. Santos.

# License
## This project is open source and available under the MIT License.

Enjoy the game and have fun sinking those ships!
