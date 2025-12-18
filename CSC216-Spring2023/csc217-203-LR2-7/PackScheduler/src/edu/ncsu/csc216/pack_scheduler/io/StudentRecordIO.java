package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/** 
 * Handles inputting, processing, and outputting student records
 * @author Kartik Alle
 * @author Hunt Tynch
 * @author Connor Hekking
 */
public class StudentRecordIO {

	/**
	 * Reads a file with student records and returns an SortedList of students.
	 * @param fileName location of file to read
	 * @return returns SortedList of students from file
	 * @throws FileNotFoundException throws exception if the file is not found at the location
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Student> students = new SortedList<Student>(); //Create an empty SortedList of Student objects
	    while (fileReader.hasNextLine()) { 
	        try {
	        	Student student = processStudent(fileReader.nextLine()); 
	            students.add(student); //Add to the SortedList!
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a student, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the SortedList with all the students we read!
	    return students;
	}
	/**
	 * Writes student records to a specified file
	 * @param fileName the file that records will be written to
	 * @param studentDirectory the SortedList of student objects
	 * @throws IOException throws exception if unable to write to file
	 */
	
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}
	
	/**
	 * Helper method for readStudentRecords, 
	 * takes in a line of fields and returns a new student object.
	 * @param line line with student info to read
	 * @return returns new student object from given fields
	 */
	private static Student processStudent(String line) {
		Scanner reader = new Scanner(line);
		reader.useDelimiter(",");
		try {
			String firstName = reader.next();
			String lastName = reader.next();
			String id = reader.next();
			String email = reader.next();
			String hashPassword = reader.next();
			int maxCredits = reader.nextInt();
			
			reader.close();
			return new Student(firstName, lastName, id, email, hashPassword, maxCredits);
			
		} catch(NoSuchElementException e) {
			reader.close();
			throw new IllegalArgumentException();
		}
	}

}
