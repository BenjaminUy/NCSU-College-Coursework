package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;

/**
 * Class tests Schedule class
 * @author Benjamin Uy
 */
public class ScheduleTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	
	/**
	 * Method tests Schedule constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}
	
	/**
	 * Method tests addCourseToSchedule()
	 */
	@Test
	public void testAddCourseToSchedule() {
		// Load courses with CourseCatalog for convenient access to valid Course objects
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);

		Schedule s = new Schedule(); 
		
		// Add first Course to Schedule
		assertTrue(s.addCourseToSchedule(c.getCourseFromCatalog("CSC116", "001")));
		
		// Attempt to add duplicate
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> s.addCourseToSchedule(c.getCourseFromCatalog("CSC116", "001")));
		assertEquals("You are already enrolled in CSC116", e1.getMessage());
		// Attempt to add Course that would cause a conflict in time
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c.getCourseFromCatalog("CSC316", "001")));
		assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
		// Attempt to add a null Course
		Exception e3 = assertThrows(NullPointerException.class,
				() -> s.addCourseToSchedule(null));
		assertEquals("Null element.", e3.getMessage());
	}
	
	/**
	 * Test Schedule.setTitle() and Schedule.resetSchedule()
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		Exception e = assertThrows(IllegalArgumentException.class, () -> s.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage());
		s.setTitle("Schedule");
		assertEquals("Schedule", s.getTitle());
		
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * Test Schedule.getScheduleCredis
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		// Check empty schedule
		assertEquals(0, s.getScheduleCredits());
		
		// Load courses with CourseCatalog for convenient access to valid Course objects
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		
		// Add Course to Schedule with 3 credits
		assertTrue(s.addCourseToSchedule(c.getCourseFromCatalog("CSC116", "001")));
		assertEquals(3, s.getScheduleCredits());
		
		// Add another Course to Schedule with 3 credits
		assertTrue(s.addCourseToSchedule(c.getCourseFromCatalog("CSC216", "601")));
		assertEquals(6, s.getScheduleCredits());
		
		// Add Course to Schedule with 1 credit
		assertTrue(s.addCourseToSchedule(c.getCourseFromCatalog("CSC217", "601")));
		assertEquals(7, s.getScheduleCredits());
	}
	
	/**
	 * Test Schedule.canAdd() method
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		
		// Load courses with CourseCatalog for convenient access to valid Course objects
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		
		// Add first Course to Schedule
		assertTrue(s.addCourseToSchedule(c.getCourseFromCatalog("CSC116", "001")));
		
		// Attempt to add a null Course
		assertFalse(s.canAdd(null));
		// Attempt to add a duplicate Course
		assertFalse(s.canAdd(c.getCourseFromCatalog("CSC116", "001")));
		// Attempt to add a conflicting Course
		assertFalse(s.canAdd(c.getCourseFromCatalog("CSC226", "001")));
		
		assertTrue(s.canAdd(c.getCourseFromCatalog("CSC216", "001")));
		
		
	}
	
}
