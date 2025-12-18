import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests StringArrays class
 * 
 * @author Jessica Young Schmidt
 */
public class StringArraysTest {

    /**
     * No elements - isPalindrome
     */
    @Test
    public void testIsPalindromeA() {
        String[] arr = {};
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = {};
        assertArrayEquals(copy, arr);
    }

    /**
     * One element - isPalindrome
     */
    @Test
    public void testIsPalindromeB() {
        String[] arr = { "I <3 CS!" };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "I <3 CS!" };
        assertArrayEquals(copy, arr);
    }

    /**
     * two element palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeC() {
        String[] arr = { "I <3 CS!", "I <3 CS!" };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "I <3 CS!", "I <3 CS!" };
        assertArrayEquals(copy, arr);
    }

    /**
     * two element palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeCString() {
        String[] arr = { new String("I <3 CS!"), new String("I <3 CS!") };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "I <3 CS!", "I <3 CS!" };
        assertArrayEquals(copy, arr);
    }

    /**
     * two element not palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeD() {
        String[] arr = { "I <3 CS!", "I <3 CS" };
        boolean test = StringArrays.isPalindrome(arr);

        assertFalse(test);

        // Check that did not modify initial array(s)
        String[] copy = { "I <3 CS!", "I <3 CS" };
        assertArrayEquals(copy, arr);
    }

    /**
     * three element palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeE() {
        String[] arr = { "one", "two", "one" };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "one", "two", "one" };
        assertArrayEquals(copy, arr);
    }

    /**
     * three element not palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeF() {
        String[] arr = { "one", "two", "ONE" };
        boolean test = StringArrays.isPalindrome(arr);

        assertFalse(test);

        // Check that did not modify initial array(s)
        String[] copy = { "one", "two", "ONE" };
        assertArrayEquals(copy, arr);
    }

    /**
     * six element palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeG() {
        String[] arr = { "aay", "bee", "cee", "cee", "bee", "aay" };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "aay", "bee", "cee", "cee", "bee", "aay" };
        assertArrayEquals(copy, arr);
    }

    /**
     * seven element palindrome - isPalindrome
     */
    @Test
    public void testIsPalindromeH() {
        String[] arr = { "aay", "bee", "cee", "dee", "cee", "bee", "aay" };
        boolean test = StringArrays.isPalindrome(arr);

        assertTrue(test);

        // Check that did not modify initial array(s)
        String[] copy = { "aay", "bee", "cee", "dee", "cee", "bee", "aay" };
        assertArrayEquals(copy, arr);
    }

    /**
     * Equals2D - testing same variable
     */
    @Test
    public void testEquals2Di() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };

        assertTrue(StringArrays.equals2D(strings1, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
    }

    /**
     * Equals2D - testing same contents
     */
    @Test
    public void testEquals2Dj() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        String[][] strings2 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };// Same

        assertTrue(StringArrays.equals2D(strings1, strings2));
        assertTrue(StringArrays.equals2D(strings2, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        assertArrayEquals(strings1copy, strings2);
    }

    /**
     * Equals2D - testing same contents - checks that equivalent strings
     */
    @Test
    public void testEquals2DjString() {
        String[][] strings1 = { { new String("Cat"), new String("Dog"), new String("Puppy") },
            { new String("A"), new String("B"), new String("C") } };
        String[][] strings2 = { { new String("Cat"), new String("Dog"), new String("Puppy") },
            { new String("A"), new String("B"), new String("C") } };// Same

        assertTrue(StringArrays.equals2D(strings1, strings2));
        assertTrue(StringArrays.equals2D(strings2, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        assertArrayEquals(strings1copy, strings2);
    }

    /**
     * Equals2D - testing one element different
     */
    @Test
    public void testEquals2Dk() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        String[][] strings3 = { { "Cat", "dog", "Puppy" }, { "A", "B", "C" } };// One

        assertFalse(StringArrays.equals2D(strings1, strings3));
        assertFalse(StringArrays.equals2D(strings3, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        String[][] strings3Copy = { { "Cat", "dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings3Copy, strings3);
    }

    /**
     * Equals2D - testing different number of row
     */
    @Test
    public void testEquals2Dl() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        String[][] strings4 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" },
            { "Cat", "Dog", "Puppy" } }; // more rows

        assertFalse(StringArrays.equals2D(strings1, strings4));
        assertFalse(StringArrays.equals2D(strings4, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        String[][] strings4Copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" },
            { "Cat", "Dog", "Puppy" } };
        assertArrayEquals(strings4Copy, strings4);
    }

    /**
     * Equals2D - testing jagged
     */
    @Test
    public void testEquals2Dm() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        String[][] strings5 = { { "Cat", "Dog" }, { "A", "B", "C" } };

        assertFalse(StringArrays.equals2D(strings1, strings5));
        assertFalse(StringArrays.equals2D(strings5, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        String[][] strings5Copy = { { "Cat", "Dog" }, { "A", "B", "C" } };
        assertArrayEquals(strings5Copy, strings5);
    }

    /**
     * Equals2D - testing different dimensions
     */
    @Test
    public void testEquals2Dn() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        String[][] strings6 = { {} };

        assertFalse(StringArrays.equals2D(strings1, strings6));
        assertFalse(StringArrays.equals2D(strings6, strings1));

        // Check that did not modify initial array(s)
        String[][] strings1copy = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        assertArrayEquals(strings1copy, strings1);
        String[][] stringsCopy = { {} };
        assertArrayEquals(stringsCopy, strings6);
    }

    /**
     * Equals2D - testing empty
     */
    @Test
    public void testEquals2Do() {
        String[][] strings6 = { {} };
        String[][] strings7 = { {} };

        assertTrue(StringArrays.equals2D(strings7, strings6));
        assertTrue(StringArrays.equals2D(strings6, strings7));

        // Check that did not modify initial array(s)
        String[][] stringsCopy = { {} };
        assertArrayEquals(stringsCopy, strings6);
        assertArrayEquals(strings7, strings6);
    }

    /**
     * Test null array
     */
    @Test
    public void testIsPalindromeExceptionA() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> StringArrays.isPalindrome(null),
                "Test that exception is thrown with invalid array");
        assertEquals("Invalid array", exception.getMessage(),
                "Test exception message with invalid array");

    }

    /**
     * Test null element
     */
    @Test
    public void testIsPalindromeExceptionB() {
        String[] arr = { "hello", null };
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> StringArrays.isPalindrome(arr),
                "Test that exception is thrown with invalid element");
        assertEquals("Invalid element", exception.getMessage(),
                "Test exception message with invalid element");

    }

    /**
     * Test null array
     */
    @Test
    public void testEquals2DExceptionA() {
        String[][] strings1 = { { "Cat", "Dog", "Puppy" }, { "A", "B", "C" } };
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> StringArrays.equals2D(strings1, null),
                "Test that exception is thrown with invalid array");
        assertEquals("Invalid array", exception.getMessage(),
                "Test exception message with invalid array");
        exception = assertThrows(IllegalArgumentException.class,
            () -> StringArrays.equals2D(null, strings1),
                "Test that exception is thrown with invalid array");
        assertEquals("Invalid array", exception.getMessage(),
                "Test exception message with invalid array");

    }
}
