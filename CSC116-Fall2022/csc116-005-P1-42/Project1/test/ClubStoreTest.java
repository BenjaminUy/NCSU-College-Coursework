import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test ClubStore methods
 * @author Michelle Glatz
 * @author Benjamin Uy
 */
public class ClubStoreTest {

    /**
     * Tests isValidDate with month = 10 and day = 23
     */
    @Test
    public void testIsValidDateValidOctNonBoundary() {
        assertTrue(ClubStore.isValidDate(10, 23), "ClubStore.isValidDate(10, 23)");
    }
    
    /**
     * Tests isValidDate with month = 10 and day = 14
     */
    @Test
    public void testIsValidDateInvalidOctBoundary() {
        assertFalse(ClubStore.isValidDate(10, 14), "ClubStore.isValidDate(10, 14)");
    }
    
    /**
     * Tests isValidDate with month = 9 and day = 30
     */
    @Test
    public void testIsValidDateInvalidMonth() {
        assertFalse(ClubStore.isValidDate(9, 30), "ClubStore.isValidDate(9, 30)");
    } 
    
    /**
     * Tests isValidDate with month = 11 and day = -1
     */
    @Test
    public void testIsValidDateNovDayNegative() {
        assertFalse(ClubStore.isValidDate(11, -1), "ClubStore.isValidDate(11, -1)");
    }

    /**
     * Tests isValidDate with month = 0 and day = 1
     */
    @Test
    public void testIsValidDateMonthZero() {
        assertFalse(ClubStore.isValidDate(0, 1), "ClubStore.isValidDate(0, 1)");
    }  

    /**
     * Tests isValidDate with month = -1 and day = 1
     */
    @Test
    public void testIsValidDateMonthNegative() {
        assertFalse(ClubStore.isValidDate(-1, 1), "ClubStore.isValidDate(-1, 1)");
    }        

    /**
     * Tests isValidDate with month = 11 and day = 1
     */
    @Test
    public void testIsValidDateNov1() {
        assertTrue(ClubStore.isValidDate(11, 1), "ClubStore.isValidDate(11, 1)");
    }

    /**
     * Tests isValidDate with month = 10 and day = 31
     */
    @Test
    public void testIsValidDateOct31() {
        assertTrue(ClubStore.isValidDate(10, 31), "ClubStore.isValidDate(10, 31)");
    } 

    /**
     * Tests isValidDate with month = 11 and day = 30
     */    
    @Test
    public void testIsValidDateNov30() {
        assertTrue(ClubStore.isValidDate(11, 30), "ClubStore.isValidDate(11, 30)");
    }

    /**
     * Tests isValidDate with month = 11 and day = 23
     */        
    @Test
    public void testIsValidDateNovNonBoundary() {
        assertTrue(ClubStore.isValidDate(11, 23), "ClubStore.isValidDate(11, 23)");
    }

    /**
     * Tests isValidDate with month = 10 and day = 15
     */      
    @Test
    public void testIsValidDateOctober15() {
        assertTrue(ClubStore.isValidDate(10, 15), "ClubStore.isValidDate(10, 15)");
    } 

    /**
     * Tests isValidDate with month = 11 and day = 0
     */          
    @Test
    public void testIsValidDateNovDayZero() {
        assertFalse(ClubStore.isValidDate(11, 0), "ClubStore.isValidDate(11, 0)");
    }  

    /**
     * Tests isValidDate with month = 11 and day = 31
     */          
    @Test
    public void testIsValidDateNov31() {
        assertFalse(ClubStore.isValidDate(11, 31), "ClubStore.isValidDate(11, 31)");
    }        

    /**
     * Tests getSubtotal with one water bottle ordered
     */ 
    @Test
    public void testGetSubtotalOneBottle() {
        assertEquals(10, ClubStore.getSubtotal(1, 0, 0),
                     "ClubStore.getSubtotal(1, 0, 0)");
    }

    /**
     * Tests getSubtotal with no items ordered
     */ 
    @Test
    public void testGetSubtotalNoItems() {
        assertEquals(0, ClubStore.getSubtotal(0, 0, 0), 
                     "ClubStore.getSubtotal(0, 0, 0)");
    }
   
    /**
     * Tests getSubtotal with one coffee mug ordered
     */ 
    @Test
    public void testGetSubtotalOneMug() {
        assertEquals(12, ClubStore.getSubtotal(0, 1, 0), 
            "ClubStore.getSubtotal(0, 1, 0)");
    }
    
    /**
     * Tests getSubtotal with one tote bag ordered
     */ 
    @Test
    public void testGetSubtotalOneToteBag() {
        assertEquals(18, ClubStore.getSubtotal(0, 0, 1), 
            "ClubStore.getSubtotal(0, 0, 1)");
    }  

    /**
     * Tests getSubtotal with one water bottle, one coffee mug, and one tote bag ordered
     */ 
    @Test
    public void testGetSubtotalOneOfEachItem() {
        assertEquals(40, ClubStore.getSubtotal(1, 1, 1), 
            "ClubStore.getSubtotal(1, 1, 1)");
    }

