import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Symbol class
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Benjamin Uy
 */
public class SymbolTest { 

    /** Symbol giraffe for testing */
    private Symbol giraffe;

    /** Symbol penguin for testing */
    private Symbol penguin;

    /**
     * Create Symbols for testing
     */
    @BeforeEach
    public void setUp() {
        giraffe = new Symbol("giraffe", 25);
        penguin = new Symbol("penguin", 30);
    }

    /**
     * Test getName for giraffe Symbol
     */
    @Test
    public void testGetNameGiraffe() {
        assertEquals("giraffe", giraffe.getName(), "giraffe name");
    }    

    /**
     * (BU) Test getName for penguin Symbol
     */
    @Test
    public void testGetNamePenguin() {
        assertEquals("penguin", penguin.getName(), "penguin name");
    }

    /**
     * Test getPoints for giraffe Symbol
     */
    @Test
    public void testGetPointsGiraffe() {
        assertEquals(25, giraffe.getPoints(), "giraffe points");
    }     

    /**
     * (BU) Test getPoints for penguin Symbol
     */
    @Test
    public void testGetPointsPenguin() {
        assertEquals(30, penguin.getPoints(), "penguin points");
    }

    /**
     * Test toString for giraffe Symbol
     */
    @Test
    public void testToStringGiraffe() {
        assertEquals("giraffe 25 false", giraffe.toString(), "giraffe toString");        
    }

    /**
     * (BU) Test toString for penguin Symbol
     */
    @Test
    public void testToStringPenguin() {
        assertEquals("penguin 30 false", penguin.toString(), "penguin toString");
    }

    /**
     * Test hasBeenClickedOn for giraffe Symbol
     */
    @Test
    public void testHasBeenClickedOnGiraffe() {
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe hasBeenClickedOn");
    }

    /**
     * (BU) Test hasBeenClickedOn for penguin Symbol
     */
    @Test
    public void testHasBeenClickedOnPenguin() {
        assertFalse(penguin.hasBeenClickedOn(), "penguin hasBeenClickedOn");
    }

    /**
     * Test setHasBeenClickedOn for giraffe Symbol
     */
    @Test
    public void testSetHasBeenClickedOnGiraffe() {
        giraffe.setHasBeenClickedOn(true);
        assertTrue(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn true");
        giraffe.setHasBeenClickedOn(false);
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn false");
    }

    /**
     * (BU) Test setHasBeenClickedOn for penguin Symbol
     */
    @Test
    public void testSetHasBeenClickedOnPenguin() {
        penguin.setHasBeenClickedOn(true);
        assertTrue(penguin.hasBeenClickedOn(), "penguin setHasBeenClickedOn true");
        penguin.setHasBeenClickedOn(false);
        assertFalse(penguin.hasBeenClickedOn(), "penguin setHasBeenClickedOn false");
    }

    /**
     * Test equals for giraffe Symbol
     */
    @Test
    public void testEqualsGiraffe() {
        assertTrue(giraffe.equals(giraffe), "giraffe equals with same instance");
        assertTrue(giraffe.equals(new Symbol("giraffe", 25)), 
                   "giraffe equals with different instances");
        assertFalse(giraffe.equals(new Symbol("cow", 25)), "giraffe with different name");
        assertFalse(giraffe.equals(new Symbol("giraffe", 4)), "giraffe with different points");
        assertFalse(giraffe.equals(new Symbol("horse", 5)), 
                    "giraffe with different name and points");
        assertFalse(giraffe.equals(null), "giraffe compared to null object");
        assertFalse(giraffe.equals("giraffe"), "giraffe compared to String");
    }

    /**
     * (BU) Test equals for penguin Symbol
     */
    @Test
    public void testEqualsPenguin() {
        assertTrue(penguin.equals(penguin), "penguin equals with same instnace");
        assertTrue(penguin.equals(new Symbol("penguin", 30)),
                    "penguin equals with different instances");
        assertFalse(penguin.equals(new Symbol("giraffe", 30)), "penguin with different name");
        assertFalse(penguin.equals(new Symbol("penguin", 25)), "penguin with different points");
        assertFalse(penguin.equals(new Symbol("horse", 10)),
                    "penguin with different name and points");
        assertFalse(penguin.equals(null), "penguin compared to null object");
        assertFalse(penguin.equals("penguin"), "penguin compared to String");
    }

    /**
     * (BU) Test equals and setHasBeenClickedOn for penguin Symbol
     */
    @Test
    public void testEqualsAndSetHasBeenClickedOnPenguin() {
        penguin.setHasBeenClickedOn(true);
        assertTrue(penguin.hasBeenClickedOn(), "penguin setHasBeenClickedOn true");
        assertFalse(penguin.equals(new Symbol("penguin", 30)),
                    "penguin with different hasBeenClickedOn status");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        
        // Testing constructor with null name
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol(null, 1), "Constructor name null");
        assertEquals("Null name", exception.getMessage(),
                "Testing null name message");
                
        // Testing constructor with 0 points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("snake", 0), "Constructor points 0");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing points 0 message");
                
        // Testing constructor with negative points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("frog", -5), "Constructor points -5");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing negative points message");
                
    }

}