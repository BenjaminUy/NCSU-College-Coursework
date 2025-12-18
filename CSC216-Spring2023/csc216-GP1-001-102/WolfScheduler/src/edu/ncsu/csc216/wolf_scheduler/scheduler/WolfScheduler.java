package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
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
	 * Field representing the course schedule
	 */
	private ArrayList<Course> schedule;
	
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
		schedule = new ArrayList<Course>();
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
			catalogArray[i][0] = s.getName();
			catalogArray[i][1] = s.getSection();
			catalogArray[i][2] = s.getTitle();
		}
		
		return catalogArray;
	}

	/**
	 * Method takes the elements of the schedule ArrayList and copies them into a 2D string array.
	 * The 2D string array, after getting the elements for Course names, sections, and titles, will be returned.
	 * @return 2D string array of the schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[schedule.size()][3];
		
		// Loop goes through schedule and copies contents into a string array
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			scheduleArray[i][0] = s.getName();
			scheduleArray[i][1] = s.getSection();
			scheduleArray[i][2] = s.getTitle();
		}
		
		return scheduleArray;
	}

	/**
	 * Method takes the elements of the schedule ArrayList and copies them into a 2D string array.
	 * The 2D string array, after getting the elements for Course names, sections, titles, credits,
	 * instructor IDs, and meeting days, will be returned.
	 * @return 2D string array of the schedule with all info
	 */
	public String[][] getFullScheduledCourses() {
		String[][] fullScheduleArray = new String[schedule.size()][6];
		
		// Loop goes through schedule and copies contents into a string array
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
				fullScheduleArray[i][0] = s.getName();
				fullScheduleArray[i][1] = s.getSection();
				fullScheduleArray[i][2] = s.getTitle();
				fullScheduleArray[i][3] = "" + s.getCredits();
				fullScheduleArray[i][4] = s.getInstructorId();
				fullScheduleArray[i][5] = s.getMeetingString();
		}
		
		return fullScheduleArray;
	}

	/**
	 * Method searches through the elements of the catalog ArrayList to try and find a 
	 * Course with a given name and section. Returns null if the Course cannot be found
	 * @param name name of the course to be found
	 * @param section section of the course to be found
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
		
		// Check to see if Course is already in schedule
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			if (name.equals(s.getName())) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		Course addition = getCourseFromCatalog(name, section);
		schedule.add(addition);
		return true;
	}

	/**
	 * Method returns a boolean depending on whether a Course can be removed from a schedule. A Course will be removed if
	 * the given name and section both match to the same Course within the user's schedule
	 * @param name name of the course
	 * @param section section of the course
	 * @return true if the Course can be removed, false if not
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		// Loop checks to see if Course with given name and section exists in schedule, if it does, it will be removed
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			if (name.equals(s.getName()) && section.equals(s.getSection())) {
				schedule.remove(s);
				return true;
			}
		}
	
		return false;
	}

	/**
	 * Creates an empty ArrayList for the schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		
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
			CourseRecordIO.writeCourseRecords(filename, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}	
}
