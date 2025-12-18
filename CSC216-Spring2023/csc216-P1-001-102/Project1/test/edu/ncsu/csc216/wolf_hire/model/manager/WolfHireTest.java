package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Class tests WolfHire.java
 * @author Benjamin Uy
 */
public class WolfHireTest {
	
	/** Creates local WolfHire object */ 
	private WolfHire singleton;
	
	/**
	 * Method calls the setCounter method from Application before each test, thereby resetting the counter
	 */
	@BeforeEach
	public void setUp() {
		//Reset the counter at the beginning of every test.
		Application.setCounter(0);
		
		singleton = WolfHire.getInstance();
		singleton.resetManager();
	}
	
	/**
	 * Test loadPositionsFromFile()
	 */
	@Test
	public void testLoadPositionsFromFile() {	
		singleton.loadPositionsFromFile("test-files/positions1.txt");
		
		assertEquals("CSC 216 PTF", singleton.getActivePositionName());
		assertEquals(12, singleton.getActivePosition().getHoursPerWeek());
		assertEquals(15, singleton.getActivePosition().getPayRate());
		
		singleton.resetManager();
	}
	
	/**
	 * Tests addNewPosition() method for various valid and invalid input
	 */
	@Test
	public void testAddNewPosition() {
		singleton.resetManager();
		assertNull(singleton.getActivePosition());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> singleton.addNewPosition(null, 20, 20));
		assertEquals("Position cannot be created.", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> singleton.addNewPosition("", 20, 20));
		assertEquals("Position cannot be created.", e1.getMessage());
		
		singleton.addNewPosition("CSC 116 Grader", 20, 20);
		assertEquals(1, singleton.getPositionList().length);
		assertEquals("CSC 116 Grader", singleton.getActivePositionName());
		
