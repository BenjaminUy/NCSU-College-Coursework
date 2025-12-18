import java.util.Scanner;

/** 
 * This program determines if every digit in an integer is odd
 * 
 * @author Bryson Brading
 * @author Benjamin Uy
 * @author Eric Chin
 */
public class AllDigitsOdd {

    /** 
     * Starts the program that determines if every digit in an integer is odd
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        // Prompts user for integer
        System.out.print("Enter integer: ");

        // Prompts user for integer and continues to prompt until integer is given
        while (!user.hasNextInt()) {
            user.next();
            System.out.print("Please enter an integer: ");
        }

        // Reads in next input as a valid integer
        int val = user.nextInt();

        // Prints "true" if all digits in integer are odd and "false" if any digits are even 
        System.out.println(val + ": " + areAllDigitsOdd(val));
    }
     
    /**
     * Method returns "true" if integer only consists of odd digits (1,3,5,7,9), otherwise 
     * (0,2,4,6,8) returns "false"
     * @param val validated integer input
     * @return true if integer only consists of odd digits (1,3,5,7,9)
     */
    public static boolean areAllDigitsOdd(int val) {
        boolean result = false;
        val = Math.abs(val);
        while (val > 0) {
            if (val < 10) {
                if (val == 1) {
                    result = true;
                    val = 0;
                } else {
                    val = val % 2;
                    if (val == 0) {
                        val = 0;
                    } else {
                        result = true;
                        val = 0;
                    }  
                }
            } else {
                int lastDigit = lastDigit(val);
                int lastDigitValue = lastDigit % 2;
                if (lastDigitValue == 0) {
                    val = 0;
                } else {
                    val = withoutLastDigit(val);
                }
            }
        }
        return (result);
    }

    /**
     * Method returns the last digit of the integer
     * @param val validated integer input
     * @return last digit of the integer
     */
    public static int lastDigit(int val) {
        return (Math.abs(val % 10));
    }

    /** 
     * Method returns integer without its last digit
     * @param val validated integer input
     * @return integer without the last digit
     */
    public static int withoutLastDigit(int val) {
        return (Math.abs(val / 10));
    }
}