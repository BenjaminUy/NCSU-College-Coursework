import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests SortedDoubleArray
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Alex Calisto
 */
public class SortedDoubleArrayTest {

    /** Delta for comparing doubles */
    public static final double DELTA = 0.0001;

    /**
     * Teaching staff (TS) test for getNewArray of three elements
     */
    @Test
    public void testGetNewArrayTS() {
        double[] expected = { 0, .0, 0.0 };
        assertEquals(3, expected.length);
        double[] actual = SortedDoubleArray.getNewArray(3);
        assertEquals(3, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * Teaching staff (TS) test for getNewArray with invalid parameter
     */
    @Test
    public void testGetNewArrayNegTS() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.getNewArray(-1), "Testing negative number of elements");
        assertEquals("Invalid number of elements", exception.getMessage(),
            "Testing negative number of elements message");
    }

    /**
     * (AC) Test for getNewArray for empty array
     */
    @Test
    public void testGetNewArrayEmpty() {
        double[] expected = {};
        assertEquals(0, expected.length);
        double[] actual = SortedDoubleArray.getNewArray(0);
        assertEquals(0, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * Teaching staff (TS) test for isSorted with duplicate elements
     */
    @Test
    public void testIsSortedTS() {
        double[] arr = { 1, 2, 2.0, 3, 4 };
        assertTrue(SortedDoubleArray.isSorted(arr));
        // Test size after method
        assertEquals(5, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 2.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for isSorted with null array
     */
    @Test
    public void testIsSortedNullTS() {
        double[] arr = null;

        // Test invalid parameters
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.isSorted(arr), "Testing null array");
        assertEquals("Null array", exception.getMessage(), "Testing null array message");

        // Test that method does not modify
        double[] copy = null;
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (AC) Test for isSorted with unsorted array
     */
    @Test
    public void testIsSortedUnsorted() {
        double[] arr = { 4, 2, 6, 1 };
        assertFalse(SortedDoubleArray.isSorted(arr));
        assertEquals(4, arr.length);
        double[] copy = { 4.0, 2.0, 6.0, 1.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (AC) Test for isSorted with reverse sorted array
     */
    @Test
    public void testIsSortedReverse() {
        double[] arr = { 4, 3, 2, 1 };
        assertFalse(SortedDoubleArray.isSorted(arr));
        assertEquals(4, arr.length);
        double[] copy = { 4.0, 3.0, 2.0, 1.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (AC) Test for isSorted with empty array
     */
    @Test
    public void testIsSortedEmpty() {
        double[] arr = {};
        assertTrue(SortedDoubleArray.isSorted(arr));
        assertEquals(0, arr.length);
        double[] copy = {};
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for sequentialSearch for element not in array and
     * insertion point not at 0 or length
     */
    @Test
    public void testSequentialSearchTS() {
        double[] arr = { 1.0, 3.0, 4.0 };
        assertEquals(-2, SortedDoubleArray.sequentialSearch(arr, 2.2));
        // Test size after method
        assertEquals(3, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for sequentialSearch for null array
     */
    @Test
    public void testSequentialSearchNullTS() {
        double[] arr = null;
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.sequentialSearch(arr, 9.0),
                "Test that exception is thrown with null array");
        assertEquals("Null Array", exception.getMessage(),
                "Test exception message with null array");
    }

    /**
     * (AC) Test for sequentialSearch for an unsorted array 
     */
    @Test
    public void testSequentialSearchUnsorted() {
        double[] arr = { 5.0, 3.0, 7.0, 2.0 };
        assertFalse(SortedDoubleArray.isSorted(arr));
        assertEquals(4, arr.length);
        double[] copy = { 5.0, 3.0, 7.0, 2.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for sequentialSearch where element is found
     */
    @Test
    public void testSequentialSearchElementFound() {
        double[] arr = { 1.0, 2.0, 3.0, 4.0 };
        assertEquals(1, SortedDoubleArray.sequentialSearch(arr, 2.0));
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for sequentialSearch where element is not found and insertion point
     * would be at the beginning of array
     */
    @Test
    public void testSequentialSearchNoElementFound1() {
        double[] arr = { 1.0, 2.0, 3.0, 4.0 };
        assertEquals(-1, SortedDoubleArray.sequentialSearch(arr, 0.5));
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for sequentialSearch where element is not found and insertion point
     * would be at the end of array
     */
    @Test
    public void testSequentialSearchNoElementFound2() {
        double[] arr = { 1.0, 2.0, 3.0, 4.0 };
        assertEquals(-5, SortedDoubleArray.sequentialSearch(arr, 4.5));
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for addElement with three element array
     */
    @Test
    public void testAddElementTS() {
        double[] arr = { 1.0, 3.0, 4.0 };
        double[] expected = { 1.0, 2.2, 3.0, 4.0 };
        assertEquals(4, expected.length);
        double[] actual = SortedDoubleArray.addElement(arr, 2.2);
        assertArrayEquals(expected, actual);
        // Test size after method
        assertEquals(3, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for addElement with null array
     */
    @Test
    public void testAddElementNullTS() {
        double[] arr = null;
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.addElement(arr, 9.0),
                "Test that exception is thrown with null array");
        assertEquals("Null Array", exception.getMessage(),
                "Test exception message with null array");
    }

    /**
     * (BU) Test for addElement for an unsorted array
     */
    @Test
    public void testAddElementUnsorted() {
        double[] arr = { 4.0, 3.0, 2.0, 1.0 };
        assertFalse(SortedDoubleArray.isSorted(arr));
        assertEquals(4, arr.length);
        double[] copy = { 4.0, 3.0, 2.0, 1.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for addElement with adding a duplicate element
     */
    @Test
    public void testAddElementDuplicate() {
        double[] arr = { 1.0, 3.0, 5.0, 7.0 };
        double[] expected = {1.0, 3.0, 3.0, 5.0, 7.0 };
        assertEquals(5, expected.length);
        double[] actual = SortedDoubleArray.addElement(arr, 3.0);
        assertArrayEquals(expected, actual);
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 3.0, 5.0, 7.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for addElement with a four element array
     */
    @Test
    public void testAddElement1() {
        double[] arr = { 1.0, 2.0, 3.0, 4.0 };
        double[] expected = {1.0, 2.0, 3.0, 3.6, 4.0 };
        assertEquals(5, expected.length);
        double[] actual = SortedDoubleArray.addElement(arr, 3.6);
        assertArrayEquals(expected, actual);
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 3.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for addElement with a five element array
     */
    @Test
    public void testAddElement2() {
        double[] arr = { 1.0, 2.0, 3.0, 4.0, 5.0 };
        double[] expected = {1.0, 2.0, 2.2, 3.0, 4.0, 5.0};
        assertEquals(6, expected.length);
        double[] actual = SortedDoubleArray.addElement(arr, 2.2);
        assertArrayEquals(expected, actual);
        // Test size after method
        assertEquals(5, arr.length);
        // Test that method does not modify
        double[] copy = { 1.0, 2.0, 3.0, 4.0, 5.0};
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for indexOfMinValue with non-empty array
     */
    @Test
    public void testIndexOfMinValueTS() {
        double[] arr = { 3.0, 10.0, 4.0 };
        assertEquals(0, SortedDoubleArray.indexOfMinValue(arr, 0, 2));
        // Test size after method
        assertEquals(3, arr.length);
        // Test that method does not modify
        double[] copy = { 3.0, 10.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for indexOfMinValue with non-empty array
     */
    @Test
    public void testIndexOfMinValue2TS() {
        double[] arr = { 3.0, 10.0, 4.0 };
        assertEquals(2, SortedDoubleArray.indexOfMinValue(arr, 1, 2));
        // Test size after method
        assertEquals(3, arr.length);
        // Test that method does not modify
        double[] copy = { 3.0, 10.0, 4.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with null array
     */
    @Test
    public void testIndexOfMinValueNull() {
        double[] arr = null;
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.indexOfMinValue(arr, 0, 0),
                "Test that exception is thrown with null array");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with null array");
        double[] copy = null;
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with array with no elements
     */
    @Test
    public void testIndexOfMinValueEmpty() {
        double[] arr = {};
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.indexOfMinValue(arr, 0, 2),
                "Test that exception is thrown with empty array");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with empty array");
        double[] copy = {};
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with negative index A
     */
    @Test
    public void testIndexOfMinValueInvalidIndex1() {
        double[] arr = { 1.0, 3.0, 2.0 };
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.indexOfMinValue(arr, -1, 0),
                "Test that exception is thrown with negative index A");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with negative index A");
        double[] copy = { 1.0, 3.0, 2.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with negative index B
     */
    @Test
    public void testIndexOfMinValueInvalidIndex2() {
        double[] arr = { 1.0, 3.0, 2.0 };
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.indexOfMinValue(arr, 0, -1),
                "Test that exception is thrown with negative index B");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with negative index B");
        double[] copy = { 1.0, 3.0, 2.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with index B being less than index A
     */
    @Test
    public void testIndexOfMinValueInvalidIndex3() {
        double[] arr = { 1.0, 3.0, 2.0 };
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.indexOfMinValue(arr, 2, 1),
                "Test that exception is thrown with index B being less than index A");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with index B being less than index A");
        double[] copy = { 1.0, 3.0, 2.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with a valid four-element array
     */
    @Test
    public void testIndexOfMinValueValid() {
        double[] arr = { 3.0, 2.0, 4.0, 10.0 };
        assertEquals(1, SortedDoubleArray.indexOfMinValue(arr, 1, 3));
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 3.0, 2.0, 4.0, 10.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with a valid six-element array
     */
    @Test
    public void testIndexOfMinValueValid2() {
        double[] arr = { 7.0, 12.0, 3.0, 2.0, 4.0, 10.0 };
        assertEquals(3, SortedDoubleArray.indexOfMinValue(arr, 0, 4));
        // Test size after method
        assertEquals(6, arr.length);
        // Test that method does not modify
        double[] copy = { 7.0, 12.0, 3.0, 2.0, 4.0, 10.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) Test for indexOfMinValue with duplicate elements
     */
    @Test
    public void testIndexOfMinValueDuplicate() {
        double[] arr = { 2.0, 5.0, 2.0, 2.0 };
        assertEquals(0, SortedDoubleArray.indexOfMinValue(arr, 0, 3));
        // Test size after method
        assertEquals(4, arr.length);
        // Test that method does not modify
        double[] copy = { 2.0, 5.0, 2.0, 2.0 };
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for swap with first two elements
     */
    @Test
    public void testSwapTS() {
        double[] actual = { 3.0, 10.0, 4.0 };
        double[] expected = { 10.0, 3.0, 4.0 };
        assertEquals(3, expected.length);
        SortedDoubleArray.swap(actual, 0, 1);
        // Test size after method
        assertEquals(3, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * Teaching staff (TS) test for swap with first and last elements
     */
    @Test
    public void testSwap2TS() {
        double[] actual = { 3.0, 10.0, 4.0 };
        double[] expected = { 4.0, 10.0, 3.0 };
        assertEquals(3, expected.length);
        SortedDoubleArray.swap(actual, 0, 2);
        // Test size after method
        assertEquals(3, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * (BU) test for swap with null array
     */
    @Test
    public void testSwapNull() {
        double[] arr = null;
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.swap(arr, 0, 0),
                "Test that exception is thrown with null array");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with null array");
        double[] copy = null;
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * (BU) test for swap with empty array last two elements
     */
    @Test
    public void testSwap1() {
        double[] actual = { 1.0, 2.0, 3.0};
        double[] expected = { 1.0, 3.0, 2.0};
        assertEquals(3, expected.length);
        SortedDoubleArray.swap(actual, 1, 2);
        // Test size after method
        assertEquals(3, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * (BU) test for swap with empty array
     */
    @Test
    public void testSwapEmpty() {
        double[] arr = {};
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.swap(arr, 0, 0),
                "Test that exception is thrown with empty array");
        assertEquals("Invalid parameter", exception.getMessage(),
                "Test exception message with empty array");
        double[] copy = {};
        assertArrayEquals(copy, arr, DELTA);
    }

    /**
     * Teaching staff (TS) test for selectionSort for unsorted array of three
     * elements
     */
    @Test
    public void testSelectionSortTS() {
        double[] actual = { 10.0, 3.0, 4.0 };
        double[] expected = { 3.0, 4.0, 10.0 };
        assertEquals(3, expected.length);
        SortedDoubleArray.selectionSort(actual);
        // Test size after method
        assertEquals(3, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * Teaching staff (TS) test for selectionSort for null array
     */
    @Test
    public void testSelectionSortNullTS() {
        double[] actual = null;
        double[] expected = null;
        // Test invalid parameters
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SortedDoubleArray.selectionSort(actual), "Testing null array");
        assertEquals("Null array", exception.getMessage(), "Testing null array message");
        // Test size after method
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * (BU) Test for selectionSort for an unsorted 4-element array
     */
    @Test
    public void testSelectionUnsorted() {
        double[] actual = { 10.0, 5.0, 1.0, 2.0 };
        double[] expected = { 1.0, 2.0, 5.0, 10.0 };
        assertEquals(4, expected.length);
        SortedDoubleArray.selectionSort(actual);
        // Test size after method
        assertEquals(4, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * (BU) Test for selectionSort for reverse sorted array
     */
    @Test
    public void testSelectionReverseSorted() {
        double[] actual = { 10.0, 5.0, 2.0, 1.0 };
        double[] expected = { 1.0, 2.0, 5.0, 10.0 };
        assertEquals(4, expected.length);
        SortedDoubleArray.selectionSort(actual);
        // Test size after method
        assertEquals(4, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }

    /**
     * (BU) Test for selectionSort for duplicates
     */
    @Test
    public void testSelectionDuplicate() {
        double[] actual = { 10.0, 5.0, 5.0, 1.0 };
        double[] expected = { 1.0, 5.0, 5.0, 10.0 };
        assertEquals(4, expected.length);
        SortedDoubleArray.selectionSort(actual);
        // Test size after method
        assertEquals(4, actual.length);
        assertArrayEquals(expected, actual, DELTA);
    }
}
