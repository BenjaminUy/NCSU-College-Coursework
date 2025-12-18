/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests ConflictException class.
 * @author Benjamin Uy
 */
class ConflictExceptionTest {
	
	/**
	 * Tests parameterized constructor of ConflictException.java
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}
	
	/**
	 * Tests parameterless constructor of ConflictException.java
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
