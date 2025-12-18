package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

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
	/** A LinkedAbstractList to store students in our wait list. */
	private LinkedQueue<Student> waitlist;
	/** The roll's enrollment capacity */
	private int enrollmentCap;
	/** Constant for the smallest class size */
	private static final int MIN_ENROLLMENT = 10;
	/** Constant for the largest class size */
	private static final int MAX_ENROLLMENT = 250;
	/** Constant for the size of our wait list. */
	private static final int WAITLIST_SIZE = 10;
	/** The current course. */
	private Course currentCourse;
	
	/**
	 * Constructor method of CourseRoll that creates an empty LinkedAbstractList of Student objects.
	 * The enrollmentCap is set to the given parameter, using setEnrollmentCap().
	 * @param enrollmentCap roll's enrollment capacity
	 * @param c the course to which our course roll belongs
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if(c == null) {
			throw new IllegalArgumentException("Invalid course.");
		}
		currentCourse = c;
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
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
		if (s == null) {
			throw new IllegalArgumentException("Can't enroll.");
		}
		//If the roll already contains the student, we can't enroll them.
		if(roll.contains(s)) {
			throw new IllegalArgumentException("Can't enroll.");
		}
		//Otherwise, we can check if there is size in the roll to add the student.
		else {
			//If the roll is at maximum capacity
			if(roll.size() == enrollmentCap) {
				//If the wait list is at capacity, throw an IAE.
				if(waitlist.size() == WAITLIST_SIZE) {
					throw new IllegalArgumentException("Can't enroll.");
				}
				//If canEnroll() STILL returns true, that means we CAN add
				//the student to the wait list.
				if(canEnroll(s)) {
					//So we do so.
					waitlist.enqueue(s);
				}
			}
			//If the roll isn't at maximum capacity, we can add the student to
			//the roll, not the wait list.
			try {
				roll.add(s);
			}
			catch(IllegalArgumentException e) {
				//Do nothing
			}
		}
	}
	
	/**
	 * Method returns true if the Student can be added to the CourseRoll
	 * @param s Student object that could be added to the roll
	 * @return true if the Student can be added to the class, false if not
	 * @throws IllegalArgumentException if given parameter is null
	 */
	public boolean canEnroll(Student s) {
		if(roll.size() != enrollmentCap) {
			if(!roll.contains(s)) {
				return true;
			}
		}
		else {
			if(!waitlist.contains(s) && waitlist.size() < WAITLIST_SIZE) {
				return true;
			}
		}
		return false;
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
		if(roll.contains(s)) {
			try {
				//Drop the old student from the roll.
				roll.remove(s);
				//If the wait list is not empty
				if(waitlist.size() != 0) {
					//Creates a new student which was first in the queue.
					Student st2 = waitlist.dequeue();
					//Add the student dequeued from the list to the roll.
					roll.add(st2);
					//Add the current course to the student's schedule.
					st2.getSchedule().addCourseToSchedule(currentCourse);
				}
			} catch (IndexOutOfBoundsException ioobe) {
				throw new IllegalArgumentException("Can't Drop");
			}
		}
		else if(waitlist.contains(s)) {
			waitlist.dequeue();
		}
	}
	
	/**
	 * Gets the number of students on the wait list
	 * @return the integer representing the number of students on the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
