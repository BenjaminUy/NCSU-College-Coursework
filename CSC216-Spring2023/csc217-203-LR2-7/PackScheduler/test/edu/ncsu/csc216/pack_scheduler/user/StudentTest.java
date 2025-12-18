package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;

/**
 * Test class for Student.
 * 
 * @author kartik alle
 * @author hunt tynch
 * @author connor hekking
 */
public class StudentTest	{

	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Max credits for Student */
	private static final int MAX_CREDITS = 18;
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

	// This is a block of code that is executed when the StudentTest object is
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
		User s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * Test the Student.hashCode() method.
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		User s2 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		User s3 = new Student("Larry", lastName, id, email, hashPW, MAX_CREDITS);
		User s4 = new Student(firstName, "Trevor", id, email, hashPW, MAX_CREDITS);
		User s5 = new Student(firstName, lastName, "badId", email, hashPW, MAX_CREDITS);
		User s6 = new Student(firstName, lastName, id, "email@ncsu.edu", hashPW, MAX_CREDITS);
		// Test same hashCode
		assertEquals(s1.hashCode(), s2.hashCode());
		// Test different hashCode
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());

	}
	
	/**
	 * Test the full Student Constructor method for validity.
	 */
	@Test
	public void testStudentConstructorFullValid() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		assertEquals("first", s1.getFirstName());
		assertEquals("last", s1.getLastName());
		assertEquals("flast", s1.getId());
		assertEquals("first_last@ncsu.edu", s1.getEmail());
		assertEquals(hashPW, s1.getPassword());
		assertEquals(18, s1.getMaxCredits());
	}
	
	/**
	 * Test invalid parameters for Student Constructor.
	 */
	@Test
	public void testStudentConstructorFullInvalid() {
		Exception firstInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW, MAX_CREDITS));
		Exception lastInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW, MAX_CREDITS));
		Exception idInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW, MAX_CREDITS));
		Exception emailInvalid1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW, MAX_CREDITS));
		Exception emailInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "emailncsu.edu", hashPW, MAX_CREDITS));
		Exception emailInvalid3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "email@ncsuedu", hashPW, MAX_CREDITS));
		Exception emailInvalid4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "e.mail@ncsu", hashPW, MAX_CREDITS));
		Exception passwordInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null, MAX_CREDITS));
		Exception maxCreditsInvalid1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 0));
		Exception maxCreditsInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 20));

		assertEquals("Invalid first name", firstInvalid.getMessage());
		assertEquals("Invalid last name", lastInvalid.getMessage());
		assertEquals("Invalid id", idInvalid.getMessage());

		assertEquals("Invalid email", emailInvalid1.getMessage());
		assertEquals("Invalid email", emailInvalid2.getMessage());
		assertEquals("Invalid email", emailInvalid3.getMessage());
		assertEquals("Invalid email", emailInvalid4.getMessage());

		assertEquals("Invalid password", passwordInvalid.getMessage());
		assertEquals("Invalid max credits", maxCreditsInvalid1.getMessage());
		assertEquals("Invalid max credits", maxCreditsInvalid2.getMessage());

		Exception firstInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, hashPW, MAX_CREDITS));
		Exception lastInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW, MAX_CREDITS));
		Exception idInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW, MAX_CREDITS));
		Exception emailInvalid5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW, MAX_CREDITS));
		Exception passwordInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, "", MAX_CREDITS));

		assertEquals("Invalid first name", firstInvalid2.getMessage());
		assertEquals("Invalid last name", lastInvalid2.getMessage());
		assertEquals("Invalid id", idInvalid2.getMessage());
		assertEquals("Invalid email", emailInvalid5.getMessage());
		assertEquals("Invalid password", passwordInvalid2.getMessage());
	}
	
	/**
	 * Test secondary Student constructor for validity.
	 */
	@Test
	public void testStudentConstructor2Valid() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first", s1.getFirstName());
		assertEquals("last", s1.getLastName());
		assertEquals("flast", s1.getId());
		assertEquals("first_last@ncsu.edu", s1.getEmail());
		assertEquals(hashPW, s1.getPassword());
		assertEquals(18, s1.getMaxCredits());
	}
	
	/**
	 * Test secondary Student constructor with invalid parameters.
	 */
	@Test
	public void testStudentConstructor2Invalid() {
		Exception firstInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW));
		Exception lastInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW));
		Exception idInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW));
		Exception emailInvalid1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW));
		Exception emailInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "emailncsu.edu", hashPW));
		Exception emailInvalid3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "email@ncsuedu", hashPW));
		Exception emailInvalid4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "e.mail@ncsu", hashPW));
		Exception passwordInvalid = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null));

		assertEquals("Invalid first name", firstInvalid.getMessage());
		assertEquals("Invalid last name", lastInvalid.getMessage());
		assertEquals("Invalid id", idInvalid.getMessage());

		assertEquals("Invalid email", emailInvalid1.getMessage());
		assertEquals("Invalid email", emailInvalid2.getMessage());
		assertEquals("Invalid email", emailInvalid3.getMessage());
		assertEquals("Invalid email", emailInvalid4.getMessage());

		assertEquals("Invalid password", passwordInvalid.getMessage());

		Exception firstInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, hashPW));
		Exception lastInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW));
		Exception idInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW));
		Exception emailInvalid5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW));
		Exception passwordInvalid2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, ""));

		assertEquals("Invalid first name", firstInvalid2.getMessage());
		assertEquals("Invalid last name", lastInvalid2.getMessage());
		assertEquals("Invalid id", idInvalid2.getMessage());
		assertEquals("Invalid email", emailInvalid5.getMessage());
		assertEquals("Invalid password", passwordInvalid2.getMessage());
	}
	
	/**
	 * Test Student.setEmail().
	 */
	@Test
	public void testSetEmail() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		// Test that Exceptions are thrown and email isn't updated.
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail(null));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals("first_last@ncsu.edu", s1.getEmail());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail(""));
		assertEquals("Invalid email", e2.getMessage());
		assertEquals("first_last@ncsu.edu", s1.getEmail());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail("emailncsu.edu"));
		assertEquals("Invalid email", e3.getMessage());
		assertEquals("first_last@ncsu.edu", s1.getEmail());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail("email@ncsuedu"));
		assertEquals("Invalid email", e4.getMessage());
		assertEquals("first_last@ncsu.edu", s1.getEmail());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail("e.mail@ncsu"));
		assertEquals("Invalid email", e5.getMessage());
		assertEquals("first_last@ncsu.edu", s1.getEmail());
	}
	
	/**
	 * Test Student.setPassword().
	 */
	@Test
	public void testSetPassword() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		// Test that exceptions are thrown and that password isn't updated.
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setPassword(null));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals(hashPW, s1.getPassword());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setPassword(""));
		assertEquals("Invalid password", e2.getMessage());
		assertEquals(hashPW, s1.getPassword());
	}
	
	/**
	 * Test Student.setMaxCredits().
	 */
	@Test
	public void testSetMaxCredits() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		// Test that exceptions are thrown and maxCredits isn't updated.
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(0));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(18, s1.getMaxCredits());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(20));
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(18, s1.getMaxCredits());
	}
	
	/**
	 * Test Student.setFirstName().
	 */
	@Test
	public void testSetFirstName() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		// Test that exceptions are thrown and firstName isn't updated.
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals("first", s1.getFirstName());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals("first", s1.getFirstName());
	}
	
	/**
	 * Test Student.setLastName().
	 */
	@Test
	public void testSetLastName() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		// Test that exceptions are thrown and lastName isn't updated.
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals("last", s1.getLastName());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("last", s1.getLastName());
	}
	
	/**
	 * Test Student.equals().
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		User s2 = new Student(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		User s3 = new Student("Larry", lastName, id, email, hashPW, MAX_CREDITS);
		User s4 = new Student(firstName, "Trevor", id, email, hashPW, MAX_CREDITS);
		User s5 = new Student(firstName, lastName, "Blank", email, hashPW, MAX_CREDITS);
		User s6 = new Student(firstName, lastName, id, "blank@ncsu.edu", hashPW, MAX_CREDITS);
		User s7 = new Student(firstName, lastName, id, email, "blank", MAX_CREDITS);
		User s8 = new Student(firstName, lastName, id, email, hashPW, 5);
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
	
	/**
	 * Tests Student.compareTo().
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		Student s2 = new Student(firstName, lastName, id, email, hashPW);
		Student s3 = new Student("apple", lastName, id, email, hashPW);
		Student s4 = new Student("zebra", lastName, id, email, hashPW);
		Student s5 = new Student(firstName, "apple", id, email, hashPW);
		Student s6 = new Student(firstName, "zebra", id, email, hashPW);
		Student s7 = new Student(firstName, lastName, "apple", email, hashPW);
		Student s8 = new Student(firstName, lastName, "zebra", email, hashPW);
		
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		assertTrue(s1.compareTo(s3) > 0);
		assertTrue(s1.compareTo(s4) < 0);
		assertTrue(s1.compareTo(s5) > 0);
		assertTrue(s1.compareTo(s6) < 0);
		assertTrue(s1.compareTo(s7) > 0);
		assertTrue(s1.compareTo(s8) < 0);
	}
	
	/**
	 * Test Student.canAdd() method
	 */
	@Test
	public void testCanAdd() {
		// Create new Student with max credits of 6
		Student s = new Student(firstName, lastName, id, email, hashPW, 6);
		
		// Load courses with CourseCatalog for convenient access to valid Course objects
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(validTestFile);
		
		// Add first Course to Student's Schedule
		assertTrue(s.getSchedule().addCourseToSchedule(c.getCourseFromCatalog("CSC116", "001")));
		
		// Attempt to add a null Course
		assertFalse(s.canAdd(null));
		// Attempt to add a duplicate Course
		assertFalse(s.canAdd(c.getCourseFromCatalog("CSC116", "001")));
		// Attempt to add a conflicting Course
		assertFalse(s.canAdd(c.getCourseFromCatalog("CSC226", "001")));
		
		assertTrue(s.canAdd(c.getCourseFromCatalog("CSC216", "001")));
		assertTrue(s.getSchedule().addCourseToSchedule(c.getCourseFromCatalog("CSC216", "001")));
		
		assertEquals(6, s.getSchedule().getScheduleCredits());
		
		// Attempt to add a Course that exceeds Student's max credits
		assertFalse(s.canAdd(c.getCourseFromCatalog("CSC230", "001")));
	}
}
