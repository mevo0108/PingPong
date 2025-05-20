# Java Pong Game

A classic Pong game implementation in Java using Swing. This project features a two-player game with score tracking and smooth paddle movement.

## Features

- Two-player gameplay
- Score tracking
- Smooth paddle movement
- Increasing difficulty as the game progresses
- Modern UI with different colors for each player

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Visual Studio Code (recommended) or any Java IDE
- VS Code Extensions (if using VS Code):
  - Extension Pack for Java
  - Debugger for Java

## Setup and Running

### Using VS Code (Recommended)

1. Clone or download this repository
2. Open the project in VS Code
3. Install the required VS Code extensions if not already installed
4. Press F5 or use the Run and Debug menu to start the game

### Using Command Line

1. Navigate to the project directory
2. Compile the code:
   ```bash
   javac src/Main.java
   ```
3. Run the game:
   ```bash
   java -cp src Main
   ```

## How to Play

### Controls

#### Player 1 (Blue Paddle)
- W: Move paddle up
- S: Move paddle down

#### Player 2 (Magenta Paddle)
- Up Arrow: Move paddle up
- Down Arrow: Move paddle down

### Game Rules
- Each player controls a paddle on opposite sides of the screen
- The ball bounces between the paddles
- Score a point when your opponent misses the ball
- The ball speed increases slightly after each paddle hit
- The game continues until you close the window

## Project Structure

```
pong_game/
├── src/
│   └── Main.java         # Main game file containing all game logic
├── .vscode/             # VS Code configuration files
│   ├── launch.json      # Debug and run configuration
│   └── settings.json    # Project settings
└── README.md           # This file
```

## Development

The game is built using:
- Java Swing for the GUI
- AWT for graphics and event handling
- Custom game loop implementation for smooth animation

## License

This project is open source and available for educational purposes.

## Contributing

Feel free to fork this project and submit pull requests for any improvements. 