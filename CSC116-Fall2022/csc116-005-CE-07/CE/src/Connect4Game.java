/**
 * Class for Connect4Game
 * 
 * @author Noah Evans Lehner
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 * @author Aman Anas
 */
public class Connect4Game {

    /** An integer that represents the number of rows in the grid (2D array) */
    private int rows;

    /** An integer that represents the number of columns in the grid (2D array) */
    private int cols;

    /** An integer that represents the number of max connected pieces */
    private int maxToConnect;

    /** An integer that represents the number of moves */
    private int numOfMoves;

    /** A boolean that tracks whether or not the game is finished */
    private boolean gameFinished;

    /** Boolean to track whether or not the game has ended in a draw */
    private boolean draw;

    /**
     * A 2D array of Piece objects. Each element in the array is a single Piece
     * object
     */
    private Piece[][] pieces;

    /** An integer that represents the min number for maxToConnect */
    private static final int MIN_CONNECT = 4;

    /** An integer that represents the max number for maxToConnect */
    private static final int MAX_CONNECT = 10;

    /**
     * Constructor for Connect4Game
     * 
     * @param maxToConnect max number of pieces to connect to win the game
     * @throws IllegalArgumentException if maxToConnect is less than 4 or greater
     *                                  than 10,
     *                                  Message: Invalid maxToConnect
     */
    public Connect4Game(int maxToConnect) {
        // Checks if maxToConnect is less than 4 or greater than 10
        if (maxToConnect < MIN_CONNECT || maxToConnect > MAX_CONNECT) {
            throw new IllegalArgumentException("Invalid maxToConnect");
        }

        this.gameFinished = false;
        this.maxToConnect = maxToConnect;
        this.rows = maxToConnect * 2;
        this.cols = maxToConnect * 2;
        this.numOfMoves = 0;
        this.pieces = new Piece[rows][cols];
    }

    /**
     * Method that gets the number of rows in the grid
     * 
     * @return rows as integer
     */
    public int getRows() {
        return rows;
    }

    /**
     * Method that gets the number of columns in the grid
     * 
     * @return cols as integer
     */
    public int getCols() {
        return cols;
    }

    /**
     * Method that returns the maximum number connected pieces
     * 
     * @return maxConnected as integer
     */
    public int getMaxToConnect() {
        return maxToConnect;
    }

    /**
     * Method that returns the number of moves
     * 
     * @return numOfMoves as integer
     */
    public int getNumOfMoves() {
        return numOfMoves;
    }

    /**
     * Method that returns gameFinished boolean
     * 
     * @return gameFinished as boolean
     */
    public boolean getGameFinished() {
        return gameFinished;
    }

    /**
     * Method that returns draw boolean
     * 
     * @return draw as boolean
     */
    public boolean getGameDraw() {
        return draw;
    }

