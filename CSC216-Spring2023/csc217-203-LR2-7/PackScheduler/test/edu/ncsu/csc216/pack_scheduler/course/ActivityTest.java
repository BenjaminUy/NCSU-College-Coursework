/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the activity class checkConflict method.
 * @author hctynch
 * @author bsuy
 * @author ajwarre5
 */
class ActivityTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 * Tests activity objects which should not have conflict 
	 */
	@Test
	void testCheckConflictValid() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
	    assertDoesNotThrow(() -> a3.checkConflict(a1));
	    assertDoesNotThrow(() -> a1.checkConflict(a3));
	}
	
	/**
	 * Tests activity objects which should have a conflict.
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1445, 1600);
	    
	    Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
	    
	    Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	}
	

}
