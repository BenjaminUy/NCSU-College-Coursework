package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Class tests PositionWriter.java
 * @author Benjamin Uy
 */
public class PositionWriterTest {
	
	/**
	 * Tests writePositionsToFile() method with a file that does not exist
	 */
	@Test
	public void testWritePositionsToFileInvalid() {
		ArrayList<Position> positions = new ArrayList<Position>();
		Exception e = assertThrows(IllegalArgumentException.class, () -> PositionWriter.writePositionsToFile(".", positions));
		assertEquals("Unable to save file.", e.getMessage());
	}
	
	/**
	 * Tests writePositionsToFile() method
	 */
	@Test
	public void testWritePostitionsToFile() {
		ArrayList<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("Position A", 18, 20));
		Application a = new Application(2, "Submitted", "Carol", "Schmidt", "cschmid", null, null);
		positions.get(0).addApplication(a);
		Application b = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
		positions.get(0).addApplication(b);
		assertEquals(2, positions.get(0).getApplications().size());		// Check size of applications list of Position A
		
		positions.add(new Position("Position B", 10, 12));
		Application c = new Application(7, "Inactive", "Deanna", "Sanders", "dsander", "tmbarnes", "Completed");
		positions.get(1).addApplication(c);
		Application d = new Application(8, "Interviewing", "Benjamin", "Nieves", "bmnieves", "sesmith5", null);
		positions.get(1).addApplication(d);
		Application e = new Application(11, "Processing", "Quemby", "Mullen", "qmullen", "sesmith5", null);
		positions.get(1).addApplication(e);
		assertEquals(3, positions.get(1).getApplications().size());		// Check size of applications list of Position B
		
		positions.add(new Position("Position C", 15, 20));				// Create a new Position but don't add any applications to it
		assertEquals(0, positions.get(2).getApplications().size());		// Check size of empty Position's application list
		
		positions.add(new Position("Position D", 11, 11));
		Application f = new Application(3, "Rejected", "Kathleen", "Gillespie", "kgilles", null, "Incomplete");
		positions.get(3).addApplication(f);
		assertEquals(1, positions.get(3).getApplications().size());		// Check size of applications list of Position D
		
		assertEquals(4, positions.size());		// Check size of Position list, including Position C which has no applications
		
		try {
			// Call method to write Positions to file
			PositionWriter.writePositionsToFile("test-files/actualTestWritePositionsToFile.txt", positions);
		} catch (IllegalArgumentException e1) {
			fail("Cannot write to file");
		}
		
		checkFiles("test-files/expected_positions.txt", "test-files/actualTestWritePositionsToFile.txt");
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
