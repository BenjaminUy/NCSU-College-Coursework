import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Tests WhackaMole class
 * @author Suzanne Balik
 ^ @author Michelle Glatz
 * @author
 */
public class WhackaMoleTest  {
    
   
    /** WhackaMole instance used for testing */
    private WhackaMole whackaMole;

    /**
     * Create new WhackaMole instance for testing
     */
    @BeforeEach
    public void setUp() {
        whackaMole = new WhackaMole(true);
    }

    /**
     * Test to make sure the required constants are correct
     */
    @Test
    public void testConstants() {
        // The following test tests that required constants are defined as specified
        assertEquals(5, WhackaMole.ROWS, "ROWS");
        assertEquals(5, WhackaMole.COLS, "COLS");
        String[][] symbolNames = {{"cat", "dog", "tiger", "frog", "cat"}, 
                                  {"tiger", "lion", "dog", "tiger", "frog"},
                                  {"lion", "frog", "mole",  "dog", "cat"},
                                  {"frog", "dog", "tiger", "cat", "lion"},
                                  {"cat", "frog", "lion", "dog", "tiger"}};
                                  
        assertTrue(Arrays.deepEquals(symbolNames,WhackaMole.SYMBOL_NAMES), "SYMBOL_NAMES");
        int[][] symbolPoints = {{10, 15, 30, 20, 10},      
                                {30, 40, 15, 30, 20},      
                                {40, 20, 50, 15, 10},      
                                {20, 15, 30, 10, 40},      
                                {10, 20, 40, 15, 30}};     
        assertTrue(Arrays.deepEquals(symbolPoints,WhackaMole.SYMBOL_POINTS), "SYMBOL_POINTS" );
    }

