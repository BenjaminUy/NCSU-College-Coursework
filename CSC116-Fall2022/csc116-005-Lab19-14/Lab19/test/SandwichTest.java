import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//FIXED: Add documentation
/**
 * Testing Sandwich class
 * @author Dr. Schmidt
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class SandwichTest {

    /** Private sandwich variable to be used throughout class */
    private Sandwich sandwich;

    /** Private rye variable of Bread type for first sandwich */
    private Bread rye;

    /** Private turkey variable of SandwichFilling type for first sandwich */
    private SandwichFilling turkey;

    /** Private grilledcheese variable to be used throughout class */
    private Sandwich grilledCheese;

    /** Private wheat variable of Bread type for second sandwich */
    private Bread wheat;

    /** Private cheese variable of SandwichFilling type for second sandwich */
    private SandwichFilling cheese;

    /**
     * Initializing all variables to be used throughout test file
     */
    @BeforeEach
    public void setUp() {
        rye = new Bread("Rye", 65);
        turkey = new SandwichFilling("Turkey", 54);
        sandwich = new Sandwich("Turkey Sandwich", rye, turkey);
        wheat = new Bread("Wheat", 70);
        cheese = new SandwichFilling("Cheese", 15);
        grilledCheese = new Sandwich("Grilled Cheese", wheat, cheese);
    }

    /**
     * TS test for total calories of turkey sandwich
     */
    @Test
    public void testTotalCalories() {
        assertEquals(184, sandwich.getCalories(), "Total Calories");
    }

    // FIXED: Add at least 1 additional test for getCalories
    /**
     * Student test for total calories of grilled cheese sandwich
     */
    @Test
    public void testTotalCaloriesGrilledCheese() {
        assertEquals(155, grilledCheese.getCalories(), "Total calories");
    }

    /**
     * TS tests of equalities for turkey sandwich
     */
    @Test
    public void testEquals() {
        assertTrue(sandwich.equals(sandwich), "Test same object");
        assertTrue(sandwich.equals(new Sandwich("Turkey Sandwich", rye, turkey)),
                "Test same values");
        assertTrue(sandwich.equals(new Sandwich("Turkey Sandwich", new Bread("Rye", 65),
                new SandwichFilling("Turkey", 54))), "Test same components");
        assertFalse(sandwich.equals(new Sandwich("Turkey Sandwich", new Bread("Rye", 65),
                new SandwichFilling("turkey", 54))), "Test filling with different case");
        assertFalse(sandwich.equals(new Sandwich("Turkey Sandwich", new Bread("Rye", 61),
                new SandwichFilling("Turkey", 54))), "Test bread with different calories");
        assertFalse(sandwich.equals(null), "Test with null");
        assertFalse(sandwich.equals("Hello"), "Test with string");
    }

    // FIXED: Add at least 1 additional test for equals
    /**
     * Student tests of equalities for grilled cheese sandwich
     */
    @Test
    public void testEquals2() {
        assertTrue(grilledCheese.equals(grilledCheese), "Test same object");
        assertTrue(grilledCheese.equals(new Sandwich("Grilled Cheese", wheat, cheese)),
                "Test same values");
        assertTrue(grilledCheese.equals(new Sandwich("Grilled Cheese", new Bread("Wheat", 70),
                new SandwichFilling("Cheese", 15))), "Test same components");
        assertFalse(grilledCheese.equals(new Sandwich("Grilled Cheese", new Bread("Wheat", 70),
                new SandwichFilling("cheese", 15))), "Test filling with different case");
        assertFalse(grilledCheese.equals(new Sandwich("Grilled Cheese", new Bread("Wheat", 23),
                new SandwichFilling("Cheese", 15))), "Test bread with different calories");
        assertFalse(grilledCheese.equals(null), "Test with null");
        assertFalse(grilledCheese.equals("Hello"), "Test with string");
    }

    /**
     * TS test for toString method of turkey sandwich
     */
    @Test
    public void testToString() {
        assertEquals("Total Calories (184): Turkey (54 calories per serving) on"
            + " Rye (65 calories per slice)", sandwich.toString(), "Test toString");
    }

    // FIXED: Add at least 1 additional test for toString
    /**
     * Student test for toString method of grilled cheese sandwich
     */
    @Test
    public void testToString2() {
        assertEquals("Total Calories (155): Cheese (15 calories per serving) on"
            + " Wheat (70 calories per slice)", grilledCheese.toString(), "Test toString");
    }
}
