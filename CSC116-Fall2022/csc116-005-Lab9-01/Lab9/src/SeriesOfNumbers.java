import java.util.Scanner;

/**
 * A program with a for loop that produces series of squares based on user input
 * 
 * @author Aaron Ferguson
 * @author Benjamin Uy
 */
public class SeriesOfNumbers {

    /**
     * Starts the program that generates series of squares
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("This program will display a series of numbers that "
                + "are the squares of consecutive values to the max (user-provided integer)."
                + "\nNote: program will reprompt for max until a positive integer is given.");
        
        // Declare max variable that determines the number of squared values in printed series
        int max;
        
        /*
         * Prompts user for max and continues to do so until an integer between 1 and 10, 
         * inclusive, is given
         */
        do {
            max = getInteger(in);
        } while (max < 1 || max > 10);

        // Prints the series of squared values from 1 to the number given by user
        System.out.println(getSeries(max));
    }

    /**
     * Returns integer value from the Scanner. Continues to reprompt for integer
     * until an integer value is given.
     * 
     * @param in input scanner
     * @return integer value from Scanner
     */
    public static int getInteger(Scanner in) {
        // Prompt user for integer
        System.out.print("Enter integer: ");

        // Continues to prompt user until input can be read as integer
        while (!in.hasNextInt()) {
            in.next();
            System.out.print("Enter integer: ");
        }
        return (in.nextInt());
    }

    /**
     * Gets the series of squares from 1 to max. Method cannot use multiplication or
     * division.
     * 
     * @param max max number to square
     * @return series of squares from 1 to max
     * @throws IllegalArgumentException if max is non-positive. Message:
     *             Non-positive integer (max)
     */
    public static String getSeries(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Non-positive integer (" + max + ")");
        }

        /*
         * Declare and initialize variable for the value of the first squared number: 1. 
         * Value will increase as loop continues. Used in the squaredRange String.
         */ 
        int squaredNumber = 1;

        /*
         * Declare and initialize constant that is the difference between the squared values
         * of 1 and 2 which are 1 and 4, respectively
         */
        final int DIFFERENCE_BETWEEN_1_AND_4 = 3;

        /*
         * Declare and initialize variable that describes the distance between squares;
         * Starting at 3, increases by 2 after each squared value.
         * For example, from 1^2 to 2^2 (1 to 4) the value between squares is 3.
         * From 2^2 to 3^2 (4 to 9), the value is increased by 2 to 5.
         */
        int valueBetweenSquares = DIFFERENCE_BETWEEN_1_AND_4;

        String squaredRange = "";
        
        /*
         * This for loop compares a generic variable "k" to the max integer value given
         * by the user at start of program. While k is less than max, the program goes
         * the control statements; afterwards, k is increased by 1.
         */
        for (int k = 1; k <= max; k++) {
            // If k equals max, squaredRange String will end without a space
            if (k == max) {
                squaredRange = squaredRange + squaredNumber + "";
                squaredNumber = squaredNumber + valueBetweenSquares;
                valueBetweenSquares = valueBetweenSquares + 2;
            // If k is less than max, squaredRange String will end with a space
            } else if (k < max) {
                squaredRange = squaredRange + squaredNumber + " ";
                squaredNumber = squaredNumber + valueBetweenSquares;
                valueBetweenSquares = valueBetweenSquares + 2;
            }
        }
        return squaredRange;
    }
}
