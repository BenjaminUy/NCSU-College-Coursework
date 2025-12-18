package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Activity class for checkConflict() and various methods
 * @author Benjamin Uy
 */
class ActivityTest {

	/**
	 * Test Activity.checkConflict() with Courses that start at the same times, but on different days
	 */
	@Test
	public void testCheckConflictNoConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Test Activity.checkConflict() with both Courses being arranged
	 */
	@Test
	public void testCheckConflictNoConflict2() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Test Activity.checkConflict() with one Course being arranged
	 */
	@Test
	public void testCheckConflictNoConflict3() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Test Activity.checkConflict() with conflict for one day with the same start/end times
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
	    
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Test Activity.checkConflict() with conflict for a Course that starts within the time range of another Course, but
	 * ends after it. 
	 */
	@Test
	public void testCheckConflictWithConflict2() {
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1400, 1450);
	    Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    
	    Exception e1 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e1.getMessage());
	    
	    Exception e2 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Test Activity.checkConflict() with conflict where endTime of a Course is the same as the startTime of the other Course
	 */
	@Test
	public void testCheckConflictWithConflict3() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1600);
	    
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Test Activity.checkConflict() with conflict where a Course's time range is within another Course's time range
	 */
	@Test
	public void testCheckConflictWithConflict4() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1400, 1430);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	
}
