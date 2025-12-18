package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyRecordIO contains three methods relating to the Faculty class. This
 * includes a method to read Faculty in from a file, and write Faculty records
 * to a file. It also contains a private method to process one Faculty from a
 * file as a helper method to the public method.
 * 
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
public class FacultyRecordIO {

	/**
	 * Reads in Faculty from a specified file and creates a LinkedList full of
	 * faculty objects from the file. Will use processFaculty() as a helper method
	 * in order to break up the work.
	 * 
	 * @param fileName the name of the file we will read from
	 * @return a LinkedList of Faculty read in from the file
	 * @throws FileNotFoundException if we cannot find the file to read from
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> faculties = new LinkedList<Faculty>();
		while(fileReader.hasNextLine()) {
			try {
				Faculty f = processFaculty(fileReader.nextLine());
				faculties.add(f);
			}
			catch(IllegalArgumentException e) {
				// Do nothing
			}
		}
		fileReader.close();
		return faculties;
	}

	/**
	 * Helper method to process a single faculty member from a line
	 * 
	 * @param facultyText the text for one string of faculty
	 * @return a faculty object based on the string of text
	 * @throws IllegalArgumentException if a NoSuchElementException is found
	 */
	private static Faculty processFaculty(String facultyText) {
		Scanner facultyScan = new Scanner(facultyText);
		facultyScan.useDelimiter(",");
		try {
			String firstName = facultyScan.next();
			String lastName = facultyScan.next();
			String id = facultyScan.next();
			String email = facultyScan.next();
			String pw = facultyScan.next();
			int maxCourses = Integer.parseInt(facultyScan.next());
			
			facultyScan.close();
			return new Faculty(firstName, lastName, id, email, pw, maxCourses);
		}
		catch(NoSuchElementException e) {
			facultyScan.close();
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Writes our faculty records to a file.
	 * 
	 * @param fileName  the name of the file we will write to/create
	 * @param faculties the list of faculty members we will write
	 * @throws IOException if we cannot successfully write to a file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> faculties) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(Faculty f : faculties) {
			fileWriter.println(f.toString());
		}
		fileWriter.close();
	}
}
