import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Connect4Game class
 * 
 * @author Noah Evans Lehner
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 * @author Aman Anas
 */
public class Connect4GameTest {

    /** Piece X for testing */
    private Piece xPiece;

    /** Piece O for testing */
    private Piece oPiece;

    /** Connect4Game object for testing */
    private Connect4Game game;

    /**
     * Create Piece for Testing
     */
    @BeforeEach
    public void setUp() {
        xPiece = new Piece('X');
        oPiece = new Piece('O');
        game = new Connect4Game(4);
    }

    /**
     * Test getRows method
     */
    @Test
    public void testGetRows() {
        assertEquals(8, game.getRows(), "8 rows in Connect4 game");
    }

    /**
     * Test getCols method
     */
    @Test
    public void testGetCols() {
        assertEquals(8, game.getCols(), "8 cols in Connect4 game");
    }

    /**
     * Test getMaxToConnect method
     */
    @Test
    public void testGetMaxToConnect() {
        assertEquals(4, game.getMaxToConnect(), "4 is the maxToConnect");
    }

    /**
     * Test getNumOfMoves method
     */
    @Test
    public void testGetNumOfMoves() {
        assertEquals(0, game.getNumOfMoves(), "0 is numOfMoves at start of game");
    }

    /**
     * Test getGameFinished method
     */
    @Test
    public void testGetGameFinished() {
        assertFalse(game.getGameFinished(), "Check if game is finished at the start");
    }

    /**
     * Test getGameDraw method
     */
    @Test
    public void testGetGameDraw() {
        assertFalse(game.getGameDraw(), "Check if game is at a draw at the start");
    }

    /**
     * Test getMaxConnectedType and placePiece
     */
    @Test
    public void testGetMaxConnectedTypeAndPlacePiece() {
        assertTrue(game.placePiece(0, oPiece), "Add first piece");
        assertEquals(1, game.getMaxConnectedType('O'), "Find max connected to O");
        assertTrue(game.placePiece(0, xPiece), "Add second piece above first");
        assertEquals(1, game.getMaxConnectedType('O'), 
            "Find max connected to first piece after other piece is placed");
        assertTrue(game.placePiece(1, oPiece), "Add third piece");
        assertTrue(game.placePiece(2, oPiece), "Add fourth piece");
        assertEquals(3, game.getMaxConnectedType('O'),
            "Max connected is three in a horizontal line"); 
        assertTrue(game.placePiece(4, oPiece), "Add fifth piece away from the others");
        assertEquals(3, game.getMaxConnectedType('O'),
            "Max connected is still three in a horizontal line");
    }

    /**
     * Test getNumPlacedType and placePiece
     */
    @Test
    public void testGetNumPlacedTypeAndPlacePiece() {
        assertEquals(0, game.getNumPlacedType('O'), "Find number placed for O");
        assertTrue(game.placePiece(0, oPiece), "Add first piece");
        assertEquals(1, game.getNumPlacedType('O'), "Find number placed for O");
        assertTrue(game.placePiece(1, oPiece), "Add second piece");
        assertEquals(2, game.getNumPlacedType('O'), "Find number placed for O");
        assertEquals(0, game.getNumPlacedType('X'), "Find number placed for X");
        assertTrue(game.placePiece(0, xPiece), "Add first piece");
        assertEquals(1, game.getNumPlacedType('X'), "Find number placed for X");
        assertTrue(game.placePiece(1, xPiece), "Add second piece");
        assertEquals(2, game.getNumPlacedType('X'), "Find number placed for X");
    }

    /**
     * Test placePiece method
     */
    @Test
    public void testPlacePiece() {
        assertTrue(game.placePiece(1, xPiece), "Add first piece");
        assertTrue(game.placePiece(1, xPiece), "Add second piece");
        assertTrue(game.placePiece(1, xPiece), "Add third piece");
        assertTrue(game.placePiece(1, xPiece), "Add fourth piece");
        assertTrue(game.placePiece(1, xPiece), "Add fifth piece");
        assertTrue(game.placePiece(1, xPiece), "Add sixth piece");
        assertTrue(game.placePiece(1, xPiece), "Add seventh piece");
        assertTrue(game.placePiece(1, xPiece), "Add eighth piece");
        assertFalse(game.placePiece(1, xPiece), "Unable to add piece in col 1");
        assertTrue(game.placePiece(2, xPiece), "Can add piece in another col ");
    }

    /**
     * Test placePiece, getPieceAtPos
     */
    @Test
    public void testPlacePieceAndGetPieceAtPos() {
        assertTrue(game.placePiece(5, xPiece), "Add first piece");
        assertTrue(game.placePiece(5, oPiece), "Add second piece");
        assertEquals(xPiece, game.getPieceAtPos(5, 0), "Get piece at col 5, row 0");
        assertEquals(oPiece, game.getPieceAtPos(5, 1), "Get piece at Col 5, row 1");
        assertEquals(null, game.getPieceAtPos(5, 2), "Get null piece at col 5, row 2");
    }

