/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Unit tests the CourseCatalog test.
 * @author Kartik Alle, Hunt Tynch, Connor Hekking
 */
class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course roll enrollment capacity */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		assertEquals(0, c.getCourseCatalog().length);
	}
	
	/**
	 * Tests loadCoursesFromFile()
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(invalidTestFile);
		
		assertEquals(0, c1.getCourseCatalog().length);
		c1.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Test with valid file containing 8 courses.  Will test other methods in other tests.
		CourseCatalog c2 = new CourseCatalog();
		c2.loadCoursesFromFile(validTestFile);
		assertEquals(13, c2.getCourseCatalog().length);		
	}
	
	/**
	 * Tests addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		
		assertTrue(c1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, c1.getCourseCatalog().length);
		Course course = c1.getCourseFromCatalog(NAME, SECTION);
		assertEquals(NAME, course.getName());
		assertEquals(SECTION, course.getSection());
		assertEquals(TITLE, course.getTitle());
		assertEquals(CREDITS, course.getCredits());
		assertEquals(INSTRUCTOR_ID, course.getInstructorId());
		assertEquals(ENROLLMENT_CAP, course.getCourseRoll().getOpenSeats());
		assertEquals(START_TIME, course.getStartTime());
		assertEquals(END_TIME, course.getEndTime());
		
		assertFalse(c1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, c1.getCourseCatalog().length);
	}
	
	/**
	 * Tests removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		c1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertFalse(c1.removeCourseFromCatalog(NAME, "222"));
		assertTrue(c1.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, c1.getCourseCatalog().length);
	}
	
	/**
	 * Tests getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = c.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		//Row 6
		assertEquals("CSC217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		//Row 7
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		//Row 8
		assertEquals("CSC217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		//Row 9
		assertEquals("CSC217", catalog[9][0]);
		assertEquals("601", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		//Row 10
		assertEquals("CSC226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		//Row 11
		assertEquals("CSC230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		//Row 12
		assertEquals("CSC316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
	}
	
	/**
	 * Test getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(c.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, null, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(course, c.getCourseFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Tests saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);

		Exception e = assertThrows(IllegalArgumentException.class, () -> c.saveCourseCatalog(null));
		assertEquals("Null parameter.", e.getMessage());

		//Test that empty schedule exports correctly
		CourseCatalog c2 = new CourseCatalog();
		c2.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");

		//Add courses and test that exports correctly
		c2.addCourseToCatalog("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 10, "MW", 1330, 1445);
		c2.addCourseToCatalog("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 10, "MWF", 935, 1025);
		c2.saveCourseCatalog("test-files/actual_schedule_export.txt");
		checkFiles("test-files/expected_schedule_export.txt", "test-files/actual_schedule_export.txt");

	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
