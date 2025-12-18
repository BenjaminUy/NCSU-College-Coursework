/**
 * This program uses two methods to determine if (1) a one-dimensional array of strings 
 * is a palindrome and (2) whether two two-dimensional arrays of strings are equal
 * (containing the same elements in the same locations)
 * 
 * @author Alex Calisto
 * @author Benjamin Uy
 */
public class StringArrays {

    /**
     * Accepts a one-dimensional array of strings and determines if it is a palindrome.
     * An array is considered a palindrome if the string elemtns are the same forwards
     * and backwards within the array.
     * 
     * @param arr one-dimensional array of strings 
     * @return whether arr is a palindrome
     * @throws IllegalArgumentException if arr is null (Message: Invalid array)
     * @throws IllegalArgumentException if any element is null (Message: Invalid element)
     */
    public static boolean isPalindrome(String[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Invalid array");
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    throw new IllegalArgumentException("Invalid element");
                }
            }
        }
        
        boolean palindrome = true;

        if (arr.length == 2) {
            String a = arr[0];
            String b = arr[1];
            if (b.compareTo(a) < 0) {
                palindrome = false;
            }
        } else if (arr.length > 2) {
            for (int k = 0; k < (arr.length / 2); k++) {
                String a = arr[k];
                String b = arr[arr.length - 1 - k];
                if (b.compareTo(a) < 0) {
                    palindrome = false;
                }
            }
        }
        return palindrome;
    }

    /** 
     * Accepts two two-dimensional arrays of strings and determines if the two arrays
     * are equal (having the same elements in the same order & location)
     * 
     * @param a two-dimensional array of strings
     * @param b another two-dimensional array of strings
     * @return whether the same elements are in the same order & location
     * @throws IllegalArgumentException if a or b are null (Message: Invalid array)
     */
    public static boolean equals2D(String[][] a, String[][] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Invalid array");
        }

        boolean equals = true;
        
        if (a.length != b.length || a[0].length != b[0].length) {
            equals = false;
        } else {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a[j].length; k++) {
                    String x = a[j][k];
                    String y = b[j][k];
                    if (x.compareTo(y) != 0) {
                        equals = false;
                    }
                }
            }
        }
        return equals;
    }
}