package edu.ncsu.csc216.wolf_hire.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class tests Command.java
 * @author Benjamin Uy
 */
public class CommandTest {
	
	
	/**
	 * Tests Command constructor and get methods with various inputs
	 */
	@Test
	public void commandConstructorTest() {
		Command c = new Command(Command.CommandValue.ASSIGN, "Reviewer ID");	// Create a valid Command object
		assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
		assertEquals("Reviewer ID", c.getCommandInformation());
		
		try {
			c = new Command(null, "Review");	// Invalid Command with a null CommandValue parameter
			fail();								// Constructor method should throw IAE, if not, fail test
		} catch (IllegalArgumentException e1) {
			assertEquals("Invalid information.", e1.getMessage());
		}
		
		try {
			c = new Command(Command.CommandValue.REJECT, "");	// Invalid Command with CommandValue of REJECT and empty command info string
			fail();												// Constructor method should throw IAE, if not, fail test
		} catch (IllegalArgumentException e2) {
			assertEquals("Invalid information.", e2.getMessage());
		}
		
		try {
			c = new Command(Command.CommandValue.HIRE, "Not empty");	// Invalid Command with CommandValue of HIRE and non-empty command info string
			fail();														// Constructor method should throw IAE, if not, fail test
		} catch (IllegalArgumentException e3) {
			assertEquals("Invalid information.", e3.getMessage());
		}
	}

}
