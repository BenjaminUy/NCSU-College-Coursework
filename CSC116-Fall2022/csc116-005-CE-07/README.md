# csc116-005-CE-07
1. In order to run our Connect4 game, you need to compile all of the source code files first: 
 - javac -d bin -cp bin src/*.java
2. After all the files have been compiled without any errors, run this command next:
 - java -cp bin Connect4Interface
3. Once the game has been started, you will be prompted to enter the max number of pieces you want to be connected in order to win (4 is the minimum, and 10 is the maximum). Enter an integer value ranging from 4 to 10 (inclusive), and press enter.
4. Once you have determined the max number of pieces to be connected for a win, an empty grid will show up, depending on the number of connected pieces you chose (e.g. 8x8 for 4 in a row, 10x10 for 5 in a row, etc.)
5. After the empty grid has been displayed, Player 1 (O) will begin. You will be prompted to enter in an integer value of the column you want to place a piece in – 1 will always be the smallest column number, not 0. The max column number will vary depending on your grid size.
6. After Player 1 places a piece, the grid will be redisplayed, with the piece placed in its correct position, along with the current maximum number of pieces connected and the current number of pieces placed.
7. Following those displayed numbers, Player 2 (X) will be prompted to play, similar to Player 1.
8. This order of displaying will continue until either a player has won or the board is full, leading to a draw.