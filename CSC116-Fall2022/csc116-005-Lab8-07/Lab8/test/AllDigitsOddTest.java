import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests AllDigitsOdd program, which tests if all digits are odd in an integer
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Bryson Brading
 * @author Eric Chin
 */
public class AllDigitsOddTest {

    /**
     * Tests areAllDigitsOdd method - If one of the digits
     * is even, return false
     */
    @Test
    public void testAreAllDigitsOddA() {
        assertFalse(AllDigitsOdd.areAllDigitsOdd(12));
    }

    /**
     * Tests areAllDigitsOdd method - If all of the digits
     * are odd and start with one, return true
     */
    @Test
    public void testAreAllDigitsOddB() {
        assertTrue(AllDigitsOdd.areAllDigitsOdd(135));
    }

    /**
     * Tests areAllDigitsOdd method - If all digits
     * are odd, return true
     */
    @Test
    public void testAreAllDigitsOddC() {
        assertTrue(AllDigitsOdd.areAllDigitsOdd(357));
    }

    /**
     * Tests lastDigit method - If last digit is 2,
     * return 2
     */
    @Test
    public void testLastDigitA() {
        assertEquals(2, AllDigitsOdd.lastDigit(12));
    }

    /**
     * Tests lastDigit method - If last digit is 5,
     * return 5
     */
    @Test
    public void testLastDigitB() {
        assertEquals(5, AllDigitsOdd.lastDigit(135));
    }

    /**
     * Tests withoutLastDigit method - If integer is 12,
     * return 1
     */
    @Test
    public void testWithoutLastDigitA() {
        assertEquals(1, AllDigitsOdd.withoutLastDigit(12));
    }

     /**
     * Tests withoutLastDigit method - If integer is 1567,
     * return 156
     */
    @Test
    public void testWithoutLastDigitB() {
        assertEquals(156, AllDigitsOdd.withoutLastDigit(1567));
    }
}