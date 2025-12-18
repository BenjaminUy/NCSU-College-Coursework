/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict exception class for use in WolfScheduler
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for ConflictException
	 * @param message conflict message
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for ConflictException with no parameters. 
	 * Default message is "Schedule conflict."
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
