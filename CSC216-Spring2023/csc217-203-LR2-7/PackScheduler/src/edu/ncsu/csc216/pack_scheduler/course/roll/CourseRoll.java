package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Class that manages information about a Course's enrollment limits
 * and its accessibility to enrollment by other Students
 * @author hctynch
 * @author bsuy
 * @author ajwdr
 */
public class CourseRoll {
	/** A custom LinkedAbstractList of Student objects */ 
	private LinkedAbstractList<Student> roll;
	/** The roll's enrollment capacity */
	private int enrollmentCap;
	/** Constant for the smallest class size */
	private static final int MIN_ENROLLMENT = 10;
	/** Constant for the largest class size */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructor method of CourseRoll that creates an empty LinkedAbstractList of Student objects.
	 * The enrollmentCap is set to the given parameter, using setEnrollmentCap().
	 * @param enrollmentCap roll's enrollment capacity
	 */
	public CourseRoll(int enrollmentCap) {
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
	}
	
	/**
	 * Method returns the difference between the enrollmentCap and the size of the roll
	 * @return difference between the enrollmentCap and the roll size
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Method returns the enrollmentCap of the CourseRoll
	 * @return enrollmentCap of the CourseRoll
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Method sets the enrollmentCap of the CourseRoll
	 * @param enrollmentCap enrollment cap to set
	 * @throws IllegalArgumentException if enrollmentCap is less than 10 or is greater than 250
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT)
			throw new IllegalArgumentException("Invalid enrollment capacity");
		if (roll != null && enrollmentCap >= roll.size()) {
			roll.setCapacity(enrollmentCap);
			this.enrollmentCap = enrollmentCap;
		} else {
			throw new IllegalArgumentException("Invalid enrollment capacity");
		}
	}
	
	/**
	 * Method adds the given Student to the end of the roll
	 * @param s Student object to add to the roll
	 * @throws IllegalArgumentException if Student is null
	 * @throws IllegalArgumentException if there is capacity is already full
	 * @throws IllegalArgumentException if Student is already enrolled
	 * @throws IllegalArgumentException if adding the student to roll generates an exception
	 */
	public void enroll(Student s) {
		if (s == null || roll.size() == enrollmentCap) {
			throw new IllegalArgumentException("Can't enroll.");
		} 
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).compareTo(s) == 0) {
				throw new IllegalArgumentException("Can't enroll.");
			}
		}
		try {
			roll.add(s);
		} catch (IllegalArgumentException | NullPointerException | IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Can't enroll.");
		}
	}
	
	/**
	 * Method returns true if the Student can be added to the CourseRoll
	 * @param s Student object that could be added to the roll
	 * @return true if the Student can be added to the class, false if not
	 * @throws IllegalArgumentException if given parameter is null
	 */
	public boolean canEnroll(Student s) {
		if (s == null)
			throw new IllegalArgumentException("Can't enroll");
		if (getOpenSeats() <= 0)
			return false;
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method removes the given Student object from the roll.
	 * @param s Student object to remove from the roll
	 * @throws IllegalArgumentException if s is null
	 * @throws IllegalArgumentException if removing the student from roll
	 * 		generates an exception
	 */
	public void drop(Student s) {
		if (s == null)
			throw new IllegalArgumentException("Can't Drop");
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				try {
					roll.remove(i);
				} catch (IndexOutOfBoundsException ioobe) {
					throw new IllegalArgumentException("Can't Drop");
				}
			}
		}
	}
}