    /**
     * Test placePiece and findConnectDir
     */
    @Test
    public void testPlacePieceAndFindConnectedDir() {
        assertTrue(game.placePiece(0, xPiece), "Add first piece");
        assertTrue(game.placePiece(1, xPiece), "Add second piece");
        assertTrue(game.placePiece(2, xPiece), "Add third piece");
        assertEquals(3, game.findConnectedDir(0, 0, 1, 0), "Find connected pieces in horizontal");
        assertEquals(1, game.findConnectedDir(0, 0, 0, 1), "Find connected pieces in vertical");
    }

    /**
     * Test getMaxConnectedToPiece and placePiece
     */
    @Test
    public void testGetMaxConnectedToPieceAndPlacePiece() {
        assertTrue(game.placePiece(0, oPiece), "Add first piece");
        assertEquals(1, game.getMaxConnectedToPiece(0, 0), "Find max connected to first piece");
        assertTrue(game.placePiece(0, xPiece), "Add second piece above first");
        assertEquals(1, game.getMaxConnectedToPiece(0, 0), 
            "Find max connected to first piece after other piece is placed");
        assertTrue(game.placePiece(1, oPiece), "Add third piece");
        assertTrue(game.placePiece(2, oPiece), "Add fourth piece");
        assertEquals(3, game.getMaxConnectedToPiece(0, 0),
            "Max connected is three in a horizontal line"); 
        assertTrue(game.placePiece(4, oPiece), "Add fifth piece away from the others");
        assertEquals(3, game.getMaxConnectedToPiece(0, 0),
            "Max connected is still three in a horizontal line"); 
    }

    /**
     * Test pieceExists and placePiece
     */
    @Test
    public void testPieceExistsAndPlacePiece() {
        assertFalse(game.pieceExists(0, 0), "No piece exists at start");
        assertTrue(game.placePiece(0, xPiece), "Add first piece");
        assertTrue(game.pieceExists(0, 0), "Piece now exists at row 0, col 0");
    }

    /**
     * Test getPieceAtPos and placePiece
     */
    @Test
    public void testGetPieceAtPosAndPlacePiece() {
        assertEquals(null, game.getPieceAtPos(0, 0), "Gets null piece at the start");
        assertTrue(game.placePiece(0, xPiece), "Add first piece");
        assertEquals(xPiece, game.getPieceAtPos(0, 0), "Get X piece at row 0, col 0");
    }

    /**
     * Test piecesToString and placePiece
     */
    @Test
    public void testPiecesToString() {
        assertEquals("-----------------\n" + 
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     "-----------------\n",
                    game.piecesToString(), "Get string display for empty grid");
        assertTrue(game.placePiece(0, xPiece), "Add first piece");
        assertTrue(game.placePiece(0, oPiece), "Add second piece");
        assertEquals("-----------------\n" + 
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " - - - - - - - - \n" +
                     " O - - - - - - - \n" +
                     " X - - - - - - - \n" +
                     "-----------------\n",
                    game.piecesToString(), "Get string display for non-empty grid");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {
        // Testing Connect4Game Constructor with maxToConnect less than 4
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> new Connect4Game(-1),
                "Connect4Game Constructor with maxToConnect less than 4");
        assertEquals("Invalid maxToConnect",
            exception.getMessage(), "Testing maxToConnect less than 4");

        // Testing Connect4Game Constructor with maxToConnect greater than 10
        exception = assertThrows(
                IllegalArgumentException.class, () -> new Connect4Game(11),
                "Connect4Game Constructor with maxToConnect greater than 10");
        assertEquals("Invalid maxToConnect",
            exception.getMessage(), "Testing maxToConnect greater than 10");

        // Testing getMaxConnectedType with a null character
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedType('\0'),
                "getMaxConnectedType null character");
        assertEquals("Type is a null character", exception.getMessage(),
                "Testing null character type message");

