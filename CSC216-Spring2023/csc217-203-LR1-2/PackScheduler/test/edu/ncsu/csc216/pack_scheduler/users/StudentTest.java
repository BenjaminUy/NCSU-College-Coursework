package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the Student object.
 * @author SarahHeckman; 
 * @author Hank Lenham;
 * @author Noah Anderson; 
 * @author Benjamin Uy
 */
public class StudentTest { 
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed hashPW */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext hashPW once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext hashPW, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "hashPW";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	/**
	 * Tests a valid instance of a Student object that includes all fields
	 * including max credits.
	 */
	@Test
	public void testFullStudentValid() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		assertEquals(firstName, s1.getFirstName());
		assertEquals(lastName, s1.getLastName());
		assertEquals(id, s1.getId());
		assertEquals(email, s1.getEmail());
		assertEquals(hashPW, s1.getPassword());
		assertEquals(18, s1.getMaxCredits());

	}

	/**
	 * Tests an invalid instance of a Student object where at least one parameter
	 * constructed is invalid, including max credits.
	 * @throws IllegalArgumentException when an invalid student is constructed.
	 */
	@Test
	public void testFullStudentInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Student(null, lastName, id, email, hashPW, 18));
		assertEquals("Invalid first name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Student("", lastName, id, email, hashPW, 18));
		assertEquals("Invalid first name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW, 18));
		assertEquals("Invalid last name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW, 18));
		assertEquals("Invalid last name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW, 18));
		assertEquals("Invalid id", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW, 18));
		assertEquals("Invalid id", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW, 18));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW, 18));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmithncsu.edu", hashPW, 18));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith@ncsuedu", hashPW, 18));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith.ncsu@edu", hashPW, 18));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null, 18));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, "", 18));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 2));
		assertEquals("Invalid max credits", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 19));
		assertEquals("Invalid max credits", e1.getMessage());
	}


	/**
	 * Tests a valid instance of a Student object that includes all fields
	 * except max credits.
	 */
	@Test
	public void testStudentValid() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(firstName, s1.getFirstName());
		assertEquals(lastName, s1.getLastName());
		assertEquals(id, s1.getId());
		assertEquals(email, s1.getEmail());
		assertEquals(hashPW, s1.getPassword());
	}
	
	/**
	 * Tests an invalid instance of a Student object where at least one parameter
	 * constructed is invalid, without max credits.
	 * @throws IllegalArgumentException when an invalid student is constructed.
	 */
	@Test
	public void testStudentInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Student(null, lastName, id, email, hashPW));
		assertEquals("Invalid first name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Student("", lastName, id, email, hashPW));
		assertEquals("Invalid first name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW));
		assertEquals("Invalid last name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW));
		assertEquals("Invalid last name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW));
		assertEquals("Invalid id", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW));
		assertEquals("Invalid id", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmithncsu.edu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith@ncsuedu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith.ncsu@edu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, ""));
		assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Tests valid input for setting the first name field.
	 */
	@Test
	public void testSetFirstName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Student("", lastName, id, email, hashPW));
		assertEquals(firstName, s1.getFirstName());
	}
	/**
	 * Tests valid input for setting the last name field.
	 */
	@Test
	public void testSetLastName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals(lastName, s1.getLastName());
	}

	/**
	 * Tests valid input for setting the email field.
	 */
	@Test
	public void testSetemail() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmithncsu.edu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith@ncsuedu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "sesmith.ncsu@edu", hashPW));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals(email, s1.getEmail());
	}


	/**
	 * Tests valid input for setting the max credits field.
	 */
	@Test
	public void testSetMaxCredits() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setMaxCredits(2));
		assertEquals("Invalid max credits", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setMaxCredits(19));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(18, s1.getMaxCredits());
	}


	/**
	 * Tests valid input for setting the hashPW field.
	 */
	@Test
	public void testSethashPW() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setPassword(null));
		assertEquals("Invalid password", e1.getMessage());
		e1 = assertThrows(IllegalArgumentException.class,
				() -> s1.setPassword(""));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals(hashPW, s1.getPassword());
	}

	/**
	 * Tests the equals method.
	 */
	@Test
	public void testEqualsObject() {
		//Creating students which are the same
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, 18);
		//Creating students which are different
		Student s3 = new Student(firstName, "Different", id, email, hashPW, 18);
		Student s4 = new Student(firstName, lastName, "sesmith5", email, hashPW, 18);
		Student s5 = new Student(firstName, lastName, id, email, "test_hashPW", 18);
		Student s6 = new Student(firstName, lastName, id, email, hashPW, 17);
		Student s7 = new Student("Jane", lastName, id, email, hashPW, 18);
		Student s8 = new Student(firstName, lastName, id, "last_first@ncsu.edu", hashPW, 18);
		
		//Tests for equality in both directions.
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		//Test for equality for comparing the same object
		assertTrue(s1.equals(s1));
		
		//Test for each other field being different
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		//Test with a null object and string
		assertNotNull(s1 == null);
		assertFalse("Student".equals(s1.toString()));
		
	}
	

	
	/**
	 * Tests hash code and equality.
	 */
	@Test
	public void testHashCode() {
		//Creating new students, the first two of which are the same.
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, 18);
		//Creating students which are different
		Student s3 = new Student(firstName, "Different", id, email, hashPW, 18);
		Student s4 = new Student(firstName, lastName, "sesmith5", email, hashPW, 18);
		Student s5 = new Student(firstName, lastName, id, email, "test_hashPW", 18);
		Student s6 = new Student(firstName, lastName, id, email, hashPW, 17);
		
		//Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//Test for all the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
	}
	
	/**
	 * Tests toString.
	 */
	@Test
	public void testToString() {
		//Creating a student object
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		//Creating a string that should match
		String st1 = "first,last,flast,first_last@ncsu.edu," + hashPW + ",18";
		//Testing if the two are equal.
		assertEquals(st1, s1.toString());
		
		Student s2 = new Student(firstName, lastName, id, email, hashPW, 12);
		
		String st2 = "first,last,flast,first_last@ncsu.edu," + hashPW + ",12";
		assertEquals(st2, s2.toString());
	} 
	
	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		//Creating two students that are equal
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, 18);
		//Creating multiple different students
		Student s3 = new Student("daniel", "anderson", id, email, hashPW, 18);
		Student s4 = new Student("noah", "anderson", id, email, hashPW, 18);
		Student s5 = new Student("john", "brown", "jbrown", email, hashPW, 18);
		Student s6 = new Student("john", "brown", "jbrown2", email, hashPW, 18);
		
		//Testing students based on last name
		//These should return 0 as s1 and s2 are identical.
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		//These should return 1 and -1 respectively as "last" is later in the alphabet
		//than anderson.
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s3.compareTo(s1));
		
		//Testing students based on first name
		//These should return -1 and 1 respectively as "daniel" is earlier in
		//the alphabet than noah.
		assertEquals(-1, s3.compareTo(s4));
		assertEquals(1, s4.compareTo(s3));
		
		//Testing students based on ID
		//These should return -1 and 1 respectively as "jbrown" is less than "jbrown2"
		assertEquals(-1, s5.compareTo(s6));
		assertEquals(1, s6.compareTo(s5));
	}
	
	/**
	 * Second test for compareTo()
	 */
	@Test
	public void testCompareTo2() {
		// Creating two students that are equal
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 18);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, 18);
		
		// Testing students based on last name
		//These should return 0 as s1 and s2 are identical.
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		
		// Creating Student with a last name lexicographically before lastName
		Student s3 = new Student(firstName, "first", id, email, hashPW, 18);
		//Creating Student with a last name alphabetically after lastName
		Student s4 = new Student(firstName, "second", id, email, hashPW, 18);
		
		// The last name of s1 ("last") is lexicographically after the last name of s3 ("first")
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s3.compareTo(s1));
		// The last name of s1 ("last") is lexicographically before the last name of s4 ("second")
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s4.compareTo(s1));
		
		// Creating Student with a first name lexicographically before firstName
		Student s5 = new Student("Allen", lastName, id, email, hashPW, 18);
		//Creating Student with a first name lexicographically after firstName
		Student s6 = new Student("Monnie", lastName, id, email, hashPW, 18);
		
		// The first name of s1 ("first") is lexicographically after the first name of s5 ("Allen")
		assertEquals(1, s1.compareTo(s5));
		assertEquals(-1, s5.compareTo(s1));
		// The first name of s1 ("first") is lexicographically before the first name of s6 ("Monnie")
		assertEquals(-1, s1.compareTo(s6));
		assertEquals(1, s6.compareTo(s1));
		
		// Creating Student with a unity id lexicographically before id
		Student s7 = new Student(firstName, lastName, "fastl", email, hashPW, 18);
		// Creating Student with a unity id lexicographically after id
		Student s8 = new Student(firstName, lastName, "lastf", email, hashPW, 18);
		
		// The unity id of s1 ("flast") is lexicographically after the unity id of s7 ("fastl")
		assertEquals(1, s1.compareTo(s7));
		assertEquals(-1, s7.compareTo(s1));
		// The unity id of s1 ("flast") is lexicographically before the unity id of s8 ("lastf")
		assertEquals(-1, s1.compareTo(s8));
		assertEquals(1, s8.compareTo(s1));
	}
}