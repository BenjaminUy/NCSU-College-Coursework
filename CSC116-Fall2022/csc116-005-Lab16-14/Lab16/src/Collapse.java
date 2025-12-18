import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Program that processes input files to collapse and replace all whitespace
 *
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class Collapse {

    /**
     * Prompts user for input filename (and reprompts as needed until file is
     * found). Prompts user for output filename. If output file does not already
     * exist, collapse spaces for input file and write into output file. If output
     * file already exists, print error message to console
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Scanner for console to read file name
        Scanner console = new Scanner(System.in);

        // File scanner created after prompting user for input file
        Scanner in = getInput(console);

        // Printwriter created after prompting user for output file
        PrintWriter out = getOutput(console);
        if (out == null) {
            System.out.println("Output file already exists!");
            System.exit(1);
        }

        // Calling method that will output collapsed input file into output file
        collapseSpaces(in, out);

        // Close file scanner for input file
        in.close(); 

        // Close PrintWriter
        out.close();
    }

    /**
     * Prompts the user for an input filename, then creates and returns a Scanner
     * tied to the file. Reprompts as needed if file does not exist.
     * 
     * @param console console input scanner
     * @return scanner for input file
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

    /**
     * Prompts the user for an output filename, then creates and returns a
     * PrintStream tied to the file. If file with filename already exists, returns
     * null. If construction of PrintWriter and FileOutputStream results 
     * in FileNotFoundException, return null.
     * 
     * @param console console input scanner
     * @return PrintWriter for output file, or null if file exists
     */
    public static PrintWriter getOutput(Scanner console) {
        System.out.print("output file name? ");
        String filename = console.next();

        Path path = Path.of(filename);
        if (Files.exists(path)) {
            return null;
        }

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            return null;
        }
        PrintWriter output = new PrintWriter(outputStream);
        return output;
    }

    /**
     * Outputs collapsed input file into output file
     * 
     * @param in Scanner for input file
     * @param out PrintWriter for output file
     */
    public static void collapseSpaces(Scanner in, PrintWriter out) {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String collapsedLine = collapseLine(line);
            out.println(collapsedLine);
        }
    }

    /**
     * Returns string with spaces collapsed
     * 
     * @param line input string to collapse spaces
     * @return string with spaces collapsed
     */
    public static String collapseLine(String line) {
        String newString = "";

        if (line.length() == 0) {
            return newString;
        }

        // Loop will keep going until the first non-space character is reached
        int beforeFirstNonSpace = 0; 
        while (line.charAt(beforeFirstNonSpace) == ' ' ||
            line.charAt(beforeFirstNonSpace) == '\t') {
            beforeFirstNonSpace++;
        }

        boolean whiteSpaceIncluded = false;

        // Loop continues off from beforeFirstNonSpace--now collapsing the spaces
        for (int i = beforeFirstNonSpace; i < line.length(); i++) {
            if (line.charAt(i) != ' ' && line.charAt(i) != '\t') {
                newString += line.charAt(i);
                whiteSpaceIncluded = false;
            } else if (whiteSpaceIncluded == false && (line.charAt(i) == ' ' ||
                line.charAt(i) == '\t')) {
                newString += " ";
                // Once whitespace is added to collapsed line, stops adding more whitespace
                whiteSpaceIncluded = true;
            }
        }

        int fromEndOfString = newString.length() - 1;
        while (newString.charAt(fromEndOfString) == ' ') {
            fromEndOfString--;
        }

        newString = newString.substring(0, fromEndOfString + 1);

        return newString;
    
    }

}