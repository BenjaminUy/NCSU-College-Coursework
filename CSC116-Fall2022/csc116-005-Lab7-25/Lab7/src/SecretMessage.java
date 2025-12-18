import java.util.Scanner;

/**
 * Program encrypts and decrypts messages
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Bryson Brading
 * @author Eric Chin
 */
public class SecretMessage {
    /**
     * Starts the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        userInterface();
    }

    /**
     * Completes user interactions by prompting for message and whether to encrypt
     * or decrypt message
     */
    public static void userInterface() {
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to (E)ncrypt or (D)ecrypt message? ");
        char option = in.nextLine().toUpperCase().charAt(0);

        System.out.print("Enter message: ");
        String message = in.next();

        System.out.println();
        switch (option) {
            case 'E':
                System.out.println(encrypt(message));
                break;
            case 'D':
                System.out.println(decrypt(message));
                break;
            default:
                System.out.println("Invalid option.");
        }

    }

    /**
     * Returns encrypted message based on encrypt algorithm
     * 
     * @param message message to be encrypted
     * @return encrypted message
     */
    public static String encrypt(String message) {
        if (message.length() <= 3) {
            return message;
        }
        message = swapCharacter(message);
        message = moveCharacter(message);
        message = swapSubstrings(message);
        return message;
    }

    /**
     * Returns decrypted message based on decrypt algorithm
     * 
     * @param message message to be decrypted
     * @return decrypted message
     */
    public static String decrypt(String message) {
        if (message.length() <= 3) {
            return message;
        }

        message = swapSubstrings(message);
        message = moveCharacter(message);
        message = swapCharacter(message);
        return message;
    }

    /**
     * Returns results of *Swap characters* step of encrypt and decrypt algorithms.
     * Works for messages of any length.
     * 
     * @param message message to encrypt or decrypt
     * @return message that results from *Swap characters* step of encrypt and
     *         decrypt algorithms
     */
    public static String swapCharacter(String message) {
        int length = message.length();
        if (length <= 2) {
            return message;
        }
        char first = message.charAt(0);
        char last = message.charAt(length - 1);
        if (Character.isLetter(first) && Character.isLetter(last)) {
            return "" + last + message.substring(1, length - 1) + first;
        } else if (Character.isDigit(first)) {
            if (length == 2) {
                return "" + last + first;
            } else if (length == 3) {
                return message;
            } 
            int indexOfSecond = 1;
            int indexOfNextToLast = length - 2;
            return "" + first + message.charAt(indexOfNextToLast)
                    + message.substring(indexOfSecond, indexOfNextToLast)
                    + last;
        }
        return message;
    }

    /**
     * Returns results of *Move character* step of encrypt and decrypt algorithms.
     * Works for messages of length two or greater.
     * 
     * @param message message to encrypt or decrypt
     * @return message that results from *Move character* step of encrypt and
     *         decrypt algorithms
     * @throws IllegalArgumentException if length of 0 or 1
     */
    public static String moveCharacter(String message) {
        int length = message.length();
        if (length < 2) {
            throw new IllegalArgumentException("Invalid string");
        }
        return message.substring(0, length - 2) + message.charAt(length - 1)
                + message.charAt(length - 2);
    }

    /**
     * Returns results of *Swap substrings* step of encrypt and decrypt algorithms.
     * Works for messages of any length.
     * 
     * @param message message to encrypt or decrypt
     * @return message that results from *Swap substrings* step of encrypt and
     *         decrypt algorithms
     */
    public static String swapSubstrings(String message) {
        int length = message.length();
        if (length < 2) {
            return message;
        }
        if (length % 2 == 0) {
            int mid = length / 2;
            return message.substring(mid) + message.substring(0, mid);
        } else if (length % 3 == 0) {
            int third = length / 3;
            return message.substring(2 * third) + message.substring(third, 2 * third)
                    + message.substring(0, third);
        } else {
            int mid = length / 2;
            return message.substring(mid + 1, length) + message.charAt(mid) + 
                message.substring(0, mid);
        }
    }
}
