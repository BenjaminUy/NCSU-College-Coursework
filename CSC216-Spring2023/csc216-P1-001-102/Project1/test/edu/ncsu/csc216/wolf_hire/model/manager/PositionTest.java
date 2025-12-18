package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;

/**
 * Class tests Position.java
 * @author Benjamin Uy
 */
public class PositionTest {
	/** Constant for position name*/
	private static final String POSITION_NAME = "CSC 116 Grader";
	/** Constant for position hours per week */
	private static final int POSITION_HOURS = 10;
	/** Constant for position pay rate */
	private static final int POSITION_PAY = 20;

	/**
	 * Method calls the setCounter method from Application before each test, thereby resetting the counter
	 */
	@BeforeEach
	public void setUp() {
		//Reset the counter at the beginning of every test.
		Application.setCounter(0);
	}
	
	/**
	 * Test the addApplication(String, int, int) method by creating a new applicant.
	 * Also tests addApplication(Application) method.
	 */
	@Test
	public void addApplicationTest1() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		
		assertEquals("CSC 116 Grader", p.getPositionName());					// Check to see if position info matches
		assertEquals(10, p.getHoursPerWeek());
		assertEquals(20, p.getPayRate());
		
		Application a = new Application("Alex", "Zander", "azand3");	
		assertEquals(1, p.addApplication(a));
		assertEquals(1, p.getApplications().get(0).getId());
		assertEquals(1, p.getApplications().size());							// Check size of the applications list
		
		assertEquals("Alex", p.getApplicationById(1).getFirstName());			// Check info of the first applicant
		assertEquals("Zander", p.getApplicationById(1).getSurname());
		assertEquals("azand3", p.getApplicationById(1).getUnityId());
		
		Application b = new Application("Amy", "Zoe", "azoe56");				// Create a new Application
		assertEquals(2, p.addApplication(b));	
		assertEquals(2, p.getApplications().get(1).getId());
		assertEquals(2, p.getApplications().size());							// Check size of the applications list
		
		assertEquals("Amy", p.getApplicationById(2).getFirstName());			// Check info of the second applicant
		assertEquals("Zoe", p.getApplicationById(2).getSurname());
		assertEquals("azoe56", p.getApplicationById(2).getUnityId());
		
