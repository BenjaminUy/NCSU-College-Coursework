package edu.ncsu.csc216.wolf_hire.model.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Class tests Application.java
 * @author Benjamin Uy
 */
public class ApplicationTest {
	
	/**
	 * Method calls the setCounter method from Application before each test, thereby resetting the counter
	 */
	@BeforeEach
	public void setUp() {
		//Reset the counter at the beginning of every test.
		Application.setCounter(0);
	}
	
	/**
	 * Test Application constructor that takes seven parameters
	 */
	@Test
	public void testCreateNewApplication() {
		Application a = new Application(5, "Reviewing", "Paula", "Ricker", "pricker9", "id", null);
		assertEquals("Paula", a.getFirstName());
		assertEquals("Ricker", a.getSurname());
		assertEquals("pricker9", a.getUnityId());
		assertEquals(5, a.getId());
		assertEquals("Reviewing", a.getState());
		assertEquals("id", a.getReviewer());
		assertEquals("", a.getNote());
		
		try {
			a = new Application(-1, "Submitted", "Ben", "Yuri", "byuri3", null, null);
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
		try {
			a = new Application(1, "Submitted", "Ben", "Yuri", "byuri3", "id", null);
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
		try {
			a = new Application(1, "Rejected", "Ben", "Yuri", "byuri3", null, "Fired");
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
		try {
			a = new Application(1, "Reviewing", "Ben", "Yuri", "byuri3", null, null);
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
		try {
			a = new Application(1, "Hired", "Ben", "Yuri", "byuri3", null, null);
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
		try {
			a = new Application(1, "Inactive", "Ben", "Yuri", "byuri3", "id", "Duplicate");
			fail();		// Invalid application should not be constructed
		} catch (IllegalArgumentException e1) {
			assertEquals("Application cannot be created.", e1.getMessage());
		}
	}

	/**
	 * Method tests Application.update() method on several applications
	 */
	@Test
	public void testUpdateApplications() {
		Application a = new Application("Alex", "Zane", "azane5");
		assertEquals("Alex", a.getFirstName());
		assertEquals("Zane", a.getSurname());
		assertEquals("azane5", a.getUnityId());
		assertEquals(1, a.getId());
		assertEquals("Submitted", a.getState());
		assertEquals("", a.getReviewer());
		assertEquals("", a.getNote());

		a.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals(Application.REVIEWING_NAME, a.getState());
		assertEquals("id", a.getReviewer());
		assertEquals("", a.getNote());
		a.update(new Command(Command.CommandValue.REJECT, "Qualifications"));
		assertEquals(Application.REJECTED_NAME, a.getState());
		assertEquals("", a.getReviewer());
		assertEquals("Qualifications", a.getNote());
		a.update(new Command(Command.CommandValue.RESUBMIT, null));
		assertEquals(Application.SUBMITTED_NAME, a.getState());
		assertEquals("", a.getReviewer());
		assertEquals("", a.getNote());
		
		Application b = new Application("Ben", "York", "byork2");
		assertEquals("Ben", b.getFirstName());
		assertEquals("York", b.getSurname());
		assertEquals("byork2", b.getUnityId());
		assertEquals(2, b.getId());
		assertEquals("Submitted", b.getState());
		assertEquals("", b.getReviewer());
		assertEquals("", b.getNote());
		
		b.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals(Application.REVIEWING_NAME, b.getState());
		assertEquals("id", b.getReviewer());
		assertEquals("", b.getNote());
		b.update(new Command(Command.CommandValue.SCHEDULE, null));
		assertEquals(Application.INTERVIEWING_NAME, b.getState());
		assertEquals("id", b.getReviewer());
		assertEquals("", b.getNote());
		b.update(new Command(Command.CommandValue.PROCESS, null));
		assertEquals(Application.PROCESSING_NAME, b.getState());
		assertEquals("id", b.getReviewer());
		assertEquals("", b.getNote());
		b.update(new Command(Command.CommandValue.HIRE, null));
		assertEquals(Application.HIRED_NAME, b.getState() );
		assertEquals("id", b.getReviewer());
		assertEquals("", b.getNote());
		b.update(new Command(Command.CommandValue.TERMINATE, "Resigned"));
		assertEquals(Application.INACTIVE_NAME, b.getState());
		assertEquals("id", b.getReviewer());
		assertEquals("Resigned", b.getNote());
		
		Application c = new Application("Charlie", "Maku", "cmaku81");
		assertEquals("Charlie", c.getFirstName());
		assertEquals("Maku", c.getSurname());
		assertEquals("cmaku81", c.getUnityId());
		assertEquals(3, c.getId());
		assertEquals("Submitted", c.getState());
		assertEquals("", c.getReviewer());
		assertEquals("", c.getNote());
		
		c.update(new Command(Command.CommandValue.REJECT, "Qualifications"));
		assertEquals("Rejected", c.getState());
		assertEquals("", c.getReviewer());
		assertEquals("Qualifications", c.getNote());
		c.update(new Command(Command.CommandValue.RESUBMIT, null));
		assertEquals("Submitted", c.getState());
		assertEquals("", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals("Reviewing", c.getState());
		assertEquals("id", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.RETURN, null));
		assertEquals("Submitted", c.getState());
		assertEquals("", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals("Reviewing", c.getState());
		assertEquals("id", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.SCHEDULE, null));
		assertEquals("Interviewing", c.getState());
		assertEquals("id", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.SCHEDULE, null));
		assertEquals("Interviewing", c.getState());
		assertEquals("id", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.ASSIGN, "id2"));
		assertEquals("Reviewing", c.getState());
		assertEquals("id2", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.SCHEDULE, null));
		assertEquals("Interviewing", c.getState());
		assertEquals("id2", c.getReviewer());
		assertEquals("", c.getNote());
		c.update(new Command(Command.CommandValue.REJECT, "Positions"));
		assertEquals("Rejected", c.getState());
		assertEquals("", c.getReviewer());
		assertEquals("Positions", c.getNote());
	}
	
	/**
	 * Tests Application.update() method for invalid transitions
	 */
	@Test
	public void testUpdateApplicationsInvalidCommand() {
		Application a = new Application("Alex", "Zane", "azane5");
		assertEquals("Alex", a.getFirstName());
		assertEquals("Zane", a.getSurname());
		assertEquals("azane5", a.getUnityId());
		assertEquals(1, a.getId());
		assertEquals("Submitted", a.getState());
		assertEquals("", a.getReviewer());
		assertEquals("", a.getNote());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.HIRE, null)));
		assertEquals("Invalid command.", e1.getMessage());
		
