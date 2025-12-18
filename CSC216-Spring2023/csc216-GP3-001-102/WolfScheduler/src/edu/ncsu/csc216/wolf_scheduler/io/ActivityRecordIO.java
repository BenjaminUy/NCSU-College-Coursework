package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes activities to file.
 * @author Benjamin Uy
 */
public class ActivityRecordIO {

	/**
	 * Method writes the ArrayList of Courses to the file with the given fileName
	 * @param fileName string representing desired file to write the course records to
	 * @param activities ArrayList that contains information on multiple Course objects
	 * @throws IOException if the file cannot be written to
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (Activity a : activities) {
			fileWriter.println(a.toString());
		}
	
		fileWriter.close();        
	}

}
