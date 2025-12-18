import java.util.Arrays;
import java.util.Scanner;

/**
 * This program counts how many vowels (case-sensitive) are in a String given by user and swaps 
 * characters in adjacent indexes in an array of vowels.  
 * 
 * @author Benjamin Uy
 * @author Aaron Ferguson
 */
public class CharacterArray {

    /**
     * Starts the program that counts how many vowels (case-sensitive) are in a String given by
     * the user and swaps characters in adjacent indexes in an array of vowels.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] vowels = { 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' };
        System.out.print("Enter string: ");
        String inputString = in.nextLine();
        int[] counts = countCharacters(vowels, inputString);
        for (int i = 0; i < vowels.length; i++) {
            System.out.println(vowels[i] + ": " + counts[i]);
        }

        System.out.println("Before swap: " + Arrays.toString(vowels));
        swapPairs(vowels);
        System.out.println("After swap:  " + Arrays.toString(vowels));
    }

    /**
     * Accepts an array of characters and swaps the elements at adjacent
     * indexes. That is, elements 0 and 1 are swapped, elements 2 and 3 are swapped,
     * and so on. If the array has an odd length, the final element should be left
     * unmodified.
     * 
     * @param arr array of characters
     */
    public static void swapPairs(char[] arr) {
        for (int i = 0; i <= (arr.length - 2); i += 2) {
            char temp = arr[i + 1];
            arr[i + 1] = arr[i];
            arr [i] = temp;
        }
    }

    /**
     * Return a new array with the counts for each character array element in the
     * string. The task should be case-sensitive.
     * 
     * @param characters array of characters to find in str
     * @param str string to examine
     * @return counts of each character in string
     */
    public static int[] countCharacters(char[] characters, String str) {
        // construct counts array that is the same length as characters
        int[] arrayCounts = new int[characters.length];
        // traverse string to examine each character of string
        int i = 0;
        int j = 0;
        for (j = 0; j <= (str.length() - 1); j++) {
            for (i = 0; i <= (characters.length - 1); i++) {
                if (str.charAt(j) == characters[i] ) {
                    arrayCounts[i] += 1;
                }
            }
        }
        return arrayCounts;
    }

}
