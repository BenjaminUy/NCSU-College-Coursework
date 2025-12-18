import java.util.Scanner;
import java.util.Random;

/**
 * This program, when run, will start the game by generating a "secret code" that
 * the user has to find out by inputting a string in the format "# # # #". The program
 * will then show the number of correct digits and the number of correct locations. If
 * the user the doesn't input the secret code, it will be revealed after ten tries.
 * 
 * @author Benjamin Uy
 */
public class GuessingGame {

    /**
     * Constant representing the desired length of the secret code - 4 integers
     */
    public static final int LENGTH = 4;

    /**
     * Constant representing the number of rows in the storeGameProgress array,
     * which will keep track of the user's guesses. The number of rows corresponds
     * to the number of attempts the user has to guess the secret code.
     */
    public static final int ROWS_IN_STORE_ARRAY = 10;

    /**
     * Constant representing the number of columns in the storeGameProgress array.
     * Columns will store information such as guess digits, number of correct
     * digits, and number of correctly placed digits.
     */
    public static final int COLUMNS_IN_STORE_ARRAY = 6;

    /**
     * Constant representing the index in the storeGameProgress array where the
     * elements for correct digits are stored. Also, the indexes before 4
     * (0, 1, 2, 3) are used to store the guess digits. 
     */
    public static final int INDEX_FOR_CORRECT_DIGITS = 4;

    /**
     * Constant representing the index in the storeGameProgress array where the
     * elements for correctly placed digits are stored.
     */
    public static final int INDEX_FOR_CORRECT_PLACE = 5;

    /**
     * Constant representing the largest single digit. Used to determine invalid
     * input due to values being more than one-digit long.
     */
    public static final int LARGEST_DIGIT = 9;

    /**
     * Starts the program for the Guessing Game
     * @param args from command line, used to set the seed
     */
    public static void main (String[] args) {
        Random rand = new Random();
        if (args.length == 1) {
            try {
                int seed = Integer.parseInt(args[0]);
                rand.setSeed(seed);
            }
            catch (NumberFormatException e) {
                System.out.println("Usage: java -cp bin GuessingGame <seed>");
                System.exit(1);
            }
        } else if (args.length > 1) {
            System.out.println("Usage: java -cp bin GuessingGame <seed>");
            System.exit(1);
        }

        final int length = LENGTH;
        int[] secretCode = getSecretCode(rand, length);
        
        Scanner user = new Scanner(System.in);
        
        System.out.println();
        System.out.printf("%41s", "Welcome to the Guessing Game!\n");
        System.out.println("You must try to guess a secret code consisting of 4 digits.");
        System.out.println("After each guess,  the total number of correct digits (CD)");
        System.out.println("and the number of correct digits in the correct place (CP)");
        System.out.println("for the guess and all preceding guesses will be output. You");
        System.out.println("will have 10 chances to guess the secret code, which will");
        System.out.println("be revealed, if you do not guess it!");
        System.out.println();
        System.out.print("Guess 4 digits (e.g., 2 8 5 8): ");

        /* 
         * Create integer array that will store the user's guesses and show the number of 
         * correct digits and correctly placed digits for each guess
         */
        int[][] storeGameProgess = new int[ROWS_IN_STORE_ARRAY][COLUMNS_IN_STORE_ARRAY];

        /*
         * For loop gives the user ten attempts to guess the secret code. If the user
         * correctly guesses the secret code within ten tries, the loop will end. If not,
         * the code will be revealed after.
         */
        for (int attempt = 1; attempt <= 10; attempt++) {

            int[] guess = getGuess(user);
            int correctDigits = getCorrectDigits(secretCode, guess);
            int correctPlace = getCorrectDigitsInCorrectPlace(secretCode, guess);
            System.out.println();
            System.out.println(" Guess  | CD CP");

            /*
             * For every attempt, the number of printed rows showing the user's guesses
             * will increase by 1. For example, after the first guess (attempt), the program 
             * will print only 1 row.
             */
            for(int row = 0; row < attempt; row++) {
                /*
                 * For loop assigning and printing information such as guess digits,
                 * correct digits, and correctly placed digits
                 */
                for (int column = 0; column < COLUMNS_IN_STORE_ARRAY; column++) {
                    if (column < INDEX_FOR_CORRECT_DIGITS) {
                        storeGameProgess[attempt - 1][column] = guess[column];
                        System.out.print(storeGameProgess[row][column] + " ");
                    } else if (column == INDEX_FOR_CORRECT_DIGITS) {
                        storeGameProgess[attempt - 1][column] = correctDigits;
                        System.out.print("|  " + storeGameProgess[row][column] + " ");
                    } else if (column == INDEX_FOR_CORRECT_PLACE) {
                        storeGameProgess[attempt - 1][column] = correctPlace;
                        System.out.print(storeGameProgess[row][column]);
                        System.out.println();
                    }
                }
            }

            if (correctDigits == LENGTH && correctPlace == LENGTH) {
                System.out.println();
                System.out.print("You guessed correctly after " + attempt + " guess(es)!");
                attempt = 10;
            } else if (attempt < 10) {
                System.out.println();
                System.out.print("Guess 4 digits (e.g., 2 8 5 8): ");
            } else {
                System.out.println();
                System.out.print("Sorry, no more guesses - the secret code is ");
                for (int k = 0; k < secretCode.length; k++) {
                    if (k < secretCode.length - 1) {
                        System.out.print(secretCode[k] + " ");
                    } else {
                        System.out.print(secretCode[k]);
                    }
                }
            }
        }
    }

