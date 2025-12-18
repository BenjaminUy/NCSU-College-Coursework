package edu.ncsu.csc216.pack_scheduler.user;

/**
 * A Student is an object that represents an individual student record, which is 
 * obtained from StudentRecordIO which reads a file and creates new Student objects.
 * The object class contains the information: first name, last name, id, email, 
 * password, and max credits.
 * The object class provides functionality for setting and getting said information,
 * constructing a Student object, generating hashCode for Student, comparing two
 * Student objects, and outputting String representation of Student.
 * @author Benjamin Uy
 * @author Hank Lenham
 * @author Noah Anderson
 *
 */
public class Student implements Comparable<Student> {
	
	/**
	 * Constant representing the max number of credits a student can take
	 */
	public static final int MAX_CREDITS = 18;

	/**
	 * String representing the first name of a student
	 */
	private String firstName;
	
	/**
	 * String representing the last name of a student
	 */
	private String lastName;
	
	/**
	 * String representing the id of a student
	 */
	private String id;
	
	/**
	 * String representing the email of a student
	 */
	private String email;
	
	/**
	 * String representing the password of a student
	 */
	private String password;
	
	/**
	 * Integer representing the max number of credits taken by a student
	 */
	private int maxCredits;
	
	/**
	 * Constructs Student object with all fields of Student
	 * @param firstName first name of Student
	 * @param lastName last name of Student
	 * @param id id of Student
	 * @param email email of Student
	 * @param hashPW hashed password of Student
	 * @param maxCredits max credits of Student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * Constructs Student object with all fields but max credits; instead calls the other constructor 
	 * with the default max credits value of 18.
	 * @param firstName first name of Student
	 * @param lastName last name of Student
	 * @param id id of Student
	 * @param email email of Student
	 * @param hashPW hashed password of Student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}
	
	/**
	 * Gets the first name of Student
	 * @return String for first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of Student
	 * @return String for last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the id of Student
	 * @return String for id
	 */	
	public String getId() {
		return id;
	}

	/**
	 * Gets the email of Student
	 * @return String for email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the password of Student
	 * @return String for password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the max credits hours of Student
	 * @return int for max credits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	/**
	 * Sets first name of Student, and throws an exception if firstName is invalid
	 * @param firstName first name of Student
	 * @throws IllegalArgumentException if firstName is null or an empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of Student, and throws an exception if lastName is invalid
	 * @param lastName last name of Student
	 * @throws IllegalArgumentException if lastName is null or an empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
	}
		this.lastName = lastName;
	}

	/**
	 * Sets the id of Student, and throws an exception if id is invalid
	 * @param id id of Student
	 * @throws IllegalArgumentException if id is null or an empty string
	 */
	private void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
	}
		this.id = id;
	}
	
	/**
	 * Sets the email of Student, and throws an exception if email is invalid
	 * @param email email of Student
	 * @throws IllegalArgumentException if email is null or an empty string
	 * @throws IllegalArgumentException if email doesn't contain '@' or '.'
	 * @throws IllegalArgumentException if the index of the last '.' is before
	 * 		the index of the last '@' 
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		} else if (email.indexOf('@') < 0) {
			throw new IllegalArgumentException("Invalid email");
		} else if (email.indexOf('.') < 0) {
			throw new IllegalArgumentException("Invalid email");
		} else if (email.lastIndexOf('.') < email.lastIndexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets the password of Student, and throws an exception if password is invalid
	 * @param password password of Student
	 * @throws IllegalArgumentException if password is null or an empty string
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	} 

	/**
	 * Sets the max credit hours of Student, and throws an exception if maxCredits is invalid
	 * @param maxCredits maximum number of credit hours taken by Student
	 * @throws IllegalArgumentException if less than three or greater than 18 (MAX_CREDITS)
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated value String for all Student fields to match the data format
	 * when stored in a file: firstName,lastName,id,email,hashedPassword,maxCredits
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password + "," + maxCredits;
	}

	/**
	 * Generates a hashCode for Student using all fields
	 * @return hashCode integer for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality; equality is determined
	 * when both Student objects have equal field values.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields of the Student class
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		return password.equals(other.password);
	}

	/**
	 * Method compares the given Student object to this and returns an integer (-1, 0, or 1) indicating
	 * the order of this Student object in relation to the given Student object
	 * @param s Student object that is being compared to this
	 * @return 0 if both Student objects are equal, 1 if this Student object should be ordered after 
	 * 		the given Student object, and -1 if this Student object should be ordered before the
	 * 		given Student object 
	 */
	@Override
	public int compareTo(Student s) {
		String otherLName = s.getLastName();
		String otherFName = s.getFirstName();
		String otherId = s.getId();
		
		if (!this.lastName.equals(otherLName)) {
			if (this.lastName.compareToIgnoreCase(otherLName) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (!this.firstName.equals(otherFName)) {
			if (this.firstName.compareToIgnoreCase(otherFName) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (!this.id.equals(otherId)) {
			if (this.id.compareToIgnoreCase(otherId) > 0) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}
}