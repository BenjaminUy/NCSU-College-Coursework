package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class tests the Student class
 * @author Benjamin Uy
 */
public class StudentTest {
	
	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;
	private Student sSix;
	
	private Student alexA;
	private Student alexB;
	private Student alexA2;

	/**
	 * This constructs the Student objects used in testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		alexA = new Student("Alexander", "Arne", 1, 1, 1.0, "aarne");
		alexB = new Student("Alexie", "Arne", 1, 1, 1.0, "aarne");
		alexA2 = new Student("Alexander", "Arne", 2, 1, 1.0, "aarne");
	}

	/**
	 * Tests the setFirst method of Student
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast method of Student
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the setId method of Student
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa method of Student
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests the setUnityID method of Student
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the compareTo method of Student
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		
		// Compare two Student objects with the same last name but different first name
		assertTrue(alexA.compareTo(alexB) < 0);
		
		// Compare two Student objects with the same first & last name, but different ids
		assertTrue(alexA.compareTo(alexA2) < 0);
	}
	
	/**
	 * Tests the Student equals method
	 */
	@Test
	public void testEquals() {
		assertTrue(sFour.equals(sFour));
		assertFalse(sOne.equals(sFive));
	}
	
	/**
	 * Tests the toString method of Student
	 */
	@Test
	public void testToString() {
		assertEquals("OneFirst OneLast, 1, 1, 1.0, oneUnityID", sOne.toString());
		assertEquals("TwoFirst TwoLast, 2, 2, 2.0, twoUnityID", sTwo.toString());
	}
	
	/**
	 * Method tests the hashCode method of the Student class
	 */
	@Test
	public void testHashCode() {
		// Tests Student objects with the same field values, therefore same hashcode
		assertEquals(sOne.hashCode(), sOne.hashCode());
		assertEquals(sOne.hashCode(), sSix.hashCode());
		
		// Tests Student objects with different hashCodes;
		assertNotEquals(sOne.hashCode(), sThree.hashCode());
		assertNotEquals(sFive.hashCode(), sSix.hashCode());
	}
	
	/**
	 * Method tests both the setter and getter methods for Student credit hours
	 */
	@Test
	public void testGetCreditHoursAndSetCreditHours() {
		Student s = new Student("First", "Last", 1, 15, 4.0, "flast");
		assertEquals(15, s.getCreditHours());
		s.setCreditHours(14);
		assertEquals(14, s.getCreditHours());
	}
}
