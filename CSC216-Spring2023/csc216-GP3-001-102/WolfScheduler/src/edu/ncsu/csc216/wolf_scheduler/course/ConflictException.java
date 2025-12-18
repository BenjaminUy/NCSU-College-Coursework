/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class defines the custom exception, a ConflictException, which is thrown when
 * an Activity, if added to the schedule, would cause a conflict or an overlap in
 * between two Activity 's times.
 * @author Benjamin Uy
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parent constructor of ConflictException that uses the given String to
	 * specify the message of the Exception object.
	 * @param message message that is shown when a ConflictException exists
	 */
	ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructor of ConflictException that is parameterless; calls the parent constructor
	 * with a default message of "Schedule conflict."
	 */
	ConflictException(){
		this("Schedule conflict.");
	}

}
