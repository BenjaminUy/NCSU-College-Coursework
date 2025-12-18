/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for ConflictException
 * @author simba
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}. 
	 * Tests constructor with message parameter.
	 */
	@Test
	void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom Exception Message.");
		assertEquals("Custom Exception Message.", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}. 
	 * Tests parameterless constructor.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
