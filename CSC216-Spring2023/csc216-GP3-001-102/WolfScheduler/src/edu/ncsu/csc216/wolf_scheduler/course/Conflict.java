package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Interface that checks conflict specific to the Activity hierarchy. 
 * Has a method that determines if a given Activity will cause a scheduling conflict.
 * 
 * @author Benjamin Uy
 */
public interface Conflict {

	/**
	 * Method accepts an Activity object as a parameter and determines if the given Activity will
	 * cause a conflict which is indicated by whether this method throws a custom ConflictException.
	 * @param possibleConflictingActivity Activity that may cause a conflict, if added to the schedule
	 * @throws ConflictException if possibleConflictingActivity is determined to cause a conflict,
	 * 		should it be added to the schedule.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
	
}
