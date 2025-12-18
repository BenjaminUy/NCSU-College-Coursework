package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

class StudentRecordIOTest {

	//Students ordered by LAST name
	/** Test Student 7 string. */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Test Student 9 string. */
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Test Student 5 string. */
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Test Student 1 string. */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Test Student 3 string. */
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Test Student 4 string. */
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Test Student 2 string. */
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Test Student 10 string. */
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** Test Student 6 string. */
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Test Student 8 string. */
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** Array of valid test students. */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	/** Hashed string for password pw. */
	private String hashPW;
	/** Hashing algorithm for password. */
	private static final String HASH_ALGORITHM = "SHA-256";

	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	
	/**
	 * Tests valid and invalid student lists on readStudentRecords
	 */
	@Test
	void testReadStudentRecords() {
		new StudentRecordIO();
		try {
		SortedList<Student> validTestStudents = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
		assertEquals(10, validTestStudents.size());
		for(int i = 0; i < validTestStudents.size(); i++) {
			String validString = validStudents[i];
			String testString = validTestStudents.get(i).toString();
			if(!validString.equals(testString)) {
				fail("Expected: " + validString + " Actual: " + testString);
			}
		}
		
		SortedList<Student> invalidTestStudents = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
		assertEquals(0, invalidTestStudents.size());
		} catch(FileNotFoundException e) {
			fail(e.getMessage());
		}
		
		assertThrows(FileNotFoundException.class, () -> StudentRecordIO.readStudentRecords("test-files/filedoesnotexist.txt"));

		
		
	}

	/**
	 * Tests writeStudentRecords records and compares expected versus actual file output. 
	 * Additionally tests when you cannot write to a given file.
	 */
	@Test
	void testWriteStudentRecords() {
		
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
			checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		} catch(IOException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Tests writeStudentRecords when it does not have permissions to write to a file.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}


}
