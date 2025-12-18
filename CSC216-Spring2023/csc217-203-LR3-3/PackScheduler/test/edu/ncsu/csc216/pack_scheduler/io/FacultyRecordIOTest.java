package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests FacultyRecordIO. Includes methods to test both writing, and reading
 * from a valid file and an invalid file.
 * 
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
class FacultyRecordIOTest {
 
	/**
	 * Tests reading from a valid file.
	 */
	@Test
	void testReadFileValid() {
		try {
			LinkedList<Faculty> validFaculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(8, validFaculty.size());
		}
		catch(FileNotFoundException e) {
			fail("File could not be found.");
		}
	}

	/**
	 * Tests writeFacultyRecords.
	 */
	@Test
	void testWriteFacultyRecords() {
		Faculty f = new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 2);
		Faculty f1 = new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 3);
		Faculty f2 = new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 1);
		LinkedList<Faculty> faculties = new LinkedList<Faculty>();
		faculties.add(f);
		faculties.add(f1);
		faculties.add(f2);
		assertEquals(3, faculties.size());
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_file_records.txt", faculties);
			checkFiles("test-files/actual_file_records.txt", "test-files/expected_faculty_records.txt");
		} catch (IOException e) {
			fail("IOException.");
		}
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * 
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
