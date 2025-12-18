package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Class representing an individual faculty record. Has similar implementation to Student;
 * the main difference between the two is that Faculty objects keep track of the number of
 * courses they can teach in a semester.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
public class Faculty extends User {
	
	/** Max number of courses that Faculty can teach in a semester */
	private int maxCourses;
	/** Constant for the minimum number of courses Faculty can teach in a semester */
	private static final int MIN_COURSES = 1;
	/** Constant for the maximum number of courses Faculty can teach in a semester */	
	private static final int MAX_COURSES = 3;
	/** The Faculty's course schedule */
	private FacultySchedule schedule;

	/**
	 * Constructor method for Faculty object. Calls the 
	 * @param firstname first name of Faculty
	 * @param lastname last name of Faculty
	 * @param id unity id of Faculty
	 * @param email email of Faculty
	 * @param password password of Faculty
	 * @param maxCourses max courses of Faculty
	 */
	public Faculty(String firstname, String lastname, String id, String email, String password, int maxCourses) {
		super(firstname, lastname, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
 
	/**
	 * Method sets maxCourses to the given parameter
	 * @param maxCourses max number of courses Faculty can teach
	 * @throws IllegalArgumentException if given parameter is not between 1 and 3, inclusive
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Method returns maxCourses for Faculty
	 * @return the max number of courses Faculty can teach
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Method returns the hashCode for the fields of Faculty
	 * @return integer representing the hashCode for Faculty fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Method determines if two Faculty objects are equal by comparing
	 * the object's instance, class, and maxCourses field
	 * @return true if the given object is equal to this object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Method returns a String representation of the Faculty object containing first name,
	 * last name, id, email, password, and max courses
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + getMaxCourses();
	}
	
	/**
	 * Returns the Faculty's course schedule as a FacultySchedule object.
	 * @return the schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Finds if the Faculty is overloaded, which means the number of scheduled courses is greater
	 * than the Faculty's max number of courses.
	 * @return true if the Faculty is overloaded, false if not
	 */
	public boolean isOverloaded() {
		return getSchedule().getNumScheduledCourses() > getMaxCourses();
	}
}
