import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests IntArrayAlgorithms class
 * 
 * @author Jessica Young Schmidt
 */
public class IntArrayAlgorithmsTest {

    /**
     * Tests countInRange method with large range
     */
    @Test
    public void testCountInRangeA() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "countInRange 0-1000";
        int expected = 7;
        int actual = IntArrayAlgorithms.countInRange(arr, 0, 1000);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with small range
     */
    @Test
    public void testCountInRangeB() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "countInRange 101-102";
        int expected = 2;
        int actual = IntArrayAlgorithms.countInRange(arr, 101, 102);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with single value
     */
    @Test
    public void testCountInRangeC() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "countInRange 102";
        int expected = 1;
        int actual = IntArrayAlgorithms.countInRange(arr, 102, 102);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with no elements in range
     */
    @Test
    public void testCountInRangeD() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "countInRange 900-1000";
        int expected = 0;
        int actual = IntArrayAlgorithms.countInRange(arr, 900, 1000);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with negatives
     */
    @Test
    public void testCountInRangeE() {
        int[] arr = { 14, 1, 22, 17, 36, 7, -43, 5 };
        String desc = "countInRange 4-17";
        int expected = 4;
        int actual = IntArrayAlgorithms.countInRange(arr, 4, 17);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 14, 1, 22, 17, 36, 7, -43, 5 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with invalid range
     */
    @Test
    public void testCountInRangeF() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "countInRange 102-101";
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> IntArrayAlgorithms.countInRange(arr, 102, 101), desc);
        assertEquals("Invalid range", exception.getMessage(), desc + " Message");

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests countInRange method with empty array
     */
    @Test
    public void testCountInRangeG() {
        int[] arr = {};
        String desc = "countInRange 4-17";
        int expected = 0;
        int actual = IntArrayAlgorithms.countInRange(arr, 4, 17);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = {};
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests range method with invalid range
     */
    @Test
    public void testRangeA() {
        int[] arr = {};
        String desc = "range empty array";
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> IntArrayAlgorithms.range(arr), desc);
        assertEquals("Empty array.", exception.getMessage(), desc + " Message");

        // Test that method does not modify array
        int[] arrCopy = {};
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests range method with duplicates
     */
    @Test
    public void testRangeB() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "range - Duplicate elements";
        int expected = 47;
        int actual = IntArrayAlgorithms.range(arr);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests range method with no duplicates
     */
    @Test
    public void testRangeC() {
        int[] arr = { 36, 12, 25, 19, 46, 31, 22 };
        String desc = "range - No duplicate elements";
        int expected = 35;
        int actual = IntArrayAlgorithms.range(arr);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 36, 12, 25, 19, 46, 31, 22 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests range method with single element
     */
    @Test
    public void testRangeD() {
        int[] arr = { 36 };
        String desc = "range - single element";
        int expected = 1;
        int actual = IntArrayAlgorithms.range(arr);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 36 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests lastIndexOf method with duplicate of element finding
     */
    @Test
    public void testLastIndexOfA() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "lastIndexOf - duplicate element";
        int expected = 5;
        int actual = IntArrayAlgorithms.lastIndexOf(arr, 85);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with duplicate of element finding
     */
    @Test
    public void testIndexOfA() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "indexOf - duplicate element";
        int expected = 1;
        int actual = IntArrayAlgorithms.indexOf(arr, 85);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with duplicate of element finding
     */
    @Test
    public void testListOfIndexWithValueA() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "listOfIndexWithValue - duplicate element";
        String expected = "1, 5";
        String actual = IntArrayAlgorithms.listOfIndexWithValue(arr, 85);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests lastIndexOf method with single instance of element finding
     */
    @Test
    public void testLastIndexOfB() {
        int[] arr = { 74, 85, 102, 99, 101, 85 };
        String desc = "lastIndexOf - single element found";
        int expected = 2;
        int actual = IntArrayAlgorithms.lastIndexOf(arr, 102);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with single instance of element finding
     */
    @Test
    public void testIndexOfB() {
        int[] arr = { 74, 85, 102, 99, 101, 85 };
        String desc = "indexOf - single element found";
        int expected = 2;
        int actual = IntArrayAlgorithms.indexOf(arr, 102);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with single instance of element finding
     */
    @Test
    public void testListOfIndexWithValueB() {
        int[] arr = { 74, 85, 102, 99, 101, 85 };
        String desc = "listOfIndexWithValue - single element";
        String expected = "2";
        String actual = IntArrayAlgorithms.listOfIndexWithValue(arr, 102);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests lastIndexOf method with element not found
     */
    @Test
    public void testLastIndexOfC() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "lastIndexOf - element not found";
        int expected = -1;
        int actual = IntArrayAlgorithms.lastIndexOf(arr, 0);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with element not found
     */
    @Test
    public void testIndexOfC() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "indexOf - element not found";
        int expected = -1;
        int actual = IntArrayAlgorithms.indexOf(arr, 0);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with element not found
     */
    @Test
    public void testListOfIndexWithValueC() {
        int[] arr = { 74, 85, 102, 99, 101, 85, 56 };
        String desc = "listOfIndexWithValue - element not found";
        String expected = "";
        String actual = IntArrayAlgorithms.listOfIndexWithValue(arr, 0);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74, 85, 102, 99, 101, 85, 56 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests lastIndexOf method with single instance of element finding - single
     * element array
     */
    @Test
    public void testLastIndexOfD() {
        int[] arr = { 74 };
        String desc = "lastIndexOf - single element found";
        int expected = 0;
        int actual = IntArrayAlgorithms.lastIndexOf(arr, 74);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with single instance of element finding - single element
     * array
     */
    @Test
    public void testIndexOfD() {
        int[] arr = { 74 };
        String desc = "indexOf - single element found";
        int expected = 0;
        int actual = IntArrayAlgorithms.indexOf(arr, 74);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with single instance of element finding - single element
     * array
     */
    @Test
    public void testListOfIndexWithValueD() {
        int[] arr = { 74 };
        String desc = "listOfIndexWithValue - single element";
        String expected = "0";
        String actual = IntArrayAlgorithms.listOfIndexWithValue(arr, 74);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = { 74 };
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests lastIndexOf method with element not found - empty array
     */
    @Test
    public void testLastIndexOfE() {
        int[] arr = {};
        String desc = "lastIndexOf - element not found";
        int expected = -1;
        int actual = IntArrayAlgorithms.lastIndexOf(arr, 25);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = {};
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with element not found - empty array
     */
    @Test
    public void testIndexOfE() {
        int[] arr = {};
        String desc = "indexOf - element not found";
        int expected = -1;
        int actual = IntArrayAlgorithms.indexOf(arr, 0);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = {};
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }

    /**
     * Tests indexOf method with element not found - empty array
     */
    @Test
    public void testListOfIndexWithValueE() {
        int[] arr = {};
        String desc = "listOfIndexWithValue - element not found";
        String expected = "";
        String actual = IntArrayAlgorithms.listOfIndexWithValue(arr, 0);
        assertEquals(expected, actual, desc);

        // Test that method does not modify array
        int[] arrCopy = {};
        assertArrayEquals(arrCopy, arr, "Checking that method did not modify array.");
    }
}
