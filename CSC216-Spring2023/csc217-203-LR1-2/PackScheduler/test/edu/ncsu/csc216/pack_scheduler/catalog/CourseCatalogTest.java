package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests all of the methods in CourseCatalog.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 *
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC 216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Course meeting string */
	private static final String MEETING_INFO = "TH 1:30PM-2:45PM";

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("CSC 216", "002"));
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC 492", "001"));
		
		//Create a new Activity by constructing a new Course
		Activity c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		
		//Attempt to get a course that does exist
		assertEquals(c, cc.getCourseFromCatalog("CSC 216", "001"));
		
		//Attempt to get a course that has the same name but nonexistent section
		assertNull(cc.getCourseFromCatalog("CSC 216", "003"));
	}
	
	/**
	 * Tests CourseCatalog.newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
		
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
				
		//Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursesFromFile() with a file name that does not exist
	 */
	@Test
	public void testLoadCoursesFromFileInvalid() {
		CourseCatalog cc = new CourseCatalog();
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> cc.loadCoursesFromFile(invalidTestFile));
		assertEquals("Unable to read file " + invalidTestFile, e1.getMessage());
	}

	/**
	 * Tests CourseCatalog.addCourseToCatalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add a valid Course to an empty Course Catalog
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		
		//Check contents of CourseCatalog
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_INFO, courseCatalog[0][3]);
		
		//Attempt to add a duplicate in the Course Catalog
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		
		//Attempt to add another valid Course
		assertTrue(cc.addCourseToCatalog("CSC 217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", "M", 1200, 1350));
		courseCatalog = cc.getCourseCatalog();
		assertEquals(2, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_INFO, courseCatalog[0][3]);
		assertEquals("CSC 217", courseCatalog[1][0]);
		assertEquals("601", courseCatalog[1][1]);
		assertEquals("Software Development Fundamentals Lab", courseCatalog[1][2]);
		assertEquals("M 12:00PM-1:50PM", courseCatalog[1][3]);
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromCatalog
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add a valid Course to an empty Course Catalog
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		
		//Check contents of CourseCatalog
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_INFO, courseCatalog[0][3]);
		
		//Attempt to remove a Course with the same name, but different section
		assertFalse(cc.removeCourseFromCatalog(NAME, "002"));
		
		//Attempt to remove a Course with a different name, but same section
		assertFalse(cc.removeCourseFromCatalog("CSC 116", SECTION));
		
		//Attempt to remove the previously added Course
		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		courseCatalog = cc.getCourseCatalog();
		assertEquals(0, courseCatalog.length);
		
		//Attempt to remove the same Course in an empty Course Catalog
		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
	}
	
	/**
	 * Tests saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add a few Courses to the Course Catalog
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(cc.addCourseToCatalog("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440));
		assertTrue(cc.addCourseToCatalog("CSC 216", "Software Development Fundamentals", "601", 3, "jctetter", "A", 0, 0));
		
		try {
			cc.saveCourseCatalog("test-files/actual_course_catalog.txt");
		} catch (IllegalArgumentException e) {
			fail("Cannot write to course catalog file");
		}

		checkFiles("test-files/expected_course_catalog.txt", "test-files/actual_course_catalog.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
}
