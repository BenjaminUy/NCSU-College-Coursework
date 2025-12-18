import java.util.*;
import java.io.*;

/**
 * Program that, when run, will prompt the user to enter a weather file
 * which it will then process and output in the format showing month, day, 
 * year, low, high, and if there was rain or snow.
 * 
 * @author Benjamin Uy
 * @author Alex Calisto
 */
public class WeatherReporter {

    /**
     * Constant representing the number of tokens in a line within the
     * weather text files
     */
    public static final int NUM_OF_TOKENS_IN_LINE = 5;

    /**
     * Constant representing the index value separating year and month
     * in the date string
     */
    public static final int INDEX_BETWEEN_YEAR_AND_MONTH = 4;

    /**
     * Constant representing the index value separating month and day
     * in the date string
     */
    public static final int INDEX_BETWEEN_MONTH_AND_DAY = 6;

    /**
     * Starts program
     * 
     * @param args command arguments
     */
    public static void main(String[] args) {
        // Set up scanner for console to read filename
        Scanner console = new Scanner(System.in);

        // Get file scanner (getInput method)
        Scanner in = getInput(console);

        // Process file and print output to console
        System.out.print(processFile(in));
    }

    /**
     * Processes file (from Scanner) and returns string of processed file with 
     * correct output format
     * 
     * @param in input Scanner for file
     * @return string of processed file with correct output format
     */
    public static String processFile(Scanner in) {
        String output = "";

        /* Skip first line that shows format of info */
        String line = in.nextLine();

        while (in.hasNextLine()) {
            line = in.nextLine();
            String processedLine = processLine(line) + "\n";
            output = output + processedLine;
        }
        in.close();

        return output;
    }
            
    /**
     * Processes a line of the text file (String) in order to return string with the
     * correct output format
     * 
     * Reminder: tokens will be separated by commas
     * 
     * @param line the line to be processed
     * @return processed line
     */
    public static String processLine(String line) {
        String date = "";
        String year = "";
        String month = "";
        String day = "";

        double average = 0;
        double high = 0;
        double low = 0;

        String weather = "";
        int rainNum = 0;
        int snowNum = 0;
        String rain = "";
        String snow = "";
    
        Scanner lineScan = new Scanner(line);
        lineScan.useDelimiter(",");

        date = lineScan.next();
        year = date.substring(0, INDEX_BETWEEN_YEAR_AND_MONTH);
        month = date.substring(INDEX_BETWEEN_YEAR_AND_MONTH, 
            INDEX_BETWEEN_MONTH_AND_DAY);
        day = date.substring(INDEX_BETWEEN_MONTH_AND_DAY);

        average = lineScan.nextDouble();

        high = lineScan.nextDouble();

        low = lineScan.nextDouble();

        weather = lineScan.next();
        rainNum = Integer.parseInt(weather.substring(1, 2));
        snowNum = Integer.parseInt(weather.substring(2, 3));

        lineScan.close();

        if (rainNum == 1) {
            rain = "yes";
        } else {
            rain = "no";
        }

        if (snowNum == 1) {
            snow = "yes";
        } else {
            snow = "no";
        }
        
        return String.format("%2s/%2s/%4s Low:%6.1f High:%6.1f Rain:%4s Snow:%4s", 
            month, day, year, low, high, rain, snow);
    }

    /**
     * Prompt the user for a legal file name, create and return a Scanner tied to the file
     * Reprompt until Scanner is created.
     * 
     * @param console console for user input
     * @return Scanner tied to the input file
     */
    public static Scanner getInput(Scanner console) {
        Scanner scan = null;
        while (scan == null) {
            System.out.print("input file name? ");
            String filename = console.next();
            try {
                FileInputStream input = new FileInputStream(filename);
                scan = new Scanner(input); 
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }
        return scan;
    }
}