    /**
     * Tests getShippingCost with 0 for subtotal, false for twoDayShipping, and 
     * false for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubtotal0() {
        assertEquals(0, ClubStore.getShippingCost(0, false, false), 
                     "ClubStore.getShippingCost(0, false, false)");   
    }

    /**
     * Tests getShippingCost with 10 for subtotal, false for twoDayShipping, and 
     * true for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubtotal10CouponStandard() {
        assertEquals(0, ClubStore.getShippingCost(10, false, true), 
                     "ClubStore.getShippingCost(10, false, true)");        
    }

    /**
     * Tests getShippingCost with 25 for subtotal, false for twoDayShipping, and 
     * false for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubtotal25Standard() {
        assertEquals(0, ClubStore.getShippingCost(125, false, false), 
            "ClubStore.getShippingCost(125, false, false)");   
    }
    
    /**
     * Tests getShippingCost with 20 for subtotal, false for twoDayShipping, and 
     * false for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubtotal20NoCouponStandard() {
        assertEquals(3, ClubStore.getShippingCost(20, false, false), 
            "ClubStore.getShippingCost(20, false, false)");  
    } 

    /**
     * Tests getShippingCost with 15 for subtotal, true for twoDayShipping, and 
     * false for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubTotal15TwoDay() { 
        assertEquals(5, ClubStore.getShippingCost(15, true, false), 
            "ClubStore.getShippingCost(15, true, false)");  
    }   
    
    /**
     * Tests getShippingCost with 45 for subtotal, true for twoDayShipping, and 
     * false for hasValidCoupon
     */ 
    @Test
    public void testGetShippingCostSubTotal45TwoDay() {
        assertEquals(5, ClubStore.getShippingCost(45, true, false), 
            "ClubStore.getShippingCost(45, true, false)");  
    }

    /**
     * Tests getArrivalDate with orderMonth = 10, orderDay = 15, and numberOfShippingDays = 3
     */ 
    @Test
    public void testGetArrivalDateOct15ThreeDays() {
        assertEquals("Tue, 10/18/2022", ClubStore.getArrivalDate(10, 15, 3), 
                     "ClubStore.getArrivalDate(10, 15, 3)");       
    }

    /**
     * Tests getArrivalDate with orderMonth = 10, orderDay = 30, and numberOfShippingDays = 4
     */ 
    @Test
    public void testGetArrivalDateOct30FourDays() {
        assertEquals("Thu, 11/3/2022", ClubStore.getArrivalDate(10, 30, 4), 
                     "ClubStore.getArrivalDate(10, 30, 4)");            
    }
    
    /**
     * Tests getArrivalDate with orderMonth = 11, orderDay = 30, and numberOfShippingDays = 1
     */ 
    @Test
    public void testGetArrivalDateNov30OneDay() {   
        assertEquals("Thu, 12/1/2022", ClubStore.getArrivalDate(11, 30, 1), 
            "ClubStore.getArrivalDate(11, 30, 1)");         
    }

    /**
     * Tests getArrivalDate with orderMonth = 11, orderDay = 25, and numberOfShippingDays = 5
     */ 
    @Test
    public void testGetArrivalDateNov25FiveDays() {
        assertEquals("Wed, 11/30/2022", ClubStore.getArrivalDate(11, 25, 5), 
            "ClubStore.getArrivalDate(11, 25, 5)");  
    }    
    
    /**
     * Tests getArrivalDate with orderMonth = 10, orderDay = 29, and numberOfShippingDays = 2
     */ 
    @Test
    public void testGetArrivalDateOct29TwoDays() {
        assertEquals("Mon, 10/31/2022", ClubStore.getArrivalDate(10, 29, 2), 
            "ClubStore.getArrivalDate(10, 29, 2)");  
    }

    /**
     * Tests getArrivalDate with orderMonth = 11, orderDay = 1, and numberOfShippingDays = 5
     */ 
    @Test
    public void testGetArrivalDateNov1FiveDays() {
        assertEquals("Sun, 11/6/2022", ClubStore.getArrivalDate(11, 1, 5), 
            "ClubStore.getArrivalDate(11, 1, 5)");                  
    }    
       
    /**
     * Test the ClubStore methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getSubtotal(-1, 0, 0),
                                 "ClubStore.getSubtotal(-1, 0, 0)");
        assertEquals("Invalid number", exception.getMessage(),
                     "Testing ClubStore.getSubtotal(-1, 0, 0) - " +
                     "exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getSubtotal(1, -1, 0),
                                 "ClubStore.getSubtotal(1, -1, 0)");
        assertEquals("Invalid number", exception.getMessage(),
                     "Testing ClubStore.getSubtotal(1, -1, 0) - " +
                     "exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getSubtotal(1, 1, -1),
                                 "ClubStore.getSubtotal(1, 1, -1)");
        assertEquals("Invalid number", exception.getMessage(),
                     "Testing ClubStore.getSubtotal(1, 1, -1) - " +
                     "exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getShippingCost(-1, false, false),
                                 "ClubStore.getShippingCost(-1, false, false)");
        assertEquals("Invalid subtotal", exception.getMessage(),
                     "Testing ClubStore.getShippingCost(-1, false, false) - " +
                     "exception message");                     
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getArrivalDate(10, 1, 1),
                                           "ClubStore.getArrivalDate(10, 1, 1)");
        assertEquals("Invalid date", exception.getMessage(),
                     "Testing ClubStore.getArrivalDate(10, 1, 1) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getArrivalDate(2, 1, 0),
                                           "ClubStore.getArrivalDate(2, 1, 0)");
        assertEquals("Invalid date", exception.getMessage(),
                     "Testing ClubStore.getArrivalDate(2, 1, 0) - " + 
                     "exception message");                     
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getArrivalDate(10, 20, 0),
                                           "ClubStore.getArrivalDate(10, 20, 0)");
        assertEquals("Invalid days", exception.getMessage(),
                     "Testing ClubStore.getArrivalDate(10, 20, 0) - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> ClubStore.getArrivalDate(10, 20, 6),
                                           "ClubStore.getArrivalDate(10, 20, 6)");
        assertEquals("Invalid days", exception.getMessage(),
                     "Testing ClubStore.getArrivalDate(10, 20, 6) - " + 
                     "exception message");                       
    }
}