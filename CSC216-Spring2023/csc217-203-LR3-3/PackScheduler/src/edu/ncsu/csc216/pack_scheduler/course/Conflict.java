package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict interface requires a method to check if activities conflict.
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public interface Conflict {
	/**
	 * Checks for a time conflict with the given activity
	 * @param possibleConflictingActivity the activity to check for conflict
	 * @throws ConflictException if the two activities have conflicting times
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
