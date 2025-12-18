/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ConflictException class.
 * @author Noah Anderson
 *
 */
class ConflictExceptionTest {
 
	/**
	 * Test method for ConflictException constructor with custom user-submitted exception message.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for ConflictException constructor with default message.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
