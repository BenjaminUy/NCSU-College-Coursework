import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests CharacterArray class
 * 
 * @author Jessica Young Schmidt
 */
public class CharacterArrayTest {

    /**
     * Test with array of even elements
     */
    @Test
    public void testSwapPairsA() {
        char[] arrSwapA = { 'a', 'b', 'c', 'd' };
        char[] arrSwapAExp = { 'b', 'a', 'd', 'c' };
        CharacterArray.swapPairs(arrSwapA);
        assertArrayEquals(arrSwapAExp, arrSwapA);
    }

    /**
     * Test with array of odd number of elements
     */
    @Test
    public void testSwapPairsB() {
        char[] arrSwapA = { 'a', 'b', 'c', 'd', 'e' };
        char[] arrSwapAExp = { 'b', 'a', 'd', 'c', 'e' };
        CharacterArray.swapPairs(arrSwapA);
        assertArrayEquals(arrSwapAExp, arrSwapA);
    }

    /**
     * Test with array of two elements
     */
    @Test
    public void testSwapPairsC() {
        char[] arrSwapA = { 'a', 'b' };
        char[] arrSwapAExp = { 'b', 'a' };
        CharacterArray.swapPairs(arrSwapA);
        assertArrayEquals(arrSwapAExp, arrSwapA);
    }

    /**
     * Test with array of one element
     */
    @Test
    public void testSwapPairsD() {
        char[] arrSwapA = { 'a' };
        char[] arrSwapAExp = { 'a' };
        CharacterArray.swapPairs(arrSwapA);
        assertArrayEquals(arrSwapAExp, arrSwapA);
    }

    /**
     * Test with empty array
     */
    @Test
    public void testSwapPairsE() {
        char[] arrSwapA = {};
        char[] arrSwapAExp = {};
        CharacterArray.swapPairs(arrSwapA);
        assertArrayEquals(arrSwapAExp, arrSwapA);
    }

    /**
     * Test with empty chars array and empty string
     */
    @Test
    public void testCountCharactersA() {
        char[] chars = {};
        int[] counts = {};
        int[] expCounts = CharacterArray.countCharacters(chars, "");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = {};
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with empty chars array and non-empty string
     */
    @Test
    public void testCountCharactersB() {
        char[] chars = {};
        int[] counts = {};
        int[] expCounts = CharacterArray.countCharacters(chars, "CSC116");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = {};
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with chars array and empty string
     */
    @Test
    public void testCountCharactersC() {
        char[] chars = { 'c', 's' };
        int[] counts = { 0, 0 };
        int[] expCounts = CharacterArray.countCharacters(chars, "");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = { 'c', 's' };
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with non-empty chars array and non-empty string
     */
    @Test
    public void testCountCharactersD() {
        char[] chars = { 'n', 'c', 's', 'u' };
        int[] counts = { 0, 0, 0, 0 };
        int[] expCounts = CharacterArray.countCharacters(chars, "CSC116");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = { 'n', 'c', 's', 'u' };
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with non-empty chars array and non-empty string
     */
    @Test
    public void testCountCharactersE() {
        char[] chars = { 'n', 'c', 's', 'u' };
        int[] counts = { 0, 2, 1, 0 };
        int[] expCounts = CharacterArray.countCharacters(chars, "csc116");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = { 'n', 'c', 's', 'u' };
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with non-empty chars array and non-empty string. Repeats in character array.
     */
    @Test
    public void testCountCharactersF() {
        char[] chars = { 'n', 'c', 's', 'u', 's' };
        int[] counts = { 0, 2, 1, 0, 1 };
        int[] expCounts = CharacterArray.countCharacters(chars, "csc116");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = { 'n', 'c', 's', 'u', 's' };
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

    /**
     * Test with non-empty chars array and non-empty string. Repeats in character array.
     * Testing case.
     */
    @Test
    public void testCountCharactersG() {
        char[] chars = { 'n', 'c', 's', 'u', 's' };
        int[] counts = { 0, 1, 1, 0, 1 };
        int[] expCounts = CharacterArray.countCharacters(chars, "Csc116");
        assertArrayEquals(counts, expCounts);

        // Test that method does not modify array
        char[] charsCopy = { 'n', 'c', 's', 'u', 's' };
        assertArrayEquals(charsCopy, chars, "Checking that method did not modify array.");
    }

}