        // Testing getNumPlacedType with a null character
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getNumPlacedType('\0'),
                "getNumPlacedType null character");
        assertEquals("Type is a null character", exception.getMessage(),
                "Testing null character type message");
        
        // Testing findConnectedDir with no piece at start position
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(0, 0, 0, 0),
                "findConnectedDir with no piece at start position");
        assertEquals("Invalid position, no piece exists at start position",
            exception.getMessage(), "Testing no piece exists at start position");

        // Testing findConnectedDir with negative col
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(-1, 0, 0, 0),
                "findConnectedDir with negative col");
        assertEquals("Invalid col", exception.getMessage(), "Testing with negative col");

        // Testing findConnectedDir with col equal to num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(8, 0, 0, 0),
                "findConnectedDir with col equal to num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col equal to total cols");

        // Testing findConnectedDir with col greater than num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(9, 0, 0, 0),
                "findConnectedDir with col greater than num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col greater than total cols");

        // Testing findConnctedDir with negative row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(0, -1, 0, 0),
                "findConnectedDir with invalid row");
        assertEquals("Invalid row", exception.getMessage(), "Testing with invalid row");

        // Testing findConnectedDir with row equal to num of row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(0, 8, 0, 0),
                "findConnectedDir with row equal to num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row equal to total rows");

        // Testing findConnectedDir with row greater than num of rows
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.findConnectedDir(0, 9, 0, 0),
                "findConnectedDir with row greater than num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row greater than total rows");
            
        // Testing getMaxConnectedToPiece with no piece at start position
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(5, 5),
                "getMaxConnectedToPiece with no piece at start position");
        assertEquals("Invalid position, no piece exists at start position",
            exception.getMessage(), "Testing no piece exists at start position");

        // Testing getMaxConnectedToPiece with negative col
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(-1, 0),
                "getMaxConnectedToPiece with negative col");
        assertEquals("Invalid col", exception.getMessage(), "Testing with negative col");

        // Testing getMaxConnectedToPiece with col equal to num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(8, 0),
                "getMaxConnectedToPiece with col equal to num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col equal to total cols");

        // Testing getMaxConnectedToPiece with col greater than num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(9, 0),
                "getMaxConnectedToPiece with col greater than num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col greater than total cols");

        // Testing getMaxConnectedToPiece with negative row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(0, -1),
                "getMaxConnectedToPiece with invalid row");
        assertEquals("Invalid row", exception.getMessage(), "Testing with invalid row");

        // Testing getMaxConnectedToPiece with row equal to num of row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(0, 8),
                "getMaxConnectedToPiece with row equal to num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row equal to total rows");

        // Testing getMaxConnectedToPiece with row greater than num of rows
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getMaxConnectedToPiece(0, 9),
                "getMaxConnectedToPiece with row greater than num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row greater than total rows");

        // Testing pieceExists with negative col
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(-1, 0),
                "pieceExists with negative col");
        assertEquals("Invalid col", exception.getMessage(), "Testing with negative col");

        // Testing pieceExists with col equal to num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(8, 0),
                "pieceExists with col equal to num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col equal to total cols");

        // Testing pieceExists with col greater than num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(9, 0),
                "pieceExists with col greater than num of cols");
        assertEquals("Invalid col", exception.getMessage(), 
                "Test with col greater than total cols");

        // Testing pieceExists with negative row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(0, -1),
                "pieceExists with invalid row");
        assertEquals("Invalid row", exception.getMessage(), "Testing with invalid row");

        // Testing pieceExists with row equal to num of row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(0, 8),
                "pieceExists with row equal to num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row equal to total rows");

        // Testing pieceExists with row greater than num of rows
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.pieceExists(0, 9),
                "pieceExists with row greater than num of rows");
        assertEquals("Invalid row", exception.getMessage(), 
                "Test with row greater than total rows");

        // Testing getPieceAtPos with negative col
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(-1, 0),
                "getPieceAtPos with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with negative col");

        // Testing getPieceAtPos with col equal to num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(8, 0),
                "getPieceAtPos with col equal to num of cols");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with col equal to num of cols");

        // Testing getPieceAtPos with col greater than num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(9, 0),
                "getPieceAtPos with col greater than num of cols");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with col greater than num of cols");
        
        // Testing getPieceAtPos with negative row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(0, -1),
                "getPieceAtPos with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Test with negative row");

        // Testing getPieceAtPos with row equal to num of row
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(0, 8),
                "getPieceAtPos with row equal to num of rows");
        assertEquals("Invalid row", exception.getMessage(),
                "Test with row equal to num of rows");

        // Testing getPieceAtPos with row greater than num of rows
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.getPieceAtPos(0, 9),
                "getPieceAtPos with row greater than num of row");
        assertEquals("Invalid row", exception.getMessage(),
                "Test with row greater than num of rows");

        // Testing placePiece with negative col
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.placePiece(-1, xPiece),
                "placePiece with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with negative col");

        // Testing placePiece with col equal to num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.placePiece(8, xPiece),
                "placePiece with col equal to num of cols");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with col equal to num of cols");

        // Testing placePiece with col greater than num of cols
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.placePiece(9, xPiece),
                "placePiece with col greater than num of cols");
        assertEquals("Invalid col", exception.getMessage(),
                "Test with col greater than num of cols");

        // Testing placePiece with null s
        exception = assertThrows(
                IllegalArgumentException.class, () -> game.placePiece(0, null),
                "placePiece with null s");
        assertEquals("Null s", exception.getMessage(),
                "Test with null s");
    }
}