		Application.setCounter(1);												// Reset counter to 0
		Application c = new Application("Ben", "Zoren", "bzoren4");				// Attempt to add Application into the list with an existing id number
		assertEquals(1, c.getId());
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> p.addApplication(c));
		assertEquals("Application cannot be created.", e1.getMessage());
	}
	
	/**
	 * Tests the addApplication(Application) method for different places that
	 * Application will be added in applications list
	 */
	@Test
	public void addApplicationTest2() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		Application.setCounter(2);
		Application a = new Application("Alex", "Zoe", "azoe56");
		assertEquals(2, p.addApplication(a));
		
		Application.setCounter(0);		// Reset the counter to create applications with ids less than previous id
		Application b = new Application("Ben", "Zoren", "bzoren4");
		assertEquals(1, b.getId());		// Check that id of newly created Application has a different value because of reseted counter
		assertEquals(1, p.addApplication(b));
		
		assertEquals(a, p.getApplications().get(1));
		assertEquals(b, p.getApplications().get(0));
	}
	
	/**
	 * Test the deleteApplicationById() method
	 */
	@Test
	public void deleteApplicationTest() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		assertEquals(1, p.addApplication("Alex", "Zander", "azand3"));			// Add new application with given applicant information
		assertEquals(1, p.getApplications().size());							// Check size of the applications list
		
		assertEquals("Alex", p.getApplicationById(1).getFirstName());			// Check info of the first applicant
		assertEquals("Zander", p.getApplicationById(1).getSurname());
		assertEquals("azand3", p.getApplicationById(1).getUnityId());
		
		Application a = new Application("Amy", "Zoe", "azoe56");				// Create a new Application
		assertEquals(2, p.addApplication(a));									// Add created application to applications list
		assertEquals(2, p.getApplications().size());							// Check size of the applications list
		
		assertEquals("Amy", p.getApplicationById(2).getFirstName());			// Check info of the second applicant
		assertEquals("Zoe", p.getApplicationById(2).getSurname());
		assertEquals("azoe56", p.getApplicationById(2).getUnityId());
		
		p.deleteApplicationById(2);												// Delete the first added application
		
		assertEquals(1, p.getApplications().size());							// Check size of the application list
		
		assertEquals("Alex", p.getApplicationById(1).getFirstName());			// Check info of the remaining applicant
		assertEquals("Zander", p.getApplicationById(1).getSurname());
		assertEquals("azand3", p.getApplicationById(1).getUnityId());
	}
	
	/**
	 * Tests creating new Positions with invalid parameters
	 * Also constructs a new valid position and tests toString() method
	 */
	@Test
	public void invalidPositions() {
		Position p;
		try {
			p = new Position("", POSITION_HOURS, POSITION_PAY);	// Create an invalid Position with empty name
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		try {
			p = new Position(null, POSITION_HOURS, POSITION_PAY);	// Create an invalid Position with null name parameter
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		try {
			p = new Position(POSITION_NAME, 3, POSITION_PAY);		// Create an invalid Position with less than minimum hours per week
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		try {
			p = new Position(POSITION_NAME, 25, POSITION_PAY);		// Create an invalid Position with more than maximum hours per week
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		try {
			p = new Position(POSITION_NAME, POSITION_HOURS, 5);		// Create an invalid Position with less than minimum pay rate
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		try {
			p = new Position(POSITION_NAME, POSITION_HOURS, 40);		// Create an invalid Position with more than maximum pay rate
			fail();		// Constructing invalid position should throw IAE, if not, fail the test
		} catch (IllegalArgumentException e) {
			assertEquals("Position cannot be created.", e.getMessage());
		}
		p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		assertEquals("# CSC 116 Grader,10,20", p.toString());
	}
	
	/**
	 * Tests getApplicationById for a Position with no applications
	 */
	@Test
	public void getApplicationByIdNullTest() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		assertEquals(0, p.getApplications().size());		// Check the size of the applications list in the position
		assertNull(p.getApplicationById(0));
	}
	
	/**
	 * Tests addApplication and setApplicationId with multiple applications
	 */
	@Test
	public void setApplicationIdTest() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		assertEquals(0, p.getApplications().size());		// Check the size of the applications list in the position
		
		assertEquals(1, p.addApplication("Alex", "Zander", "azand3"));			// Add new application with given applicant information
		assertEquals(1, p.getApplications().size());							// Check size of the applications list
		
		assertEquals(2, p.addApplication("Baron", "Maxim", "bmaxim7"));
		assertEquals(2, p.getApplications().size());
		
		Application a = new Application(12, "Submitted", "Fernard", "Dutch", "fdutch3", null, null);
		
		assertEquals(12, p.addApplication(a));
		assertEquals(3, p.getApplications().size());
		
		p.setApplicationId();	// Call method to update Application.counter field to max id in Application list
		
		Application b = new Application("Charlie", "Douglas", "cdoug19");
		assertEquals(13, b.getId());
		assertEquals(13, p.addApplication(b));
	}
	
	/**
	 * Tests addApplication method with multiple applications added to a Position
	 */
	@Test
	public void testAddApplication() {
		Position p = new Position(POSITION_NAME, POSITION_HOURS, POSITION_PAY);
		
		assertEquals(1, p.addApplication("Carol", "Schmidt", "cschmid"));
		
		assertEquals(2, p.addApplication(new Application(2, "Rejected", "Clinton", "Armstrong", "carmstr", null, "Qualifications")));

		assertEquals(5, p.addApplication(new Application(5, "Hired", "Craig", "Armstrong", "carmstr", "tnmacnei", null)));
		
		assertEquals(6, p.addApplication("Cailin", "Roach", "cvroach"));
	
	}
}