    /**
     * Test constructor methods
     */
    @Test
    public void testConstructor() {
        assertEquals(0, whackaMole.getTotalScore(), "Initial total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "Initial number of misses");
        assertEquals(0, whackaMole.getNextRow(), "Initial next row");
        assertEquals(0, whackaMole.getNextCol(), "Initial next col");
        assertEquals("cat", whackaMole.getSymbolName(0,0), "Correct symbol at 0 0");
        assertEquals("lion", whackaMole.getSymbolName(2,0), "Correct symbol at 2 0");
        assertFalse(whackaMole.hasBeenClickedOn(2, 4), "Not clicked on at 2 4");
    }

    /**
     * Test getGrid method
     */
    @Test
    public void testGrid() {
        Grid grid = whackaMole.getGrid();
        String exp = "cat dog tiger frog cat\n" +
                     "tiger lion dog tiger frog\n" +
                     "lion frog mole dog cat\n" +
                     "frog dog tiger cat lion\n" +
                     "cat frog lion dog tiger\n";
        assertEquals(exp, grid.toString(), "Grid is correct");
    }

    /**
     * Test clickOnSymbol method for a single Symbol
     */
    @Test
    public void testClickOnSymbolAndUpdateNextRowAndCol() {
        whackaMole.clickOnSymbol(0,0);
        assertTrue(whackaMole.hasBeenClickedOn(0,0), "Click on one symbol has been clicked on");
        assertEquals(10, whackaMole.getTotalScore(), "Click on one symbol total score");
        assertEquals(0, whackaMole.getNextRow(), "Click on one symbol next row");
        assertEquals(1, whackaMole.getNextCol(), "Click on one symbol next col"); 
    }

    /**
     * Test addMiss and updateNextRowAndCol method
     */
    @Test
    public void testAddMissAndUpdateNextRowAndCol() {
        whackaMole.addMiss();
        assertEquals(0, whackaMole.getNextRow(), "Miss  one symbol next row");
        assertEquals(1, whackaMole.getNextCol(), "Miss one symbol next col"); 
    }

    /**
     * Test allSymbolsClickedOn starting with no Symbols clicked on previously
     */
    @Test
    public void testallSymbolsClickedOn() {
        assertFalse(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn no clicks");
        for (int i = 0; i < 5; i++) {
            assertFalse(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn some clicks");
            for (int j = 0; j < 5; j++) {
                whackaMole.clickOnSymbol(i,j);
            }
        }
        assertTrue(whackaMole.allSymbolsClickedOn(), "allSymboksClickedOn all clicks");
        assertEquals(585, whackaMole.getTotalScore(), "allSymboksClickedOn total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "allSymboksClickedOn no misses");
    }

    /**
     * Test game play with two misses and three non-misses
     */
    @Test
    public void testPlayWhackaMole1() {
        whackaMole.clickOnSymbol(0,0);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(2,0);
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(0,4);
        assertEquals(60, whackaMole.getTotalScore(), "Play WhackaMole 1 total score");
        assertEquals(2, whackaMole.getNumberOfMisses(), "Play WhackaMole 1 number of misses");
    }

    /**
     * (BU) Test game play with no misses
     */
    @Test
    public void testPlayWhackaMole2() {
        whackaMole.clickOnSymbol(0, 0);
        whackaMole.clickOnSymbol(0, 1);
        whackaMole.clickOnSymbol(1, 0);
        whackaMole.clickOnSymbol(4, 4);
        whackaMole.clickOnSymbol(3, 2);
        assertEquals(115, whackaMole.getTotalScore(), "Play WhackaMole2 total score");
        assertEquals(0, whackaMole.getNumberOfMisses(), "Play WhackaMole2 number of misses");
    }

    /**
     * (BU) Test game play with all misses
     */
    @Test
    public void testPlayWhackaMole3() {
        whackaMole.addMiss();
        whackaMole.addMiss();
        whackaMole.addMiss();
        whackaMole.addMiss();
        whackaMole.addMiss();
        assertEquals(0, whackaMole.getTotalScore(), "Play WhackaMole3 total score");
        assertEquals(5, whackaMole.getNumberOfMisses(), "Play WhackaMole3 number of misses");
    }

    /**
     * (BU) Test game play with four misses, one non-miss
     */
    @Test
    public void testPlayWhackaMole4() {
        whackaMole.addMiss();
        whackaMole.addMiss();
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(2, 2);
        whackaMole.addMiss();
        assertEquals(50, whackaMole.getTotalScore(), "Play WhackaMole4 total score");
        assertEquals(4, whackaMole.getNumberOfMisses(), "Play WhackaMole4 number of misses");
    }

    /**
     * (BU) Test game play with four non-misses, one miss
     */
    @Test
    public void testPlayWhackaMole5() {
        whackaMole.clickOnSymbol(2, 2);
        whackaMole.clickOnSymbol(1, 1);
        whackaMole.clickOnSymbol(2, 0); 
        whackaMole.addMiss();
        whackaMole.clickOnSymbol(4, 2); 
        assertEquals(170, whackaMole.getTotalScore(), "Play WhackaMole5 total score");
        assertEquals(1, whackaMole.getNumberOfMisses(), "Play WhackaMole5 number of misses");
    }

    /**
     * (BU) Test game play with one non-miss, four clicking same symbol
     */
    @Test
    public void testPlayWhackaMole6() {
        whackaMole.clickOnSymbol(0, 0);
        assertEquals(10, whackaMole.getTotalScore(), "Play WhackMole6 score after first symbol");
        whackaMole.clickOnSymbol(0, 0);
        whackaMole.clickOnSymbol(0, 0);
        whackaMole.clickOnSymbol(0, 0);
        whackaMole.clickOnSymbol(0, 0);
        assertEquals(10, whackaMole.getTotalScore(), 
            "Play WhackaMole6 and checking total score after clicking same symbol four times");
    }
       
    
    /**
     * Tests exceptions 
     */
    @Test
    public void testExceptions() {       

        // Testing getSymbolName with row too large
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(5, 0), "getSymbolName with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolName Invalid row message with row too large");
                
 
        // Testing getSymbolName with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(-1, 4), "getSymbolName with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolName Invalid row message with negative row");                
 
         // Testing getSymbolName with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(4, 5), "getSymbolName with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolName Invalid col message with col too large");
                
                
        // Testing getSymbolName with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolName(0, -1), "getSymbolName with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolName Invalid col message with negative col");                

 
        // Testing getSymbolPoints with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(5, 0), "getSymbolPoints with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolPoints Invalid row message with row too large");
                
 
        // Testing getSymbolPoints with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(-1, 4), "getSymbolPoints with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getSymbolPoints Invalid row message with negative row");                
 
         // Testing getSymbolName with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(4, 5), "getSymbolPoints with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolPoints Invalid col message with col too large");
                
 
        // Testing getSymbolPoints with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.getSymbolPoints(0, -1), "getSymbolPoints with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getSymbolPoints Invalid col message with negative col");
    
    
        // Testing hasBeenClickedOn with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(5, 0), "hasBeenClickedOn with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid row message with row too large");
                
 
        // Testing hasBeenClickedOn with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(-1, 4), "hasBeenClickedOn with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid row message with negative row");                
 
         // Testing hasBeenClickedOn with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(4, 5), "hasBeenClickedOn with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid col message with col too large");
                
 
        // Testing hasBeenClickedOn with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.hasBeenClickedOn(0, -1), "hasBeenClickedOn with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing hasBeenClickedOn Invalid col message with negative col");


         // Testing clickOnSymbol with row too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(5, 0), "clickOnSymbol with row too large");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing clickOnSymbol Invalid row message with row too large");
                
 
        // Testing clickOnSymbol with negative row
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(-1, 4), "clickOnSymbol with negative row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing clickOnSymbol Invalid row message with negative row");                
 
         // Testing clickOnSymbol with col too large
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(4, 5), "clickOnSymbol with col too large");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing clickOnSymbol Invalid col message with col too large");
                
 
        // Testing clickOnSymbol with negative col
        exception = assertThrows(IllegalArgumentException.class,
            () -> whackaMole.clickOnSymbol(0, -1), "clickOnSymbol with negative col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing clickOnSymbol Invalid col message with negative col");                 
                   
    }    

}