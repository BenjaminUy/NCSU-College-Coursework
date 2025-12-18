package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Construction class for RegistrationManager.
 * @author hunt tynch
 * @author benjamin uy
 * @author andrew warren
 */
public class RegistrationManager {
	/** Singleton instance of RegistrationManager. */
	private static RegistrationManager instance;
	/** Course Catalog. */
	private CourseCatalog courseCatalog;
	/** Student Directory. */
	private StudentDirectory studentDirectory;
	/** Registrar. */
	private User registrar;
	/** Current User selected. */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** File with registrar info. */
	private static final String PROP_FILE = "registrar.properties";
	
	/**
	 * Constructor method for RegistrationManager creates a Registrar.
	 */
	private RegistrationManager() {
		createRegistrar();
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
	}
	
	/**
	 * Creates a new Registrar.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hash's a String.
	 * @param pw Registrar's Password
	 * @return The Registrar's Password after hashing
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	
	/**
	 * Returns the instance of RegistrationManager if instance is null a new RegistrationManager is created.
	 * @return The current RegistrationManager instance or a new one
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the Course Catalog.
	 * @return courseCatalog field
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the Student Directory.
	 * @return studentDirectory field
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Takes in a User's id and password and checks if they are a student in the Student Directory or
	 * if they are the registrar.
	 * @param id User id
	 * @param password User password
	 * @return true if the password matches a student or registrar
	 * @throws IllegalArgumentException if the student returned from StudentDirectory.getStudentById() is null
	 * 		or if registrar is null
	 */
	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
	
		if (s == null && !registrar.getId().equals(id)) {	// If student is null and registrar.getId is not equal to id, throw IAE
			throw new IllegalArgumentException("User doesn't exist.");
		}
		if (currentUser == null) {
			String localHashPW = hashPW(password);
			if (s != null && s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;	
			} else if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
		}
		return false;
	}

	/**
	 * Logs the User out.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the Current User.
	 * @return the Current User
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears both the Course Catalog and Student Directory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		boolean isStudent = currentUser instanceof Student;
	    if (currentUser == null || !isStudent) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		boolean isStudent = currentUser instanceof Student;
	    if (currentUser == null || !isStudent) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
		boolean isStudent = currentUser instanceof Student;
	    if (currentUser == null || !isStudent) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Inner class creates a registrar.
	 * @author ajwdr
	 * @author hctynch
	 * @author bsuy
	 */
	private static class Registrar extends User {
		/**
		 * Constructs a new Registrar with provided parameters.
		 * @param firstName Registrar's first name.
		 * @param lastName Registrar's last name.
		 * @param id Registrar's id.
		 * @param email Registrar's email.
		 * @param hashPW Registrar's Password.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
}