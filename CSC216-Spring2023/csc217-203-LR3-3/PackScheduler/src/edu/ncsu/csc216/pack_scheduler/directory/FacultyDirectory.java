/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * The FacultyDirectory class maintains a list of faculty in the form of a linked list.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
public class FacultyDirectory {
	/** The internal LinkedList that stores the list of faculty */
	private LinkedList<Faculty> facultyDirectory;
	/** The SHA-256 hash algorithm used to hash faculty passwords. */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * The constructor for the faculty directory. Calls a helper method that makes a new directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Sets the facultyDirectory to a new linked list of faculty.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads and stores a linked list of faculty from the file with the passed file name.
	 * @param fileName the path of the file to read from
	 * @throws IllegalArgumentException if there are any errors in reading the file
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Attempts to add a new faculty to the faculty directory.
	 * Hashes the passwords for security before the new faculty is stored.
	 * 
	 * @param firstName the first name of the faculty
	 * @param lastName the last name of the faculty
	 * @param id the unity id of the faculty
	 * @param email the email of the faculty
	 * @param password the password of the faculty
	 * @param repeatPassword the repeated password of the faculty
	 * @param maxCourses the maximum number of courses the faculty can teach
	 * @return true if the faculty is added, valid and is not a duplicate
	 * @throws IllegalArgumentException if the passwords don't match or are invalid
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty f = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (f.getId().equals(facultyDirectory.get(i).getId())) {
				return false;
			}
		}
		
		facultyDirectory.add(f);
		return true;
	}

	/**
	 * Attempts to remove a faculty with the selected ID from the faculty directory.
	 * @param facultyId the ID of the faculty to be removed
	 * @return true if the faculty is removed, false if the faculty is not found in the directory
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyId.equals(facultyDirectory.get(i).getId())) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a 2-dimensional String array that lists the first name, last name, and ID 
	 * of each faculty member.
	 * @return the faculty directory as a 2D string array
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}

	/**
	 * Saves the faculty directory to a file located at the passed file name.
	 * @param fileName the path of the file to write the directory to
	 * @throws IllegalArgumentException if it is unable to write to the file 
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	 
	/**
	 * Returns the faculty with the passed id if it is in the directory.
	 * @param id the id of the faculty to search for
	 * @return the faculty if found, null if not
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < getFacultyDirectory().length; i++) {
			Faculty f = facultyDirectory.get(i);
			if (id.equals(f.getId()))
				return f;
		}
		return null;
	}
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if the password cannot be hashed.
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		// This line is NOT expected to have coverage
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	
}
