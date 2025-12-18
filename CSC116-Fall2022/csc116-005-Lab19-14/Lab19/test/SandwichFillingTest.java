import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//FIXED: Add documentation
/**
 * Testing SandwichFilling class
 * @author Dr. Schmidt
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class SandwichFillingTest {

    /** Private turkey variable of SandwichFilling type to be used throughout class */
    private SandwichFilling turkey;

    /** Private cheese variable of SandwichFilling type to be used throughout class */
    private SandwichFilling cheese;

    /**
     * Initializing turkey and cheese variables to be used through test file
     */
    @BeforeEach
    public void setUp() {
        turkey = new SandwichFilling("Turkey", 54);
        cheese = new SandwichFilling("Cheese", 15);
    }

    /**
     * TS test for type and get calories of turkey variable
     */
    @Test
    public void testGetters() {
        assertEquals("Turkey", turkey.getType(), "Turkey type");
        assertEquals(54, turkey.getCalories(), "Turkey calories");
    }

    // FIXED: Add additional test for getType
    /**
     * Student test for type of cheese variable
     */
    @Test 
    public void testGetType() {
        assertEquals("Cheese", cheese.getType(), "Cheese type");
    }

    // FIXED: Add additional test for getCalories
    /**
     * Student test for calories of cheese variable
     */
    @Test
    public void testGetCalories() {
        assertEquals(15, cheese.getCalories(), "Cheese calories");
    }

    /**
     * TS tests of equalities for turkey variable
     */
    @Test
    public void testEquals() {
        assertTrue(turkey.equals(turkey), "Test same object");
        assertTrue(turkey.equals(new SandwichFilling("Turkey", 54)),
                "Test objects with same fields");
        assertFalse(turkey.equals(new SandwichFilling("Turkey", 60)),
                "Test with different calories");
        assertFalse(turkey.equals(new SandwichFilling("turkey", 54)), "Test with different name");
        assertFalse(turkey.equals(null), "Test with null");
        assertFalse(turkey.equals("Hello"), "Test with string");
    }

    // FIXED: Add additional test for equals
    /**
     * Student tests of equalities for cheese variable
     */
    @Test
    public void testEquals2() {
        assertTrue(cheese.equals(cheese), "Test same object");
        assertTrue(cheese.equals(new SandwichFilling("Cheese", 15)),
                "Test objects with same fields");
        assertFalse(cheese.equals(new SandwichFilling("Cheese", 0)),
                "Test with different calories");
        assertFalse(cheese.equals(new SandwichFilling("celery", 15)), "Test with different name");
        assertFalse(cheese.equals(null), "Test with null");
        assertFalse(cheese.equals("Hello"), "Test with string");
    }

    /**
     * TS test for toString method of turkey variable
     */
    @Test
    public void testToString() {
        assertEquals("Turkey (54 calories per serving)", turkey.toString(), "Test toString");
    }

    // FIXED: Add additional test for toString
    /**
     * Student test for toString method of cheese variable
     */
    @Test
    public void testToString2() {
        assertEquals("Cheese (15 calories per serving)", cheese.toString(), "Test toString");
    }

}
