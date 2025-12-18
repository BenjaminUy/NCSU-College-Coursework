package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict interface handles checking for schedule conflicts and conflict exceptions.
 * It contains an abstract method that checks for schedule conflicts.
 * @author Noah Anderson
 * @author Benjamin Uy
 * @author Hank Lenham
 */ 
public interface Conflict {
	/**
	 * Checks for time conflicts when adding an activity to the schedule.
	 * Throws a ConflictException if this activity overlaps in time by at least one minute,
	 * on at least one day with possibleConflictingActivity
	 * @param possibleConflictingActivity The activity that is being compared to
	 * @throws ConflictException If this activity conflicts with the possibleConflictingActivity
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
