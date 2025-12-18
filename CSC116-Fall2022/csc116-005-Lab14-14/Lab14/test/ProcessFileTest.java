import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Tests ProcessFile
 * 
 * @author Jessica Young Schmidt
 */
public class ProcessFileTest {

    /** Delta for comparing doubles */
    public static final double DELTA = 0.0001;

    /**
     * Tests a file with no integers
     * 
     * @throws FileNotFoundException if file is not found
     */
    @Test
    public void testProcessNoIntegers() throws FileNotFoundException {
        int[] expected = { 0, 0, 0, 0 };
        String filename = "test-files/csc116.txt";
        Scanner input = new Scanner(new FileInputStream(filename));
        int[] actual = ProcessFile.process(input);
        input.close();
        assertEquals(4, actual.length, "Tests length of actual");
        assertArrayEquals(expected, actual, "Tests that array values are correct.");
    }

    /**
     * Tests a file with one integer
     * 
     * @throws FileNotFoundException if file is not found
     */
    @Test
    public void testProcessOneInteger() throws FileNotFoundException {
        int[] expected = { 1, 116, 116, 116 };
        String filename = "test-files/csc116-title.txt";
        Scanner input = new Scanner(new FileInputStream(filename));
        int[] actual = ProcessFile.process(input);
        input.close();
        assertEquals(4, actual.length, "Tests length of actual");
        assertArrayEquals(expected, actual, "Tests that array values are correct.");
    }

    // TODO: Add test for process where input has multiple integers

    /**
     * Tests with zero integers
     */
    @Test
    public void testStatsToStringForNoIntegers() {
        int[] stats = { 0, 0, 0, 0 };
        String expected = "No integers.";
        String actual = ProcessFile.statsToString(stats);
        assertEquals(expected, actual, "Tests that string version is correct");
    }

    /**
     * Tests with one integer
     */
    @Test
    public void testStatsToStringForOneInteger() {
        int[] stats = { 1, 116, 116, 116 };
        String expected = "Maximum = " + 116 + "\nMinimum = " + 116 + "\nSum = " + 116
                + "\nCount = " + 1 + "\nAverage = " + 116.0;
        String actual = ProcessFile.statsToString(stats);
        assertEquals(expected, actual, "Tests that string version is correct");
    }

    /**
     * (BU) Tests statsToString with multiple integers
     */
    @Test
    public void testStatsToStringForMultipleIntegers() {
        int[] stats = {3, 115, 117, 348};
        String expected = "Maximum = " + 117 + "\nMinimum = " + 115 + "\nSum = " + 348
            + "\nCount = " + 3 + "\nAverage = " + 116.0;
        String actual = ProcessFile.statsToString(stats);
        assertEquals(expected, actual, "Tests that string version is correct");
    }

    /**
     * Tests a file with no integers
     * 
     * @throws FileNotFoundException if file is not found
     */
    @Test
    public void testProcessFileNoIntegers() throws FileNotFoundException {
        String expected = "No integers.";
        String filename = "test-files/csc116.txt";
        String actual = ProcessFile.processFile(filename);
        assertEquals(expected, actual, "Correct string returned?");
    }

    /**
     * Tests a file with one integer
     * 
     * @throws FileNotFoundException
     */
    @Test
    public void testProcessFileOneInteger() throws FileNotFoundException {
        String expected = "Maximum = " + 116 + "\nMinimum = " + 116 + "\nSum = " + 116
                + "\nCount = " + 1 + "\nAverage = " + 116.0;
        String filename = "test-files/csc116-title.txt";
        String actual = ProcessFile.processFile(filename);
        assertEquals(expected, actual, "Correct string returned?");
    }
    
    // TODO Add test to process file with multiple integers
}
