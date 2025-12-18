package edu.ncsu.csc216.pack_scheduler.directory;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 * @author Hank Lenham;
 * @author Noah Anderson; 
 * @author Benjamin Uy
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	
	/** Invalid course records that does not exist */
	private final String invalidTestFile = "test-files/";
	/** Additional test first name */
	private static final String FIRST_NAME2 = "Uts";
	/** Additional test last name */
	private static final String LAST_NAME2 = "Dentist";
	/** Additional test ID */
	private static final String ID2 = "extra";
	/** Additional test email */
	private static final String EMAIL2 = "student@ncsu.edu";
	/** Additional test password */
	private static final String PASSWORD2 = "pw2";
	
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
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}
	
	/**
	 * Tests StudentDirector.loadStudentsFromFile() with a file name that does not exist
	 */
	@Test
	public void testLoadStudentsFromFileInvalid() {
		StudentDirectory sd = new StudentDirectory();
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.loadStudentsFromFile(invalidTestFile));
		assertEquals("Unable to read file " + invalidTestFile, e1.getMessage());
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}
	
	/**
	 * Tests StudentDirectory.addStudent() with invalid passwords that lead to exceptions
	 */
	@Test
	public void testAddStudentInvalid() {
		StudentDirectory sd = new StudentDirectory();
		
		// Test invalid Student
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD2, MAX_CREDITS));
		assertEquals("Passwords do not match", e1.getMessage());
	}
	
	/**
	 * Tests StudentDirectory.addStudent() with invalid max credits and adding a student with the same id as
	 * an existing student in the directory, then adding a student with a different id
	 */
	@Test
	public void testAddStudent2() {
		StudentDirectory sd = new StudentDirectory();
		
		//Adding a Student with max credits less than 3
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 2);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		//Reset the student directory
		sd = new StudentDirectory();
		
		//Adding a Student with max credits more than 18
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS * 2);
		studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		//Attempt to add a Student with the same id
		assertFalse(sd.addStudent(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		
		//Attempt to add a Student with a different id
		sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL2, PASSWORD2, PASSWORD2, MAX_CREDITS);
		studentDirectory = sd.getStudentDirectory();
		assertEquals(2, studentDirectory.length);
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Tests StudentDirectory.saveStudentDirectory() using an invalid file name
	 */
	@Test
	public void testSaveStudentDirectoryInvalid() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> sd.saveStudentDirectory(invalidTestFile));
		assertEquals("Unable to write to file " + invalidTestFile, e1.getMessage());
		
		
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