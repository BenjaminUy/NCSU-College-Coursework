import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Testing WeatherReporter class
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Alex Calisto
 */
public class WeatherReporterTest {

    /**
     * Testing day with no snow, no rain, temperature over 100
     */
    @Test
    public void testProcessLine0627() {
        String input = "20100627,85.4,100.9,73,000000";
        String description = "Temperature over 100";
        String expected = "06/27/2010 Low:  73.0 High: 100.9 Rain:  no Snow:  no";
        String actual = WeatherReporter.processLine(input);
        assertEquals(expected, actual, description);
    }

    /**
     * (BU) Testing day with only snow, low temperature less than 0 and high temp 0.0
     */
    @Test
    public void testProcessLineSnowAndNegTemperatures() {
        String input = "20070822,-55.2,0.0,-128.6,001000";
        String description = "Temperature less than or equal to 0.0 and snowy day";
        String expected = "08/22/2007 Low:-128.6 High:   0.0 Rain:  no Snow: yes";
        String actual = WeatherReporter.processLine(input);
        assertEquals(expected, actual, description);
    }

    /**
     * (BU) Testing day with only rain, low temperature 0.0
     */
    @Test
    public void testProcessLineRainAndZeroForLowTemperature() {
        String input = "20040215,25.0,42.6,0.0,010000";
        String description = "0.0 for low temperature and rainy day";
        String expected = "02/15/2004 Low:   0.0 High:  42.6 Rain: yes Snow:  no";
        String actual = WeatherReporter.processLine(input);
        assertEquals(expected,actual, description);
    }

    /**
     * Testing processFile for single day. Scanner from string.
     */
    @Test
    public void testProcessFileSingleDayFromString() {
        String input = "YYYYMMDD,Average(F),High(F),Low(F),Fog/Rain/Snow/Hail/Thunder/Tornado\n"
                + "20100627,85.4,100.9,73,000000";
        String description = "Temperature over 100";
        String expected = "06/27/2010 Low:  73.0 High: 100.9 Rain:  no Snow:  no\n";
        String actual = WeatherReporter.processFile(new Scanner(input));
        assertEquals(expected, actual, description);
    }

    /**
     * Testing processFile for two days. Scanner from file.
     */
    @Test
    public void testProcessFileTwoDaysFromFile() throws FileNotFoundException {
        String inputFile = "test-files/RaleighWeather-2010-two-days.txt";
        String description = "two days from file";
        String expected = "06/26/2010 Low:  73.0 High:  97.0 Rain:  no Snow:  no\n"
                + "06/27/2010 Low:  73.0 High: 100.9 Rain:  no Snow:  no\n";
        String actual = WeatherReporter.processFile(new Scanner(new FileInputStream(inputFile)));
        assertEquals(expected, actual, description);
    }

    /**
     * Tests getInput with valid filename
     */
    @Test
    public void testGetInput1() {
        String message = "valid filename";
        String expectedContents = "CSC116: \nIntroduction to Computing - Java";
        Scanner actual = WeatherReporter.getInput(new Scanner("test-files/csc116Title.txt"));
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
        Scanner actual = WeatherReporter
                .getInput(new Scanner("test-files/doesNotExist.txt\ntest-files/csc116Title.txt"));
        assertNotNull(actual, "Check that Scanner is not null");
        Scanner expected = new Scanner(expectedContents);
        testFileContents(expected, actual, message);
        expected.close();
        actual.close();
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
