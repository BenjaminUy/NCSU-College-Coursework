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

/**
 * Tests StudentRecordIO class
 * @author SarahHeckman; 
 * @author Hank Lenham;
 * @author Noah Anderson; 
 * @author Benjamin Uy;
 */
class StudentRecordIOTest {
	/** A valid student. */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** A valid student. */
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** A valid student. */
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** A valid student. */
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** A valid student. */
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** A valid student. */
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** A valid student. */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** A valid student. */
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** A valid student. */
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** A valid student. */
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** A valid student. */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};
	
	/** The hashed password we will store for the user. */
	private String hashPW;
	/** The type of algorithm we will use to hash passwords. */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** Valid Student records */
	private final String validStudentFile = "test-files/student_records.txt";
	/** Non-TS Student records */
	private final String duplicateStudentFile = "test-files/duplicate_student.txt";
	/** Invalid Student records */
	private final String invalidStudentFile = "test-files/invalid_student_records.txt";
	/** Invalid Student records with too many tokens in line */
	private final String tooManyTokensFile = "test-files/too_many_tokens.txt";
	
	
	/**
	 * Helper method to set up the files for testing before calling each method.
	 */
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
	 * Tests processStudent and readStudentRecords with invalid file that has an extra token in a line
	 */
	@Test
	public void testProcessStudentAndReadStudentRecordsFileInvalid2() { 
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(tooManyTokensFile);
			assertEquals(0, students.size());
			
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + tooManyTokensFile);
		}
	}
	
	
	/**
	 * Tests readValidStudentRecords
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validStudentFile);
			assertEquals(10, students.size());
			
			for(int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		}
		catch(FileNotFoundException e) {
			fail("Unexpected error reading " + validStudentFile);
		}
	} 
	
	/**
	 * Tests readStudentRecords with a text file with duplicate Students
	 */
	@Test
	public void testReadStudentRecordsDuplicateStudent() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(duplicateStudentFile);
			assertEquals(1, students.size());
			assertEquals(validStudents[6], students.get(0).toString());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + duplicateStudentFile);
		}
	}
	
	/**
	 * Tests readInvalidStudentRecords.
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(invalidStudentFile);
			assertEquals(0, students.size());
		}
		catch(FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}


	/**
	 * Tests writeStudentRecords.
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		checkFiles("test-files/actual_student_records.txt", "test-files/expected_student_records.txt");
		
	}

	/**
	 * Tests writeStudentRecords when the user does not have permission to access the target file
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