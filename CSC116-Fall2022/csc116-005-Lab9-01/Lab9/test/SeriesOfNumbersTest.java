import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Tests SeriesOfNumbers program
 * 
 * @author Jessica Young Schmidt
 */
public class SeriesOfNumbersTest {

    /**
     * Tests going through loop of getSeries once
     */
    @Test
    public void testGetSeries1() {
        assertEquals("1", SeriesOfNumbers.getSeries(1), "Test once through loop");
    }

    /**
     * Tests going through loop of getSeries twice
     */
    @Test
    public void testGetSeries2() {
        assertEquals("1 4", SeriesOfNumbers.getSeries(2), "Test twice through loop");
    }

    /**
     * Tests going through loop of getSeries ten times
     */
    @Test
    public void testGetSeries10() {
        assertEquals("1 4 9 16 25 36 49 64 81 100", SeriesOfNumbers.getSeries(10),
                "Test ten times through loop");
    }

    /**
     * Tests getSeries with invalid parameter (0)
     */
    @Test
    public void testGetSeries0() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SeriesOfNumbers.getSeries(0),
                "Test that exception is thrown with non-positive (0)");
        assertEquals("Non-positive integer (0)", exception.getMessage(),
                "Test exception message with non-positive (0)");
    }

    /**
     * Tests getSeries with invalid parameter (-11)
     */
    @Test
    public void testGetSeriesNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SeriesOfNumbers.getSeries(-11),
                "Test that exception is thrown with non-positive (-11)");
        assertEquals("Non-positive integer (-11)", exception.getMessage(),
                "Test exception message with non-positive (-11)");
    }

    /**
     * Tests getInt with first value integer
     */
    @Test
    public void testGetInteger1() {
        Scanner scan = new Scanner("1 never seen");
        assertEquals(1, SeriesOfNumbers.getInteger(scan), "First value is integer");
    }

    /**
     * Tests getInt with second value integer
     */
    @Test
    public void testGetInteger2() {
        Scanner scan = new Scanner("one 2 3");
        assertEquals(2, SeriesOfNumbers.getInteger(scan), "Second value is integer");
    }

    /**
     * Tests getInt with fourth value integer
     */
    @Test
    public void testGetInteger4() {
        Scanner scan = new Scanner("one two three 4 5");
        assertEquals(4, SeriesOfNumbers.getInteger(scan), "Fourth value is integer");
    }

    /**
     * Tests getInt with negative integer
     */
    @Test
    public void testGetIntegerNeg5() {
        Scanner scan = new Scanner("-5");
        assertEquals(-5, SeriesOfNumbers.getInteger(scan), "Negative integer");
    }

}
