package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files, and gets info about a Course in order name, title, section
 * credits, instructorId, meeting days, and start/end times, if appropriate
 * Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 * @author Benjamin Uy
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
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
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Method reads a string line from a file and stores the information into a Course object
	 * @param nextLine line that is read from the Scanner
	 * @return a Course object from the information in the line
	 * @throws IllegalArgumentException if "A" is in meetingDays and there are more tokens in the line
	 * @throws IllegalArgumentException if there are more tokens in the nextLine after the start and end time tokens
	 * @throws IllegalArgumentException if the scanner cannot read the line
	 */
    private static Course readCourse(String nextLine) {
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		
		String name = "";
		String title = "";
		String section = "";
		int credits = 0;
		String instructorId = "";
		String meetingDays = "";
		int startTime = 0;
		int endTime = 0;
		
		try {
			name = s.next();
			title = s.next();
			section = s.next();
			credits = Integer.parseInt(s.next());
			instructorId = s.next();
			meetingDays = s.next();
			
			if ("A".equals(meetingDays)) {
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException("Invalid");
				} else {
					s.close();
					Course c = new Course(name, title, section, credits, instructorId, meetingDays);
					return c;
				}
			} else {
				startTime = Integer.parseInt(s.next());
				endTime = Integer.parseInt(s.next());
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException("Invalid");
				} else {
					s.close();
				}
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Error reading tokens");
		}
		
		Course c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		return c;
		
	}

	/**
     * Method writes the ArrayList of Courses to the file with the given fileName
     * @param fileName string representing desired file to write the course records to
     * @param courses ArrayList that contains information on multiple Course objects
     * @throws IOException if the file cannot be written to
     */
    public static void writeCourseRecords(String fileName, ArrayList<Course> courses) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	fileWriter.close();        
    }
    

}