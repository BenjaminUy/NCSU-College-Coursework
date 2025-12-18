import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests RationalNumber class
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 */
public class RationalNumberTest {

    /** Rational number 1/4 */
    private RationalNumber quarter;

    /** Rational number -1/4 */
    private RationalNumber quarterNeg;

    /** Rational number 2/8 which is 1/4 */
    private RationalNumber twoOverEight;

    /** Rational number 1/3 */
    private RationalNumber third;

    /** Rational number 1/2 */
    private RationalNumber half;

    /** Rational number 3/1 */
    private RationalNumber three;

    /** Rational number 1/5 */
    private RationalNumber fifth;
    
    /** Rational number -1/5 */
    private RationalNumber fifthNeg;

    /** Rational number 2/10  which is 1/5 */
    private RationalNumber twoOverTen;

    /** Rational number 2/5 */
    private RationalNumber twoOverFive;
    
    /** Rational number -2/5 */
    private RationalNumber twoOverFiveNeg;

    /** Rational number -2/10 */
    private RationalNumber twoOverTenNeg;

    /**
     * Set up fields
     */
    @BeforeEach
    public void setUp() {
        quarter = new RationalNumber(1, 4);
        quarterNeg = new RationalNumber(1, -4);
        twoOverEight = new RationalNumber(2, 8);
        third = new RationalNumber(1, 3);
        half = new RationalNumber(1, 2);
        three = new RationalNumber(6, 2);
        fifth = new RationalNumber(1, 5);
        fifthNeg = new RationalNumber(1, -5);
        twoOverTen = new RationalNumber(2, 10);
        twoOverTenNeg = new RationalNumber(-2, 10);
        twoOverFive = new RationalNumber(2, 5);
        twoOverFiveNeg = new RationalNumber(-2, 5);
    }

