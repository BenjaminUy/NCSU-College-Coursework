package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class representing a Student which extends the user class and implements the Comparable interface.
 * @author Kartik Alle
 * @author Hunt Tynch
 * @author Connor Hekking
 */
public class Student extends User implements Comparable<Student> {
	
	/** Instance of Schedule that holds a Student's courses */
	private Schedule schedule;
	
	/** Max number of credits the student can take */
	private int maxCredits;
	/** Max number of credits any student can take */
	public static final int MAX_CREDITS = 18;

	/**
	 * Constructs a student object using all fields. Calls setter methods.
	 * @param firstName the first name
	 * @param lastName The last name
	 * @param id The student id
	 * @param email the student's email
	 * @param hashPW The student's password
	 * @param maxCredits Max number of credits the student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	/**
	 * Constructs a student object using all fields except maxCredits. Calls calls the other constructor.
	 * @param firstName the first name
	 * @param lastName The last name
	 * @param id The student id
	 * @param email the student's email
	 * @param hashPW The student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}
	
	/**
	 * Returns student's maxCredits.
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the student's maxCredits.
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if maxCredits is below 3 or above 18
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18)
			throw new IllegalArgumentException("Invalid max credits");
		this.maxCredits = maxCredits;
	}

	/**
	 * A string representation of this object.
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}
	
	/**
	 * Overrides Comparable.compareTo() to check a Student's lastname, firstname, and ID against another Student.
	 * 
	 * @param o A Student Object
	 * @return 0 if the objects are the Same, 1 if this object is greater than the parameter, and -1 if this object is less than the parameter.
	 */
	@Override
	public int compareTo(Student o) {
		if (getLastName().compareTo(o.getLastName()) != 0) {
			return getLastName().compareTo(o.getLastName());
		} else if (getFirstName().compareTo(o.getFirstName()) != 0){
			return getFirstName().compareTo(o.getFirstName());
		} else if (getId().compareTo(o.getId()) != 0) {
			return getId().compareTo(o.getId());
		}
		return 0;
	}

	/**
	 * Method returns the hashCode for the fields of Student
	 * @return integer representing the hashCode for Student fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Method determines if two Student objects are equal
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
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Method gets the Schedule instance of the Student object
	 * @return Schedule instance of the Student object
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Method determines if a Course can be added to the Student's schedule
	 * @param c Course that can be added to Student's Schedule
	 * @return true if the Course is not null, isn't in the schedule, doesn't
	 * 		cause a conflict, and doesn't exceed the Student's max credits
	 */
	public boolean canAdd(Course c) {
		if (!schedule.canAdd(c)) {
			return false;
		} else if (schedule.getScheduleCredits() + c.getCredits() > maxCredits) {
			return false;
		}
		return true;
	}

}
