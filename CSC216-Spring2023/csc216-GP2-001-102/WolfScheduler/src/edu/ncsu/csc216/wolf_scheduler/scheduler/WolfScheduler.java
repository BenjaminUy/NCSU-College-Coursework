package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Class reads in and stores as a list all of the Course records stored in a file. Class also provides
 * functions for creating a schedule, such as naming, adding/removing a course, and resetting
 * 
 * @author Benjamin Uy
 */
public class WolfScheduler {
	
	/**
	 * Field representing the course catalog
	 */
	private ArrayList<Course> catalog;
	
	/**
	 * Field representing the activity schedule
	 */
	private ArrayList<Activity> schedule;
	
	/**
	 * Field representing schedule title
	 */
	private String title;
	
	/**
	 * Constructs a WolfScheduler object using the given filename
	 * @param filename string representing file containing course records
	 * @throws IllegalArgumentException if CourseRecordIO.readCourseRecords() throws a fileNotFoundException
	 */
	public WolfScheduler(String filename) {
		schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file");
		}
	}

	/**
	 * Method takes the elements of the catalog ArrayList and copies them into a 2D string array.
	 * The 2D string array, after getting the elements for Course names, sections, and titles, will be returned.
	 * @return 2D string array of the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][3];
		
		// Loop goes through catalog and copies contents into a string array
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			catalogArray[i] = s.getShortDisplayArray();
		}
		
		return catalogArray;
	}

	/**
	 * Method takes the elements of the schedule ArrayList and copies them into a 2D string array.
	 * The 2D string array, after getting the elements for an Activity's fields, will be returned.
	 * @return 2D string array of the schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduleArray = new String[schedule.size()][3];
		
		// Loop goes through schedule and copies contents into a string array
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			scheduleArray[i] = a.getShortDisplayArray();
		}
		return scheduleArray;
	}

	/**
	 * Method takes the elements of the schedule ArrayList and copies them into a 2D string array.
	 * The 2D string array, after getting the elements for an Activity's fields, will be returned.
	 * @return 2D string array of the schedule with all info
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullScheduleArray = new String[schedule.size()][6];
		
		// Loop goes through schedule and copies contents into a string array
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
				fullScheduleArray[i] = a.getLongDisplayArray();
		}
		return fullScheduleArray;
	}

	/**
	 * Method searches through the elements of the catalog ArrayList to try and find a 
	 * Course with a given name and section. Returns null if the Course cannot be found
	 * @param name name of the Course to be found
	 * @param section section of the Course to be found
	 * @return Course with the given name and section, or null if not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course s = catalog.get(i);
			if (name.equals(s.getName()) && section.equals(s.getSection())) {
				return s;
			}
		}
		// return null is the Course with the given name/section is not found
		return null;
	}

	/**
	 * Method determines if a Course (represented by name and section) can be added to the schedule.
	 * A Course will be added to a schedule if it exists in the course catalog, and it is not already in
	 * the user's schedule. After checking these conditions, the Course will be added to the user's schedule.
	 * @param name name of the Course
	 * @param section section of the Course
	 * @return true if (1) the course exists in the catalog and (2) the course is not already in the user's schedule
	 * @throws IllegalArgumentException if the name of the course desired to be added matches with the name of a course
	 * 		in the user's schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
		if (getCourseFromCatalog(name, section) == null) {
			return false;
		}
		
		Course courseToAdd = getCourseFromCatalog(name, section);
		
		// Check to see if Course is already in schedule
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			if (courseToAdd.isDuplicate(a)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(courseToAdd);
		return true;
	}

	/**
	 * Method returns a boolean depending on whether an Activity can be removed from a schedule. An Activity will be removed if
	 * the given index of the Activity exists in the schedule ArrayList.
	 * @param idx index of the Activity that should be removed
	 * @return true if the Activity can be removed, false if not
	 * @throws IndexOutOfBoundsException if the given parameter is not an existing index value of in the schedule ArrayList
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	/**
	 * Creates an empty ArrayList for the schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
		
	}
	
	/**
	 * Gets the schedule title
	 * @return string representing schedule title
	 */
	public String getScheduleTitle() {
		return title;
	}
	
	/**
	 * Sets the title of the schedule
	 * @param newTitle string for desired title of the schedule
	 * @throws IllegalArgumentException if newTitle is null
	 */
	public void setScheduleTitle(String newTitle) {
		if (newTitle == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		
		this.title = newTitle;
	}

	/**
	 * Method exports the schedule to the given filename
	 * @param filename string of filename
	 * @throws IllegalArgumentException if CourseRecordIO.writeCourseRecords() throws an IOException
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}	
	
	/**
	 * Method creates a new Event from the given parameters and will add an Event to the schedule if the newly 
	 * created Event is not a duplicate of another Event in the schedule.
	 * @param eventTitle title of Event
	 * @param eventMeetingDays meeting days of Event
	 * @param eventStartTime starting time of Event
	 * @param eventEndTime ending time of Event
	 * @param eventDetails description of Event
	 * @throws IllegalArgumentException if the created event from the parameters is a duplicate of an Activity already in the schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event e = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		// Check to see if Event is already in schedule
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			if (e.isDuplicate(a)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		schedule.add(e);
	}
}