    /**
     * Testing constructor
     */
    @Test
    public void testConstructor() {
        RationalNumber def = new RationalNumber();
        assertEquals(0, def.getNumerator(), "Default constructor getNumerator");
        assertEquals(1, def.getDenominator(), "Default constructor getDenominator");
        RationalNumber rn = new RationalNumber(2, -4);
        assertEquals(-1, rn.getNumerator(), "getNumerator");
        assertEquals(2, rn.getDenominator(), "getDenominator");
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> new RationalNumber(3, 0),
                "Testing constructor with denominator 0");
        assertEquals("Denominator is 0", exception.getMessage(),
                "Testing constructor with denominator 0 - exception message");
    }

    /**
     * Test toString
     */
    @Test
    public void testToStringTS() {
        assertEquals("1/4", quarter.toString(), "quarter toString");
        assertEquals("-1/4", quarterNeg.toString(), "quarterNeg toString");
        assertEquals("1/4", twoOverEight.toString(), "twoOverEight toString");
        assertEquals("1/3", third.toString(), "third toString");
        assertEquals("1/2", half.toString(), "half toString");
        assertEquals("3", three.toString(), "three toString");
    }

    /**
     * Test toString student test
     */
    @Test
    public void testToString() {
        // FIXED: Add at least 1 additional test
        assertEquals("1/5", fifth.toString(), "fifth toString");
        assertEquals("-1/5", fifthNeg.toString(), "fifthNeg toString");
    }

    /**
     * Test equals
     */
    @Test
    public void testEqualsTS() {
        String desc = "quarter.equals(twoOverEight)";
        boolean actB = quarter.equals(twoOverEight);
        assertTrue(actB, desc);

    }

    /**
     * Test equals student test
     */
    @Test
    public void testEquals1() {
        // FIXED: Add at least 1 additional test
        String desc = "fifth.equals(twoOverTen)";
        boolean actB = fifth.equals(twoOverTen);
        assertTrue(actB, desc);
    }

    /**
     * Test equals student test positive and negative fraction not equal
     */
    @Test
    public void testEquals2() {
        String desc = "twoOverFive.equals(twoOverFiveNeg)";
        boolean act = twoOverFive.equals(twoOverFiveNeg);
        assertFalse(act, desc);
    }

    /**
     * Test equals student test equal negative fractions
     */
    @Test
    public void testEquals3() {
        String desc = "twoOverTenNeg.equals(fifthNeg)";
        boolean act = twoOverTenNeg.equals(fifthNeg);
        assertTrue(act, desc);
    }

    /**
     * Test add
     */
    @Test
    public void testAddTS() {
        String desc = "quarter.add(twoOverEight)";
        RationalNumber exp = half;
        RationalNumber act = quarter.add(twoOverEight);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 4), quarter);
        assertEquals(new RationalNumber(1, 4), twoOverEight);
    }

    /**
     * Test add student test
     */
    @Test
    public void testAdd1() {
        // FIXED: Add at least 1 additional test
        String desc = "fifth.add(twoOverTen)";
        RationalNumber exp = twoOverFive;
        RationalNumber act = fifth.add(twoOverTen);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
        assertEquals(new RationalNumber(1, 5), twoOverTen);
    }

    /**
     * Test add student test same rational numbers
     */
    @Test
    public void testAdd2() {
        String desc = "fifth.add(fifth)";
        RationalNumber exp = twoOverFive;
        RationalNumber act = fifth.add(fifth);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
    }

    /**
     * Test subtract
     */
    @Test
    public void testSubtractTS() {
        String desc = "quarter.subtract(twoOverEight)";
        RationalNumber exp = new RationalNumber();
        RationalNumber act = quarter.subtract(twoOverEight);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 4), quarter);
        assertEquals(new RationalNumber(1, 4), twoOverEight);
    }

    /**
     * Test subtract student test equal to zero
     */
    @Test
    public void testSubtract1() {
        // FIXED: Add at least 1 additional test
        String desc = "fifth.subtract(twoOverTen)";
        RationalNumber exp = new RationalNumber();
        RationalNumber act = fifth.subtract(twoOverTen);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
        assertEquals(new RationalNumber(1, 5), twoOverTen);
    }

    /**
     * Test subtract student test negative from positive
     */
    @Test
    public void testSubtract2() {
        String desc = "quarterNeg.subtract(quarter)";
        RationalNumber exp = half;
        RationalNumber act = quarterNeg.subtract(quarter);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(-1, 4), quarterNeg);  
        assertEquals(new RationalNumber(1, 4), quarter);
    }

    /**
     * Test subtract student test positive from negative
     */
    @Test
    public void testSubtract3() {
        String desc = "fifth.subtract(fifthNeg)";
        RationalNumber exp = twoOverFiveNeg;
        RationalNumber act = fifth.subtract(fifthNeg);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
        assertEquals(new RationalNumber(-1, 5), fifthNeg);  
    }

    /**
     * Test multiply
     */
    @Test
    public void testMultiplyTS() {
        String desc = "quarter.multiply(twoOverEight)";
        RationalNumber exp = new RationalNumber(1, 16);
        RationalNumber act = quarter.multiply(twoOverEight);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 4), quarter);
        assertEquals(new RationalNumber(1, 4), twoOverEight);
    }

    /**
     * Test multiply student test
     */
    @Test
    public void testMultiply() {
        // FIXED: Add at least 1 additional test
        String desc = "fifth.multiply(twoOverTen)";
        RationalNumber exp = new RationalNumber(1, 25);
        RationalNumber act = fifth.multiply(twoOverTen);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
        assertEquals(new RationalNumber(1, 5), twoOverTen);
    }

    /**
     * Test divide
     */
    @Test
    public void testDivideTS() {
        String desc = "quarter.divide(twoOverEight)";
        RationalNumber exp = new RationalNumber(1, 1);
        RationalNumber act = quarter.divide(twoOverEight);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 4), quarter);
        assertEquals(new RationalNumber(1, 4), twoOverEight);
    }

    /**
     * Test divide student test
     */
    @Test
    public void testDivide() {
        // FIXED: Add at least 1 additional test
        String desc = "fifth.divide(twoOverTen)";
        RationalNumber exp = new RationalNumber(1, 1);
        RationalNumber act = fifth.divide(twoOverTen);
        assertEquals(exp, act, desc);
        // check that rational number variables were not changed
        assertEquals(new RationalNumber(1, 5), fifth);
        assertEquals(new RationalNumber(1, 5), twoOverTen);
    }

}