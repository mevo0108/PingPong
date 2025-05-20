# 🏓 Java Pong Game

```
   ╭─────────╮    ⚪    ╭─────────╮
   │         │   ╱│╲    │         │
   │    🟦   │  ╱ │ ╲   │    🟪  │
   │         │ ╱  │  ╲  │         │
   ╰─────────╯    │     ╰─────────╯
                  │
                  │
   Score: 0       │      Score: 0
```

A classic Pong game implementation in Java using Swing. This project features a two-player game with score tracking and smooth paddle movement.

## Features

- Two-player gameplay
- Score tracking
- Smooth paddle movement
- Increasing difficulty as the game progresses
- Modern UI with different colors for each player

## Quick Start (Recommended)

1. Download the latest release from [here](https://github.com/mevo0108/PingPong/releases/latest)
2. Extract the ZIP file
3. Double-click `run-pong.bat` to start the game

## Prerequisites

- Java Runtime Environment (JRE) 8 or higher
- Visual Studio Code (for development only)

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

## For Developers

### Using VS Code

1. Clone or download this repository
2. Open the project in VS Code
3. Install the required VS Code extensions:
   - Extension Pack for Java
   - Debugger for Java
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

## Project Structure

```
pong_game/
├── src/
│   └── Main.java         # Main game file containing all game logic
├── PongGame.jar         # Executable game file
├── run-pong.bat         # Easy launcher for Windows
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

## Demo and Download

- [Download Latest Version](https://github.com/mevo0108/PingPong/releases/latest)
- [View Demo Page](https://mevo0108.github.io/PingPong/) 
