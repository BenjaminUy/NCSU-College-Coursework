import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Piece class
 * 
 * @author Noah Evans Lehner
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 * @author Aman Anas
 */
public class PieceTest {

    /** Piece X for testing */
    private Piece xPiece;

    /** Piece O for testing */
    private Piece oPiece;

    /**
     * Create Piece for testing
     */
    @BeforeEach
    public void setUp() {
        xPiece = new Piece('X');
        oPiece = new Piece('O');
    }

    /**
     * Test getType
     */
    @Test
    public void testGetType() {
        assertEquals('X', xPiece.getType(), "xPiece type");
        assertEquals('O', oPiece.getType(), "oPiece type");
    }

    /** Test toString method */
    @Test
    public void testToString() {
        assertEquals("X", xPiece.toString(), "xPiece type to String");
        assertEquals("O", oPiece.toString(), "oPiece type to String");
    }

    /**
     * Test equals for xPiece
     */
    @Test
    public void testEqualsxPiece() {
        assertTrue(xPiece.equals(xPiece), "xPiece equals with same instance");
        assertTrue(xPiece.equals(new Piece('X')), "xPiece equals with different instances");
        assertFalse(xPiece.equals(new Piece('O')), "xPiece with different type");
        assertFalse(xPiece.equals(null), "xPiece compared to null object");
        assertFalse(xPiece.equals("xPiece"), "xPiece compared to String");
    }

    /**
     * Test equals for oPiece
     */
    @Test
    public void testEqualsoPiece() {
        assertTrue(oPiece.equals(oPiece), "oPiece equals with same instance");
        assertTrue(oPiece.equals(new Piece('O')), "oPiece equals with different instances");
        assertFalse(oPiece.equals(new Piece('X')), "oPiece with different type");
        assertFalse(oPiece.equals(null), "O compared to null object");
        assertFalse(oPiece.equals("O"), "oPiece compared to String");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        // Testing constructor with a null character
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> new Piece('\0'),
                "Constructor type null character");
        assertEquals("Type is a null character", exception.getMessage(),
                "Testing null character type message");
    }
}