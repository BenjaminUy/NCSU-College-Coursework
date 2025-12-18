/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import edu.ncsu.csc217.collections.list.SortedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Handles I/O for the PackScheduler project.
 * @author Hunt Tynch
 * @author Connor Hekking
 * @author Kartik Alle
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the SortedList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the SortedList with all the courses we read!
	    return courses;
	}
	/**
	 * Takes in a string containing course information, 
	 * reads each of the fields and returns a new course object using each provided field.
	 * @param nextLine receive next line containing Course information
	 * @return returns a new Course object, given that the line was formatted correctly
	 */
	private static Course readCourse(String nextLine) {
		Scanner reader = new Scanner(nextLine);
		reader.useDelimiter(",");
		try {
			String name = reader.next();
			String title = reader.next();
			String section = reader.next();
			int credits = Integer.parseInt(reader.next());
			String instructor = reader.next();
			int enrollmentCap = Integer.parseInt(reader.next());
			String meetingDays = reader.next();
			
			if("A".equals(meetingDays)) {
				if(reader.hasNext()) {
					reader.close();
					throw new IllegalArgumentException();
				} else {
					reader.close();
					return new Course(name, title, section, credits, instructor, enrollmentCap, meetingDays);
				}
			} else {
				int startTime = Integer.parseInt(reader.next());
				int endTime = Integer.parseInt(reader.next());
				if(reader.hasNext()) {
					reader.close();
					throw new IllegalArgumentException();
				}
				reader.close();
				return new Course(name, title, section, credits, instructor, enrollmentCap, meetingDays, startTime, endTime);
			}
		} catch(NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param courses  list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
