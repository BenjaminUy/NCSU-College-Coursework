/**
 * Class that represents a two-dimensional layout of symbols
 * 
 * @author Benjamin Uy
 */
public class Grid {

    /** Creates integer representing number of rows in the grid */
    private int rows;

    /** Creates integer representing number of columns in the grid */
    private int cols;

    /** Creates 2D array of Symbol objects; each array element being a single Symbol object */
    private Symbol[][] symbols;

    /**
     * Constructor method for Grid class 
     * @param rows integer representing number of rows in the grid
     * @param cols integer representing number of columns in the grid
     * @throws IllegalArgumentException if rows or cols is less than 1
     */
    public Grid(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Invalid rows/cols");
        }
        this.rows = rows;
        this.cols = cols;
        this.symbols = new Symbol[rows][cols];
    }

    /**
     * Method that gets the number of rows in the grid
     * @return the number of rows in the grid
     */
    public int getRows() {
        return rows;
    }

    /**
     * Method that gets the number of columns in the grid
     * @return number of columns in the grid
     */
    public int getCols(){
        return cols;
    }

    /**
     * Method stores the symbol in the 2D array at the given row and column
     * @param row integer representing row position in the grid
     * @param col integer representing column position in the grid
     * @param symbol Symbol object is stored in the 2D grid array
     * @throws IllegalArgumentException if symbol is null
     * @throws IllegalArgumentException if row is less than 0 or greater than 
     *                                  or equal to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than
     *                                  or equal to the number of columns        
     */
    public void setSymbol(int row, int col, Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Null symbol");
        } else if (row < 0 || row >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        }   
        symbols[row][col] = symbol;
    }

    /**
     * Method returns the symbol in the array at the given row and column
     * @param row integer representing row position in the grid
     * @param col integer representing column position in the grid
     * @return symbol in the 2D array at the given row and column
     * @throws IllegalArgumentException if row is less than 0 or greater than 
     *                                  or equal to the number of rows
     * @throws IllegalArgumentException if col is less than 0 or greater than
     *                                  or equal to the number of columns  
     */
    public Symbol getSymbol(int row, int col) {
        if (row < 0 || row >= getRows()) {
            throw new IllegalArgumentException("Invalid row");
        } else if (col < 0 || col >= getCols()) {
            throw new IllegalArgumentException("Invalid col");
        }
        return symbols[row][col];
    }

    /**
     * Method creates a String representaiton of the grid and contains the name
     * of each symbol in the grid. The names in each row are separated by a space
     * @return String representation of the grid containing the name of each symbol
     */
    public String toString() {
        String gridOfNames = "";
        for (int i = 0; i < symbols.length; i++) {
            for (int k = 0; k < symbols[0].length; k++) {
                if (k < symbols[0].length - 1) {
                    gridOfNames += symbols[i][k].getName() + " ";
                } else {
                    gridOfNames += symbols[i][k].getName() + "\n";
                }
            }
        }
        return gridOfNames;
    }
}
