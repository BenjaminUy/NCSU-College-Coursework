/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A custom checked exception specifically made for schedule conflicts.
 * It constructs an exception with a custom message or a default message.
 * @author Noah Anderson
 * @author Benjamin Uy
 * @author Hank Lenham 
 */
public class ConflictException extends Exception {
	/**
	 * Constructs a ConflictException with a user-specified message
	 * @param message the user-specified message
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a ConflictException with a default message of "Schedule conflict."
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

}
