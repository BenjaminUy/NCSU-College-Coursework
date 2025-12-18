/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class.
 * 
 * @author Noah Anderson
 * @author Benjamin Uy
 */
class ActivityTest {
 
	/**
	 * Test method for checkConflict() with non-conflicting activities.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));

		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 830, 945);
		
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));
		
		Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		Activity a6 = new Course("CSC 217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", "A");
		
		assertDoesNotThrow(() -> a5.checkConflict(a6));
		assertDoesNotThrow(() -> a6.checkConflict(a5));
		
		Activity a7 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		Activity a8 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a7.checkConflict(a8));
		assertDoesNotThrow(() -> a8.checkConflict(a7));	
		
	}

	/**
	 * Test method for checkConflict() with conflicting activities.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1445,
				1600);

		Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e4.getMessage());
	
	}
	
	/**
	 * Test Activity.getMeetingString() with various start and end times
	 */
	@Test
	public void testGetMeetingString() { 
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 0, 1200);
		assertEquals("MW 12:00AM-12:00PM", a1.getMeetingString());
	}
	
}
