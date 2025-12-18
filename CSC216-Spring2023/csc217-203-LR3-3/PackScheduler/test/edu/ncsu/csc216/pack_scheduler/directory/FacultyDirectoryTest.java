package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Noah";
	/** Test last name */
	private static final String LAST_NAME = "Anderson";
	/** Test id */
	private static final String ID = "neander2";
	/** Test email */
	private static final String EMAIL = "neander2@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;

	@Test
	void testFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("neander2"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	@Test
	void testNewFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);

		fd.newFacultyDirectory();
		assertFalse(fd.removeFaculty("neander2"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	@Test
	void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		Exception e = assertThrows(IllegalArgumentException.class, () -> fd.loadFacultyFromFile("hello.txt"));
		assertEquals("Unable to read file hello.txt", e.getMessage());

	}

	@Test
	void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES));
		assertEquals("Invalid password", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, "hello", MAX_COURSES));
		assertEquals("Passwords do not match", e2.getMessage());

		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES));
		assertEquals("Invalid password", e3.getMessage());

		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", e4.getMessage());

		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", e5.getMessage());

		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 0));
		assertEquals("Invalid max courses", e6.getMessage());

		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 5));
		assertEquals("Invalid max courses", e7.getMessage());

		assertFalse(fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));

	}

	@Test
	void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		
		fd.removeFaculty("awitt");
		assertEquals(7, fd.getFacultyDirectory().length);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals("Fiona", facultyDirectory[0][0]);
		assertEquals("Meadows", facultyDirectory[0][1]);
		assertEquals("fmeadow", facultyDirectory[0][2]);
		
		fd.removeFaculty("neander2");
		assertEquals(7, fd.getFacultyDirectory().length);
	}

	@Test
	void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, fd.getFacultyDirectory().length);
		
		try {
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	@Test
	void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();

		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		Faculty f2 = fd.getFacultyById(ID);
		assertEquals(f.getFirstName(), f2.getFirstName());
		assertEquals(f.getLastName(), f2.getLastName());
		assertEquals(f.getId(), f2.getId());
		assertEquals(null, fd.getFacultyById(""));
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
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
