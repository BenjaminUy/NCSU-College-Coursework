package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

/**
 * Class tests Faculty
 *
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
public class FacultyTest {

	/** Test Faculty's first name. */
	private String firstName = "first";
	/** Test Faculty's last name */
	private String lastName = "last";
	/** Test Faculty's id */
	private String id = "flast";
	/** Test Faculty's email */
	private String email = "first_last@ncsu.edu";
	/** Test Faculty's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Test Faculty's maxCourses */ 
	private int maxCourses = 3;
	
	// This is a block of code that is executed when the FacultyTest object is
	// created by JUnit. Since we only need to generate the hashed version
	// of the plaintext password once, we want to create it as the StudentTest
	// object is
	// constructed. By automating the hash of the plaintext password, we are
	// not tied to a specific hash implementation. We can change the algorithm
	// easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",3", s1.toString());
	}
	
	/**
	 * Test the Faculty.hashCode() method.
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		User s2 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		User s3 = new Faculty("Larry", lastName, id, email, hashPW, maxCourses);
		User s4 = new Faculty(firstName, "Trevor", id, email, hashPW, maxCourses);
		User s5 = new Faculty(firstName, lastName, "badId", email, hashPW, maxCourses);
		User s6 = new Faculty(firstName, lastName, id, "email@ncsu.edu", hashPW, maxCourses);
		// Test same hashCode
		assertEquals(s1.hashCode(), s2.hashCode());
		// Test different hashCode
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
	}
	
	/**
	 * Test the Faculty Constructor method for validity.
	 */
	@Test
	public void testFacultyConstructorValid() {
		Faculty f = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals("first", f.getFirstName());
		assertEquals("last", f.getLastName());
		assertEquals("flast", f.getId());
		assertEquals("first_last@ncsu.edu", f.getEmail());
		assertEquals(hashPW, f.getPassword());
		assertEquals(3, f.getMaxCourses());
		
		// Test invalid parameters for maxCourses when constructing a Faculty object
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, hashPW, 0));
		assertEquals("Invalid max courses", e.getMessage());
		e = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, hashPW, 4));
		assertEquals("Invalid max courses", e.getMessage());
	}
	
	/**
	 * Test Faculty.equals().
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		User s2 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		User s3 = new Faculty("Larry", lastName, id, email, hashPW, maxCourses);
		User s4 = new Faculty(firstName, "Trevor", id, email, hashPW, maxCourses);
		User s5 = new Faculty(firstName, lastName, "Blank", email, hashPW, maxCourses);
		User s6 = new Faculty(firstName, lastName, id, "blank@ncsu.edu", hashPW, maxCourses);
		User s7 = new Faculty(firstName, lastName, id, email, "blank", maxCourses);
		User s8 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		User s9 = null;
		// Test that equals correctly works for the same object and fields.
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		assertTrue(s1.equals(s1));
		// Test that equals works for different objects and each field.
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
		assertFalse(s1.equals(new Object()));
	}
}
