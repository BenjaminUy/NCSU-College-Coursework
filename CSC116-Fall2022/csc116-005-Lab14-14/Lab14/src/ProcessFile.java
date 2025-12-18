import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program, when run will process a file based on commmand line arguments,
 * scan for integers in the file, and output the integer count, minimum, 
 * maximum, sum, and average.
 * 
 * @author Jessica Young Schmidt
 * @author Alex Calisto
 * @author Benjamin Uy
 */
public class ProcessFile {
    /** Index for count within stats array */
    public static final int INDEX_FOR_COUNT = 0;

    /** Index for minimum value within stats array */
    public static final int INDEX_FOR_MIN = 1;

    /** Index for maximum value within stats array */
    public static final int INDEX_FOR_MAX = 2;

    /** Index for sum within stats array */
    public static final int INDEX_FOR_SUM = 3;
    
    /**
     * Starts the program for ProcessFile
     *  
     * @param args string array used to get filename; if empty or longer than 1, ends program
     * @throws FileNotFoundException if file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java -cp bin ProcessFile input_filename");
            System.exit(1);
        } else {
            String filename = args[0];
            String output = processFile(filename);
            System.out.println(output);
        }
    }

    /**
     * Returns string version of stats for the given file.
     * 
     * @param filename name of the file
     * @return string with statistics of the file
     * @throws FileNotFoundException if file is not found
     */ 
    public static String processFile(String filename) throws FileNotFoundException {
        FileInputStream fileInput = new FileInputStream(filename);
        Scanner scan = new Scanner(fileInput);
        /* Creating stats array to store count, min, max, and sum */
        int[] stats = new int[INDEX_FOR_SUM + 1];
        stats = process(scan);
        String output = statsToString(stats);
        return output;
    }
    
    /**
     * Returns string version of stats. Given array of four elements: number of integers 
     * in scanner (count), minimum integer value in scanner, maximum integer value in scanner, 
     * and sum of all integer values in scanner.
     * 
     * @param stats array with the statistics for file
     * @return string with statistics of file
     */ 
    public static String statsToString(int[] stats) {
        if (stats[INDEX_FOR_COUNT] > 0) {
            double average = (double) stats[INDEX_FOR_SUM] / stats[INDEX_FOR_COUNT];
            return ("Maximum = " + stats[INDEX_FOR_MAX] + "\nMinimum = " + stats[INDEX_FOR_MIN]
                    + "\nSum = " + stats[INDEX_FOR_SUM] + "\nCount = " + stats[INDEX_FOR_COUNT]
                    + "\nAverage = " + average);
        } else {
            return "No integers.";
        }
    }

    /**
     * Process scanner only examining integers in it. Returns array of four
     * elements: number of integers in scanner, minimum integer value in scanner,
     * maximum integer value in scanner, and sum of all integer values in scanner.
     * If the scanner contains no integers, returns array {0, 0, 0, 0}.
     * 
     * @param scan scanner to scan txt file
     * @return int array statistics with count, min, max, and sum of sacnned ints
     * @throws FileNotFoundException if file is not found
     */ 
    public static int[] process(Scanner scan) throws FileNotFoundException {
        int count = 0;
        int minimum = 0;
        int maximum = 0;
        int sum = 0;
        int readNumber = 0;
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                count++;
                readNumber = scan.nextInt();
                if (count == 1) {
                    minimum = readNumber;
                    maximum = readNumber;
                } else {
                    minimum = Math.min(minimum, readNumber);
                    maximum = Math.max(maximum, readNumber);
                }
                sum += readNumber;
            }
        }
        scan.close();

        int[] statistics = new int[INDEX_FOR_SUM + 1];
        statistics[INDEX_FOR_COUNT] = count;
        statistics[INDEX_FOR_MIN] = minimum;
        statistics[INDEX_FOR_MAX] = maximum;
        statistics[INDEX_FOR_SUM] = sum;

        return statistics;
    }
}
