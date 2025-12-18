import java.util.Arrays;

/**
 * This program takes an array of student exam scores and completes multiple subtasks
 * such as finding students with a given score, finding the highest and lowest scores, and
 * counting students with a given letter grade.
 * 
 * @author Aaron Ferguson
 * @author Benjamin Uy
 */
public class IntArrayAlgorithms {

    /**
     * accepts an array of integers and an integer value as its parameters and
     * returns the first index at which the value occurs in the array
     * 
     * @param arr array of integers to examine
     * @param val integer value to find in arr
     * @return the first (smallest) index of arr that contains val, or -1 if val is
     *         not in arr
     */
    public static int indexOf(int[] arr, int val) {
        int k;
        for (k = 0; k <= (arr.length - 1); k++) {
            if (arr[k] == val) {
                return k;
            } else if (k == (arr.length - 1) && (arr[k] != val) ) {
                return -1;
            }
        } return -1;
    }

    /**
     * accepts an array of integers and an integer value as its parameters and
     * returns the last index at which the value occurs in the array
     * 
     * @param arr array of integers to examine
     * @param val integer value to find in arr
     * @return the last (highest) index of arr that contains val, or -1 if val is
     *         not in arr
     */
    public static int lastIndexOf(int[] arr, int val) {
        int k;
        for (k = arr.length - 1; k >= 0; k--) {
            if (arr[k] == val) {
                return k;
            } else if (k == 0 && (arr[k] != val)) {
                return -1;
            }
        } return k;
    }

    /**
     * accepts an array of integers and an integer value as its parameters and
     * returns comma-separated list of indexes at which the value occurs in the
     * array
     * 
     * @param arr array of integers to examine
     * @param val integer value to find in arr
     * @return comma-separated list of indexes that contains val, or empty string
     *         ("") if val is not in arr
     */
    public static String listOfIndexWithValue(int[] arr, int val) {
        String listOfIndexes = "";
        int k;
        int j;
        int last = 0;
        if (arr.length == 0){
            return listOfIndexes;
        }
        for (k = 0; k <= arr.length - 1; k++) {
            if (arr[k] == val)
                last = k;
        }
        for (j = 0; j <= last; j++) {
            if (arr[j] == val && j < last) {
                listOfIndexes = listOfIndexes + j + ", "; 
            } else if (arr[j] == val && j == (last)) {
                listOfIndexes = listOfIndexes + j;
            } else if (Arrays.binarySearch(arr, val) <= 0) {
                listOfIndexes = "";
            }
        } return listOfIndexes;
    }

    /**
     * Returns the range of values in an array of integers. The range is defined as
     * 1 more than the difference between the maximum and minimum values in the
     * array.
     * 
     * Precondition: array has at least one element
     * 
     * @param arr array to examine
     * @return range of values in arr
     * @throws IllegalArgumentException with message "Empty array." if size of arr
     *             is 0
     */
    public static int range(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Empty array.");
        }

        if (arr.length == 1) {
            return 1;
        }

        int minimum = 0; 
        for (int k = 0; k <= (arr.length - 1); k++) {
            if (k == 0) {
                minimum = arr[k]; 
            } else if (k <= (arr.length - 1)) {
                minimum = Math.min(minimum, arr[k]);
            }
        }

        int maximum = 0;
        for (int k = 0; k <= (arr.length - 1); k++) {
            if (k == 0) {
                maximum = arr[k]; 
            } else if (k <= (arr.length - 1)) {
                maximum = Math.max(maximum, arr[k]);
            }
        }

        return (1 + maximum - minimum);
    }

    /**
     * accepts an array of integers, a minimum value, and a maximum value as
     * parameters and returns the count of how many elements from the array fall
     * between the minimum and maximum (inclusive)
     * 
     * @param arr array to examine
     * @param min minimum value to find in arr
     * @param max maximum value to find in arr
     * @return count of elements from arr that are between min and max (inclusive)
     * @throws IllegalArgumentException with message "Invalid range." if max is less
     *             than min
     */
    public static int countInRange(int[] arr, int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Invalid range");
        }

        int valsRange = 0;

        for (int k = 0; k <= (arr.length - 1); k++) {
            if (min <= arr[k] && arr[k] <= max) {
                valsRange++;
            }
        } return valsRange;
    }
}