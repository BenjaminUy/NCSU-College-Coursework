/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for CourseNameValidator.
 * @author hctynch
 * @author bsuy
 * @author ajwarre5
 */
class CourseNameValidatorTest {

	/**
	 * Tests invalid paths that throw exceptions in CourseNameValidator.isValid().
	 */
	@Test
	void testIsValidInvalid() {
		CourseNameValidator cnv = new CourseNameValidator();
		
		//state 1
		Exception e1 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("4"));
	    assertEquals("Course name must start with a letter.", e1.getMessage());
	    
	    //state 1
	    e1 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("+"));
	    assertEquals("Course name can only contain letters and digits.", e1.getMessage());
	    
	    //state 0
	    Exception e2 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("."));
	    assertEquals("Course name can only contain letters and digits.", e2.getMessage());
	    
	    //state 1
	    Exception e3 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("hhhhh"));
	    assertEquals("Course name cannot start with more than 4 letters.", e3.getMessage());
	    
	    //state 1
	    Exception e4 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h."));
	    assertEquals("Course name can only contain letters and digits.", e4.getMessage());
	    
	    //state 2
	    Exception e5 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h1h"));
	    assertEquals("Course name must have 3 digits.", e5.getMessage());

	    //state 3
	    Exception e6 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h11h"));
	    assertEquals("Course name must have 3 digits.", e6.getMessage());

	    //state 4
	    Exception e7 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h1111"));
	    assertEquals("Course name can only have 3 digits.", e7.getMessage());
	
	    //state 5
	    Exception e8 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h111h1"));
	    assertEquals("Course name cannot contain digits after the suffix.", e8.getMessage());
 
	    //state 5
	    Exception e9 = assertThrows(InvalidTransitionException.class, () -> cnv.isValid("h111hh"));
	    assertEquals("Course name can only have a 1 letter suffix.", e9.getMessage());
	}
	
	/**
	 * Tests valid paths in CourseNameValidatorFSM.isValid().
	 */
	@Test
	void isValidTrue() {
		CourseNameValidator fsm = new CourseNameValidator();
		try {
			assertTrue(fsm.isValid("h111"));
			assertTrue(fsm.isValid("h111h"));
			assertTrue(fsm.isValid("hh111h"));
			assertTrue(fsm.isValid("hhh111h"));
			assertTrue(fsm.isValid("hhhh111h"));
			assertTrue(fsm.isValid("hh111"));
			assertTrue(fsm.isValid("hhh111"));
			assertTrue(fsm.isValid("hhhh111"));
		} catch (InvalidTransitionException ite) {
			fail();
		}
		
	}
	
	/**
	 * Tests invalid final states in CourseNameValidatorFSM.isValid().
	 */
	@Test 
	void isValidFalse() {
		CourseNameValidator fsm = new CourseNameValidator();
		try {
			assertFalse(fsm.isValid("hh1"));
			assertFalse(fsm.isValid("hhh1"));
			assertFalse(fsm.isValid("hhhh1"));
		} catch (InvalidTransitionException ite) {
			fail();
		}
	}

}