		a.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals(Application.REVIEWING_NAME, a.getState());
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.TERMINATE, "Fired")));
		assertEquals("Invalid command.", e1.getMessage());
		
		a.update(new Command(Command.CommandValue.SCHEDULE, null));
		assertEquals(Application.INTERVIEWING_NAME, a.getState());
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.RESUBMIT, null)));
		assertEquals("Invalid command.", e1.getMessage());	
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.REJECT, "random")));
		assertEquals("Invalid command.", e1.getMessage());
		
		a.update(new Command(Command.CommandValue.PROCESS, null));
		assertEquals(Application.PROCESSING_NAME, a.getState());
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.ASSIGN, "id")));
		assertEquals("Invalid command.", e1.getMessage());	
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.REJECT, "random")));
		assertEquals("Invalid command.", e1.getMessage());
		
		a.update(new Command(Command.CommandValue.HIRE, null));
		assertEquals(Application.HIRED_NAME, a.getState());
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.HIRE, null)));
		assertEquals("Invalid command.", e1.getMessage());	
		
		a.update(new Command(Command.CommandValue.TERMINATE, "Fired"));
		assertEquals(Application.INACTIVE_NAME, a.getState());
		e1 = assertThrows(UnsupportedOperationException.class,
				() -> a.update(new Command(Command.CommandValue.RESUBMIT, null)));
		assertEquals("Invalid command.", e1.getMessage());	
		
		Application b = new Application("Ben", "York", "byork2");
		assertEquals("Ben", b.getFirstName());
		assertEquals("York", b.getSurname());
		assertEquals("byork2", b.getUnityId());
		assertEquals(2, b.getId());
		assertEquals("Submitted", b.getState());
		assertEquals("", b.getReviewer());
		assertEquals("", b.getNote());
		
		Exception e2 = assertThrows(UnsupportedOperationException.class,
				() -> b.update(new Command(Command.CommandValue.REJECT, "random")));
		assertEquals("Invalid command.", e2.getMessage());

		b.update(new Command(Command.CommandValue.ASSIGN, "id"));
		assertEquals("Reviewing", b.getState());
		assertEquals("id", b.getReviewer());
		assertEquals("", b.getNote());
		e2 = assertThrows(UnsupportedOperationException.class,
				() -> b.update(new Command(Command.CommandValue.REJECT, "random")));
		assertEquals("Invalid command.", e2.getMessage());
		b.update(new Command(Command.CommandValue.REJECT, "Qualifications"));
		assertEquals("Rejected", b.getState());
		assertEquals("", b.getReviewer());
		assertEquals("Qualifications", b.getNote());
		e2 = assertThrows(UnsupportedOperationException.class,
				() -> b.update(new Command(Command.CommandValue.HIRE, null)));
		assertEquals("Invalid command.", e2.getMessage());
	}
}
