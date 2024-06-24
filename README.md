# Minesweeper

Minesweeper is a classic Windows game where the objective is to avoid clicking on bombs. 
The player wins by successfully flagging all the bombs without detonating any.

# Ubiquitous Language

- Tile: A slot that can either contain a bomb or be an empty space.
- Board: The game space, a matrix of tiles where bombs are hidden.
- Flag: An element that can be placed on a hidden tile to mark it as a suspected bomb location.

# Rules

## Board Setup:

The game consists of a board, which is a matrix of tiles that can be of any desired size.

A number 𝑁 of bombs are randomly placed and hidden on the board.
The player is given 𝑁 + 𝑀 flags to use throughout the game.

## Gameplay:

A flag can be placed on any hidden tile. If all bombs are correctly flagged, the player wins.

Clicking on a tile reveals its content:
- If the tile contains a bomb, all unflagged bombs are revealed, and the game ends with the player losing.
- If the tile is empty, a number is shown indicating the count of tiles between the clicked tile and the nearest bomb.

## Winning Condition:

The game is won when all bombs are correctly flagged without any explosions.

## Losing Condition:

The game is lost if a tile containing a bomb is clicked, revealing all unflagged bombs.


