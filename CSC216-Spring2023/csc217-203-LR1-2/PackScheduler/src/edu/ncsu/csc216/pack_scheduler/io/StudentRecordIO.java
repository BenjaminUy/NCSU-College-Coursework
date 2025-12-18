package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileInputStream;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Class manages the student records and deals with input and output.
 * This class contains no fields; it provides the functionality of reading from a given
 * file, converting lines within the file into Student objects, and outputting student
 * records to a file.
 * 
 * @author Benjamin Uy
 * @author Noah Anderson
 * @author Hank Lenham
 */
public class StudentRecordIO {
	
	/**
	 * This method allows us to convert a String line given from a file into a 
	 * Student object which we can then store. Throws an exception if the line
	 * is incorrectly formatted.
	 * @param line line that is read from a file
	 * @return returns a newly constructed Student from the given line
	 * @throws IllegalArgumentException if the line contains more tokens after 
	 * 		initializing maxCredits
	 * @throws IllegalArgumentException if a NoSuchElementException is caught
	 * 		when reading the line
	 */
	private static Student processStudent(String line) {
		//Make a new scanner to scan the lines from the file
		Scanner studentScan = new Scanner(line);
		//Use commas as the delimiter
		studentScan.useDelimiter(",");
		//Attempt to receive information from line and store that in variables
		//for our student.
		try {
			String firstName = studentScan.next();
			String lastName = studentScan.next();
			String id = studentScan.next();
			String email = studentScan.next();
			String hashPW = studentScan.next();
			int maxCredits = studentScan.nextInt();
			
			//if the line has another token then there is an error; throw 
			//an exception and close the scanner.
			if(studentScan.hasNext()) {
				studentScan.close();
				throw new IllegalArgumentException("Invalid student.");
			}
			//Close our scanner.
			studentScan.close();
			//Return the student constructed by our scanner.
			return new Student(firstName, lastName, id, email, hashPW, maxCredits);
		}
		//Catch any no such element exception, and throw an IAE instead.
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid student.");
		}
	}
	
	
	
	/**
	 * Method reads student records from a file with the given file name and outputs an
	 * ArrayList of Student objects. Throws an exception if unable to read records from the file.
	 * @param fileName name of the file
	 * @return an ArrayList of the student records
	 * @throws FileNotFoundException if unable to read records from the file
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		//Creates a scanner receiving the file given by the user and converts
		//those into student records in the form of an arrayList.
		Scanner studentRecordScanner = new Scanner(new FileInputStream(fileName));
		//Creates a new arrayList of Students to hold the records from the scanned file
		SortedList<Student> studentRecords = new SortedList<Student>();
		//While the file has a next line, we will continue scanning in student records.
		while(studentRecordScanner.hasNextLine()) {
				try {
					//Use our private method from above to construct a Student from 
					//the lines given in the file.
					Student student = processStudent(studentRecordScanner.nextLine());
					//Creating a flag to see if the new student matches one already in the records
					boolean duplicate = false;
					//Add all the students to our records.
					for(int i = 0; i < studentRecords.size(); i++) {
						//Get the student at index i
						Student current = studentRecords.get(i);
						if (student.getId().equals(current.getId())) {
							//If there is a duplicate, change the variable to true and break out of the loop.
							duplicate = true;
							break; 
						}
					}
					//If there is no duplicate, then add the student to the records.
					if(!duplicate) {
						studentRecords.add(student);
					}
				}
				catch(IllegalArgumentException e) {
					//Nothing will actually be thrown here.
				}
			}
		//Close scanner
		studentRecordScanner.close();
		//Return ArrayList of student records.
		return studentRecords;
	}
	/**
	 * Method writes student records to a file using a given file name and a ArrayList of Student
	 * objects; throws an exception if unable to write to the file.
	 * @param fileName name of the file
	 * @param studentDirectory ArrayList of the student records (directory)
	 * @throws IOException if unable to write to the file
	 * @throws IllegalArgumentException if an IOException is caught
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
			//We create a new FileInputStream to ensure for password encryption,
			//and write the data to a new File.
			PrintStream studentRecordWriter = new PrintStream(new FileOutputStream(fileName));
			//Loop through the size of our records
			for(int i = 0; i < studentDirectory.size(); i++) {
			//On a new line each time, print the current student record converted to a string.
			studentRecordWriter.println(studentDirectory.get(i).toString());
			}
			//Close our writer.
			studentRecordWriter.close();
	}

}
