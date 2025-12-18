import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Test Collapse class
 * 
 * @author Jessica Young Schmidt
 */
public class CollapseTest {

    /**
     * Test file with two lines and different spaces
     * 
     * @throws FileNotFoundException if file not found
     */
    @Test
    public void testCollapseSpaces() throws FileNotFoundException {
        String inputFile = "test-files/collapse1.txt";
        String expected = "test-files/collapse1_EXPECTED.txt";
        String actual = "test-files/collapse1_ACTUAL.txt";
        Scanner in = new Scanner(new FileInputStream(inputFile));
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Collapse.collapseSpaces(in, out);
        in.close();
        out.close();
        assertFilesEqual(expected, actual, "File with two lines and different spaces");
    }

    /**
     * Test file with single word and spaces
     * 
     * @throws FileNotFoundException if file not found
     */
    @Test
    public void testCollapseSpaces2() throws FileNotFoundException {
        String inputFile = "test-files/collapse2.txt";
        String expected = "test-files/collapse2_EXPECTED.txt";
        String actual = "test-files/collapse2_ACTUAL.txt";
        Scanner in = new Scanner(new FileInputStream(inputFile));
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Collapse.collapseSpaces(in, out);
        in.close();
        out.close();
        assertFilesEqual(expected, actual, "File with single word and spaces");
    }

    /**
     * Test file with single word and no spaces
     * 
     * @throws FileNotFoundException if file not found
     */
    @Test
    public void testCollapseSpaces3() throws FileNotFoundException {
        String inputFile = "test-files/collapse3.txt";
        String expected = "test-files/collapse3_EXPECTED.txt";
        String actual = "test-files/collapse3_ACTUAL.txt";
        Scanner in = new Scanner(new FileInputStream(inputFile));
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Collapse.collapseSpaces(in, out);
        in.close();
        out.close();
        assertFilesEqual(expected, actual, "File with single word and no spaces");
    }

    /**
     * Test file with java program
     * 
     * @throws FileNotFoundException if file not found
     */
    @Test
    public void testCollapseSpacesHW() throws FileNotFoundException {
        String inputFile = "test-files/HelloWorld.java";
        String expected = "test-files/HelloWorld.java_EXPECTED";
        String actual = "test-files/HelloWorld.java_ACTUAL";
        Scanner in = new Scanner(new FileInputStream(inputFile));
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Collapse.collapseSpaces(in, out);
        in.close();
        out.close();
        assertFilesEqual(expected, actual, "File with java program");
    }

    /**
     * Tests a line with no extra spaces
     */
    @Test
    public void testCollapseLineA() {
        String line = "CSC116 Java";
        String expected = "CSC116 Java";
        String actual = Collapse.collapseLine(line);
        assertEquals(expected, actual, "Line with no extra spaces");
    }

    /**
     * Tests line with extra spaces
     */
    @Test
    public void testCollapseLineB() {
        String line = "\tCSC116      Java     \t    ";
        String expected = "CSC116 Java";
        String actual = Collapse.collapseLine(line);
        assertEquals(expected, actual, "Line with extra spaces");
    }

    /**
     * Tests getInput with valid filename
     */
    @Test
    public void testGetInput1() {
        String message = "valid filename";
        String expectedContents = "CSC116: \nIntroduction to Computing - Java";
        Scanner actual = Collapse.getInput(new Scanner("test-files/csc116Title.txt"));
        assertNotNull(actual, "Check that Scanner is not null");
        Scanner expected = new Scanner(expectedContents);
        testFileContents(expected, actual, message);
        expected.close();
        actual.close();
    }

    /**
     * Tests getInput with valid filename after invalid filename
     */
    @Test
    public void testGetInput2() {
        String message = "valid filename after invalid filename";
        String expectedContents = "CSC116: \nIntroduction to Computing - Java";
        Scanner actual = Collapse
                .getInput(new Scanner("test-files/doesNotExist.txt\ntest-files/csc116Title.txt"));
        assertNotNull(actual, "Check that Scanner is not null");
        Scanner expected = new Scanner(expectedContents);
        testFileContents(expected, actual, message);
        expected.close();
        actual.close();
    }

    /**
     * Tests getOutput with filename that exist
     */
    @Test
    public void testGetOutput1() {
        PrintWriter actual = Collapse.getOutput(new Scanner("test-files/existingOutput.txt"));
        assertNull(actual, "Check that PrintWriter is null");
    }

    /**
     * Tests getOutput with filename that does not exist
     * 
     * @throws FileNotFoundException if file does not exist
     */
    @Test
    public void testGetOutput2() throws FileNotFoundException {
        // Output file
        String filename = "test-files/A_New_Output.txt";

        // Delete file if it already exists
        Path path = Path.of(filename);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }

        String message = "filename does not exists";
        String expectedContents = "CSC116: \nIntroduction to Computing - Java";
        PrintWriter actualPW = Collapse.getOutput(new Scanner(filename));
        assertNotNull(actualPW, "Check that PrintWriter is not null");
        actualPW.println(expectedContents);
        actualPW.close();
        Scanner expected = new Scanner(expectedContents);
        Scanner actual = new Scanner(new FileInputStream(filename));
        testFileContents(expected, actual, message);
        expected.close();
        actual.close();
    }

    /**
     * Tests getOutput with filename that is a directory
     */
    @Test
    public void testGetOutput3() {
        PrintWriter actual = Collapse.getOutput(new Scanner("test-files"));
        assertNull(actual, "Check that PrintWriter is null since filename is directory");
    }

    /**
     * Tests whether files contain the same contents
     * 
     * @param pathToExpected path to file with expected contents
     * @param pathToActual path to file with actual content
     * @param message message for test
     * @throws FileNotFoundException if Scanner cannot be constructed with file
     */
    private void assertFilesEqual(String pathToExpected, String pathToActual, String message)
            throws FileNotFoundException {
        try (Scanner expected = new Scanner(new FileInputStream(pathToExpected));
                Scanner actual = new Scanner(new FileInputStream(pathToActual));) {
            while (expected.hasNextLine()) {
                if (!actual.hasNextLine()) { // checks that actual has line as well
                    fail(message + ": Actual missing line(s)");
                } else { // both have another line
                    assertEquals(expected.nextLine(), actual.nextLine(),
                            message + ": Checking line equality");
                }
            }

            if (actual.hasNextLine()) {
                fail(message + ": Actual has extra line(s)");
            }
        }
    }

    /**
     * Testing contents of scanner
     * 
     * @param expected expected scanner
     * @param actual actual scanner
     * @param message message for test
     */
    public void testFileContents(Scanner expected, Scanner actual, String message) {
        int line = 0;
        while (expected.hasNextLine()) {
            line++;
            if (actual.hasNextLine()) {
                assertEquals(expected.nextLine(), actual.nextLine(),
                        message + ": Testing line " + line);
            } else {
                fail(message + ": Too few lines: line " + line);
            }
        }
        if (actual.hasNextLine()) {
            fail(message + ": Too many lines");
        }
    }
}
