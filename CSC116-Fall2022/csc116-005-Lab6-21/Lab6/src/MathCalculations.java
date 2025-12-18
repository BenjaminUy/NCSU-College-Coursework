import java.util.Scanner;

/**
 * The class MathCalculations first asks user for number (2, 3, or 4) of integers in
 * set; after prompting the user and recieving those values, it computes the minimum, 
 * maximum, sum, mean, and median of the set.
 * @author Benjamin Uy and Emma Gould
 */
public class MathCalculations {

    /**
     * Starts the program
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many values (2, 3, or 4) will you enter? ");
        int numValues = in.nextInt();

        /* Constants establish how many integer values are in a data set;
         * for example, a two-number set will have two integer values.
         * Used to determine if user input to "How many values (2, 3, or 4)
         * will you enter?" is invalid
         */
        final int twoNumberSet = 2;
        final int threeNumberSet = 3;
        final int fourNumberSet = 4;

        if (numValues == twoNumberSet) {
            calculations(in, numValues);
        } else if (numValues == threeNumberSet) {
            calculations(in, numValues);
        } else if (numValues == fourNumberSet) {
            calculations(in, numValues);
        } else {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Prints the minimum, maximum, sum, mean, and median of a data set
     * @param in input from Scanner
     * @param numValues number of values in data set
     * @throws IllegalArgumentException if user input is not 2, 3, or 4
     */
    public static void calculations(Scanner in, int numValues) {
        /* Constants establish how many integer values are in a data set;
         * for example, a two-number set will have two integer values.
         */
        final int twoNumberSet = 2;
        final int threeNumberSet = 3;
        final int fourNumberSet = 4;

        if (numValues == twoNumberSet) {
            System.out.print("Enter two integers: ");
            int val1 = in.nextInt();
            int val2 = in.nextInt();
            System.out.println("Minimum: " + min(val1, val2));
            System.out.println("Maximum: " + max(val1, val2));
            System.out.println("Sum: " + sum(val1, val2));
            System.out.println("Mean: " + mean(val1, val2));
            System.out.println("Median: " + median(val1, val2));
        } else if (numValues == threeNumberSet) {
            System.out.print("Enter three integers: ");
            int val1 = in.nextInt();
            int val2 = in.nextInt();
            int val3 = in.nextInt();
            System.out.println("Minimum: " + min(val1, val2, val3));
            System.out.println("Maximum: " + max(val1, val2, val3));
            System.out.println("Sum: " + sum(val1, val2, val3));
            System.out.println("Mean: " + mean(val1, val2, val3));
            System.out.println("Median: " + median(val1, val2, val3));
        } else if (numValues == fourNumberSet){
            System.out.print("Enter four integers: ");
            int val1 = in.nextInt();
            int val2 = in.nextInt();
            int val3 = in.nextInt();
            int val4 = in.nextInt();
            System.out.println("Minimum: " + min(val1, val2, val3, val4));
            System.out.println("Maximum: " + max(val1, val2, val3, val4));
            System.out.println("Sum: " + sum(val1, val2, val3, val4));
            System.out.println("Mean: " + mean(val1, val2, val3, val4));
            System.out.println("Median: " + median(val1, val2, val3, val4));
        } else if (numValues != twoNumberSet || numValues != threeNumberSet || 
            numValues != fourNumberSet) {
            throw new IllegalArgumentException("Invalid number of values: " + numValues);
        }
    }

    /**
     * Method returns minimum of two given integer values
     * @param a first integer value
     * @param b second integer value
     * @return minimum value of the two given values
     */
    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * Method returns minimum of three given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @return minimum value of the three given values
     */
    public static int min(int a, int b, int c) {
        return min(min(a, b), c);
    }

    /**
     * Method returns minimum of four given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @param d fourth integer value
     * @return minimum value of the four given values
     */
    public static int min(int a, int b, int c, int d) {
        return min(min(a, b, c), d);
    }

    /**
     * Method returns maximum of two given integer values
     * @param a first integer value
     * @param b second integer value
     * @return maximum value of the two given values
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Method returns maximum of three given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @return maximum value of the three given values
     */
    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    /**
     * Method returns maximum of four given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @param d fourth integer value
     * @return maximum value of the four given values
     */
    public static int max(int a, int b, int c, int d) {
        return max(max(a, b, c), d);
    }

    /**
     * Method returns sum of two given integer values
     * @param a first integer value
     * @param b second integer value
     * @return sum of the two given values
     */
    public static int sum(int a, int b) {
        return (a + b);
    }

    /**
     * Method returns sum of three given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @return sum of the three given values
     */
    public static int sum(int a, int b, int c) {
        return sum(sum(a, b), c);
    }

    /**
     * Method returns sum of four given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @param d fourth integer value
     * @return sum of the four given values
     */
    public static int sum(int a, int b, int c, int d) {
        return sum(sum(a, b, c), d);
    }

    /**
     * Method returns mean of two given integer values
     * @param a first integer value
     * @param b second integer value
     * @return mean of the two given values
     */
    public static double mean(int a, int b) {
        return  (double) sum(a, b) / 2;
    }

    /**
     * Method returns mean of three given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @return mean of the three values
     */
    public static double mean(int a, int b, int c) {
        return (double) sum(a, b, c) / 3;
    }

    /**
     * Method returns mean of four given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @param d fourth integer value
     * @return mean of the four values
     */
    public static double mean(int a, int b, int c, int d) {
        final int fourNumberSet = 4;
        double me = (double) (sum(a, b, c, d)) / fourNumberSet;
        return me;
    }

    /**
     * Method returns median of two given integer values
     * @param a first integer value
     * @param b second integer value
     * @return median of the two values
     */
    public static double median(int a, int b) {
        return (double) mean(a, b);
    }

    /**
     * Method returns median of three given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @return median of the three values
     */
    public static double median(int a, int b, int c) {
        int minOfThree = min(a, b, c);
        int maxOfThree = max (a, b, c);
        return sum(a, b, c) - minOfThree - maxOfThree;
    }

    /**
     * Method returns median of four given integer values
     * @param a first integer value
     * @param b second integer value
     * @param c third integer value
     * @param d fourth integer value
     * @return median of the four values
     */
    public static double median(int a, int b, int c, int d) {
        int minOfFour = min(a, b, c, d);
        int maxOfFour = max(a, b, c, d);
        double med = (double) (sum(a, b, c, d) - minOfFour - maxOfFour) / 2;
        return med;
    }

}
