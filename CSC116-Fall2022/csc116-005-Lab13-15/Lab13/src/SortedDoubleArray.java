import java.util.Arrays;
import java.util.Scanner;

/**
 * Class prototypes two different approaches to sorted arrays
 * 
 * @author Jessica Young Schmidt
 */
public class SortedDoubleArray {

    /**
     * Starts the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numValues;
        do {
            System.out.print("Enter number of values to store: ");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.print("Enter number (integer) of values to store: ");
            } 
            numValues = scan.nextInt();
        } while (numValues < 0);
        // Array with zero elements that we will add to with addElement method so that
        // it is always sorted
        double[] arrBuild = getNewArray(0);
        // Array with numValues elements that we will add then sort
        double[] arr = getNewArray(numValues);
        System.out.println("***Initial:***");
        System.out.println("Array that adds elements in sorted order:                "
                + Arrays.toString(arrBuild));
        System.out.println("Array that adds elements in the input order, then sorts: "
                + Arrays.toString(arr));
        for (int i = 0; i < numValues; i++) {
            System.out.print("Enter double: ");
            while (!scan.hasNextDouble()) {
                scan.next();
                System.out.print("ERROR - Enter double: ");
            }
            double val = scan.nextDouble();
            arrBuild = addElement(arrBuild, val);
            arr[i] = val;
        }
        System.out.println("***After all values added:***");
        System.out.println("Array that adds elements in sorted order:                "
                + Arrays.toString(arrBuild));
        System.out.println("Array that adds elements in the input order, then sorts: "
                + Arrays.toString(arr));

        selectionSort(arr);

        System.out.println("***After sorting second array:***");
        System.out.println("Array that adds elements in sorted order:                "
                + Arrays.toString(arrBuild));
        System.out.println("Array that adds elements in the input order, then sorts: "
                + Arrays.toString(arr));
        scan.close();
    }

    /**
     * Returns double array with numElements elements
     * 
     * @param numElements number of elements in array
     * @return double array with numElements elements
     * @throws IllegalArgumentException (with message "Invalid number of elements")
     *             if negative number of elements
     */
    public static double[] getNewArray(int numElements) {
        if (numElements < 0) {
            throw new IllegalArgumentException("Invalid number of elements");
        }
        return new double[numElements];
    }

    /**
     * Returns whether arr is sorted in increasing order (duplicates allowed)
     * 
     * @param arr array to examine
     * @return whether arr is sorted in increasing order
     * @throws IllegalArgumentException (with message "Null array") if null array
     */
    public static boolean isSorted(double[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Null array");
        }

        if (arr.length == 0) {
            return true;
        }

        /* Variable that will increase if arr[i + 1] is less than arr[i] */
        int outOfOrder = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                outOfOrder++;
            }
        }
    
        if (outOfOrder == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns index of element (first instance) in SORTED array if element is
     * contacted in array; otherwise, (-(insertion point) - 1). The insertion point
     * is defined as the point at which the key would be inserted into the array:
     * the index of the first element greater than the key, or a.length if all
     * elements in the array are less than the specified element. Note that this
     * guarantees that the return value will be >= 0 if and only if the element is
     * found.
     * 
     * @param arr SORTED array of doubles
     * @param element element to find in arr
     * @return index of element in SORTED array if element is contacted in array;
     *         otherwise, (-(insertion point) - 1). The insertion point is defined
     *         as the point at which the key would be inserted into the array: the
     *         index of the first element greater than the key, or a.length if all
     *         elements in the array are less than the specified element. Note that
     *         this guarantees that the return value will be >= 0 if and only if the
     *         element is found.
     * @throws IllegalArgumentException (with message "Null array") if null array
     * @throws IllegalArgumentException (with message "Unsorted Array") if array is
     *             unsorted
     */
    public static int sequentialSearch(double[] arr, double element) {
        if (arr == null) {
            throw new IllegalArgumentException("Null Array");
        }
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Unsorted Array");
        }

        int insertionPoint = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                insertionPoint = i;
                return insertionPoint;
            } else if (arr[i] > element) {
                insertionPoint = i;
                return (-insertionPoint - 1);
            }
        }

        insertionPoint = (-arr.length - 1);
        return insertionPoint;

    }

    /**
     * Returns new sorted array with the added element
     * 
     * @param arr SORTED array of doubles
     * @param element element to add to array
     * @return sorted array with added element
     * @throws IllegalArgumentException (with message "Null array") if null array
     * @throws IllegalArgumentException (with message "Unsorted Array") if array is
     *             unsorted
     */
    public static double[] addElement(double[] arr, double element) {
        if (arr == null) {
            throw new IllegalArgumentException("Null Array");
        }
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Unsorted Array");
        }

        // create array that has one more element
        double[] newArray = new double[arr.length + 1];
        int indexToAdd = sequentialSearch(arr, element);
        // if element is not in arr, indexToAdd is currently negative. Convert to
        // positive index where element should be added
        if (indexToAdd < 0) {
            indexToAdd = (indexToAdd + 1) * -1;
        }
        newArray[indexToAdd] = element; //ISSUE HERE!

        // copy arr elements to correct location in newArray
        for (int i = 0; i < arr.length; i++) {
            if (i < indexToAdd) {
                newArray[i] = arr[i];
            } else {
                newArray[i + 1] = arr[i];
            }
        }
        return newArray;
    }

    /**
     * Returns the (smallest) index of the minimum value in arr between indexA and
     * indexB (inclusive)
     * 
     * @param arr array to examine
     * @param indexA smallest index to examine
     * @param indexB largest index to examine
     * @return index of the minimum value in arr between indexA and indexB
     *         (inclusive)
     * @throws IllegalArgumentException (with message "Invalid parameter") if any of
     *             the following are true: null array, array with no elements,
     *             invalid index for arr, indexB is smaller than indexA
     */
    public static int indexOfMinValue(double[] arr, int indexA, int indexB) {
        if (arr == null || arr.length < 1 || indexA < 0 || indexB < 0 || indexA >= arr.length
                || indexB > arr.length || indexB < indexA) {
            throw new IllegalArgumentException("Invalid parameter");
        }
    
        int indexOfMinValue = indexB;
        for (int i = indexB - 1; i >= indexA; i--) {
            if (arr[i] <= arr[indexOfMinValue]) {
                indexOfMinValue = i;
            }
        }

        return indexOfMinValue;
    }

    /**
     * Swaps elements at indexA and indexB in arr
     * 
     * @param arr array in which elements will swap
     * @param indexA first index to swap
     * @param indexB second index to swap
     * @throws IllegalArgumentException (with message "Invalid parameter") if any of
     *             the following are true: null array, array with no elements,
     *             invalid index for arr
     */
    public static void swap(double[] arr, int indexA, int indexB) {
        if (arr == null || arr.length < 1 || indexA < 0 || indexA >= arr.length
                || indexB >= arr.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        double temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    /**
     * Return sorted by using selection sort algorithm
     * 
     * @param arr array to sort
     * @throws IllegalArgumentException (with message "Null array") if null array
     */
    public static void selectionSort(double[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Null array");
        }
        for (int i = 0; i < arr.length; i++) {
            int indexOfMinValue = indexOfMinValue(arr, i, arr.length - 1);
            if (indexOfMinValue != i) {
                swap(arr, indexOfMinValue, i);
            }
        }
    }
}