    /**
     * This method creates and returns an integer array representing the user's guess
     * for the secret code. 
     * 
     * @param user scanner that takes in user input
     * @return integer array representing the user's guess
     */
    public static int[] getGuess(Scanner user) {
        String[] guessIn = new String[LENGTH];

        /* Boolean determines if the program should reprompt the user to get input */
        boolean needValidInput = true;

        /* Variable representing the number of invalid inputs given by user */
        int numWrongInputs = 0;

        /* Boolean determines if the program outputs a message for invalid input */
        boolean firstPrompt = true;

        while (needValidInput == true) {
            if (firstPrompt != true) {
                System.out.println("Invalid guess");
                System.out.println();
                System.out.print("Guess 4 digits (e.g., 2 8 5 8): ");
            }

            /* User's responses will get stored as strings in an array */
            guessIn[0] = user.next();
            guessIn[1] = user.next();
            guessIn[2] = user.next();
            guessIn[3] = user.next(); 

            /* If the string is not a single digit, it is considered invalid */
            for (int k = 0; k < LENGTH; k++) {
                if (guessIn[k].length() != 1) {
                    numWrongInputs++;
                }
                switch (guessIn[k]) {
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        break;
                    default:
                        numWrongInputs++;
                }
            }

            if (numWrongInputs > 0) {
                needValidInput = true;
            } else if (numWrongInputs == 0) {
                needValidInput = false;
            }

            numWrongInputs = 0;
            firstPrompt = false;
        }

        /* Creating array of integers which will be returned by method */
        int[] guess = new int[LENGTH];
        for (int k = 0; k < LENGTH; k++) {
            guess[k] = Integer.parseInt(guessIn[k]);
        }
        return guess;
    }
    
    /**
     * This method creates and returns an integer array of the given length.
     * From index 1, the elements in the integer array will be 
     * determined by a rand instance which will generate digits between 0 and 9. 
     * 
     * @param rand instance that is used to pseudorandomly generate digits between 0 and 9
     * @param length integer value representing the desired length of the secret code
     * @return integer array of the given length representing the secret code
     * @throws IllegalArgumentException if rand is null
     * @throws IllegalArgumentException if length less than 1
     */
    public static int[] getSecretCode(Random rand, int length) {
        if (rand == null) {
            throw new IllegalArgumentException("Null rand");
        } else if (length < 1) {
            throw new IllegalArgumentException("Invalid length");
        }
        int[] code = new int[length];
        for (int x = 0; x < code.length; x++) {
            code[x] = rand.nextInt(10);
        }
        return code;
    }   

    /**
     * This method returns the number of digits in the guess array that are also in the
     * code array
     * 
     * @param code integer array representing the secret code
     * @param guess integer array representing the user's guess of the code
     * @return integer for the number digits in the guess array that are also in the code array
     * @throws IllegalArgumentException if code is null
     * @throws IllegalArgumentException if guess is null
     * @throws IllegalArgumentException if the lengths of the code and guess arrays differ
     * @throws IllegalArgumentException if code or guess array contains integer that is less than 0
     *      or greater than 9.
     */
    public static int getCorrectDigits(int[] code, int[] guess) {
        if (code == null) {
            throw new IllegalArgumentException("Null code");
        } else if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        } else if (code.length != guess.length)
            throw new IllegalArgumentException("Different lengths");
        for (int k = 0; k < code.length; k++) {
            if (code[k] > LARGEST_DIGIT || code[k] < 0 || guess[k] > 
                LARGEST_DIGIT || guess[k] < 0) {
                throw new IllegalArgumentException("Invalid digit");
            }
        }

        /* Variable representing the number of correct digits in the guess */
        int correctNumDigits = 0;

        /*
         * Create array of booleans to mark if a number in the secret code has already been
         * matched with a number in the guess code. Used to prevent digits in the guess
         * from counting to the number of correct digits if the secret code does not have 
         * enough of those digits.
         */
        boolean[] correctDigitExists = new boolean[code.length];
        for (int x = 0; x < correctDigitExists.length; x++) {
            correctDigitExists[x] = false;
        }

        /* Loop that goes through the elements of the guess array */
        for (int k = 0; k < guess.length; k++) {
            /*
             * Loop that goes through the elements of the code array. If secret code array element
             * is matched to a guess array element, it will be noted in a boolean array.
             */
            for (int g = 0; g < code.length; g++) {
                if (guess[k] == code[g] && correctDigitExists[g] == false) {
                    correctDigitExists[g] = true;
                    correctNumDigits++;
                    g = code.length;
                }
            }
        }
        return correctNumDigits;
    }

    /**
     * Returns the number of digits in the guess array that are in the same position
     * in the code array
     * 
     * @param code integer array representing the secret code
     * @param guess integer array representing the user's guess of the code
     * @return integer value for the number of correct digits in the correct position
     * @throws IllegalArgumentException if code is null
     * @throws IllegalArgumentException if guess is null
     * @throws IllegalArgumentException if the lengths of the code and guess arrays differ
     * @throws IllegalArgumentException if code or guess array contains integer that is less than 0
     *      or greater than 9.
     */
    public static int getCorrectDigitsInCorrectPlace(int[] code, int[] guess) {
        if (code == null) {
            throw new IllegalArgumentException("Null code");
        } else if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        } else if (code.length != guess.length)
            throw new IllegalArgumentException("Different lengths");
        for (int k = 0; k < code.length; k++) {
            if (code[k] > LARGEST_DIGIT || code[k] < 0 || guess[k] > 
                LARGEST_DIGIT || guess[k] < 0) {
                throw new IllegalArgumentException("Invalid digit");
            }
        }

        /* Variable representing the number of correctly placed digits in the guess */
        int correctDigitAndPlace = 0;

        /* Loop that goes compares the elements of the guess and code arrays */
        for (int k = 0; k < guess.length; k++) {
            if (guess[k] == code[k]) {
                correctDigitAndPlace++;
            }
        }
        return correctDigitAndPlace;
    }
}