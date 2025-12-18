package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests InvalidTransitionException class
 * @author hctynch
 * @author ajwdr
 * @author bsuy
 */
class InvalidTransitionExceptionTest {

	/**
	 * tests parameterized constructor
	 */
	@Test
	void testInvalidTransitionString() {
		InvalidTransitionException ie = new InvalidTransitionException("Custom message");
		assertEquals("Custom message", ie.getMessage());
	}
	
	/**
	 * tests default constructor
	 */
	@Test
	void testInvalidTransition() {
		InvalidTransitionException ie = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ie.getMessage());
	}

}
