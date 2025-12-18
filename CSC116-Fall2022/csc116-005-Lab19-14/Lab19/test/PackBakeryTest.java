import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the PackBakery class
 * @author Dr. Schmidt
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class PackBakeryTest {
    
    /** Private bakery field of PackBakery type */
    private PackBakery bakery;

    /** Private turkeySandwich variable of Sandwich type */
    private Sandwich turkeySandwich;

    /** Private pbjSandwich variable of Sandwich type */
    private Sandwich pbjSandwich;

    /** Private bltSandwich variable of Sandwich type */
    private Sandwich bltSandwich;

    /** Private bananaSandwich variable of Sandwich type */
    private Sandwich bananaSandwich;

    /** Private grilledCheese variable of Sandwich type */
    private Sandwich grilledCheese;

    /** Private bakery field of PackBakery type second */
    private PackBakery bakery2;

    /** Private nutellaSandwich variable of Sandwich type */
    private Sandwich nutellaSandwich;

    /**
     * Initializing all complete sandwiches to be used throughout class
     */
    @BeforeEach
    public void setUp() {
        Bread rye = new Bread("Rye", 65);
        SandwichFilling turkey = new SandwichFilling("Turkey", 54);
        turkeySandwich = new Sandwich("Turkey Sandwich", rye, turkey);

        Bread white = new Bread("White", 50);
        SandwichFilling pbj = new SandwichFilling("Peanut Butter and Jelly", 120);
        pbjSandwich = new Sandwich("PBJ Sandwich", white, pbj);

        Bread french = new Bread("French", 75);
        SandwichFilling blt = new SandwichFilling("Bacon, Lettuce, and Tomato", 200);
        bltSandwich = new Sandwich("BLT Sandwich", french, blt);

        Bread wheat = new Bread("Wheat", 70);
        SandwichFilling banana = new SandwichFilling("Banana", 50);
        bananaSandwich = new Sandwich("Banana", wheat, banana);

        Bread artisan = new Bread("Artisan", 80);
        SandwichFilling cheese = new SandwichFilling("Cheese", 15);
        grilledCheese = new Sandwich("Grilled Cheese", artisan, cheese);

        Bread raisin = new Bread("Raisin", 90);
        SandwichFilling nutella = new SandwichFilling("Nutella", 220);
        nutellaSandwich = new Sandwich("Nutella Sandwich", raisin, nutella);

        bakery = new PackBakery(3);

        bakery2 = new PackBakery(2);
    }

    /**
     * TS test for adding a sandwich to the menu
     */
    @Test
    public void testAddSandwich() {
        assertTrue(bakery.addSandwich(turkeySandwich), "Adds first sandwich");
        assertEquals(turkeySandwich, bakery.getMenu()[0],
            "Checks contents after first sandwich added [0]");
        assertNull(bakery.getMenu()[1], "Checks contents after first sandwich added [1]");
        assertNull(bakery.getMenu()[2], "Checks contents after first sandwich added [2]");
        assertTrue(bakery.addSandwich(bltSandwich), "Adds second sandwich");
        assertEquals(turkeySandwich, bakery.getMenu()[0],
            "Checks contents after second sandwich added [0]");
        assertEquals(bltSandwich, bakery.getMenu()[1],
            "Checks contents after second sandwich added [1]");
        assertNull(bakery.getMenu()[2], "Checks contents after second sandwich added [2]");
        assertTrue(bakery.addSandwich(pbjSandwich), "Adds third sandwich");
        assertEquals(turkeySandwich, bakery.getMenu()[0],
            "Checks contents after third sandwich added [0]");
        assertEquals(bltSandwich, bakery.getMenu()[1],
            "Checks contents after third sandwich added [1]");
        assertEquals(pbjSandwich, bakery.getMenu()[2],
            "Checks contents after third sandwich added [2]");
        assertFalse(bakery.addSandwich(bananaSandwich), "Attempt to add fourth sandwich");
    }

    // FIXED: Add at least 1 additional test for addSandwich
    /**
     * Student test for adding a sandwich to the menu
     */
    @Test
    public void testAddSandwich2() {
        assertTrue(bakery2.addSandwich(grilledCheese), "Adds first sandwich");
        assertEquals(grilledCheese, bakery2.getMenu()[0],
            "Checks contents after first sandwich added [0]");
        assertNull(bakery2.getMenu()[1], "Checks contents after first sandwich added [1]");
        assertTrue(bakery2.addSandwich(nutellaSandwich), "Adds second sandwich");
        assertEquals(grilledCheese, bakery2.getMenu()[0],
             "Checks contents after second sandwich added [0]");
        assertEquals(nutellaSandwich, bakery2.getMenu()[1],
                "Checks contents after second sandwich added [1]");
        assertFalse(bakery2.addSandwich(bananaSandwich), "Attempt to add third sandwich");
    }

    /** 
     * TS test for removing sandwich from the menu
     */
    @Test
    public void testRemoveSandwich() {
        assertTrue(bakery.addSandwich(turkeySandwich), "Add one sandwich");
        assertEquals(turkeySandwich, bakery.getMenu()[0], "One sandwich in list");
        assertTrue(bakery.removeSandwich(turkeySandwich), "Remove only sandwich");
        assertNull(bakery.getMenu()[0], "Sandwich has been removed");

        assertTrue(bakery.addSandwich(turkeySandwich), "Add turkey sandwich");
        assertTrue(bakery.addSandwich(bltSandwich), "Add BLT");
        assertTrue(bakery.addSandwich(pbjSandwich), "Add PB&J sandwich");
        assertFalse(bakery.addSandwich(bananaSandwich), "Cannot add fourth sandwich");

        assertTrue(bakery.removeSandwich(turkeySandwich), "Remove turkey sandwich at index 0");
        assertNull(bakery.getMenu()[0], "Removed item is null");
        assertTrue(bakery.removeSandwich(pbjSandwich), "Remove PB&J sandwich at index 2");
        assertNull(bakery.getMenu()[0], "Removed item is null");
        assertNull(bakery.getMenu()[2], "Removed item is null");
        assertTrue(bakery.removeSandwich(bltSandwich), "Remove BLT at index 1");
        assertNull(bakery.getMenu()[0], "Removed item is null");
        assertNull(bakery.getMenu()[1], "Removed item is null");
        assertNull(bakery.getMenu()[2], "Removed item is null");
        assertFalse(bakery.removeSandwich(bananaSandwich), "Cannot remove sandwich not in bakery");
    }

    // FIXED: Add at least 1 additional test for removeSandwich
    /**
     * Student test for removing sandwich from the menu
     */
    @Test
    public void testRemoveSandwich2() {
        assertTrue(bakery2.addSandwich(grilledCheese), "Add one sandwich");
        assertEquals(grilledCheese, bakery2.getMenu()[0], "One sandwich in list");
        assertTrue(bakery2.removeSandwich(grilledCheese), "Remove only sandwich");
        assertNull(bakery2.getMenu()[0], "Sandwich has been removed");

        assertTrue(bakery2.addSandwich(grilledCheese), "Add grilled cheese");
        assertTrue(bakery2.addSandwich(nutellaSandwich), "Add nutella sandwich");
        assertFalse(bakery2.addSandwich(pbjSandwich), "Cannot add third sandwich");

        assertTrue(bakery2.removeSandwich(grilledCheese), "Remove grilled cheese at index 0");
        assertNull(bakery2.getMenu()[0], "Removed item is null");
        assertTrue(bakery2.removeSandwich(nutellaSandwich), "Remove nutella sandwich at index 1");
        assertNull(bakery2.getMenu()[1], "Removed item is null");
        assertFalse(bakery2.removeSandwich(bananaSandwich), "Cannot remove sandwich not in bakery");
    }

    /**
     * TS test for getting a sandwich
     */
    @Test
    public void testGetSandwich() {
        assertTrue(bakery.addSandwich(turkeySandwich), "Add turkey sandwich");
        assertTrue(bakery.addSandwich(bltSandwich), "Add BLT");
        assertTrue(bakery.addSandwich(pbjSandwich), "Add PB&J");

        assertEquals(turkeySandwich, bakery.getSandwich("Turkey Sandwich"),
            "Turkey Sandwich for get method");
        assertEquals(pbjSandwich, bakery.getSandwich("PBJ Sandwich"),
            "PBJ Sandwich for get method");
        assertEquals(bltSandwich, bakery.getSandwich("BLT Sandwich"),
            "BLT Sandwich for get method");

        assertNull(bakery.getSandwich("Banana Sandwich"), "Get sandwich not in bakery");
    }

    // FIXED: Add at least 1 additional test for getSandwich
    /**
     * Student test for getting a sandwich
     */
    @Test
    public void testGetSandwich2() {
        assertTrue(bakery2.addSandwich(grilledCheese), "Add grilledCheese");
        assertTrue(bakery2.addSandwich(nutellaSandwich), "Add nutella sandwich");

        assertEquals(grilledCheese, bakery2.getSandwich("Grilled Cheese"),
            "Grilled Cheese for get method");
        assertEquals(nutellaSandwich, bakery2.getSandwich("Nutella Sandwich"),
            "Nutella Sandwich for get method");

        assertNull(bakery2.getSandwich("Banana Sandwich"), "Get sandwich not in bakery");
    }

    /**
     * TS test for the constructor methods
     */
    @Test
    public void testConstructor() {
        Sandwich[] menu = bakery.getMenu();
        assertEquals(3, menu.length);
        assertNull(bakery.getMenu()[0], "Checks contents with constructor with 3 [0]");
        assertNull(bakery.getMenu()[1], "Checks contents with constructor with 3 [1]");
        assertNull(bakery.getMenu()[2], "Checks contents with constructor with 3 [2]");
        bakery = new PackBakery();
        menu = bakery.getMenu();
        assertEquals(3, menu.length);
        assertNull(bakery.getMenu()[0], "Checks contents with constructor with no parameter [0]");
        assertNull(bakery.getMenu()[1], "Checks contents with constructor with no parameter [1]");
        assertNull(bakery.getMenu()[2], "Checks contents with constructor with no parameter [2]");
        bakery = new PackBakery(4);
        menu = bakery.getMenu();
        assertEquals(4, menu.length);
        assertNull(bakery.getMenu()[0], "Checks contents with constructor with 4 [0]");
        assertNull(bakery.getMenu()[1], "Checks contents with constructor with 4 [1]");
        assertNull(bakery.getMenu()[2], "Checks contents with constructor with 4 [2]");
        assertNull(bakery.getMenu()[3], "Checks contents with constructor with 4 [3]");
    }
}
