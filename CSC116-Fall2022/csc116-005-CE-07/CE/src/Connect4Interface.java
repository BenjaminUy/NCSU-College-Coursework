import java.util.Scanner;

/**
 * Class for UserInterface
 * 
 * @author Noah Evans Lehner
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 * @author Aman Anas
 */
public class Connect4Interface {

    /** 
     * A string that prompts the user how many pieces (4 to 10, inclusive)
     * should be connected to win
     */
    private static final String NUM_PIECES_PROMPT = "How many pieces (4 to 10, inclusive)"
                                                    + " should be connected to win: ";

    /** A char that represents the character of player one */
    private static final char PLAYER_ONE_CHAR = 'O';

    /** A char that represents the character of player two */
    private static final char PLAYER_TWO_CHAR = 'X';

    /** An integer that represents the min number for connectedToWin */
    private static final int MIN_CONNECT = 4;

    /** An integer that represents the max number for connectedToWin */
    private static final int MAX_CONNECT = 10;

    /**
     * Starts the program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Connect4Game (model) instanced
        Connect4Game game;

        // Creates Scanner Object for user input
        Scanner input = new Scanner(System.in).useDelimiter("\r?\n");

        // Checks if there are two arguments on the command line
        if (args.length != 0) {
            System.out.println("Usage: java Connect4Interface");
            System.exit(1);
        }

        // Variables to track how many games won for each player
        int p1GamesWon = 0;
        int p2GamesWon = 0;

        // Declares and initializes the variable
        boolean endAllGames = false;

        while (endAllGames == false) {

            // Declares and initializes the variable
            int connectedToWin = 0;

            do {
                // Prompts the user for the number of pieces connected to win
                System.out.print(NUM_PIECES_PROMPT);
                while (!input.hasNextInt()) {
                    System.out.print(NUM_PIECES_PROMPT);
                    input.next();
                }
                connectedToWin = input.nextInt();

            } while ((connectedToWin < MIN_CONNECT || connectedToWin > MAX_CONNECT));

            game = new Connect4Game(connectedToWin);

            // Display the grid
            System.out.println(game.piecesToString());

            // Declares and initializes the variable
            boolean playerOneTurn = true;

            while (game.getGameFinished() == false) {

                if (playerOneTurn) {
                    System.out.println("Player 1 (O)'s turn.");

                    // Declares and initializes the variable
                    int column = 0;

                    do {
                        System.out.print("Enter a column (1-" + game.getCols() + "): ");

                        while (!input.hasNextInt()) {
                            input.next();
                            System.out.print("Enter a column (1-" + game.getCols() + "): ");
                        }

                        column = input.nextInt();
                    } while (column < 1 || column > game.getCols());

                    if (game.placePiece(column - 1, new Piece(PLAYER_ONE_CHAR))) {
                        playerOneTurn = !playerOneTurn;
                    }

                } else {
                    System.out.println("Player 2 (X)'s turn.");

                    // Declares and initializes the variable
                    int column = 0;

                    do {
                        System.out.print("Enter a column (1-" + game.getCols() + "): ");

                        while (!input.hasNextInt()) {
                            input.next();
                            System.out.print("Enter a column (1-" + game.getCols() + "): ");
                        }

                        column = input.nextInt();

                    } while (column < 1 || column > game.getCols());

                    if (game.placePiece(column - 1, new Piece(PLAYER_TWO_CHAR))) {
                        playerOneTurn = !playerOneTurn;
                    }

                }
                game.updateGameState();
                System.out.println(game.piecesToString());
                System.out.println("-------------------------------\n"
                        + "Current max connected:\nPlayer one: "
                        + game.getMaxConnectedType(PLAYER_ONE_CHAR)
                        + "  |  Player two: " + game.getMaxConnectedType(PLAYER_TWO_CHAR)
                        + "\n-------------------------------");
                System.out.println("-------------------------------\n"
                        + "Current placed pieces:\nPlayer one: "
                        + game.getNumPlacedType(PLAYER_ONE_CHAR)
                        + "  |  Player two: " + game.getNumPlacedType(PLAYER_TWO_CHAR)
                        + "\n-------------------------------\n");

            }
            System.out.println("Game finished!");
            if (game.getGameDraw()) {
                System.out.println("Draw! No players win :(");
            } else if (playerOneTurn) {
                System.out.println("Player 2 (X) wins!");
                p2GamesWon += 1;
            } else {
                System.out.println("Player 1 (O) wins!");
                p1GamesWon += 1;
            }

            System.out.println("---------\nCurrent scores\nPlayer one: " + p1GamesWon +
                    "\nPlayer two: " + p2GamesWon + "\n---------");
            System.out.print("Play again? (y/n): ");
            if (!input.next().toLowerCase().startsWith("y")) {
                System.out.println("Bye");
                break;
            }
        }
        input.close();
    }
}