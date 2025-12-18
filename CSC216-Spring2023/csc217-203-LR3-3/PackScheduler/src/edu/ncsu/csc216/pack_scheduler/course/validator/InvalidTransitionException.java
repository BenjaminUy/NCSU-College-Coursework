/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class representing a custom exception, InvalidTransitionException, which is
 * thrown if it is determined by the FSM that a transition is inappropriate.
 * @author ajwdr
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parent constructor for InvalidTransitionException that uses string
	 * @param message error message as shown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor of InvalidTransitionException with default message
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
	
	
}
