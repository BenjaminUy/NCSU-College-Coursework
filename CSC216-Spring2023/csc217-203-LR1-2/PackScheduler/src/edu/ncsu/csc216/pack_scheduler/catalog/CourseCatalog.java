package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Course catalog
 * @author Hank Lenham;
 * @author Noah Anderson;
 * @author Benjamin Uy;
 */
public class CourseCatalog {
	
	/**
	 * Instantiates the number of course columns for courses,
	 * one for name, section, title, and meeting information.
	 */
	private static final int SCHEDULE_COL = 4;
	
	/** A SortedList of Course s that make up the course catalog */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs a new empty catalog by calling newCourseCatalog().
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/** 
	 * Method also constructs an empty catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Attempts to load the courses from a file. If it fails, it throws an IAE saying the file could not be read.
	 * @param fileName the file to be read
	 * @throws IllegalArgumentException if the file cannot be read
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		}
		catch(FileNotFoundException e){
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	} 
	/**
	 * Adds a course to catalog.
	 * @param name the course's name
	 * @param title the course's title
	 * @param section the course's section
	 * @param credits the course's credits
	 * @param instructorId the instructor of the course's id
	 * @param meetingDays the course's meeting days
	 * @param startTime the course's start time
	 * @param endTime the course's end time
	 * @return true if the course can be added, if not, return false
	 * @throws IllegalArgumentException if there is an error adding the course
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
		//Retrieves the course from the catalog.
		Course course = getCourseFromCatalog(name, section);
		//If the course does not exist in the catalog, construct new Course with given parameters and return true.
		if(course == null) {
			course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			this.catalog.add(course);
			return true;
		}
		//Iterate through the schedule. If any duplicate course
		//is found, throw a new exception letting the user know.
		for(int i = 0; i < this.catalog.size(); i++) {
			if(this.catalog.get(i).isDuplicate(course)) {
				return false;
			}
		}
		course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		this.catalog.add(course);
		return true;
	}
	/**
	 * Removes a course from the catalog.
	 * @param name the name of the course to remove
	 * @param section the section of the course to remove
	 * @return true if the course can be removed, false if not.
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		//Iterating through all courses in the catalog
		for(int i = 0; i < catalog.size(); i++) {
			//If the parameters match
			if(name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				//Remove this index in the catalog.
				this.catalog.remove(i);
				//Return true.
				return true;
			}
		}
		//Otherwise, return false.
		return false;
	}
	/**
	 * Gets a particular course from the catalog.
	 * @param name the course's name
	 * @param section the course's section 
	 * @return the course with the parameters
	 */
	public Course getCourseFromCatalog(String name, String section) {
		//Loop through all the courses in our course catalog
		for(int i = 0; i < this.catalog.size(); i++) {
			//If the course's name and section matches the parameters, return it.
			if(this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section)) {
				//If the names and sections match, return the course.
				return this.catalog.get(i);
			}
		}
		//Otherwise, return null.
		return null;
	}
	/**
	 * Gets the full course catalog as a 2D array
	 * @return the course catalog as a 2D array
	 */
	public String[][] getCourseCatalog() {
		//The number of courses inside the catalog.
		int numCourses = this.catalog.size();
		//If there are no courses in the catalog, return an empty 2D string array.
		if(numCourses == 0) {
			return new String[0][0];
		}
		/*
		 * If there are courses in the catalog, make a new 2D array with rows 
		 * for each course and columns for name, section, and title
		 */
		String[][] courseCatalog = new String[numCourses][SCHEDULE_COL];
		
		/*
		 * For the number of courses in the catalog, set the course
		 * to whatever is returned by short display array.
		 */
		for(int i = 0; i < numCourses; i++) {
			Course c = catalog.get(i);
			courseCatalog[i] = c.getShortDisplayArray();
		}
		//Return the catalog.
		return courseCatalog;
	}
	/**
	 * Attempts to save the course catalog to a file.
	 * @param fileName the name of the file to which we will save
	 */
	public void saveCourseCatalog(String fileName) {
		//Attempt to write the courses to a file with the given name.
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		}
		//Catch an IOException and throw an exception letting the user know
		//their file could not be saved.
		catch(IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
}
