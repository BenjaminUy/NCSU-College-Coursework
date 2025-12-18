import java.util.Random;

/**
 * Class that handles logic behind WhackaMole game
 * 
 * @author Benjamin Uy
 */
public class WhackaMole {
  
    /** Constant representing the number of rows in Grid object */
    public static final int ROWS = 5;

    /** Constant representing the number of columns in Grid object */
    public static final int COLS = 5;

    /** String array containing the symbol names that will be stored in the Grid */
    public static final String[][] SYMBOL_NAMES = {{"cat", "dog", "tiger", "frog", "cat"}, 
                                                   {"tiger", "lion", "dog", "tiger", "frog"},
                                                   {"lion", "frog", "mole",  "dog", "cat"},
                                                   {"frog", "dog", "tiger", "cat", "lion"},
                                                   {"cat", "frog", "lion", "dog", "tiger"}};
                                                
    /** Integer array containing the symbols points that will be stored in the Grid */
    public static final int[][] SYMBOL_POINTS = {{10, 15, 30, 20, 10},
                                                 {30, 40, 15, 30, 20},
                                                 {40, 20, 50, 15, 10},
                                                 {20, 15, 30, 10, 40},
                                                 {10, 20, 40, 15, 30}};

    /** Creates the boolean for whether or not the game is in testing mode */
    private boolean testing;

    /** Creates the integer for the total score of the player */
    private int totalScore;

    /** Creates the integer for the number of misses made by the player */
    private int numberOfMisses;

    /** Creates a Grid object that represents how the symbols are arranged in the game */
    private Grid grid;

    /** Creates a Random object that is used to choose the next row and column if not testing */
    private Random rand;

    /** Creates the integer for the value of the row of the next symbol to be displayed */
    private int nextRow;

    /** Creates the integer for the value of the column of the next symbol to be displayed */
    private int nextCol;

    /**
     * Constructor method of the WhackaMole class
     * @param testing boolean for whether or not game is in testing mode
     */
    public WhackaMole(boolean testing) {
        this.testing = testing;
        grid = new Grid(ROWS, COLS);
        for (int i = 0; i < ROWS; i++) {
            for (int k = 0; k < COLS; k++) {
                grid.setSymbol(i, k, new Symbol(SYMBOL_NAMES[i][k], SYMBOL_POINTS[i][k]));
            }
        }
        if (testing == true) {
            nextRow = 0;
            nextCol = 0;
        } else {
            rand = new Random();
            nextRow = rand.nextInt(ROWS);
            nextCol = rand.nextInt(COLS);
        }
    }

    /**
     * Method returns the total score for the player
     * @return integer for the total score fir the player
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Method returns the number of misses made by the player
     * @return integer for the number of misses made by the player
     */
    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    /**
     * Method returns the row of the next Symbol to be displayed
     * @return integer for the row of the next Symbol to be displayed
     */
    public int getNextRow() {
        return nextRow;
    }

    /**
     * Method returns the column of the next Symbol to be displayed
     * @return integer for the column of the next Symbol to be displayed
     */
    public int getNextCol() {
        return nextCol;
    }

    /**
     * Method returns the name of the Symbol at the given row and col in the Grid
     * @param row integer representing row in the Grid
     * @param col integer representing column in the Grid
     * @return String for the name of the Symbol
     * @throws IllegalArgumentException if row is less than 0 or greater than or equal
     *      to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than or equal
     *      to the number of columns
     */
    public String getSymbolName(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return ((grid.getSymbol(row, col)).getName());
    }

    /**
     * Method returns the points for the Symbol at the given row and col in the Grid 
     * @param row integer representing row in the Grid
     * @param col integer representing column in the Grid
     * @return integer for the number of points for the Symbol
     * @throws IllegalArgumentException if row is less than 0 or greater than or equal
     *      to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than or equal
     *      to the number of columns
     */
    public int getSymbolPoints(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return ((grid.getSymbol(row, col)).getPoints());
    }

    /**
     * Method returns true if the Symbol at the given row and col in the Grid has
     * been clicked on and false otherwise
     * @param row integer representing row in the Grid
     * @param col integer representing column in the Grid
     * @return boolean for whether or not Symbol has been clicked on
     * @throws IllegalArgumentException if row is less than 0 or greater than or equal
     *      to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than or equal
     *      to the number of columns
     */
    public boolean hasBeenClickedOn(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return (grid.getSymbol(row, col)).hasBeenClickedOn();
    }

    /**
     * Method returns true if every Symbol in the grid has been clicked on
     * @return boolean for whether or not every Symbol in the grid has been clicked on
     */
    public boolean allSymbolsClickedOn() {
        for (int i = 0; i < ROWS; i++) {
            for (int k = 0; k < COLS; k++) {
                if (hasBeenClickedOn(i, k) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method sets the row and column of the next Symbol to be displayed.
     */
    private void updateNextRowAndCol() {
        if (allSymbolsClickedOn() == true) {
            nextRow = -1;
            nextCol = -1;
        } else if (testing == true) {
            do { 
                if (nextCol < COLS - 1) {
                    nextCol++;
                } else if (nextCol == COLS - 1 && (nextRow < ROWS - 1)) {
                    nextCol = 0;
                    nextRow++;
                } else if (nextCol == COLS - 1 && nextRow == ROWS - 1) {
                    nextCol = 0;
                    nextRow = 0;
                }
            } while (hasBeenClickedOn(nextRow, nextCol) == true);
        } else {
            rand = new Random();
            do {
                nextCol = rand.nextInt(COLS);
                nextRow = rand.nextInt(ROWS); 
            } while (hasBeenClickedOn(nextRow, nextCol) == true);   
        }
    }

    /**
     * Method sets the Symbol to 'has been clicked on' and updates the total score
     * based on the points of the Symbol
     * @param row integer representing row in the Grid
     * @param col integer representing column in the Grid
     * @throws IllegalArgumentException if row is less than 0 or greater than or equal
     *      to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than or equal
     *      to the number of columns
     */
    public void clickOnSymbol(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        if (hasBeenClickedOn(row, col) == false) {
            grid.getSymbol(row, col).setHasBeenClickedOn(true);
            totalScore += getSymbolPoints(row, col);
            updateNextRowAndCol();
        }
    }

    /**
     * Method adds one to the number of misses and calls updateNextRowAndCol() method
     */
    public void addMiss() {
        numberOfMisses++;
        updateNextRowAndCol();
    }

    /**
     * Method returns the Grid object
     * @return Grid object
     */
    public Grid getGrid() {
        return grid;
    }

    
}