    /**
     * Method updates the game state (gameFinished and draw conditions) based on
     * how filled the grid is.
     */
    public void updateGameState() {
        // Find number of connected pieces
        boolean full = true;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (pieceExists(col, row)) {
                    int numConnected = getMaxConnectedToPiece(col, row);
                    if (numConnected >= maxToConnect) {
                        this.gameFinished = true;
                    }
                } else {
                    full = false;
                }

            }
        }
        if (full) {
            this.gameFinished = true;
            this.draw = true;
        }
    }

    /**
     * Method to get the max connected pieces for the specified type
     * 
     * @param type type as a char
     * @return max connected pieces for the specified type
     * @throws IllegalArgumentException if the type is a null character,
     *                                  Message: Type is a null character
     */
    public int getMaxConnectedType(char type) {
        // Checks if the type is a null character
        if (type == '\0') {
            throw new IllegalArgumentException("Type is a null character");
        }

        int maxConnect = 0;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (pieceExists(col, row)) {
                    if (getPieceAtPos(col, row).getType() == type) {
                        int numConnected = getMaxConnectedToPiece(col, row);
                        if (numConnected >= maxConnect) {
                            maxConnect = numConnected;
                        }
                    }
                }
            }
        }
        return maxConnect;
    }

    /**
     * Method to get the number of pieces placed for the specified type
     * 
     * @param type type as a char
     * @return the number of pieces placed for the specified type
     * @throws IllegalArgumentException if the type is a null character,
     *                                  Message: Type is a null character
     */
    public int getNumPlacedType(char type) {
        // Checks if the type is a null character
        if (type == '\0') {
            throw new IllegalArgumentException("Type is a null character");
        }

        int count = 0;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (pieceExists(col, row)) {
                    if (getPieceAtPos(col, row).getType() == type) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Method to return the number of similar pieces connected to a given row and
     * column in a given direction. Includes the piece at that position.
     * 
     * @param startCol     The starting column to look at
     * @param startRow     The starting row to look at
     * @param colDirection The step size and direction in the vertical direction
     * @param rowDirection The step size and direction in the horizontal direction
     * @return The number of connected pieces in that direction, as an integer
     * @throws IllegalArgumentException if start col is negative or is greater than
     *                                  or equal to the number of cols
     *                                  Message: Invalid col
     * @throws IllegalArgumentException if start row is negative or is greater than
     *                                  or equal to the number of rows
     *                                  Message: Invalid row
     * @throws IllegalArgumentExcpetion if no piece exists at start position
     *                                  Message: Invalid position, no piece exists
     *                                  at start position
     */
    public int findConnectedDir(int startCol, int startRow, int colDirection, int rowDirection) {
        if (startCol < 0 || startCol >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        } else if (startRow < 0 || startRow >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        } else if (!pieceExists(startCol, startRow)) {
            throw new IllegalArgumentException("Invalid position, no piece exists"
                    + " at start position");
        }

        int connected = 1;
        Piece startPiece = getPieceAtPos(startCol, startRow);
        char currentType = startPiece.getType();

        int currentCol = startCol;
        int currentRow = startRow;

        while (true) {
            currentCol += colDirection;
            currentRow += rowDirection;

            if (currentCol < 0 || currentCol > cols - 1) {
                break;
            } else if (currentRow < 0 || currentRow > rows - 1) {
                break;
            } else if (!pieceExists(currentCol, currentRow)) {
                break;
            }

            Piece currentPiece = getPieceAtPos(currentCol, currentRow);
            if (currentType == currentPiece.getType()) {
                connected += 1;
            } else {
                break;
            }
        }
        return connected;
    }

    /**
     * Method to get the maximum number of similar pieces connected to a piece at a
     * given column and row
     * 
     * @param col The column of the piece
     * @param row The row of the piece
     * @return The maximum number of connected pieces to that piece
     * @throws IllegalArgumentException if start col is negative or is greater than
     *                                  or equal to the number of cols
     *                                  Message: Invalid col
     * @throws IllegalArgumentException if start row is negative or is greater than
     *                                  or equal to the number of rows
     *                                  Message: Invalid row
     * @throws IllegalArgumentExcpetion if no piece exists at start position
     *                                  Message: Invalid position, no piece exists
     *                                  at start position
     */
    public int getMaxConnectedToPiece(int col, int row) {
        if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        } else if (row < 0 || row >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        } else if (!pieceExists(col, row)) {
            throw new IllegalArgumentException("Invalid position, no piece exists"
                    + " at start position");
        }

        int currentMax = 1;
        int connected;
        for (int colDirection = -1; colDirection < 2; colDirection++) {
            for (int rowDirection = -1; rowDirection < 2; rowDirection++) {
                if (!(colDirection == 0 && rowDirection == 0)) {
                    connected = findConnectedDir(col, row, colDirection, rowDirection);
                    if (connected > currentMax) {
                        currentMax = connected;
                    }
                }
            }
        }
        return currentMax;
    }

    /**
     * Check if piece exists at a certain position
     * 
     * @param col column of position
     * @param row row of position
     * @return true if piece exists, false otherwise
     * @throws IllegalArgumentException if start col is negative or is greater than
     *                                  or equal to the number of cols
     *                                  Message: Invalid col
     * @throws IllegalArgumentException if start row is negative or is greater than
     *                                  or equal to the number of rows
     *                                  Message: Invalid row
     */
    public boolean pieceExists(int col, int row) {
        if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        } else if (row < 0 || row >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        }

        if (pieces[col][row] == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method returns the Piece in the grid at the given row and column
     * 
     * @param row integer representing row position in the grid
     * @param col integer representing column position in the grid
     * @return Piece in the 2D array at the given row and column
     * @throws IllegalArgumentException if col is less than 0 or greater than
     *                                  or equal to the number of columns
     *                                  Message: Invalid col
     * @throws IllegalArgumentException if row is less than 0 or greater than
     *                                  or equal to the number of rows
     *                                  Message: Invalid row
     */
    public Piece getPieceAtPos(int col, int row) {
        if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        } else if (row < 0 || row >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        }

        return pieces[col][row];
    }

    /**
     * Method goes through pieces array to place Piece object in the first open
     * (null) array location in the given column
     * 
     * @param col integer representing column position in the grid
     * @param s   Piece object that is being added to the grid
     * @return boolean for whetehr Piece s is added to the grid
     * @throws IllegalArgumentException if col is less than 0 or greater than
     *                                  or equal to the number of columns
     *                                  Message: Invalid col
     * @throws IllegalArgumentException if s is null
     *                                  Message: Null s
     */
    public boolean placePiece(int col, Piece s) {
        if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        } else if (s == null) {
            throw new IllegalArgumentException("Null s");
        }

        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i] == null) {
                pieces[col][i] = s;
                numOfMoves += 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Method takes the pieces array and prints it as a string arrangement
     * 
     * @return string representation of the pieces (grid) array
     */
    public String piecesToString() {
        String displayStr = "";

        for (int col = 0; col < 1 + (this.cols * 2); col++) {
            displayStr += "-";
        }
        displayStr += "\n";

        for (int row = this.rows - 1; row >= 0; row--) {

            displayStr += " ";
            for (int col = 0; col < this.cols; col++) {
                if (pieceExists(col, row)) {
                    displayStr += pieces[col][row].toString();
                } else {
                    displayStr += "-";
                }
                displayStr += " ";
            }
            displayStr += "\n";

        }
        for (int col = 0; col < 1 + (this.cols * 2); col++) {
            displayStr += "-";
        }
        displayStr += "\n";

        return displayStr;
    }
}