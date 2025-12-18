import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// FIXED: Add documentation
/**
 * Testing Bread class
 * @author Dr. Schmidt
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class BreadTest {

    /** Private rye variable of Bread type */
    private Bread rye;

    /** Private wheat variable of Bread type */
    private Bread wheat;

    /**
     * Initializing rye and wheat to be used throughout test file
     */
    @BeforeEach
    public void setUp() {
        rye = new Bread("Rye", 65);
        wheat = new Bread("Wheat", 70);
    }

    /**
     * TS test for type and get calories of rye variable
     */
    @Test
    public void testGetters() {
        assertEquals("Rye", rye.getType(), "Rye type");
        assertEquals(65, rye.getCalories(), "Rye calories");
    }

    // FIXED: Add at least 1 additional test for getType
    /**
     * Student test for type of wheat variable
     */
    @Test
    public void testGetTypeWheat() {
        assertEquals("Wheat", wheat.getType(), "Wheat type");
        
    }

    // FIXED: Add at least 1 additional test for getCalories
    /**
     * Student test for calories of wheat variable
     */
    @Test
    public void testGetCaloriesWheat() {
        assertEquals(70, wheat.getCalories(), "Wheat calories");
    }

    /**
     * TS tests of equalities for rye variable
     */
    @Test
    public void testEquals() {
        assertTrue(rye.equals(rye), "Test equals for same object");
        assertTrue(rye.equals(new Bread("Rye", 65)), "Test equals with bread with same fields");
        assertFalse(rye.equals(new Bread("Rye", 60)), "Test equals with different calories");
        assertFalse(rye.equals(new Bread("rye", 65)), "Test equals with different case");
        assertFalse(rye.equals(null), "Test equals with null");
        assertFalse(rye.equals("Hello"), "Test equals with string");
    }

    // FIXED: Add at least 1 additional test for equals
    /**
     * Student tests of equalities for wheat variable
     */
    @Test
    public void testEquals2() {
        assertTrue(wheat.equals(wheat), "Test equals for same object");
        assertTrue(wheat.equals(new Bread("Wheat", 70)), "Test equals with bread with same fields");
        assertFalse(wheat.equals(new Bread("Wheat", 60)), "Test equals with different calories");
        assertFalse(wheat.equals(new Bread("wheat", 70)), "Test equals with different case");
        assertFalse(wheat.equals(null), "Test equals with null");
        assertFalse(wheat.equals("Hello"), "Test equals with string");
    }

    /**
     * TS test for toString method of rye variable
     */
    @Test
    public void testToString() {
        assertEquals("Rye (65 calories per slice)", rye.toString(), "Test toString");
    }

    // FIXED: Add at least 1 additional test for toString
    /**
     * Student test for toString method of wheat variable
     */
    @Test
    public void testToString2() {
        assertEquals("Wheat (70 calories per slice)", wheat.toString(), "Test toString");
    }

}
