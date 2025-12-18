package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class tests the StudentIDComparator class
 * @author Benjamin Uy
 */
public class StudentIDComparatorTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	private StudentIDComparator comparator;

	/**
	 * Class constructs the Student and StudentIDComparator objects used in testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Method tests the compare method of StudentIDComparator for various inputs
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		// Additional test cases
		assertTrue(comparator.compare(sOne, sThree) < 0);
		assertTrue(comparator.compare(sOne, sFive) < 0);
		assertTrue(comparator.compare(sFive, sFour) > 0);
		assertTrue(comparator.compare(sOne, sOne) == 0);
	}


}
