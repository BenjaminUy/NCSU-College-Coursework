package edu.ncsu.csc216.pack_scheduler.user.schedule;


import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Class with similar functionality to WolfScheduler in the Guided Projects. Has functionality to add and remove courses,
 * reset the Schedule, and get a string representation of the Schedule
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public class Schedule {
	
	/** String representing title of the Schedule object */
	private String title;
	
	/** ArrayList that contains multiple Course objects */
	private ArrayList<Course> schedule;
	
	/**
	 * Constructor method for Schedule object; creates a new array list of Course objects for schedule
	 * and sets the title to the default title "My Schedule"
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * This method adds Courses to Schedule. If the same course you
	 * are trying to add is in Schedule an exception is thrown.
	 * An exception is also thrown if the course isn't in the catalog.
	 * 
	 * @param c Course object to add to Schedule
	 * @return true if a Course is successfully added to Schedule, false if not
	 * @throws IllegalArgumentException if a Course is already in Schedule
	 * @throws IllegalArgumentException if a ConflictException is caught
	 */
	public boolean addCourseToSchedule(Course c) {
		
		for (int i = 0; i < this.schedule.size(); i++ ) {
			// uses isDuplicate to check if Course is duplicate
			if (this.schedule.get(i).isDuplicate(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			
		}
		this.schedule.add(c);
		try {
			for (int i = 0; i < this.schedule.size() - 1; i++ ) {
					this.schedule.get(i).checkConflict(c);
			}
		} catch (ConflictException ce) {
			removeCourseFromSchedule(c);
			throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		}
		return true;
	}
	
	/**
	 * Method attempts to remove given Course object from the Schedule and returns
	 * true if successful
	 * @param c Course object to remove from Schedule
	 * @return true if the given Course was removed from the Schedule, false if not
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if (c == null)
			return false;
		for (int i = 0; i < schedule.size(); i++) {
			if (c.equals(schedule.get(i))) {
				this.schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method sets Schedule to a new ArrayList.
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * This method returns a 2D String array with the Scheduled courses. If the schedule is empty
	 * an empty array is returned. The course name, section, title, meeting string, and open seats are displayed.
	 * 
	 * @return A 2D String array with some of Scheduled Course's info
	 */
	public String[][] getScheduledCourses() {
		if (this.schedule.size() == 0)
			return new String[0][0];
		String[][] scheduledActivities = new String[this.schedule.size()][5];
		for ( int i = 0; i < this.schedule.size(); i++ ) {
			scheduledActivities[i] = this.schedule.get(i).getShortDisplayArray();
		}
		return scheduledActivities;
	}
	
	/**
	 * Method sets the title of Schedule
	 * @param title title of Schedule
	 * @throws IllegalArgumentException if given title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Method returns title of Schedule
	 * @return title of Schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Method returns the cumulative sum of credits in the Schedule
	 * @return total credits in the Schedule
	 */
	public int getScheduleCredits () {
		int totalCredits = 0;
		for (int i = 0; i < schedule.size(); i++) {
			totalCredits = totalCredits + schedule.get(i).getCredits();
		}
		return totalCredits;
	}
	
	/**
	 * Method returns true if the Course can be added to the Schedule
	 * @param c Course object that could be added to Schedule
	 * @return true if the Course can be added to the Schedule
	 * 		returns false if the Course is null, is a duplicate, or there is a conflict
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
			if (c.isDuplicate(schedule.get(i))) {
				return false;
			}
		}
		return true;
	}
	
}
