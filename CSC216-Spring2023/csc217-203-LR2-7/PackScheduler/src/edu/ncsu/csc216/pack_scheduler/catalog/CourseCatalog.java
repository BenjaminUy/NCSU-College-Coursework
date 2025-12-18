package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This Class constructs a CourseCatalog. The CourseCatalog holds Course objects that can
 * be created manually or read in from a file. You can access specific courses in a catalog
 * or all of them, and you can also export the CourseCatalog to a new File.
 * 
 * @author Hunt Tynch
 * @author Kartik Alle
 * @author Connor Hekking
 */
public class CourseCatalog {
	/** SortedList to hold courses*/
	private SortedList<Course> catalog;
	
	/**
	 * Constructor Class for CourseCatalog calls on NewCourseCatalog() to make
	 * an empty SortedList.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	/**
	 * Sets catalog field to an empty SortedList.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	/**
	 * Takes in a file and CourseRecordIO to create course objects and return them to the catalog field.
	 * @param filename file to read courses from.
	 * @throws IllegalArgumentException if filename is unable to be read.
	 */
	public void loadCoursesFromFile(String filename) {
		try {
			this.catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (FileNotFoundException fnfe) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	}
	/**
	 * The method adds Course Objects to the Catalog.
	 * @param name Course Name
	 * @param title Course Title
	 * @param section Course Section
	 * @param credits Course Credits
	 * @param instructorId Course Instructor Id
	 * @param enrollmentCap enrollment capacity of Course
	 * @param meetingDays Course meeting days
	 * @param startTime Course Start time
	 * @param endTime Course end time
	 * @return true if the new Course is added to the Catalog.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < this.catalog.size(); i++ ) {
			if (this.catalog.get(i).compareTo(c) == 0) {
				return false;
			}
			
		}
		catalog.add(c);
		return true;
		
	}
	/**
	 * This method removes courses from the Catalog. Returns true if the Course is successfully removed and, otherwise false
	 * @param name Course Name
	 * @param section Course Section
	 * @return true if the Course is successfully removed from the Catalog.
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
		if (c == null) {
			return false;
		} else {
			this.catalog.remove(this.catalog.indexOf(c));
		}
		return true;
	}
	/**
	 * This method gets courses from the Catalog using the given name and section
	 * @param name Course Name
	 * @param section Course Section
	 * @return Course if it is in the Catalog.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			Course c = this.catalog.get(i);
			if (name.equals(c.getName()) && section.equals(c.getSection())) {
				return c;
			}
		}
		return null;
	}
	/**
	 * This method returns a 2d String Array contains the Course catalog. The fields
	 * Name, Section, Title, meetingString, and open seats from each course in the Catalog are
	 * are in the Array.
	 * @return An Array with each of the Courses in the Catalog information.
	 */
	public String[][] getCourseCatalog(){
		if (this.catalog == null || this.catalog.size() == 0) {
			return new String[0][0];
		}
		String[][] courseCatalog = new String[this.catalog.size()][5];
		for (int i = 0; i < this.catalog.size(); i++) {
			Course c = this.catalog.get(i);
			courseCatalog[i][0] = c.getName();
			courseCatalog[i][1] = c.getSection();
			courseCatalog[i][2] = c.getTitle();
			courseCatalog[i][3] = c.getMeetingString();
			courseCatalog[i][4] = "" + c.getCourseRoll().getOpenSeats();
		}
		return courseCatalog;
	}
	/**
	 * Saves the Catalog to a file with the given filename
	 * @param filename file to save Catalog to.
	 * @throws IllegalArgumentException if file cannot be saved.
	 */
	public void saveCourseCatalog(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("Null parameter.");
		}
		try {
			CourseRecordIO.writeCourseRecords(filename, this.catalog);
		} catch (IOException ioe) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}
}
