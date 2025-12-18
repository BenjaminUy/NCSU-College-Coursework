
# Connect Four

I loved playing the two-player game Connect Four as a child and want you to write a Java implementation of the game. With the game, there is an 8x8 vertical grid on which players take turns placing their pieces. The goal of the game is to have four of your game pieces connected---in a row, column, or diagonally. When a player places a piece in a column, the piece will  land in the bottom-most available row (see example below). 

In the following 4x4 grid, if I were to add a piece to the first column, it would land in the bottom row since nothing else is in the column. If I were to add a piece to the second column, it would land in the second row from the bottom on top of the ‘O’ since it is the bottom-most available row.

| **-** | **-** | **-** | **-** |
|---|---|---|---|
| **-** | **-** | **-** | **-** |
| **-** | **O** | **-** | **-** |
| **-** | **X** | **-** | **-** |


As the players play the game, they should be updated with how many pieces they have placed and the max number of connected game pieces they currently have (this will be less than four until the game is won).