		singleton.resetManager();
	}
	
	/**
	 * Tests getApplicationById() method
	 */
	@Test
	public void testGetApplicationByIdAndDeleteApplicationById() {
		assertNull(singleton.getActivePosition());
		
		assertNull(singleton.getApplicationById(1));
		
		singleton.addNewPosition("CSC 116 Grader", 15, 15);
		singleton.addApplicationToPosition("Charles", "Mackney", "cmackn7");
		singleton.addApplicationToPosition("Ben", "Studor", "bstudo9");
		assertEquals(2, singleton.getActivePosition().getApplications().size());
		
		assertNull(singleton.getApplicationById(20));	// Call method to get application with a non existent id number
		
		// Call method with an existing id and check for correct info
		assertEquals("Charles", singleton.getApplicationById(1).getFirstName());
		assertEquals("Mackney", singleton.getApplicationById(1).getSurname());
		assertEquals("cmackn7", singleton.getApplicationById(1).getUnityId());
		
		assertEquals("Ben", singleton.getApplicationById(2).getFirstName());
		assertEquals("Studor", singleton.getApplicationById(2).getSurname());
		assertEquals("bstudo9", singleton.getApplicationById(2).getUnityId());
		
		singleton.deleteApplicationById(1);		// Delete application with id of 1
		// Check remaining application in list
		assertEquals("Ben", singleton.getApplicationById(2).getFirstName());
		assertEquals("Studor", singleton.getApplicationById(2).getSurname());
		assertEquals("bstudo9", singleton.getApplicationById(2).getUnityId());
	}
	
	/**
	 * Tests getApplicationAsArray() method
	 */ 
	@Test
	public void testGetApplicationsAsArray() {
		assertNull(singleton.getActivePosition());
		
		assertNull(singleton.getApplicationsAsArray("Submitted"));
		
		singleton.addNewPosition("CSC 116 Grader", 15, 15);
		singleton.addApplicationToPosition("Charles", "Mackney", "cmackn7");
		singleton.addApplicationToPosition("Ben", "Studor", "bstudo9");
		
		assertEquals("Submitted", singleton.getActivePosition().getApplications().get(0).getState());
		assertEquals(1, singleton.getActivePosition().getApplications().get(0).getId());
		assertEquals("Submitted", singleton.getActivePosition().getApplications().get(1).getState());
		assertEquals(2, singleton.getActivePosition().getApplications().get(1).getId());
		
		assertEquals(2, singleton.getActivePosition().getApplications().size());	
		
		String[][] applicationArray = singleton.getApplicationsAsArray("Submitted");
		assertEquals(2, applicationArray.length);
		assertEquals(1, Integer.parseInt(applicationArray[0][0]));
		assertEquals("Submitted", applicationArray[0][1]);
		assertEquals("cmackn7", applicationArray[0][2]);
		assertEquals("", applicationArray[0][3]);
		assertEquals(2, Integer.parseInt(applicationArray[1][0]));
		assertEquals("Submitted", applicationArray[1][1]);
		assertEquals("bstudo9", applicationArray[1][2]);
		assertEquals("", applicationArray[1][3]);
		
		applicationArray = singleton.getApplicationsAsArray("Hired");
		assertEquals(0, applicationArray.length);
	}
	
	/**
	 * Tests WolfHire's executeCommand() method on an application in the active position
	 */
	@Test
	public void testExecuteCommand() {
		assertNull(singleton.getActivePosition());
		singleton.addNewPosition("CSC 116 Grader", 15, 15);
		singleton.addApplicationToPosition("Charles", "Mackney", "cmackn7");
		assertEquals(1, singleton.getActivePosition().getApplications().get(0).getId());
		assertEquals("Charles", singleton.getActivePosition().getApplications().get(0).getFirstName());
		assertEquals("Mackney", singleton.getActivePosition().getApplications().get(0).getSurname());
		assertEquals("cmackn7", singleton.getActivePosition().getApplications().get(0).getUnityId());
		assertEquals("Submitted", singleton.getActivePosition().getApplications().get(0).getState());
		
		// Attempt to issue a command for an application that does not have the given id
		singleton.executeCommand(10, new Command(Command.CommandValue.REJECT, "Qualifications"));
		
		// Check that the application is unchanged
		assertEquals(1, singleton.getActivePosition().getApplications().get(0).getId());
		assertEquals("Charles", singleton.getActivePosition().getApplications().get(0).getFirstName());
		assertEquals("Mackney", singleton.getActivePosition().getApplications().get(0).getSurname());
		assertEquals("cmackn7", singleton.getActivePosition().getApplications().get(0).getUnityId());
		assertEquals("Submitted", singleton.getActivePosition().getApplications().get(0).getState());
		
		// Attempt to issue a command for an application that does not have the given id
		singleton.executeCommand(1, new Command(Command.CommandValue.REJECT, "Qualifications"));
		
		// Check that the application's state has changed
		assertEquals(1, singleton.getActivePosition().getApplications().get(0).getId());
		assertEquals("Charles", singleton.getActivePosition().getApplications().get(0).getFirstName());
		assertEquals("Mackney", singleton.getActivePosition().getApplications().get(0).getSurname());
		assertEquals("cmackn7", singleton.getActivePosition().getApplications().get(0).getUnityId());
		assertEquals("Rejected", singleton.getActivePosition().getApplications().get(0).getState());
	}
	
	/**
	 * Tests savePositionsToFile() with a null active position field
	 */
	@Test
	public void testSavePositionsToFileInvalid() {
		assertNull(singleton.getActivePosition());
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> singleton.savePositionsToFile(""));
		assertEquals("Unable to save file.", e1.getMessage());
	}
	
	/**
	 * Tests WolfHire.savePositionsToFile() method and checks output of what is written to file
	 */
	@Test
	public void testSavePositionsToFileValid() {
		assertNull(singleton.getActivePosition());
		
		singleton.addNewPosition("CSC 116 Grader", 15, 15);
		singleton.addApplicationToPosition("Charles", "Mackney", "cmackn7");
		singleton.addApplicationToPosition("Ben", "Studor", "bstudo9");
		assertEquals(2, singleton.getActivePosition().getApplications().size());
		
		singleton.addNewPosition("CSC 216 Tutor", 16, 16);
		singleton.addApplicationToPosition("Amy", "Julien", "ajulie3");
		assertEquals(1, singleton.getActivePosition().getApplications().size());
		
		assertEquals(2, singleton.getPositionList().length);
		
		singleton.savePositionsToFile("test-files/actualtestSavePositionsToFile.txt");
		checkFiles("test-files/expectedtestWritePositionsToFile.txt", "test-files/actualtestSavePositionsToFile.txt");
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
